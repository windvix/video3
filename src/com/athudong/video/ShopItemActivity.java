package com.athudong.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.athudong.video.dialog.ConfirmDialog;
import com.athudong.video.task.BaseTask;

public class ShopItemActivity extends BaseActivity{


	private ImageView imgV;
	
	private TextView nameTv;
	
	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_shop_item);
		
		imgV = (ImageView)findViewById(R.id.scene_detail_img);
		nameTv = (TextView)findViewById(R.id.scene_detail_name);
		
		int resId = getIntent().getIntExtra("resId", R.drawable.icon_gift_flower);
		String name = getIntent().getStringExtra("name");
		
		imgV.setImageResource(resId);
		nameTv.setText(name);
		
		
		findViewById(R.id.scene_detail_back_btn).setOnClickListener(this);
		findViewById(R.id.scene_detail_buy_btn).setOnClickListener(this);
	}

	
	

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.scene_detail_back_btn){
			// 返回
			finish();
		}else if(id==R.id.scene_detail_buy_btn){

			showDialog();
			
		}
	}
	
	
	private void showDialog(){
		final ConfirmDialog dialog = new ConfirmDialog(this, R.style.DimDialog, "星币余额不足，无法购买");
		TextView leftBtn = dialog.getLeftBtn();
		leftBtn.setText("取消");
		TextView rightBtn = dialog.getRightBtn();
		rightBtn.setText("马上充值");
		
		leftBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				Uri uri = Uri.parse("http://mobile.alipay.com/");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
			}
		});
		dialog.show();
	}
	
	@Override
	protected void beforeEveryVisable() {
		
	}

	@Override
	protected void afterEveryInVisable() {
		
	}

	@Override
	protected void beforeDestory() {
		
	}

	@Override
	public void executeTaskResult(BaseTask task, Object data) {
		
	}

}
