package com.athudong.video.util;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.bean.User;

/**
 * 20个单机测试人物
 */
public class TestDataUtil {

	private static List<User> users = new ArrayList<User>();

	public static void init() {

		if (users.size() == 0) {
			u01();
			u02();
			u03();
			u04();
			u05();
			u06();
			u07();
			u08();
			u09();
			u10();
			u11();
		}

	}
	
	
	private static void u11(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("11");
		// 名称
		user.setName("糖蜜果儿");
		// 城市
		user.setCity("哈尔滨");
		// 粉丝
		user.setFans(4698);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("小沈阳");
		// 海选时的自我介绍
		user.setDescription("高调生活，勤奋工作，低调做人…");
		// 人气值
		user.setPopular(4954);
		// 明星梦
		user.setSaying("高调生活，勤奋工作，低调做人…");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(8866);
		// 关注了多少个人
		user.setFocusCount(12);
		// 生日
		user.setBirthday("19900927");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u10(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("10");
		// 名称
		user.setName("Bank");
		// 城市
		user.setCity("福建");
		// 粉丝
		user.setFans(6755);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("谢霆锋，李玖哲");
		// 海选时的自我介绍
		user.setDescription("今天天气不错");
		// 人气值
		user.setPopular(4954);
		// 明星梦
		user.setSaying("今天天气不错");
		// 性别
		user.setSex("男");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(2354);
		// 关注了多少个人
		user.setFocusCount(12);
		// 生日
		user.setBirthday("19870927");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u08(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("08");
		// 名称
		user.setName("太单纯");
		// 城市
		user.setCity("深圳");
		// 粉丝
		user.setFans(1580);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("旅游，看电影，上网");
		// 海选时的自我介绍
		user.setDescription("被陌生人伤，我无动于衷。");
		// 人气值
		user.setPopular(7535);
		// 明星梦
		user.setSaying("被陌生人伤，我无动于衷。");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(4432);
		// 关注了多少个人
		user.setFocusCount(12);
		// 生日
		user.setBirthday("19890525");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	
	private static void u07(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("07");
		// 名称
		user.setName("刘诗涵");
		// 城市
		user.setCity("长沙");
		// 粉丝
		user.setFans(4567);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("旅游，看电影，上网");
		// 海选时的自我介绍
		user.setDescription("游戏第一美女，瑞丽之星");
		// 人气值
		user.setPopular(7535);
		// 明星梦
		user.setSaying("游戏第一美女，瑞丽之星");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(5321);
		// 关注了多少个人
		user.setFocusCount(12);
		// 生日
		user.setBirthday("19890818");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	
	private static void u09(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("09");
		// 名称
		user.setName("徐浩鑫");
		// 城市
		user.setCity("阜新");
		// 粉丝
		user.setFans(9880);

		// 图片的数量
		user.setImgCount(4);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("光良，刘德华等");
		// 海选时的自我介绍
		user.setDescription("人的存在感不在于外在的华丽，虚荣并不能长存，自身智慧的充实才是长久之计。");
		// 人气值
		user.setPopular(8545);
		// 明星梦
		user.setSaying("人的存在感不在于外在的华丽，虚荣并不能长存，自身智慧的充实才是长久之计");
		// 性别
		user.setSex("男");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(3229);
		// 关注了多少个人
		user.setFocusCount(12);
		// 生日
		user.setBirthday("19941122");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u06(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("06");
		// 名称
		user.setName("夏安娜");
		// 城市
		user.setCity("成都");
		// 粉丝
		user.setFans(223);

		// 图片的数量
		user.setImgCount(1);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("那英、韩红");
		// 海选时的自我介绍
		user.setDescription("中国传媒大学新闻主持节目才艺奖，四川天府之都选美大赛冠军");
		// 人气值
		user.setPopular(135);
		// 明星梦
		user.setSaying("中国传媒大学新闻主持节目才艺奖，四川天府之都选美大赛冠军");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(1325);
		// 关注了多少个人
		user.setFocusCount(12);
		// 生日
		user.setBirthday("19890209");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u05(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("05");
		// 名称
		user.setName("威仔");
		// 城市
		user.setCity("咸宁");
		// 粉丝
		user.setFans(623);

		// 图片的数量
		user.setImgCount(3);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("筷子兄弟，羽泉");
		// 海选时的自我介绍
		user.setDescription("帅气的外表形象，声音带有磁性，给人很温柔的感觉，对粉丝和睦。");
		// 人气值
		user.setPopular(235);
		// 明星梦
		user.setSaying("帅气的外表形象，声音带有磁性，给人很温柔的感觉，对粉丝和睦。");
		// 性别
		user.setSex("男");
		// 星级(1~9级)
		user.setStarLevel(1);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(4345);
		// 关注了多少个人
		user.setFocusCount(836);
		// 生日
		user.setBirthday("19970209");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u04(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("04");
		// 名称
		user.setName("陈思妤");
		// 城市
		user.setCity("台湾");
		// 粉丝
		user.setFans(8745);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("周杰伦，欧弟");
		// 海选时的自我介绍
		user.setDescription("百变双双，跳舞、看电视、上网、逛街、看电影，我就是我");
		// 人气值
		user.setPopular(9455);
		// 明星梦
		user.setSaying("百变双双，跳舞、看电视、上网、逛街、看电影，我就是我");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(1);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(8745);
		// 关注了多少个人
		user.setFocusCount(8456);
		// 生日
		user.setBirthday("19900121");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u03(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("03");
		// 名称
		user.setName("Snaki");
		// 城市
		user.setCity("南京");
		// 粉丝
		user.setFans(237);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("李宇春，蛮多的");
		// 海选时的自我介绍
		user.setDescription("我每天都不一样,有时满意,有时厌烦。");
		// 人气值
		user.setPopular(3245);
		// 明星梦
		user.setSaying("好多东西都害怕,我从小胆子就好小的哈,丢脸");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(3);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(3356);
		// 关注了多少个人
		user.setFocusCount(446);
		// 生日
		user.setBirthday("19900820");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	private static void u01(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("01");
		// 名称
		user.setName("汤施施");
		// 城市
		user.setCity("武汉");
		// 粉丝
		user.setFans(153);

		// 图片的数量
		user.setImgCount(2);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("王菲、陈奕迅");
		// 海选时的自我介绍
		user.setDescription("大家好，我喜欢旅行、听歌、逛街、聚会。希望大家多多支持我");
		// 人气值
		user.setPopular(2345);
		// 明星梦
		user.setSaying("我想成为一名摇滚歌手");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(12356);
		// 关注了多少个人
		user.setFocusCount(256);
		// 生日
		user.setBirthday("19900101");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	
	private static void u02(){
		User user = new User();
		// //////////////////////////////////////////////////////////////////////
		// 编号
		user.setId("02");
		// 名称
		user.setName("屠程瑶");
		// 城市
		user.setCity("上海");
		// 粉丝
		user.setFans(4567);

		// 图片的数量
		user.setImgCount(8);
		// 喜欢的明星，（三个左右，逗号或顿号分开）
		user.setLoveSinger("滨崎步，Twins");
		// 海选时的自我介绍
		user.setDescription("大家好，我富有生活气息，时而甜美，时而成熟");
		// 人气值
		user.setPopular(2345);
		// 明星梦
		user.setSaying("富有生活气息，时而甜美，时而成熟");
		// 性别
		user.setSex("女");
		// 星级(1~9级)
		user.setStarLevel(4);
		// 视频数量（1~8个左右）
		user.setVideoCount(2);
		// 票数
		user.setVote(4756);
		// 关注了多少个人
		user.setFocusCount(256);
		// 生日
		user.setBirthday("19900301");
		
		users.add(user);
		//////////////////////////////////////////////////////////////////////////
	}
	
	
	private static int index = 0;
	
	/**
	 * 生成随机用户
	 */
	public static User getRandomUser(){
		
		if(index>(users.size()-1)){
			index = 0;
		}
		
		User use  = users.get(index);
		index++;
		return use;
	}
	
	
	public static User getUserById(String id){
		User user = null;
		for(User u:users){
			if(u.getId().equals(id)){
				user = u;
			}
		}
		
		return user;
	}
}
