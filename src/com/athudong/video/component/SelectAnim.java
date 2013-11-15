package com.athudong.video.component;

import com.nineoldandroids.animation.ObjectAnimator;

import android.view.View;

public class SelectAnim {

	private View leftHead;
	private View midHead;
	private View rightHead;
	private View fourHead;
	
	public SelectAnim(View leftHead, View midHead, View rightHead, View fourHead){
		this.leftHead = leftHead;
		this.midHead = midHead;
		this.rightHead = rightHead;
		this.fourHead = fourHead;
	}
	

	private int length =0;
	
	private static final int DUR_TIME = 300;
	
	private int round = 1;
	
	public void prev(){
		if(length<=0){
			length = rightHead.getLeft()-midHead.getLeft();
		}
		
		if(round==NOW_LEFT_MID_RIGHT_FOUR){
			ObjectAnimator.ofFloat(leftHead, "translationX", 0, length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", 0, length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", 0, length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", -3*length,-2*length).setDuration(DUR_TIME).start();
			round=NOW_FOUR_LEFT_MID_RIGHT;
		}else if(round==NOW_FOUR_LEFT_MID_RIGHT){
			ObjectAnimator.ofFloat(leftHead, "translationX", length, 2*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", length, 2*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", -3*length, -2*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", -2*length,-length).setDuration(DUR_TIME).start();
			round=NOW_RIGHT_FOUR_LEFT_MID;
		}else if(round==NOW_RIGHT_FOUR_LEFT_MID){
			ObjectAnimator.ofFloat(leftHead, "translationX", 2*length, 3*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", -2*length, -length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", -2*length, -length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", -length,0).setDuration(DUR_TIME).start();
			round = NOW_MID_RIGHT_FOUR_LEFT;
		}else if(round==NOW_MID_RIGHT_FOUR_LEFT){
			ObjectAnimator.ofFloat(leftHead, "translationX", -length, 0).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", -length, -0).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", -length, -0).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", 0,length).setDuration(DUR_TIME).start();
			round = NOW_LEFT_MID_RIGHT_FOUR;
		}
	}
	
	private static final int NOW_LEFT_MID_RIGHT_FOUR = 1;
	private static final int NOW_MID_RIGHT_FOUR_LEFT = 2;
	private static final int NOW_RIGHT_FOUR_LEFT_MID = 3;
	private static final int NOW_FOUR_LEFT_MID_RIGHT = 4;
	
	public void next(){
		if(length<=0){
			length = rightHead.getLeft()-midHead.getLeft();
		}
		
		if(round==NOW_LEFT_MID_RIGHT_FOUR){
			ObjectAnimator.ofFloat(leftHead, "translationX", 0, -length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", 0, -length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", 0, -length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", length,0).setDuration(DUR_TIME).start();
			round=NOW_MID_RIGHT_FOUR_LEFT;
		}else if(round==NOW_MID_RIGHT_FOUR_LEFT){
			//rightHead, fourHead, leftHead, midHead, 
			ObjectAnimator.ofFloat(leftHead, "translationX", 3*length, 2*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", -length, -2*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", -length, -2*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", 0,-length).setDuration(DUR_TIME).start();
			round=NOW_RIGHT_FOUR_LEFT_MID;
		}else if(round==NOW_RIGHT_FOUR_LEFT_MID){ 
			ObjectAnimator.ofFloat(leftHead, "translationX", 2*length, 1*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", 2*length, length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", -2*length, -3*length).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", -length,-2*length).setDuration(DUR_TIME).start();
			round = NOW_FOUR_LEFT_MID_RIGHT;
		}else if(round==NOW_FOUR_LEFT_MID_RIGHT){
			ObjectAnimator.ofFloat(leftHead, "translationX", 1*length, 0).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(midHead, "translationX", length, 0).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(rightHead, "translationX", length, 0).setDuration(DUR_TIME).start();
			ObjectAnimator.ofFloat(fourHead, "translationX", -2*length,-3*length).setDuration(DUR_TIME).start();
			round=NOW_LEFT_MID_RIGHT_FOUR;
		}
		
	}
	
}
