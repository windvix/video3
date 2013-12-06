package com.athudong.video;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

/**
 * 消息通知界面
 */
public class NotificationActivity extends BaseActivity{

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			finish();
		}else{
			Intent intent= new Intent(this, NotificationDetailActivity.class);
			startActivity(intent);
		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_notification);
		findViewById(R.id.backBtn).setOnClickListener(this);
		findViewById(R.id.msgBtn01).setOnClickListener(this);
		findViewById(R.id.msgBtn02).setOnClickListener(this);
		findViewById(R.id.msgBtn03).setOnClickListener(this);
		findViewById(R.id.msgBtn04).setOnClickListener(this);
		findViewById(R.id.msgBtn05).setOnClickListener(this);
		findViewById(R.id.msgBtn06).setOnClickListener(this);
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
