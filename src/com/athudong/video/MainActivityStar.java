package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.MainActivity.ViewPagerAdapter;
import com.athudong.video.MainActivity.ViewPagerPageChangeListener;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

public class MainActivityStar implements OnClickListener{

	private MainActivity act;
	
	private View root;
	
	
	private ViewPager viewpager;
	
	
	private List<View> viewList;
	
	private View v01;
	private View v02;
	
	public MainActivityStar(MainActivity act, View root){
		this.act = act;
		this.root = root;
		viewpager  =(ViewPager)root.findViewById(R.id.starviewpager);
		
		
		viewList = new ArrayList<View>();
		
		v01 = act.createView(R.layout.rank_star_01);
		v02 = act.createView(R.layout.rank_star_02);
		
		viewList.add(v01);
		viewList.add(v02);
		
		viewpager.setAdapter(new ViewPagerAdapter(viewList));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);
		
		root.findViewById(R.id.leftRankBtn).setVisibility(View.INVISIBLE);
		
		root.findViewById(R.id.leftRankBtn).setOnClickListener(this);
		root.findViewById(R.id.rightRankBtn).setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		
		int id = v.getId();
		if(id==R.id.leftRankBtn){
			viewpager.setCurrentItem(0);
		}else if(id==R.id.rightRankBtn){
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
				root.findViewById(R.id.leftRankBtn).setVisibility(View.INVISIBLE);
				root.findViewById(R.id.rightRankBtn).setVisibility(View.VISIBLE);
			}else if(page==1){
				root.findViewById(R.id.leftRankBtn).setVisibility(View.VISIBLE);
				root.findViewById(R.id.rightRankBtn).setVisibility(View.INVISIBLE);
			}
		}
	}
}
