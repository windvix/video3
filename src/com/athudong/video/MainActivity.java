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

public class MainActivity extends BaseActivity{

	private ViewPager viewpager;
	
	private List<View> list;
	
	private View v01;
	private View v02;
	private View v03;
	private View v04;
	
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
		
		if(id==R.id.bottomBtn01){
			viewpager.setCurrentItem(0);
		}else if(id==R.id.bottomBtn02){
			viewpager.setCurrentItem(1);
		}else if(id==R.id.bottomBtn03){
			viewpager.setCurrentItem(2);
		}else if(id==R.id.bottomBtn04){
			viewpager.setCurrentItem(3);
		}
	}
	

	
	// 重写Activity中onKeyDown（）方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 当keyCode等于退出事件值时
			ToQuitTheApp();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	private boolean isExit = false;

	// 封装ToQuitTheApp方法
	private void ToQuitTheApp() {
		if (isExit) {
			// ACTION_MAIN with category CATEGORY_HOME 启动主屏幕
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					MainActivity.this.finish();
					System.exit(0);// 使虚拟机停止运行并退出程序
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
