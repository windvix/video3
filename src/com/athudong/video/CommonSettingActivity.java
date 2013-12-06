package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;


/**
 * 通用设置界面（主界面第四个tab,点击通用设置进入）
 */
public class CommonSettingActivity extends BaseActivity{

	@Override
	public void onClick(View v) {
		int id = v.getId();
		//返回按钮
		if(id==R.id.backBtn){
			finish();
		}
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_common_setting);
		findViewById(R.id.backBtn).setOnClickListener(this);
		
		findViewById(R.id.setBtn01).setOnClickListener(this);
		findViewById(R.id.setBtn02).setOnClickListener(this);
		findViewById(R.id.setBtn03).setOnClickListener(this);
		findViewById(R.id.setBtn04).setOnClickListener(this);
		findViewById(R.id.setBtn05).setOnClickListener(this);
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
