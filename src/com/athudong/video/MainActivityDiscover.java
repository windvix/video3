package com.athudong.video;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivityDiscover implements OnClickListener{

	private MainActivity act;
	
	private View root;
	
	
	public MainActivityDiscover(MainActivity act, View root){
		this.act = act;
		this.root = root;
		
		root.findViewById(R.id.selectBtn).setOnClickListener(this);
		root.findViewById(R.id.weekBtn).setOnClickListener(this);
		root.findViewById(R.id.actBtn).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.selectBtn){
			Intent intent = new Intent(act, SelectActivity.class);
			intent.putExtra("exist", "exist");
			act.startActivity(intent);
		}else if(id==R.id.weekBtn){
			Intent intent = new Intent(act, WeekActivity.class);
			act.startActivity(intent);
		}
	}
}
