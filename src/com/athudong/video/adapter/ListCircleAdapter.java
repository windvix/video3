package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.IntroActivity;
import com.athudong.video.R;
import com.athudong.video.bean.CircleLine;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Adapter: 娱乐圈明星列表
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

		convertView.findViewById(R.id.starBtn01).setOnClickListener(this);
		convertView.findViewById(R.id.starBtn02).setOnClickListener(this);
		convertView.findViewById(R.id.starBtn03).setOnClickListener(this);
		return convertView;
	}

	@Override
	public void onClick(View v) {
		Intent intent  = new Intent(act, IntroActivity.class);
		act.startActivity(intent);
	}

}
