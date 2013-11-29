package com.athudong.video.adapter;

import java.util.ArrayList;

import com.athudong.video.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 活动列表适配器
 * @author 谢启祥
 */
public class UEAdap extends BaseAdapter {

	private Context context;
	
	public UEAdap(Context context){
		this.context = context;
	}
	
	private ArrayList<String> arrayList;
	
	public void setArrayList(ArrayList<String> arrayList) {
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		if(arrayList==null){
			return 3;
		}
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold viewHold = null;
		if(convertView==null){
			convertView = View.inflate(context,R.layout.viewpage_item,null);
		    viewHold = new ViewHold();
		    viewHold.ivPoster = (ImageView) convertView.findViewById(R.id.event_poster);
		    convertView.setTag(viewHold);
		}else {
			viewHold = (ViewHold) convertView.getTag();
		}
		if(position==0){
			viewHold.ivPoster.setBackgroundResource(R.drawable.event1);
		}else if(position==1){
			viewHold.ivPoster.setBackgroundResource(R.drawable.event2);
		}else if(position==2){
			viewHold.ivPoster.setBackgroundResource(R.drawable.event3);
		}
		return convertView;
	}
	
	private class ViewHold{
		public ImageView ivPoster;
	}
}
