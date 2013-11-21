package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.athudong.video.component.ImageViewTouchListener;
import com.athudong.video.component.VideoHelper;
import com.athudong.video.task.BaseTask;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 参赛者的视频列表界面（主要是参赛者的视频）
 */
public class VideosActivity extends BaseActivity {

	private ViewPager viewpager;

	private List<View> imgViews;

	private ImageView videoBg;

	private VideoHelper videoHelper;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_videos);
		findViewById(R.id.backBtn).setOnClickListener(this);

		viewpager = (ViewPager) findViewById(R.id.viewpager);

		findViewById(R.id.defaultPlayBtn).setOnClickListener(this);

		videoBg = (ImageView) findViewById(R.id.videoBgImg);

		imgViews = new ArrayList<View>();

		View h01 = createView(R.layout.video_nav_imgveiw);
		View h02 = createView(R.layout.video_nav_imgveiw);

		imgViews.add(h01);
		imgViews.add(h02);

		viewpager.setAdapter(new ViewPagerAdapter(imgViews));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		initPage1(h01);
		initPage2(h02);

		videoHelper = new VideoHelper(this);
		
		
		findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
		findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
	}

	@Override
	protected void onPause() {
		videoHelper.pause();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		videoHelper.stop();
		super.onDestroy();
	}

	private void initPage1(View page) {

		View btn01 = page.findViewById(R.id.videoBtn01);
		View btn02 = page.findViewById(R.id.videoBtn02);
		View btn03 = page.findViewById(R.id.videoBtn03);
		View btn04 = page.findViewById(R.id.videoBtn04);

		ImageView img01 = (ImageView) btn01.findViewWithTag("img");
		ImageView img02 = (ImageView) btn02.findViewWithTag("img");
		ImageView img03 = (ImageView) btn03.findViewWithTag("img");
		ImageView img04 = (ImageView) btn04.findViewWithTag("img");

		img01.setImageResource(R.drawable.intro_test_head_01);
		img02.setImageResource(R.drawable.intro_test_head_02);
		img03.setImageResource(R.drawable.intro_test_head_03);
		img04.setImageResource(R.drawable.intro_test_head_04);

		btn01.setTag("intro_test_img_01.jpg");
		btn02.setTag("intro_test_img_02.jpg");
		btn03.setTag("intro_test_img_03.jpg");
		btn04.setTag("intro_test_img_04.jpg");

		btn01.setOnClickListener(this);
		btn02.setOnClickListener(this);
		btn03.setOnClickListener(this);
		btn04.setOnClickListener(this);

		viewpager.setCurrentItem(0);
		anim(btn01);
	}

	private void initPage2(View page) {

		View btn01 = page.findViewById(R.id.videoBtn01);
		View btn02 = page.findViewById(R.id.videoBtn02);
		View btn03 = page.findViewById(R.id.videoBtn03);
		View btn04 = page.findViewById(R.id.videoBtn04);

		ImageView img01 = (ImageView) btn01.findViewWithTag("img");
		ImageView img02 = (ImageView) btn02.findViewWithTag("img");
		ImageView img03 = (ImageView) btn03.findViewWithTag("img");
		ImageView img04 = (ImageView) btn04.findViewWithTag("img");

		img01.setImageResource(R.drawable.intro_test_head_05);
		img02.setImageResource(R.drawable.intro_test_head_06);
		img03.setImageResource(R.drawable.intro_test_head_07);
		img04.setImageResource(R.drawable.intro_test_head_08);

		btn01.setTag("intro_test_img_05.jpg");
		btn02.setTag("intro_test_img_06.jpg");
		btn03.setTag("intro_test_img_07.jpg");
		btn04.setTag("intro_test_img_08.jpg");

		btn01.setOnClickListener(this);
		btn02.setOnClickListener(this);
		btn03.setOnClickListener(this);
		btn04.setOnClickListener(this);
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
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.backBtn) {
			finish();
		} else if (id == R.id.videoBtn01 || id == R.id.videoBtn02 || id == R.id.videoBtn03 || id == R.id.videoBtn04) {
			anim(view);
			Object path = view.getTag();
			Bitmap bitmap = getBitmapFromAsset(path.toString());
			findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
			findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
			videoBg.setImageBitmap(bitmap);
			videoHelper.pause();
		} else if (id == R.id.defaultPlayBtn) {
			String path = Environment.getExternalStorageDirectory() + "/test.mp4";
			videoHelper.play(path);
		}
	}

	class ViewPagerAdapter extends PagerAdapter {
		private List<View> list = null;

		public ViewPagerAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(list.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	class ViewPagerPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int state) {

		}

		@Override
		public void onPageScrolled(int page, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int page) {
			View view = imgViews.get(page);
			View btn01 = view.findViewById(R.id.videoBtn01);
			Object path = btn01.getTag();
			Bitmap bitmap = getBitmapFromAsset(path.toString());
			findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
			findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
			videoBg.setImageBitmap(bitmap);

			anim(btn01);
		}
	}

	private void anim(View btn) {
		View view = imgViews.get(viewpager.getCurrentItem());
		View btn01 = view.findViewById(R.id.videoBtn01);
		View btn02 = view.findViewById(R.id.videoBtn02);
		View btn03 = view.findViewById(R.id.videoBtn03);
		View btn04 = view.findViewById(R.id.videoBtn04);

		btn01.setClickable(true);
		btn02.setClickable(true);
		btn03.setClickable(true);
		btn04.setClickable(true);
		btn.setClickable(false);

		if (btn == currentVideoView) {
			return;
		}

		if (btn01 == btn) {
			animBig(btn01.findViewWithTag("img"));
			animSmall(btn02.findViewWithTag("img"));
			animSmall(btn03.findViewWithTag("img"));
			animSmall(btn04.findViewWithTag("img"));

		} else if (btn02 == btn) {
			animBig(btn02.findViewWithTag("img"));
			animSmall(btn01.findViewWithTag("img"));
			animSmall(btn03.findViewWithTag("img"));
			animSmall(btn04.findViewWithTag("img"));
		} else if (btn03 == btn) {
			animBig(btn03.findViewWithTag("img"));
			animSmall(btn02.findViewWithTag("img"));
			animSmall(btn01.findViewWithTag("img"));
			animSmall(btn04.findViewWithTag("img"));
		} else if (btn04 == btn) {
			animBig(btn04.findViewWithTag("img"));
			animSmall(btn02.findViewWithTag("img"));
			animSmall(btn03.findViewWithTag("img"));
			animSmall(btn01.findViewWithTag("img"));
		}

		btn = currentVideoView;
	}

	private static final float big = 1.2f;
	private static final float small = 1.0f;

	private View currentVideoView;

	private void animBig(View btn) {
		ObjectAnimator.ofFloat(btn, "scaleX", small, big).start();
		ObjectAnimator.ofFloat(btn, "scaleY", small, big).start();

	}

	private void animSmall(View btn) {
		if (btn == currentVideoView) {
			ObjectAnimator.ofFloat(btn, "scaleX", big, small).start();
			ObjectAnimator.ofFloat(btn, "scaleY", big, small).start();
		} else {
			ObjectAnimator.ofFloat(btn, "scaleX", small, small).start();
			ObjectAnimator.ofFloat(btn, "scaleY", small, small).start();
		}
	}
}
