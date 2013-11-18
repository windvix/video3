package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.ListRankAdapter;
import com.athudong.video.bean.Rank;
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

public class MainActivityStar implements OnClickListener {

	private MainActivity act;

	private View root;

	private ViewPager viewpager;

	private List<View> viewList;

	private View v01;
	private View v02;

	public MainActivityStar(MainActivity act, View root) {
		this.act = act;
		this.root = root;
		viewpager = (ViewPager) root.findViewById(R.id.starviewpager);

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

		initRank01();
		initRank02();
	}

	private PullToRefreshListView listView01;

	private void initRank01() {
		listView01 = (PullToRefreshListView) v01.findViewById(R.id.pull_refresh_list);

		List<Rank> list = new ArrayList<Rank>();
		
		Rank r01 = new Rank(1, R.drawable.rank_head_01+"", "朱玲玲", "15,396");
		Rank r02 = new Rank(2, R.drawable.rank_head_02+"", "李倩倩", "14,000");
		Rank r03 = new Rank(3, R.drawable.rank_head_03+"", "Belly", "10,845");
		Rank r04 = new Rank(4, R.drawable.rank_head_04+"", "温嘉宝", "9,566");
		Rank r05 = new Rank(5, R.drawable.rank_head_05+"", "梁亦", "9,423");
		Rank r06 = new Rank(6, R.drawable.rank_head_06+"", "Candy", "5,226");
		Rank r07 = new Rank(7, R.drawable.rank_head_07+"", "张可", "4,123");
		Rank r08 = new Rank(8, R.drawable.rank_head_08+"", "南南", "3,564");
		Rank r09 = new Rank(9, R.drawable.rank_head_09+"", "孙小利", "1,396");
		Rank r10 = new Rank(10, R.drawable.rank_head_01+"", "黄朋仔", "875");
		
		
		list.add(r01);
		list.add(r02);
		list.add(r03);
		list.add(r04);
		list.add(r05);
		list.add(r06);
		list.add(r07);
		list.add(r08);
		list.add(r09);
		list.add(r10);
		
		
		ListRankAdapter adapter = new ListRankAdapter(act, R.layout.list_rank_template, list);

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

	private void initRank02() {
		listView02 = (PullToRefreshListView) v02.findViewById(R.id.pull_refresh_list);

		List<Rank> list = new ArrayList<Rank>();
		
		Rank r01 = new Rank(1, R.drawable.rank_head_09+"", "陈家怡", "15,396");
		Rank r02 = new Rank(2, R.drawable.rank_head_08+"", "刘小明", "14,000");
		Rank r03 = new Rank(3, R.drawable.rank_head_07+"", "Belly", "10,845");
		Rank r04 = new Rank(4, R.drawable.rank_head_06+"", "阿Li", "9,566");
		Rank r05 = new Rank(5, R.drawable.rank_head_05+"", "梁亦", "9,423");
		Rank r06 = new Rank(6, R.drawable.rank_head_04+"", "Candy", "5,226");
		Rank r07 = new Rank(7, R.drawable.rank_head_03+"", "Gogo", "4,123");
		Rank r08 = new Rank(8, R.drawable.rank_head_02+"", "卖火柴的明星", "3,564");
		Rank r09 = new Rank(9, R.drawable.rank_head_01+"", "小贫友", "1,396");
		Rank r10 = new Rank(10, R.drawable.rank_head_09+"", "阿宝", "875");
		
		
		list.add(r01);
		list.add(r02);
		list.add(r03);
		list.add(r04);
		list.add(r05);
		list.add(r06);
		list.add(r07);
		list.add(r08);
		list.add(r09);
		list.add(r10);
		
		
		ListRankAdapter adapter = new ListRankAdapter(act, R.layout.list_rank_template2, list);

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
		if (id == R.id.leftRankBtn) {
			viewpager.setCurrentItem(0);
		} else if (id == R.id.rightRankBtn) {
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
			if (page == 0) {
				root.findViewById(R.id.leftRankBtn).setVisibility(View.INVISIBLE);
				root.findViewById(R.id.rightRankBtn).setVisibility(View.VISIBLE);
			} else if (page == 1) {
				root.findViewById(R.id.leftRankBtn).setVisibility(View.VISIBLE);
				root.findViewById(R.id.rightRankBtn).setVisibility(View.INVISIBLE);
			}
		}
	}
}
