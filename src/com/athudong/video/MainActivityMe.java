package com.athudong.video;

import android.view.View;
import android.view.View.OnClickListener;
/**
 * 我界面操作
 */
public class MainActivityMe implements OnClickListener{

	private View root;
	
	private BaseActivity act;
	
	
	public MainActivityMe(BaseActivity act, View root){
		this.act = act;
		this.root = root;
		
		root.findViewById(R.id.meBtn01).setOnClickListener(this);
		root.findViewById(R.id.meBtn02).setOnClickListener(this);
		root.findViewById(R.id.meBtn03).setOnClickListener(this);
		root.findViewById(R.id.meBtn04).setOnClickListener(this);
		root.findViewById(R.id.meBtn05).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		
	}
}
