package com.athudong.video;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.athudong.video.bean.User;
import com.athudong.video.task.BaseTask;

/**
 * 账号管理界面
 */
public class AccountManageActivity extends BaseActivity {

	/**
	 * 头像图片
	 */
	private ImageView headImg;

	/**
	 * 昵称
	 */
	private TextView nameTv;

	/**
	 * 年龄
	 */
	private TextView ageTv;

	
	
	private TextView starTv;

	/**
	 * 昵称编辑框
	 */
	private EditText nameEt;

	private EditText birthEt;

	private EditText sexEt;

	private EditText cityEt;

	private EditText sayingEt;
	
	private ImageView sexImg;

	private User user;

	@Override
	protected void initView(Bundle savedInstanceState) {
		setContentView(R.layout.activity_account_manage);
		
		/**
		 * 增加点击事件
		 */
		findViewById(R.id.backBtn).setOnClickListener(this);
		findViewById(R.id.editBtn).setOnClickListener(this);
		
		
		/**
		 * 页面中有EditText，防止页面打开时就弹出软键盘
		 */
		findViewById(R.id.no_use).requestFocus();

		/**
		 * 获取当前用户
		 */
		user = getUser();

		/**
		 * 获取页面中的各组件
		 */
		headImg = (ImageView) findViewById(R.id.headImg);
		nameTv = (TextView) findViewById(R.id.nameTv);
		ageTv = (TextView) findViewById(R.id.ageTv);
		starTv = (TextView) findViewById(R.id.starSignTv);
		nameEt = (EditText) findViewById(R.id.nameEt);
		birthEt = (EditText) findViewById(R.id.birthEt);
		sexEt = (EditText) findViewById(R.id.sexEt);
		cityEt = (EditText) findViewById(R.id.cityEt);
		sayingEt = (EditText) findViewById(R.id.sayingEt);
		sexImg = (ImageView)findViewById(R.id.sexImg);
		
		
		findViewById(R.id.weiboBtn).setOnClickListener(this);
		findViewById(R.id.qqBtn).setOnClickListener(this);

		initContent();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				setEditable(false);
			}
		}, 500);
	}

	/**
	 * 设置组件的可编辑状态
	 */
	private void setEditable(boolean editable) {
		//组件不可编辑状态
		if (!editable) {
			nameEt.setClickable(false);
			nameEt.setFocusable(false);

			birthEt.setClickable(false);
			birthEt.setEnabled(false);
			birthEt.setFocusable(false);
			
			sexEt.setClickable(false);
			sexEt.setEnabled(false);
			sexEt.setFocusable(false);
			
			cityEt.setClickable(false);
			cityEt.setEnabled(false);
			cityEt.setFocusable(false);
			
			sayingEt.setClickable(false);
			sayingEt.setFocusable(false);
		}
		//组件可编辑状态
		else{
			nameEt.setClickable(true);
			nameEt.setEnabled(true);
			nameEt.setFocusable(true);

			birthEt.setClickable(true);
			birthEt.setFocusable(true);
			birthEt.setOnClickListener(this);
			
			sexEt.setClickable(true);
			sexEt.setFocusable(true);
			sexEt.setOnClickListener(this);
			
			cityEt.setClickable(true);
			cityEt.setOnClickListener(this);
			cityEt.setFocusable(true);
			
			sayingEt.setClickable(true);
			sayingEt.setEnabled(true);
			sayingEt.setFocusable(true);
		}
	}

	/**
	 * 初始化内容
	 */
	private void initContent() {
		
		/**
		 * 加载头像
		 */
		String path = getTestPath() + user.getId() + "_head.jpg";
		Bitmap bitmap = readBitmapAutoSize(path, headImg.getWidth(), headImg.getHeight());
		headImg.setImageBitmap(bitmap);
		
		
		/**
		 * 写入名称，年龄等
		 */
		nameTv.setText(user.getName());
		ageTv.setText(user.getAge());
		starTv.setText(user.getStarSign());
		nameEt.setText(user.getName());

		
		/**
		 * 拼接生日字符串，字符串原本格式：yyyyMMdd
		 */
		String birth = user.getBirthday();
		birthEt.setText(birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6, 8));
		sexEt.setText(user.getSex());
		cityEt.setText(user.getCity());
		sayingEt.setText(user.getSaying());
		
		/**
		 * 判断用户的性别。根据性别显示特定的性别图片
		 */
		if(User.getSexInt(user.getSex())==User.WOMAN){
			sexImg.setImageResource(R.drawable.icon_woman);
		}else{
			sexImg.setImageResource(R.drawable.icon_man);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		//返回按钮
		if (id == R.id.backBtn) {
			finish();
		}
		//编辑按钮 
		else if (id == R.id.editBtn) {
			TextView tv = (TextView) v;
			if (tv.getText().toString().contains("完")) {
				setEditable(false);
				tv.setText("编辑");
			} else {
				setEditable(true);
				tv.setText("完成");
			}
		}
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
