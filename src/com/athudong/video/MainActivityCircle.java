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
 * 主界面第三个tab:娱乐圈界面操作
 */
public class MainActivityCircle implements OnClickListener {

	private MainActivity act;

	private View root;

	private ViewPager viewpager;

	private List<View> viewList;

	
	/**
	 * 第一个tab：明星动态
	 */
	private View v01;
	
	/**
	 * 第二个tab:我喜欢的明星
	 */
	private View v02;
	
	/**
	 * 第三个tab:我关注的明星
	 */
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

		
		//
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				initTabOne();
				initTabTwo();
				initTabThree();
			}
		}, 1500);
	}

	/**
	 * 初始化第地个tab（我喜欢的明星）
	 */
	private void initTabTwo() {
		final PullToRefreshListView listView = (PullToRefreshListView) v02.findViewById(R.id.pull_refresh_list);

		List<CircleLine> list = new ArrayList<CircleLine>();

		
		/**
		 * 创建出6个测试的用户
		 */
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
		
		/**
		 * listView加上下拉刷新事件
		 */
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

	/**
	 * 初始化第三个tab：我关注的明星
	 */
	private void initTabThree() {
		final PullToRefreshListView listView = (PullToRefreshListView) v03.findViewById(R.id.pull_refresh_list);

		List<CircleLine> list = new ArrayList<CircleLine>();

		/**
		 * 
		 * 创建出3个测试的明星
		 */
		List<User> users = TestDataUtil.getAllUsers();
		
		User u01 = users.get(2);
		User u02 = users.get(3);
		User u03 = users.get(4);
		
		
		String hd = "_head.jpg";
		list.add(new CircleLine(u01.getId(), u01.getName(), act.getTestPath()+u01.getId()+hd, u02.getId(), u02.getName(), act.getTestPath()+u02.getId()+hd,u03.getId(), u03.getName(), act.getTestPath()+u03.getId()+hd));
		
		listView.setAdapter(new ListCircleAdapter(act, R.layout.list_main_circle_02_template, list));
		
		/**
		 * listView加上下拉刷新事件
		 */
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

	/**
	 * 初始化第一个tab（明星动态）
	 */
	private void initTabOne() {
		LinearLayout contentLayout = (LinearLayout) v01.findViewById(R.id.contentLayout);

		List<User> users = TestDataUtil.getAllUsers();
		
		/**
		 * 创建四个测试明星
		 */
		for (int i = 2; i < 6; i++) {
			
			final User user = users.get(i);
			
			//本页使用的是ScrollView，所以每个列表项都要手动创建出来
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
			
			//设置动态的详细内容
			longContentTv.setText(content);
			
			//截取部分动态内容
			if(content.length()>18){
				content = content.subSequence(0, 19)+"...";
			}
			
			//设置动态的简略内容
			shortContentTv.setText(content);
			
			//设置用户的头像
			Bitmap headBm = act.readBitmapAutoSize(act.getTestPath()+user.getId()+"_head.jpg", headImg.getWidth(), headImg.getHeight());
			headImg.setImageBitmap(headBm);
			
			/**
			 * 设置动态内容的图片
			 */
			Bitmap contentBm = act.readBitmapAutoSize(act.getTestPath()+user.getId()+"_02.jpg", contentImg.getWidth(), contentImg.getHeight());
			contentImg.setImageBitmap(contentBm);
			
			//设置明星的名称
			nameTv.setText(user.getName()+"");
			
			//设置用户的动态有多少人喜欢（单机版假的，随便找个数字填进去 ）
			favCountTv.setText(user.getFans()+"");
			
			//设置用户动态有多少人评论（单机版假的，随便找个数字填进去 ）
			commentCountTv.setText(user.getPopular()+"");
			
			/**
			 * 设置动态的发表时间，单机版，做几个假的时间
			 */
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
			
			//将一条动态加入到scrollView中
			contentLayout.addView(view);
			
			//点击一条动态，就打开对应用户的个人空间界面
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(act, ZoneActivity.class);
					intent.putExtra("id", user.getId());
					act.startActivity(intent);
				}
			});
			
			
			/**
			 * 每条动态都有一张图片，点击每条动态上面的图片，就会打开这条动态的详细内容
			 */
			view.findViewById(R.id.imgLayout).setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					/**
					 * 动态的详细内容从下往上升直到盖完整个框，动态的简略内容变成不可见
					 * 动态的详细出现的时。点击它就会下降，最后消息 ，动态的简略内容再次显示
					 */
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
