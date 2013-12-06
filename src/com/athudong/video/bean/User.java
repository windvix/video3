package com.athudong.video.bean;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.athudong.video.util.StringUtil;

/**
 * 用户
 */
public class User {

	/**
	 * 账号ID
	 */
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 星级
	 */
	private int starLevel;

	/**
	 * 粉丝数量
	 */
	private int fans;

	/**
	 * 人气值
	 */
	private int popular;

	/**
	 * 票数
	 */
	private int vote;

	/**
	 * 喜欢的明星
	 */
	private String loveSinger;

	/**
	 * 明星誓言
	 */
	private String saying;

	/**
	 * 所在城市
	 */
	private String city;

	/**
	 * 明星自我描述
	 */
	private String description;
	
	/**
	 * 生日(yyyymmdd)
	 */
	private String birthday;

	
	
	/**
	 * 剩余票数
	 */
	private int voteCount = 10;
	
	private int focusCount;
	
	

	private int imgCount;
	private int videoCount;

	public int getImgCount() {
		return imgCount;
	}

	
	public int getFocusCount() {
		return focusCount;
	}


	public void setFocusCount(int focusCount) {
		this.focusCount = focusCount;
	}


	public void setImgCount(int imgCount) {
		this.imgCount = imgCount;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public int getPopular() {
		return popular;
	}

	public void setPopular(int popular) {
		this.popular = popular;
	}

	public int getVote() {
		return vote;
	}

	public void setVote(int vote) {
		this.vote = vote;
	}

	public String getLoveSinger() {
		return loveSinger;
	}

	public void setLoveSinger(String loveSinger) {
		this.loveSinger = loveSinger;
	}

	public String getSaying() {
		return saying;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static final int MAN = 1;
	public static final int WOMAN = 2;

	public static int getSexInt(String sex) {
		int gender = WOMAN;
		if (sex == null) {
			sex = "男";
		}
		if (sex.equals("男")) {
			gender = MAN;
		}
		return gender;
	}
	
	
	
	public static String getSexSimbol(String sex){
		String gender = "♀";
		if (sex == null) {
			sex = "男";
		}
		if (sex.equals("男")) {
			gender = "♂";
		}
		return gender;
	}
	
	public String getSexSimbol(){
		String gender = "♀";
		if (sex == null) {
			sex = "男";
		}
		if (sex.equals("男")) {
			gender = "♂";
		}
		return gender;
	}
	
	public static String getSexSimbol(int sex){
		String gender = "♀";
		if (sex<=0 || sex>2) {
			sex = 1;
		}
		if (sex==1) {
			gender = "♂";
		}
		return gender;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getAge(){
		String age = "20";
		
		if(birthday!=null){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			Date date = null;
			try{
				date = df.parse(birthday);
			}catch(Exception e){
				
			}
			if(date!=null){
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
				Date cDate = new Date(System.currentTimeMillis());
				String one  =df2.format(date);
				String two = df2.format(cDate);
				age = (Integer.parseInt(two)-Integer.parseInt(one))+"";
			}
		}
		return age;
	}
	
	public String getStarSign(){
		int month = 01;
		int day = 01;
		
		if(birthday!=null){
			month = Integer.parseInt(birthday.substring(4, 6));
			day = Integer.parseInt(birthday.substring(6, 8));
		}
		return StringUtil.getStarSignFromDate(month, day);
	}
}
