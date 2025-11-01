package com.example.web.filmforum.Controller;

import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.VarietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/varieties")
public class VarietyController {

    @Autowired
    private VarietyService varietyService;

    @GetMapping
    public DataResponse list(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String tag,
                             @RequestParam(required = false) Integer year,
                             @RequestParam(required = false) Double rating,
                             @RequestParam(required = false) String actor,
                             @RequestParam(required = false) String award) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return varietyService.list(tag, year, rating, actor, award, pageRequest);
    }

    @GetMapping("/{id}")
    public DataResponse detail(@PathVariable Long id) {
        return varietyService.detail(id);
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
        return varietyService.search(keyword, tag, year, actor, award, rating, pageRequest);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/like")
    public DataResponse like(@PathVariable("id") Long id) {
        return varietyService.like(id);
    }

    @Secured("ROLE_USER")
    @PostMapping("/{id}/unlike")
    public DataResponse unlike(@PathVariable("id") Long id) {
        return varietyService.unlike(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public DataResponse addVariety(@RequestBody Map<String, Object> body) {
        return varietyService.add(body);
    }
}
