package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.athudong.video.task.BaseTask;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity{

	private ViewPager viewpager;
	
	private List<View> list;
	
	/**
	 * 发现界面
	 */
	private View v01;
	
	/**
	 * 排行榜界面
	 */
	private View v02;
	
	/**
	 * 娱乐圈界面
	 */
	private View v03;
	
	/**
	 * 关于我的界面
	 */
	private View v04;
	
	
	/**
	 * 主界面底部的四个tab
	 */
	private List<View>  bottomBtnList;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		viewpager = (ViewPager)findViewById(R.id.viewpager);
		
		list = new ArrayList<View>();
		bottomBtnList = new ArrayList<View>();
		
		v01 = createView(R.layout.main_view_01);
		v02 = createView(R.layout.main_view_02);
		v03 = createView(R.layout.main_view_03);
		v04 = createView(R.layout.main_view_04);
		
		bottomBtnList.add(findViewById(R.id.bottomBtn01));
		bottomBtnList.add(findViewById(R.id.bottomBtn02));
		bottomBtnList.add(findViewById(R.id.bottomBtn03));
		bottomBtnList.add(findViewById(R.id.bottomBtn04));
		
		for(View v:bottomBtnList){
			v.setOnClickListener(this);
		}
		
		list.add(v01);
		list.add(v02);
		list.add(v03);
		list.add(v04);
		
		viewpager.setAdapter(new ViewPagerAdapter(list));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(0);
		
		findViewById(R.id.bottomBtn01).setSelected(true);
		
		/**
		 * 主界面包括四页，将四页分发至四个类处理逻辑
		 */
		new MainActivityDiscover(this, v01);
		new MainActivityStar(this, v02);
		new MainActivityCircle(this, v03);
		new MainActivityMe(this, v04);
		
		
		/**
		 * 系统默认进入的不是主界面，而是SelectFirstActivity,释放SelectFirstActivity的资源
		 */
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				SelectFirstActivity.self.releaseView();
			}
		}, 2000);
		
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
	public void onClick(View view) {
		int id = view.getId();
		
		//底部发现tab
		if(id==R.id.bottomBtn01){
			viewpager.setCurrentItem(0,false);
		}
		//底部排行榜tab
		else if(id==R.id.bottomBtn02){
			viewpager.setCurrentItem(1,false);
		}
		//底部娱乐圈tab
		else if(id==R.id.bottomBtn03){
			viewpager.setCurrentItem(2,false);
		}
		//底部我tab
		else if(id==R.id.bottomBtn04){
			viewpager.setCurrentItem(3,false);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		//再按一次退出系统
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			ToQuitTheApp();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	private boolean isExit = false;

	// 退出程序
	private void ToQuitTheApp() {
		if (isExit) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					MainActivity.this.finish();
					if(SelectFirstActivity.self!=null){
						SelectFirstActivity.self.finish();
					}
					// 使虚拟机停止运行并退出程序
					System.exit(0);
				}
			}, 1500);
		} else {
			isExit = true;
			toast("再按一次退出");
			mHandler.sendEmptyMessageDelayed(0, 3000);// 3秒后发送消息
		}
	}

	// 创建Handler对象，用来处理消息
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// 处理消息
			super.handleMessage(msg);
			isExit = false;
		}
	};
	
	
	
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
			//bottom tab选中之后颜色改变
			for(int i=0;i<bottomBtnList.size();i++){
				if(page==i){
					bottomBtnList.get(i).setSelected(true);
				}else{
					bottomBtnList.get(i).setSelected(false);
				}
			}
		}
	}
}
