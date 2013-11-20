package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.IntroActivity;
import com.athudong.video.R;
import com.athudong.video.ZoneActivity;
import com.athudong.video.bean.Rank;

import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * adapter: 排行榜
 */
public class ListRankAdapter extends ArrayAdapter<Rank> implements OnTouchListener , OnClickListener{

	private int viewId;

	private BaseActivity act;

	public ListRankAdapter(BaseActivity act, int layoutId, List<Rank> objects) {
		super(act, layoutId, objects);
		this.act = act;
		this.viewId = layoutId;
	}

	private static final String TAG_NUM = "num";
	private static final String TAG_CONTENT = "content";

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = act.createView(viewId);
		}
		if (position == 0) {
			convertView.findViewById(R.id.one).setVisibility(View.VISIBLE);
			convertView.findViewWithTag(TAG_NUM).setVisibility(View.GONE);
			convertView.findViewWithTag(TAG_CONTENT).setBackgroundResource(R.drawable.list_rank_item_bg_default);

		} else if (position % 2 == 1) {
			convertView.findViewById(R.id.one).setVisibility(View.GONE);
			convertView.findViewWithTag(TAG_NUM).setBackgroundResource(R.drawable.list_rank_num_bg_selected);
			convertView.findViewWithTag(TAG_NUM).setVisibility(View.VISIBLE);
			convertView.findViewWithTag(TAG_CONTENT).setBackgroundResource(R.drawable.list_rank_item_bg_selected);

		} else {
			convertView.findViewById(R.id.one).setVisibility(View.GONE);
			convertView.findViewWithTag(TAG_NUM).setBackgroundResource(R.drawable.list_rank_num_bg_default);
			convertView.findViewWithTag(TAG_NUM).setVisibility(View.VISIBLE);
			convertView.findViewWithTag(TAG_CONTENT).setBackgroundResource(R.drawable.list_rank_item_bg_default);
		}

		TextView num = (TextView) convertView.findViewWithTag(TAG_NUM);
		ImageView img = (ImageView) convertView.findViewById(R.id.head);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView count = (TextView) convertView.findViewById(R.id.count);

		Rank rank = getItem(position);
		num.setText(rank.getNum() + "");
		img.setImageResource(Integer.parseInt(rank.getImg()));
		name.setText(rank.getName());
		count.setText(rank.getCount());
			
		
		convertView.findViewById(R.id.guessBtn).setOnTouchListener(this);
		
		convertView.setOnClickListener(this);
		
		return convertView;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			v.findViewWithTag("default").setVisibility(View.INVISIBLE);
		} else{
			v.findViewWithTag("default").setVisibility(View.VISIBLE);
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent  intent  = new Intent(act, ZoneActivity.class);
		act.startActivity(intent);
	}
}
