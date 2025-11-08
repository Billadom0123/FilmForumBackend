package com.example.web.filmforum.Controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Award.AwardRecordPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Service.AwardService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/award")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @PostMapping("/save")
    @Secured("ROLE_ADMIN")
    public DataResponse save(@RequestBody JSONObject payload) {
        return awardService.save(payload);
    }

    @GetMapping("/query")
    public DataResponse query(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "target_type", required = false) String targetType) {
        PageRequest pageRequest = PageRequest.of(Math.max(page - 1, 0), Math.max(size, 1));
        return awardService.query(keyword, targetType, pageRequest);
    }

    @GetMapping("/{id}")
    public DataResponse getById(@PathVariable Long id) {
        return awardService.getById(id);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/delete")
    public DataResponse delete(@PathVariable Long id) {
        return awardService.delete(id);
    }
}
