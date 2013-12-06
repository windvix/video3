package com.athudong.video;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;



/**
 * 主界面第一个tab:发现界面操作
 */
public class MainActivityDiscover implements OnClickListener{

	private MainActivity act;
	
	private View root;
	
	
	public MainActivityDiscover(MainActivity act, View root){
		this.act = act;
		this.root = root;
		
		root.findViewById(R.id.selectBtn).setOnClickListener(this);
		root.findViewById(R.id.weekBtn).setOnClickListener(this);
		root.findViewById(R.id.actBtn).setOnClickListener(this);
		root.findViewById(R.id.applyBtn).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		//海选按钮
		if(id==R.id.selectBtn){
			Intent intent = new Intent(act, SelectNormalActivity.class);
			act.startActivity(intent);
		}
		//周赛按钮
		else if(id==R.id.weekBtn){
			Intent intent = new Intent(act, WeekActivity.class);
			act.startActivity(intent);
		}
		//最近活动按钮
		else if(id==R.id.actBtn){
			Intent intent = new Intent(act, ActNoticeActivity.class);
			act.startActivity(intent);
		}
		//我要报名按钮
		else if(id==R.id.applyBtn){
			Intent intent = new Intent(act, ApplyActivity.class);
			act.startActivity(intent);
		}
	}
}
