package com.athudong.video;


import java.util.ArrayList;
import java.util.List;

import com.athudong.video.component.ButtonTouchListener;
import com.athudong.video.task.BaseTask;
import com.athudong.video.util.TestUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

public class MainActivity extends BaseActivity{
	
	
	private View midHead;
	private View leftHead;
	private View rightHead;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_main);
		midHead = findViewById(R.id.midHead);
		leftHead  =findViewById(R.id.leftHead);
		rightHead = findViewById(R.id.rightHead);
		
		findViewById(R.id.main_select_btn_03).setOnClickListener(this);
		threeHead = new ArrayList<View>();
		threeHead.add(leftHead);
		threeHead.add(midHead);
		threeHead.add(rightHead);
		
		findViewById(R.id.thumbBtn).setOnClickListener(this);
	}

	private int length = 0;
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.main_select_btn_03){
			left();
		}else if(id==R.id.thumbBtn){
			toast("你好");
		}
	}

	private List<View> threeHead;
	
	private void left(){
		if(length<=0){
			length =  rightHead.getLeft()-midHead.getLeft();
		}
		View one = threeHead.get(0);
		View two = threeHead.get(1);
		View three = threeHead.get(2);
		
		
		animate(one).x(one.getX()-length).x(one.getX()+length*3).x(one.getX()+length*2);
		animate(two).x(two.getX()-length);
		animate(three).x(three.getX()-length);
		
		threeHead.set(0, two);
		threeHead.set(1, three);
		threeHead.set(2, one);
		
		changeThree();
	}
	
	private void changeThree(){
		ImageView img = (ImageView)threeHead.get(2).findViewWithTag("head");
		img.setImageResource(TestUtil.getRandomHead());
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
