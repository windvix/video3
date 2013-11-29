package com.athudong.video;

import com.athudong.video.R;
import com.athudong.video.task.BaseTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


/**
 * 活动详细界面
 */
public class ActDetailActivity extends BaseActivity {
	
	public void controlClick(View view){
		switch(view.getId()){
		case R.id.title_bar_left:
			this.finish();
			break;
		case R.id.detail_sign:
			Intent intent = new Intent(this,ApplyActivity.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.viewpage_item_detail);

		TextView tvTitle = (TextView) findViewById(R.id.title_bar_tv);
		tvTitle.setText(getString(R.string.upcoming_events));
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
