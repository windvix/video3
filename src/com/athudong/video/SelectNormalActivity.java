package com.athudong.video;


import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

/**
 * 正常的海选界面（点击海选按钮后显示的）
 */
public class SelectNormalActivity extends BaseActivity{

	@Override
	protected void initView(Bundle savedInstanceState) {
		new SelectActivityCommon(this, false);
	}

	@Override
	public void onClick(View v) {
		
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
