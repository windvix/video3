package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.dialog.ConfirmDialog;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 海选界面通用部分
 */
public class SelectActivityCommon implements OnClickListener {

	private boolean isCreateMainActivity = false;

	private BaseActivity act;

	private ViewPager viewpager;

	private List<View> views;

	private TextView thumbCount;

	private int count = 10;

	public SelectActivityCommon(BaseActivity act, boolean isCreatemainActivity) {
		this.isCreateMainActivity = isCreatemainActivity;
		this.act = act;
		act.setContentView(R.layout.activity_select_back);

		viewpager = (ViewPager) act.findViewById(R.id.viewpager);

		views = new ArrayList<View>();

		views.add(act.createView(R.layout.imgageview_centercrop));
		views.add(act.createView(R.layout.imgageview_centercrop));
		views.add(act.createView(R.layout.imgageview_centercrop));
		views.add(act.createView(R.layout.imgageview_centercrop));

		((ImageView) views.get(0).findViewById(R.id.img)).setImageResource(R.drawable.pk_video_bg_02);
		((ImageView) views.get(2).findViewById(R.id.img)).setImageResource(R.drawable.pk_video_bg_02);

		viewpager.setAdapter(new ViewPagerAdapter(views));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());
		viewpager.setCurrentItem(1);

		act.findViewById(R.id.changeBtn).setOnClickListener(this);
		act.findViewById(R.id.thumbBtn).setOnClickListener(this);
		act.findViewById(R.id.backBtn).setOnClickListener(this);
		act.findViewById(R.id.zoneBtn).setOnClickListener(this);

		thumbCount = (TextView) act.findViewById(R.id.thumbCountTv);

		count = Integer.parseInt(thumbCount.getText().toString());
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.backBtn) {
			if (isCreateMainActivity) {
				showMain();
			} else {
				act.finish();
			}
		} else if (id == R.id.changeBtn) {
			if (count > 0) {
				nextOne();
			} else {
				showDialog();
			}
		} else if (id == R.id.thumbBtn) {
			if (count > 0) {
				nextOne();
				count--;
				thumbCount.setText(count + "");
			} else {
				showDialog();
			}
		}else if(id==R.id.backBtn){
			act.finish();
		}else if(id==R.id.zoneBtn){
			Intent intent = new Intent(act, ZoneActivity.class);
			act.startActivity(intent);
		}
	}

	private void showDialog() {
		final ConfirmDialog dialog = new ConfirmDialog(act, R.style.DimDialog, "今天的票数已用完，是否购买？");
		dialog.getLeftBtn().setText("取消");
		dialog.getRightBtn().setText("购买");
		dialog.getLeftBtn().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.getRightBtn().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Intent intent = new Intent(act, ShopActivity.class);
				act.startActivity(intent);
			}
		});

		dialog.show();
	}

	/**
	 * 选择下一个
	 */
	private void nextOne() {
		int cur = viewpager.getCurrentItem();
		if (cur <= (views.size() - 2)) {
			viewpager.setCurrentItem(cur + 1);
		}
	}

	/**
	 * 显示主界面
	 */
	public void showMain() {
		Intent intent = new Intent(act, MainActivity.class);
		act.startActivity(intent);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				act.finish();
			}
		}, 3500);
	}

	/**
	 * 释放视频资源
	 */
	public void releaseView() {

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

		}
	}
}
