package com.athudong.video;

import com.athudong.video.bean.User;
import com.athudong.video.dialog.ConfirmDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 主界面第4个tab:我界面操作
 */
public class MainActivityMe implements OnClickListener{

	private View root;
	
	private BaseActivity act;
	
	/**
	 * 个人头像
	 */
	private ImageView headImg;
	
	/**
	 * 我的名称
	 */
	private TextView nameTv;
	
	/**
	 * 我的名称下面的at部分
	 */
	private TextView atNameTv;
	
	/**
	 * 粉丝数
	 */
	private TextView fansCountTv;
	
	
	/**
	 * 我的关注数
	 */
	private TextView focusCountTv;
	
	
	private User currentUser;
	
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
		
		
		headImg = (ImageView)root.findViewById(R.id.headImg);
		nameTv  = (TextView)root.findViewById(R.id.nameTv);
		atNameTv = (TextView)root.findViewById(R.id.atNameTv);
		fansCountTv = (TextView)root.findViewById(R.id.fansTv);
		focusCountTv = (TextView)root.findViewById(R.id.focusTv);
		
		//得到当前的登录用户
		currentUser = act.getUser();
		
		/**
		 * 设置头像
		 */
		String path = act.getTestPath()+currentUser.getId()+"_head.jpg";
		Bitmap bitmap = act.readBitmapAutoSize(path, headImg.getWidth(), headImg.getHeight());
		headImg.setImageBitmap(bitmap);
		
		/**
		 * 设置名称
		 */
		nameTv.setText(currentUser.getName());
		
		//设置at
		atNameTv.setText("@"+currentUser.getName());
		
		//设置粉丝数
		fansCountTv.setText(currentUser.getFans()+"");
		
		//设置关注数
		focusCountTv.setText(currentUser.getFocusCount()+"");
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		//退出登录按钮
		if(id==R.id.meBtn06){
			//退出登录确认对话框
			final ConfirmDialog dialog = new ConfirmDialog(act, R.style.DimDialog, "确定退出吗？");
			dialog.getLeftBtn().setText("确定");
			dialog.getRightBtn().setText("取消");
			
			//点击确定退出按钮，显示注册登录界面（单机版只做简单显示）
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
		}
		
		//账号管理按钮
		else if(id==R.id.meBtn01){
			Intent intent  = new Intent(act, AccountManageActivity.class);
			act.startActivity(intent);
			
		}
		//消息通知按钮
		else if(id==R.id.meBtn02){
			Intent intent  = new Intent(act, NotificationActivity.class);
			act.startActivity(intent);
		}
		//明星商城按钮
		else if(id==R.id.meBtn03){
			Intent intent  = new Intent(act, ShopActivity.class);
			act.startActivity(intent);
		}
		//我的钱包按钮
		else if(id==R.id.meBtn04){
			Intent intent  = new Intent(act, MyWalletActivity.class);
			act.startActivity(intent);
		}
		//通用设置按钮
		else if(id==R.id.meBtn05){
			Intent intent  = new Intent(act, CommonSettingActivity.class);
			act.startActivity(intent);
		}
		//点击头像
		else if(id==R.id.headLayout){
			Intent intent  = new Intent(act, NotificationActivity.class);
			act.startActivity(intent);
		}
	}
}
