package com.athudong.video;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
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

	/**
	 * 左选手的组件布局（三个按钮）
	 */
	private View leftComLayout;

	/**
	 * 右选手的组件布局（三个按钮）
	 */
	private View rightComLayout;

	/**
	 * 中间的PK环
	 */
	private View pkRing;

	/**
	 * 左选手的背景布局
	 */
	private View leftBg;

	/**
	 * 右选手的背景布局
	 */
	private View rightBg;

	/**
	 * 左选手名称
	 */
	private TextView leftName;

	/**
	 * 右选手名称
	 */
	private TextView rightName;

	/**
	 * 投票按钮
	 */
	private Button thumbBtn;

	/**
	 * 左选手
	 */
	private User leftUser;

	/**
	 * 右选手
	 */
	private User rightUser;

	/**
	 * 左选手背景图片(包括在左选手的背景布局中)
	 */
	private ImageView leftBgImg;

	/**
	 * 右选手背景图片(包括在右选手的背景布局)
	 */
	private ImageView rightBgImg;

	/**
	 * 剩余票数
	 */
	private static int voteCount = 2;

	@Override
	protected void initView(Bundle savedInstanceState) {
		
		if(voteCount<=0){
			finish();
			Intent intent = new Intent(this, WeekResultActivity.class);
			startActivity(intent);
			return;
		}
		
		
		//以下是3.0版本的操作（动画效果在3.0版本以下与4.0的操作不一样）
		if(isSystemVersionLess4()){
			new WeekActivityLess4(this);
			return;
		}
		
		
		setContentView(R.layout.activity_week_pk_back);
		
		
		
		findViewById(R.id.backBtn).setOnClickListener(this);

		ringLayout = findViewById(R.id.ringLayout);
		leftName = (TextView) findViewById(R.id.leftNameTv);
		rightName = (TextView) findViewById(R.id.rightNameTv);

		leftComLayout = findViewById(R.id.leftComLayout);
		thumbBtn = (Button) findViewById(R.id.thumbBtn);

		leftBg = findViewById(R.id.leftBg);
		rightBg = findViewById(R.id.rightBg);

		leftBgImg = (ImageView) leftBg.findViewWithTag("img");
		rightBgImg = (ImageView) rightBg.findViewWithTag("img");

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

		/**
		 * 单机测试,在测试数据中。抽出两个用户，进行PK
		 */
		leftUser = TestDataUtil.getRandomUser();
		rightUser = TestDataUtil.getRandomUser();

		/**
		 * 开始的时候。左选手和右选手的组件按钮设为不可见
		 */
		leftComLayout.setVisibility(View.INVISIBLE);
		rightComLayout.setVisibility(View.INVISIBLE);

		// 延迟调用，初始化组件（当前界面还没可见时。无法获取各组件的大小。所以延迟，等界面可见时再调用）
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				initComLayout();
			}
		}, 600);

		/**
		 * 投票按钮默认设为不可见
		 */
		thumbBtn.setVisibility(View.INVISIBLE);

		/**
		 * 左背景和右背景设为空
		 */
		leftBgImg.setImageBitmap(null);
		rightBgImg.setImageBitmap(null);

		/**
		 * 设置左右选手的名字
		 */
		leftName.setText(leftUser.getName());
		rightName.setText(rightUser.getName());
	}

	/**
	 * 初始化组件（让背景分成两半。隐藏左右选手的组件按钮）
	 */
	private void initComLayout() {

		/**
		 * 设置左右选手的背景图片
		 */
		String p1 = getTestPath() + leftUser.getId() + "_01.jpg";
		Bitmap b1 = readBitmapAutoSize(p1, leftBgImg.getWidth(), leftBgImg.getHeight());

		String p2 = getTestPath() + rightUser.getId() + "_01.jpg";
		Bitmap b2 = readBitmapAutoSize(p2, rightBgImg.getWidth(), rightBgImg.getHeight());

		leftBgImg.setImageBitmap(b1);
		rightBgImg.setImageBitmap(b2);

		/**
		 * 得到动画移动的距离
		 */
		int dis = getScreenWidth() - leftComLayout.getLeft();

		/**
		 * 左右选手的资料组件，一个左移，一个右移（看不到效果。因为组件默认invisible）
		 */
		ObjectAnimator.ofFloat(leftComLayout, "translationX", 0, -dis).start();
		ObjectAnimator.ofFloat(rightComLayout, "translationX", 0, dis).start();

		/**
		 * 两张背景图，一个左移，一个右移
		 */
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

	// 当前是否在显示左选手
	private boolean isShowLeft = false;

	// 当前是否在显示右选手
	private boolean isShowRight = false;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		// 返回按钮
		if (id == R.id.backBtn) {
			finish();
		}
		// 左选手背景图
		else if (id == R.id.leftBg && !isShowRight) {
			moveLeft();
		}
		// 右选手背景图
		else if (id == R.id.rightBg && !isShowLeft) {
			moveRight();
		}
		// 左选手视频按钮
		else if (id == R.id.leftVideoBtn) {
			playVideo(getTestPath() + "01_video_01.flv");
		}
		// 右选手视频按钮
		else if (id == R.id.rightVideoBtn) {
			playVideo(getTestPath() + "01_video_02.flv");
		}
		// 左选手资料按钮
		else if (id == R.id.leftZoneBtn) {
			Intent intent = new Intent(this, ZoneActivity.class);
			intent.putExtra("id", leftUser.getId());
			startActivity(intent);
		}
		// 右选手资料按钮
		else if (id == R.id.rightZoneBtn) {
			Intent intent = new Intent(this, ZoneActivity.class);
			intent.putExtra("id", rightUser.getId());
			startActivity(intent);
		}
		// 投票按钮
		else if (id == R.id.thumbBtn) {
			if (thumbBtn.getVisibility() == View.VISIBLE) {
				// 投票给左选手
				if (isShowLeft) {
					// 重置布局，回到中间
					moveLeft();

					// 延迟调用，等布局还原到中间位置，再换掉右边的选手
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							// 票数大于0时
							if (voteCount > 0) {
								changeRight();
								voteCount--;
							} else {
								finish();
								Intent intent = new Intent(WeekActivity.this, WeekResultActivity.class);
								startActivity(intent);
							}
						}
					}, DUR_TIME + 100);

				}
				// 投票给右选手
				if (isShowRight) {
					// 重置布局，回到中间
					moveRight();

					// 延迟调用，等布局还原到中间位置，再换掉左边的选手
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							// 票数大于0时
							if (voteCount > 0) {
								changeLeft();
								voteCount--;
							} else {
								finish();
								Intent intent = new Intent(WeekActivity.this, WeekResultActivity.class);
								startActivity(intent);
							}
						}
					}, DUR_TIME + 100);

				}
			}
		}
	}

	/**
	 * 更换左边的选手
	 */
	private void changeLeft() {
		// 换一个选手
		leftUser = TestDataUtil.getRandomUser();

		// 左选手的背景图片，从中间向左退出屏幕
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftBg, "translationX", -getScreenWidth() / 2, -getScreenWidth());
		anim1.setDuration(DUR_TIME);
		anim1.start();

		// 左选手的名称，向左退出屏幕
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(leftName, "translationX", 0, -getScreenWidth() / 2);
		anim2.setDuration(DUR_TIME);
		anim2.start();

		// 延迟调用 ，等左选手组件全部退出屏幕后，2秒后再换一个选手重新进入
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				/**
				 * 重新设置左选手的背景图片
				 */
				String path = getTestPath() + leftUser.getId() + "_01.jpg";
				Bitmap bitmap = readBitmapAutoSize(path, leftBgImg.getWidth(), leftBgImg.getHeight());
				leftBgImg.setImageBitmap(bitmap);

				/**
				 * 左选手背景图从左边慢慢进入到屏幕中间
				 */
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftBg, "translationX", -getScreenWidth(), -getScreenWidth() / 2);
				anim1.setDuration(DUR_TIME);
				anim1.start();

				/**
				 * 左选手的名称，从左边慢慢进入屏幕
				 */
				ObjectAnimator anim2 = ObjectAnimator.ofFloat(leftName, "translationX", -getScreenWidth() / 2, 0);
				anim2.setDuration(DUR_TIME);
				anim2.start();

				/**
				 * 重新设置左选手的名字 动画原因，必须invalidate才能显示改变后的text
				 */
				String name = leftUser.getName();
				leftName.clearAnimation();
				leftName.setText(name);
				leftName.invalidate();
			}
		}, 2000);

	}

	/**
	 * 更换右边的选手
	 */
	private void changeRight() {
		// 换一个选手
		rightUser = TestDataUtil.getRandomUser();

		// 右选手的背景图片，从中间向右退出屏幕
		ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightBg, "translationX", getScreenWidth() / 2, getScreenWidth());
		anim1.setDuration(DUR_TIME);
		anim1.start();

		// 右选手的名称，向右退出屏幕
		ObjectAnimator anim2 = ObjectAnimator.ofFloat(rightName, "translationX", 0, getScreenWidth() / 2);
		anim2.setDuration(DUR_TIME);
		anim2.start();

		// 延迟调用 ，等右选手组件全部退出屏幕后，2秒后再换一个选手重新进入
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				/**
				 * 重新设置右选手的背景图片
				 */
				String path = getTestPath() + rightUser.getId() + "_01.jpg";
				Bitmap bitmap = readBitmapAutoSize(path, rightBgImg.getWidth(), rightBgImg.getHeight());
				rightBgImg.setImageBitmap(bitmap);

				/**
				 * 右选手背景图从右边慢慢进入到屏幕中间
				 */
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightBg, "translationX", getScreenWidth(), getScreenWidth() / 2);
				anim1.setDuration(DUR_TIME);
				anim1.start();

				/**
				 * 右选手的名称，从右边慢慢进入屏幕
				 */
				ObjectAnimator anim2 = ObjectAnimator.ofFloat(rightName, "translationX", getScreenWidth() / 2, 0);
				anim2.setDuration(DUR_TIME);
				anim2.start();

				/**
				 * 重新设置右选手的名字 动画原因，必须invalidate才能显示改变后的text
				 */
				String name = rightUser.getName();
				rightName.clearAnimation();
				rightName.setText(name);
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

	/**
	 * 动画的持续时间
	 */
	private static final int DUR_TIME = 500;

	/**
	 * 移动左边组件
	 */
	private void moveLeft() {
		// 左右选手组件状态变为可见
		leftComLayout.setVisibility(View.VISIBLE);
		rightComLayout.setVisibility(View.VISIBLE);
		// 显示左选手，视图向右移
		if (!isShowLeft) {
			// 移动距离1
			int dis = getScreenWidth() - leftComLayout.getLeft();

			// 左选手资料组件从左边移出来
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftComLayout, "translationX", -dis, 0);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			// 移动距离2
			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;

			// 左选手名称向右移
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", 0, nW);
			anim21.setDuration(DUR_TIME);
			anim21.start();

			// 右选手名称从右边退出去
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", 0, nW);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			// 移动距离3
			int w = (getScreenWidth() / 2);

			// PK环向右移到屏幕边缘
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", 0, w);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			// 左选手背景从中间向右，移满整个屏幕
			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", -w, 0);
			anim41.setDuration(DUR_TIME);
			anim41.start();

			// 右选手背景从中间向右，退出屏幕
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", w, 2 * w);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			// 显示投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 0, 1);
			anim5.setDuration(DUR_TIME);
			anim5.setStartDelay(250);
			anim5.start();
			thumbBtn.setVisibility(View.VISIBLE);

			// 改变投票按钮文本的性别，清除动画后内容变化才能显示
			thumbBtn.clearAnimation();
			if (User.getSexInt(leftUser.getSex()) == User.MAN) {
				thumbBtn.setText("投他");
			} else {
				thumbBtn.setText("投她");
			}
			thumbBtn.invalidate();

			// 左选手显示状态为真
			isShowLeft = true;

		}
		// 还原中间位置 ，隐藏左选手
		else {

			// 移动距离1
			int dis = getScreenWidth() - leftComLayout.getLeft();

			// 左选手资料组件向左边移出屏幕
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(leftComLayout, "translationX", 0, -dis);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			// 移动距离2
			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;

			// 左选手名称向右移
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", nW, 0);
			anim21.setDuration(DUR_TIME);
			anim21.start();

			// 右选手名字从右边移入屏幕
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", nW, 0);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			// 移动距离3
			int w = (getScreenWidth() / 2);

			// PK环从屏幕边缘移回中间
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", w, 0);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			// 左选手背景图向左移，回到半屏状态
			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", 0, -w);
			anim41.setDuration(DUR_TIME);
			anim41.start();

			// 右选手背景图向左移，回到斗屏状态
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", 2 * w, w);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			// 隐藏投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 1, 0);
			anim5.setDuration(DUR_TIME);
			anim5.start();
			thumbBtn.setVisibility(View.INVISIBLE);

			// 显示左选手视图状态为假
			isShowLeft = false;
		}
	}

	/**
	 * 移动右边组件
	 */
	private void moveRight() {
		// 左右选手组件状态变为可见
		leftComLayout.setVisibility(View.VISIBLE);
		rightComLayout.setVisibility(View.VISIBLE);

		// 显示右选手，视图向左移
		if (!isShowRight) {
			// 移动距离1
			int dis = getScreenWidth() - leftComLayout.getLeft();

			// 右选手资料组件从右边进入屏幕
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightComLayout, "translationX", dis, 0);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			// 移动距离2
			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;

			// 左选手名字向左边退出屏幕
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", 0, -nW);
			anim21.setDuration(DUR_TIME);
			anim21.start();

			// 右选手名字向左移动
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", 0, -nW);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			// 移动距离3
			int w = (getScreenWidth() / 2);

			// PK环向左移动，移到左边屏幕边缘
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", 0, -w);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			// 左选手背景图片从中间向左移，最后退出屏幕
			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", -w, -2 * w);
			anim41.setDuration(DUR_TIME);
			anim41.start();

			// 右选手背景图片从中间向左移，占满整个屏幕
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", w, 0);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			// 显示投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 0, 1);
			anim5.setDuration(DUR_TIME);
			anim5.setStartDelay(250);
			anim5.start();
			thumbBtn.setVisibility(View.VISIBLE);

			// 改变投票按钮的性别，清除动画后内容变化才能显示
			thumbBtn.clearAnimation();
			if (User.getSexInt(rightUser.getSex()) == User.MAN) {
				thumbBtn.setText("投他");
			} else {
				thumbBtn.setText("投她");
			}
			thumbBtn.invalidate();

			// 显示右选手视图状态为真
			isShowRight = true;

		}

		// 还原中间位置 ，隐藏右选手
		else {
			// 移动距离1
			int dis = getScreenWidth() - leftComLayout.getLeft();

			// 右选手资料组件向右移出屏幕
			ObjectAnimator anim1 = ObjectAnimator.ofFloat(rightComLayout, "translationX", 0, dis);
			anim1.setDuration(DUR_TIME);
			anim1.start();

			// 移动距离2
			int nW = getScreenWidth() - (ringLayout.getWidth() / 2) - 10;

			// 左选手名字向右移，进入屏幕
			ObjectAnimator anim21 = ObjectAnimator.ofFloat(leftName, "translationX", -nW, 0);
			anim21.setDuration(DUR_TIME);
			anim21.start();

			// 右选手名字向右移
			ObjectAnimator anim22 = ObjectAnimator.ofFloat(rightName, "translationX", -nW, 0);
			anim22.setDuration(DUR_TIME);
			anim22.start();

			// 移动距离3
			int w = (getScreenWidth() / 2);

			// PK环从左边缘向屏幕中间移动
			ObjectAnimator anim3 = ObjectAnimator.ofFloat(ringLayout, "translationX", -w, 0);
			anim3.setDuration(DUR_TIME);
			anim3.start();

			// 左选手背景从屏幕外向右移到屏幕中间
			ObjectAnimator anim41 = ObjectAnimator.ofFloat(leftBg, "translationX", -2 * w, -w);
			anim41.setDuration(DUR_TIME);
			anim41.start();

			// 右选手背景向右移，到一半屏幕位置
			ObjectAnimator anim42 = ObjectAnimator.ofFloat(rightBg, "translationX", 0, w);
			anim42.setDuration(DUR_TIME);
			anim42.start();

			// 隐藏投票按钮
			ObjectAnimator anim5 = ObjectAnimator.ofFloat(thumbBtn, "alpha", 1, 0);
			anim5.setDuration(DUR_TIME);
			anim5.start();
			thumbBtn.setVisibility(View.INVISIBLE);

			// 显示右选手视力状态为假
			isShowRight = false;
		}
	}
}
