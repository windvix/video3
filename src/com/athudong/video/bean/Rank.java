package com.athudong.video.bean;

public class Rank {

	private int num;
	private String img;
	private String name;
	private String count;
	
	private String saying;
	
	private String id;

	public int getNum() {
		return num;
	}

	
	public String getSaying() {
		return saying;
	}


	public void setSaying(String saying) {
		this.saying = saying;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Rank(int num, String img, String name, String count, String saying, String id) {
		super();
		this.num = num;
		this.img = img;
		this.name = name;
		this.count = count;
		this.saying = saying;
		this.id = id;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {

		this.count = count;
	}
}
