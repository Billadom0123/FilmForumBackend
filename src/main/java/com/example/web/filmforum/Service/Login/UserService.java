package com.example.web.filmforum.Service.Login;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.web.filmforum.Model.Common.FavoritePO;
import com.example.web.filmforum.Model.Film.FilmPO;
import com.example.web.filmforum.Model.Post.PostPO;
import com.example.web.filmforum.Model.TvShow.TvShowPO;
import com.example.web.filmforum.Model.User.FollowPO;
import com.example.web.filmforum.Model.User.UserPO;
import com.example.web.filmforum.Model.Variety.VarietyPO;
import com.example.web.filmforum.Payload.DataResponse;
import com.example.web.filmforum.Payload.Enums.CommonErr;
import com.example.web.filmforum.Payload.Enums.UserType;
import com.example.web.filmforum.Payload.Pagination;
import com.example.web.filmforum.Repository.*;
import com.example.web.filmforum.Util.H;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    // 新增依赖
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TvShowRepository tvShowRepository;
    @Autowired
    private VarietyRepository varietyRepository;


    public DataResponse registerUser(JSONObject payload) {
        String username = payload.getString("username");
        String nickname = payload.getString("nickname");
        String email = payload.getString("email");
        String password = payload.getString("password");

        if (userRepository.existsByUsername(username)) {
            return DataResponse.failure(CommonErr.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(email)) {
            return DataResponse.failure(CommonErr.EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserPO newUser = new UserPO();
        newUser.setUsername(username);
        newUser.setNickname(nickname);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setRole(UserType.ROLE_USER); // 默认角色为USER
        newUser.setLevel(1);
        newUser.setExp(0);
        newUser.setJoinDate(LocalDateTime.now());
        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            return DataResponse.failure(CommonErr.UNKNOWN_REGISTER_ERROR);
        }


        return DataResponse.success(H.build()
                .put("username", username)
                .put("nickname", nickname)
                .put("email", email)
                .put("role", newUser.getRole().getString())
                .put("level", newUser.getLevel())
                .put("exp", H.build()
                        .put("now", newUser.getExp())
                        .put("next", newUser.getLevel() * 100)
                        .toJson())
                .put("joinDate", newUser.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .put("avatar", newUser.getAvatar())
                .toJson()
        );
    }

    public DataResponse registerAdmin(JSONObject payload) {
        String username = payload.getString("username");
        String nickname = payload.getString("nickname");
        String email = payload.getString("email");
        String password = payload.getString("password");

        if (userRepository.existsByUsername(username)) {
            return DataResponse.failure(CommonErr.USERNAME_ALREADY_EXISTS);
        }
        if (userRepository.existsByEmail(email)) {
            return DataResponse.failure(CommonErr.EMAIL_ALREADY_EXISTS);
        }

        String encodedPassword = passwordEncoder.encode(password);
        UserPO newUser = new UserPO();
        newUser.setUsername(username);
        newUser.setNickname(nickname);
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setRole(UserType.ROLE_ADMIN);
        newUser.setLevel(1);
        newUser.setExp(0);
        newUser.setJoinDate(LocalDateTime.now());
        try {
            userRepository.save(newUser);
        } catch (Exception e) {
            return DataResponse.failure(CommonErr.UNKNOWN_REGISTER_ERROR);
        }


        return DataResponse.success(H.build()
                .put("username", username)
                .put("nickname", nickname)
                .put("email", email)
                .put("role", newUser.getRole().getString())
                .put("level", newUser.getLevel())
                .put("exp", H.build()
                        .put("now", newUser.getExp())
                        .put("next", newUser.getLevel() * 100)
                        .toJson())
                .put("joinDate", newUser.getJoinDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .put("avatar", newUser.getAvatar())
                .toJson()
        );
    }

    public UserPO findOrCreateByOpenId(String openId, JSONObject userInfoJson) {
        UserPO user = userRepository.findByOpenId(openId);
        if (user != null) {
            return user;
        }

        // 如果用户不存在，创建新用户
        String nickname = userInfoJson.getString("nickname");
        String avatarUrl = userInfoJson.getString("figureurl_2");

        UserPO newUser = new UserPO();
        newUser.setUsername("qq_" + openId); // 生成一个唯一的用户名
        newUser.setNickname(nickname);
        newUser.setPassword(passwordEncoder.encode(openId)); // 使用openId作为密码并加密，之后可提示用户修改密码
        newUser.setOpenId(openId);
        newUser.setAvatar(avatarUrl);
        newUser.setEmail(nickname + "@example.com"); // 使用昵称生成一个伪邮箱
        newUser.setRole(UserType.ROLE_USER); // 默认角色为USER
        newUser.setLevel(1);
        newUser.setExp(0);
        newUser.setJoinDate(LocalDateTime.now());

        userRepository.save(newUser);
        return newUser;
    }

    // 更新用户头像
    public DataResponse updateAvatar(JSONObject payload) {
        if (payload == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        String avatar = payload.getString("avatar");
        if (avatar == null || avatar.isBlank()) {
            return DataResponse.failure(30000, "avatar不能为空");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        }
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) user = userRepository.findByEmail(name);
        if (user == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);

        user.setAvatar(avatar);
        userRepository.save(user);
        return DataResponse.success(H.build()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .toJson());
    }

    private UserPO currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return null;
        String name = auth.getName();
        UserPO user = userRepository.findByUsername(name);
        if (user == null) user = userRepository.findByEmail(name);
        return user;
    }

    // 获取用户信息
    public DataResponse getUserInfo(Long userId) {
        if (userId == null || userId <= 0) return DataResponse.failure(CommonErr.PARAM_WRONG);
        UserPO target = userRepository.findById(userId).orElse(null);
        if (target == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        UserPO me = currentUser();
        boolean isFollowing = false;
        if (me != null && !me.getId().equals(target.getId())) {
            isFollowing = followRepository.existsByFollower_IdAndFollowing_Id(me.getId(), target.getId());
        }
        long followers = followRepository.countByFollowing_Id(target.getId());
        long following = followRepository.countByFollower_Id(target.getId());
        return DataResponse.success(
                H.build()
                        .put("id", target.getId())
                        .put("username", target.getUsername())
                        .put("nickname", target.getNickname())
                        .put("avatar", target.getAvatar())
                        .put("email", target.getEmail())
                        .put("level", target.getLevel())
                        .put("exp", target.getExp())
                        .put("join_date", target.getJoinDate())
                        .put("followers_count", followers)
                        .put("following_count", following)
                        .put("isFollowing", isFollowing)
                        .put("role", target.getRole().getString())
                        .toJson()
        );
    }

    // 更新用户资料（仅本人或管理员）
    public DataResponse updateUser(Long userId, JSONObject body) {
        if (userId == null || userId <= 0 || body == null) return DataResponse.failure(CommonErr.PARAM_WRONG);
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        UserPO target = userRepository.findById(userId).orElse(null);
        if (target == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        boolean isAdmin = me.getRole() != null && me.getRole() == UserType.ROLE_ADMIN;
        if (!isAdmin && !me.getId().equals(target.getId())) return DataResponse.failure(CommonErr.NO_AUTHORITY);
        if (body.containsKey("nickname")) {
            String nickname = body.getString("nickname");
            if (nickname != null && !nickname.isBlank()) target.setNickname(nickname);
        }
        if (body.containsKey("avatar")) {
            String avatar = body.getString("avatar");
            if (avatar != null && !avatar.isBlank()) target.setAvatar(avatar);
        }
        if (body.containsKey("email")) {
            String email = body.getString("email");
            if (email != null && !email.isBlank() && !email.equals(target.getEmail())) {
                if (userRepository.existsByEmail(email)) {
                    return DataResponse.failure(CommonErr.EMAIL_ALREADY_EXISTS);
                }
                target.setEmail(email);
            }
        }
        userRepository.save(target);
        return DataResponse.success(H.build().put("updated", true).put("id", target.getId()).toJson());
    }

    // 获取用户帖子列表
    public DataResponse getUserPosts(Long userId, Pageable pageable) {
        if (userId == null || userId <= 0) return DataResponse.failure(CommonErr.PARAM_WRONG);
        UserPO target = userRepository.findById(userId).orElse(null);
        if (target == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        UserPO me = currentUser();
        Page<PostPO> page = postRepository.findByAuthor_IdOrderByCreateTimeDesc(userId, pageable);
        JSONArray arr = new JSONArray();
        for (PostPO p : page.getContent()) {
            long likes = likeRepository.countByTargetTypeAndTargetId("POST", p.getId());
            boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "POST", p.getId());
            long comments = commentRepository.countByPost_Id(p.getId());
            String snippet = p.getContent() == null ? null : (p.getContent().length() > 120 ? p.getContent().substring(0,120) : p.getContent());
            arr.add(H.build()
                    .put("id", p.getId())
                    .put("title", p.getTitle())
                    .put("content", snippet)
                    .put("createTime", p.getCreateTime())
                    .put("views", p.getViews())
                    .put("likes", likes)
                    .put("commentsCount", comments)
                    .put("isLiked", isLiked)
                    .toJson());
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("posts", arr).put("pagination", pag.toJSON()).toJson());
    }

    // 获取用户关注的列表
    public DataResponse getUserFollowing(Long userId, Pageable pageable) {
        if (userId == null || userId <= 0) return DataResponse.failure(CommonErr.PARAM_WRONG);
        UserPO target = userRepository.findById(userId).orElse(null);
        if (target == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        Page<FollowPO> page = followRepository.findByFollower_Id(userId, pageable);
        com.alibaba.fastjson2.JSONArray arr = new com.alibaba.fastjson2.JSONArray();
        UserPO me = currentUser();
        for (FollowPO f : page.getContent()) {
            UserPO u = f.getFollowing();
            if (u == null) continue;
            boolean isFollowing = false;
            if (me != null && !me.getId().equals(u.getId())) {
                isFollowing = followRepository.existsByFollower_IdAndFollowing_Id(me.getId(), u.getId());
            }
            long followers = followRepository.countByFollowing_Id(u.getId());
            arr.add(H.build()
                    .put("id", u.getId())
                    .put("username", u.getUsername())
                    .put("nickname", u.getNickname())
                    .put("avatar", u.getAvatar())
                    .put("followers_count", followers)
                    .put("is_following", isFollowing)
                    .toJson());
        }
        Pagination pag = new Pagination(page.getTotalElements(), pageable.getPageNumber() + 1, pageable.getPageSize(), page.hasNext());
        return DataResponse.success(H.build().put("list", arr).put("pagination", pag.toJSON()).toJson());
    }

    // 获取用户收藏的电影、电视剧、综艺
    public DataResponse getUserFavorites(Long userId, Pageable pageable) {
        if (userId == null || userId <= 0) return DataResponse.failure(CommonErr.PARAM_WRONG);
        UserPO target = userRepository.findById(userId).orElse(null);
        if (target == null) return DataResponse.failure(CommonErr.RESOURCE_NOT_FOUND);
        UserPO me = currentUser();

        Page<FavoritePO> filmFavPage = favoriteRepository.findByUser_IdAndTargetTypeOrderByCreateTimeDesc(userId, "FILM", pageable);
        Page<FavoritePO> tvFavPage = favoriteRepository.findByUser_IdAndTargetTypeOrderByCreateTimeDesc(userId, "TVSHOW", pageable);
        Page<FavoritePO> varietyFavPage = favoriteRepository.findByUser_IdAndTargetTypeOrderByCreateTimeDesc(userId, "VARIETY", pageable);

        List<Long> filmIds = filmFavPage.getContent().stream().map(FavoritePO::getTargetId).toList();
        List<Long> tvIds = tvFavPage.getContent().stream().map(FavoritePO::getTargetId).toList();
        List<Long> varietyIds = varietyFavPage.getContent().stream().map(FavoritePO::getTargetId).toList();

        Map<Long, FilmPO> filmMap = new HashMap<>();
        if (!filmIds.isEmpty()) {
            for (FilmPO f : filmRepository.findAllById(filmIds)) filmMap.put(f.getId(), f);
        }
        Map<Long, TvShowPO> tvMap = new HashMap<>();
        if (!tvIds.isEmpty()) {
            for (TvShowPO t : tvShowRepository.findAllById(tvIds)) tvMap.put(t.getId(), t);
        }
        Map<Long, VarietyPO> varietyMap = new HashMap<>();
        if (!varietyIds.isEmpty()) {
            for (VarietyPO v : varietyRepository.findAllById(varietyIds)) varietyMap.put(v.getId(), v);
        }

        JSONArray filmArr = new JSONArray();
        for (FavoritePO fav : filmFavPage.getContent()) {
            FilmPO f = filmMap.get(fav.getTargetId());
            if (f == null) continue;
            long likes = likeRepository.countByTargetTypeAndTargetId("FILM", f.getId());
            boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "FILM", f.getId());
            Double rating = filmRepository.getAvgScore(f.getId());
            filmArr.add(H.build()
                    .put("id", f.getId())
                    .put("title", f.getTitle())
                    .put("poster", f.getPoster())
                    .put("year", f.getYear())
                    .put("likes", likes)
                    .put("rating", rating == null ? 0 : rating)
                    .put("isLiked", isLiked)
                    .put("isFavorited", true)
                    .toJson());
        }

        JSONArray tvArr = new JSONArray();
        for (FavoritePO fav : tvFavPage.getContent()) {
            TvShowPO t = tvMap.get(fav.getTargetId());
            if (t == null) continue;
            long likes = likeRepository.countByTargetTypeAndTargetId("TVSHOW", t.getId());
            boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "TVSHOW", t.getId());
            Double rating = tvShowRepository.getAvgScore(t.getId());
            tvArr.add(H.build()
                    .put("id", t.getId())
                    .put("title", t.getTitle())
                    .put("poster", t.getPoster())
                    .put("year", t.getYear())
                    .put("likes", likes)
                    .put("rating", rating == null ? 0 : rating)
                    .put("isLiked", isLiked)
                    .put("isFavorited", true)
                    .toJson());
        }

        JSONArray varietyArr = new JSONArray();
        for (FavoritePO fav : varietyFavPage.getContent()) {
            VarietyPO v = varietyMap.get(fav.getTargetId());
            if (v == null) continue;
            long likes = likeRepository.countByTargetTypeAndTargetId("VARIETY", v.getId());
            boolean isLiked = me != null && likeRepository.existsByUser_IdAndTargetTypeAndTargetId(me.getId(), "VARIETY", v.getId());
            Double rating = varietyRepository.getAvgScore(v.getId());
            varietyArr.add(H.build()
                    .put("id", v.getId())
                    .put("title", v.getTitle())
                    .put("poster", v.getPoster())
                    .put("year", v.getYear())
                    .put("likes", likes)
                    .put("rating", rating == null ? 0 : rating)
                    .put("isLiked", isLiked)
                    .put("isFavorited", true)
                    .toJson());
        }

        Pagination filmPag = new Pagination(filmFavPage.getTotalElements(), pageable.getPageNumber()+1, pageable.getPageSize(), filmFavPage.hasNext());
        Pagination tvPag = new Pagination(tvFavPage.getTotalElements(), pageable.getPageNumber()+1, pageable.getPageSize(), tvFavPage.hasNext());
        Pagination varietyPag = new Pagination(varietyFavPage.getTotalElements(), pageable.getPageNumber()+1, pageable.getPageSize(), varietyFavPage.hasNext());

        return DataResponse.success(H.build()
                .put("movies", filmArr)
                .put("tvshows", tvArr)
                .put("varieties", varietyArr)
                .put("pagination", H.build()
                        .put("movies", filmPag.toJSON())
                        .put("tvshows", tvPag.toJSON())
                        .put("varieties", varietyPag.toJSON())
                        .toJson())
                .toJson());
    }

    public DataResponse getCurrentUserInfo() {
        UserPO me = currentUser();
        if (me == null) return DataResponse.failure(CommonErr.NO_AUTHENTICATION);
        long followers = followRepository.countByFollowing_Id(me.getId());
        long following = followRepository.countByFollower_Id(me.getId());
        return DataResponse.success(
                H.build()
                        .put("id", me.getId())
                        .put("username", me.getUsername())
                        .put("nickname", me.getNickname())
                        .put("avatar", me.getAvatar())
                        .put("email", me.getEmail())
                        .put("level", me.getLevel())
                        .put("exp", me.getExp())
                        .put("role", me.getRole()==null?null:me.getRole().getString())
                        .put("followers_count", followers)
                        .put("following_count", following)
                        .put("join_date", me.getJoinDate())
                        .toJson()
        );
    }
}
