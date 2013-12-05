package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.task.BaseTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ShopActivity extends BaseActivity {
	private ViewPager viewPager;
	private LayoutInflater inflater;
	private List<View> views;
	private TextView leftTv, rightTv;

	private View goods01;
	private View goods02;
	private View goods03;
	private View goods04;
	private View goods05;
	private View goods06;
	

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_shop);
		leftTv = (TextView) findViewById(R.id.start_store_left_select);
		rightTv = (TextView) findViewById(R.id.start_store_right_select);
		// 初始化ViewPager
		viewPager = (ViewPager) findViewById(R.id.start_store_viewpager);
		inflater = LayoutInflater.from(this);
		View storeView = inflater.inflate(R.layout.viewpager_item_scene_store, null);
		View goodsView = inflater.inflate(R.layout.viewpager_item_my_goods, null);
		views = new ArrayList<View>();
		views.add(storeView);
		views.add(goodsView);
		ViewPagerAdapter adapter = new ViewPagerAdapter(views);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		leftTv.setSelected(true);
		rightTv.setSelected(false);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0) {
					leftTv.setSelected(true);
					rightTv.setSelected(false);
				} else {
					leftTv.setSelected(false);
					rightTv.setSelected(true);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		// 为控件设置监听器
		findViewById(R.id.start_store_back_btn).setOnClickListener(this);
		findViewById(R.id.start_store_left_select).setOnClickListener(this);
		findViewById(R.id.start_store_right_select).setOnClickListener(this);

		goods01 = storeView.findViewById(R.id.scene_store_goods1);
		
		goods02 = storeView.findViewById(R.id.scene_store_goods2);
		goods03 = storeView.findViewById(R.id.scene_store_goods3);
		goods04 = storeView.findViewById(R.id.scene_store_goods4);
		goods05 = storeView.findViewById(R.id.scene_store_goods5);
		goods06 = storeView.findViewById(R.id.scene_store_goods6);
		
		

		
		
		initGoods(R.drawable.icon_gift_locator, "定位搜索器", goods01);
		initGoods(R.drawable.icon_gift_voicer, "活动通知器", goods02);
		initGoods(R.drawable.icon_gift_rose, "玫瑰", goods03);
		initGoods(R.drawable.icon_gift_rose_3, "玫瑰(3支)", goods04);
		initGoods(R.drawable.icon_gift_flower, "优雅花束", goods05);
		initGoods(R.drawable.icon_gift_basketer, "高档花篮", goods06);
		
		goods01.setOnClickListener(this);
		goods02.setOnClickListener(this);
		goods03.setOnClickListener(this);
		goods04.setOnClickListener(this);
		goods05.setOnClickListener(this);
		goods06.setOnClickListener(this);
	}

	
	private void initGoods(int resId, String name, View goods){
		ImageView imgIv = (ImageView)goods.findViewWithTag("img");
		TextView nameTv = (TextView)goods.findViewWithTag("name");
		imgIv.setImageResource(resId);
		nameTv.setText(name);
		goods.setTag(resId);
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
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.start_store_back_btn){
			finish();
		}else if(id==R.id.start_store_left_select){
			// 道具商城
			viewPager.setCurrentItem(0);
			leftTv.setSelected(true);
			rightTv.setSelected(false);
		}else if(id==R.id.start_store_right_select){
			// 我的物品
			viewPager.setCurrentItem(1);
			leftTv.setSelected(false);
			rightTv.setSelected(true);
		}else {
			Intent intent = new Intent(this, ShopItemActivity.class);
			TextView nameTv = (TextView)v.findViewWithTag("name");
			int resId = Integer.parseInt(v.getTag().toString());
			String name = nameTv.getText().toString();
			intent.putExtra("resId", resId);
			intent.putExtra("name", name);
			startActivity(intent);
		}
		
	}
	
	
	private class ViewPagerAdapter extends PagerAdapter {
		private List<View> list = null;

		public ViewPagerAdapter(List<View> list) {
			if(list != null){
				this.list = list;
			}else{
				this.list = new ArrayList<View>();
			}
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

}
