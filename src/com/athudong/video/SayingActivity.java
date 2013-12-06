package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

/**
 * 发表我的动态界面
 */
public class SayingActivity extends BaseActivity{

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
		setContentView(R.layout.activity_saying);
		
		
		
		
		/**
		 * 页面中有EditText，防止页面打开时就弹出软键盘
		 */
		findViewById(R.id.no_use).requestFocus();
		
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
