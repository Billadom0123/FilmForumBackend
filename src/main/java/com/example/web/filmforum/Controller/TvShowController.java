package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tvshows")
public class TvShowController {

    @Autowired
    private TvShowService tvShowService;

    @GetMapping
    public DataResponse list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String tag,
                             @RequestParam(required = false) Integer year,
                             @RequestParam(required = false) Double rating,
                             @RequestParam(required = false) String actor,
                             @RequestParam(required = false) String award) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return tvShowService.list(tag, year, rating, actor, award, pageRequest);
    }

    @GetMapping("/{id}")
    public DataResponse detail(@PathVariable Long id) {
        return tvShowService.detail(id);
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
        return tvShowService.search(keyword, tag, year, actor, award, rating, pageRequest);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/like")
    public DataResponse like(@PathVariable("id") Long id) {
        return tvShowService.like(id);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/unlike")
    public DataResponse unlike(@PathVariable("id") Long id) {
        return tvShowService.unlike(id);
    }

    // 新增：评分提交（含短评）
    @Secured("ROLE_USER")
    @PostMapping("/{id}/rate")
    public DataResponse rate(@PathVariable("id") Long id, @RequestBody JSONObject body) {
        Integer score = body.getInteger("score");
        String comment = body.getString("comment");
        return tvShowService.rate(id, score, comment);
    }

    // 新增：获取评分和海报
    @GetMapping("/{id}/rating-poster")
    public DataResponse ratingPoster(@PathVariable("id") Long id) {
        return tvShowService.ratingPoster(id);
    }

    // 新增：收藏/取消收藏
    @Secured("ROLE_USER")
    @PostMapping("/{id}/favorite")
    public DataResponse favorite(@PathVariable("id") Long id) {
        return tvShowService.favorite(id);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/unfavorite")
    public DataResponse unfavorite(@PathVariable("id") Long id) {
        return tvShowService.unfavorite(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public DataResponse addTvShow(@RequestBody JSONObject body) {
        return tvShowService.add(body);
    }
}
