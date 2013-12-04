package com.athudong.video.bean;

public class CircleLine {

	private String id1;
	private String id2;
	private String id3;
	
	private String name1;
	private String name2;
	private String name3;
	
	
	private String img1;
	private String img2;
	private String img3;
	
	public CircleLine(String id1, String name1, String img1, String id2, String name2, String img2, String id3, String name3, String img3) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
		this.name1 =name1;
		this.name2 = name2;
		this.name3 = name3;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public String getId3() {
		return id3;
	}

	public void setId3(String id3) {
		this.id3 = id3;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

}
