package com.athudong.video.adapter;

import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.IntroActivity;
import com.athudong.video.R;
import com.athudong.video.ZoneActivity;
import com.athudong.video.bean.Rank;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * adapter: 排行榜
 */
public class ListRankAdapter extends ArrayAdapter<Rank> implements OnClickListener {


	private BaseActivity act;
	
	
	private int type = 0;

	public static final int TYPE_01 = 1;
	public static final int TYPE_02 = 2;
	
	public ListRankAdapter(BaseActivity act, List<Rank> objects, int type) {
		super(act, R.layout.list_rank_template, objects);
		this.act = act;
		this.type = type;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			if (position % 2 == 0) {
				convertView = act.createView(R.layout.list_rank_template);
			} else {
				convertView = act.createView(R.layout.list_rank_template_02);
			}
		} else {
			if(position%2==0){
				if(convertView.findViewWithTag("one")==null){
					convertView = act.createView(R.layout.list_rank_template);
				}
			}else{
				if(convertView.findViewWithTag("two")==null){
					convertView = act.createView(R.layout.list_rank_template_02);
				}
			}
			
		}
		ImageView img = (ImageView) convertView.findViewById(R.id.head);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView count = (TextView) convertView.findViewById(R.id.count);
		TextView tyTv = (TextView)convertView.findViewWithTag("unit");
		
		
		
		if(type==TYPE_01){
			tyTv.setText("票");
		}else{
			tyTv.setText("人气");
		}
		Rank rank = getItem(position);

		img.setImageResource(Integer.parseInt(rank.getImg()));
		name.setText(rank.getName());
		count.setText(rank.getCount());

		convertView.findViewById(R.id.guessBtn).setOnClickListener(this);

		convertView.setOnClickListener(this);
		
		

		return convertView;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.guessBtn) {

		} else {
			Intent intent = new Intent(act, ZoneActivity.class);
			act.startActivity(intent);
		}
	}
}
