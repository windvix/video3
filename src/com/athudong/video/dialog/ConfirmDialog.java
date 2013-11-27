package com.athudong.video.dialog;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ConfirmDialog extends Dialog{

	private String msg;
	
	private TextView leftBtn;
	
	private TextView rightBtn;
	
	private TextView msgTv;
	
	public ConfirmDialog(BaseActivity act, int  style, String msg) {
		super(act, style);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
		this.msg = msg;
		
		setContentView(R.layout.dialog_confirm);
		
		msgTv = (TextView)findViewById(R.id.msgTv);
		leftBtn = (TextView)findViewById(R.id.leftBtn);
		rightBtn = (TextView)findViewById(R.id.rightBtn);
		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		lp.width = act.getScreenWidth();
		
		onWindowAttributesChanged(lp);
		
		if(msg!=null){
			if(!msg.equals("")){
				msgTv.setText(msg);
			}
		}
	}
	
	
	public TextView getLeftBtn(){
		return leftBtn;
	}
	
	public TextView getRightBtn(){
		return rightBtn;
	}

}