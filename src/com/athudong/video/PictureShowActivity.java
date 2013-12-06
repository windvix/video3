package com.athudong.video;

import android.os.Bundle;
import android.view.View;

import com.athudong.video.task.BaseTask;

/**
 * 图片展示界面（用于周赛PK，查看图片的，目前暂时未使用）
 */
public class PictureShowActivity extends BaseActivity{

	@Override
	public void onClick(View v) {
		
	}

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pic_show);
		
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
