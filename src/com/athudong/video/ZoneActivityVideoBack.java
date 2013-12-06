package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.VideosActivity.ViewPagerAdapter;
import com.athudong.video.VideosActivity.ViewPagerPageChangeListener;
import com.athudong.video.bean.User;
import com.athudong.video.component.VideoHelper;
import com.athudong.video.util.TestDataUtil;
import com.nineoldandroids.animation.ObjectAnimator;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 个人空间视频（备份版 ）
 */
public class ZoneActivityVideoBack implements OnClickListener{

	private ZoneActivity act;
	private View root;
	
	
	private ViewPager viewpager;

	private List<View> imgViews;

	private ImageView videoBg;

	private VideoHelper videoHelper;
	
	private User user;
	
	public void release(){
		videoHelper.stop();
	}
	
	public void pause(){
		videoHelper.pause();
	}

	public ZoneActivityVideoBack(final ZoneActivity act, final View root) {
		this.act = act;
		this.root = root;

		
		user = TestDataUtil.getRandomUser();
		
		viewpager = (ViewPager) root.findViewById(R.id.viewpager);

		root.findViewById(R.id.defaultPlayBtn).setOnClickListener(this);

		videoBg = (ImageView) root.findViewById(R.id.videoBgImg);

		imgViews = new ArrayList<View>();

		final View h01 = act.createView(R.layout.video_nav_imgveiw);

		imgViews.add(h01);
		
		viewpager.setAdapter(new ViewPagerAdapter(imgViews));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		initPage1(h01);
		

		videoHelper = new VideoHelper(act, root);
		
		
		root.findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
		root.findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
		
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				root.findViewById(R.id.defaultPlayBtn).setOnClickListener(ZoneActivityVideoBack.this);
				
				View btn01 = h01.findViewById(R.id.videoBtn01);
				View btn02 = h01.findViewById(R.id.videoBtn02);
				View btn03 = h01.findViewById(R.id.videoBtn03);
				View btn04 = h01.findViewById(R.id.videoBtn04);

				ImageView img01 = (ImageView) btn01.findViewWithTag("img");
				ImageView img02 = (ImageView) btn02.findViewWithTag("img");
				ImageView img03 = (ImageView) btn03.findViewWithTag("img");
				ImageView img04 = (ImageView) btn04.findViewWithTag("img");

				String userId = user.getId();
				
				Bitmap b01 = act.readBitmapAutoSize(act.getTestPath()+userId+"_video_01.jpg", img01.getWidth(), img01.getHeight());
				Bitmap b02 = act.readBitmapAutoSize(act.getTestPath()+userId+"_video_02.jpg", img02.getWidth(), img02.getHeight());
				Bitmap b03 = act.readBitmapAutoSize(act.getTestPath()+userId+"_video_03.jpg", img03.getWidth(), img03.getHeight());
				Bitmap b04 = act.readBitmapAutoSize(act.getTestPath()+userId+"_video_04.jpg", img04.getWidth(), img04.getHeight());				
				
				img01.setImageBitmap(b01);
				img02.setImageBitmap(b02);
				img03.setImageBitmap(b03);
				img04.setImageBitmap(b04);

				btn01.setTag(act.getTestPath()+userId+"_video_01");
				btn02.setTag(act.getTestPath()+userId+"_video_02");
				btn03.setTag(act.getTestPath()+userId+"_video_03");
				btn04.setTag(act.getTestPath()+userId+"_video_04");

				btn01.setOnClickListener(ZoneActivityVideoBack.this);
				btn02.setOnClickListener(ZoneActivityVideoBack.this);
				btn03.setOnClickListener(ZoneActivityVideoBack.this);
				btn04.setOnClickListener(ZoneActivityVideoBack.this);

				Bitmap bg = act.readBitmapAutoSize(act.getTestPath()+userId+"_video_01.jpg", videoBg.getWidth()*2, videoBg.getHeight()*2);
				videoBg.setImageBitmap(bg);
				videoBg.setTag(act.getTestPath()+userId+"_video_01");
				viewpager.setCurrentItem(0);
				anim(btn01);
			}
		}, 600);
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

		img01.setImageBitmap(null);
		img02.setImageBitmap(null);
		img03.setImageBitmap(null);
		img04.setImageBitmap(null);

		btn01.setTag("intro_test_img_01.jpg");
		btn02.setTag("intro_test_img_02.jpg");
		btn03.setTag("intro_test_img_03.jpg");
		btn04.setTag("intro_test_img_04.jpg");

		viewpager.setCurrentItem(0);
		anim(btn01);
	}

	
	
	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.videoBtn01 || id == R.id.videoBtn02 || id == R.id.videoBtn03 || id == R.id.videoBtn04) {
			anim(view);
			Object path = view.getTag();
			Bitmap bitmap = act.readBitmapAutoSize(path.toString()+".jpg", videoBg.getWidth(), videoBg.getHeight());
			root.findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
			root.findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
			videoBg.setImageBitmap(bitmap);
			videoBg.setTag(path);
			videoHelper.pause();
		} else if (id == R.id.defaultPlayBtn) {
			String path = videoBg.getTag().toString()+".flv";
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
			String path = btn01.getTag().toString()+".jpg";
			Bitmap bitmap = act.readBitmapAutoSize(path, videoBg.getWidth(), videoBg.getHeight());
			root.findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
			root.findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
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
