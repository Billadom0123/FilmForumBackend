package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.TvShow.TvShowPO;
import com.example.web.filmforum.Model.TvShow.TvShowActor;
import com.example.web.filmforum.Model.TvShow.TvShowSeason;
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
        for (TvShowPO show : page.getContent()) {
            Double avg = tvShowRepository.getAvgScore(show.getId());
            JSONObject obj = H.build()
                    .put("id", show.getId())
                    .put("title", show.getTitle())
                    .put("year", show.getYear())
                    .put("tag", show.getTags())
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
        Double avg = tvShowRepository.getAvgScore(show.getId());
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
        List<TvShowActor> rels = tvShowActorRepository.findByTvShow_Id(show.getId());
        JSONArray actors = new JSONArray();
        for (TvShowActor ta : rels) {
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
        List<TvShowSeason> seasonsList = tvShowSeasonRepository.findByTvShow_IdOrderByNumberAsc(show.getId());
        JSONArray seasons = new JSONArray();
        for (TvShowSeason s : seasonsList) {
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
        List<AwardRecordPO> awardRecords = awardRecordRepository.findByTargetIdAndAward_TargetType(show.getId(), "TVSHOW");
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
        long likes = likeRepository.countByTargetTypeAndTargetId("TVSHOW", show.getId());
        return DataResponse.success(
                H.build()
                        .put("id", show.getId())
                        .put("title", show.getTitle())
                        .put("original_title", show.getOriginalTitle())
                        .put("year", show.getYear())
                        .put("episodes", show.getEpisodes())
                        .put("tags", show.getTags())
                        .put("rating", avg)
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
        for (TvShowPO show : page.getContent()) {
            Double avg = tvShowRepository.getAvgScore(show.getId());
            JSONObject obj = H.build()
                    .put("id", show.getId())
                    .put("title", show.getTitle())
                    .put("year", show.getYear())
                    .put("tag", show.getTags())
                    .put("rating", avg)
                    .put("poster", show.getPoster())
                    .toJson();
            data.add(obj);
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("tvshows", data).put("pagination", pag.toJSON()).toJson());
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

    public DataResponse add(Map<String, Object> body) {
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String title = (String) body.get("title");
        if (title == null || title.trim().isEmpty()) return DataResponse.failure(CommonErr.PARAM_WRONG);
        Integer year = body.get("year") instanceof Number ? ((Number) body.get("year")).intValue() : null;
        if (year == null) return DataResponse.failure(CommonErr.PARAM_WRONG);

        TvShowPO show = new TvShowPO();
        show.setTitle(title);
        show.setOriginalTitle((String) body.get("original_title"));
        show.setYear(year);
        show.setEpisodes(body.get("episodes") instanceof Number ? ((Number) body.get("episodes")).intValue() : 0);
        show.setPoster((String) body.get("poster"));
        show.setSummary((String) body.get("summary"));
        show.setCountry((String) body.get("country"));
        show.setLanguage((String) body.get("language"));
        show.setTrailer((String) body.get("trailer"));
        show.setPhotos(body.get("photos") instanceof List ? (List<String>) body.get("photos") : new ArrayList<>());
        show.setTags(body.get("tags") instanceof List ? (List<String>) body.get("tags") : new ArrayList<>());
        show.setViews(body.get("views") instanceof Number ? ((Number) body.get("views")).intValue() : 0);

        if (body.get("director_id") instanceof Number) {
            Long dirId = ((Number) body.get("director_id")).longValue();
            actorRepository.findById(dirId).ifPresent(show::setDirector);
        }

        TvShowPO saved = tvShowRepository.save(show);

        // actors simple
        if (body.get("actors") instanceof List) {
            List<Map<String, Object>> actors = (List<Map<String, Object>>) body.get("actors");
            for (Map<String, Object> a : actors) {
                if (a == null) continue;
                Object oid = a.get("id");
                if (!(oid instanceof Number)) continue;
                Long aid = ((Number) oid).longValue();
                actorRepository.findById(aid).ifPresent(actor -> {
                    TvShowActor ta = new TvShowActor();
                    ta.setTvShow(saved);
                    ta.setActor(actor);
                    ta.setDescription(a.get("description") == null ? null : a.get("description").toString());
                    ta.setRole(a.get("role") == null ? null : a.get("role").toString());
                    tvShowActorRepository.save(ta);
                });
            }
        }

        // seasons
        if (body.get("seasons") instanceof List) {
            List<Map<String, Object>> seasons = (List<Map<String, Object>>) body.get("seasons");
            for (Map<String, Object> s : seasons) {
                if (s == null) continue;
                TvShowSeason season = new TvShowSeason();
                season.setTvShow(saved);
                season.setNumber(s.get("number") instanceof Number ? ((Number) s.get("number")).intValue() : 0);
                season.setTitle(s.get("title") == null ? null : s.get("title").toString());
                season.setEpisodes(s.get("episodes") instanceof Number ? ((Number) s.get("episodes")).intValue() : 0);
                season.setYear(s.get("year") instanceof Number ? ((Number) s.get("year")).intValue() : 0);
                tvShowSeasonRepository.save(season);
            }
        }

        return DataResponse.success(H.build().put("id", saved.getId()).toJson());
    }
}
