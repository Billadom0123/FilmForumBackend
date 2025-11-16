package com.example.web.filmforum.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Award.AwardPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.AwardRecordRepository;
import com.example.web.filmforum.Repository.AwardRepository;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AwardService {

    @Autowired
    private AwardRepository awardRepository;
    @Autowired
    private AwardRecordRepository awardRecordRepository;

    public DataResponse save(JSONObject payload) {

        AwardPO awardPO;
        if (payload.containsKey("id")) {
            Long id = payload.getLong("id");
            awardPO = awardRepository.findById(id).orElse(null);
            if (awardPO == null) return DataResponse.failure(CommonErr.AWARD_NOT_FOUND);
        } else {
            if (awardRepository.findByName(payload.getString("name")).isPresent()) {
                return DataResponse.failure(CommonErr.DUPLICATE_AWARD_NAME);
            }
            awardPO = new AwardPO();
        }
        awardPO.setName(payload.getString("name"));
        awardPO.setOrganization(payload.getString("organization"));
        awardPO.setTargetType(payload.getString("target_type"));
        if (!List.of("ACTOR", "FILM", "TVSHOW", "VARIETY").contains(awardPO.getTargetType())) {
            return DataResponse.failure(CommonErr.INVALID_AWARD_TARGET_TYPE);
        }
        awardPO.setDescription(payload.getString("description"));

        try {
            awardRepository.save(awardPO);
        } catch (Exception e) {
            return DataResponse.failure(500, "Failed to save award: " + e.getMessage());
        }

        return DataResponse.ok();
    }

    public DataResponse query(String keyword, String targetType, PageRequest pageRequest) {
        Page<AwardPO> awards;
        if (targetType == null || targetType.isEmpty()) {
            awards = awardRepository.queryAwards(keyword, keyword, keyword, pageRequest);
        } else {
            awards = awardRepository.queryAwards(keyword, keyword, keyword, targetType, pageRequest);
        }
        JSONArray data = new JSONArray();
        for (AwardPO award : awards.getContent()) {
            JSONObject obj = H.build()
                    .put("id", award.getId())
                    .put("name", award.getName())
                    .put("organization", award.getOrganization())
                    .put("target_type", award.getTargetType())
                    .put("description", award.getDescription())
                    .toJson();
            data.add(obj);
        }

        Pagination pagination = new Pagination(awards.getTotalElements(),
                pageRequest.getPageNumber() + 1, pageRequest.getPageSize(), awards.hasNext());

        return DataResponse.success(H.build()
                .put("awards", data)
                .put("pagination", pagination)
                .toJson()
        );
    }

    public DataResponse getById(Long id) {
        AwardPO award = awardRepository.findById(id).orElse(null);
        if (award == null) {
            return DataResponse.failure(CommonErr.AWARD_NOT_FOUND);
        }

        JSONObject data = H.build()
                .put("id", award.getId())
                .put("name", award.getName())
                .put("organization", award.getOrganization())
                .put("target_type", award.getTargetType())
                .put("description", award.getDescription())
                .put("actors", new JSONArray())
                .toJson();

        return DataResponse.success(data);
    }

    @Transactional
    public DataResponse delete(Long id) {
        AwardPO award = awardRepository.findById(id).orElse(null);
        if (award == null) return DataResponse.failure(CommonErr.AWARD_NOT_FOUND);
        try {
            var records = awardRecordRepository.findByAward_Id(id);
            if (!records.isEmpty()) awardRecordRepository.deleteAll(records);
            awardRepository.delete(award);
            return DataResponse.ok();
        } catch (Exception e) {
            return DataResponse.failure(500, "删除奖项失败: " + e.getMessage());
        }
    }
}
