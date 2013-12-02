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

public class SelectResDialog extends Dialog implements android.view.View.OnClickListener {

	private BaseActivity activity;

	public static final int TYPE_PIC = 0;
	public static final int TYPE_VIDEO = 1;
	public static final int TYPE_AUDIO = 2;

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
		if (id == R.id.btn_03) {
			
		} else if (id == R.id.btn_01) {
			if (type == TYPE_PIC) {
				takePhoto();
			} else if (type == TYPE_VIDEO) {
				takeVideo();
			} else if (type == TYPE_AUDIO) {
				takeAudio();
			}
		} else if (id == R.id.btn_02) {
			if (type == TYPE_PIC) {
				selectPhoto();
			} else if (type == TYPE_VIDEO) {
				selectVideo();
			} else if (type == TYPE_AUDIO) {
				selectAudio();
			}
		}
	}

	private void takeAudio() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/amr");
		intent.setClassName("com.android.soundrecorder", "com.android.soundrecorder.SoundRecorder");
		activity.startActivityForResult(intent, AUDIO_TAKE);
	}

	private void selectAudio() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("audio/amr");
		Intent wrapperIntent = Intent.createChooser(intent, null);
		activity.startActivityForResult(wrapperIntent, AUDIO_SELECT);
	}

	private void takeVideo() {
		File file = new File(activity.getExternalCacheDir() + "athudongvideo.mp4");
		try {
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

	private void selectVideo() {
		Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);

		innerIntent.setType("video/*");

		Intent wrapperIntent = Intent.createChooser(innerIntent, null);

		activity.startActivityForResult(wrapperIntent, VIDEO_SELECT);
	}

	private void takePhoto() {
		File file = new File(activity.getExternalCacheDir() + "athudongvideo.jpg");
		try {
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

	private void selectPhoto() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		activity.startActivityForResult(Intent.createChooser(intent, "选择图片"), PIC_SELECT);
	}
}
