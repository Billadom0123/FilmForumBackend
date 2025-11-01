package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Film.FilmPO;
import com.example.web.filmforum.Model.Film.FilmActor;
import com.example.web.filmforum.Model.Award.AwardRecordPO;
import com.example.web.filmforum.Model.Common.LikePO;
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
import java.util.Map;
import java.util.ArrayList;

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
    private UserRepository userRepository;
    @Autowired
    private ActorRepository actorRepository;

    public DataResponse list(String tag, Integer year, Double rating, String actor, String award, Pageable pageable) {
        Page<FilmPO> page = filmRepository.queryMovies(null, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        for (FilmPO film : page.getContent()) {
            Double avg = filmRepository.getAvgScore(film.getId());
            JSONObject obj = H.build()
                    .put("id", film.getId())
                    .put("title", film.getTitle())
                    .put("year", film.getYear())
                    .put("tag", film.getTags())
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
        for (FilmPO film : page.getContent()) {
            Double avg = filmRepository.getAvgScore(film.getId());
            JSONObject obj = H.build()
                    .put("id", film.getId())
                    .put("title", film.getTitle())
                    .put("year", film.getYear())
                    .put("tag", film.getTags())
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
        Double avg = filmRepository.getAvgScore(film.getId());
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
        return DataResponse.success(
                H.build()
                        .put("id", film.getId())
                        .put("title", film.getTitle())
                        .put("original_title", film.getOriginalTitle())
                        .put("year", film.getYear())
                        .put("tags", film.getTags())
                        .put("rating", avg)
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

    public DataResponse add(Map<String, Object> body) {
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String title = (String) body.get("title");
        if (title == null || title.trim().isEmpty()) return DataResponse.failure(CommonErr.PARAM_WRONG);
        Integer year = body.get("year") instanceof Number ? ((Number) body.get("year")).intValue() : null;
        if (year == null) return DataResponse.failure(CommonErr.PARAM_WRONG);

        FilmPO film = new FilmPO();
        film.setTitle(title);
        film.setOriginalTitle((String) body.get("original_title"));
        film.setYear(year);
        film.setPoster((String) body.get("poster"));
        film.setSummary((String) body.get("summary"));
        film.setDuration(body.get("duration") instanceof Number ? ((Number) body.get("duration")).intValue() : 0);
        film.setCountry((String) body.get("country"));
        film.setLanguage((String) body.get("language"));
        film.setTrailer((String) body.get("trailer"));
        film.setPhotos(body.get("photos") instanceof List ? (List<String>) body.get("photos") : new ArrayList<>());
        film.setTags(body.get("tags") instanceof List ? (List<String>) body.get("tags") : new ArrayList<>());
        film.setViews(body.get("views") instanceof Number ? ((Number) body.get("views")).intValue() : 0);

        // director: accept director_id
        if (body.get("director_id") instanceof Number) {
            Long dirId = ((Number) body.get("director_id")).longValue();
            actorRepository.findById(dirId).ifPresent(film::setDirector);
        }

        FilmPO saved = filmRepository.save(film);

        // actors: list of {id, description, role}
        if (body.get("actors") instanceof List) {
            List<Map<String, Object>> actors = (List<Map<String, Object>>) body.get("actors");
            for (Map<String, Object> a : actors) {
                if (a == null) continue;
                Object oid = a.get("id");
                if (!(oid instanceof Number)) continue;
                Long aid = ((Number) oid).longValue();
                actorRepository.findById(aid).ifPresent(actor -> {
                    FilmActor fa = new FilmActor();
                    fa.setFilm(saved);
                    fa.setActor(actor);
                    fa.setDescription(a.get("description") == null ? null : a.get("description").toString());
                    fa.setRole(a.get("role") == null ? null : a.get("role").toString());
                    filmActorRepository.save(fa);
                });
            }
        }

        return DataResponse.success(H.build().put("id", saved.getId()).toJson());
    }
}
