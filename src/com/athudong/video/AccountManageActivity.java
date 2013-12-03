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

public class AccountManageActivity extends BaseActivity {

	private ImageView headImg;

	private TextView nameTv;

	private TextView ageTv;

	private TextView starTv;

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
		findViewById(R.id.backBtn).setOnClickListener(this);
		findViewById(R.id.no_use).requestFocus();
		findViewById(R.id.editBtn).setOnClickListener(this);
		user = getUser();

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

	private void setEditable(boolean editable) {
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
		}else{
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
		String path = getTestPath() + user.getId() + "_head.jpg";
		Bitmap bitmap = readBitmapAutoSize(path, headImg.getWidth(), headImg.getHeight());
		headImg.setImageBitmap(bitmap);
		nameTv.setText(user.getName());
		ageTv.setText(user.getAge());
		starTv.setText(user.getStarSign());
		nameEt.setText(user.getName());

		String birth = user.getBirthday();

		birthEt.setText(birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6, 8));
		sexEt.setText(user.getSex());
		cityEt.setText(user.getCity());
		sayingEt.setText(user.getSaying());
		
		if(User.getSexInt(user.getSex())==User.WOMAN){
			sexImg.setImageResource(R.drawable.icon_woman);
		}else{
			sexImg.setImageResource(R.drawable.icon_man);
		}
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.backBtn) {
			finish();
		} else if (id == R.id.editBtn) {
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
