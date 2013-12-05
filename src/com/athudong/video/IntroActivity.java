package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.athudong.video.bean.User;
import com.athudong.video.component.ImageViewTouchListener;
import com.athudong.video.task.BaseTask;
import com.athudong.video.util.TestDataUtil;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 个人资料界面（主要展示个人图片）
 */
public class IntroActivity extends BaseActivity {

	private ImageView intrImg;

	private ImageViewTouchListener touch;

	private TextView sayingTv;

	private ViewPager viewpager;

	private List<View> imgViews;
	
	private TextView titleTv;
	private TextView popularTv;
	private TextView fansTv;
	
	private User user = null;
	

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_intro);
		
		String id = getIntent().getStringExtra("id");
		user = TestDataUtil.getUserById(id);
		

		sayingTv = (TextView) findViewById(R.id.introSayingTv);
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		findViewById(R.id.backBtn).setOnClickListener(this);
		intrImg = (ImageView) findViewById(R.id.introImg);
		titleTv = (TextView)findViewById(R.id.titleTv);
		popularTv = (TextView)findViewById(R.id.popularTv);
		fansTv = (TextView)findViewById(R.id.fansTv);

		
		sayingTv.setText(user.getSaying());
		titleTv.setText(user.getName());
		popularTv.setText(user.getPopular()+"");
		fansTv.setText(user.getFans()+"");
		
		
		
		
		
		imgViews = new ArrayList<View>();
		
		
		for(int i=0;i<user.getImgCount();i++){
			View head = createView(R.layout.intro_nav_imgveiw);
			imgViews.add(head);
		}
		
		viewpager.setAdapter(new ViewPagerAdapter(imgViews));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());
		viewpager.setOffscreenPageLimit(5);
		viewpager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.intro_img_nav_margin));
		//viewpager.setCurrentItem(imgViews.size() / 2);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				for(int i=0;i<user.getImgCount();i++){
					View oneHead = imgViews.get(i);
					ImageView img = (ImageView) oneHead.findViewById(R.id.intro_headimg);
					String imgPath = getTestPath()+user.getId()+"_"+"0"+(i+1)+".jpg";
					
					Bitmap bitmap = readBitmapAutoSize(imgPath, img.getWidth(), img.getHeight());
					
					
					img.setImageBitmap(bitmap);
					
					
					oneHead.setTag(imgPath);
					touch = new ImageViewTouchListener(IntroActivity.this) {
						@Override
						public void touchUp() {

						}
					};
				}
				viewpager.setCurrentItem(imgViews.size() / 2);
			}
		}, 600);

		initImgViewsClick();

		findViewById(R.id.addBtn).setOnClickListener(this);
		findViewById(R.id.favBtn).setOnClickListener(this);
		
		
		
		initStarLevel();
	}

	private void initStarLevel(){
		ImageView star01 = (ImageView)findViewById(R.id.star01);
		ImageView star02 = (ImageView)findViewById(R.id.star02);
		ImageView star03 = (ImageView)findViewById(R.id.star03);
		ImageView star04 = (ImageView)findViewById(R.id.star04);
		ImageView star05 = (ImageView)findViewById(R.id.star05);
		
	}
	
	
	private void initImgViewsClick() {
		for (int i = 0; i < imgViews.size(); i++) {
			View view = imgViews.get(i);
			final int index = i;
			view.findViewById(R.id.intro_headimg).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					int cur = viewpager.getCurrentItem();
					if (cur != index) {
						viewpager.setCurrentItem(index);
					}
				}
			});
		}
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

	private boolean isAdd = false;

	private boolean isFav = false;

	@Override
	public void onClick(View view) {
		int id = view.getId();
		if (id == R.id.backBtn) {
			finish();
		} else if (id == R.id.addBtn) {
			if (isAdd) {
				isAdd = false;
				ObjectAnimator.ofFloat(view, "rotation", 45f, 0).start();

				toast("取消关注");
			} else {
				ObjectAnimator.ofFloat(view, "rotation", 0, 45f).start();
				isAdd = true;
				toast("关注");
			}
		} else if (id == R.id.favBtn) {
			ImageView favIcon = (ImageView) view.findViewWithTag("img");
			TextView numTv = (TextView)view.findViewWithTag("num");
			
			if (!isFav) {
				
				favIcon.setImageResource(R.drawable.icon_fav);
				numTv.setText((Integer.parseInt(numTv.getText().toString())+1)+"");
				isFav = true;
			}else{
				
				favIcon.setImageResource(R.drawable.icon_fav_gray);
				numTv.setText((Integer.parseInt(numTv.getText().toString())-1)+"");
				
				isFav = false;
			}
		}
	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {

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
			for (int i = 0; i < imgViews.size(); i++) {
				View view = imgViews.get(i);

				if (page == i) {

					String path = view.getTag().toString();

					touch = new ImageViewTouchListener(IntroActivity.this) {
						@Override
						public void touchUp() {

						}
					};
					intrImg.setImageBitmap(null);
					Bitmap bitmap = readBitmapAutoSize(path, (int)intrImg.getWidth()*2, intrImg.getHeight()*2);
					touch.initMatrix(intrImg, bitmap);

					ObjectAnimator.ofFloat(view, "scaleX", 1, 1.3f).start();
					ObjectAnimator.ofFloat(view, "scaleY", 1, 1.3f).start();
				} else {
					ObjectAnimator.ofFloat(view, "scaleX", 1, 1f).start();
					ObjectAnimator.ofFloat(view, "scaleY", 1, 1f).start();
				}
			}
		}
	}
}
