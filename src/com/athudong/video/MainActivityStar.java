package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.ListRankAdapter;
import com.athudong.video.bean.Rank;
import com.athudong.video.bean.User;
import com.athudong.video.util.TestDataUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 明星界面操作
 */
public class MainActivityStar implements OnClickListener, OnItemClickListener {

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

		root.findViewById(R.id.tab01).setOnClickListener(this);
		root.findViewById(R.id.tab02).setOnClickListener(this);
		root.findViewById(R.id.tab01).setSelected(true);
		root.findViewById(R.id.tab02).setSelected(false);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				initRank01();
				initRank02();
			}
		}, 1000);

	}

	private ListView listView01;

	private List<Rank> rankList01;

	/**
	 * 初始化排行榜一
	 */
	private void initRank01() {
		final PullToRefreshListView tempListview = (PullToRefreshListView) v01.findViewById(R.id.pull_refresh_list);

		listView01 = tempListview.getRefreshableView();

		rankList01 = new ArrayList<Rank>();

		List<User> users01 = TestDataUtil.getAllUsers();

		String path = act.getTestPath();
		String head = "_head.jpg";

		Rank r01 = new Rank(1, path + users01.get(01).getId() + head, users01.get(01).getName(), "15,396", users01.get(01).getSaying(), users01.get(01).getId());
		Rank r02 = new Rank(2, path + users01.get(02).getId() + head, users01.get(02).getName(), "14,000", users01.get(02).getSaying(), users01.get(02).getId());
		Rank r03 = new Rank(3, path + users01.get(03).getId() + head, users01.get(03).getName(), "10,845", users01.get(03).getSaying(), users01.get(03).getId());
		Rank r04 = new Rank(4, path + users01.get(04).getId() + head, users01.get(04).getName(), "9,566", users01.get(04).getSaying(), users01.get(04).getId());
		Rank r05 = new Rank(5, path + users01.get(05).getId() + head, users01.get(05).getName(), "9,423", users01.get(05).getSaying(), users01.get(05).getId());
		Rank r06 = new Rank(6, path + users01.get(06).getId() + head, users01.get(06).getName(), "5,226", users01.get(06).getSaying(), users01.get(06).getId());
		Rank r07 = new Rank(7, path + users01.get(07).getId() + head, users01.get(07).getName(), "4,123", users01.get(07).getSaying(), users01.get(07).getId());
		Rank r08 = new Rank(8, path + users01.get(8).getId() + head, users01.get(8).getName(), "3,564", users01.get(8).getSaying(), users01.get(8).getId());
		Rank r09 = new Rank(9, path + users01.get(9).getId() + head, users01.get(9).getName(), "1,396", users01.get(9).getSaying(), users01.get(9).getId());
		Rank r10 = new Rank(10, path + users01.get(0).getId() + head, users01.get(0).getName(), "875", users01.get(0).getSaying(), users01.get(0).getId());

		rankList01.add(r01);
		rankList01.add(r02);
		rankList01.add(r03);
		rankList01.add(r04);
		rankList01.add(r05);
		rankList01.add(r06);
		rankList01.add(r07);
		rankList01.add(r08);
		rankList01.add(r09);
		rankList01.add(r10);

		final ListRankAdapter adapter = new ListRankAdapter(act, rankList01, ListRankAdapter.TYPE_01);

		act.registerForContextMenu(listView01);
		listView01.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		tempListview.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						tempListview.onRefreshComplete();
						adapter.notifyDataSetChanged();
					}
				}, 2000);
			}
		});

		tempListview.setOnItemClickListener(this);
	}

	private PullToRefreshListView listView02;
	private List<Rank> rankList02;

	/**
	 * 初始化排行榜二
	 */
	private void initRank02() {
		listView02 = (PullToRefreshListView) v02.findViewById(R.id.pull_refresh_list);

		rankList02 = new ArrayList<Rank>();
		List<User> users01 = TestDataUtil.getAllUsers();

		String path = act.getTestPath();
		String head = "_head.jpg";

		Rank r01 = new Rank(1, path + users01.get(9).getId() + head, users01.get(9).getName(), "15,396", users01.get(9).getSaying(), users01.get(9).getId());
		Rank r02 = new Rank(2, path + users01.get(8).getId() + head, users01.get(8).getName(), "14,000", users01.get(8).getSaying(), users01.get(8).getId());
		Rank r03 = new Rank(3, path + users01.get(7).getId() + head, users01.get(7).getName(), "10,845", users01.get(7).getSaying(), users01.get(7).getId());
		Rank r04 = new Rank(4, path + users01.get(06).getId() + head, users01.get(6).getName(), "9,566", users01.get(6).getSaying(), users01.get(6).getId());
		Rank r05 = new Rank(5, path + users01.get(05).getId() + head, users01.get(5).getName(), "9,423", users01.get(5).getSaying(), users01.get(5).getId());
		Rank r06 = new Rank(6, path + users01.get(04).getId() + head, users01.get(4).getName(), "5,226", users01.get(4).getSaying(), users01.get(4).getId());
		Rank r07 = new Rank(7, path + users01.get(03).getId() + head, users01.get(3).getName(), "4,123", users01.get(3).getSaying(), users01.get(3).getId());
		Rank r08 = new Rank(8, path + users01.get(2).getId() + head, users01.get(2).getName(), "3,564", users01.get(2).getSaying(), users01.get(2).getId());
		Rank r09 = new Rank(9, path + users01.get(1).getId() + head, users01.get(1).getName(), "1,396", users01.get(1).getSaying(), users01.get(1).getId());
		Rank r10 = new Rank(10, path + users01.get(0).getId() + head, users01.get(0).getName(), "875", users01.get(0).getSaying(), users01.get(0).getId());

		rankList02.add(r01);
		rankList02.add(r02);
		rankList02.add(r03);
		rankList02.add(r04);
		rankList02.add(r05);
		rankList02.add(r06);
		rankList02.add(r07);
		rankList02.add(r08);
		rankList02.add(r09);
		rankList02.add(r10);
		
		final ListRankAdapter adapter = new ListRankAdapter(act, rankList02, ListRankAdapter.TYPE_02);

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

		listView02.setOnItemClickListener(this);
	}

	@Override
	public void onClick(View v) {

		int id = v.getId();
		if (id == R.id.tab01) {
			viewpager.setCurrentItem(0, false);
		} else if (id == R.id.tab02) {
			viewpager.setCurrentItem(1, false);
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
				root.findViewById(R.id.msgLayout).setVisibility(View.VISIBLE);
			} else if (page == 1) {
				root.findViewById(R.id.tab02).setSelected(true);
				root.findViewById(R.id.tab01).setSelected(false);
				root.findViewById(R.id.msgLayout).setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(act, ZoneActivity.class);
		String userId = "01";
		Rank rank = null;
		if (viewpager.getCurrentItem() == 0) {
			rank = rankList01.get(position - 1);
		} else {
			rank = rankList02.get(position - 1);
		}
		userId = rank.getId();
		intent.putExtra("id", userId);
		act.startActivity(intent);
	}
}
