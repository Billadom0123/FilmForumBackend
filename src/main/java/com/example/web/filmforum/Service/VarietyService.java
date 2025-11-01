package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Variety.VarietyPO;
import com.example.web.filmforum.Model.Variety.VarietyGuest;
import com.example.web.filmforum.Model.Variety.VarietySeason;
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
        for (VarietyPO v : page.getContent()) {
            Double avg = varietyRepository.getAvgScore(v.getId());
            JSONObject obj = H.build()
                    .put("id", v.getId())
                    .put("title", v.getTitle())
                    .put("year", v.getYear())
                    .put("tag", v.getTags())
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
        Double avg = varietyRepository.getAvgScore(v.getId());
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
        return DataResponse.success(
                H.build()
                        .put("id", v.getId())
                        .put("title", v.getTitle())
                        .put("original_title", v.getOriginalTitle())
                        .put("year", v.getYear())
                        .put("episodes", v.getEpisodes())
                        .put("tags", v.getTags())
                        .put("rating", avg)
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
                        .toJson()
        );
    }

    public DataResponse search(String keyword, String tag, Integer year, String actor, String award, Double rating, Pageable pageable) {
        Page<VarietyPO> page = varietyRepository.queryVarieties(keyword, tag, year, actor, award, rating, pageable);
        JSONArray data = new JSONArray();
        for (VarietyPO v : page.getContent()) {
            Double avg = varietyRepository.getAvgScore(v.getId());
            JSONObject obj = H.build()
                    .put("id", v.getId())
                    .put("title", v.getTitle())
                    .put("year", v.getYear())
                    .put("tag", v.getTags())
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

    public DataResponse add(Map<String, Object> body) {
        if (body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String title = (String) body.get("title");
        if (title == null || title.trim().isEmpty()) return DataResponse.failure(CommonErr.PARAM_WRONG);
        Integer year = body.get("year") instanceof Number ? ((Number) body.get("year")).intValue() : null;
        if (year == null) return DataResponse.failure(CommonErr.PARAM_WRONG);

        VarietyPO v = new VarietyPO();
        v.setTitle(title);
        v.setOriginalTitle((String) body.get("original_title"));
        v.setYear(year);
        v.setEpisodes(body.get("episodes") instanceof Number ? ((Number) body.get("episodes")).intValue() : 0);
        v.setPoster((String) body.get("poster"));
        v.setSummary((String) body.get("summary"));
        v.setCountry((String) body.get("country"));
        v.setLanguage((String) body.get("language"));
        v.setTrailer((String) body.get("trailer"));
        v.setPhotos(body.get("photos") instanceof List ? (List<String>) body.get("photos") : new ArrayList<>());
        v.setTags(body.get("tags") instanceof List ? (List<String>) body.get("tags") : new ArrayList<>());
        v.setViews(body.get("views") instanceof Number ? ((Number) body.get("views")).intValue() : 0);

        if (body.get("host_id") instanceof Number) {
            Long hid = ((Number) body.get("host_id")).longValue();
            actorRepository.findById(hid).ifPresent(v::setHost);
        }

        VarietyPO saved = varietyRepository.save(v);

        // guests
        if (body.get("guests") instanceof List) {
            List<Map<String, Object>> guests = (List<Map<String, Object>>) body.get("guests");
            for (Map<String, Object> g : guests) {
                if (g == null) continue;
                Object oid = g.get("id");
                if (!(oid instanceof Number)) continue;
                Long aid = ((Number) oid).longValue();
                actorRepository.findById(aid).ifPresent(actor -> {
                    VarietyGuest vg = new VarietyGuest();
                    vg.setVariety(saved);
                    vg.setActor(actor);
                    vg.setDescription(g.get("description") == null ? null : g.get("description").toString());
                    varietyGuestRepository.save(vg);
                });
            }
        }

        // seasons
        if (body.get("seasons") instanceof List) {
            List<Map<String, Object>> seasons = (List<Map<String, Object>>) body.get("seasons");
            for (Map<String, Object> s : seasons) {
                if (s == null) continue;
                VarietySeason season = new VarietySeason();
                season.setVariety(saved);
                season.setNumber(s.get("number") instanceof Number ? ((Number) s.get("number")).intValue() : 0);
                season.setTitle(s.get("title") == null ? null : s.get("title").toString());
                season.setEpisodes(s.get("episodes") instanceof Number ? ((Number) s.get("episodes")).intValue() : 0);
                season.setYear(s.get("year") instanceof Number ? ((Number) s.get("year")).intValue() : 0);
                varietySeasonRepository.save(season);
            }
        }

        return DataResponse.success(H.build().put("id", saved.getId()).toJson());
    }
}
