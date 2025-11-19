package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Film.FilmPO;
import com.example.web.filmforum.Model.Film.FilmActor;
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

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.example.web.filmforum.Model.Common.RatingStatPO;
// 添加导入
import com.example.web.filmforum.Model.Award.AwardPO;

@Service
public class MovieService {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmActorRepository filmActorRepository;
    @Autowired
    private AwardRecordRepository awardRecordRepository;
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
    private UserRepository userRepository;
    @Autowired
    private ActorRepository actorRepository;
    // 新增：校验奖项需要
    @Autowired
    private AwardRepository awardRepository;

    public DataResponse list(String tag, Integer year, Double rating, String actor, String award, Pageable pageable) {
        Page<FilmPO> page = filmRepository.queryMovies(null, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        List<Long> ids = new ArrayList<>();
        for (FilmPO item : page.getContent()) {
            ids.add(item.getId());
        }
        Map<Long, Double> avgMap = new HashMap<>();
        if (!ids.isEmpty()) {
            List<RatingStatPO> stats = ratingStatRepository.findByTargetTypeAndTargetIdIn("FILM", ids);
            for (RatingStatPO rs : stats) {
                avgMap.put(rs.getTargetId(), rs.getRatingAvg());
            }
        }
        for (FilmPO film : page.getContent()) {
            Double avg = avgMap.getOrDefault(film.getId(), 0.0);
            JSONObject obj = H.build()
                    .put("id", film.getId())
                    .put("title", film.getTitle())
                    .put("year", film.getYear())
                    .put("tags", film.getTags())
                    .put("rating", avg)
                    .put("poster", film.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("movies", data).put("pagination", pag.toJSON()).toJson());
    }

    public DataResponse search(String keyword, String tag, Integer year, String actor, String award, Double rating, Pageable pageable) {
        Page<FilmPO> page = filmRepository.queryMovies(keyword, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        List<Long> ids = new ArrayList<>();
        for (FilmPO item : page.getContent()) {
            ids.add(item.getId());
        }
        Map<Long, Double> avgMap = new HashMap<>();
        if (!ids.isEmpty()) {
            List<RatingStatPO> stats = ratingStatRepository.findByTargetTypeAndTargetIdIn("FILM", ids);
            for (RatingStatPO rs : stats) {
                avgMap.put(rs.getTargetId(), rs.getRatingAvg());
            }
        }
        for (FilmPO film : page.getContent()) {
            Double avg = avgMap.getOrDefault(film.getId(), 0.0);
            JSONObject obj = H.build()
                    .put("id", film.getId())
                    .put("title", film.getTitle())
                    .put("year", film.getYear())
                    .put("tags", film.getTags())
                    .put("rating", avg)
                    .put("poster", film.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("movies", data).put("pagination", pag.toJSON()).toJson());
    }

    private UserPO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) user = userRepository.findByEmail(name);
        return user;
    }

    public DataResponse detail(Long id) {
        FilmPO film = filmRepository.findById(id).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        film.setViews(film.getViews() + 1);
        filmRepository.save(film);
        RatingService.RatingSummary sum = ratingService.summary("FILM", film.getId());
        JSONObject director = null;
        if (film.getDirector() != null) {
            director = H.build()
                            .put("id", film.getDirector().getId())
                            .put("name", film.getDirector().getName())
                            .put("avatar", film.getDirector().getAvatar())
                            .put("description", "Director")
                            .toJson();
        }
        List<FilmActor> faList = filmActorRepository.findByFilm_Id(film.getId());
        JSONArray actors = new JSONArray();
        for (FilmActor fa : faList) {
            if (fa.getActor() == null) continue;
            actors.add(
                    H.build()
                            .put("id", fa.getActor().getId())
                            .put("name", fa.getActor().getName())
                            .put("avatar", fa.getActor().getAvatar())
                            .put("description", fa.getDescription())
                            .toJson()
            );
        }
        List<AwardRecordPO> awardRecords = awardRecordRepository.findByTargetIdAndAward_TargetType(film.getId(), "FILM");
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
        long likes = likeRepository.countByTargetTypeAndTargetId("FILM", film.getId());
        UserPO me = currentUser();
        boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", film.getId());
        boolean isFavorited = me != null && favoriteRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", film.getId());
        return DataResponse.success(
                H.build()
                        .put("id", film.getId())
                        .put("title", film.getTitle())
                        .put("original_title", film.getOriginalTitle())
                        .put("year", film.getYear())
                        .put("tags", film.getTags())
                        .put("rating", sum.avg())
                        .put("poster", film.getPoster())
                        .put("summary", film.getSummary())
                        .put("duration", film.getDuration())
                        .put("country", film.getCountry())
                        .put("language", film.getLanguage())
                        .put("trailer", film.getTrailer())
                        .put("photos", film.getPhotos())
                        .put("director", director)
                        .put("actors", actors)
                        .put("awards", awards)
                        .put("views", film.getViews())
                        .put("likes", likes)
                        .put("isLiked", isLiked)
                        .put("isFavorited", isFavorited)
                        .toJson()
        );
    }

    public DataResponse like(Long filmId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", filmId)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        LikePO like = new LikePO();
        like.setUser(me);
        like.setTargetType("FILM");
        like.setTargetId(filmId);
        likeRepository.save(like);
        return DataResponse.ok();
    }

    public DataResponse unlike(Long filmId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        LikePO like = likeRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", filmId);
        if (like == null) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        likeRepository.delete(like);
        return DataResponse.ok();
    }

    // 新增：评分提交/更新（改为使用统一RatingService）
    public DataResponse rate(Long filmId, Integer score, String comment) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (score == null || score < 1 || score > 10) return DataResponse.failure(CommonErr.PARAM_WRONG);
        ratingService.rate("FILM", filmId, me, score, comment);
        return DataResponse.ok();
    }

    // 新增：获取评分和海报（改为统一统计）
    public DataResponse ratingPoster(Long filmId) {
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        RatingService.RatingSummary s = ratingService.summary("FILM", filmId);
        return DataResponse.success(
                H.build()
                        .put("id", film.getId())
                        .put("rating", s.avg())
                        .put("poster", film.getPoster())
                        .toJson()
        );
    }

    // 新增：收藏/取消收藏
    public DataResponse favorite(Long filmId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        if (favoriteRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", filmId)) {
            return DataResponse.failure(CommonErr.OPERATE_REPEAT);
        }
        FavoritePO fav = new FavoritePO();
        fav.setUser(me);
        fav.setTargetType("FILM");
        fav.setTargetId(filmId);
        favoriteRepository.save(fav);
        return DataResponse.ok();
    }

    public DataResponse unfavorite(Long filmId) {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        FavoritePO fav = favoriteRepository.findByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", filmId);
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
        FilmPO film = null;
        boolean isUpdate = false;
        if (id != null) {
            film = filmRepository.findById(id).orElse(null);
            if (film != null) isUpdate = true;
        }
        if (film == null) film = new FilmPO();

        // 按 containsKey 控制更新；未传不动，显式 null 置空
        film.setTitle(title);
        if (body.containsKey("original_title")) film.setOriginalTitle(body.getString("original_title"));
        film.setYear(year);
        if (body.containsKey("poster")) film.setPoster(body.getString("poster"));
        if (body.containsKey("summary")) film.setSummary(body.getString("summary"));
        if (body.containsKey("duration")) film.setDuration(body.getInteger("duration") == null ? 0 : body.getInteger("duration"));
        if (body.containsKey("country")) film.setCountry(body.getString("country"));
        if (body.containsKey("language")) film.setLanguage(body.getString("language"));
        if (body.containsKey("trailer")) film.setTrailer(body.getString("trailer"));
        if (body.containsKey("photos")) {
            JSONArray arr = body.getJSONArray("photos");
            List<String> photos = new ArrayList<>();
            if (arr != null && !arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    Object o = arr.get(i);
                    photos.add(o == null ? null : o.toString());
                }
            }
            film.setPhotos(photos);
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
            film.setTags(tags);
        }
        if (body.containsKey("views")) film.setViews(body.getInteger("views") == null ? 0 : body.getInteger("views"));

        // director
        if (body.containsKey("director")) {
            Long dirId = body.getLong("director");
            if (dirId == null) {
                film.setDirector(null);
            } else {
                actorRepository.findById(dirId).ifPresent(film::setDirector);
            }
        }

        FilmPO saved = filmRepository.save(film);

        // 如果是更新，清空旧的演员关联（仅当请求包含 actors 时）
        if (isUpdate && body.containsKey("actors")) {
            List<FilmActor> olds = filmActorRepository.findByFilm_Id(saved.getId());
            if (olds != null && !olds.isEmpty()) filmActorRepository.deleteAll(olds);
        }

        // actors: [{id, description, role}]
        if (body.containsKey("actors")) {
            JSONArray actors = body.getJSONArray("actors");
            for (JSONObject a : actors.toArray(JSONObject.class)) {
                if (a == null) continue;
                Long aid = a.getLong("id");
                if (aid == null) continue;
                actorRepository.findById(aid).ifPresent(actor -> {
                    FilmActor fa = new FilmActor();
                    fa.setFilm(saved);
                    fa.setActor(actor);
                    fa.setDescription(a.getString("description"));
                    fa.setRole(a.getString("role"));
                    filmActorRepository.save(fa);
                });
            }
        }

        // 新增：awards 处理（仅当请求包含 awards 时按覆盖策略更新）
        if (body.containsKey("awards")) {
            // 清空旧记录
            awardRecordRepository.deleteByTargetIdAndAward_TargetType(saved.getId(), "FILM");
            JSONArray arr = body.getJSONArray("awards");
            if (arr != null && !arr.isEmpty()) {
                for (int i = 0; i < arr.size(); i++) {
                    JSONObject a = arr.getJSONObject(i);
                    if (a == null) continue;
                    Long awId = a.getLong("id");
                    Integer awYear = a.getInteger("year");
                    String status = a.getString("status");
                    String note = a.getString("note");
                    if (awId == null) continue;
                    var opt = awardRepository.findById(awId);
                    if (opt.isEmpty()) continue; // 奖项不存在
                    AwardPO aw = opt.get();
                    if (!"FILM".equals(aw.getTargetType())) continue; // 类型不匹配
                    AwardRecordPO rec = new AwardRecordPO();
                    rec.setAward(aw);
                    rec.setYear(awYear == null ? 0 : awYear);
                    rec.setTargetId(saved.getId());
                    rec.setStatus(status);
                    rec.setNote(note);
                    awardRecordRepository.save(rec);
                }
            }
        }
        return DataResponse.success(H.build().put("id", saved.getId()).put("updated", isUpdate).toJson());
    }

    // 新增：删除电影（仅管理员在控制器层可访问）
    public DataResponse delete(Long filmId) {
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        // 清理演员关联
        List<FilmActor> rels = filmActorRepository.findByFilm_Id(filmId);
        if (rels != null && !rels.isEmpty()) filmActorRepository.deleteAll(rels);
        // 清理点赞/收藏/评分/评分统计
        likeRepository.deleteByTargetTypeAndTargetId("FILM", filmId);
        favoriteRepository.deleteByTargetTypeAndTargetId("FILM", filmId);
        ratingRepository.deleteByTargetTypeAndTargetId("FILM", filmId);
        RatingStatPO stat = ratingStatRepository.findByTargetTypeAndTargetId("FILM", filmId);
        if (stat != null) ratingStatRepository.delete(stat);
        // 清理奖项记录
        List<AwardRecordPO> awards = awardRecordRepository.findByTargetIdAndAward_TargetType(filmId, "FILM");
        if (awards != null && !awards.isEmpty()) awardRecordRepository.deleteAll(awards);
        // 删除电影本体
        filmRepository.delete(film);
        return DataResponse.ok();
    }

    public DataResponse reviews(Long filmId, Pageable pageable) {
        FilmPO film = filmRepository.findById(filmId).orElse(null);
        if (film == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        var page = ratingRepository.findByTargetTypeAndTargetIdAndCommentIsNotNull("FILM", filmId, pageable);
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

    // 新增：最热电影分页（按浏览量倒序）
    public DataResponse hot(Pageable pageable) {
        Page<FilmPO> page = filmRepository.findAllByOrderByViewsDesc(pageable);
        JSONArray data = new JSONArray();
        List<Long> ids = new ArrayList<>();
        for (FilmPO item : page.getContent()) ids.add(item.getId());
        Map<Long, Double> avgMap = new HashMap<>();
        if (!ids.isEmpty()) {
            List<RatingStatPO> stats = ratingStatRepository.findByTargetTypeAndTargetIdIn("FILM", ids);
            for (RatingStatPO rs : stats) avgMap.put(rs.getTargetId(), rs.getRatingAvg());
        }
        for (FilmPO film : page.getContent()) {
            Double avg = avgMap.getOrDefault(film.getId(), 0.0);
            data.add(
                    H.build()
                            .put("id", film.getId())
                            .put("title", film.getTitle())
                            .put("year", film.getYear())
                            .put("tags", film.getTags())
                            .put("rating", avg)
                            .put("poster", film.getPoster())
                            .put("views", film.getViews())
                            .put("likes", likeRepository.countByTargetTypeAndTargetId("FILM", film.getId()))
                            .toJson()
            );
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("movies", data).put("pagination", pag.toJSON()).toJson());
    }
}
