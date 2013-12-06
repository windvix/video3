package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.athudong.video.bean.User;
import com.athudong.video.task.BaseTask;
import com.athudong.video.util.StringUtil;
import com.athudong.video.util.TestDataUtil;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * 个人空间界面
 */
public class ZoneActivity extends BaseActivity implements OnRefreshListener<ScrollView> {

	private ViewPager viewpager;

	private View v01;
	private View v02;
	private View v03;

	private List<View> views;

	private List<View> arrows;

	/**
	 * 图片view
	 */
	private ZoneActivityPic zonePic;
	
	/**
	 * 视频view
	 */
	private ZoneActivityVideo zoneVideo;
	
	/**
	 * 
	 */
	private ZoneActivityStar zoneStar;
	
	private TextView titleTv;
	private ImageView headImg;
	private TextView fansCountTv;
	private TextView popCountTv;
	private TextView ageTv;
	private TextView starSignTv;
	private TextView sayingTv;
	
	
	

	private PullToRefreshScrollView scrollview;
	
	private User star;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_personres);
		findViewById(R.id.backBtn).setOnClickListener(this);

		String id = getIntent().getStringExtra("id");
		
		if(StringUtil.isEmpty(id)){
			id = "01";
		}
		//当前空间用户
		star = TestDataUtil.getUserById(id);
		
		titleTv = (TextView)findViewById(R.id.titleTv);
		headImg = (ImageView)findViewById(R.id.headImg);
		fansCountTv = (TextView)findViewById(R.id.fansCountTv);
		popCountTv = (TextView)findViewById(R.id.popCountTv);
		ageTv = (TextView)findViewById(R.id.ageTv);
		starSignTv = (TextView)findViewById(R.id.starSignTv);
		sayingTv = (TextView)findViewById(R.id.sayingTv);
		
		views = new ArrayList<View>();
		arrows = new ArrayList<View>();

		arrows.add(findViewById(R.id.arrow01));
		arrows.add(findViewById(R.id.arrow02));
		arrows.add(findViewById(R.id.arrow03));

		v01 = createView(R.layout.personres_01);
		v02 = createView(R.layout.personres_02);
		v03 = createView(R.layout.personres_03);

		findViewById(R.id.tab01).setOnClickListener(this);
		findViewById(R.id.tab02).setOnClickListener(this);
		findViewById(R.id.tab03).setOnClickListener(this);

		views.add(v01);
		views.add(v02);
		views.add(v03);

		viewpager = (ViewPager) findViewById(R.id.viewpager);

		viewpager.setAdapter(new ViewPagerAdapter(views));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		scrollview = (PullToRefreshScrollView) findViewById(R.id.scrollview);

		scrollview.setOnRefreshListener(this);

		/**
		 * 当前页面中包括三个tab ，分别初始化tab内容
		 */
		zonePic = new ZoneActivityPic(this, v01, star);
		zoneVideo = new ZoneActivityVideo(this, v02);
		zoneStar = new ZoneActivityStar(this, v03);
		
		initContent();
	}

	/**
	 * 初始化个人空间头部内容
	 */
	private void initContent(){
		//设置标题(当前用户名)
		titleTv.setText(star.getName());
		
		//设置年龄
		ageTv.setText(star.getAge());
		
		//设置粉丝数
		fansCountTv.setText(star.getFans()+"");
		
		//设置人气
		popCountTv.setText(star.getPopular()+"");
		
		//设置星座
		starSignTv.setText(star.getStarSign());
		
		//设置明星动态
		sayingTv.setText(star.getSaying());
		
		//设置头像为空
		headImg.setImageBitmap(null);
		
		
		//延迟加载头像图片
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				String path = getTestPath()+star.getId()+"_head.jpg";
				Bitmap bitmap = readBitmapAutoSize(path, headImg.getWidth(), headImg.getHeight());
				headImg.setImageBitmap(bitmap);
			}
		}, 500);

	}
	
	@Override
	protected void beforeEveryVisable() {

	}

	@Override
	protected void afterEveryInVisable() {

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
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
		//返回按钮
		if (id == R.id.backBtn) {
			finish();
		}
		//第一个tab
		else if (id == R.id.tab01) {
			viewpager.setCurrentItem(0, false);
		}
		//第二个tab
		else if (id == R.id.tab02) {
			viewpager.setCurrentItem(1, false);
		}
		//第三个tab
		else if (id == R.id.tab03) {
			viewpager.setCurrentItem(2, false);
		}
	}

	@Override
	public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				scrollview.onRefreshComplete();
			}
		}, 2000);
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
			for (int i = 0; i < arrows.size(); i++) {
				if (page == i) {
					arrows.get(i).setVisibility(View.VISIBLE);
				} else {
					arrows.get(i).setVisibility(View.INVISIBLE);
				}
			}
		}
	}
}
