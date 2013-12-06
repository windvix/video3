package com.athudong.video.adapter;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.MyWalletActivity;
import com.athudong.video.R;
import com.athudong.video.ShopActivity;
import com.athudong.video.ZoneActivity;
import com.athudong.video.bean.Rank;
import com.athudong.video.dialog.ConfirmDialog;
import com.athudong.video.dialog.GameGuessDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * adapter: 排行榜(用于主界面第二个tab的两个排行榜)
 */
public class ListRankAdapter extends ArrayAdapter<Rank> implements OnClickListener {

	private BaseActivity act;

	private int type = 0;

	/**
	 * 票数旁类型
	 */
	public static final int TYPE_01 = 1;

	/**
	 * 人气旁类型
	 */
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

		// 排行榜单行和双行的颜色有差别，对显示进行处理
		if (position % 2 == 0) {
			convertView.findViewWithTag("unit1").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("saying2").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("unit2").setVisibility(View.GONE);
			convertView.findViewWithTag("saying1").setVisibility(View.GONE);

			tyTv = (TextView) convertView.findViewWithTag("unit1");
			sayTv = (TextView) convertView.findViewWithTag("saying2");
		} else {
			convertView.findViewWithTag("unit1").setVisibility(View.GONE);
			convertView.findViewWithTag("saying2").setVisibility(View.GONE);
			convertView.findViewWithTag("unit2").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("saying1").setVisibility(View.VISIBLE);
			tyTv = (TextView) convertView.findViewWithTag("unit2");
		}

		// 票数榜显示票数
		if (type == TYPE_01) {
			tyTv.setText("票");
		}
		// 人气榜显示人气
		else {
			tyTv.setText("人气");
		}
		final Rank rank = getItem(position);

		// 设置头像
		Bitmap b = act.readBitmapAutoSize(rank.getImg(), img.getWidth(), img.getHeight());
		img.setImageBitmap(b);

		// 设置名称
		name.setText(rank.getName());
		count.setText(rank.getCount());

		// 设置明星简短动态
		String say = rank.getSaying();
		if (say.length() > 9) {
			say = say.substring(0, 10) + "...";
		}
		sayTv.setText(say);

		convertView.setTag(position);

		// 竞猜/送花按钮
		View guessBtn = convertView.findViewById(R.id.guessBtn);

		/**
		 * 判断显示的是票数/人气，从而显示竞猜/送花按钮
		 */
		if (TYPE_01 == type) {
			final int pos = position;
			// 点击竞猜按钮，显示竞猜对话框
			guessBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					GameGuessDialog dialog = new GameGuessDialog(act, rank, pos);
					dialog.show();
				}
			});
		} else {
			((TextView) guessBtn.findViewWithTag("text")).setText("送花");
			guessBtn.findViewWithTag("gold").setVisibility(View.GONE);
			guessBtn.findViewWithTag("flower").setVisibility(View.VISIBLE);
			guessBtn.setOnClickListener(new OnClickListener() {
				// 点击送花按钮，打开明星商城界面
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(act, ShopActivity.class);
					act.startActivity(intent);
				}
			});
		}

		// 因为排行榜的第一名，第二名、第三名的图标不一样，所以判断当前位置，特殊显示排名数字
		if (position == 0) {
			// 显示第一名图标
			convertView.findViewWithTag("rank01").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		} else if (position == 1) {
			// 显示第二名图标
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		} else if (position == 2) {
			// 显示第三名图标
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		} else {
			// 显示通用的排名图标
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.VISIBLE);
			((TextView) convertView.findViewWithTag("rank04")).setText((position + 1) + "");
		}

		views.add(convertView);

		return convertView;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent(act, ZoneActivity.class);
		act.startActivity(intent);
	}

}
