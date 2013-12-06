package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.athudong.video.task.BaseTask;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 我的钱包界面
 */
public class MyWalletActivity extends BaseActivity {

	private ViewPager viewPager;
	private List<View> views;
	private LayoutInflater inflater;
	
	/**
	 * 四个子界面
	 */
	private View viewBuy, viewOnline, viewFriend, viewPk;
	private ImageView select1, select2, select3, select4;
	private ImageView[] imageViews;
	private Drawable red, gray;

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.backBtn) {
			finish();
		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_my_wallet);

		imageViews = new ImageView[4];
		select1 = (ImageView) findViewById(R.id.my_wallet_select1);
		select2 = (ImageView) findViewById(R.id.my_wallet_select2);
		select3 = (ImageView) findViewById(R.id.my_wallet_select3);
		select4 = (ImageView) findViewById(R.id.my_wallet_select4);
		imageViews[0] = select1;
		imageViews[1] = select2;
		imageViews[2] = select3;
		imageViews[3] = select4;
		red = getResources().getDrawable(R.drawable.dot_red_bg);
		gray = getResources().getDrawable(R.drawable.dot_gray_bg);

		viewPager = (ViewPager) findViewById(R.id.my_wallet_viewpager);
		views = new ArrayList<View>();
		inflater = LayoutInflater.from(this);
		viewBuy = inflater.inflate(R.layout.my_wallet_viewpager_item_buy, null);
		viewOnline = inflater.inflate(R.layout.my_wallet_viewpager_item_online, null);
		viewFriend = inflater.inflate(R.layout.my_wallet_viewpager_item_friend, null);
		viewPk = inflater.inflate(R.layout.my_wallet_viewpager_item_pk, null);
		views.add(viewBuy);
		views.add(viewOnline);
		views.add(viewFriend);
		views.add(viewPk);
		MyViewPagerAdapter adapter = new MyViewPagerAdapter(views);
		viewPager.setAdapter(adapter);
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				for (int i = 0; i < imageViews.length; i++) {
					if (i == arg0) {
						imageViews[i].setBackgroundResource(R.drawable.dot_red_bg);
					} else {
						imageViews[i].setBackgroundResource(R.drawable.dot_gray_bg);
					}
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		findViewById(R.id.backBtn).setOnClickListener(this);
	}

	@Override
	protected void beforeEveryVisable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void afterEveryInVisable() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void beforeDestory() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {
		// TODO Auto-generated method stub

	}

	private class MyViewPagerAdapter extends PagerAdapter {
		private List<View> views;

		public MyViewPagerAdapter(List<View> views) {
			if (views != null) {
				this.views = views;
			} else {
				this.views = new ArrayList<View>();
			}
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(views.get(position));
			return views.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(views.get(position));
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

}
