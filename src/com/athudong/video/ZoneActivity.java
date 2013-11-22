package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.athudong.video.component.ImageViewTouchListener;
import com.athudong.video.task.BaseTask;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 个人空间界面
 */
public class ZoneActivity extends BaseActivity implements OnRefreshListener<ScrollView> {

	private PullToRefreshScrollView scrollView;

	private ViewPager viewpager = null;
	
	
	private List<View> tabViews = null;
	
	private List<View> tabIndexView = null;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_zone);
		findViewById(R.id.backBtn).setOnClickListener(this);
		scrollView = (PullToRefreshScrollView) findViewById(R.id.scrollview);
		scrollView.setOnRefreshListener(this);
		
		viewpager = (ViewPager)findViewById(R.id.viewpager);
		
		tabViews = new ArrayList<View>();
		tabViews.add(createView(R.layout.zone_pic));
		tabViews.add(createView(R.layout.zone_video));
		tabViews.add(createView(R.layout.zone_gift));
		
		tabIndexView = new ArrayList<View>();
		tabIndexView.add(findViewById(R.id.tab01));
		tabIndexView.add(findViewById(R.id.tab02));
		tabIndexView.add(findViewById(R.id.tab03));
		
		viewpager.setAdapter(new ViewPagerAdapter(tabViews));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());
		
		
		for(View tab:tabIndexView){
			tab.setOnClickListener(this);
		}
		
		viewpager.setCurrentItem(0);
		tabIndexView.get(0).setSelected(true);
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
		if (id == R.id.backBtn) {
			finish();
		}else if(id==R.id.tab01){
			viewpager.setCurrentItem(0,false);
		}else if(id==R.id.tab02){
			viewpager.setCurrentItem(1,false);
		}else if(id==R.id.tab03){
			viewpager.setCurrentItem(2,false);
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				scrollView.onRefreshComplete();
			}	
		}, 2000);
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
			for(int i=0;i<tabIndexView.size();i++){
				
				View view = tabIndexView.get(i);
				
				
				if(i==page){
					view.setSelected(true);
				}else{
					view.setSelected(false);
				}
				
				
			}
		}
	}
}
