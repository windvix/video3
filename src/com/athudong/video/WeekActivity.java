package com.athudong.video;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.athudong.video.bean.User;
import com.athudong.video.task.BaseTask;
import com.athudong.video.util.TestDataUtil;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 周赛PK界面
 */
public class WeekActivity extends BaseActivity {

	private View ringLayout;

	private View leftComLayout;

	private View rightComLayout;

	private View pkRing;

	private View leftBg;

	private View rightBg;

	private TextView leftName;

	private TextView rightName;

	private View thumbBtn;

	private User leftUser;
	private User rightUser;
	
	
	private ImageView leftBgImg;
	private ImageView rightBgImg;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_week_pk_back);
		findViewById(R.id.backBtn).setOnClickListener(this);

		ringLayout = findViewById(R.id.ringLayout);
		leftName = (TextView)findViewById(R.id.leftNameTv);
		rightName = (TextView)findViewById(R.id.rightNameTv);

		leftComLayout = findViewById(R.id.leftComLayout);
		thumbBtn = findViewById(R.id.thumbBtn);

		leftBg = findViewById(R.id.leftBg);
		rightBg = findViewById(R.id.rightBg);
		
		leftBgImg = (ImageView)leftBg.findViewWithTag("img");
		rightBgImg = (ImageView)rightBg.findViewWithTag("img");
		

		rightComLayout = findViewById(R.id.rightComLayout);

		pkRing = findViewById(R.id.pkRing);

		leftBg.setOnClickListener(this);
		rightBg.setOnClickListener(this);

		findViewById(R.id.leftVideoBtn).setOnClickListener(this);
		findViewById(R.id.rightVideoBtn).setOnClickListener(this);
		
		findViewById(R.id.leftZoneBtn).setOnClickListener(this);
		findViewById(R.id.rightZoneBtn).setOnClickListener(this);
		
		findViewById(R.id.leftAudioBtn).setOnClickListener(this);
		findViewById(R.id.rightAudioBtn).setOnClickListener(this);
		
		thumbBtn.setOnClickListener(this);

		leftUser = TestDataUtil.getRandomUser();
		rightUser = TestDataUtil.getRandomUser();

		leftComLayout.setVisibility(View.INVISIBLE);
		rightComLayout.setVisibility(View.INVISIBLE);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				initComLayout();
			}
		}, 600);
		thumbBtn.setVisibility(View.INVISIBLE);
		
		leftBgImg.setImageBitmap(null);
		rightBgImg.setImageBitmap(null);
		
		leftName.setText(leftUser.getName());
		rightName.setText(rightUser.getName());
	}

	/**
	 * 初始化组件（让背景分成两半。隐藏左右的按钮）
	 */
	private void initComLayout() {
		
		String p1 = getTestPath()+leftUser.getId()+"_01.jpg";
		Bitmap b1 = readBitmapAutoSize(p1, leftBgImg.getWidth(), leftBgImg.getHeight());
		
		String p2 = getTestPath()+rightUser.getId()+"_01.jpg";
		Bitmap b2 = readBitmapAutoSize(p2, rightBgImg.getWidth(), rightBgImg.getHeight());
		
		leftBgImg.setImageBitmap(b1);
		rightBgImg.setImageBitmap(b2);
		
		int dis = getScreenWidth() - leftComLayout.getLeft();
		ObjectAnimator.ofFloat(leftComLayout, "translationX", 0, -dis).start();
		ObjectAnimator.ofFloat(rightComLayout, "translationX", 0, dis).start();

		ObjectAnimator.ofFloat(leftBg, "translationX", 0, -getScreenWidth() / 2).start();
		ObjectAnimator.ofFloat(rightBg, "translationX", 0, getScreenWidth() / 2).start();
	
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

	private boolean isShowLeft = false;

	private boolean isShowRight = false;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.backBtn) {
			finish();
		} else if (id == R.id.leftBg && !isShowRight) {
			moveLeft();
		} else if (id == R.id.rightBg && !isShowLeft) {
			moveRight();
		} else if (id == R.id.leftVideoBtn) {
			playVideo(getTestPath()  + "01_video_01.flv");
		} else if (id == R.id.rightVideoBtn) {
			playVideo(getTestPath() +  "01_video_02.flv");
		} else if (id == R.id.leftZoneBtn) {
			Intent intent = new Intent(this, ZoneActivity.class);
			intent.putExtra("id", leftUser.getId());
			startActivity(intent);
		} else if (id == R.id.rightZoneBtn) {
			Intent intent = new Intent(this, ZoneActivity.class);
			intent.putExtra("id", rightUser.getId());
			startActivity(intent);
		} else if (id == R.id.thumbBtn) {
			if (thumbBtn.getVisibility() == View.VISIBLE) {
				// 左边投票
				if (isShowLeft) {
					moveLeft();

					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							changeRight();
						}
					}, DUR_TIME + 100);

				}
				// 右边投票
				if (isShowRight) {
					
					moveRight();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							changeLeft();
						}
					}, DUR_TIME + 100);
				}
			}
		}
	}

	private void changeLeft() {
		leftUser = TestDataUtil.getRandomUser();
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftBg, "translationX", -getScreenWidth()/2, -getScreenWidth());
		anim1.setDuration(DUR_TIME);
		anim1.start();

		ObjectAnimator anim2 = ObjectAnimator.ofFloat(leftName, "translationX", 0, -getScreenWidth()/2);
		anim2.setDuration(DUR_TIME);
		anim2.start();


		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				
				
				String path = getTestPath()+leftUser.getId()+"_01.jpg";
				Bitmap bitmap = readBitmapAutoSize(path, leftBgImg.getWidth(), leftBgImg.getHeight());
				
				leftBgImg.setImageBitmap(bitmap);
				
				String name = leftUser.getName();
				toast(name);
				leftName.setText(name);
				
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftBg, "translationX", -getScreenWidth(), -getScreenWidth()/2);
				anim1.setDuration(DUR_TIME);
				anim1.start();

				ObjectAnimator anim2 = ObjectAnimator.ofFloat(leftName, "translationX", -getScreenWidth()/2, 0);
				anim2.setDuration(DUR_TIME);
				anim2.start();
			}
		}, 2000);

	}

	private void changeRight() {
		rightUser = TestDataUtil.getRandomUser();
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightBg, "translationX", getScreenWidth()/2, getScreenWidth());
		anim1.setDuration(DUR_TIME);
		anim1.start();

		ObjectAnimator anim2 = ObjectAnimator.ofFloat(rightName, "translationX", 0, getScreenWidth()/2);
		anim2.setDuration(DUR_TIME);
		anim2.start();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				
				
				String path = getTestPath()+rightUser.getId()+"_01.jpg";
				Bitmap bitmap = readBitmapAutoSize(path, rightBgImg.getWidth(), rightBgImg.getHeight());
				
				rightBgImg.setImageBitmap(bitmap);
				

				
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightBg, "translationX", getScreenWidth(), getScreenWidth()/2);
				anim1.setDuration(DUR_TIME);
				anim1.start();

				ObjectAnimator anim2 = ObjectAnimator.ofFloat(rightName, "translationX", getScreenWidth()/2, 0);
				anim2.setDuration(DUR_TIME);
				anim2.start();
				
				
				
				String name = rightUser.getName();
				toast(name);
				
				rightName.clearAnimation();
				rightName.setText(rightUser.getName());
				rightName.invalidate();
			}
		}, 2000);
	}

	/**
	 * 播放视频
	 */
	private void playVideo(String path) {
		Uri uri = Uri.parse(path);
		// 调用系统自带的播放器
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "video/mp4");
		startActivity(intent);
	}

	private static final int DUR_TIME = 500;

	/**
	 * 移动左边组件
	 */
	private void moveLeft() {
		leftComLayout.setVisibility(View.VISIBLE);
		rightComLayout.setVisibility(View.VISIBLE);

		if (!isShowLeft) {
			int dis = getScreenWidth() - leftComLayout.getLeft();
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftComLayout, "translationX", -dis, 0);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", 0, nW);
			anim21.setDuration(DUR_TIME);
			anim21.start();

			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", 0, nW);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			int w = (getScreenWidth() / 2);
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", 0, w);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", -w, 0);
			anim41.setDuration(DUR_TIME);
			anim41.start();
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", w, 2 * w);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			// 显示投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 0, 1);
			anim5.setDuration(DUR_TIME);
			anim5.setStartDelay(250);
			anim5.start();
			thumbBtn.setVisibility(View.VISIBLE);

			isShowLeft = true;

		} else {
			int dis = getScreenWidth() - leftComLayout.getLeft();
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftComLayout, "translationX", 0, -dis);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", nW, 0);
			anim21.setDuration(DUR_TIME);
			anim21.start();
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", nW, 0);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			int w = (getScreenWidth() / 2);
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", w, 0);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", 0, -w);
			anim41.setDuration(DUR_TIME);
			anim41.start();
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", 2 * w, w);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			// 隐藏投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 1, 0);
			anim5.setDuration(DUR_TIME);
			anim5.start();
			thumbBtn.setVisibility(View.INVISIBLE);

			isShowLeft = false;
		}
	}

	/**
	 * 移动右边组件
	 */
	private void moveRight() {
		leftComLayout.setVisibility(View.VISIBLE);
		rightComLayout.setVisibility(View.VISIBLE);

		if (!isShowRight) {
			// 移动右边的组件过来
			int dis = getScreenWidth() - leftComLayout.getLeft();
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightComLayout, "translationX", dis, 0);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			// 将名字往左移
			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", 0, -nW);
			anim21.setDuration(DUR_TIME);
			anim21.start();
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", 0, -nW);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			// 将环入左移
			int w = (getScreenWidth() / 2);
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", 0, -w);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", -w, -2 * w);
			anim41.setDuration(DUR_TIME);
			anim41.start();
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", w, 0);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			
			// 显示投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 0, 1);
			anim5.setDuration(DUR_TIME);
			anim5.setStartDelay(250);
			anim5.start();
			thumbBtn.setVisibility(View.VISIBLE);
			
			isShowRight = true;

		} else {
			int dis = getScreenWidth() - leftComLayout.getLeft();
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightComLayout, "translationX", 0, dis);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", -nW, 0);
			anim21.setDuration(DUR_TIME);
			anim21.start();
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", -nW, 0);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			int w = (getScreenWidth() / 2);
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", -w, 0);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", -2 * w, -w);
			anim41.setDuration(DUR_TIME);
			anim41.start();
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", 0, w);
			anim42.setDuration(DUR_TIME);
			anim42.start();
			
			
			// 隐藏投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 1, 0);
			anim5.setDuration(DUR_TIME);
			anim5.start();
			thumbBtn.setVisibility(View.INVISIBLE);

			isShowRight = false;
		}
	}
}
