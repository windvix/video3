package com.athudong.video;

import com.athudong.video.bean.User;
import com.athudong.video.dialog.BigPictureDialog;
import com.athudong.video.util.TestDataUtil;

import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 个人空间图片操作
 */
public class ZoneActivityPic implements OnClickListener {

	private ZoneActivity act;
	private View root;

	private User user;

	/**
	 * 所有图片
	 */
	private ImageView img01;
	private ImageView img02;
	private ImageView img03;
	private ImageView img04;
	private ImageView img05;
	private ImageView img06;
	private ImageView img07;
	private ImageView img08;
	private ImageView img09;

	private View pic01;
	private View pic02;
	private View pic03;
	private View pic04;
	private View pic05;
	private View pic06;
	private View pic07;
	private View pic08;
	private View pic09;

	public ZoneActivityPic(final ZoneActivity act, final View root, final User user) {
		this.act = act;
		this.root = root;
		//当前用户
		this.user = user;

		pic01 = root.findViewById(R.id.pic01);
		pic02 = root.findViewById(R.id.pic02);
		pic03 = root.findViewById(R.id.pic03);
		pic04 = root.findViewById(R.id.pic04);
		pic05 = root.findViewById(R.id.pic05);
		pic06 = root.findViewById(R.id.pic06);
		pic07 = root.findViewById(R.id.pic07);
		pic08 = root.findViewById(R.id.pic08);
		pic09 = root.findViewById(R.id.pic09);

		img01 = (ImageView) pic01.findViewWithTag("img");
		img02 = (ImageView) pic02.findViewWithTag("img");
		img03 = (ImageView) pic03.findViewWithTag("img");
		img04 = (ImageView) pic04.findViewWithTag("img");
		img05 = (ImageView) pic05.findViewWithTag("img");
		img06 = (ImageView) pic06.findViewWithTag("img");
		img07 = (ImageView) pic07.findViewWithTag("img");
		img08 = (ImageView) pic08.findViewWithTag("img");
		img09 = (ImageView) pic09.findViewWithTag("img");

		img01.setImageBitmap(null);
		img02.setImageBitmap(null);
		img03.setImageBitmap(null);
		img04.setImageBitmap(null);
		img05.setImageBitmap(null);
		img06.setImageBitmap(null);
		img07.setImageBitmap(null);
		img08.setImageBitmap(null);
		img09.setImageBitmap(null);

		
		/**
		 * 延迟加载图片和图片点击事件
		 */
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				
				String p1 = act.getTestPath() + user.getId() + "_01.jpg";
				String p2 = act.getTestPath() + user.getId() + "_02.jpg";
				String p3 = act.getTestPath() + user.getId() + "_03.jpg";
				String p4 = act.getTestPath() + user.getId() + "_04.jpg";
				String p5 = act.getTestPath() + user.getId() + "_05.jpg";
				String p6 = act.getTestPath() + user.getId() + "_06.jpg";
				String p7 = act.getTestPath() + user.getId() + "_07.jpg";
				String p8 = act.getTestPath() + user.getId() + "_08.jpg";

				Bitmap b1 = act.readBitmapAutoSize(p1, img01.getWidth(), img02.getHeight());
				Bitmap b2 = act.readBitmapAutoSize(p2, img01.getWidth(), img02.getHeight());
				Bitmap b3 = act.readBitmapAutoSize(p3, img01.getWidth(), img02.getHeight());
				Bitmap b4 = act.readBitmapAutoSize(p4, img01.getWidth(), img02.getHeight());
				Bitmap b5 = act.readBitmapAutoSize(p5, img01.getWidth(), img02.getHeight());
				Bitmap b6 = act.readBitmapAutoSize(p6, img01.getWidth(), img02.getHeight());
				Bitmap b7 = act.readBitmapAutoSize(p7, img01.getWidth(), img02.getHeight());
				Bitmap b8 = act.readBitmapAutoSize(p8, img01.getWidth(), img02.getHeight());

				if (b1 != null) {
					img01.setImageBitmap(b1);
					pic01.setTag(p1);
					pic01.setOnClickListener(ZoneActivityPic.this);
				}
				if (b2 != null) {
					img02.setImageBitmap(b2);
					pic02.setTag(p2);
					pic02.setOnClickListener(ZoneActivityPic.this);
				}
				if (b3 != null) {
					img03.setImageBitmap(b3);
					pic03.setTag(p3);
					pic03.setOnClickListener(ZoneActivityPic.this);
				}
				if (b4 != null) {
					img04.setImageBitmap(b4);
					pic04.setTag(p4);
					pic04.setOnClickListener(ZoneActivityPic.this);
				}
				if (b5 != null) {
					img05.setImageBitmap(b5);
					pic05.setTag(p5);
					pic05.setOnClickListener(ZoneActivityPic.this);
				}
				if (b6 != null) {
					img06.setImageBitmap(b6);
					pic06.setTag(p6);
					pic06.setOnClickListener(ZoneActivityPic.this);
				}
				if (b7 != null) {
					img07.setImageBitmap(b7);
					pic07.setTag(p7);
					pic07.setOnClickListener(ZoneActivityPic.this);
				}
				if (b8 != null) {
					img08.setImageBitmap(b8);
					pic08.setTag(p8);
					pic08.setOnClickListener(ZoneActivityPic.this);
				}
			}
		}, 500);
	}

	@Override
	public void onClick(View v) {
		String path = act.getTestPath() + user.getId() + "_01.jpg";
		if (v.getTag() != null) {
			path = v.getTag().toString();

		}
		/**
		 * 点击图片，显示大图片对话框
		 */
		BigPictureDialog dialog = new BigPictureDialog(act, "", path);
		dialog.show();
	}

}
