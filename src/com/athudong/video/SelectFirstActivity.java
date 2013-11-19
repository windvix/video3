package com.athudong.video;

import com.athudong.video.task.BaseTask;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

/**
 * 海选界面(第一次启动显示的)
 */
public class SelectFirstActivity extends BaseActivity {

	public static SelectFirstActivity self = null;
	
	private SelectActivityCommon common;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		self = this;
		common = new SelectActivityCommon(this, true);
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
	
	// 重写Activity中onKeyDown（）方法
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {// 当keyCode等于退出事件值时
			common.showMain();
			return false;
		} 
		return true;
	}



}
