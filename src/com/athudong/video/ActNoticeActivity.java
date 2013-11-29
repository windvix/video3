package com.athudong.video;

import java.util.ArrayList;
import com.athudong.video.R;
import com.athudong.video.adapter.UEAdap;
import com.athudong.video.adapter.ViewPagerAdapter;
import com.athudong.video.task.BaseTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;


/**
 * 活动主界面
 */
public class ActNoticeActivity extends BaseActivity {

	private ViewPager vp;
	private Button btnLeft, btnRight;
	private UEAdap adapter;
	private ViewPagerAdapter vpAdap;
	private ListView listView, listView2;
	private ArrayList<View> viewList;


	private void initListener() {
		vp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				if (arg0 == 0) {
					btnLeft.setSelected(true);
					btnRight.setSelected(false);
				} else {
					btnLeft.setSelected(false);
					btnRight.setSelected(true);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Intent intent = new Intent(ActNoticeActivity.this,ActDetailActivity.class);
				ActNoticeActivity.this.startActivity(intent);
			}
		});
		
		listView2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ActNoticeActivity.this,ActDetailActivity.class);
				ActNoticeActivity.this.startActivity(intent);
			}
		});
	}

	private void initData() {

	}

	public void controlClick(View view) {
		switch (view.getId()) {
		case R.id.title_bar_left:
			this.finish();
			break;
		case R.id.uem_all_events:
			vp.setCurrentItem(0);
			btnLeft.setSelected(true);
			btnRight.setSelected(false);
			break;
		case R.id.uem_my_events:
			vp.setCurrentItem(1);
			btnLeft.setSelected(false);
			btnRight.setSelected(true);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.upcoming_events_main);
		vp = (ViewPager) findViewById(R.id.uem_viewpager);
		btnLeft = (Button) findViewById(R.id.uem_all_events);
		btnRight = (Button) findViewById(R.id.uem_my_events);

		adapter = new UEAdap(this);

		LayoutInflater lf = LayoutInflater.from(this);
		// viewpage1
		View viewPage1 = lf.inflate(R.layout.viewpage1, null);
		listView = (ListView) viewPage1.findViewById(R.id.viewpage1_lv);
		listView.setAdapter(adapter);

		// viewpage2
		View viewpage2 = lf.inflate(R.layout.viewpage2, null);
		listView2 = (ListView) viewpage2.findViewById(R.id.viewpage2_lv);
		listView2.setAdapter(adapter);

		viewList = new ArrayList<View>();
		viewList.add(viewPage1);
		viewList.add(viewpage2);

		vpAdap = new ViewPagerAdapter();
		vpAdap.setViews(viewList);
		vp.setAdapter(vpAdap);
		vp.setPageMargin(5);
		btnLeft.setSelected(true);
		

		initListener();
		initData();
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

}
