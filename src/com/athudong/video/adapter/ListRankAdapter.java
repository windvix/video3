package com.athudong.video.adapter;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.IntroActivity;
import com.athudong.video.R;
import com.athudong.video.ZoneActivity;
import com.athudong.video.bean.Rank;
import com.athudong.video.dialog.ConfirmDialog;

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

	private List<View> views;

	public ListRankAdapter(BaseActivity act, List<Rank> objects, int type) {
		super(act, R.layout.list_rank_template, objects);
		this.act = act;
		this.type = type;
		views = new ArrayList<View>();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = act.createView(R.layout.list_rank_template);
		}

		ImageView img = (ImageView) convertView.findViewById(R.id.head);
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView count = (TextView) convertView.findViewById(R.id.count);
		TextView tyTv = (TextView) convertView.findViewWithTag("unit1");
		TextView sayTv = (TextView) convertView.findViewWithTag("saying1");
		if (position % 2 == 0) {
			convertView.findViewWithTag("unit1").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("saying2").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("unit2").setVisibility(View.GONE);
			convertView.findViewWithTag("saying1").setVisibility(View.GONE);

			tyTv = (TextView) convertView.findViewWithTag("unit1");
		} else {
			convertView.findViewWithTag("unit1").setVisibility(View.GONE);
			convertView.findViewWithTag("saying2").setVisibility(View.GONE);
			convertView.findViewWithTag("unit2").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("saying1").setVisibility(View.VISIBLE);
			tyTv = (TextView) convertView.findViewWithTag("unit2");
		}

		if (type == TYPE_01) {
			tyTv.setText("票");
		} else {
			tyTv.setText("人气");
		}
		Rank rank = getItem(position);

		img.setImageResource(Integer.parseInt(rank.getImg()));
		name.setText(rank.getName());
		count.setText(rank.getCount());

		convertView.setTag(position);
		convertView.findViewById(R.id.guessBtn).setOnClickListener(new GuessClick(convertView));

		views.add(convertView);

		return convertView;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent(act, ZoneActivity.class);
		act.startActivity(intent);
	}

	private class GuessClick implements OnClickListener {
		private View view;

		public GuessClick(View root) {
			this.view = root;
		}

		@Override
		public void onClick(View v) {

			View arrow = view.findViewById(R.id.arrow);
			if (arrow.getVisibility() != View.VISIBLE) {
				hidePop();
				showPop(view);
				for(View itemView:views){
					if(itemView!=view){
						itemView.findViewById(R.id.dimView).setVisibility(View.VISIBLE);
					}
				}
			} else {
				hidePop();
			}
		}

	}

	public void hidePop() {
		for (View itemView : views) {
			itemView.findViewById(R.id.dimView).setVisibility(View.INVISIBLE);
			itemView.findViewById(R.id.arrow).setVisibility(View.INVISIBLE);
			itemView.findViewById(R.id.popup).setVisibility(View.GONE);
			itemView.findViewById(R.id.rank_line).setVisibility(View.VISIBLE);
		}
	}

	private void showPop(View root) {
		root.findViewById(R.id.dimView).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.arrow).setVisibility(View.VISIBLE);
		root.findViewById(R.id.popup).setVisibility(View.VISIBLE);
		root.findViewById(R.id.rank_line).setVisibility(View.GONE);
		initPopupClick(root);
	}
	
	
	private void initPopupClick(final View root){
		View money01 = root.findViewById(R.id.money01);
		View money02 = root.findViewById(R.id.money02);
		View money03 = root.findViewById(R.id.money03);
		View money04 = root.findViewById(R.id.money04);
		
		final List<View> mViews = new ArrayList<View>();
		
		mViews.add(money01);
		mViews.add(money02);
		mViews.add(money03);
		mViews.add(money04);
		
		for(View v:mViews){
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					for(View one:mViews){
						if(one!=v){
							one.setBackgroundResource(R.drawable.game_money_default);
						}else{
							one.setBackgroundResource(R.drawable.game_money_selected);
						}
					}
				}
			});
		}
		
		root.findViewById(R.id.confirmBtn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
		
		root.findViewById(R.id.currentMoneyLayout).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	private void showDialog(){
		final ConfirmDialog dialog = new ConfirmDialog(act, R.style.DimDialog, "");
		dialog.show();
		dialog.getLeftBtn().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.getRightBtn().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				act.toast("开发中");
				hidePop();
			}
		});
	}
}
