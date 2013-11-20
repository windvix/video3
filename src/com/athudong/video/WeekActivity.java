package com.athudong.video;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.athudong.video.task.BaseTask;
import com.athudong.video.util.AppConst;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 周赛PK界面
 */
public class WeekActivity extends BaseActivity{
	
	private View leftHead;
	private View rightHead;
	private View leftVideoLayout;
	private View rightVideoLayout;
	private TextView leftName;
	private TextView rightName;
	
	private View leftStarLayout;
	private View rightStarLayout;
	
	private View leftThumbBtn;
	private View rightThumbBtn;
	
	private View vsTv;
	private View andTv;
	
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_week_pk);
		findViewById(R.id.backBtn).setOnClickListener(this);
		
		
		
		vsTv = findViewById(R.id.vsTv);
		andTv = findViewById(R.id.andTv);
		
		
		leftThumbBtn = findViewById(R.id.leftThumbBtn);
		leftName = (TextView)findViewById(R.id.leftNameTv);
		leftHead = findViewById(R.id.leftHeadImg);
		leftVideoLayout = findViewById(R.id.left_play_btn);
		leftStarLayout = findViewById(R.id.leftFiveStar);
		leftThumbBtn.setOnClickListener(this);
		leftVideoLayout.setOnClickListener(this);
		
		
		rightThumbBtn = findViewById(R.id.rightThumbBtn);
		rightName = (TextView)findViewById(R.id.rightNameTv);
		rightHead = findViewById(R.id.rightHeadImg);
		rightVideoLayout = findViewById(R.id.right_play_btn);
		rightStarLayout = findViewById(R.id.rightFiveStar);
		rightThumbBtn.setOnClickListener(this);
		rightVideoLayout.setOnClickListener(this);
		
		
		leftHead.setOnClickListener(this);
		rightHead.setOnClickListener(this);
		
		
	}

	@Override
	protected void beforeEveryVisable() {
		
	}

	@Override
	protected void afterEveryInVisable() {
		
	}

	@Override
	protected void beforeDestory() {
		
	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {
		
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			finish();
		}else if(id==R.id.leftThumbBtn){
			moveRight();
		}else if(id==R.id.rightThumbBtn){
			moveLeft();
		}else if(id==R.id.leftHeadImg){
			Intent intent  = new Intent(this, IntroActivity.class);
			startActivity(intent);
			
		}else if(id==R.id.rightHeadImg){
			Intent intent = new Intent(this, IntroActivity.class);
			startActivity(intent);
		}else if(id==R.id.left_play_btn){
			Intent intent = new Intent(this, VideosActivity.class);
			startActivity(intent);
		}else if(id==R.id.right_play_btn){
			Intent intent = new Intent(this, VideosActivity.class);
			startActivity(intent);
		}
	}
	
	private static final int DUR_TIME = 500;
	
	private static final int DELAY_TIME = 100;


	/**
	 * 移动右边的组件
	 */
	private void moveRight(){
		ObjectAnimator.ofFloat(rightHead, AppConst.ANIM_TRANSLATION_X,0,getScreenWidth()).setDuration(DUR_TIME).start();		
		ObjectAnimator.ofFloat(rightVideoLayout, AppConst.ANIM_TRANSLATION_X,0,getScreenWidth()).setDuration(DUR_TIME).start();		
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightStarLayout, AppConst.ANIM_TRANSLATION_X, 0, getScreenWidth()).setDuration(DUR_TIME);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(rightName, AppConst.ANIM_TRANSLATION_X, 0, getScreenWidth()).setDuration(DUR_TIME);
		anim1.setStartDelay(DELAY_TIME);
		anim2.setStartDelay(DELAY_TIME);
		anim1.start();
		anim2.start();
		zoomInAnim();
		
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				ObjectAnimator.ofFloat(rightStarLayout, AppConst.ANIM_TRANSLATION_X, getScreenWidth(), 0).setDuration(DUR_TIME).start();
				ObjectAnimator.ofFloat(rightName, AppConst.ANIM_TRANSLATION_X, getScreenWidth(), 0).setDuration(DUR_TIME).start();
				
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightHead, "translationX",getScreenWidth(),0).setDuration(DUR_TIME);
				anim1.setStartDelay(DELAY_TIME);
				anim1.start();
				
				ObjectAnimator anim2 = ObjectAnimator.ofFloat(rightVideoLayout, "translationX",getScreenWidth(),0).setDuration(DUR_TIME);
				anim2.setStartDelay(DELAY_TIME);
				anim2.start();
				zoomOutAnim();
			}
		}, 2000);
	}
	
	
	/**
	 * 移动左边的组件
	 */
	private void moveLeft(){
		ObjectAnimator.ofFloat(leftHead, AppConst.ANIM_TRANSLATION_X,0,-leftHead.getLeft()-leftHead.getWidth()).setDuration(DUR_TIME).start();		
		ObjectAnimator.ofFloat(leftVideoLayout, AppConst.ANIM_TRANSLATION_X,0,-leftVideoLayout.getLeft()-leftVideoLayout.getWidth()).setDuration(DUR_TIME).start();		
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftStarLayout, AppConst.ANIM_TRANSLATION_X, 0, -leftStarLayout.getLeft()-leftStarLayout.getWidth()).setDuration(DUR_TIME);
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(leftName, AppConst.ANIM_TRANSLATION_X, 0, -leftName.getLeft()-leftName.getWidth()).setDuration(DUR_TIME);
		anim1.setStartDelay(DELAY_TIME);
		anim2.setStartDelay(DELAY_TIME);
		anim1.start();
		anim2.start();
		zoomInAnim();
		
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				ObjectAnimator.ofFloat(leftStarLayout, AppConst.ANIM_TRANSLATION_X, -leftStarLayout.getLeft()-leftStarLayout.getWidth(), 0).setDuration(DUR_TIME).start();
				ObjectAnimator.ofFloat(leftName, AppConst.ANIM_TRANSLATION_X, -leftName.getLeft()-leftName.getWidth(), 0).setDuration(DUR_TIME).start();
				
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftHead, "translationX",-leftHead.getLeft()-leftHead.getWidth(),0).setDuration(DUR_TIME);
				anim1.setStartDelay(DELAY_TIME);
				anim1.start();
				
				ObjectAnimator anim2 = ObjectAnimator.ofFloat(leftVideoLayout, "translationX",-leftVideoLayout.getLeft()-leftVideoLayout.getWidth(),0).setDuration(DUR_TIME);
				anim2.setStartDelay(DELAY_TIME);
				anim2.start();
				zoomOutAnim();
			}
		}, 2000);
	}
	
	private void zoomInAnim(){
		ObjectAnimator.ofFloat(vsTv, AppConst.ANIM_SCALE_X, 1, 0).setDuration(DUR_TIME).start();
		ObjectAnimator.ofFloat(vsTv, AppConst.ANIM_SCALE_Y, 1, 0).setDuration(DUR_TIME).start();
		ObjectAnimator.ofFloat(andTv, AppConst.ANIM_SCALE_X, 1, 0).setDuration(DUR_TIME).start();
		ObjectAnimator.ofFloat(andTv, AppConst.ANIM_SCALE_Y, 1, 0).setDuration(DUR_TIME).start();
	}
	
	private void zoomOutAnim(){
		ObjectAnimator.ofFloat(vsTv, AppConst.ANIM_SCALE_X, 0, 1).setDuration(DUR_TIME).start();
		ObjectAnimator.ofFloat(vsTv, AppConst.ANIM_SCALE_Y, 0, 1).setDuration(DUR_TIME).start();
		ObjectAnimator.ofFloat(andTv, AppConst.ANIM_SCALE_X, 0, 1).setDuration(DUR_TIME).start();
		ObjectAnimator.ofFloat(andTv, AppConst.ANIM_SCALE_Y, 0, 1).setDuration(DUR_TIME).start();
	}
	
}
