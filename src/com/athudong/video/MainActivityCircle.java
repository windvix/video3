package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.ListCircleAdapter;
import com.athudong.video.bean.CircleLine;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class MainActivityCircle implements OnClickListener{

	private MainActivity act;
	
	private View root;
	
	
	private ViewPager viewpager;
	
	
	private List<View> viewList;
	
	private View v01;
	private View v02;
	
	public MainActivityCircle(MainActivity act, View root){
		this.act = act;
		this.root = root;
		viewpager  =(ViewPager)root.findViewById(R.id.circleviewpager);
		
		
		viewList = new ArrayList<View>();
		
		v01 = act.createView(R.layout.circle_01);
		v02 = act.createView(R.layout.circle_02);
		
		viewList.add(v01);
		viewList.add(v02);
		
		viewpager.setAdapter(new ViewPagerAdapter(viewList));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);
		
		root.findViewById(R.id.leftCircleBtn).setVisibility(View.INVISIBLE);
		
		root.findViewById(R.id.leftCircleBtn).setOnClickListener(this);
		root.findViewById(R.id.rightCircleBtn).setOnClickListener(this);
	
		initV01();
		initV02();
	}

	
	private PullToRefreshListView listView01;
	
	private void initV01(){
		listView01 = (PullToRefreshListView)v01.findViewById(R.id.pull_refresh_list);
		
		List<CircleLine> list = new ArrayList<CircleLine>();
		for(int i=0;i<10;i++){
			list.add(new CircleLine());
		}
		
		ListCircleAdapter adapter= new ListCircleAdapter(act, R.layout.list_star_template, list);
		
		listView01.setAdapter(adapter);
		
		listView01.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						listView01.onRefreshComplete();
					}
				}, 2000);
			}
		});
	}
	
	
	
	
	private PullToRefreshListView listView02;
	
	private void initV02(){
		listView02 = (PullToRefreshListView)v02.findViewById(R.id.pull_refresh_list);
		
		List<CircleLine> list = new ArrayList<CircleLine>();
		for(int i=0;i<10;i++){
			list.add(new CircleLine());
		}
		
		ListCircleAdapter adapter= new ListCircleAdapter(act, R.layout.list_star_template, list);
		
		listView02.setAdapter(adapter);
		
		listView02.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						listView02.onRefreshComplete();
					}
				}, 2000);
			}
		});
	}

	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		if(id==R.id.leftCircleBtn){
			viewpager.setCurrentItem(0);
		}else if(id==R.id.rightCircleBtn){
			viewpager.setCurrentItem(1);
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
			if(page==0){
				root.findViewById(R.id.leftCircleBtn).setVisibility(View.INVISIBLE);
				root.findViewById(R.id.rightCircleBtn).setVisibility(View.VISIBLE);
			}else if(page==1){
				root.findViewById(R.id.leftCircleBtn).setVisibility(View.VISIBLE);
				root.findViewById(R.id.rightCircleBtn).setVisibility(View.INVISIBLE);
			}
		}
	}
}
