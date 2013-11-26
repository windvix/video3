package com.athudong.video;

import com.athudong.video.dialog.ConfirmDialog;

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
					act.finish();
					if(SelectFirstActivity.self!=null){
						SelectFirstActivity.self.finish();
					}
					System.exit(0);// 使虚拟机停止运行并退出程序
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
	}
}
