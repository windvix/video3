package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

/**
 * 周赛PK界面
 */
public class WeekActivity extends BaseActivity{
	

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_week_pk);
		findViewById(R.id.backBtn).setOnClickListener(this);
		findViewById(R.id.left_play_btn).setOnClickListener(this);
		findViewById(R.id.leftThumbBtn).setOnClickListener(this);
		findViewById(R.id.right_play_btn).setOnClickListener(this);
		findViewById(R.id.rightThumbBtn).setOnClickListener(this);
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
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			finish();
		}
	}

}
