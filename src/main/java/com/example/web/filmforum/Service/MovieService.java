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

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
    private UserRepository userRepository;
    @Autowired
    private ActorRepository actorRepository;

    public DataResponse list(String tag, Integer year, Double rating, String actor, String award, Pageable pageable) {
        Page<FilmPO> page = filmRepository.queryMovies(null, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        java.util.List<Long> ids = page.getContent().stream().map(FilmPO::getId).collect(Collectors.toList());
        java.util.Map<Long, Double> avgMap = new java.util.HashMap<>();
        if (!ids.isEmpty()) {
            for (var rs : ratingStatRepository.findByTargetTypeAndTargetIdIn("FILM", ids)) {
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
        java.util.List<Long> ids = page.getContent().stream().map(FilmPO::getId).collect(Collectors.toList());
        java.util.Map<Long, Double> avgMap = new java.util.HashMap<>();
        if (!ids.isEmpty()) {
            for (var rs : ratingStatRepository.findByTargetTypeAndTargetIdIn("FILM", ids)) {
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
        JSONArray directorArr = new JSONArray();
        if (film.getDirector() != null) {
            directorArr.add(
                    H.build()
                            .put("id", film.getDirector().getId())
                            .put("name", film.getDirector().getName())
                            .put("avatar", film.getDirector().getAvatar())
                            .put("description", "Director")
                            .toJson()
            );
        }
        java.util.List<com.example.web.filmforum.Model.Film.FilmActor> faList = filmActorRepository.findByFilm_Id(film.getId());
        JSONArray actors = new JSONArray();
        for (com.example.web.filmforum.Model.Film.FilmActor fa : faList) {
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
        java.util.List<com.example.web.filmforum.Model.Award.AwardRecordPO> awardRecords = awardRecordRepository.findByTargetIdAndAward_TargetType(film.getId(), "FILM");
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
        long likes = likeRepository.countByTargetTypeAndTargetId("FILM", film.getId());
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
                        .put("director", directorArr)
                        .put("actors", actors)
                        .put("awards", awards)
                        .put("views", film.getViews())
                        .put("likes", likes)
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
            film.setPhotos(arr == null ? new ArrayList<>() : arr.stream().map(Object::toString).collect(Collectors.toList()));
        }
        if (body.containsKey("tags")) {
            JSONArray arr = body.getJSONArray("tags");
            film.setTags(arr == null ? new ArrayList<>() : arr.stream().map(Object::toString).collect(Collectors.toList()));
        }
        if (body.containsKey("views")) film.setViews(body.getInteger("views") == null ? 0 : body.getInteger("views"));

        // director
        if (body.containsKey("director_id")) {
            Long dirId = body.getLong("director_id");
            if (dirId == null) film.setDirector(null); else actorRepository.findById(dirId).ifPresent(film::setDirector);
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
            if (actors != null) {
                for (int i = 0; i < actors.size(); i++) {
                    JSONObject a = actors.getJSONObject(i);
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
        }

        return DataResponse.success(H.build().put("id", saved.getId()).put("updated", isUpdate).toJson());
    }
}
