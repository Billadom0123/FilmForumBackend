-- 自动生成电影/演员/类型插入脚本 (清洗后)
START TRANSACTION;
-- 幂等处理：仅当不存在同名演员时插入
SET @director_id = NULL;
-- 电影 太极
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 中国香港', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1833562883.jpg', '周星驰在片中饰演一名隐姓埋名的太极宗师，他移居美国在唐人街打工洗盘子，为了保护受暴徒威胁的同胞们，他挺身而出，此后创办了武术学校将他的一身好功夫传授给他人。', '太极', NULL, 0, 2025, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '安妮·海瑟薇');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '太极');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '香港');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '中国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
-- 演员/角色
INSERT INTO actor(name) SELECT '周星驰' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='周星驰');
SET @actor_id = (SELECT id FROM actor WHERE name='周星驰');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 功夫2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('中国大陆 / 中国香港', 100, '汉语普通话 / 粤语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2043023919.jpg', '暂无', '功夫2', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '周星驰');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '功夫2');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '香港');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '香港电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '搞笑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '华语电影');

SET @director_id = NULL;
-- 电影 破娃娃
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语 / 西班牙语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1710560149.jpg', 'Tori Alvarez - a sexy 18 year old Mexican American girl is struggling to be the first in her family to graduate from high school. She begins a torrid affair with an older cop who happens to be the Father of her best friend. The affair triggers a chain of events that will change the lives of those around her, forever. Straddling the line between redemption and despair - an unexpected twist sends life for Tori spiraling out of control into a web of sex, deceit, gangs, drugs, abuse, revenge, and murder.', '破娃娃', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '4.0星');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '纪录片美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Serratos3');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Christian');
-- 演员/角色
INSERT INTO actor(name) SELECT '西拉·拉米雷斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='西拉·拉米雷斯');
SET @actor_id = (SELECT id FROM actor WHERE name='西拉·拉米雷斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '伊万·罗斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='伊万·罗斯');
SET @actor_id = (SELECT id FROM actor WHERE name='伊万·罗斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾德里安娜·巴拉扎' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾德里安娜·巴拉扎');
SET @actor_id = (SELECT id FROM actor WHERE name='艾德里安娜·巴拉扎');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '费尔南达·罗梅罗' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='费尔南达·罗梅罗');
SET @actor_id = (SELECT id FROM actor WHERE name='费尔南达·罗梅罗');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '伊迪·加内姆' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='伊迪·加内姆');
SET @actor_id = (SELECT id FROM actor WHERE name='伊迪·加内姆');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '沙利姆·奥提兹' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='沙利姆·奥提兹');
SET @actor_id = (SELECT id FROM actor WHERE name='沙利姆·奥提兹');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 出现在我脑海
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国 / 匈牙利', 100, '英语', '存在我深深的脑海里', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2498522264.jpg', 'An intense and erotic love is born out of loneliness and secret passion. The woman is married. The young soldier is starting to recover. She is not who he thinks she is. A film about the urgency of deceit and desire; seeing and not seeing.', '出现在我脑海', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JackLowden');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'SallyHawkins');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'UK');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Oli...');
-- 演员/角色
INSERT INTO actor(name) SELECT '杰克·劳登' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰克·劳登');
SET @actor_id = (SELECT id FROM actor WHERE name='杰克·劳登');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 通缉令2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '刺客联盟2', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530218352.jpg', '去年票房大卖的影片《通缉令》（Wanted，又译刺客联盟）的导演提莫·贝克曼贝托夫（Timur Bekmambetov）近日接受俄罗斯媒体采访时透露，《通缉令2》将于今年年底开拍，主演安吉丽娜·朱丽的角色有望“复活”。', '通缉令2', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'AngelinaJolie');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JamesMcAvoy');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '刺客联盟2');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '刺客联盟');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电...');

SET @director_id = NULL;
-- 电影 革命往事
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('法国', 100, '西班牙语', '派其奥·维拉的七个朋友，以及一个六根手指的女人', 'https://img1.doubanio.com/view/subject/l/public/s4061297.jpg', '塞尔维亚导演埃米尔·库斯图里卡（Emir Kusturica）最近在筹拍一部讲述墨西哥革命英雄派其奥·维拉（Pancho Villa）故事的西班牙语传记片，约翰尼·德普（Johnny Depp）有望担纲主演，扮演这位墨西哥历史上著名的开国功臣。', '革命往事', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '传记');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JohnnyDepp');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'EmirKusturica');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '约翰尼·德普');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '西班牙');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影...');

SET @director_id = NULL;
-- 电影 窈窕淑女
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, '英语', null, 'https://img3.doubanio.com/view/subject/l/public/s4252822.jpg', '卖花女伊莉莎·杜利特尔，长得眉清目秀，聪明伶俐，但出身寒微，家境贫寒。她每天到街头叫卖鲜花，赚点钱养活自己补贴父亲。一天，伊莉莎低俗的口音引起了语言学家希金斯教授的注意，教授夸口只要经过他的训练，卖花女也可以成为贵夫人。伊莉莎觉得教授说的话对她是一个机会，就主动上门要求 教授训练她，并付学费。被嘲弄后，教授的朋友皮克林（为了成全伊莉莎）和他打赌，如果让伊莉莎以贵夫人的身份出席两个月后将举办的大使游园会而不被人识破真相，那么皮克林愿意承担一切试验费用和伊莉莎的学费，这激起了教授的斗志，希金斯欣然接受了挑战。他是不甘示弱的，他从最基本的字母发音开始教起。希金斯是个精力旺盛和讲究科学的学者，对每一件感兴趣的事都能废寝忘食。他胸怀坦荡、丝毫不怀任何恶意，但他又象孩子一样，毫不顾及他人的感情，对伊莉莎严加训练。 来源：', '窈窕淑女', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '歌舞');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'CareyMulligan');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'KeiraKnightley');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电...');

SET @director_id = NULL;
-- 电影 牧羊少年奇幻之旅
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '炼金之旅', 'https://img3.doubanio.com/view/subject/l/public/s2567190.jpg', 'Paulo Coelho 畅销新世纪(new age)灵魂小说《The Alchemist》(炼金士)将被华纳兄弟(WB)搬上大银幕。该书一经出版受到大量读者好评，号称一部触动灵魂之作。', '牧羊少年奇幻之旅', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '小说改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '梦想');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '成长');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '炼金术士');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '经典');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '人之所以为人');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');

SET @director_id = NULL;
-- 电影 古驰
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '古琦', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2513457765.jpg', '王家卫又有了新项目。金牌女制作人梅根·埃里森已与墨镜王接洽，邀后者执导雷德利·斯科特搁置多年、描绘Gucci家族的犯罪传记片。该项目将聚焦Gucci创始人Guccio Gucci的孙子凶杀犯Maurizio Gucci。原定李奥纳多·迪卡普里奥和安吉丽娜·朱莉出演。现在则传闻玛格特·罗比加盟，饰演Patrizia Reggiani，这位被判定谋杀前夫，而锒铛入狱的女人。Andrea Berloff和Charles Randolph曾撰写本片剧本初稿，尚不确定谁会来接手最终剧本。', '古驰', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '传记');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '王家卫');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Gucci');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '@王家卫');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '人物传记');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '安吉丽娜朱丽');

SET @director_id = NULL;
-- 电影 未定名我是传奇重启版
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '我是传奇2', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2160072982.jpg', '据美国媒体报道，华纳兄弟一直在筹备《我是传奇》的续集（或前传），但由于男主角威尔·史密斯没有回归的意愿，华纳只能另辟蹊径重启该项目，新故事将和原版的《我是传奇》没有任何关系。', '未定名我是传奇重启版', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '威尔·史密斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我是传奇2');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'WillSmith');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');

SET @director_id = NULL;
-- 电影 Interworld
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, null, null, 'https://img3.doubanio.com/view/subject/l/public/s2565416.jpg', null, 'Interworld', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'NeilGaiman');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '小说改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Neil·Gaiman');

SET @director_id = NULL;
-- 电影 魔法奇缘2：解除魔法
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '解除魔法', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2564087044.jpg', '据悉，《魔法奇缘2》（Enchanted 2）将由曾执导过桑德拉·布洛克卖座浪漫喜剧《假结婚》（The Proposal）的女导演安妮·弗莱切（Anne Fletcher）执导，曾创作《蓝精灵》（The Smurfs）、《怪物史瑞克2》（Shrek 2）等影片剧本的J. David Stem和David N. Weiss将担任编剧。', '魔法奇缘2：解除魔法', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '家庭');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '童话');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '迪士尼');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '魔幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '啥？第二部？');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'AmyAdams');
-- 演员/角色
INSERT INTO actor(name) SELECT '艾米·亚当斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾米·亚当斯');
SET @actor_id = (SELECT id FROM actor WHERE name='艾米·亚当斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 阿基拉
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '美国版阿基拉', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2113286655.jpg', '莱昂纳多迪卡普里奥非常热心地想以制片人的身份将其搬上大银幕，但由于一直没有成形的令人满意的剧本，资金也没有到位，并且至今也没有一位有能力肩负1.5亿美元投资的巨星表示愿意加盟（迪卡普里奥本人曾经被传言将出演Kaneda，但后来被证实只是谣言；制片方曾接触过布拉德皮特，但皮特拒绝了该片）。Kaneda一角还曾被传言归属Zac Efron，最近又传出James Franco正在协商出演Kaneda，Mila Kunis也可能加入该片——但师太预测这一切都将化为浮云，除非投资规模大幅度缩小，并且剧本能在近期竣工，我不认为James Franco会接下该角色。所以，所有的原著迷们暂时可以松一口气了，相信在相当长的一段时间内这部电影不会变成现实。', '阿基拉', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'LeonardoDiCaprio');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫畫改編');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JosephGordon-Levit...');

SET @director_id = NULL;
-- 电影 Hansel and Gretel in 3D
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('德国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p553079042.jpg', null, 'Hansel and Gretel in 3D', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'null');

SET @director_id = NULL;
-- 电影 不明飞行物
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国 / 美国', 100, null, null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2263374841.jpg', 'Based in the near future - where a super secret military organization SHADO (Supreme Headquarters Alien Defense Organization) becomes operational. Its purpose, to defend Earth from an Alien race, which has been abducting humans from all corners of the planet for decades. SHADO''s cover is a London movie studio where their main headquarters are hidden deep below the surface. SHADO, run by the extremely dedicated "Studio Mogul" and SHADO Commander Ed Straker has a cadre of crack operatives with an arsenal of cutting edge futuristic weapons systems and hidden bases on earth and beyond. UFO is the first in a trilogy of tent-pole movies based on the 1970''s cult TV show, UFO, created by Gerry Anderson.', '不明飞行物', NULL, 0, 2022, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'U.F.O.');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '烂片');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '垃圾');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '外星人');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美國');

SET @director_id = NULL;
-- 电影 活死人军团
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '死亡之师 / 死亡军团 / 僵尸军团', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2546803041.jpg', '自扎克·施奈德因“家庭变故”中途离开《正义联盟》导演岗位之后（也有说法称他是被华纳DC炒了鱿鱼），就一直未有长片新作推出。今天，扎导自《正义联盟》之后第一个长片执导项目终于官宣了——他将为Netflix执导丧尸惊悚片《死亡之师》（暂译，Army of the Dead）。值得一提的是，扎导15年前的导演处女作《活死人黎明》就是丧尸片。在为华纳DC执导多部超级英雄大片后，扎导又重回开始的地方。', '活死人军团', NULL, 0, 2021, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '扎克·施奈德');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '丧尸片');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'ZackSnyder');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Netflix');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '丧尸');
-- 演员/角色
INSERT INTO actor(name) SELECT '戴夫·巴蒂斯塔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='戴夫·巴蒂斯塔');
SET @actor_id = (SELECT id FROM actor WHERE name='戴夫·巴蒂斯塔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾拉·珀内尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾拉·珀内尔');
SET @actor_id = (SELECT id FROM actor WHERE name='艾拉·珀内尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '加瑞特·迪拉胡特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='加瑞特·迪拉胡特');
SET @actor_id = (SELECT id FROM actor WHERE name='加瑞特·迪拉胡特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安娜·德拉·雷古拉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安娜·德拉·雷古拉');
SET @actor_id = (SELECT id FROM actor WHERE name='安娜·德拉·雷古拉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '西奥·罗西' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='西奥·罗西');
SET @actor_id = (SELECT id FROM actor WHERE name='西奥·罗西');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '诺拉·阿娜泽德尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='诺拉·阿娜泽德尔');
SET @actor_id = (SELECT id FROM actor WHERE name='诺拉·阿娜泽德尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克里斯·德埃利亚' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里斯·德埃利亚');
SET @actor_id = (SELECT id FROM actor WHERE name='克里斯·德埃利亚');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '真田广之' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='真田广之');
SET @actor_id = (SELECT id FROM actor WHERE name='真田广之');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '欧玛瑞·哈德威克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='欧玛瑞·哈德威克');
SET @actor_id = (SELECT id FROM actor WHERE name='欧玛瑞·哈德威克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '胡玛·库雷希' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='胡玛·库雷希');
SET @actor_id = (SELECT id FROM actor WHERE name='胡玛·库雷希');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '劳尔·卡斯提洛' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='劳尔·卡斯提洛');
SET @actor_id = (SELECT id FROM actor WHERE name='劳尔·卡斯提洛');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '萨曼塔·若' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='萨曼塔·若');
SET @actor_id = (SELECT id FROM actor WHERE name='萨曼塔·若');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马提亚斯·施维赫夫' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马提亚斯·施维赫夫');
SET @actor_id = (SELECT id FROM actor WHERE name='马提亚斯·施维赫夫');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '理查德·赛特伦' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='理查德·赛特伦');
SET @actor_id = (SELECT id FROM actor WHERE name='理查德·赛特伦');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 罗伯
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2542547645.jpg', '华纳兄弟公司将请盖伊·里奇（Guy Ritchie）执导根据DC漫画《罗伯》（Lobo）改编的同名电影。', '罗伯', NULL, 0, 2021, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'DC');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '超级英雄');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '期待我最喜欢的角色');

SET @director_id = NULL;
-- 电影 真人快打：毁灭
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('澳大利亚 / 美国', 100, '英语', '格斗之王', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2263282664.jpg', '援引That Hashtag Show消息，乔尔·埃哲顿是新线游改电影[真人快打：毁灭]中Kano角色的首选。埃哲顿此番将使用他的澳洲本土口音。本片由温子仁担任制片人，西蒙·麦夸伊德执导，格雷格·罗素已完成了剧本。影片将围绕一个身心俱疲的拳击手科尔·特纳展开，他被招募来帮助保护地球和他的女儿。他必须加入一支战斗队伍，与来自另一个维度的杀手进行一场永恒的战斗。该片有望今夏在澳洲开机。', '真人快打：毁灭', NULL, 0, 2021, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '真人快打');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '游戏改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '游戏电影!');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Mortal');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Kombat');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
-- 演员/角色
INSERT INTO actor(name) SELECT '乔·塔斯利姆' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='乔·塔斯利姆');
SET @actor_id = (SELECT id FROM actor WHERE name='乔·塔斯利姆');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '林路迪' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='林路迪');
SET @actor_id = (SELECT id FROM actor WHERE name='林路迪');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '浅野忠信' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='浅野忠信');
SET @actor_id = (SELECT id FROM actor WHERE name='浅野忠信');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '刘易斯·谭' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='刘易斯·谭');
SET @actor_id = (SELECT id FROM actor WHERE name='刘易斯·谭');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '真田广之' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='真田广之');
SET @actor_id = (SELECT id FROM actor WHERE name='真田广之');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '黄经汉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='黄经汉');
SET @actor_id = (SELECT id FROM actor WHERE name='黄经汉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '麦卡德·布鲁克斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='麦卡德·布鲁克斯');
SET @actor_id = (SELECT id FROM actor WHERE name='麦卡德·布鲁克斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰西卡·麦克娜美' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰西卡·麦克娜美');
SET @actor_id = (SELECT id FROM actor WHERE name='杰西卡·麦克娜美');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '约什·劳森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='约什·劳森');
SET @actor_id = (SELECT id FROM actor WHERE name='约什·劳森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 养鬼吃人
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '新养鬼吃人', 'https://img1.doubanio.com/view/subject/l/public/s6875508.jpg', '经典恐怖片[养鬼吃人]将被重启。大卫·S·高耶([蝙蝠侠：黑暗骑士])将担任重启影片制片人与编剧。在这30年来，[养鬼吃人]这个IP已推出10部影片、漫画及周边商品，首部[养鬼吃人]于1987年上映，故事改编自克莱夫·巴克小说《受地狱羁绊之心》，讲述英国人弗兰克·科顿通过某种神秘的仪式打开了魔方和另一个世界的通道，魔鬼赛诺贝斯降临人间，弗兰克也为其所杀。在此之后，弗兰克的哥哥莱瑞和妻子朱莉搬到这座房子里。朱莉是莱瑞的新任妻子，她曾背着丈夫和弗兰克有染。莱瑞搬家具时不慎弄伤右手，血滴到阁楼地板上，偏巧使埋在下面的弗兰克得以复苏。为了使情人恢复本来面貌，朱莉开始想方设法为其奉上活人的血肉。', '养鬼吃人', NULL, 0, 2021, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '克里夫·巴克');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '三浦');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Horror');

SET @director_id = NULL;
-- 电影 岛王
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, null, null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2552557087.jpg', '莎莉·霍金斯、基里安·墨菲、渡边谦、拉菲·卡西迪将担任动画影片[岛王](Kensuke''s Kingdom，暂译)配音。影片故事根据迈克尔·莫波格同名小说改编，讲述麦克坐船环游世界途中，他和小狗史特拉被风浪扫落大海。靠着一个足球，他们漂浮到一座荒岛。荒岛上住着一个二次世界大战因船难流亡的日本老兵健介。[淘气大侦探]动画师尼尔·鲍伊、柯尔克·亨德瑞执导。', '岛王', NULL, 0, 2021, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '温情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'murphy');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'cillian');
-- 演员/角色
INSERT INTO actor(name) SELECT '基里安·墨菲' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='基里安·墨菲');
SET @actor_id = (SELECT id FROM actor WHERE name='基里安·墨菲');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莎莉·霍金斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莎莉·霍金斯');
SET @actor_id = (SELECT id FROM actor WHERE name='莎莉·霍金斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '渡边谦' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='渡边谦');
SET @actor_id = (SELECT id FROM actor WHERE name='渡边谦');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '拉菲·卡西迪' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='拉菲·卡西迪');
SET @actor_id = (SELECT id FROM actor WHERE name='拉菲·卡西迪');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 全民超人汉考克2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2493060550.jpg', '据《好莱坞报道》消息，哥伦比亚公司已确定由亚当&middot;费耶罗和格伦&middot;马扎拉操刀《全民超人汉考克2》的剧本，《全民超人汉考克》原班制作人马也有望回归。《全民超人》花费了12年时间才最终搬上大银幕，本片在2008年收入了6.24亿美元全球票房，这个不错的成绩决定了续集的进展要远远超过上一集', '全民超人汉考克2', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '威尔·史密斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'WillSmith');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '威尔史密斯');

SET @director_id = NULL;
-- 电影 拥挤的房间
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '24个比利', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2251159789.jpg', '《24个比利》(The Crowded Room，直译为拥挤的房间)改编自著名作家丹尼尔·凯斯的多重人格分裂纪实小说《24个比利》与《比利战争》（比利战争在美国一直无法出版），其主角正是臭名昭著的美国话题人物比利·米利根。上世纪70年代末，他在校园犯下三起强奸案被捕，律师以其患多重人格障碍进行辩护，最终无罪释放，但却持续被媒体和公 众质疑“假借多重人格脱罪”。', '拥挤的房间', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '24个比利');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '多重人格');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '心理');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'LeonardoDiCa...');
-- 演员/角色
INSERT INTO actor(name) SELECT '莱昂纳多·迪卡普里奥' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莱昂纳多·迪卡普里奥');
SET @actor_id = (SELECT id FROM actor WHERE name='莱昂纳多·迪卡普里奥');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 洛克军士长
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2383556802.jpg', '洛克中士》的主角是一个美军步兵中士弗兰克·洛克（Sgt. Frank Rock），他带领Easy Company连的弟兄在二战战场上浴血奋战。', '洛克军士长', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'DC');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画改编');

SET @director_id = NULL;
-- 电影 康斯坦丁2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '地狱神探2', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2558509825.jpg', 'Constantine （译：地狱神探）电影名称，此片改编自畅销漫画《地狱神探》。由弗朗西斯·劳伦斯导演，基努·里维斯、雷切尔·薇姿、蒂尔达·斯文顿等出演的惊悚恐怖片。《地狱神探》是一部介乎于善与恶、对于错之间的故事片，是一部带有大量动作场面与精彩对话的娱乐片，会令你耐 心地坐在椅子上从头看到尾。', '康斯坦丁2', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '地狱神探2');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '基努里维斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '魔幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '宗教');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '康斯坦丁2');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'KeanuReeves');

SET @director_id = NULL;
-- 电影 我，机器人2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '机械公敌2 / 智能叛变2', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p589731063.jpg', 'In the year 2035 a techno-phobic cop investigates a crime that may have been perpetrated by a robot, which leads to a larger threat to humanity.', '我，机器人2', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '威尔·史密斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '机械公敌');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '机器人');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'WillSmith');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '值得期待');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');

SET @director_id = NULL;
-- 电影 糊涂侦探2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2314411782.jpg', 'Maxwell "Max" Smart, an analyst for the top secret American intelligence agency, CONTROL, yearns to become a field agent like his idol, Agent 23. Despite top scores in the acceptance tests, Max is denied the promotion due to his higher value as an analyst. m.ysgou.cc When CONTROL headquarters is attacked by the terrorist organization KAOS, almost all of CONTROL''s agents'' identities are exposed, leaving only Agent 99 as a viable field operative. Max is also promoted to field agent as Agent 86, but the experienced 99 is reluctant to partner with him because of his inexperience. On the first day of his job, Max receives a Swiss army knife which includes special add-ons like a miniature flamethrower and a crossbow that shoots darts attached to spider web thread.', '糊涂侦探2', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'SteveCarell');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '安妮海瑟薇');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '西片');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');

SET @director_id = NULL;
-- 电影 比扎罗
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '异魔', 'https://img1.doubanio.com/view/subject/l/public/s6521947.jpg', null, '比扎罗', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'DC');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '反英雄主义');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '超英');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');

SET @director_id = NULL;
-- 电影 黑湖妖潭
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语 / 英语', null, 'https://img3.doubanio.com/view/subject/l/public/s22719736.jpg', '环球影业并没有放弃翻拍《黑湖妖谭》的计划，近日有消息传出，曾为《超凡蜘蛛侠2》撰写剧本的杰夫·平克纳将执笔这部惊悚冒险片的重启之作。 《黑湖妖谭》是1954年由杰克·阿诺德执导的惊悚片，讲述了一群深入亚马逊丛林的地质科考队发现了一头史前鱼怪，而鱼怪爱上并掳走了一名科考队员(理查德·加尔森 饰)的女友(朱莉·亚当斯 饰)。 据报道，杰夫·平克纳已经与环球影业签约，他将在戴夫·凯甘尼奇撰写的第一版剧本上进行修改和加工，前者曾于2012年写出了《黑湖妖谭》初稿。除了《黑湖妖谭》外，杰夫·平克纳日前还加入了派拉蒙“变形金刚电影宇宙”编剧团队，将与阿齐瓦·高斯曼共同执笔《变形金刚5》的剧本。', '黑湖妖潭', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '怪兽');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');

SET @director_id = NULL;
-- 电影 小美人鱼
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '小美人鱼真人版', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2208998048.jpg', '由环球和Working Title公司联合制作的真人版《小美人鱼》（The Little Mermaid）最近又失去了它的导演——索菲亚·科波拉（Sofia Coppola）。这部根据安徒生（Hans Christian Andersen）经典童话《海的女儿》改编的电影迄今已经先后失去两位导演，在科波拉之前，还有英国导演乔·怀特（Joe Wright）。', '小美人鱼', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '艾玛沃森');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画小说真人版');

SET @director_id = NULL;
-- 电影 棒棒骨
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, null, '鬼骨兄弟 / 鬼骨家族', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2458866562.jpg', '导演马克·奥斯本将为华纳把人气漫画《鬼骨家族(Bone，暂译)》搬上大银幕，并与亚当·凯莱联手负责剧本。原漫画曾被《时代》评为2004年十大漫画之一，画风属于滑稽复杂的冒险漫画：3个鬼骨家族小伙伴在一个村庄走散，被一个小女孩和她外婆拯救后，发现村庄被邪恶力量所操控。', '棒棒骨', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待看');

SET @director_id = NULL;
-- 电影 无足轻重的女人
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '一个无关紧要的女人', 'https://img3.doubanio.com/view/subject/l/public/s4473806.jpg', '故事根据王尔德的同名作品改编而成。女主人公在年轻时与一位男子相恋，并生下了一个私生子，然而该男子此后却音信全无。多年后，女主人公发现自己儿子的未来雇主竟然就是当初那个负心人，她该如何面对那段不堪回首的过往呢？', '无足轻重的女人', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '王尔德');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'amandaseyfried');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'AnnetteBening');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'sienn...');

SET @director_id = NULL;
-- 电影 神秘海域
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '神秘海域：德雷克船长的宝藏', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2558964271.jpg', '汤姆·霍兰德主演的《神秘海域》真人电影仍在开发，日前该项目的创意总监、同名游戏的开发人Neil Druckmann接受采访，分享了一些想法。该片此前已经定下由肖恩·利维(《铁甲钢拳》《博物馆奇妙夜》)执导，他决定不根据已经出的4款同名游戏进行改编，而是讲述男主角Nathan Drake的起源故事。这也得到了Druckmann的支持，他认为比起仅仅将原游戏搬上大银幕，开发新故事要有趣得多。', '神秘海域', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '游戏改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'RobertDeNiro');
-- 演员/角色
INSERT INTO actor(name) SELECT '汤姆·赫兰德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汤姆·赫兰德');
SET @actor_id = (SELECT id FROM actor WHERE name='汤姆·赫兰德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 间谍猎手
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/subject/l/public/s2987672.jpg', '本片改编自1983年版的同名游戏。前F-15战斗机飞行员亚历克斯重被政府招募，加入了一个名叫国际间谍处的组织，成为间谍猎手。亚历克斯将要面对的是一伙用高科技武装试图控制全世界的坏蛋，为了维护社会安定，亚历克斯驾驶着G-6155高级飞车，开始了他间谍猎手的职业生涯。', '间谍猎手', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我看过的英语电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我看过的电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '看电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我的电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '间谍');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '赛车');

SET @director_id = NULL;
-- 电影 死亡拼图
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2513464454.jpg', '张艺谋签约好莱坞环球影业，将改编拍摄《谍影重重》原作者罗伯特·鲁德鲁姆的另一部经典作品《死亡拼图》（The Parsifal Mosaic）。这是一部精彩的间谍小说，出版于1982年冷战后期，讲述的是一名美国特工亲眼目睹了他的爱人被处决，尔后又在罗马重新遇见他的爱人，为追查爱人的生死之谜，他卷入了一起跨国阴谋。朗·霍华德将担任本片制片。', '死亡拼图', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '张艺谋');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '章子怡');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '死亡拼图');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '张艺谋导演');

SET @director_id = NULL;
-- 电影 爱难开口
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, '英语', '想说爱你不容易 / 爱你在心口难开 / 难以开口 / 无语 / 我的失语男友 / 想说爱你并不是一件很容易的事', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2378494591.jpg', '英国老帅哥休·格兰特（Hugh Grant）已经离开浪漫喜剧片《爱难开口》（Lost for Words）的剧组，放弃了此片的演出。休·格兰特的一名发言人说原因是创作上的分歧，主要问题是不能在剧本上意见达成一致，不过休·格兰特仍不失风度地祝愿影片一切顺利。', '爱难开口', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '章子怡');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'HughGrant');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '新片.期待');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');

SET @director_id = NULL;
-- 电影 身在高地
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '活在上城', 'https://img1.doubanio.com/view/subject/l/public/s4107759.jpg', '华纳以5000万美元购得音乐剧《身在高地》电影拍摄权。该片2016年最早由韦恩斯坦公司立项，自韦恩斯坦宣布破产后，影片的拍摄权引发多家公司竞标。音乐剧《身在高地》由百老汇全能才子林-曼努尔·米兰达主演，曾获封2008年托尼奖最佳音乐剧。新片导演朱浩伟，原剧编剧奎埃拉·阿里格里亚·赫德兹操刀剧本，影片围绕纽约曼哈顿区一个拉丁移民家庭的追梦故事展开。', '身在高地', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '歌舞');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '音乐剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'LMM');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'musical');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '舞台剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待映');
-- 演员/角色
INSERT INTO actor(name) SELECT '林-曼努尔·米兰达' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='林-曼努尔·米兰达');
SET @actor_id = (SELECT id FROM actor WHERE name='林-曼努尔·米兰达');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '丽塔·莫雷诺' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='丽塔·莫雷诺');
SET @actor_id = (SELECT id FROM actor WHERE name='丽塔·莫雷诺');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 金属人
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '金属战队', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2562646342.jpg', 'Based on a DC Comics hero created by Robert Kanigher and Ross Andru in 1962, "Metal" revolves around a brilliant scientist, William Magnus, and his creations: six highly advanced robots who have powers associated with their respective metals -- gold, iron, lead, tin, mercury and platinum. Instead of having to be programed, the Metal Men can think for themselves, which is both their genius and their biggest flaw.', '金属人', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'DC');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');

SET @director_id = NULL;
-- 电影 比尔和泰德寻歌记
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2521615678.jpg', '此前基努·里维斯、亚历克斯·温特已确认联手回归喜剧[比尔和泰德]系列第三部[比尔和泰德寻歌记]，导演迪恩·帕里索特，该系列原编剧克里斯·马瑟森、艾德·索罗门操刀剧本。但近日里维斯却对该片能否如期而至表示不太确定。里维斯是在接受“雅虎娱乐”采访时透露的这一消息，他称由于一些幕后问题，该片能否照原计划进行现在并不确定。里维斯表示：“我们为这部片子已经等了很长时间，但现在还是存在一些‘挑战’。我很喜欢这个系列的这些角色，新片剧本已经就绪，故事很棒，但在‘资金、版权、合同’上还存在一些问题。”', '比尔和泰德寻歌记', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '音乐');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '基努里维斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'KeanuReeves');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待看');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '比尔和泰德寻歌记');
-- 演员/角色
INSERT INTO actor(name) SELECT '基努·里维斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='基努·里维斯');
SET @actor_id = (SELECT id FROM actor WHERE name='基努·里维斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '萨玛拉·维文' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='萨玛拉·维文');
SET @actor_id = (SELECT id FROM actor WHERE name='萨玛拉·维文');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安东尼·凯瑞根' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安东尼·凯瑞根');
SET @actor_id = (SELECT id FROM actor WHERE name='安东尼·凯瑞根');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '亚历克斯·温特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='亚历克斯·温特');
SET @actor_id = (SELECT id FROM actor WHERE name='亚历克斯·温特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '威廉姆·赛德勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='威廉姆·赛德勒');
SET @actor_id = (SELECT id FROM actor WHERE name='威廉姆·赛德勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰玛·梅斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰玛·梅斯');
SET @actor_id = (SELECT id FROM actor WHERE name='杰玛·梅斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '埃瑞恩·海耶斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='埃瑞恩·海耶斯');
SET @actor_id = (SELECT id FROM actor WHERE name='埃瑞恩·海耶斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吉莉恩·贝尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吉莉恩·贝尔');
SET @actor_id = (SELECT id FROM actor WHERE name='吉莉恩·贝尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布里盖特·伦迪·佩恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布里盖特·伦迪·佩恩');
SET @actor_id = (SELECT id FROM actor WHERE name='布里盖特·伦迪·佩恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '贝克·班尼特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='贝克·班尼特');
SET @actor_id = (SELECT id FROM actor WHERE name='贝克·班尼特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '卡迪小子' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='卡迪小子');
SET @actor_id = (SELECT id FROM actor WHERE name='卡迪小子');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '小哈尔·兰登' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='小哈尔·兰登');
SET @actor_id = (SELECT id FROM actor WHERE name='小哈尔·兰登');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 合金装备
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 日本', 100, '法语 / 西班牙语 / 日语 / 英语', '潜龙谍影(台)', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2533737687.jpg', '索尼有意将小岛秀夫的游戏《合金装备》搬上大银幕，目前与作家杰·巴苏([怪物：黑暗大陆]编剧)达成协议，执笔电影版剧本。《合金装备》是从红白机时代以来一直风靡全球的经典潜入类游戏，主角蛇君Snake是最知名的游戏人物之一，乔丹·福格特-罗伯茨([夏日之王])有望担当电影版[合金装备]导演。', '合金装备', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '合金装备');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '游戏改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '小岛秀夫');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '日本');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '经典');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
-- 演员/角色
INSERT INTO actor(name) SELECT '奥斯卡·伊萨克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥斯卡·伊萨克');
SET @actor_id = (SELECT id FROM actor WHERE name='奥斯卡·伊萨克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 马可·波罗
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 中国大陆', 100, '英语', '丝绸之路：马可·波罗游记', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2183002604.jpg', '影片会以13世纪（中国元朝时期）意大利人马可·波罗游历中国为背景，但会拍成一部魔幻电影，影片由派拉蒙和中国的电影公司合作拍摄。', '马可·波罗', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'TarsemSingh');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '高中笔记本');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '欧美古装电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '未上映2017');

SET @director_id = NULL;
-- 电影 红圈
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2185244822.jpg', '塔里克·萨利赫(《西部世界》)将执导新版[红圈]。影片翻拍自1970年同名影片，老版导演让-皮埃尔·梅尔维尔，主演阿兰·德龙、布尔维尔、吉安·玛丽亚·沃隆特、伊夫·蒙当，影片围绕一个猫捉老鼠式的惊悚故事展开。斯蒂文·奈特(《浴血黑帮》)操刀新片剧本。', '红圈', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '杜琪峰');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我看过的电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '银河映像');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '香港电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '周润发');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '香港');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我看过的华语电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '华语电影');

SET @director_id = NULL;
-- 电影 疯狂山脉
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2557101824.jpg', null, '疯狂山脉', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '克苏鲁');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Lovecraft');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '洛夫克拉夫特');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');

SET @director_id = NULL;
-- 电影 绝地战警3
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2340231160.jpg', 'Marcus Burnett(劳伦斯)在与Mike Lowery(史密斯)闹翻后，离开了警局，成为了一名私家侦探，Lowery正在经历中年危机，与一个傲慢但忠诚的年轻搭档打交道。反派是一个阿尔巴尼亚雇佣兵，为了给死去的哥哥报仇，他向Burnett和Lowery两个前搭档展开攻击，闹别扭的两人不得不重新组队对抗恶势力。', '绝地战警3', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '绝地战警');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '黑色幽默');
-- 演员/角色
INSERT INTO actor(name) SELECT '威尔·史密斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='威尔·史密斯');
SET @actor_id = (SELECT id FROM actor WHERE name='威尔·史密斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马丁·劳伦斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马丁·劳伦斯');
SET @actor_id = (SELECT id FROM actor WHERE name='马丁·劳伦斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凡妮莎·哈金斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凡妮莎·哈金斯');
SET @actor_id = (SELECT id FROM actor WHERE name='凡妮莎·哈金斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '亚历山大·路德韦格' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='亚历山大·路德韦格');
SET @actor_id = (SELECT id FROM actor WHERE name='亚历山大·路德韦格');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '查尔斯·梅尔顿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='查尔斯·梅尔顿');
SET @actor_id = (SELECT id FROM actor WHERE name='查尔斯·梅尔顿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '葆拉·努涅斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='葆拉·努涅斯');
SET @actor_id = (SELECT id FROM actor WHERE name='葆拉·努涅斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT 'DJ卡拉德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='DJ卡拉德');
SET @actor_id = (SELECT id FROM actor WHERE name='DJ卡拉德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 Bubba Nosferatu and the Curse of the She-Vampires
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2191789509.jpg', null, 'Bubba Nosferatu and the Curse of the She-Vampires', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Vampire');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'PaulGiamatti');

SET @director_id = NULL;
-- 电影 美丽新世界
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1997454963.jpg', '未来的人类社会，处于高度的平衡状态。生产与消耗已达到固定标准，人们为此感到快乐，愿意放弃自由和人性，享受这种没有竞争也不需努力的舒适生活。', '美丽新世界', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '社会');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '人性');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '莱昂纳多·迪卡普里奥');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '反乌托邦');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '颠覆世界观');
-- 演员/角色
INSERT INTO actor(name) SELECT '杰西卡·布朗·芬德利' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰西卡·布朗·芬德利');
SET @actor_id = (SELECT id FROM actor WHERE name='杰西卡·布朗·芬德利');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯莉·班伯里' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯莉·班伯里');
SET @actor_id = (SELECT id FROM actor WHERE name='凯莉·班伯里');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '阿尔登·埃伦瑞奇' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='阿尔登·埃伦瑞奇');
SET @actor_id = (SELECT id FROM actor WHERE name='阿尔登·埃伦瑞奇');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '汉娜·乔恩-卡门' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汉娜·乔恩-卡门');
SET @actor_id = (SELECT id FROM actor WHERE name='汉娜·乔恩-卡门');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '哈里·劳埃德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='哈里·劳埃德');
SET @actor_id = (SELECT id FROM actor WHERE name='哈里·劳埃德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '三辻茜' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='三辻茜');
SET @actor_id = (SELECT id FROM actor WHERE name='三辻茜');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '约瑟夫·摩根' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='约瑟夫·摩根');
SET @actor_id = (SELECT id FROM actor WHERE name='约瑟夫·摩根');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '尼娜·索珊娅' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='尼娜·索珊娅');
SET @actor_id = (SELECT id FROM actor WHERE name='尼娜·索珊娅');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '黛米·摩尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='黛米·摩尔');
SET @actor_id = (SELECT id FROM actor WHERE name='黛米·摩尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 黑水晶的魔力
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '黑水晶2', 'https://img3.doubanio.com/view/subject/l/public/s4069794.jpg', null, '黑水晶的魔力', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '家庭');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待看');

SET @director_id = NULL;
-- 电影 鬼泣
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2543434659.jpg', 'Netflix《恶魔城》动画导演布将拍摄《鬼泣》动画，不是之前传言的《塞尔达传说》。 他表示《鬼泣》还会加入《恶魔城》动画的多元宇宙中。', '鬼泣', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '鬼泣');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待看');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Netflix');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');

SET @director_id = NULL;
-- 电影 群鸟
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '鸟', 'https://img1.doubanio.com/view/subject/l/public/s3571747.jpg', '希区柯克1963年《群鸟》的重拍版。', '群鸟', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '灾难');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '希区柯克');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'NaomiWatts');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我看过的电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '我看过的英语电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '看电影');

SET @director_id = NULL;
-- 电影 汉江怪物2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('韩国', 100, '韩语', '怪物2', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1785221542.jpg', '漫画家姜草创作剧本的《怪物2》将由谁来执导一直是众人好奇的焦点，续集中“怪物”从汉江“翻腾”到了首尔的清溪川，剧情方面则将延续前作的亲情主题。而同时还有一只来路不明的“怪物”也将粉墨登场，由申正源导演的《陷阱》讲述的一个十年没有发生过犯罪案件的平静山村中惊现食人怪兽的故事，演员阵容已经确定下了严泰雄、张项善、尹宰文以及金有美，为了达到今年12月上映的目标剧组本月底就将远赴美国旧金山市开机。', '汉江怪物2', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '韩国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '韩国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '汉江怪物2');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '怪物');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '韩语标签');

SET @director_id = NULL;
-- 电影 圣母玛利亚
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2185841793.jpg', '据国外媒体消息，Aloe娱乐公司将拍影片《圣母玛利亚》（Mary, Mother of Christ），曾在《史前一万年》（10,000 BC）中扮演女主角、并出演即将上映的科幻片《推》（Push）的卡米拉·贝尔（Camilla Belle）将扮演“圣母”。', '圣母玛利亚', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '传记');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'AlPacino');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '宗教');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '阿尔·帕西诺');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '圣母玛利亚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JonathanRhysMeyers...');
-- 演员/角色
INSERT INTO actor(name) SELECT '朱莉娅·奥蒙德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='朱莉娅·奥蒙德');
SET @actor_id = (SELECT id FROM actor WHERE name='朱莉娅·奥蒙德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 毒魔复仇
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '带毒的复仇者 / 新禁毒卫士', 'https://img3.doubanio.com/view/subject/l/public/s4337110.jpg', '梅肯·布莱尔(《小奸小恶》《无处为家》)将执导并编写重启版《毒魔复仇》电影。', '毒魔复仇', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '翻拍');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '血腥');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '暴力');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '情色');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'cult');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Cult');

SET @director_id = NULL;
-- 电影 冷血悍将
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541802586.jpg', '故事改编自汤姆·克兰西（Tom Clancy）的同名小说。从越战退伍的特种兵约翰，结识了一位女友。然而没过多久，后者就被贩毒集团凌辱致死。约翰决定用他的特种兵技能为女友复仇。与此同时，华盛顿高层正在进行一项秘密计划，准备解救在越南战俘营的一些美军高级军官，五角大楼要求约翰回到越南协助完成这项计划。约翰要如何在完成自己复仇任务的同时，做好自己的本职工作呢？', '冷血悍将', NULL, 0, 2020, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '呵呵');
-- 演员/角色
INSERT INTO actor(name) SELECT '迈克尔·B·乔丹' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·B·乔丹');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·B·乔丹');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 1066
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508784903.jpg', null, 1066, NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '政史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '等待');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '必看');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '一流');
-- 演员/角色
INSERT INTO actor(name) SELECT '凯蒂娅·温特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯蒂娅·温特');
SET @actor_id = (SELECT id FROM actor WHERE name='凯蒂娅·温特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '奥丽维娅·赫西' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥丽维娅·赫西');
SET @actor_id = (SELECT id FROM actor WHERE name='奥丽维娅·赫西');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯特·马伯里' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯特·马伯里');
SET @actor_id = (SELECT id FROM actor WHERE name='凯特·马伯里');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '伊恩·怀特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='伊恩·怀特');
SET @actor_id = (SELECT id FROM actor WHERE name='伊恩·怀特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马丁·科勒巴' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马丁·科勒巴');
SET @actor_id = (SELECT id FROM actor WHERE name='马丁·科勒巴');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '李·阿伦伯格' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='李·阿伦伯格');
SET @actor_id = (SELECT id FROM actor WHERE name='李·阿伦伯格');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '苏珊·乔治' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='苏珊·乔治');
SET @actor_id = (SELECT id FROM actor WHERE name='苏珊·乔治');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '罗南·维博特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='罗南·维博特');
SET @actor_id = (SELECT id FROM actor WHERE name='罗南·维博特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马克·莱斯特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马克·莱斯特');
SET @actor_id = (SELECT id FROM actor WHERE name='马克·莱斯特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '哈泽尔·德让' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='哈泽尔·德让');
SET @actor_id = (SELECT id FROM actor WHERE name='哈泽尔·德让');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马丁·德兰尼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马丁·德兰尼');
SET @actor_id = (SELECT id FROM actor WHERE name='马丁·德兰尼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 危机：龙潭之战
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('澳大利亚', 100, '英语', '非常危险 / 危险逼近', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2554535574.jpg', '崔维斯·费米尔、卢克·布雷西、理查德·劳斯伯格([血战钢锯岭])、丹尼尔·韦伯(《惩罚者》)将主演越南战争题材电影[危险逼近](Danger Close，暂译)，影片由科里夫·斯丹德斯([红犬历险记])执导。故事背景设定在1966年的越南南部，讲述在瓢泼大雨里，澳大利亚与新西兰的士兵与2500名越南士兵僵持。影片将于本月在澳大利亚昆士兰开拍。', '危机：龙潭之战', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '越战');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '澳大利亚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '真实事件改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争片');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
-- 演员/角色
INSERT INTO actor(name) SELECT '崔维斯·费米尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='崔维斯·费米尔');
SET @actor_id = (SELECT id FROM actor WHERE name='崔维斯·费米尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '卢克·布雷西' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='卢克·布雷西');
SET @actor_id = (SELECT id FROM actor WHERE name='卢克·布雷西');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '理查德·劳斯伯格' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='理查德·劳斯伯格');
SET @actor_id = (SELECT id FROM actor WHERE name='理查德·劳斯伯格');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '丹尼尔·韦伯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='丹尼尔·韦伯');
SET @actor_id = (SELECT id FROM actor WHERE name='丹尼尔·韦伯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 美国犯罪的最后日子
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/subject/l/public/s4352918.jpg', '《美国犯罪的最后日子》故事设定在并不遥远的未来，那时的美国政府为了打击恐怖活动和犯罪，计划秘密广播一种信号，让任何人都无法明知故犯的进行非法活动。为了转移公众视线、暗渡陈仓，他们搞了一个新的货币体系“数字付费卡”。', '美国犯罪的最后日子', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫改');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待看');
-- 演员/角色
INSERT INTO actor(name) SELECT '沙尔托·科普雷' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='沙尔托·科普雷');
SET @actor_id = (SELECT id FROM actor WHERE name='沙尔托·科普雷');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '迈克尔·皮特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·皮特');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·皮特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '埃德加·拉米雷兹' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='埃德加·拉米雷兹');
SET @actor_id = (SELECT id FROM actor WHERE name='埃德加·拉米雷兹');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安娜·布雷维斯特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安娜·布雷维斯特');
SET @actor_id = (SELECT id FROM actor WHERE name='安娜·布雷维斯特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 一墙之隔
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('中国大陆', 100, '汉语普通话', '让爱回家 / 格子里的天空', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553582956.jpg', '《让爱回家》的故事发生在女子监狱里一个最差劲的监室，几个女孩自暴自弃，面对着绝望的生活几欲崩溃。但在年轻狱警帮助下，这群女孩终于勇敢地站了起来去面对未来的生活。', '一墙之隔', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影中的女性');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '中国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '女神');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '中国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '叶童');
-- 演员/角色
INSERT INTO actor(name) SELECT '朱茵' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='朱茵');
SET @actor_id = (SELECT id FROM actor WHERE name='朱茵');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '唐嫣' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='唐嫣');
SET @actor_id = (SELECT id FROM actor WHERE name='唐嫣');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '孟广美' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='孟广美');
SET @actor_id = (SELECT id FROM actor WHERE name='孟广美');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杨青' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杨青');
SET @actor_id = (SELECT id FROM actor WHERE name='杨青');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '斯琴高娃' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='斯琴高娃');
SET @actor_id = (SELECT id FROM actor WHERE name='斯琴高娃');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '董立范' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='董立范');
SET @actor_id = (SELECT id FROM actor WHERE name='董立范');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '王姬' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='王姬');
SET @actor_id = (SELECT id FROM actor WHERE name='王姬');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 冥界管理局
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '超自然研究中心', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2500791619.jpg', '故事讲述了两个笨手笨脚的人在死后加入了“冥界管理局”，成为了这个专门对付“闹鬼事件”的精英团队的一员。然而这个工作可不好当，因为他们的终极敌手是我们这个星球上有史以来最强大可怕的鬼。', '冥界管理局', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '梦工厂');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '梦工厂动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画电影');
-- 演员/角色
INSERT INTO actor(name) SELECT '塞斯·罗根' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='塞斯·罗根');
SET @actor_id = (SELECT id FROM actor WHERE name='塞斯·罗根');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马特·波莫' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马特·波莫');
SET @actor_id = (SELECT id FROM actor WHERE name='马特·波莫');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '梅丽莎·麦卡西' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='梅丽莎·麦卡西');
SET @actor_id = (SELECT id FROM actor WHERE name='梅丽莎·麦卡西');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '比尔·默瑞' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='比尔·默瑞');
SET @actor_id = (SELECT id FROM actor WHERE name='比尔·默瑞');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '拉什达·琼斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='拉什达·琼斯');
SET @actor_id = (SELECT id FROM actor WHERE name='拉什达·琼斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '詹妮佛·库里奇' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='詹妮佛·库里奇');
SET @actor_id = (SELECT id FROM actor WHERE name='詹妮佛·库里奇');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '奥克塔维亚·斯宾瑟' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥克塔维亚·斯宾瑟');
SET @actor_id = (SELECT id FROM actor WHERE name='奥克塔维亚·斯宾瑟');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '本杰明·斯托克汉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='本杰明·斯托克汉');
SET @actor_id = (SELECT id FROM actor WHERE name='本杰明·斯托克汉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '戴维斯·克里夫兰' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='戴维斯·克里夫兰');
SET @actor_id = (SELECT id FROM actor WHERE name='戴维斯·克里夫兰');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 亚当斯一家
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 英国 / 加拿大', 100, '英语', '爱登士家庭(港) / 阿达一族', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2553145804.jpg', '米高梅定格动画版《亚当斯一家》组建团队，除此前确定的奥斯卡·伊萨克，查理兹·塞隆、艾莉森·珍妮、科洛·莫瑞兹、菲恩·伍法德、尼克·克罗尔、贝特·迈德尔也加盟。讲述一个诡异古怪的家庭与一个狡猾的真人秀主持人对峙，这个家庭同时也在为新生命的到来做准备，将举办一场亚当斯式的庆祝派对。', '亚当斯一家', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '家庭');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '黑色幽默');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '蒂姆·波顿');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'TimBurton');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
-- 演员/角色
INSERT INTO actor(name) SELECT '查理兹·塞隆' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='查理兹·塞隆');
SET @actor_id = (SELECT id FROM actor WHERE name='查理兹·塞隆');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '科洛·莫瑞兹' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='科洛·莫瑞兹');
SET @actor_id = (SELECT id FROM actor WHERE name='科洛·莫瑞兹');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '奥斯卡·伊萨克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥斯卡·伊萨克');
SET @actor_id = (SELECT id FROM actor WHERE name='奥斯卡·伊萨克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '菲恩·伍法德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='菲恩·伍法德');
SET @actor_id = (SELECT id FROM actor WHERE name='菲恩·伍法德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾莉森·珍妮' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾莉森·珍妮');
SET @actor_id = (SELECT id FROM actor WHERE name='艾莉森·珍妮');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '埃美·加西亚' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='埃美·加西亚');
SET @actor_id = (SELECT id FROM actor WHERE name='埃美·加西亚');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '尼克·克罗尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='尼克·克罗尔');
SET @actor_id = (SELECT id FROM actor WHERE name='尼克·克罗尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾尔西·费舍尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾尔西·费舍尔');
SET @actor_id = (SELECT id FROM actor WHERE name='艾尔西·费舍尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '贝特·米德勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='贝特·米德勒');
SET @actor_id = (SELECT id FROM actor WHERE name='贝特·米德勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '斯科特‧安德伍德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='斯科特‧安德伍德');
SET @actor_id = (SELECT id FROM actor WHERE name='斯科特‧安德伍德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 胡迪尼的秘密生活
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '胡迪尼的秘密人生', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2198987679.jpg', '导演丹·特拉滕伯格（Dan Trachtenberg）最近拾起了狮门公司一个被搁置许久的电影项目《胡迪尼的秘密生活》（The Secret Life of Houdini）。这部影片以魔术大师哈里·胡迪尼（Harry Houdini）为主角，却并非传记片，而是一部刺激惊险的冒险动作片。', '胡迪尼的秘密生活', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JohnnyDepp');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '约翰尼·德普');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '驚悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '传记');

SET @director_id = NULL;
-- 电影 王牌大贱谍4
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 德国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1168213373.jpg', 'The fourth installment in the ysgou.cc Austin Powers series.', '王牌大贱谍4', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '王牌大贱谍');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '"Austin');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恶搞');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Powers"');
-- 演员/角色
INSERT INTO actor(name) SELECT '麦克·梅尔斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='麦克·梅尔斯');
SET @actor_id = (SELECT id FROM actor WHERE name='麦克·梅尔斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 逃离地下天堂
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2365915662.jpg', '西蒙·金伯格([X战警]系列)将执导新版[逃离地下天堂]，[饥饿游戏]系列编剧彼得·克莱格操刀剧本。经典科幻片[逃离地下天堂]于1976年上映，导演迈克尔·安德森，主演麦克尔·约克、珍妮·艾加特。2274年，在一个被控制的城市中，年轻人们发现他们的生命均会在30岁时结束，得知真相的人决定逃跑。新版制片乔·西尔沃([黑客帝国]系列)。', '逃离地下天堂', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'RyanGosling');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Gosling');

SET @director_id = NULL;
-- 电影 怪异的艾米莉
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, null, null, 'https://img3.doubanio.com/view/subject/l/public/s3128966.jpg', '亚马逊影业即将把反传统文化品牌形象“怪异的艾米莉”（Emily the Strange）搬上大银幕，黑马娱乐公司（Dark Horse Entertainment）的总裁迈克·理查森（Mike Richardson）将担任制片。', '怪异的艾米莉', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Emily');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '艾米莉');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'ChloeMoretz');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国动画片');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国动画');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');

SET @director_id = NULL;
-- 电影 乌鸦
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2374274940.jpg', '索尼影业将杰森·莫玛主演的新版[乌鸦]北美定档2019年10月11日。该片由科林·哈迪执导，据悉本片将更加忠实詹姆斯·奥·巴尔的同名漫画。故事讲述一个摇滚乐手和未婚妻被流氓杀害后的一年，摇滚乐手从坟墓中苏醒，并进行复仇。而他所到之处必定有乌鸦跟随。', '乌鸦', NULL, 0, 2019, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '哥特');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '卢克伊万斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '翻拍');
-- 演员/角色
INSERT INTO actor(name) SELECT '杰森·莫玛' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰森·莫玛');
SET @actor_id = (SELECT id FROM actor WHERE name='杰森·莫玛');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 阿提卡
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/subject/l/public/s4395127.jpg', null, '阿提卡', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'M美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'D道格·里曼');

SET @director_id = NULL;
-- 电影 古墓丽影：源起之战
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '盗墓者罗拉(港) / 古墓奇兵(台) / 新古墓丽影', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2512717509.jpg', '劳拉（艾丽西亚·维坎德 Alicia Vikander 饰）的父亲一生都致力于研究古墓，在劳拉尚且年幼的时候，父亲在一场冒险之中失踪了。一晃眼多年过去，劳拉一直拒绝承认父亲已死的消息，也拒绝接手父亲手下的商业帝国。', '古墓丽影：源起之战', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '游戏改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '盗墓');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '女性');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '经典翻拍');
-- 演员/角色
INSERT INTO actor(name) SELECT '艾丽西亚·维坎德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾丽西亚·维坎德');
SET @actor_id = (SELECT id FROM actor WHERE name='艾丽西亚·维坎德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '多米尼克·威斯特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='多米尼克·威斯特');
SET @actor_id = (SELECT id FROM actor WHERE name='多米尼克·威斯特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '沃尔顿·戈金斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='沃尔顿·戈金斯');
SET @actor_id = (SELECT id FROM actor WHERE name='沃尔顿·戈金斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吴彦祖' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吴彦祖');
SET @actor_id = (SELECT id FROM actor WHERE name='吴彦祖');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克里斯汀·斯科特·托马斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里斯汀·斯科特·托马斯');
SET @actor_id = (SELECT id FROM actor WHERE name='克里斯汀·斯科特·托马斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '汉娜·乔恩-卡门' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汉娜·乔恩-卡门');
SET @actor_id = (SELECT id FROM actor WHERE name='汉娜·乔恩-卡门');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '尼克·弗罗斯特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='尼克·弗罗斯特');
SET @actor_id = (SELECT id FROM actor WHERE name='尼克·弗罗斯特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '德里克·雅各比' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='德里克·雅各比');
SET @actor_id = (SELECT id FROM actor WHERE name='德里克·雅各比');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安东尼奥·阿克儿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安东尼奥·阿克儿');
SET @actor_id = (SELECT id FROM actor WHERE name='安东尼奥·阿克儿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '亚历山大·维尧姆' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='亚历山大·维尧姆');
SET @actor_id = (SELECT id FROM actor WHERE name='亚历山大·维尧姆');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰美·温斯顿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰美·温斯顿');
SET @actor_id = (SELECT id FROM actor WHERE name='杰美·温斯顿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '迈克尔·奥拜奥拉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·奥拜奥拉');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·奥拜奥拉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾米丽·凯里' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾米丽·凯里');
SET @actor_id = (SELECT id FROM actor WHERE name='艾米丽·凯里');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '肯尼思·霍' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='肯尼思·霍');
SET @actor_id = (SELECT id FROM actor WHERE name='肯尼思·霍');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 陌生人2
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '只杀陌生人(港/台)', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2507775416.jpg', '阳光明媚的周末，中年男子迈克（马丁·亨德森 Martin Henderson 饰）驱车带着妻子辛蒂（克里斯蒂娜·亨德里克斯 Christina Hendricks 饰）、儿子卢克（刘易斯·普尔曼 Lewis Pullman 饰）和女儿金赛（拜莉·麦迪逊 Bailee Madison 饰）踏上漫漫旅程。他们准备前往亲戚所在的城市，将叛逆的金赛送入一间新的寄宿学校。一路上本就争吵不断，偏偏在夜深人静的时刻他们所到达的露营地又空无一人。卢克受父母之托，肩负起开导妹妹的重任。兄妹俩边散步边聊天，结果意外在某栋房子里发现了叔叔和阿姨被人残忍杀害的尸体。', '陌生人2', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '血腥');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '虐杀');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '杀手');
-- 演员/角色
INSERT INTO actor(name) SELECT '克里斯蒂娜·亨德里克斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里斯蒂娜·亨德里克斯');
SET @actor_id = (SELECT id FROM actor WHERE name='克里斯蒂娜·亨德里克斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '拜莉·麦迪逊' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='拜莉·麦迪逊');
SET @actor_id = (SELECT id FROM actor WHERE name='拜莉·麦迪逊');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马丁·亨德森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马丁·亨德森');
SET @actor_id = (SELECT id FROM actor WHERE name='马丁·亨德森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾玛·贝洛米' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾玛·贝洛米');
SET @actor_id = (SELECT id FROM actor WHERE name='艾玛·贝洛米');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '刘易斯·普尔曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='刘易斯·普尔曼');
SET @actor_id = (SELECT id FROM actor WHERE name='刘易斯·普尔曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莉·恩斯林' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莉·恩斯林');
SET @actor_id = (SELECT id FROM actor WHERE name='莉·恩斯林');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '达米安·马菲' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='达米安·马菲');
SET @actor_id = (SELECT id FROM actor WHERE name='达米安·马菲');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '丽雅·罗伯茨' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='丽雅·罗伯茨');
SET @actor_id = (SELECT id FROM actor WHERE name='丽雅·罗伯茨');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '普雷斯顿·萨德莱' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='普雷斯顿·萨德莱');
SET @actor_id = (SELECT id FROM actor WHERE name='普雷斯顿·萨德莱');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '加布里埃尔·A·拜恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='加布里埃尔·A·拜恩');
SET @actor_id = (SELECT id FROM actor WHERE name='加布里埃尔·A·拜恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '桑尼·迪克西特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='桑尼·迪克西特');
SET @actor_id = (SELECT id FROM actor WHERE name='桑尼·迪克西特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 凯恩与林奇
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p475884421.jpg', '据国外媒体报道，制片人阿德里安-阿斯卡利亚(Adrian Askarieh)和丹尼尔-阿尔特(Daniel Alter)一直在讨论如何将《凯恩和林奇》(Kane and Lynch)这部视频游戏更好地呈现在大银幕上，虽然坊间有很多新闻被挖掘了出来，但最引人注目的莫过于布鲁斯-威利斯(Bruce Willis)签约出演《凯恩和林奇》电影版中的凯恩。', '凯恩与林奇', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '布鲁斯·威利斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '游戏改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'BruceWillis');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '凯恩与林奇');

SET @director_id = NULL;
-- 电影 战争机器
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2541484502.jpg', '微软游戏《战争机器》（Gears of War）改编电影的计划最早可以追溯到2007年，当时新线公司获得了其电影版权，不过后来便没有了下文。如今这件事情又被重新列入议事日程，据悉这一次环球公司将会和《泰迪熊》制片人斯考特·斯塔伯（Scott Stuber）以及《猩球崛起》制片人迪兰·克拉克（Dylan Clark）一起来做这个项目。', '战争机器', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '期待和游戏一样成功');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '西洋');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻奇幻');

SET @director_id = NULL;
-- 电影 根西岛文学与土豆皮馅饼俱乐部
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国 / 美国', 100, '英语', '真爱收信中(台) / 纳粹铁蹄下的密函情书(港) / 根基岛的文学和土豆派协会', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530405011.jpg', '本片根据安妮·巴罗斯、玛丽·安·谢弗同名小说改编。故事构建于二战后，讲述作家朱丽叶·艾什顿(莉莉·詹姆斯饰)与“根西岛文学与土豆皮馅饼俱乐部”之间互相了解并熟知的故事。', '根西岛文学与土豆皮馅饼俱乐部', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '文艺');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '小说改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '二战');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
-- 演员/角色
INSERT INTO actor(name) SELECT '莉莉·詹姆斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莉莉·詹姆斯');
SET @actor_id = (SELECT id FROM actor WHERE name='莉莉·詹姆斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '米契尔·哈思曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='米契尔·哈思曼');
SET @actor_id = (SELECT id FROM actor WHERE name='米契尔·哈思曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '格伦·鲍威尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='格伦·鲍威尔');
SET @actor_id = (SELECT id FROM actor WHERE name='格伦·鲍威尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '马修·古迪' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='马修·古迪');
SET @actor_id = (SELECT id FROM actor WHERE name='马修·古迪');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰西卡·布朗·芬德利' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰西卡·布朗·芬德利');
SET @actor_id = (SELECT id FROM actor WHERE name='杰西卡·布朗·芬德利');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '汤姆·康特奈' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汤姆·康特奈');
SET @actor_id = (SELECT id FROM actor WHERE name='汤姆·康特奈');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '佩内洛普·威尔顿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='佩内洛普·威尔顿');
SET @actor_id = (SELECT id FROM actor WHERE name='佩内洛普·威尔顿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 等待卡帕
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, null, null, 'https://img1.doubanio.com/view/subject/l/public/s4580318.jpg', 'Focuses on the love story and professional relationship between German-born Gerta Pohorylle and Hungarian-born Endre Friedmann from the time they met in Paris in 1935 until her death in 1937. Both exiled, communist, Jewish photographers, they changed their names to Gerta Taro and Robert Capa upon arriving to Spain to report on the Spanish Civil War.', '等待卡帕', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '传记');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '摄影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战地记者');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '卡帕');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'AndrewGarfield');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'EvaGreen');

SET @director_id = NULL;
-- 电影 福尔摩斯与华生
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '福尔摩斯&华生 / 福爾摩濕與滑生(台)', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2531927688.jpg', '老牌喜剧明星威尔·法瑞尔和约翰·C·赖利将再次联手，加盟索尼喜剧新片《福尔摩斯与华生》。法瑞尔和赖利分饰大侦探福尔摩斯与福尔摩斯的助手华生。', '福尔摩斯与华生', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '福尔摩斯');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恶搞');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '闹剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'SONY');
-- 演员/角色
INSERT INTO actor(name) SELECT '威尔·法瑞尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='威尔·法瑞尔');
SET @actor_id = (SELECT id FROM actor WHERE name='威尔·法瑞尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '约翰·C·赖利' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='约翰·C·赖利');
SET @actor_id = (SELECT id FROM actor WHERE name='约翰·C·赖利');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '丽贝卡·豪尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='丽贝卡·豪尔');
SET @actor_id = (SELECT id FROM actor WHERE name='丽贝卡·豪尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '拉尔夫·费因斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='拉尔夫·费因斯');
SET @actor_id = (SELECT id FROM actor WHERE name='拉尔夫·费因斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '休·劳瑞' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='休·劳瑞');
SET @actor_id = (SELECT id FROM actor WHERE name='休·劳瑞');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯莉·麦克唐纳' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯莉·麦克唐纳');
SET @actor_id = (SELECT id FROM actor WHERE name='凯莉·麦克唐纳');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '劳伦·拉普库斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='劳伦·拉普库斯');
SET @actor_id = (SELECT id FROM actor WHERE name='劳伦·拉普库斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '贝拉·拉姆齐' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='贝拉·拉姆齐');
SET @actor_id = (SELECT id FROM actor WHERE name='贝拉·拉姆齐');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '帕姆·费里斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='帕姆·费里斯');
SET @actor_id = (SELECT id FROM actor WHERE name='帕姆·费里斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '罗伯·布莱顿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='罗伯·布莱顿');
SET @actor_id = (SELECT id FROM actor WHERE name='罗伯·布莱顿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克瑞恩·奥伯里恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克瑞恩·奥伯里恩');
SET @actor_id = (SELECT id FROM actor WHERE name='克瑞恩·奥伯里恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '诺亚·尤佩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='诺亚·尤佩');
SET @actor_id = (SELECT id FROM actor WHERE name='诺亚·尤佩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '迈克尔·卡尔金' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·卡尔金');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·卡尔金');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 贼巢
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '极盗战(台) / 贼斗(港)', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2509782231.jpg', '从未被劫的银行，三千万无记认钞票，引爆连环盗计。一群以梅雷亚（巴勃罗·施瑞博尔 饰）为首的高智罪犯，手法高超、火力强横、而且沉迷打劫；纵使犯案累累，却甚少破绽，令警方束手无策。一次押款车劫案后，他们的党羽唐尼（奥谢拉·杰克逊 饰）被辣手神探尼克（杰拉德·巴特勒 饰）识破，更从他身上知道匪帮下一个目标，是攻破从未遇劫的洛杉矶联邦储备银行，从中盗取三千万即将销毁的旧钞！于是尼克布下天罗地网，想趁机把他们一网成擒。然而，魔高一丈；梅雷亚以声东击西之法，引开警方注意。尼克错失擒获他们的良机，唯有尾随追击，双方展开连场枪战。虽然最终截获贼匪，但赃款竟全数不翼而飞！局中有局，究竟谁是取得巨款的幕后赢家？', '贼巢', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '警匪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '枪战');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
-- 演员/角色
INSERT INTO actor(name) SELECT '杰拉德·巴特勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰拉德·巴特勒');
SET @actor_id = (SELECT id FROM actor WHERE name='杰拉德·巴特勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '巴勃罗·施瑞博尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='巴勃罗·施瑞博尔');
SET @actor_id = (SELECT id FROM actor WHERE name='巴勃罗·施瑞博尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '小奥谢拉·杰克逊' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='小奥谢拉·杰克逊');
SET @actor_id = (SELECT id FROM actor WHERE name='小奥谢拉·杰克逊');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '50分' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='50分');
SET @actor_id = (SELECT id FROM actor WHERE name='50分');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '梅多·威廉姆斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='梅多·威廉姆斯');
SET @actor_id = (SELECT id FROM actor WHERE name='梅多·威廉姆斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莫里斯·孔特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莫里斯·孔特');
SET @actor_id = (SELECT id FROM actor WHERE name='莫里斯·孔特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布莱恩·范·霍尔特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布莱恩·范·霍尔特');
SET @actor_id = (SELECT id FROM actor WHERE name='布莱恩·范·霍尔特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '伊万·琼斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='伊万·琼斯');
SET @actor_id = (SELECT id FROM actor WHERE name='伊万·琼斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莫·麦克雷' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莫·麦克雷');
SET @actor_id = (SELECT id FROM actor WHERE name='莫·麦克雷');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯维·莱曼-默塞尤' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯维·莱曼-默塞尤');
SET @actor_id = (SELECT id FROM actor WHERE name='凯维·莱曼-默塞尤');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '道恩·奥利弗瑞' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='道恩·奥利弗瑞');
SET @actor_id = (SELECT id FROM actor WHERE name='道恩·奥利弗瑞');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾瑞克·布里登' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾瑞克·布里登');
SET @actor_id = (SELECT id FROM actor WHERE name='艾瑞克·布里登');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '约旦·布里奇斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='约旦·布里奇斯');
SET @actor_id = (SELECT id FROM actor WHERE name='约旦·布里奇斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '刘易斯·谭' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='刘易斯·谭');
SET @actor_id = (SELECT id FROM actor WHERE name='刘易斯·谭');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '库珀·安德鲁斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='库珀·安德鲁斯');
SET @actor_id = (SELECT id FROM actor WHERE name='库珀·安德鲁斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 温彻斯特
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 澳大利亚', 100, '英语', '温彻斯特鬼屋(台)', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2512558317.jpg', '由美国史上最阴真实案件改编的《温彻斯特鬼屋》，堪称全球十大最厉鬼屋，也是《夺魂锯：游戏重启》导演斯派瑞格兄弟颤栗新作，奥斯卡影后海伦女爵首度挑战恐怖鬼片！海伦米伦独挑大梁主演最新惊悚片《温彻斯特鬼屋》，她饰演身为来福枪发明者威廉温彻斯特的遗孀莎拉温彻斯特，由于丈夫和小孩离奇死亡，让她开始疑神疑鬼，觉得家族深陷诅咒，因此开始不断扩建豪宅，想要将厉鬼困陷在屋内，最后变成史上最著名的鬼屋！ 「世界最恐怖建筑：温彻斯特神秘鬼屋」首度搬上大银幕，让所有影迷引颈企盼这部充满话题的恐怖电影。', '温彻斯特', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '灵异');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '鬼屋');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '澳大利亚');
-- 演员/角色
INSERT INTO actor(name) SELECT '海伦·米伦' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='海伦·米伦');
SET @actor_id = (SELECT id FROM actor WHERE name='海伦·米伦');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莎拉·斯努克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莎拉·斯努克');
SET @actor_id = (SELECT id FROM actor WHERE name='莎拉·斯努克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰森·克拉克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰森·克拉克');
SET @actor_id = (SELECT id FROM actor WHERE name='杰森·克拉克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安格斯·桑普森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安格斯·桑普森');
SET @actor_id = (SELECT id FROM actor WHERE name='安格斯·桑普森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布鲁斯·斯宾斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布鲁斯·斯宾斯');
SET @actor_id = (SELECT id FROM actor WHERE name='布鲁斯·斯宾斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '埃蒙·法伦' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='埃蒙·法伦');
SET @actor_id = (SELECT id FROM actor WHERE name='埃蒙·法伦');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '劳拉·布伦特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='劳拉·布伦特');
SET @actor_id = (SELECT id FROM actor WHERE name='劳拉·布伦特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '泰勒·科宾' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='泰勒·科宾');
SET @actor_id = (SELECT id FROM actor WHERE name='泰勒·科宾');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾姆·怀斯曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾姆·怀斯曼');
SET @actor_id = (SELECT id FROM actor WHERE name='艾姆·怀斯曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '索尔·卡尔森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='索尔·卡尔森');
SET @actor_id = (SELECT id FROM actor WHERE name='索尔·卡尔森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰夫瑞·W·詹金斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰夫瑞·W·詹金斯');
SET @actor_id = (SELECT id FROM actor WHERE name='杰夫瑞·W·詹金斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '达韦恩·乔丹' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='达韦恩·乔丹');
SET @actor_id = (SELECT id FROM actor WHERE name='达韦恩·乔丹');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '泽维尔·勾奥尔特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='泽维尔·勾奥尔特');
SET @actor_id = (SELECT id FROM actor WHERE name='泽维尔·勾奥尔特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '爱丽丝·查斯顿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='爱丽丝·查斯顿');
SET @actor_id = (SELECT id FROM actor WHERE name='爱丽丝·查斯顿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '霍梅洛·洛佩兹' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='霍梅洛·洛佩兹');
SET @actor_id = (SELECT id FROM actor WHERE name='霍梅洛·洛佩兹');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰夫·里帕瑞' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰夫·里帕瑞');
SET @actor_id = (SELECT id FROM actor WHERE name='杰夫·里帕瑞');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 这个男人来自疯狂世界
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('西班牙 / 比利时 / 法国 / 葡萄牙 / 英国', 100, '英语 / 西班牙语', '谁杀死了堂吉诃德 / 谁杀了堂吉诃德 / 杀死堂吉诃德的人 / 杀了唐吉诃德的男人', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2523397170.jpg', '托比，一个愤世嫉俗的广告导演，发现自己陷入了一个古代西班牙鞋匠的荒唐幻想中，他相信自己就是堂吉诃德。在他们越来越超现实的冒险过程中，托比被迫直面他 在青年时代拍摄的一部电影的悲剧性后果——这部电影永远地改变了一个西班牙小村庄的希望和梦想。托比能补偿并找回初心吗?堂吉诃德能在他的疯狂和即将来临的死亡中幸存吗?爱情能战胜一切吗?', '这个男人来自疯狂世界', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '西班牙');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
-- 演员/角色
INSERT INTO actor(name) SELECT '亚当·德赖弗' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='亚当·德赖弗');
SET @actor_id = (SELECT id FROM actor WHERE name='亚当·德赖弗');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '欧嘉·柯瑞兰寇' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='欧嘉·柯瑞兰寇');
SET @actor_id = (SELECT id FROM actor WHERE name='欧嘉·柯瑞兰寇');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '乔纳森·普雷斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='乔纳森·普雷斯');
SET @actor_id = (SELECT id FROM actor WHERE name='乔纳森·普雷斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '斯特兰·斯卡斯加德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='斯特兰·斯卡斯加德');
SET @actor_id = (SELECT id FROM actor WHERE name='斯特兰·斯卡斯加德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '奥斯卡·贾恩那达' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥斯卡·贾恩那达');
SET @actor_id = (SELECT id FROM actor WHERE name='奥斯卡·贾恩那达');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰森·沃特金斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰森·沃特金斯');
SET @actor_id = (SELECT id FROM actor WHERE name='杰森·沃特金斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '塞尔希·洛佩斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='塞尔希·洛佩斯');
SET @actor_id = (SELECT id FROM actor WHERE name='塞尔希·洛佩斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '詹迪·莫拉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='詹迪·莫拉');
SET @actor_id = (SELECT id FROM actor WHERE name='詹迪·莫拉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '特瑞·吉列姆' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='特瑞·吉列姆');
SET @actor_id = (SELECT id FROM actor WHERE name='特瑞·吉列姆');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '霍威克·库区科利安' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='霍威克·库区科利安');
SET @actor_id = (SELECT id FROM actor WHERE name='霍威克·库区科利安');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '阿尔维托·乔·李' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='阿尔维托·乔·李');
SET @actor_id = (SELECT id FROM actor WHERE name='阿尔维托·乔·李');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '威廉·米勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='威廉·米勒');
SET @actor_id = (SELECT id FROM actor WHERE name='威廉·米勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '萝西·德·帕尔马' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='萝西·德·帕尔马');
SET @actor_id = (SELECT id FROM actor WHERE name='萝西·德·帕尔马');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布鲁诺·塞维利亚' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布鲁诺·塞维利亚');
SET @actor_id = (SELECT id FROM actor WHERE name='布鲁诺·塞维利亚');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 2050
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2531306807.jpg', null, 2050, NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'null');

SET @director_id = NULL;
-- 电影 Monster High
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2543418378.jpg', null, 'Monster High', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '歌舞');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'null');

SET @director_id = NULL;
-- 电影 Trust
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2502024002.jpg', '这部电影是死亡事第10部，同时这是绞肉机第四部的完结篇。', 'Trust', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'null');

SET @director_id = NULL;
-- 电影 欢乐时光谋杀案
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '快乐时光凶杀案 / 賤偵MADAM摷公仔(港)', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2522466095.jpg', '这部电影设定在一个人类和布偶共同生存的世界。某天开始，80年代电视剧《欢乐时光帮》的布偶卡司们接连被谋杀，由人类和布偶组成的颇不搭调的侦探二人组开始展开调查。', '欢乐时光谋杀案', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '黑色幽默');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '搞笑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
-- 演员/角色
INSERT INTO actor(name) SELECT '梅丽莎·麦卡西' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='梅丽莎·麦卡西');
SET @actor_id = (SELECT id FROM actor WHERE name='梅丽莎·麦卡西');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '伊丽莎白·班克斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='伊丽莎白·班克斯');
SET @actor_id = (SELECT id FROM actor WHERE name='伊丽莎白·班克斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '乔尔·麦克哈尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='乔尔·麦克哈尔');
SET @actor_id = (SELECT id FROM actor WHERE name='乔尔·麦克哈尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '玛娅·鲁道夫' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='玛娅·鲁道夫');
SET @actor_id = (SELECT id FROM actor WHERE name='玛娅·鲁道夫');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莱斯利·大卫·贝克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莱斯利·大卫·贝克');
SET @actor_id = (SELECT id FROM actor WHERE name='莱斯利·大卫·贝克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布赖恩·汉森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布赖恩·汉森');
SET @actor_id = (SELECT id FROM actor WHERE name='布赖恩·汉森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '欧阳万成' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='欧阳万成');
SET @actor_id = (SELECT id FROM actor WHERE name='欧阳万成');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '比尔·巴雷塔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='比尔·巴雷塔');
SET @actor_id = (SELECT id FROM actor WHERE name='比尔·巴雷塔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吴辛惠' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吴辛惠');
SET @actor_id = (SELECT id FROM actor WHERE name='吴辛惠');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '瑞安·高卢' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='瑞安·高卢');
SET @actor_id = (SELECT id FROM actor WHERE name='瑞安·高卢');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '米奇·席尔帕' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='米奇·席尔帕');
SET @actor_id = (SELECT id FROM actor WHERE name='米奇·席尔帕');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '胡安娜·布斯彻' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='胡安娜·布斯彻');
SET @actor_id = (SELECT id FROM actor WHERE name='胡安娜·布斯彻');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 勒诺·杜兰和哈罗德·莫里斯收藏的重要文物和私人物件，包括书籍、街头时尚和珠宝
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '勒诺·杜兰和哈罗德·莫里斯收藏的重要文物和私人物件,包括书籍、街头时尚和珠宝', 'https://img3.doubanio.com/view/subject/l/public/s4139586.jpg', '这个名称古怪的小说出自艺术家、作家莲妮·夏普顿（Leanne Shapton）之手，这部虚构作品是以一份拍卖目录的形式出现，夏普顿表示她的灵感来自于一些房地产宣传册里的描述方式，因为它们通常提示着上任主人的隐晦生活。', '勒诺·杜兰和哈罗德·莫里斯收藏的重要文物和私人物件，包括书籍、街头时尚和珠宝', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'NataliePortman');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'BradPitt');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '纪录片');

SET @director_id = NULL;
-- 电影 印度之夏
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国 / 美国', 100, '英语 / 北印度语', null, 'https://img1.doubanio.com/view/subject/l/public/s3887749.jpg', '《印度之夏》根据英国女作家阿丽克斯·冯·藤泽尔曼（Alex von Tunzelmann）的同名小说改编，描写的是印度独立、印（度）巴（基斯坦）分治前后数十年中印度和国际上的风云变幻。书中不乏人们熟悉的著名历史人物，如甘地、丘吉尔、尼赫鲁和英国最后一任印度总督蒙巴顿将军等。', '印度之夏', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'CateBlanchett');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '印度');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '历史');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JoeWright');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Cate....');

SET @director_id = NULL;
-- 电影 触碰的双手
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, '英语', '觸得到的愛(台)', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2533827962.jpg', '1944年的德國正值世界大戰，少女蕾娜的母親是德國人、父親是非裔，她因為膚色飽受壓迫；然而，她和希特勒的青年軍魯茲情不自禁地墜入愛河，為此兩人更陷入險地...', '触碰的双手', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '二战');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
-- 演员/角色
INSERT INTO actor(name) SELECT '阿曼德拉·斯坦伯格' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='阿曼德拉·斯坦伯格');
SET @actor_id = (SELECT id FROM actor WHERE name='阿曼德拉·斯坦伯格');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '艾比·考尼什' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾比·考尼什');
SET @actor_id = (SELECT id FROM actor WHERE name='艾比·考尼什');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '乔治·麦凯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='乔治·麦凯');
SET @actor_id = (SELECT id FROM actor WHERE name='乔治·麦凯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克里斯托弗·埃克莱斯顿' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里斯托弗·埃克莱斯顿');
SET @actor_id = (SELECT id FROM actor WHERE name='克里斯托弗·埃克莱斯顿');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '汤姆·古德曼-希尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汤姆·古德曼-希尔');
SET @actor_id = (SELECT id FROM actor WHERE name='汤姆·古德曼-希尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '亚力克·纽曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='亚力克·纽曼');
SET @actor_id = (SELECT id FROM actor WHERE name='亚力克·纽曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '威尔·阿滕伯勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='威尔·阿滕伯勒');
SET @actor_id = (SELECT id FROM actor WHERE name='威尔·阿滕伯勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '丹尼尔·韦曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='丹尼尔·韦曼');
SET @actor_id = (SELECT id FROM actor WHERE name='丹尼尔·韦曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '汤姆·斯威特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汤姆·斯威特');
SET @actor_id = (SELECT id FROM actor WHERE name='汤姆·斯威特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 参孙
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '犹太士师参孙', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2505938238.jpg', 'Follows a Jewish boxer sent to Auschwitz who is forced to fight for the entertainment of the Nazi soldiers. But if he loses, he''ll be experimented upon.', '参孙', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '圣经');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '旧约');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
-- 演员/角色
INSERT INTO actor(name) SELECT '杰克逊·拉斯波恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰克逊·拉斯波恩');
SET @actor_id = (SELECT id FROM actor WHERE name='杰克逊·拉斯波恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '比利·赞恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='比利·赞恩');
SET @actor_id = (SELECT id FROM actor WHERE name='比利·赞恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '鲁特格尔·哈尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='鲁特格尔·哈尔');
SET @actor_id = (SELECT id FROM actor WHERE name='鲁特格尔·哈尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '林赛·瓦格纳' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='林赛·瓦格纳');
SET @actor_id = (SELECT id FROM actor WHERE name='林赛·瓦格纳');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 山谷女孩
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551127901.jpg', null, '山谷女孩', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '歌舞');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '无源');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '一流');
-- 演员/角色
INSERT INTO actor(name) SELECT '杰西卡·罗德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰西卡·罗德');
SET @actor_id = (SELECT id FROM actor WHERE name='杰西卡·罗德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '汪可盈' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='汪可盈');
SET @actor_id = (SELECT id FROM actor WHERE name='汪可盈');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 世界机器人大战
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, null, null, 'https://img1.doubanio.com/view/subject/l/public/s4464719.jpg', 'Based on the graphic novel "World War Robot" from IDW Publishing. The story, penned by Ashley Wood and recounted in war-diary form by participants on both sides, centers on a small band of humans and robots facing off in a battle on Earth, the moon and Mars.', '世界机器人大战', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '战争');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '机器人');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '待看');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '特别想看的');

SET @director_id = NULL;
-- 电影 乡间路
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '无路可退 / 乡野僻径', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2541902629.jpg', '"Back Roads" centers on a young man stuck in the Pennsylvania backwoods caring for his three younger sisters after the shooting death of his abusive father and the arrest of his mother. Family secrets and unspoken truths threaten to consume him.', '乡间路', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '人性');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'JenniferGarner');
-- 演员/角色
INSERT INTO actor(name) SELECT '朱丽叶特·刘易斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='朱丽叶特·刘易斯');
SET @actor_id = (SELECT id FROM actor WHERE name='朱丽叶特·刘易斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '詹妮弗·莫里森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='詹妮弗·莫里森');
SET @actor_id = (SELECT id FROM actor WHERE name='詹妮弗·莫里森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '妮可拉·佩尔茨' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='妮可拉·佩尔茨');
SET @actor_id = (SELECT id FROM actor WHERE name='妮可拉·佩尔茨');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 影
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('中国大陆 / 中国香港', 100, '汉语普通话', '三国·荆州 / 荆州保卫战', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530513100.jpg', '战乱年代，群雄并起。割据一方的沛国安于现状，一任国主沛良（郑恺 饰）慨歌太平。要冲之地境州早年为强邻炎国借去，而今据而不还。沛国都督子虞（邓超 饰）前往讨伐，却中了对方大将杨苍（胡军 饰）的拖刀，重伤不愈。心有不甘的子虞暗中派出替身境州（邓超 饰）假扮自己，总理军政，内则使令夫人小艾（孙俪 饰）襄助。真假子虞切磋战法，寻求破解杨苍刀法的绝技。而另一方面，沛良恼怒子虞私自约战杨苍，不惜将妹妹青萍（关晓彤 饰）嫁于杨苍之子为妾媾和。淫雨霏霏，连日不开。境州上空，杀戮与机谋纠缠撕裂……', '影', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '武侠');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '古装');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '水墨');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '视觉艺术');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '黑白');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '中国大陆');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '人性');
-- 演员/角色
INSERT INTO actor(name) SELECT '邓超' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='邓超');
SET @actor_id = (SELECT id FROM actor WHERE name='邓超');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '孙俪' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='孙俪');
SET @actor_id = (SELECT id FROM actor WHERE name='孙俪');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '郑恺' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='郑恺');
SET @actor_id = (SELECT id FROM actor WHERE name='郑恺');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '王千源' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='王千源');
SET @actor_id = (SELECT id FROM actor WHERE name='王千源');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '王景春' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='王景春');
SET @actor_id = (SELECT id FROM actor WHERE name='王景春');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '胡军' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='胡军');
SET @actor_id = (SELECT id FROM actor WHERE name='胡军');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '关晓彤' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='关晓彤');
SET @actor_id = (SELECT id FROM actor WHERE name='关晓彤');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吴磊' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吴磊');
SET @actor_id = (SELECT id FROM actor WHERE name='吴磊');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '封柏' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='封柏');
SET @actor_id = (SELECT id FROM actor WHERE name='封柏');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 时间的皱折
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '第五次元(港) / 时间的皱纹 / 奇幻时空历险 / 梅格时空大冒险', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2493230087.jpg', '影片根据同名小说《A Wrinkle in Time》改编，讲述在科学家父亲(克里斯·派恩饰)意外失踪以后，小女主Meg在 Mrs. Who (敏迪·卡琳饰),  Mrs. Whatsit (瑞希饰), Mrs. Which (奥普拉饰)这三位奇异的仙灵生物的帮助下，跨越时间、空间、维度，在黑暗中寻求光明，开启了一段奇幻寻父之旅的故事。', '时间的皱折', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '迪士尼');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '儿童');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Disney');
-- 演员/角色
INSERT INTO actor(name) SELECT '斯托姆·瑞德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='斯托姆·瑞德');
SET @actor_id = (SELECT id FROM actor WHERE name='斯托姆·瑞德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '奥普拉·温弗瑞' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥普拉·温弗瑞');
SET @actor_id = (SELECT id FROM actor WHERE name='奥普拉·温弗瑞');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '瑞茜·威瑟斯彭' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='瑞茜·威瑟斯彭');
SET @actor_id = (SELECT id FROM actor WHERE name='瑞茜·威瑟斯彭');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '敏迪·卡灵' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='敏迪·卡灵');
SET @actor_id = (SELECT id FROM actor WHERE name='敏迪·卡灵');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '莱维·米勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='莱维·米勒');
SET @actor_id = (SELECT id FROM actor WHERE name='莱维·米勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '德里克·麦凯布' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='德里克·麦凯布');
SET @actor_id = (SELECT id FROM actor WHERE name='德里克·麦凯布');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克里斯·派恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里斯·派恩');
SET @actor_id = (SELECT id FROM actor WHERE name='克里斯·派恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '古古·姆巴塔-劳' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='古古·姆巴塔-劳');
SET @actor_id = (SELECT id FROM actor WHERE name='古古·姆巴塔-劳');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '扎克·加利凡纳基斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='扎克·加利凡纳基斯');
SET @actor_id = (SELECT id FROM actor WHERE name='扎克·加利凡纳基斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '迈克尔·佩纳' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·佩纳');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·佩纳');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安德烈·霍兰' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安德烈·霍兰');
SET @actor_id = (SELECT id FROM actor WHERE name='安德烈·霍兰');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '罗温·布兰查德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='罗温·布兰查德');
SET @actor_id = (SELECT id FROM actor WHERE name='罗温·布兰查德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '贝拉米·扬' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='贝拉米·扬');
SET @actor_id = (SELECT id FROM actor WHERE name='贝拉米·扬');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '大卫·奥伊罗' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='大卫·奥伊罗');
SET @actor_id = (SELECT id FROM actor WHERE name='大卫·奥伊罗');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '康拉德·罗伯茨' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='康拉德·罗伯茨');
SET @actor_id = (SELECT id FROM actor WHERE name='康拉德·罗伯茨');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 境·界
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('日本', 100, '日语', '境界 / 死神真人版', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2551872873.jpg', '从小异于常人的黑崎一护（福士苍汰 饰），在一次机缘巧合下遭遇女死神“朽木露琪亚”（杉咲花 饰）。 露琪亚是将灵魂从现实世界引领到尸魂界的收魂者之一，他们为保护人类 不被“虚” 侵害，一直在与这些怪物战斗。在与一只异常强大的“虚” 战斗时，露琪亚身负重伤，无奈只能将死神 的力量暂时转给黑崎一护，让其代为战斗。两人经过一段时间的磨合后并肩战斗，终于战胜了当年害死一护母亲的“虚”，但在这期间，露琪亚的兄长朽木白哉（石原贵雅 饰）强迫她不顾黑崎一护的生命安危收回法力。露琪亚为保护一护，拖延到与“虚”的战斗结束，并清除了一护的记忆……', '境·界', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '日本');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '漫画改编');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '真人版');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '热血');
-- 演员/角色
INSERT INTO actor(name) SELECT '福士苍汰' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='福士苍汰');
SET @actor_id = (SELECT id FROM actor WHERE name='福士苍汰');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杉咲花' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杉咲花');
SET @actor_id = (SELECT id FROM actor WHERE name='杉咲花');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吉泽亮' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吉泽亮');
SET @actor_id = (SELECT id FROM actor WHERE name='吉泽亮');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '真野惠里菜' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='真野惠里菜');
SET @actor_id = (SELECT id FROM actor WHERE name='真野惠里菜');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '小柳友' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='小柳友');
SET @actor_id = (SELECT id FROM actor WHERE name='小柳友');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '田边诚一' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='田边诚一');
SET @actor_id = (SELECT id FROM actor WHERE name='田边诚一');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '早乙女太一' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='早乙女太一');
SET @actor_id = (SELECT id FROM actor WHERE name='早乙女太一');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '石原贵雅' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='石原贵雅');
SET @actor_id = (SELECT id FROM actor WHERE name='石原贵雅');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '长泽雅美' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='长泽雅美');
SET @actor_id = (SELECT id FROM actor WHERE name='长泽雅美');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '江口洋介' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='江口洋介');
SET @actor_id = (SELECT id FROM actor WHERE name='江口洋介');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 黑暗的爪牙
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国 / 法国', 100, '英语', '麦克白 / 麦克白后传 / 战士之敌', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2142969164.jpg', '苏格兰国王邓肯（查里斯·丹斯 Charles Dance 饰）的表弟麦克白（肖恩·宾 Sean Bean 饰）将军，为国王平叛和抵御入侵立功归来，路上遇到三个女巫。女巫对他说了一些预言和隐语，说他将进爵为王，但他并无子嗣能继承王位，反而是同僚班柯将军（詹姆斯·达西 Ja mes D''Arcy 饰）的后代要做王。麦克白是有野心的英雄，他在夫人的怂恿下谋杀邓肯，做了国王。为掩人耳目和防止他人夺位，他一步步害死了邓肯的侍卫，害死了班柯，害死了贵族麦克德夫（杰森·弗莱明 Jason Flemyng   饰）的妻子和小孩。恐惧和猜疑使麦克白心里越来越有鬼，也越来越冷酷。麦克白夫人神经失常而自杀，对他也是一大刺激。在众叛亲离的情况下，麦克白面对邓肯之子和他请来的英格兰援军的围攻，落得削首的下场。', '黑暗的爪牙', NULL, 0, 2018, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '莎士比亚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'RupertGrint');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '莎剧麦克白');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '鲁伯特格林特');
-- 演员/角色
INSERT INTO actor(name) SELECT '肖恩·宾' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='肖恩·宾');
SET @actor_id = (SELECT id FROM actor WHERE name='肖恩·宾');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '鲁伯特·格林特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='鲁伯特·格林特');
SET @actor_id = (SELECT id FROM actor WHERE name='鲁伯特·格林特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '查尔斯·丹斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='查尔斯·丹斯');
SET @actor_id = (SELECT id FROM actor WHERE name='查尔斯·丹斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '詹姆斯·达西' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='詹姆斯·达西');
SET @actor_id = (SELECT id FROM actor WHERE name='詹姆斯·达西');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 枪杀
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('英国', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2358119998.jpg', '职业抢劫犯杰克为了挖掘父亲被谋杀的真相，找到他被偷的钱，面临生命危险。很快他发现自己陷入了无可挽回的困境。西班牙的美丽风光以及伦敦黑暗的地下世界里，他和神秘的敌对力量展开搏斗，在寻求复仇的同时，背后还有一群黑帮罪犯和警察在追捕他。', '枪杀', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '动作');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '英国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '犯罪');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '貌似一般');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情,英国,动作,冒险,2017,伦敦,爱丁堡,...');
-- 演员/角色
INSERT INTO actor(name) SELECT '克雷格·法布拉斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克雷格·法布拉斯');
SET @actor_id = (SELECT id FROM actor WHERE name='克雷格·法布拉斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '詹姆斯·卡沙莫' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='詹姆斯·卡沙莫');
SET @actor_id = (SELECT id FROM actor WHERE name='詹姆斯·卡沙莫');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT 'M·费尔达' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='M·费尔达');
SET @actor_id = (SELECT id FROM actor WHERE name='M·费尔达');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '尼克·莫兰' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='尼克·莫兰');
SET @actor_id = (SELECT id FROM actor WHERE name='尼克·莫兰');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '史蒂文·伯克夫' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='史蒂文·伯克夫');
SET @actor_id = (SELECT id FROM actor WHERE name='史蒂文·伯克夫');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '娜塔莉·考克斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='娜塔莉·考克斯');
SET @actor_id = (SELECT id FROM actor WHERE name='娜塔莉·考克斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 祈愿树
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('印度', 100, '北印度语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2445873761.jpg', '''The Wishing Tree'' is a magical, inspirational and an extremely entertaining story of five children in a hill-station somewhere in India, who come together to save their ''wishing tree'' from being cut by vested interests. The film is extremely lively, endearing and engrossing so that the underlying message to protect trees and environment is seamlessly driven home.', '祈愿树', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '印度');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'IV');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '2010s');

SET @director_id = NULL;
-- 电影 绝命时钟2:22
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国 / 澳大利亚', 100, '英语', null, 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2511529908.jpg', '有严格作息规律的男主角迪伦（米契尔·哈思曼 Michiel Huisman 饰）有一种天生的洞察规律的能力，某日下午2时22分，身为航空管制员迪伦·布兰森被神秘光亮所麻痹，而这差点让他指挥的飞机失事。从此他每天都会感受到这光亮，渐渐发现自己身处某种循环之中。一次非常偶然的机会，迪伦遇见了让他一见钟情的莎拉（泰莉莎·帕尔墨 Teresa Palmer 饰），二人聊天惊讶发现，莎拉就是迪伦差点酿成飞行事故的航班乘客，非常巧合的是他们同年同月同日出生。随着二人的了解深入，迪伦与莎拉的巧合也越来越多，频繁出现的幻觉与不断循环的事件将二人的命运与三十年前发生在纽约中央车站一起情杀案联系在一起，迪伦一直探究“2:22”背后的秘密，发现三十年前的凶杀案即将重演。', '绝命时钟2:22', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
-- 演员/角色
INSERT INTO actor(name) SELECT '泰莉莎·帕尔墨' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='泰莉莎·帕尔墨');
SET @actor_id = (SELECT id FROM actor WHERE name='泰莉莎·帕尔墨');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '米契尔·哈思曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='米契尔·哈思曼');
SET @actor_id = (SELECT id FROM actor WHERE name='米契尔·哈思曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '萨姆·里德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='萨姆·里德');
SET @actor_id = (SELECT id FROM actor WHERE name='萨姆·里德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '梅芙·德莫迪' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='梅芙·德莫迪');
SET @actor_id = (SELECT id FROM actor WHERE name='梅芙·德莫迪');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '约翰·沃特斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='约翰·沃特斯');
SET @actor_id = (SELECT id FROM actor WHERE name='约翰·沃特斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '瑞米·许' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='瑞米·许');
SET @actor_id = (SELECT id FROM actor WHERE name='瑞米·许');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '西蒙娜·凯塞尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='西蒙娜·凯塞尔');
SET @actor_id = (SELECT id FROM actor WHERE name='西蒙娜·凯塞尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯瑞·阿姆斯特朗' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯瑞·阿姆斯特朗');
SET @actor_id = (SELECT id FROM actor WHERE name='凯瑞·阿姆斯特朗');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯蒂·麦康奈尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯蒂·麦康奈尔');
SET @actor_id = (SELECT id FROM actor WHERE name='凯蒂·麦康奈尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰西卡·克拉克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰西卡·克拉克');
SET @actor_id = (SELECT id FROM actor WHERE name='杰西卡·克拉克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '玛丽莎·拉蒙尼卡' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='玛丽莎·拉蒙尼卡');
SET @actor_id = (SELECT id FROM actor WHERE name='玛丽莎·拉蒙尼卡');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '理查德·戴维斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='理查德·戴维斯');
SET @actor_id = (SELECT id FROM actor WHERE name='理查德·戴维斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '扎拉·迈克尔斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='扎拉·迈克尔斯');
SET @actor_id = (SELECT id FROM actor WHERE name='扎拉·迈克尔斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安吉·特里克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安吉·特里克');
SET @actor_id = (SELECT id FROM actor WHERE name='安吉·特里克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '卡玛·莎伦' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='卡玛·莎伦');
SET @actor_id = (SELECT id FROM actor WHERE name='卡玛·莎伦');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '米切尔·巴特尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='米切尔·巴特尔');
SET @actor_id = (SELECT id FROM actor WHERE name='米切尔·巴特尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 番茄小水珠
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('日本', 100, '日语', '西红柿的小水滴 / 西红柿上的露珠', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2387519747.jpg', '5年の交際を経て入籍したさくらと真は、2人で美容院を経営しながら、幸せな新婚生活を送っていた。しかし、さくらは中学生のときに母親を亡くして以来、父親の辰夫と疎遠な関係が続き、真は義父に一度も会ったことがなかった。ある日、辰夫が美容院を訪ねてくるが、さくらと気まずい雰囲気になってしまい、トマトのぎっしり詰まった紙袋を真に託しその場を去っていく……。主演に小西真奈美、共演に吉沢悠、石橋蓮司ら。監督は「ぼくのおばあちゃん」「誘拐ラプソディー」の榊英雄。', '番茄小水珠', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '日本电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '日本');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '日影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '温情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '吉沢悠');
-- 演员/角色
INSERT INTO actor(name) SELECT '小西真奈美' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='小西真奈美');
SET @actor_id = (SELECT id FROM actor WHERE name='小西真奈美');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吉泽悠' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吉泽悠');
SET @actor_id = (SELECT id FROM actor WHERE name='吉泽悠');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '石桥莲司' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='石桥莲司');
SET @actor_id = (SELECT id FROM actor WHERE name='石桥莲司');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '中村优子' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='中村优子');
SET @actor_id = (SELECT id FROM actor WHERE name='中村优子');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 消失的西德尼·豪尔
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '西德尼·豪尔', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2510276489.jpg', '豪尔(罗根·勒曼饰)因一部小说而瞬间成名，然而其作品也发展了一出悲剧，他的事业似乎蒙上了阴影，而他过去的黑暗秘密也变得越来越复杂。', '消失的西德尼·豪尔', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '人生');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '文艺');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '爱情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '成长');
-- 演员/角色
INSERT INTO actor(name) SELECT '艾丽·范宁' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='艾丽·范宁');
SET @actor_id = (SELECT id FROM actor WHERE name='艾丽·范宁');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '米歇尔·莫纳汉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='米歇尔·莫纳汉');
SET @actor_id = (SELECT id FROM actor WHERE name='米歇尔·莫纳汉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '罗根·勒曼' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='罗根·勒曼');
SET @actor_id = (SELECT id FROM actor WHERE name='罗根·勒曼');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '玛格丽特·库里' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='玛格丽特·库里');
SET @actor_id = (SELECT id FROM actor WHERE name='玛格丽特·库里');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '凯尔·钱德勒' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='凯尔·钱德勒');
SET @actor_id = (SELECT id FROM actor WHERE name='凯尔·钱德勒');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '詹妮娜·加万卡' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='詹妮娜·加万卡');
SET @actor_id = (SELECT id FROM actor WHERE name='詹妮娜·加万卡');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布莱克·詹纳' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布莱克·詹纳');
SET @actor_id = (SELECT id FROM actor WHERE name='布莱克·詹纳');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '叶海亚·阿卜杜勒-迈丁' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='叶海亚·阿卜杜勒-迈丁');
SET @actor_id = (SELECT id FROM actor WHERE name='叶海亚·阿卜杜勒-迈丁');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '内森·连恩' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='内森·连恩');
SET @actor_id = (SELECT id FROM actor WHERE name='内森·连恩');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '蒂姆·布雷克·尼尔森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='蒂姆·布雷克·尼尔森');
SET @actor_id = (SELECT id FROM actor WHERE name='蒂姆·布雷克·尼尔森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '迈克尔·德雷亚' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·德雷亚');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·德雷亚');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '阿丽亚娜·莱因哈特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='阿丽亚娜·莱因哈特');
SET @actor_id = (SELECT id FROM actor WHERE name='阿丽亚娜·莱因哈特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克里斯蒂娜·布鲁卡托' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里斯蒂娜·布鲁卡托');
SET @actor_id = (SELECT id FROM actor WHERE name='克里斯蒂娜·布鲁卡托');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '达伦·佩蒂' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='达伦·佩蒂');
SET @actor_id = (SELECT id FROM actor WHERE name='达伦·佩蒂');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 惊心食人族3
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', null, 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2500289262.jpg', '翠施（第一部女主）现在已经是一个十几岁大孩子的妈妈，他孩子叫戴瑞，以她23年前失去的弟弟为名。翠施最近经常发恶梦，看到她儿子重复遭遇到她弟弟被那只怪物杀害的命运。为了确保恶梦不再重现。翠施利用她现在拥有的金钱和能力决定与老，少杰克（第二部的老爷子和他儿子）完成最终的任务，彻底消除蝙蝠人的恐怖阴影。', '惊心食人族3', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊心食人族3');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '血腥');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊心食人族');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
-- 演员/角色
INSERT INTO actor(name) SELECT '梅格·福斯特' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='梅格·福斯特');
SET @actor_id = (SELECT id FROM actor WHERE name='梅格·福斯特');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '吉娜·菲利普斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='吉娜·菲利普斯');
SET @actor_id = (SELECT id FROM actor WHERE name='吉娜·菲利普斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '乔纳森·布瑞克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='乔纳森·布瑞克');
SET @actor_id = (SELECT id FROM actor WHERE name='乔纳森·布瑞克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '卡丽·拉扎' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='卡丽·拉扎');
SET @actor_id = (SELECT id FROM actor WHERE name='卡丽·拉扎');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '斯坦·肖' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='斯坦·肖');
SET @actor_id = (SELECT id FROM actor WHERE name='斯坦·肖');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '乔斯·吉劳德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='乔斯·吉劳德');
SET @actor_id = (SELECT id FROM actor WHERE name='乔斯·吉劳德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '迈克尔·帕帕约翰' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='迈克尔·帕帕约翰');
SET @actor_id = (SELECT id FROM actor WHERE name='迈克尔·帕帕约翰');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '布兰顿·史密斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='布兰顿·史密斯');
SET @actor_id = (SELECT id FROM actor WHERE name='布兰顿·史密斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '唐·耶索' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='唐·耶索');
SET @actor_id = (SELECT id FROM actor WHERE name='唐·耶索');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 毁灭僵尸
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, null, null, 'https://img3.doubanio.com/view/subject/l/public/s4115372.jpg', null, '毁灭僵尸', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '僵尸');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '喜剧');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'Cult');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');

SET @director_id = NULL;
-- 电影 视网膜
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '弑网膜(台)', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2522162623.jpg', 'A young woman participates in a medical study. After a series of nightmares and unusual side effects, the line between dreams and reality is blurred. She finds herself on the run from those involved, desperate to uncover the truth.', '视网膜', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '悬疑');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '电影');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '恐怖');

SET @director_id = NULL;
-- 电影 冰肤传说
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('西班牙 / 法国', 100, '英语', '冷皮 / 冰海异种(台)', 'https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2494691269.jpg', '1914年第一次世界大战前夕，一名气象学家被派往南极孤岛上做气候观测。然而他很快发现，岛上除了他和一个疯疯癫癫的灯塔员，还有其它生物的存在... 影片改编自同名小说。', '冰肤传说', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '科幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '惊悚');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '冒险');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '奇幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '西班牙');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '魔幻');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '法国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '海行人');
-- 演员/角色
INSERT INTO actor(name) SELECT '雷·史蒂文森' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='雷·史蒂文森');
SET @actor_id = (SELECT id FROM actor WHERE name='雷·史蒂文森');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '大卫·奥克斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='大卫·奥克斯');
SET @actor_id = (SELECT id FROM actor WHERE name='大卫·奥克斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '奥拉·加里多' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='奥拉·加里多');
SET @actor_id = (SELECT id FROM actor WHERE name='奥拉·加里多');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '温斯洛·M·岩城' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='温斯洛·M·岩城');
SET @actor_id = (SELECT id FROM actor WHERE name='温斯洛·M·岩城');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '约翰·本菲尔德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='约翰·本菲尔德');
SET @actor_id = (SELECT id FROM actor WHERE name='约翰·本菲尔德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '本·坦普尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='本·坦普尔');
SET @actor_id = (SELECT id FROM actor WHERE name='本·坦普尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '伊凡·冈萨雷斯' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='伊凡·冈萨雷斯');
SET @actor_id = (SELECT id FROM actor WHERE name='伊凡·冈萨雷斯');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

SET @director_id = NULL;
-- 电影 万视瞩目
INSERT INTO film(country, duration, language, original_title, poster, summary, title, trailer, views, year, director_id) VALUES ('美国', 100, '英语', '2Pac传记', 'https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2456162121.jpg', '已故说唱传奇2Pac的传记片"All Eyez On Me"发新预告，Demetrius Shipp Jr.演的2Pac除了脸颊略丰满外，跟原型相当相似，Jamal Woolard再饰Notorious B.I.G.，另有多名同时代知名人士登场。聚焦2Pac的说唱生涯和成长历程，他作为黑人的抗争。《行尸走肉》“刀女”Danai Gurira饰其母，也有“Maggie”Lauren Cohan，凯特·格兰厄姆(《吸血鬼日记》)、希尔·哈珀(《犯罪现场调查：纽约》)也参演，6月16日北美上映。', '万视瞩目', NULL, 0, 2017, @director_id);
SET @film_id = LAST_INSERT_ID();
-- 类型标签
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '剧情');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '音乐');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '传记');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '2pac');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, 'HipHop');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '说唱');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '美国');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '黑人');
INSERT INTO film_tags(film_id, tag) VALUES (@film_id, '纪录片');
-- 演员/角色
INSERT INTO actor(name) SELECT '德米特里厄斯·西普' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='德米特里厄斯·西普');
SET @actor_id = (SELECT id FROM actor WHERE name='德米特里厄斯·西普');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '丹娜·奎里拉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='丹娜·奎里拉');
SET @actor_id = (SELECT id FROM actor WHERE name='丹娜·奎里拉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '卡特琳娜·格兰厄姆' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='卡特琳娜·格兰厄姆');
SET @actor_id = (SELECT id FROM actor WHERE name='卡特琳娜·格兰厄姆');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '希尔·哈勃' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='希尔·哈勃');
SET @actor_id = (SELECT id FROM actor WHERE name='希尔·哈勃');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '安妮·伊隆泽' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='安妮·伊隆泽');
SET @actor_id = (SELECT id FROM actor WHERE name='安妮·伊隆泽');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '劳伦·科汉' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='劳伦·科汉');
SET @actor_id = (SELECT id FROM actor WHERE name='劳伦·科汉');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '基思·罗宾逊' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='基思·罗宾逊');
SET @actor_id = (SELECT id FROM actor WHERE name='基思·罗宾逊');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '贾马尔·伍拉德' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='贾马尔·伍拉德');
SET @actor_id = (SELECT id FROM actor WHERE name='贾马尔·伍拉德');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '多米尼克·L·桑塔纳' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='多米尼克·L·桑塔纳');
SET @actor_id = (SELECT id FROM actor WHERE name='多米尼克·L·桑塔纳');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '科里·哈德里克' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='科里·哈德里克');
SET @actor_id = (SELECT id FROM actor WHERE name='科里·哈德里克');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '克里夫顿·鲍威尔' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='克里夫顿·鲍威尔');
SET @actor_id = (SELECT id FROM actor WHERE name='克里夫顿·鲍威尔');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);
INSERT INTO actor(name) SELECT '杰米·埃克托' FROM DUAL WHERE NOT EXISTS (SELECT 1 FROM actor WHERE name='杰米·埃克托');
SET @actor_id = (SELECT id FROM actor WHERE name='杰米·埃克托');
INSERT INTO film_actor(description, role, actor_id, film_id) VALUES (NULL, '主演', @actor_id, @film_id);

COMMIT;