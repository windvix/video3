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
		//竞猜对话框和排行榜使用的是同一套布局文件
		setContentView(R.layout.list_rank_template);
		
		//可取消的对话框
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		
		/**
		 * 手动设置对话框的宽度为屏幕宽度
		 */
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
		
		// 因为排行榜的第一名，第二名、第三名的图标不一样，所以判断当前位置，特殊显示排名数字
		if(position==0){
			// 显示第一名图标
			convertView.findViewWithTag("rank01").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		}else if(position==1){
			// 显示第2名图标
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		}else if(position==2){
			// 显示第3名图标
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.VISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.INVISIBLE);
		}else{
			// 显示通用的排名图标
			convertView.findViewWithTag("rank01").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank02").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank03").setVisibility(View.INVISIBLE);
			convertView.findViewWithTag("rank04").setVisibility(View.VISIBLE);
			((TextView)convertView.findViewWithTag("rank04")).setText((position+1)+"");
		}
		
		
		// 设置头像
		Bitmap b = act.readBitmapAutoSize(rank.getImg(), img.getWidth(), img.getHeight());
		img.setImageBitmap(b);
		name.setText(rank.getName());
		count.setText(rank.getCount());
		
		
		// 设置明星简短动态
		String say = rank.getSaying();
		if(say.length()>9){
			say = say.substring(0, 10)+"...";
		}
		sayTv.setText(say);
		
		// 设置名称
		name.setText(rank.getName());
		count.setText(rank.getCount());

		convertView.setTag(position);
		
		
		this.root = convertView;
		
		
		showPop(convertView);
	}

	/**
	 * 显示模版下关于竞猜的相关内容(排行榜布局文件默认将此部分隐藏起来了)
	 */
	private void showPop(View root) {
		root.findViewById(R.id.dimView).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.arrow).setVisibility(View.VISIBLE);
		root.findViewById(R.id.popup).setVisibility(View.VISIBLE);
		root.findViewById(R.id.popup).setBackgroundColor(act.getResources().getColor(R.color.theme_color));
		root.findViewById(R.id.rank_line).setVisibility(View.GONE);
		initPopupClick(root);
	}
	
	
	//初始化竞猜模版的事件
	private void initPopupClick(final View root) {
		
		//四个竞猜的选项，不同的星币
		View money01 = root.findViewById(R.id.money01);
		View money02 = root.findViewById(R.id.money02);
		View money03 = root.findViewById(R.id.money03);
		View money04 =  root.findViewById(R.id.money04);

		final List<View> mViews = new ArrayList<View>();

		mViews.add(money01);
		mViews.add(money02);
		mViews.add(money03);
		mViews.add(money04);

		/**
		 * 点击一个选项，改变它的颜色，还原其它三个的颜色
		 */
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

		/**
		 * 确定竞猜按钮
		 */
		root.findViewById(R.id.confirmGuessBtn).setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dismiss();
				//点击确定竞猜后，显示余额不足对话框
				showDialog();
			}});

		/**
		 * 点击我的竞猜，进入我的钱包界面
		 */
		root.findViewById(R.id.myGuessBtn).setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(act, MyWalletActivity.class);
				act.startActivity(intent);
			}
		});
		
		
		/**
		 * 点击取消竞猜按钮
		 */
		root.findViewById(R.id.cancelGuessBtn).setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	/**
	 * 显示余额不足对话框
	 */
	private void showDialog() {
		final ConfirmDialog dialog = new ConfirmDialog(act, R.style.DimDialog, "");
		dialog.show();
		/**
		 * 点击取消对话框显示
		 */
		dialog.getLeftBtn().setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		/**
		 * 点击马上充值按钮
		 */
		dialog.getRightBtn().setOnClickListener(new android.view.View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//单机版，打开支付宝网页，做个样子
				Uri uri = Uri.parse("http://mobile.alipay.com/");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				act.startActivity(it);
				
			}
		});
	}
	
	
	@Override
	public void onClick(View v) {
		//点击竞猜按钮
		dismiss();
	}

}
