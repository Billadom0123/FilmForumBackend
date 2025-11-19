package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Actor.Actor;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.*;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.web.filmforum.Model.Film.FilmActor;
import com.example.web.filmforum.Model.Award.AwardRecordPO;
import java.util.List;

import java.time.LocalDate;
// 新增导入
import com.example.web.filmforum.Model.Award.AwardPO;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private TvShowActorRepository tvShowActorRepository;

    @Autowired
    private VarietyRepository varietyRepository;

    @Autowired
    private VarietyGuestRepository varietyGuestRepository;

    @Autowired
    private AwardRecordRepository awardRecordRepository;

    // 新增：校验奖项需要
    @Autowired
    private AwardRepository awardRepository;

    public DataResponse searchActors(String keyword, String nationality, String gender, Pageable pageable) {
        Page<Actor> actors = actorRepository.queryActors(keyword, keyword, nationality, gender, pageable);
        JSONArray data = new JSONArray();
        for (Actor actor : actors.getContent()) {
            JSONObject obj = H.build()
                        .put("id", actor.getId())
                        .put("name", actor.getName())
                        .put("avatar", actor.getAvatar())
                        .put("birthday", actor.getBirthday())
                        .put("nationality", actor.getNationality())
                        .put("gender", actor.getGender())
                        .put("movies_count", 0)
                        .put("tv_shows_count", 0)
                        .toJson();
            data.add(obj);
        }

        Pagination pag = new Pagination(actors.getTotalElements(),
                pageable.getPageNumber() + 1, pageable.getPageSize(), actors.hasNext());

        return DataResponse.success(H.build()
                .put("actors", data)
                .put("pagination", pag.toJSON())
                .toJson()
        );
    }

    public DataResponse detail(Long id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);

        // Movies participated
        List<FilmActor> filmRelations = filmActorRepository.findByActor_Id(actor.getId());
        JSONArray movies = new JSONArray();
        for (FilmActor fa : filmRelations) {
            if (fa.getFilm() == null) continue;
            movies.add(
                    H.build()
                            .put("id", fa.getFilm().getId())
                            .put("title", fa.getFilm().getTitle())
                            .put("year", fa.getFilm().getYear())
                            .put("poster", fa.getFilm().getPoster())
                            .put("role", fa.getRole())
                            .toJson()
            );
        }

        // TV show count
        int tvShowsCount = tvShowActorRepository.findByActor_Id(actor.getId()).size();

        // Awards
        List<AwardRecordPO> records = awardRecordRepository.findByTargetIdAndAward_TargetType(actor.getId(), "ACTOR");
        JSONArray awards = new JSONArray();
        for (AwardRecordPO ar : records) {
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

        return DataResponse.success(
                H.build()
                        .put("id", actor.getId())
                        .put("name", actor.getName())
                        .put("avatar", actor.getAvatar())
                        .put("birthday", actor.getBirthday())
                        .put("nationality", actor.getNationality())
                        .put("gender", actor.getGender())
                        .put("biography", actor.getBiography())
                        .put("movies_count", filmRelations.size())
                        .put("tv_shows_count", tvShowsCount)
                        .put("awards", awards)
                        .put("movies", movies)
                        .toJson()
        );
    }

    // 新增/更新演员信息（POST）
    @Transactional
    public DataResponse save(JSONObject payload) {
        try {
            Actor actor;
            String idStr = payload.getString("id");
            if (idStr != null && !idStr.isBlank()) {
                try {
                    Long id = Long.parseLong(idStr);
                    actor = actorRepository.findById(id).orElse(new Actor());
                } catch (NumberFormatException e) {
                    actor = new Actor();
                }
            } else {
                actor = new Actor();
            }
            actor.setName(payload.getString("name"));
            actor.setAvatar(payload.getString("avatar"));
            String birthdayStr = payload.getString("birthday");
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                try { actor.setBirthday(LocalDate.parse(birthdayStr)); } catch (Exception ignore) {}
            }
            actor.setNationality(payload.getString("nationality"));
            actor.setGender(payload.getString("gender"));
            actor.setBiography(payload.getString("biography"));

            if (actor.getName() == null || actor.getName().isBlank()) {
                return DataResponse.failure(30000, "name不能为空");
            }

            Actor saved = actorRepository.save(actor);

            // 新增：awards 处理（仅当请求包含 awards 时按覆盖策略更新）
            if (payload.containsKey("awards")) {
                awardRecordRepository.deleteByTargetIdAndAward_TargetType(saved.getId(), "ACTOR");
                JSONArray arr = payload.getJSONArray("awards");
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
                        if (opt.isEmpty()) continue;
                        AwardPO aw = opt.get();
                        if (!"ACTOR".equals(aw.getTargetType())) continue;
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

            return DataResponse.success(H.build().put("id", saved.getId()).toJson());
        } catch (Exception e) {
            return DataResponse.failure(500, "保存演员失败: " + e.getMessage());
        }
    }

    @Transactional
    public DataResponse delete(Long id) {
        Actor actor = actorRepository.findById(id).orElse(null);
        if (actor == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        try {
            // 先置空其作为导演/主持的外键，避免外键约束阻止删除
            this.filmRepository.clearDirectorReferences(id);
            this.tvShowRepository.clearDirectorReferences(id);
            this.varietyRepository.clearHostReferences(id);
            this.filmActorRepository.deleteByActorId(id);
            this.tvShowActorRepository.deleteByActorId(id);
            this.varietyGuestRepository.deleteByActorId(id);
            this.awardRecordRepository.deleteByTargetIdAndAward_TargetType(id, "ACTOR");
            this.actorRepository.delete(actor);
            return DataResponse.ok();
        } catch (Exception e) {
            return DataResponse.failure(500, "删除演员失败: " + e.getMessage());
        }
    }
}
