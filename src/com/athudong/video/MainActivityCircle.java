package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.adapter.ListCircleAdapter;
import com.athudong.video.bean.CircleLine;
import com.athudong.video.bean.User;
import com.athudong.video.util.TestDataUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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

		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				initTabOne();
				initTabTwo();
				initTabThree();
			}
		}, 1500);
	}

	private void initTabTwo() {
		final PullToRefreshListView listView = (PullToRefreshListView) v02.findViewById(R.id.pull_refresh_list);

		List<CircleLine> list = new ArrayList<CircleLine>();

		
		List<User> users = TestDataUtil.getAllUsers();
		
		User u01 = users.get(5);
		User u02 = users.get(6);
		User u03 = users.get(7);
		User u04 = users.get(8);
		User u05 = users.get(9);
		User u06 = users.get(4);
		
		
		String hd = "_head.jpg";
		list.add(new CircleLine(u04.getId(), u04.getName(), act.getTestPath()+u04.getId()+hd, u05.getId(), u05.getName(), act.getTestPath()+u05.getId()+hd,u06.getId(), u06.getName(), act.getTestPath()+u06.getId()+hd));
		list.add(new CircleLine(u03.getId(), u03.getName(), act.getTestPath()+u03.getId()+hd,u01.getId(), u01.getName(), act.getTestPath()+u01.getId()+hd,u02.getId(), u02.getName(), act.getTestPath()+u02.getId()+hd));

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

		
		List<User> users = TestDataUtil.getAllUsers();
		
		User u01 = users.get(2);
		User u02 = users.get(3);
		User u03 = users.get(4);
		
		
		String hd = "_head.jpg";
		list.add(new CircleLine(u01.getId(), u01.getName(), act.getTestPath()+u01.getId()+hd, u02.getId(), u02.getName(), act.getTestPath()+u02.getId()+hd,u03.getId(), u03.getName(), act.getTestPath()+u03.getId()+hd));
		
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

		List<User> users = TestDataUtil.getAllUsers();
		
		for (int i = 2; i < 6; i++) {

			final User user = users.get(i);
			
			final View view = act.createView(R.layout.list_main_circle_01_template2);
			
			TextView shortContentTv = (TextView)view.findViewById(R.id.shortContentTv);
			TextView longContentTv = (TextView)view.findViewById(R.id.longContentTv);
			ImageView headImg = (ImageView)view.findViewById(R.id.headImg);
			TextView nameTv = (TextView)view.findViewById(R.id.nameTv);
			TextView timeTv = (TextView)view.findViewById(R.id.timeTv);
			TextView favCountTv  = (TextView)view.findViewById(R.id.favCountTv);
			TextView commentCountTv = (TextView)view.findViewById(R.id.commentCountTv);
			ImageView contentImg = (ImageView)view.findViewById(R.id.contentImg);
			
			
			
			String content  = user.getSaying();
			longContentTv.setText(content);
			if(content.length()>18){
				content = content.subSequence(0, 19)+"...";
			}
			shortContentTv.setText(content);
			
			Bitmap headBm = act.readBitmapAutoSize(act.getTestPath()+user.getId()+"_head.jpg", headImg.getWidth(), headImg.getHeight());
			
			headImg.setImageBitmap(headBm);
			
			Bitmap contentBm = act.readBitmapAutoSize(act.getTestPath()+user.getId()+"_02.jpg", contentImg.getWidth(), contentImg.getHeight());
			
			contentImg.setImageBitmap(contentBm);
			nameTv.setText(user.getName()+"");
			favCountTv.setText(user.getFans()+"");
			commentCountTv.setText(user.getPopular()+"");
			
			String tm = "30分钟前";
			if(i==2){
				tm = "2小时之前";
			}else if(i==3){
				tm = "昨天18:45";
			}else if(i==4){
				tm = "昨天09:04";
			}else if(i==5){
				tm = "前天14:03";
			}
			timeTv.setText(tm);
			
			contentLayout.addView(view);
			
			view.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(act, ZoneActivity.class);
					intent.putExtra("id", user.getId());
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
