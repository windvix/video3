package com.athudong.video;

import com.athudong.video.component.SelectAnim;
import com.athudong.video.task.BaseTask;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

/**
 * 海选界面
 */
public class SelectActivity extends BaseActivity {

	private View oneHead;
	private View twoHead;
	private View threeHead;
	private View fourHead;

	private SelectAnim anim;
	
	private TextView thumbCountTv;

	private int thumbCount = 10;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_select);
		oneHead = findViewById(R.id.oneHead);
		twoHead = findViewById(R.id.twoHead);
		threeHead = findViewById(R.id.threeHead);
		fourHead = findViewById(R.id.fourHead);

		thumbCountTv = (TextView)findViewById(R.id.thumbCountTv);
		
		findViewById(R.id.main_select_btn_01).setOnClickListener(this);
		findViewById(R.id.main_select_btn_02).setOnClickListener(this);
		findViewById(R.id.main_select_btn_03).setOnClickListener(this);

		findViewById(R.id.backBtn).setOnClickListener(this);
		
		findViewById(R.id.thumbBtn).setOnClickListener(this);
		findViewById(R.id.headLayout).setOnTouchListener(new HeadTouchListener());

		anim = new SelectAnim(oneHead, twoHead, threeHead, fourHead);
		
		thumbCount = 10;
		thumbCountTv.setText(thumbCount+"");
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.main_select_btn_03) {
			anim.next();
		} else if (id == R.id.thumbBtn) {
			if(thumbCount>=1){
				thumbCount--;
				thumbCountTv.setText(thumbCount+"");
				anim.next();
			}else{
				toast("票数为0");
			}
		}else if(id==R.id.backBtn){
			String ex = getIntent().getStringExtra("exist");
			finish();
			//从主界面跳转过来。则不需要启动主界面
			if(ex==null || ex.equals("")){
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
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
			
			String ex = getIntent().getStringExtra("exist");
			finish();
			
			//从主界面跳转过来。则不需要启动主界面
			if(ex==null || ex.equals("")){
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
			return false;
		} 
		return true;
	}


	/**
	 * 用于处理头像的左右滑动操作
	 */
	private class HeadTouchListener implements OnTouchListener {
		float x_temp01 = 0.0f;
		float y_temp01 = 0.0f;
		float x_temp02 = 0.0f;
		float y_temp02 = 0.0f;

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			// 获得当前坐标
			float x = event.getX();
			float y = event.getY();

			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				x_temp01 = x;
				y_temp01 = y;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				x_temp02 = x;
				y_temp02 = y;
				if (x_temp01 != 0 && y_temp01 != 0) {
					// 比较x_temp01和x_temp02
					// x_temp01>x_temp02 //向左
					// x_temp01==x_temp02 //竖直方向或者没动
					// x_temp01<x_temp02 //向右
					// 向左
					if (x_temp01 > x_temp02) {
						// 移动了x_temp01-x_temp02
						anim.next();
					}

					// 向右
					if (x_temp01 < x_temp02) {
						// 移动了x_temp02-x_temp01
						anim.prev();
					}
				}
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {

			}
			return true;
		}

	}
}
