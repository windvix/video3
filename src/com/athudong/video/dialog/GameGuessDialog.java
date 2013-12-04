package com.athudong.video.dialog;

import java.util.ArrayList;
import java.util.List;

import com.athudong.video.BaseActivity;
import com.athudong.video.MyWalletActivity;
import com.athudong.video.R;
import com.athudong.video.bean.Rank;
import com.athudong.video.bean.User;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GameGuessDialog extends Dialog implements OnClickListener{

	private BaseActivity act;
	
	private Rank rank;
	
	private View root;
	
	public GameGuessDialog(BaseActivity act, Rank rank, int position) {
		super(act, R.style.DimDialog);
		this.rank = rank;
		this.act = act;
		
		setContentView(R.layout.list_rank_template);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		
		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		lp.width = act.getScreenWidth();
		
		onWindowAttributesChanged(lp);
		
		findViewById(R.id.guessBtn).setOnClickListener(this);

		
		
		View convertView = findViewById(R.id.rootLayout);

		convertView.findViewById(R.id.main).setBackgroundColor(act.getResources().getColor(R.color.theme_color));
		
		
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
			sayTv = (TextView) convertView.findViewWithTag("saying2");
		} else {
			convertView.findViewWithTag("unit1").setVisibility(View.GONE);
			convertView.findViewWithTag("saying2").setVisibility(View.GONE);
			convertView.findViewWithTag("unit2").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("saying1").setVisibility(View.VISIBLE);
			tyTv = (TextView) convertView.findViewWithTag("unit2");
		}
		
		if(position==0){
			convertView.findViewWithTag("rank01").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		}else if(position==1){
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		}else if(position==2){
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		}else{
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.VISIBLE);
			((TextView)convertView.findViewWithTag("rank04")).setText((position+1)+"");
		}
		
		
		Bitmap b = act.readBitmapAutoSize(rank.getImg(), img.getWidth(), img.getHeight());
		
		img.setImageBitmap(b);
		name.setText(rank.getName());
		count.setText(rank.getCount());
		
		String say = rank.getSaying();
		if(say.length()>9){
			say = say.substring(0, 10)+"...";
		}
		sayTv.setText(say);
		
		name.setText(rank.getName());
		count.setText(rank.getCount());

		convertView.setTag(position);
		
		
		this.root = convertView;
		
		
		showPop(convertView);
		
		
	}

	
	private void showPop(View root) {
		root.findViewById(R.id.dimView).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.arrow).setVisibility(View.VISIBLE);
		root.findViewById(R.id.popup).setVisibility(View.VISIBLE);
		root.findViewById(R.id.popup).setBackgroundColor(act.getResources().getColor(R.color.theme_color));
		root.findViewById(R.id.rank_line).setVisibility(View.GONE);
		initPopupClick(root);
	}
	
	
	
	private void initPopupClick(final View root) {
		View money01 = root.findViewById(R.id.money01);
		View money02 = root.findViewById(R.id.money02);
		View money03 = root.findViewById(R.id.money03);
		View money04 =  root.findViewById(R.id.money04);

		final List<View> mViews = new ArrayList<View>();

		mViews.add(money01);
		mViews.add(money02);
		mViews.add(money03);
		mViews.add(money04);

		for (View v : mViews) {
			v.setOnClickListener(new android.view.View.OnClickListener() {
				@Override
				public void onClick(View v) {
					for (View one : mViews) {
						String text = ((TextView)one.findViewWithTag("text")).getText().toString();
						if (one != v) {
							one.setBackgroundResource(R.drawable.game_money_default);
							((TextView)one.findViewWithTag("text")).setText(Html.fromHtml("<font color='#CC1F28'>"+text+"</font>"));
						} else {
							one.setBackgroundResource(R.drawable.game_money_selected);
							((TextView)one.findViewWithTag("text")).setText(Html.fromHtml("<font color='#FFFFFF'>"+text+"</font>"));
						}
					}
				}
			});
		}

		root.findViewById(R.id.confirmGuessBtn).setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				showDialog();
			}});

		root.findViewById(R.id.myGuessBtn).setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(act, MyWalletActivity.class);
				act.startActivity(intent);
			}
		});
		
		root.findViewById(R.id.cancelGuessBtn).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	
	private void showDialog() {
		final ConfirmDialog dialog = new ConfirmDialog(act, R.style.DimDialog, "");
		dialog.show();
		dialog.getLeftBtn().setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.getRightBtn().setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("http://mobile.alipay.com/");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				act.startActivity(it);
				
			}
		});
	}
	
	
	@Override
	public void onClick(View v) {
		dismiss();
	}

}
