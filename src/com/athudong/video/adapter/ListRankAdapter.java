package com.athudong.video.adapter;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.MyWalletActivity;
import com.athudong.video.R;
import com.athudong.video.ZoneActivity;
import com.athudong.video.bean.Rank;
import com.athudong.video.dialog.ConfirmDialog;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
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
				for (View itemView : views) {
					if (itemView != view) {
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

	private void initPopupClick(final View root) {
		TextView money01 = (TextView) root.findViewById(R.id.money01);
		TextView money02 = (TextView) root.findViewById(R.id.money02);
		TextView money03 = (TextView) root.findViewById(R.id.money03);
		TextView money04 = (TextView) root.findViewById(R.id.money04);

		final List<TextView> mViews = new ArrayList<TextView>();

		mViews.add(money01);
		mViews.add(money02);
		mViews.add(money03);
		mViews.add(money04);

		for (View v : mViews) {
			v.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					for (TextView one : mViews) {
						String text = one.getText().toString();
						if (one != v) {
							one.setBackgroundResource(R.drawable.game_money_default);
							one.setText(Html.fromHtml("<font color='#CC1F28'>"+text+"</font>"));
						} else {
							one.setBackgroundResource(R.drawable.game_money_selected);
							one.setText(Html.fromHtml("<font color='#FFFFFF'>"+text+"</font>"));
						}
					}
				}
			});
		}

		root.findViewById(R.id.confirmGuessBtn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog();
			}
		});

		root.findViewById(R.id.myGuessBtn).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(act, MyWalletActivity.class);
				act.startActivity(intent);
			}
		});
		
		root.findViewById(R.id.cancelGuessBtn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hidePop();
			}
		});
	}

	private void showDialog() {
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
				Uri uri = Uri.parse("http://mobile.alipay.com/");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				act.startActivity(it);
				hidePop();
			}
		});
	}
}
