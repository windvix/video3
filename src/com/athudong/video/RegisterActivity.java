package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

/**
 * 注册界面
 */
public class RegisterActivity extends BaseActivity{

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_register);
		findViewById(R.id.backBtn).setOnClickListener(this);
		findViewById(R.id.submitBtn).setOnClickListener(this);
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
		if(id==R.id.submitBtn){
			finish();
		}else if(id==R.id.backBtn){
			finish();
		}
	}

}
