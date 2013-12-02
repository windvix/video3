package com.athudong.video;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.athudong.video.task.BaseTask;

public class AccountManageActivity extends BaseActivity{

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.backBtn){
			finish();
		}else if(id==R.id.editBtn){
			TextView tv = (TextView)v;
			if(tv.getText().toString().contains("完")){
				tv.setText("编辑");
			}else{
				tv.setText("完成");
			}
		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_account_manage);
		findViewById(R.id.backBtn).setOnClickListener(this);
		findViewById(R.id.no_use).requestFocus();
		findViewById(R.id.editBtn).setOnClickListener(this);
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
