package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Actor.Actor;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.ActorRepository;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

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
}
