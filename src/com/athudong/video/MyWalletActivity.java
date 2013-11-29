package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

public class MyWalletActivity extends BaseActivity{

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			finish();
		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_my_wallet);
		findViewById(R.id.backBtn).setOnClickListener(this);
	}


	@Override
	protected void beforeEveryVisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterEveryInVisable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void beforeDestory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {
		// TODO Auto-generated method stub
		
	}

}
