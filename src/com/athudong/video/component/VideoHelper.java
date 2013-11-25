package com.athudong.video.component;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.SurfaceHolder.Callback;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;

/**
 * 视频播放助手
 */
public class VideoHelper implements OnSeekBarChangeListener, Callback, OnClickListener {

	private MediaPlayer player;

	private SeekBar seekbar;

	private BaseActivity act;

	private SurfaceView surfaceView;

	private Timer timer;

	private TimerTask timerTask;
	
	private View root;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			int val = (Integer) msg.obj;
			seekbar.setProgress(val);
		};
	};

	public VideoHelper(BaseActivity act, View root) {
		this.act = act;
		this.root = root;
		this.seekbar = (SeekBar) root.findViewById(R.id.seekbar);

		timer = new Timer();
		seekbar.setProgress(0);
		seekbar.setEnabled(false);
		seekbar.setOnSeekBarChangeListener(this);

		// 初始化计时器，用来监听seekbar的进度变化
		if (timerTask == null) {
			timerTask = new TimerTask() {
				@Override
				public void run() {
					if (player != null) {

						boolean isPlaying = false;
						int val = -1;
						try {
							isPlaying = player.isPlaying();
							val = player.getCurrentPosition();
						} catch (Exception e) {

						}
						if (val < 0) {
							val = 0;
						}
						if (isPlaying) {
							setProgress(val);
						}
					}
				}
			};
			timer.schedule(timerTask, 0, 50);
		}
		initSurfaceView();
	}

	/**
	 * 初始化视频的视图界面
	 */
	@SuppressWarnings("deprecation")
	private void initSurfaceView() {
		surfaceView = (SurfaceView) root.findViewById(R.id.surfaceview);
		// 设置SurfaceView自己不管理的缓冲区
		surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceView.getHolder().addCallback(this);
		surfaceView.setOnClickListener(this);
	}

	private String videoPath;

	public void play(String path) {
		if (path == null) {
			path = videoPath;
		} else {
			videoPath = path;
		}
		if (videoPath == null) {
			return;
		}
		// 显示播放器布局
		root.findViewById(R.id.videoCoverLayout).setVisibility(View.INVISIBLE);
		root.findViewById(R.id.videoPlayerLayout).setVisibility(View.VISIBLE);

		try {
			seekbar.setEnabled(true);
			// 重新播放
			if (player != null) {
				player.release();
			}
			Uri uri = Uri.parse(videoPath);
			player = MediaPlayer.create(act, uri);
			seekbar.setMax(player.getDuration());
			player.setDisplay(surfaceView.getHolder());
			player.start();
			player.seekTo(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int where_pause = 0;

	public void pause() {
		if (player != null && player.isPlaying()) {
			player.pause();
			where_pause = player.getCurrentPosition();
		}
	}

	public void stop() {
		if (player != null) {
			seekbar.setEnabled(false);
			player.release();
		}
		timer.cancel();
	}

	public void continuePlay() {
		if (player != null) {
			player.seekTo(where_pause);
			player.start();
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		if (player != null && seekBar.isEnabled()) {
			player.pause();
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		if (player != null && seekBar.isEnabled()) {
			player.seekTo(seekBar.getProgress());
			player.start();
		}
	}

	public void setProgress(int progress) {
		Message msg = new Message();
		msg.obj = progress;
		handler.sendMessage(msg);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (where_pause > 0) {
			try {
				// 开始播放
				play(null);
				// 并直接从指定位置开始播放
				player.seekTo(where_pause);
				where_pause = 0;
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

	@Override
	public void onClick(View v) {

		int getVis = root.findViewById(R.id.videoPlayerLayout).getVisibility();

		if (getVis == View.VISIBLE && player!=null) {
			if (player.isPlaying()) {
				pause();
			} else {
				continuePlay();
			}
		}
	}
}
