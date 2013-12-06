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
 * 海选界面通用部分，（海选界面有两种形态，一种是一打开应用就进入，第二种是主界面中打开）
 */
public class SelectActivityCommon implements OnClickListener {

	/**
	 * 用于标识是否是从主界面中点击海选打开
	 */
	private boolean isCreateMainActivity = false;

	private BaseActivity act;

	private ViewPager viewpager;

	private List<View> imageViews;

	private TextView thumbCount;

	//剩余票数
	private int count = 10;
	
	private User me;
	
	private User currentStar;
	
	private User nextStar;
	
	
	private TextView nameTv;
	
	private TextView sayingTv;

	public SelectActivityCommon(BaseActivity act, boolean isCreatemainActivity) {
		this.isCreateMainActivity = isCreatemainActivity;
		this.act = act;
		
		//当前用户(当前用户，不是比赛的明星)
		me = act.getUser();
		
		//测试用，抽取出下一个明星
		nextStar = TestDataUtil.getRandomUser();
		
		act.setContentView(R.layout.activity_select_back);
		
		viewpager = (ViewPager) act.findViewById(R.id.viewpager);
		
		nameTv = (TextView)act.findViewById(R.id.nameTv);
		sayingTv = (TextView)act.findViewById(R.id.sayingTv);

		/**
		 * 海选界面中的明星大图片，是一个viewpager，把几个ImageView加入到viewpager中
		 * (为了实现换下一个明星，明星从右向右出现的切换的效果)
		 */
		imageViews = new ArrayList<View>();
		imageViews.add(act.createView(R.layout.imgageview_centercrop));
		imageViews.add(act.createView(R.layout.imgageview_centercrop));
		imageViews.add(act.createView(R.layout.imgageview_centercrop));
		imageViews.add(act.createView(R.layout.imgageview_centercrop));

		
		viewpager.setAdapter(new ViewPagerAdapter(imageViews));
		
		/**
		 * 设置onPageChange监听器，
		 * （ 此监听器作了特殊的处理，目的是做出无限循环的viewpager效果）
		 */
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());
		
		//注意，viewpager默认选择的是第2个！
		viewpager.setCurrentItem(1);

		/**
		 * 注册点击事件
		 */
		act.findViewById(R.id.changeBtn).setOnClickListener(this);
		act.findViewById(R.id.thumbBtn).setOnClickListener(this);
		act.findViewById(R.id.backBtn).setOnClickListener(this);
		act.findViewById(R.id.zoneBtn).setOnClickListener(this);

		thumbCount = (TextView) act.findViewById(R.id.thumbCountTv);

		
		initContent();
		
		//剩余票数
		count = Integer.parseInt(thumbCount.getText().toString());
		
		
	}

	/**
	 * 初始化内容
	 */
	private void initContent(){
		//设置剩余票数
		thumbCount.setText(me.getVoteCount()+"");
		
		//将海选明星的照片设置为空
		for(View v:imageViews){
			((ImageView) v.findViewById(R.id.img)).setImageBitmap(null);
		}
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				//显示明星照片和资料，并生成下一个明星
				changeStar();
			}
		}, 500);
	}
	
	//显示明星照片和资料，并生成下一个明星
	private void changeStar(){

		
		//当前明星变成下一个明星，下一个明星再抽一个出来
		currentStar = nextStar;
		nextStar = TestDataUtil.getRandomUser();
		
		currentStarId = currentStar.getId();
		
		ImageView img01 = ((ImageView)imageViews.get(0).findViewById(R.id.img)); 
		ImageView img02 = ((ImageView)imageViews.get(1).findViewById(R.id.img)); 
		ImageView img03 = ((ImageView)imageViews.get(2).findViewById(R.id.img)); 
		ImageView img04 = ((ImageView)imageViews.get(3).findViewById(R.id.img)); 
		
		//获取当前viewpager处于哪一页
		int page = viewpager.getCurrentItem();
		
		
		/**
		 * 由于viewpager作了特殊的处理，所以viewpager永远只可能显示第二页和第三页
		 */
		
		//当前是第一页
		if(page==1){
			//将viewpager中的 第二页和第四页图片设成 当前明星
			String p1 = act.getTestPath()+currentStar.getId()+"_01.jpg";
			Bitmap b1 = act.readBitmapAutoSize(p1, img01.getWidth(), img01.getHeight());
			img02.setImageBitmap(b1);
			img04.setImageBitmap(b1);
			
			
			//将viewpager中的第一页和第三页图片设成下一个明星
			String p2 = act.getTestPath()+nextStar.getId()+"_01.jpg";
			Bitmap b2 = act.readBitmapAutoSize(p2, img01.getWidth(), img01.getHeight());
			img01.setImageBitmap(b2);
			img03.setImageBitmap(b2);
		}
		else if(page==2){
			
			//将viewpager中的第一页和第三页图片设成当前明星
			String p2 = act.getTestPath()+currentStar.getId()+"_01.jpg";
			Bitmap b2 = act.readBitmapAutoSize(p2, img01.getWidth(), img01.getHeight());
			img01.setImageBitmap(b2);
			img03.setImageBitmap(b2);
			
			//将viewpager中的 第二页和第四页图片设成 下一个明星
			String p1 = act.getTestPath()+nextStar.getId()+"_01.jpg";
			Bitmap b1 = act.readBitmapAutoSize(p1, img01.getWidth(), img01.getHeight());
			img02.setImageBitmap(b1);
			img04.setImageBitmap(b1);
		}
		
		//设置当前显示的明星名称
		nameTv.setText(currentStar.getName());
		
		//设置当前显示的明星的比赛宣言
		sayingTv.setText(currentStar.getSaying());
	}
	
	private String currentStarId;
	
	@Override
	public void onClick(View v) {
		int id = v.getId();
		//返回按钮
		if (id == R.id.backBtn) {
			if (isCreateMainActivity) {
				showMain();
			} else {
				act.finish();
			}
		} 
		//换一个按钮
		else if (id == R.id.changeBtn) {
			//剩余票数大于0时可以换一个
			if (count > 0) {
				nextOne();
			}
			//票数不足显示充值对话框
			else {
				showDialog();
			}
		}
		//投一票按钮
		else if (id == R.id.thumbBtn) {
			//剩余票数大于0进行投票，投票后换下一个
			if (count > 0) {
				nextOne();
				count--;
				thumbCount.setText(count + "");
				me.setVoteCount(count);
			} 
			//票数不足显示充值对话框
			else {
				showDialog();
			}
		}
		//个人资料按钮，点击进入个人空间
		else if(id==R.id.zoneBtn){
			Intent intent = new Intent(act, ZoneActivity.class);
			intent.putExtra("id", currentStarId);
			act.startActivity(intent);
		}
	}

	/**
	 * 显示票数不足，充值对话框
	 */
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
		
		//点击购买按钮，打开明星商城界面
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
				
				
				/**
				 * 当前页面如果是最后一页，则到到第二页
				 * 当前页面如果是第一页，则跳到倒数第二页
				 */
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
