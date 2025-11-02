package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.TvShow.TvShowPO;
import com.example.web.filmforum.Model.TvShow.TvShowActor;
import com.example.web.filmforum.Model.TvShow.TvShowSeason;
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
import java.util.stream.Collectors;

@Service
public class TvShowService {

    @Autowired
    private TvShowRepository tvShowRepository;
    @Autowired
    private TvShowActorRepository tvShowActorRepository;
    @Autowired
    private TvShowSeasonRepository tvShowSeasonRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private RatingStatRepository ratingStatRepository;
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
        Page<TvShowPO> page = tvShowRepository.queryShows(null, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        java.util.List<Long> ids = page.getContent().stream().map(TvShowPO::getId).collect(Collectors.toList());
        java.util.Map<Long, Double> avgMap = new java.util.HashMap<>();
        if (!ids.isEmpty()) {
            for (var rs : ratingStatRepository.findByTargetTypeAndTargetIdIn("TVSHOW", ids)) {
                avgMap.put(rs.getTargetId(), rs.getRatingAvg());
            }
        }
        for (TvShowPO show : page.getContent()) {
            Double avg = avgMap.getOrDefault(show.getId(), 0.0);
            JSONObject obj = H.build()
                    .put("id", show.getId())
                    .put("title", show.getTitle())
                    .put("year", show.getYear())
                    .put("tags", show.getTags())
                    .put("rating", avg)
                    .put("poster", show.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("tvshows", data).put("pagination", pag.toJSON()).toJson());
    }

    public DataResponse detail(Long id) {
        TvShowPO show = tvShowRepository.findById(id).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        show.setViews(show.getViews() + 1);
        tvShowRepository.save(show);
        RatingService.RatingSummary sum = ratingService.summary("TVSHOW", show.getId());
        JSONArray directorArr = new JSONArray();
        if (show.getDirector() != null) {
            directorArr.add(
                    H.build()
                            .put("id", show.getDirector().getId())
                            .put("name", show.getDirector().getName())
                            .put("avatar", show.getDirector().getAvatar())
                            .put("description", "Director")
                            .toJson()
            );
        }
        java.util.List<com.example.web.filmforum.Model.TvShow.TvShowActor> rels = tvShowActorRepository.findByTvShow_Id(show.getId());
        JSONArray actors = new JSONArray();
        for (com.example.web.filmforum.Model.TvShow.TvShowActor ta : rels) {
            if (ta.getActor() == null) continue;
            actors.add(
                    H.build()
                            .put("id", ta.getActor().getId())
                            .put("name", ta.getActor().getName())
                            .put("avatar", ta.getActor().getAvatar())
                            .put("description", ta.getDescription())
                            .toJson()
            );
        }
        java.util.List<com.example.web.filmforum.Model.TvShow.TvShowSeason> seasonsList = tvShowSeasonRepository.findByTvShow_IdOrderByNumberAsc(show.getId());
        JSONArray seasons = new JSONArray();
        for (com.example.web.filmforum.Model.TvShow.TvShowSeason s : seasonsList) {
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
        java.util.List<com.example.web.filmforum.Model.Award.AwardRecordPO> awardRecords = awardRecordRepository.findByTargetIdAndAward_TargetType(show.getId(), "TVSHOW");
        JSONArray awards = new JSONArray();
        for (com.example.web.filmforum.Model.Award.AwardRecordPO ar : awardRecords) {
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
        long likes = likeRepository.countByTargetTypeAndTargetId("TVSHOW", show.getId());
        return DataResponse.success(
                H.build()
                        .put("id", show.getId())
                        .put("title", show.getTitle())
                        .put("original_title", show.getOriginalTitle())
                        .put("year", show.getYear())
                        .put("episodes", show.getEpisodes())
                        .put("tags", show.getTags())
                        .put("rating", sum.avg())
                        .put("poster", show.getPoster())
                        .put("summary", show.getSummary())
                        .put("country", show.getCountry())
                        .put("language", show.getLanguage())
                        .put("trailer", show.getTrailer())
                        .put("photos", show.getPhotos())
                        .put("director", directorArr)
                        .put("actors", actors)
                        .put("awards", awards)
                        .put("seasons", seasons)
                        .put("views", show.getViews())
                        .put("likes", likes)
                        .toJson()
        );
    }

    public DataResponse search(String keyword, String tag, Integer year, String actor, String award, Double rating, Pageable pageable) {
        Page<TvShowPO> page = tvShowRepository.queryShows(keyword, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        java.util.List<Long> ids = page.getContent().stream().map(TvShowPO::getId).collect(Collectors.toList());
        java.util.Map<Long, Double> avgMap = new java.util.HashMap<>();
        if (!ids.isEmpty()) {
            for (var rs : ratingStatRepository.findByTargetTypeAndTargetIdIn("TVSHOW", ids)) {
                avgMap.put(rs.getTargetId(), rs.getRatingAvg());
            }
        }
        for (TvShowPO show : page.getContent()) {
            Double avg = avgMap.getOrDefault(show.getId(), 0.0);
            JSONObject obj = H.build()
                    .put("id", show.getId())
                    .put("title", show.getTitle())
                    .put("year", show.getYear())
                    .put("tags", show.getTags())
                    .put("rating", avg)
                    .put("poster", show.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(
                H.build()
                        .put("tvshows", data)
                        .put("pagination", pag.toJSON())
                        .toJson()
        );
    }

    public DataResponse like(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        TvShowPO show = tvShowRepository.findById(id).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "TVSHOW", id)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        LikePO like = new LikePO();
        like.setUser(me);
        like.setTargetType("TVSHOW");
        like.setTargetId(id);
        likeRepository.save(like);
        return DataResponse.ok();
    }

    public DataResponse unlike(Long id) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        TvShowPO show = tvShowRepository.findById(id).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        LikePO like = likeRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "TVSHOW", id);
        if (like == null) return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        likeRepository.delete(like);
        return DataResponse.ok();
    }

    // 新增：评分提交/更新（改用统一RatingService）
    public DataResponse rate(Long showId, Integer score, String comment) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        TvShowPO show = tvShowRepository.findById(showId).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (score == null || score < 1 || score > 10) return DataResponse.failure(CommonErr.PARAM_WRONG);
        ratingService.rate("TVSHOW", showId, me, score, comment);
        return DataResponse.ok();
    }

    // 新增：获取评分和海报（统一统计）
    public DataResponse ratingPoster(Long showId) {
        TvShowPO show = tvShowRepository.findById(showId).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        RatingService.RatingSummary s = ratingService.summary("TVSHOW", showId);
        return DataResponse.success(
                H.build()
                        .put("id", show.getId())
                        .put("rating", s.avg())
                        .put("poster", show.getPoster())
                        .toJson()
        );
    }

    // 新增：收藏/取消收藏
    public DataResponse favorite(Long showId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        TvShowPO show = tvShowRepository.findById(showId).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (favoriteRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "TVSHOW", showId)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        FavoritePO fav = new FavoritePO();
        fav.setUser(me);
        fav.setTargetType("TVSHOW");
        fav.setTargetId(showId);
        favoriteRepository.save(fav);
        return DataResponse.ok();
    }

    public DataResponse unfavorite(Long showId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        TvShowPO show = tvShowRepository.findById(showId).orElse(null);
        if (show == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        FavoritePO fav = favoriteRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "TVSHOW", showId);
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
        TvShowPO show = null;
        boolean isUpdate = false;
        if (id != null) {
            show = tvShowRepository.findById(id).orElse(null);
            if (show != null) isUpdate = true;
        }
        if (show == null) show = new TvShowPO();

        show.setTitle(title);
        if (body.containsKey("original_title")) show.setOriginalTitle(body.getString("original_title"));
        show.setYear(year);
        if (body.containsKey("episodes")) show.setEpisodes(body.getInteger("episodes") == null ? 0 : body.getInteger("episodes"));
        if (body.containsKey("poster")) show.setPoster(body.getString("poster"));
        if (body.containsKey("summary")) show.setSummary(body.getString("summary"));
        if (body.containsKey("country")) show.setCountry(body.getString("country"));
        if (body.containsKey("language")) show.setLanguage(body.getString("language"));
        if (body.containsKey("trailer")) show.setTrailer(body.getString("trailer"));
        if (body.containsKey("photos")) {
            JSONArray arr = body.getJSONArray("photos");
            show.setPhotos(arr == null ? new ArrayList<>() : arr.stream().map(Object::toString).collect(Collectors.toList()));
        }
        if (body.containsKey("tags")) {
            JSONArray arr = body.getJSONArray("tags");
            show.setTags(arr == null ? new ArrayList<>() : arr.stream().map(Object::toString).collect(Collectors.toList()));
        }
        if (body.containsKey("views")) show.setViews(body.getInteger("views") == null ? 0 : body.getInteger("views"));

        if (body.containsKey("director_id")) {
            Long dirId = body.getLong("director_id");
            if (dirId == null) show.setDirector(null); else actorRepository.findById(dirId).ifPresent(show::setDirector);
        }

        TvShowPO saved = tvShowRepository.save(show);

        // 更新演员关联（仅当请求包含 actors）
        if (isUpdate && body.containsKey("actors")) {
            var olds = tvShowActorRepository.findByTvShow_Id(saved.getId());
            if (olds != null && !olds.isEmpty()) tvShowActorRepository.deleteAll(olds);
        }
        if (body.containsKey("actors")) {
            JSONArray arr = body.getJSONArray("actors");
            if (arr != null) {
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject a = arr.getJSONObject(i);
                    if (a == null) continue;
                    Long aid = a.getLong("id");
                    if (aid == null) continue;
                    actorRepository.findById(aid).ifPresent(actor -> {
                        TvShowActor ta = new TvShowActor();
                        ta.setTvShow(saved);
                        ta.setActor(actor);
                        ta.setDescription(a.getString("description"));
                        ta.setRole(a.getString("role"));
                        tvShowActorRepository.save(ta);
                    });
                }
            }
        }

        // 更新季信息（仅当请求包含 seasons）
        if (isUpdate && body.containsKey("seasons")) {
            var olds = tvShowSeasonRepository.findByTvShow_IdOrderByNumberAsc(saved.getId());
            if (olds != null && !olds.isEmpty()) tvShowSeasonRepository.deleteAll(olds);
        }
        if (body.containsKey("seasons")) {
            JSONArray arr = body.getJSONArray("seasons");
            if (arr != null) {
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject s = arr.getJSONObject(i);
                    if (s == null) continue;
                    TvShowSeason season = new TvShowSeason();
                    season.setTvShow(saved);
                    season.setNumber(s.getInteger("number") == null ? 0 : s.getInteger("number"));
                    season.setTitle(s.getString("title"));
                    season.setEpisodes(s.getInteger("episodes") == null ? 0 : s.getInteger("episodes"));
                    season.setYear(s.getInteger("year") == null ? 0 : s.getInteger("year"));
                    tvShowSeasonRepository.save(season);
                }
            }
        }

        return DataResponse.success(H.build().put("id", saved.getId()).put("updated", isUpdate).toJson());
    }
}
