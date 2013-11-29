package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

public class ShopActivity extends BaseActivity{

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			finish();
		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_shop);
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

}
