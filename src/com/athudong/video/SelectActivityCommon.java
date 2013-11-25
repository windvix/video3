package com.athudong.video;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.athudong.video.bean.User;
import com.athudong.video.component.FixedSpeedScroller;
import com.athudong.video.util.TestDataUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.sax.StartElementListener;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * 海选界面通用部分
 */
public class SelectActivityCommon implements OnClickListener{

	private TextView thumbCountTv;

	private int thumbCount = 10;
	
	private ViewPager viewpager;
	
	
	private List<View> headsView;
	
	private boolean isCreateMainActivity = false;
	
	private BaseActivity act;
	
	
	private View head01;
	private View head02;
	private View head03;
	private View head04;
	private View head05;
	private View head06;
	
	private ImageView img01;
	private ImageView img02;
	private ImageView img03;
	private ImageView img04;
	private ImageView img05;
	private ImageView img06;
	
	
	private TextView nameTv;
	private TextView ageTv;
	private TextView sexTv;
	private TextView despTv;
	
	
	private View bottomBtn01;
	private View bottomBtn02;
	private View bottomBtn03;
	
	
	private List<User> users;
	
	
	public SelectActivityCommon(BaseActivity act, boolean isCreatemainActivity){
		this.isCreateMainActivity = isCreatemainActivity;
		this.act = act;
		act.setContentView(R.layout.activity_select);
		users = new ArrayList<User>();
		nameTv = (TextView)act.findViewById(R.id.nameTv);
		ageTv = (TextView)act.findViewById(R.id.ageTv);
		sexTv = (TextView)act.findViewById(R.id.sexTv);
		despTv = (TextView)act.findViewById(R.id.sayingTv);
		
		
		
		thumbCountTv = (TextView)act.findViewById(R.id.thumbCountTv);
		
		bottomBtn01 = act.findViewById(R.id.main_select_btn_01);
		bottomBtn01.setOnClickListener(this);
		bottomBtn02 = act.findViewById(R.id.main_select_btn_02);
		bottomBtn02.setOnClickListener(this);
		bottomBtn03 = act.findViewById(R.id.main_select_btn_03);
		bottomBtn03.setOnClickListener(this);

		act.findViewById(R.id.backBtn).setOnClickListener(this);
		
		act.findViewById(R.id.thumbBtn).setOnClickListener(this);
		
		thumbCount = 10;
		thumbCountTv.setText(thumbCount+"");
		
		viewpager = (ViewPager)act.findViewById(R.id.viewpager);
		
		headsView = new ArrayList<View>();
		
		// D' A B C D A'
		// 让D'和D、A'和A的内容保持相同
		
		head01 = act.createView(R.layout.viewpager_select_head);
		head02 = act.createView(R.layout.viewpager_select_head);
		head03 = act.createView(R.layout.viewpager_select_head);
		head04 = act.createView(R.layout.viewpager_select_head);
		head05 = act.createView(R.layout.viewpager_select_head);
		head06 = act.createView(R.layout.viewpager_select_head);
		
		img01 = (ImageView)head01.findViewWithTag("head");
		img02 = (ImageView)head02.findViewWithTag("head");
		img03 = (ImageView)head03.findViewWithTag("head");
		img04 = (ImageView)head04.findViewWithTag("head");
		img05 = (ImageView)head05.findViewWithTag("head");
		img06 = (ImageView)head06.findViewWithTag("head");
		
		
		headsView.add(head01);
		headsView.add(head02);
		headsView.add(head03);
		headsView.add(head04);
		headsView.add(head05);
		headsView.add(head06);
		
		
		User u01 = TestDataUtil.getRandomUser();
		User u02 = TestDataUtil.getRandomUser();
		User u03 = TestDataUtil.getRandomUser();
		User u04 = TestDataUtil.getRandomUser();
		
		users.add(u04);
		users.add(u01);
		users.add(u02);
		users.add(u03);
		users.add(u04);
		users.add(u01);
		
		viewpager.setOffscreenPageLimit(3);
		viewpager.setPageMargin(act.getResources().getDimensionPixelSize(R.dimen.select_viewpager_margin));
		
		viewpager.setAdapter(new ViewPagerAdapter(headsView));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		viewpager.setCurrentItem(1);
		
		try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true); 
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewpager.getContext());
            mScroller.set(viewpager, scroller);
        } catch (Exception e) {
        	act.toast("wrong ");
        } 
		

		
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				// D' A B C D A'
				// 让D'和D、A'和A的内容保持相同
				int width = img01.getWidth();
				int height = img01.getHeight();
				String path = SelectActivityCommon.this.act.getTestPath();
				Bitmap b01  = SelectActivityCommon.this.act.readBitmapAutoSize(path+users.get(0).getId()+"_head.jpg", width, height);
				Bitmap b02  = SelectActivityCommon.this.act.readBitmapAutoSize(path+users.get(1).getId()+"_head.jpg", width, height);
				Bitmap b03  = SelectActivityCommon.this.act.readBitmapAutoSize(path+users.get(2).getId()+"_head.jpg", width, height);
				Bitmap b04  = SelectActivityCommon.this.act.readBitmapAutoSize(path+users.get(3).getId()+"_head.jpg", width, height);
				
				img02.setImageBitmap(b01);
				img03.setImageBitmap(b02);
				img04.setImageBitmap(b03);
				img05.setImageBitmap(b04);
				
				img01.setImageBitmap(b04);
				img06.setImageBitmap(b01);
				

			}
		}, 500);
	}
	
	
	
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			if(isCreateMainActivity){
				showMain();
			}else{
				act.finish();
			}
		}else if(id==R.id.main_select_btn_03){
			int cur = viewpager.getCurrentItem();
			if(cur<(headsView.size()-1)){
				viewpager.setCurrentItem(cur+1);
			}
		}else if(id==R.id.thumbBtn){
			if(thumbCount>0){
				thumbCount--;
				thumbCountTv.setText(thumbCount+"");
				int cur = viewpager.getCurrentItem();
				if(cur<(headsView.size()-1)){
					viewpager.setCurrentItem(cur+1);
				}
			}else{
				act.toast("票数为0");
			}
		}else if(id==R.id.main_select_btn_01){
			Intent intent  = new Intent(act, IntroActivity.class);
			intent.putExtra("id", v.getTag().toString());
			act.startActivity(intent);
		}else if(id==R.id.main_select_btn_02){
			Intent intent = new Intent(act, VideosActivity.class);
			intent.putExtra("id", v.getTag().toString());
			act.startActivity(intent);
		}
	}
	
	/**
	 * 显示主界面
	 */
	public void showMain(){
		Intent intent = new Intent(act, MainActivity.class);
		act.startActivity(intent);
	}
	
	/**
	 * 释放视频资源
	 */
	public void releaseView(){
		viewpager.setOnPageChangeListener(null);
		viewpager.removeAllViews();
		viewpager.removeAllViewsInLayout();
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
			
			if (state == ViewPager.SCROLL_STATE_IDLE) {
		        int curr = viewpager.getCurrentItem();
		        int lastReal = viewpager.getAdapter().getCount() - 2;
		        if (curr == 0) {
		            viewpager.setCurrentItem(lastReal, false);
		        } else if (curr > lastReal) {
		            viewpager.setCurrentItem(1, false);
		        }
		    }
		}

		@Override
		public void onPageScrolled(int page, float positionOffset, int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int page) {
			User user = users.get(page);
			sexTv.setText(user.getSexSimbol());
			nameTv.setText(user.getName());
			despTv.setText(user.getDescription());
			ageTv.setText(user.getAge()+"");
			
			bottomBtn01.setTag(user.getId());
			bottomBtn02.setTag(user.getId());
			
		}
	}
}
