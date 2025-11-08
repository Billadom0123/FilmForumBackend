package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Variety.VarietyPO;
import com.example.web.filmforum.Model.Variety.VarietyGuest;
import com.example.web.filmforum.Model.Variety.VarietySeason;
import com.example.web.filmforum.Model.Award.AwardRecordPO;
import com.example.web.filmforum.Model.Common.LikePO;
import com.example.web.filmforum.Model.Common.FavoritePO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.*;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.example.web.filmforum.Model.Common.RatingStatPO;

@Service
public class VarietyService {

    @Autowired
    private VarietyRepository varietyRepository;
    @Autowired
    private VarietyGuestRepository varietyGuestRepository;
    @Autowired
    private VarietySeasonRepository varietySeasonRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingStatRepository ratingStatRepository;
    @Autowired
    private RatingRepository ratingRepository; // 新增注入
    @Autowired
    private AwardRecordRepository awardRecordRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActorRepository actorRepository;

    private UserPO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) user = userRepository.findByEmail(name);
        return user;
    }

    public DataResponse list(String tag, Integer year, Double rating, String actor, String award, Pageable pageable) {
        Page<VarietyPO> page = varietyRepository.queryVarieties(null, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        List<Long> ids = new ArrayList<>();
        for (VarietyPO item : page.getContent()) {
            ids.add(item.getId());
        }
        Map<Long, Double> avgMap = new HashMap<>();
        if (!ids.isEmpty()) {
            List<RatingStatPO> stats = ratingStatRepository.findByTargetTypeAndTargetIdIn("VARIETY", ids);
            for (RatingStatPO rs : stats) {
                avgMap.put(rs.getTargetId(), rs.getRatingAvg());
            }
        }
        for (VarietyPO v : page.getContent()) {
            Double avg = avgMap.getOrDefault(v.getId(), 0.0);
            JSONObject obj = H.build()
                    .put("id", v.getId())
                    .put("title", v.getTitle())
                    .put("year", v.getYear())
                    .put("tags", v.getTags())
                    .put("rating", avg)
                    .put("poster", v.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("varieties", data).put("pagination", pag.toJSON()).toJson());
    }

    public DataResponse detail(Long id) {
        VarietyPO v = varietyRepository.findById(id).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        v.setViews(v.getViews() + 1);
        varietyRepository.save(v);
        RatingService.RatingSummary sum = ratingService.summary("VARIETY", v.getId());
        JSONArray hostArr = new JSONArray();
        if (v.getHost() != null) {
            hostArr.add(
                    H.build()
                            .put("id", v.getHost().getId())
                            .put("name", v.getHost().getName())
                            .put("avatar", v.getHost().getAvatar())
                            .put("description", "Host")
                            .toJson()
            );
        }
        List<VarietyGuest> rels = varietyGuestRepository.findByVariety_Id(v.getId());
        JSONArray guests = new JSONArray();
        for (VarietyGuest g : rels) {
            if (g.getActor() == null) continue;
            guests.add(
                    H.build()
                            .put("id", g.getActor().getId())
                            .put("name", g.getActor().getName())
                            .put("avatar", g.getActor().getAvatar())
                            .put("description", g.getDescription())
                            .toJson()
            );
        }
        List<VarietySeason> seasonsList = varietySeasonRepository.findByVariety_IdOrderByNumberAsc(v.getId());
        JSONArray seasons = new JSONArray();
        for (VarietySeason s : seasonsList) {
            seasons.add(
                    H.build()
                            .put("id", s.getId())
                            .put("number", s.getNumber())
                            .put("title", s.getTitle())
                            .put("episodes", s.getEpisodes())
                            .put("year", s.getYear())
                            .toJson()
            );
        }
        List<AwardRecordPO> awardRecords = awardRecordRepository.findByTargetIdAndAward_TargetType(v.getId(), "VARIETY");
        JSONArray awards = new JSONArray();
        for (AwardRecordPO ar : awardRecords) {
            awards.add(
                    H.build()
                            .put("id", ar.getId())
                            .put("name", ar.getAward() != null ? ar.getAward().getName() : null)
                            .put("year", ar.getYear())
                            .put("status", ar.getStatus())
                            .put("note", ar.getNote())
                            .toJson()
            );
        }
        long likes = likeRepository.countByTargetTypeAndTargetId("VARIETY", v.getId());
        UserPO me = currentUser();
        boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", v.getId());
        boolean isFavorited = me != null && favoriteRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", v.getId());
        return DataResponse.success(
                H.build()
                        .put("id", v.getId())
                        .put("title", v.getTitle())
                        .put("original_title", v.getOriginalTitle())
                        .put("year", v.getYear())
                        .put("episodes", v.getEpisodes())
                        .put("tags", v.getTags())
                        .put("rating", sum.avg())
                        .put("poster", v.getPoster())
                        .put("summary", v.getSummary())
                        .put("country", v.getCountry())
                        .put("language", v.getLanguage())
                        .put("trailer", v.getTrailer())
                        .put("photos", v.getPhotos())
                        .put("host", hostArr)
                        .put("guests", guests)
                        .put("awards", awards)
                        .put("seasons", seasons)
                        .put("views", v.getViews())
                        .put("likes", likes)
                        .put("isLiked", isLiked)
                        .put("isFavorited", isFavorited)
                        .toJson()
        );
    }

    public DataResponse search(String keyword, String tag, Integer year, String actor, String award, Double rating, Pageable pageable) {
        Page<VarietyPO> page = varietyRepository.queryVarieties(keyword, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        List<Long> ids = new ArrayList<>();
        for (VarietyPO item : page.getContent()) {
            ids.add(item.getId());
        }
        Map<Long, Double> avgMap = new HashMap<>();
        if (!ids.isEmpty()) {
            List<RatingStatPO> stats = ratingStatRepository.findByTargetTypeAndTargetIdIn("VARIETY", ids);
            for (RatingStatPO rs : stats) {
                avgMap.put(rs.getTargetId(), rs.getRatingAvg());
            }
        }
        for (VarietyPO v : page.getContent()) {
            Double avg = avgMap.getOrDefault(v.getId(), 0.0);
            JSONObject obj = H.build()
                    .put("id", v.getId())
                    .put("title", v.getTitle())
                    .put("year", v.getYear())
                    .put("tags", v.getTags())
                    .put("rating", avg)
                    .put("poster", v.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("varieties", data).put("pagination", pag.toJSON()).toJson());
    }

    public DataResponse like(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        VarietyPO v = varietyRepository.findById(id).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", id)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        LikePO like = new LikePO();
        like.setUser(me);
        like.setTargetType("VARIETY");
        like.setTargetId(id);
        likeRepository.save(like);
        return DataResponse.ok();
    }

    public DataResponse unlike(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        VarietyPO v = varietyRepository.findById(id).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        LikePO like = likeRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", id);
        if (like == null) return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        likeRepository.delete(like);
        return DataResponse.ok();
    }

    // 新增：评分提交/更新（统一RatingService）
    public DataResponse rate(Long varietyId, Integer score, String comment) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        VarietyPO variety = varietyRepository.findById(varietyId).orElse(null);
        if (variety == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (score == null || score < 1 || score > 10) return DataResponse.failure(CommonErr.PARAM_WRONG);
        ratingService.rate("VARIETY", varietyId, me, score, comment);
        return DataResponse.ok();
    }

    // 新增：获取评分和海报（统一统计）
    public DataResponse ratingPoster(Long varietyId) {
        VarietyPO v = varietyRepository.findById(varietyId).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        RatingService.RatingSummary s = ratingService.summary("VARIETY", varietyId);
        return DataResponse.success(
                H.build()
                        .put("id", v.getId())
                        .put("rating", s.avg())
                        .put("poster", v.getPoster())
                        .toJson()
        );
    }

    // 新增：收藏/取消收藏
    public DataResponse favorite(Long varietyId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        VarietyPO v = varietyRepository.findById(varietyId).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (favoriteRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", varietyId)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        FavoritePO fav = new FavoritePO();
        fav.setUser(me);
        fav.setTargetType("VARIETY");
        fav.setTargetId(varietyId);
        favoriteRepository.save(fav);
        return DataResponse.ok();
    }

    public DataResponse unfavorite(Long varietyId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        VarietyPO v = varietyRepository.findById(varietyId).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        FavoritePO fav = favoriteRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", varietyId);
        if (fav == null) return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        favoriteRepository.delete(fav);
        return DataResponse.ok();
    }

    public DataResponse add(JSONObject body) {
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String title = body.getString("title");
        Integer year = body.getInteger("year");
        if (title == null || title.isBlank() || year == null) return DataResponse.failure(CommonErr.PARAM_WRONG);

        Long id = body.getLong("id");
        VarietyPO v = null;
        boolean isUpdate = false;
        if (id != null) {
            v = varietyRepository.findById(id).orElse(null);
            if (v != null) isUpdate = true;
        }
        if (v == null) v = new VarietyPO();

        v.setTitle(title);
        if (body.containsKey("original_title")) v.setOriginalTitle(body.getString("original_title"));
        v.setYear(year);
        if (body.containsKey("episodes")) v.setEpisodes(body.getInteger("episodes") == null ? 0 : body.getInteger("episodes"));
        if (body.containsKey("poster")) v.setPoster(body.getString("poster"));
        if (body.containsKey("summary")) v.setSummary(body.getString("summary"));
        if (body.containsKey("country")) v.setCountry(body.getString("country"));
        if (body.containsKey("language")) v.setLanguage(body.getString("language"));
        if (body.containsKey("trailer")) v.setTrailer(body.getString("trailer"));
        if (body.containsKey("photos")) {
            JSONArray arr = body.getJSONArray("photos");
            List<String> photos = new ArrayList<>();
            if (arr != null && !arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    Object o = arr.get(i);
                    photos.add(o == null ? null : o.toString());
                }
            }
            v.setPhotos(photos);
        }
        if (body.containsKey("tags")) {
            JSONArray arr = body.getJSONArray("tags");
            List<String> tags = new ArrayList<>();
            if (arr != null && !arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    Object o = arr.get(i);
                    tags.add(o == null ? null : o.toString());
                }
            }
            v.setTags(tags);
        }
        if (body.containsKey("views")) v.setViews(body.getInteger("views") == null ? 0 : body.getInteger("views"));

        // host
        if (body.containsKey("host")) {
            Long hid = body.getLong("host");
            if (hid == null) v.setHost(null); else actorRepository.findById(hid).ifPresent(v::setHost);
        }

        VarietyPO saved = varietyRepository.save(v);

        // guests
        if (isUpdate && body.containsKey("guests")) {
            List<VarietyGuest> olds = varietyGuestRepository.findByVariety_Id(saved.getId());
            if (olds != null && !olds.isEmpty()) varietyGuestRepository.deleteAll(olds);
        }
        if (body.containsKey("guests")) {
            JSONArray arr = body.getJSONArray("guests");
            if (arr != null && !arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject g = arr.getJSONObject(i);
                    if (g == null) continue;
                    Long aid = g.getLong("id");
                    if (aid == null) continue;
                    actorRepository.findById(aid).ifPresent(actor -> {
                        VarietyGuest vg = new VarietyGuest();
                        vg.setVariety(saved);
                        vg.setActor(actor);
                        vg.setDescription(g.getString("description"));
                        varietyGuestRepository.save(vg);
                    });
                }
            }
        }

        // seasons
        if (isUpdate && body.containsKey("seasons")) {
            List<VarietySeason> olds = varietySeasonRepository.findByVariety_IdOrderByNumberAsc(saved.getId());
            if (olds != null && !olds.isEmpty()) varietySeasonRepository.deleteAll(olds);
        }
        if (body.containsKey("seasons")) {
            JSONArray arr = body.getJSONArray("seasons");
            if (arr != null && !arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject s = arr.getJSONObject(i);
                    if (s == null) continue;
                    VarietySeason season = new VarietySeason();
                    season.setVariety(saved);
                    season.setNumber(s.getInteger("number") == null ? 0 : s.getInteger("number"));
                    season.setTitle(s.getString("title"));
                    season.setEpisodes(s.getInteger("episodes") == null ? 0 : s.getInteger("episodes"));
                    season.setYear(s.getInteger("year") == null ? 0 : s.getInteger("year"));
                    varietySeasonRepository.save(season);
                }
            }
        }

        return DataResponse.success(H.build().put("id", saved.getId()).put("updated", isUpdate).toJson());
    }

    // 新增：删除综艺（仅管理员在控制器层可访问）
    public DataResponse delete(Long varietyId) {
        VarietyPO v = varietyRepository.findById(varietyId).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        // 清理嘉宾关联
        List<VarietyGuest> guests = varietyGuestRepository.findByVariety_Id(varietyId);
        if (guests != null && !guests.isEmpty()) varietyGuestRepository.deleteAll(guests);
        // 清理季信息
        List<VarietySeason> seasons = varietySeasonRepository.findByVariety_IdOrderByNumberAsc(varietyId);
        if (seasons != null && !seasons.isEmpty()) varietySeasonRepository.deleteAll(seasons);
        // 清理点赞/收藏/评分/评分统计
        likeRepository.deleteByTargetTypeAndTargetId("VARIETY", varietyId);
        favoriteRepository.deleteByTargetTypeAndTargetId("VARIETY", varietyId);
        ratingRepository.deleteByTargetTypeAndTargetId("VARIETY", varietyId);
        RatingStatPO stat = ratingStatRepository.findByTargetTypeAndTargetId("VARIETY", varietyId);
        if (stat != null) ratingStatRepository.delete(stat);
        // 清理奖项记录
        List<AwardRecordPO> awards = awardRecordRepository.findByTargetIdAndAward_TargetType(varietyId, "VARIETY");
        if (awards != null && !awards.isEmpty()) awardRecordRepository.deleteAll(awards);
        // 删除主体
        varietyRepository.delete(v);
        return DataResponse.ok();
    }

    public DataResponse reviews(Long varietyId, Pageable pageable) {
        VarietyPO v = varietyRepository.findById(varietyId).orElse(null);
        if (v == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        var page = ratingRepository.findByTargetTypeAndTargetIdAndCommentIsNotNull("VARIETY", varietyId, pageable);
        JSONArray data = new JSONArray();
        for (var r : page.getContent()) {
            UserPO u = r.getUser();
            data.add(
                    H.build()
                            .put("id", r.getId())
                            .put("score", r.getScore())
                            .put("comment", r.getComment())
                            .put("createTime", r.getCreateTime())
                            .put("updateTime", r.getUpdateTime())
                            .put("user", u == null ? null : H.build()
                                    .put("id", u.getId())
                                    .put("username", u.getUsername())
                                    .put("nickname", u.getNickname())
                                    .put("avatar", u.getAvatar())
                                    .toJson())
                            .toJson()
            );
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("reviews", data).put("pagination", pag.toJSON()).toJson());
    }
}
