package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/query")
    public DataResponse list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(required = false) String nationality,
                             @RequestParam(required = false) String gender) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));

        return actorService.searchActors(keyword, nationality, gender, pageRequest);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    public DataResponse saveActor(@RequestBody JSONObject actorData) {
        return actorService.save(actorData);
    }

    @GetMapping("/{id}")
    public DataResponse detail(@PathVariable Long id) {
        return actorService.detail(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/delete")
    public DataResponse delete(@PathVariable Long id) {
        return actorService.delete(id);
    }
}
