package com.athudong.video;

import com.athudong.video.task.BaseTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends BaseActivity{

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		findViewById(R.id.main_select_btn_03).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.main_select_btn_03){
			
		}
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
			ToQuitTheApp();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private boolean isExit = false;

	// 封装ToQuitTheApp方法
	private void ToQuitTheApp() {
		if (isExit) {
			// ACTION_MAIN with category CATEGORY_HOME 启动主屏幕
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);

			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					MainActivity.this.finish();
					System.exit(0);// 使虚拟机停止运行并退出程序
				}
			}, 1500);
		} else {
			isExit = true;
			toast("再按一次退出");
			mHandler.sendEmptyMessageDelayed(0, 3000);// 3秒后发送消息
		}
	}

	// 创建Handler对象，用来处理消息
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {// 处理消息
			super.handleMessage(msg);
			isExit = false;
		}
	};

}
