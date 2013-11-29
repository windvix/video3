package com.athudong.video;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.VideosActivity.ViewPagerAdapter;
import com.athudong.video.VideosActivity.ViewPagerPageChangeListener;
import com.athudong.video.bean.User;
import com.athudong.video.component.VideoHelper;
import com.athudong.video.util.TestDataUtil;
import com.nineoldandroids.animation.ObjectAnimator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ZoneActivityVideo implements OnClickListener{

	private ZoneActivity act;
	private View root;

	private User user;


	public ZoneActivityVideo(final ZoneActivity act, final View root) {
		this.act = act;
		this.root = root;
		user = TestDataUtil.getRandomUser();

		root.findViewById(R.id.video01).setOnClickListener(this);
		root.findViewById(R.id.video02).setOnClickListener(this);
		root.findViewById(R.id.video03).setOnClickListener(this);
		root.findViewById(R.id.video04).setOnClickListener(this);
		root.findViewById(R.id.video05).setOnClickListener(this);
		root.findViewById(R.id.video06).setOnClickListener(this);
	}

	
	
	@Override
	public void onClick(View view) {
		int id = view.getId();
		
		String path = act.getTestPath()+user.getId()+"_video_02.flv";
		if(id==R.id.video01){
			
		}else if(id==R.id.video02){
			
		}else if(id==R.id.video03){
			
		}else if(id==R.id.video04){
			
		}else if(id==R.id.video05){
			
		}else if(id==R.id.video06){
			
		}
		
		playVideo(path);
	}
	
	/**
	 * 播放视频
	 */
	private void playVideo(String path){
		Uri uri = Uri.parse(path);  
		//调用系统自带的播放器 
		Intent intent = new Intent(Intent.ACTION_VIEW); 
		intent.setDataAndType(uri, "video/mp4"); 
		act.startActivity(intent);
	}
}
