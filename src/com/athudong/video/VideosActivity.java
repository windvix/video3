package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;


/**
 * 参赛者的视频列表界面（主要是参赛者的视频）
 */
public class VideosActivity extends BaseActivity{

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_videos);
		findViewById(R.id.backBtn).setOnClickListener(this);
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
	public void onClick(View view) {
		int id = view.getId();
		if(id==R.id.backBtn){
			finish();
		}
	}

}
