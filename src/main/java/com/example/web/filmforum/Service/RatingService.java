package com.example.web.filmforum.Service;

import com.example.web.filmforum.Model.Common.RatingPO;
import com.example.web.filmforum.Model.Common.RatingStatPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Repository.RatingRepository;
import com.example.web.filmforum.Repository.RatingStatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingStatRepository ratingStatRepository;

    @Transactional
    public void rate(String targetType, Long targetId, UserPO user, int score, String comment) {
        if (score < 1 || score > 10) throw new IllegalArgumentException("score out of range");
        RatingPO old = ratingRepository.findByUser_IdAndTargetTypeAndTargetId(user.getId(), targetType, targetId);
        RatingStatPO stat = ratingStatRepository.findByTargetTypeAndTargetId(targetType, targetId);
        if (stat == null) {
            stat = new RatingStatPO();
            stat.setTargetType(targetType);
            stat.setTargetId(targetId);
            stat.setRatingCount(0);
            stat.setRatingSum(0);
            stat.setRatingAvg(0.0);
        }
        if (old == null) {
            RatingPO r = new RatingPO();
            r.setUser(user);
            r.setTargetType(targetType);
            r.setTargetId(targetId);
            r.setScore(score);
            r.setComment(comment);
            ratingRepository.save(r);
            stat.setRatingCount(stat.getRatingCount() + 1);
            stat.setRatingSum(stat.getRatingSum() + score);
        } else {
            int delta = score - old.getScore();
            old.setScore(score);
            old.setComment(comment);
            ratingRepository.save(old);
            stat.setRatingSum(stat.getRatingSum() + delta);
        }
        if (stat.getRatingCount() > 0) {
            stat.setRatingAvg(((double) stat.getRatingSum()) / stat.getRatingCount());
        } else {
            stat.setRatingAvg(0.0);
        }
        ratingStatRepository.save(stat);
    }

    public RatingSummary summary(String targetType, Long targetId) {
        RatingStatPO stat = ratingStatRepository.findByTargetTypeAndTargetId(targetType, targetId);
        if (stat != null) {
            return new RatingSummary(stat.getRatingAvg(), stat.getRatingCount());
        }
        Double avg = ratingRepository.avgByTarget(targetType, targetId);
        long cnt = ratingRepository.countByTargetTypeAndTargetId(targetType, targetId);
        return new RatingSummary(avg == null ? 0.0 : avg, cnt);
    }

    public record RatingSummary(double avg, long count) {}
}

