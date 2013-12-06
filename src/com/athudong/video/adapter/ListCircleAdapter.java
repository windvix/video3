package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.IntroActivity;
import com.athudong.video.R;
import com.athudong.video.ZoneActivity;
import com.athudong.video.bean.CircleLine;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Adapter: 用于娱乐圈界面的第二个tab和第三个tab列表
 */
public class ListCircleAdapter extends ArrayAdapter<CircleLine> implements OnClickListener {

	private int viewId;

	private BaseActivity act;

	public ListCircleAdapter(BaseActivity act, int layoutId, List<CircleLine> objects) {
		super(act, layoutId, objects);
		this.act = act;
		this.viewId = layoutId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = act.createView(viewId);
		}
		this.notifyDataSetChanged();
		
		
		setView(convertView, getItem(position));
		
		
		return convertView;
	}

	
	private void setView(View convertView, CircleLine circleLine){
		
		View one = convertView.findViewById(R.id.one);
		View two = convertView.findViewById(R.id.two);
		View three = convertView.findViewById(R.id.three);
		
		one.setOnClickListener(this);
		two.setOnClickListener(this);
		three.setOnClickListener(this);
		
		/**
		 * 因为模版中。一行有三个人，所以一个CircleLine对象就有三个人。以下分别初始化每一个部件
		 */
		oneView(one, circleLine.getId1(), circleLine.getName1(), circleLine.getImg1());
		oneView(two, circleLine.getId2(), circleLine.getName2(), circleLine.getImg2());
		oneView(three, circleLine.getId3(), circleLine.getName3(), circleLine.getImg3());
	}
	
	
	private void oneView(View one, String id, String name, String img){
		ImageView imgV = (ImageView)one.findViewWithTag("img");
		TextView nameTv = (TextView)one.findViewWithTag("name");
		
		/**
		 * 加载用户头像
		 */
		Bitmap bm =act.readBitmapAutoSize(img, imgV.getWidth(), imgV.getHeight());
		imgV.setImageBitmap(bm);
		
		nameTv.setText(name);
		
		one.setTag(id);
		
	}
	
	
	
	
	@Override
	public void onClick(View v) {
		Intent intent  = new Intent(act,ZoneActivity.class);
		//明星的个人空间界面，进入时传入明星的ID
		if(v.getTag()!=null){
			intent.putExtra("id", v.getTag().toString());
		}
		
		act.startActivity(intent);
	}

}
