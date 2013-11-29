package com.athudong.video;

import com.athudong.video.task.BaseTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * 报名参加活动
 */
public class ApplyActivity extends BaseActivity {
	
	
	public void controlClick(View view){
		switch(view.getId()){
		case R.id.title_bar_left:
			this.finish();
			break;
		}
	}

	@Override
	public void onClick(View arg0) {
		
		
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_apply);
		TextView tvTitle = (TextView) findViewById(R.id.title_bar_tv);
		tvTitle.setText(getString(R.string.join_in2));
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
}
