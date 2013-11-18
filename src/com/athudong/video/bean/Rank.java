package com.athudong.video.bean;

public class Rank {

	private int num;
	private String img;
	private String name;
	private String count;

	public int getNum() {
		return num;
	}

	public Rank(int num, String img, String name, String count) {
		super();
		this.num = num;
		this.img = img;
		this.name = name;
		this.count = count;
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
