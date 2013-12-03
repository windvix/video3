package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.bean.User;
import com.athudong.video.dialog.ConfirmDialog;
import com.athudong.video.util.TestDataUtil;

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
import android.widget.TextView;

/**
 * 海选界面通用部分
 */
public class SelectActivityCommon implements OnClickListener {

	private boolean isCreateMainActivity = false;

	private BaseActivity act;

	private ViewPager viewpager;

	private List<View> imageViews;

	private TextView thumbCount;

	private int count = 10;
	
	private User me;
	
	private User currentStar;
	
	private User nextStar;
	
	
	private TextView nameTv;
	
	private TextView sayingTv;

	public SelectActivityCommon(BaseActivity act, boolean isCreatemainActivity) {
		this.isCreateMainActivity = isCreatemainActivity;
		this.act = act;
		me = act.getUser();
		nextStar = TestDataUtil.getRandomUser();
		
		act.setContentView(R.layout.activity_select_back);
		
		viewpager = (ViewPager) act.findViewById(R.id.viewpager);
		
		nameTv = (TextView)act.findViewById(R.id.nameTv);
		sayingTv = (TextView)act.findViewById(R.id.sayingTv);

		imageViews = new ArrayList<View>();

		imageViews.add(act.createView(R.layout.imgageview_centercrop));
		imageViews.add(act.createView(R.layout.imgageview_centercrop));
		imageViews.add(act.createView(R.layout.imgageview_centercrop));
		imageViews.add(act.createView(R.layout.imgageview_centercrop));

		viewpager.setAdapter(new ViewPagerAdapter(imageViews));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());
		viewpager.setCurrentItem(1);

		act.findViewById(R.id.changeBtn).setOnClickListener(this);
		act.findViewById(R.id.thumbBtn).setOnClickListener(this);
		act.findViewById(R.id.backBtn).setOnClickListener(this);
		act.findViewById(R.id.zoneBtn).setOnClickListener(this);

		thumbCount = (TextView) act.findViewById(R.id.thumbCountTv);

		
		initContent();
		
		count = Integer.parseInt(thumbCount.getText().toString());
		
		
	}

	
	private void initContent(){
		thumbCount.setText(me.getVoteCount()+"");
		
		for(View v:imageViews){
			((ImageView) v.findViewById(R.id.img)).setImageBitmap(null);
		}
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				changeStar();
			}
		}, 500);
	}
	
	private void changeStar(){
		int page = viewpager.getCurrentItem();
		
		//当前明星变成下一个明星，下一个明星再抽一个出来
		currentStar = nextStar;
		nextStar = TestDataUtil.getRandomUser();
		
		currentStarId = currentStar.getId();
		
		ImageView img01 = ((ImageView)imageViews.get(0).findViewById(R.id.img)); 
		ImageView img02 = ((ImageView)imageViews.get(1).findViewById(R.id.img)); 
		ImageView img03 = ((ImageView)imageViews.get(2).findViewById(R.id.img)); 
		ImageView img04 = ((ImageView)imageViews.get(3).findViewById(R.id.img)); 
		
		//当前是第一页，将看不见的页改变
		if(page==1){
			String p1 = act.getTestPath()+currentStar.getId()+"_01.jpg";
			Bitmap b1 = act.readBitmapAutoSize(p1, img01.getWidth(), img01.getHeight());
			img02.setImageBitmap(b1);
			img04.setImageBitmap(b1);
			
			
			String p2 = act.getTestPath()+nextStar.getId()+"_01.jpg";
			Bitmap b2 = act.readBitmapAutoSize(p2, img01.getWidth(), img01.getHeight());
			img01.setImageBitmap(b2);
			img03.setImageBitmap(b2);
		}
		else if(page==2){
			
			
			String p2 = act.getTestPath()+currentStar.getId()+"_01.jpg";
			Bitmap b2 = act.readBitmapAutoSize(p2, img01.getWidth(), img01.getHeight());
			img01.setImageBitmap(b2);
			img03.setImageBitmap(b2);
			
			
			String p1 = act.getTestPath()+nextStar.getId()+"_01.jpg";
			Bitmap b1 = act.readBitmapAutoSize(p1, img01.getWidth(), img01.getHeight());
			img02.setImageBitmap(b1);
			img04.setImageBitmap(b1);
		}
		nameTv.setText(currentStar.getName());
		sayingTv.setText(currentStar.getSaying());
	}
	
	private String currentStarId;
	
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
				me.setVoteCount(count);
			} else {
				showDialog();
			}
		}else if(id==R.id.backBtn){
			act.finish();
		}else if(id==R.id.zoneBtn){
			Intent intent = new Intent(act, ZoneActivity.class);
			intent.putExtra("id", currentStarId);
			
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
		if (cur <= (imageViews.size() - 2)) {
			viewpager.setCurrentItem(cur + 1);
			changeStar();
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
