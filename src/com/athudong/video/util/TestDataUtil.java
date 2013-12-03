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
			// 年龄
			user.setAge(19);
			// 图片的数量
			user.setImgCount(8);
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
			
			users.add(user);
			//////////////////////////////////////////////////////////////////////////


		}

	}
	
	/**
	 * 生成随机用户
	 */
	public static User getRandomUser(){
		return users.get(0);
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
