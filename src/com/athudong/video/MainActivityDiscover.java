package com.athudong.video;

import android.view.View;
import android.view.View.OnClickListener;

public class MainActivityDiscover implements OnClickListener{

	private MainActivity act;
	
	private View root;
	
	
	public MainActivityDiscover(MainActivity act, View root){
		this.act = act;
		this.root = root;
		
		root.findViewById(R.id.left_play_btn).setOnClickListener(this);
		root.findViewById(R.id.leftThumbBtn).setOnClickListener(this);
		root.findViewById(R.id.right_play_btn).setOnClickListener(this);
		root.findViewById(R.id.rightThumbBtn).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		
	}
}
