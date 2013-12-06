package com.athudong.video.dialog;

import java.io.File;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

/**
 * 资源选择对话框，（从底部弹出，三个选项）
 */
public class SelectResDialog extends Dialog implements android.view.View.OnClickListener {

	private BaseActivity activity;

	/**
	 * 资源类型：图片，（相册，拍照，取消）
	 */
	public static final int TYPE_PIC = 0;
	
	/**
	 * 资源类型：视频，（录制，本地视频，取消）
	 */
	public static final int TYPE_VIDEO = 1;
	
	/**
	 * 资源类型：音频，（录音，本地音频，取消）
	 */
	public static final int TYPE_AUDIO = 2;

	/**
	 * 以下是调用外部程序后的返回码，用于onActivityResult
	 */
	public static final int PIC_SELECT = 3;
	public static final int PIC_TAKE = 4;
	public static final int VIDEO_SELECT = 5;
	public static final int VIDEO_TAKE = 6;
	public static final int AUDIO_SELECT = 7;
	public static final int AUDIO_TAKE = 8;

	private int type;

	public SelectResDialog(BaseActivity activity, int type) {
		super(activity, R.style.BottomDialogStyle);
		this.activity = activity;
		this.type = type;
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.dialog_selectres, null);

		addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setContentView(layout);

		layout.findViewById(R.id.btn_01).setOnClickListener(this);
		layout.findViewById(R.id.btn_02).setOnClickListener(this);
		layout.findViewById(R.id.btn_03).setOnClickListener(this);

		
		/**
		 * 根据创建的类型，改变三个按钮的文本
		 */
		if (type == TYPE_VIDEO) {
			((Button) layout.findViewById(R.id.btn_01)).setText("录制");
			((Button) layout.findViewById(R.id.btn_02)).setText("视频选择");
		}

		if (type == TYPE_AUDIO) {
			((Button) layout.findViewById(R.id.btn_01)).setText("录音");
			((Button) layout.findViewById(R.id.btn_02)).setText("音频选择");
		}

		Window win = getWindow();
		win.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams lp = win.getAttributes();

		lp.width = activity.getScreenWidth();

		onWindowAttributesChanged(lp);

		setCancelable(true);
		setCanceledOnTouchOutside(true);
		
		findViewById(R.id.clickLayout).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		dismiss();
		
		//取消按钮
		if (id == R.id.btn_03) {
			
		} 
		
		//拍照/录制/录音按钮， 不同的类型会调用不同的外部程序完成任务
		else if (id == R.id.btn_01) {
			if (type == TYPE_PIC) {
				takePhoto();
			} else if (type == TYPE_VIDEO) {
				takeVideo();
			} else if (type == TYPE_AUDIO) {
				takeAudio();
			}
		}
		//本地资源选择按钮
		else if (id == R.id.btn_02) {
			if (type == TYPE_PIC) {
				selectPhoto();
			} else if (type == TYPE_VIDEO) {
				selectVideo();
			} else if (type == TYPE_AUDIO) {
				selectAudio();
			}
		}
	}

	/**
	 * 调用系统录音机进行录音
	 */
	private void takeAudio() {
		
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/amr");
		intent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
		activity.startActivityForResult(intent, AUDIO_TAKE);
	}

	/**
	 * 选择外部的音频文件
	 */
	private void selectAudio() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/amr");
		Intent wrapperIntent = Intent.createChooser(intent, null);
		activity.startActivityForResult(wrapperIntent, AUDIO_SELECT);
	}

	/**
	 * 调用系统的录影程序录制视频
	 */
	private void takeVideo() {
		//这个文件是录制视频后保存的文件，根据实际情况进行修改
		File file = new File(activity.getExternalCacheDir() + "athudongvideo.mp4");
		
		try {
			//如果本地存在同名的文件，会删除文件
			if (file.exists()) {
				file.delete();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			Uri uri = Uri.fromFile(file);
			intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0.5);// 画质0.5
			intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 70000);// 70s
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			activity.startActivityForResult(intent, VIDEO_TAKE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 选择本地视频
	 */
	private void selectVideo() {
		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		//设定intent的类型，如果外部程序具有打开此类型文件的功能，那就可以进行浏览
		innerIntent.setType("video/*");

		Intent wrapperIntent = Intent.createChooser(innerIntent, null);

		activity.startActivityForResult(wrapperIntent, VIDEO_SELECT);
	}

	/**
	 * 调用系统相机拍照
	 */
	private void takePhoto() {
		//这个文件是拍照后保存的文件，根据实际情况进行修改
		File file = new File(activity.getExternalCacheDir() + "athudongvideo.jpg");
		try {
			//如果本地存在同名的文件，会删除文件
			if (file.exists()) {
				file.delete();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			activity.startActivityForResult(intent, PIC_TAKE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 本地图片选择
	 */
	private void selectPhoto() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		//设定intent的类型，如果外部程序具有打开此类型文件的功能，那就可以进行浏览
		intent.setType("image/*");
		activity.startActivityForResult(Intent.createChooser(intent, "选择图片"), PIC_SELECT);
	}
}
