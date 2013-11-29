package com.athudong.video.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * viewPager  ≈‰∆˜
 * @author –ª∆ÙœÈ
 */
public class ViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> views;
	
	public void setViews(ArrayList<View> views) {
		this.views = views;
	}

	@Override
	public int getCount() {
		if(views==null){
			return 0;
		}
		return views.size();
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(views.get(position));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(views.get(position),0);
		return views.get(position);
	}

}
