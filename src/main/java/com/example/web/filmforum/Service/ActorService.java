package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Actor.Actor;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.ActorRepository;
import com.example.web.filmforum.Repository.AwardRecordRepository;
import com.example.web.filmforum.Repository.FilmActorRepository;
import com.example.web.filmforum.Repository.TvShowActorRepository;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.web.filmforum.Model.Film.FilmActor;
import com.example.web.filmforum.Model.Award.AwardRecordPO;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    @Autowired
    private TvShowActorRepository tvShowActorRepository;

    @Autowired
    private AwardRecordRepository awardRecordRepository;

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
}
