package com.athudong.video;


import com.athudong.video.bean.User;
import com.athudong.video.dialog.BigPictureDialog;
import com.athudong.video.util.TestDataUtil;

import android.view.View;
import android.view.View.OnClickListener;

/**
 * 个人空间图片
 */
public class ZoneActivityPic implements OnClickListener{

	private ZoneActivity act;
	private View root;

	private User user;
	
	
	public ZoneActivityPic(final ZoneActivity act, View root) {
		this.act = act;
		this.root = root;
		user = TestDataUtil.getRandomUser();
		
		root.findViewById(R.id.pic01).setOnClickListener(this);
		root.findViewById(R.id.pic02).setOnClickListener(this);
		root.findViewById(R.id.pic03).setOnClickListener(this);
		root.findViewById(R.id.pic04).setOnClickListener(this);
		root.findViewById(R.id.pic05).setOnClickListener(this);
		root.findViewById(R.id.pic06).setOnClickListener(this);
		root.findViewById(R.id.pic07).setOnClickListener(this);
		root.findViewById(R.id.pic08).setOnClickListener(this);
		root.findViewById(R.id.pic09).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		String path = act.getTestPath()+user.getId()+"_01.jpg";
	
		BigPictureDialog dialog = new BigPictureDialog(act, "", path);
		dialog.show();
	}
	
}
