package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.VideosActivity.ViewPagerAdapter;
import com.athudong.video.VideosActivity.ViewPagerPageChangeListener;
import com.athudong.video.component.VideoHelper;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ZoneActivityVideo implements OnClickListener{

	private ZoneActivity act;
	private View root;
	
	
	private ViewPager viewpager;

	private List<View> imgViews;

	private ImageView videoBg;

	private VideoHelper videoHelper;

	public ZoneActivityVideo(ZoneActivity act, View root) {
		this.act = act;
		this.root = root;
		
		
		
		

		viewpager = (ViewPager) root.findViewById(R.id.viewpager);

		root.findViewById(R.id.defaultPlayBtn).setOnClickListener(this);

		videoBg = (ImageView) root.findViewById(R.id.videoBgImg);

		imgViews = new ArrayList<View>();

		View h01 = act.createView(R.layout.video_nav_imgveiw);
		View h02 = act.createView(R.layout.video_nav_imgveiw);

		imgViews.add(h01);
		imgViews.add(h02);

		viewpager.setAdapter(new ViewPagerAdapter(imgViews));
		viewpager.setOnPageChangeListener(new ViewPagerPageChangeListener());

		initPage1(h01);
		initPage2(h02);

		videoHelper = new VideoHelper(act);
		
		
		act.findViewById(R.id.videoCoverLayout).setVisibility(View.VISIBLE);
		act.findViewById(R.id.videoPlayerLayout).setVisibility(View.INVISIBLE);
	}

	
	private void initPage1(View page) {

		View btn01 = page.findViewById(R.id.videoBtn01);
		View btn02 = page.findViewById(R.id.videoBtn02);
		View btn03 = page.findViewById(R.id.videoBtn03);
		View btn04 = page.findViewById(R.id.videoBtn04);

		ImageView img01 = (ImageView) btn01.findViewWithTag("img");
		ImageView img02 = (ImageView) btn02.findViewWithTag("img");
		ImageView img03 = (ImageView) btn03.findViewWithTag("img");
		ImageView img04 = (ImageView) btn04.findViewWithTag("img");

		img01.setImageResource(R.drawable.intro_test_head_01);
		img02.setImageResource(R.drawable.intro_test_head_02);
		img03.setImageResource(R.drawable.intro_test_head_03);
		img04.setImageResource(R.drawable.intro_test_head_04);

		btn01.setTag("intro_test_img_01.jpg");
		btn02.setTag("intro_test_img_02.jpg");
		btn03.setTag("intro_test_img_03.jpg");
		btn04.setTag("intro_test_img_04.jpg");

		btn01.setOnClickListener(this);
		btn02.setOnClickListener(this);
		btn03.setOnClickListener(this);
		btn04.setOnClickListener(this);

		viewpager.setCurrentItem(0);
		anim(btn01);
	}

	private void initPage2(View page) {

		View btn01 = page.findViewById(R.id.videoBtn01);
		View btn02 = page.findViewById(R.id.videoBtn02);
		View btn03 = page.findViewById(R.id.videoBtn03);
		View btn04 = page.findViewById(R.id.videoBtn04);

		ImageView img01 = (ImageView) btn01.findViewWithTag("img");
		ImageView img02 = (ImageView) btn02.findViewWithTag("img");
		ImageView img03 = (ImageView) btn03.findViewWithTag("img");
		ImageView img04 = (ImageView) btn04.findViewWithTag("img");

		img01.setImageResource(R.drawable.intro_test_head_05);
		img02.setImageResource(R.drawable.intro_test_head_06);
		img03.setImageResource(R.drawable.intro_test_head_07);
		img04.setImageResource(R.drawable.intro_test_head_08);

		btn01.setTag("intro_test_img_05.jpg");
		btn02.setTag("intro_test_img_06.jpg");
		btn03.setTag("intro_test_img_07.jpg");
		btn04.setTag("intro_test_img_08.jpg");

		btn01.setOnClickListener(this);
		btn02.setOnClickListener(this);
		btn03.setOnClickListener(this);
		btn04.setOnClickListener(this);
	}
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
