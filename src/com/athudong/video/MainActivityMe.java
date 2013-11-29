package com.athudong.video;

import com.athudong.video.dialog.ConfirmDialog;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 我界面操作
 */
public class MainActivityMe implements OnClickListener{

	private View root;
	
	private BaseActivity act;
	
	
	public MainActivityMe(BaseActivity act, View root){
		this.act = act;
		this.root = root;
		
		root.findViewById(R.id.meBtn01).setOnClickListener(this);
		root.findViewById(R.id.meBtn02).setOnClickListener(this);
		root.findViewById(R.id.meBtn03).setOnClickListener(this);
		root.findViewById(R.id.meBtn04).setOnClickListener(this);
		root.findViewById(R.id.meBtn05).setOnClickListener(this);
		root.findViewById(R.id.meBtn06).setOnClickListener(this);
		root.findViewById(R.id.headLayout).setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.meBtn06){
			final ConfirmDialog dialog = new ConfirmDialog(act, R.style.DimDialog, "确定退出吗？");
			dialog.getLeftBtn().setText("确定");
			dialog.getRightBtn().setText("取消");
			dialog.getLeftBtn().setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Intent intent = new Intent(act, RegisterActivity.class);
					act.startActivity(intent);
				}
			});
			dialog.getRightBtn().setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			dialog.show();
		}else if(id==R.id.meBtn01){
			Intent intent  = new Intent(act, AccountManageActivity.class);
			act.startActivity(intent);
			
		}else if(id==R.id.meBtn02){
			Intent intent  = new Intent(act, NotificationActivity.class);
			act.startActivity(intent);
		}else if(id==R.id.meBtn03){
			Intent intent  = new Intent(act, ShopActivity.class);
			act.startActivity(intent);
		}else if(id==R.id.meBtn04){
			Intent intent  = new Intent(act, MyWalletActivity.class);
			act.startActivity(intent);
		}else if(id==R.id.meBtn05){
			Intent intent  = new Intent(act, CommonSettingActivity.class);
			act.startActivity(intent);
		}else if(id==R.id.headLayout){
			Intent intent  = new Intent(act, NotificationActivity.class);
			act.startActivity(intent);
		}
	}
}
