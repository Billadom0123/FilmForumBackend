package com.example.web.filmforum.Controller;

import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public DataResponse list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String tag,
                             @RequestParam(required = false) Integer year,
                             @RequestParam(required = false) Double rating,
                             @RequestParam(required = false) String actor,
                             @RequestParam(required = false) String award) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return movieService.list(tag, year, rating, actor, award, pageRequest);
    }

    @GetMapping("/{id}")
    public DataResponse detail(@PathVariable Long id) {
        return movieService.detail(id);
    }

    @GetMapping("/search")
    public DataResponse search(@RequestParam String keyword,
                               @RequestParam(required = false) String tag,
                               @RequestParam(required = false) Integer year,
                               @RequestParam(required = false) String actor,
                               @RequestParam(required = false) String award,
                               @RequestParam(required = false) Double rating,
                               @RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return movieService.search(keyword, tag, year, actor, award, rating, pageRequest);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/like")
    public DataResponse like(@PathVariable("id") Long id) {
        return movieService.like(id);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/unlike")
    public DataResponse unlike(@PathVariable("id") Long id) {
        return movieService.unlike(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public DataResponse addMovie(@RequestBody Map<String, Object> body) {
        return movieService.add(body);
    }
}
