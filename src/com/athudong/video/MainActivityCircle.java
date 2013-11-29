package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.ListCircleAdapter;
import com.athudong.video.bean.CircleLine;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * 娱乐圈界面操作
 */
public class MainActivityCircle implements OnClickListener {

	private MainActivity act;

	private View root;

	private ViewPager viewpager;

	private List<View> viewList;

	private View v01;
	private View v02;
	private View v03;

	public MainActivityCircle(MainActivity act, View root) {
		this.act = act;
		this.root = root;
		viewpager = (ViewPager) root.findViewById(R.id.circleviewpager);

		viewList = new ArrayList<View>();

		v01 = act.createView(R.layout.main_circle_01);
		v02 = act.createView(R.layout.main_circle_02);
		v03 = act.createView(R.layout.main_circle_02);

		viewList.add(v01);
		viewList.add(v02);
		viewList.add(v03);

		viewpager.setAdapter(new ViewPagerAdapter(viewList));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);

		root.findViewById(R.id.tab01).setOnClickListener(this);
		root.findViewById(R.id.tab02).setOnClickListener(this);
		root.findViewById(R.id.tab03).setOnClickListener(this);

		root.findViewById(R.id.tab01).setSelected(true);
		root.findViewById(R.id.tab02).setSelected(false);
		root.findViewById(R.id.tab03).setSelected(false);

		initTabOne();
		initTabTwo();
		initTabThree();
	}

	private void initTabTwo() {
		final PullToRefreshListView listView = (PullToRefreshListView) v02.findViewById(R.id.pull_refresh_list);

		List<CircleLine> list = new ArrayList<CircleLine>();

		for (int i = 0; i < 2; i++) {
			list.add(new CircleLine());
		}

		listView.setAdapter(new ListCircleAdapter(act, R.layout.list_main_circle_02_template, list));
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						listView.onRefreshComplete();
					}
				}, 2000);
			}
		});
	}

	private void initTabThree() {
		final PullToRefreshListView listView = (PullToRefreshListView) v03.findViewById(R.id.pull_refresh_list);

		List<CircleLine> list = new ArrayList<CircleLine>();

		for (int i = 0; i < 1; i++) {
			list.add(new CircleLine());
		}

		listView.setAdapter(new ListCircleAdapter(act, R.layout.list_main_circle_02_template, list));
		listView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						listView.onRefreshComplete();
					}
				}, 2000);
			}
		});
	}

	private void initTabOne() {
		LinearLayout contentLayout = (LinearLayout) v01.findViewById(R.id.contentLayout);

		for (int i = 0; i < 4; i++) {

			final View view = act.createView(R.layout.list_main_circle_01_template2);

			contentLayout.addView(view);
			
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(act, ZoneActivity.class);
					act.startActivity(intent);
				}
			});
			
			
			view.findViewById(R.id.imgLayout).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					final View longTv = view.findViewById(R.id.longTextLayout);
					view.findViewById(R.id.shortTextLayout).setVisibility(View.INVISIBLE);
					
					
					ObjectAnimator.ofFloat(longTv, "translationY", 2*longTv.getHeight(),0).start();
					longTv.setVisibility(View.VISIBLE);
					
					longTv.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							ObjectAnimator.ofFloat(longTv, "translationY", 0,2*longTv.getHeight()).start();
							longTv.setVisibility(View.INVISIBLE);
							view.findViewById(R.id.shortTextLayout).setVisibility(View.VISIBLE);
						}
					});
				}
			});
		}

	}


	@Override
	public void onClick(View v) {

		int id = v.getId();
		if (id == R.id.tab01) {
			viewpager.setCurrentItem(0, false);
		} else if (id == R.id.tab02) {
			viewpager.setCurrentItem(1, false);
		} else if (id == R.id.tab03) {
			viewpager.setCurrentItem(2, false);
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
			if (page == 0) {
				root.findViewById(R.id.tab01).setSelected(true);
				root.findViewById(R.id.tab02).setSelected(false);
				root.findViewById(R.id.tab03).setSelected(false);
			} else if (page == 1) {
				root.findViewById(R.id.tab01).setSelected(false);
				root.findViewById(R.id.tab02).setSelected(true);
				root.findViewById(R.id.tab03).setSelected(false);
			} else if (page == 2) {
				root.findViewById(R.id.tab01).setSelected(false);
				root.findViewById(R.id.tab02).setSelected(false);
				root.findViewById(R.id.tab03).setSelected(true);
			}
		}
	}
}
