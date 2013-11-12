package com.athudong.video.dialog;

import com.athudong.video.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 等待框
 */
public class LoadingDialog extends ProgressDialog {

	private TextView loadText;;

	public LoadingDialog(Context context, String msg, boolean cancelable) {
		super(context, R.style.DimDialog);
		setCancelable(cancelable);
		setCanceledOnTouchOutside(false);
		this.msg = msg;
	}

	private String msg;

	@Override
	public void setMessage(CharSequence message) {
		this.msg = message.toString();
		if (loadText != null) {
			loadText.setText(msg);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_loading);
		loadText = ((TextView) findViewById(R.id.loading_text));
		loadText.setText(msg);
		findViewById(R.id.loading_layout).getBackground().setAlpha(150);
		setOnShowListener(new OnShowListener() {

			@Override
			public void onShow(DialogInterface dialog) {
				ImageView image = (ImageView) LoadingDialog.this.findViewById(R.id.loading_img);
				Animation anim = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				loadText.setText(msg);
				anim.setRepeatCount(Animation.INFINITE); // 设置INFINITE，对应值-1，代表重复次数为无穷次
				anim.setDuration(1000); // 设置该动画的持续时间，毫秒单位
				anim.setInterpolator(new LinearInterpolator()); // 设置一个插入器，或叫补间器，用于完成从动画的一个起始到结束中间的补间部分
				image.startAnimation(anim);
			}
		});
	}
}
