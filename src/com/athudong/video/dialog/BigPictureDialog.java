package com.athudong.video.dialog;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.athudong.video.BaseActivity;
import com.athudong.video.R;
import com.athudong.video.component.LoadingCircleView;
import com.athudong.video.component.ZoomImageView;
import com.athudong.video.task.BaseTask;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.FloatMath;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

@SuppressLint("HandlerLeak")
public class BigPictureDialog extends Dialog implements OnClickListener {

	LoadingCircleView circle;

	private String url;
	private String filePath;

	private LoadImgTask task;

	private Handler handler;

	private ImageView imgView;

	private HttpURLConnection conn = null;

	private InputStream inStream = null;

	private FileOutputStream outStream = null;

	MulitPointTouchListener touch = new MulitPointTouchListener();
	
	private BaseActivity act;
	
	public BigPictureDialog(BaseActivity act, String url, String filePath){
		super(act, R.style.DimDialog);
		this.act = act;
		this.url = url;
		this.filePath = filePath;
		
		setContentView(R.layout.dialog_bigpicture);

		circle = (LoadingCircleView) findViewById(R.id.big_pic_loading);
		circle.setOnClickListener(this);
		imgView = (ImageView) findViewById(R.id.big_pic_img);

		imgView.setOnTouchListener(touch);
		imgView.setLongClickable(true);
		
		

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				int m = msg.what;
				if (m == 1) {
					if (!isStop) {
						circle.setVisibility(View.VISIBLE);
					}
				} else if (m == 50) {
					if (!isStop) {
						circle.setProgress(currentPer);
					}
				} else if (m == 100) {
					circle.setProgress(100);
				} else if (m == -1) {
					if (task != null) {
						task.stopTask();
						isStop = true;
					}
				}
			}
		};
		
		Window win = getWindow();
		win.setGravity(Gravity.BOTTOM);
		WindowManager.LayoutParams lp = win.getAttributes();

		lp.width = act.getScreenWidth();
		lp.height = act.getScreenHeight()-act.getTitleBarHeight();

		onWindowAttributesChanged(lp);

		setCancelable(true);
		setCanceledOnTouchOutside(true);
	}
	
	@Override
	public void show() {
		super.show();

		task = new LoadImgTask(act, url, filePath);
		act.addTask(task);
	}


	private int currentPer = 0;

	@Override
	public void onClick(View v) {
		task.stopTask();
		dismiss();
	}

	private long downloadSize = 0;
	private long fileSize = 0;

	private boolean isStop = false;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			task.stopTask();
			dismiss();
		}
		return false;
	}

	private void showImg(Bitmap bitmap) {
		circle.setVisibility(View.GONE);
		if (bitmap != null) {
			if (imgView != null && touch != null) {
				imgView.setImageMatrix(touch.getMatrix(bitmap.getHeight(), bitmap.getWidth()));
				imgView.setImageBitmap(bitmap);
				imgView.setVisibility(View.VISIBLE);
			}
		} else {
			if (findViewById(R.id.big_pic_noimg) != null) {
				findViewById(R.id.big_pic_noimg).setVisibility(View.VISIBLE);
				findViewById(R.id.big_pic_noimg).setOnClickListener(this);
			}
		}
	}

	private class LoadImgTask extends BaseTask {
		private String url;
		private String filePath;
		private Bitmap bitmap;

		public LoadImgTask(BaseActivity activity, String url, String filePath) {
			super(activity);
			this.url = url;
			this.filePath = filePath;
			this.execute();
		}

		private static final int SIZE = 1024;

		@Override
		protected void doInBackground() {
			if (url != null && !isCancelled()) {
				File file = new File(filePath);
				if (file.exists()) {
					bitmap = act.readBitmapAutoSize(filePath, SIZE, SIZE);
					if (bitmap == null) {
						try {
							file.delete();
						} catch (Exception e) {

						}
						getImage(url, filePath);
					} else {
						sendMessage(100);
					}
				} else {
					getImage(url, filePath);
				}
				if (bitmap == null) {
					bitmap = act.readBitmapAutoSize(filePath, SIZE, SIZE);
				}
			}
			sendMessage(100);
		}

		@Override
		protected void onProgressUpdate() {

		}

		@Override
		protected Object onPostExecute() {
			showImg(bitmap);
			return null;
		}

		private void readStream(InputStream inStream, String fileName) {
			if (!isCancelled()) {
				try {
					outStream = new FileOutputStream(fileName);
					byte[] buffer = new byte[1024];
					int len = -1;
					sendMessage(1);
					while ((len = inStream.read(buffer)) != -1) {
						if (!isCancelled()) {
							outStream.write(buffer, 0, len);
							downloadSize = downloadSize + len;
							long per = (long) ((1.0 * downloadSize / fileSize) * 100);
							if (per > currentPer) {
								currentPer = (int) per;
								sendMessage(50);
							}
						} else {
							len = -1;
							sendMessage(-1);
						}
					}
					outStream.close();
					inStream.close();
					sendMessage(100);
				} catch (Exception e) {

				}
			}
		}

		private void getImage(String path, String fileName) {
			if (!isCancelled()) {
				try {
					URL url = new URL(path);
					conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5 * 1000);
					conn.setRequestMethod("GET");
					inStream = conn.getInputStream();
					fileSize = conn.getContentLength();
					System.out.println("文件总大小：" + fileSize);
					if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
						readStream(inStream, fileName);
					}
				} catch (Exception e) {

				}
			}
		}

	}

	/**
	 * 给hand发送消息
	 * 
	 * @param what
	 */
	private void sendMessage(int what) {
		Message m = new Message();
		m.what = what;
		handler.sendMessage(m);
	}

	private class MulitPointTouchListener implements OnTouchListener {

		private static final String TAG = "Touch";
		// These matrices will be used to move and zoom image
		Matrix matrix = new Matrix();
		Matrix savedMatrix = new Matrix();
		// We can be in one of these 3 states
		static final int NONE = 0;
		static final int DRAG = 1;
		static final int ZOOM = 2;
		int mode = NONE;

		// Remember some things for zooming
		PointF start = new PointF();
		PointF mid = new PointF();
		float oldDist = 1f;

		public Matrix getMatrix(int imgHeight, int imgWidth) {
			if (imgWidth < act.getScreenWidth()) {
				matrix.postTranslate((act.getScreenWidth() - imgWidth) / 2.0f, ((act.getScreenHeight() - act.getTitleBarHeight()) / 2.0f) - (imgHeight / 2.0f));
			} else {
				matrix.postTranslate(0, ((act.getScreenHeight() - act.getTitleBarHeight()) / 2.0f) - (imgHeight / 2.0f));
			}
			return matrix;
		}

		private long downTime = 0;
		private float downX = 0;
		private float downY = 0;

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			ImageView view = (ImageView) v;
			// Log.e("view_width",
			// view.getImageMatrix()..toString()+"*"+v.getWidth());
			// Dump touch event to log
			dumpEvent(event);

			// Handle touch events here...
			switch (event.getAction() & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN:
				downTime = System.currentTimeMillis();
				downX = event.getX();
				downY = event.getY();
				matrix.set(view.getImageMatrix());
				savedMatrix.set(matrix);
				start.set(event.getX(), event.getY());
				// Log.d(TAG, "mode=DRAG");
				mode = DRAG;

				// Log.d(TAG, "mode=NONE");
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				oldDist = spacing(event);
				// Log.d(TAG, "oldDist=" + oldDist);
				if (oldDist > 10f) {
					savedMatrix.set(matrix);
					midPoint(mid, event);
					mode = ZOOM;
					// Log.d(TAG, "mode=ZOOM");
				}
				break;
			case MotionEvent.ACTION_UP:
				long now = System.currentTimeMillis();
				float upX = event.getX();
				float upY = event.getY();
				if (now - downTime < 700 && downX == upX && upY == downY) {
					task.stopTask();
					dismiss();
					break;
				}
			case MotionEvent.ACTION_POINTER_UP:
				mode = NONE;
				// Log.e("view.getWidth", view.getWidth() + "");
				// Log.e("view.getHeight", view.getHeight() + "");

				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == DRAG) {
					// ...
					matrix.set(savedMatrix);
					matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
				} else if (mode == ZOOM) {
					float newDist = spacing(event);
					Log.d(TAG, "newDist=" + newDist);
					if (newDist > 10f) {
						matrix.set(savedMatrix);
						float scale = newDist / oldDist;
						matrix.postScale(scale, scale, mid.x, mid.y);
					}
				}
				break;
			}

			view.setImageMatrix(matrix);
			return true; // indicate event was handled
		}

		private void dumpEvent(MotionEvent event) {
			String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE", "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
			StringBuilder sb = new StringBuilder();
			int action = event.getAction();
			int actionCode = action & MotionEvent.ACTION_MASK;
			sb.append("event ACTION_").append(names[actionCode]);
			if (actionCode == MotionEvent.ACTION_POINTER_DOWN || actionCode == MotionEvent.ACTION_POINTER_UP) {
				sb.append("(pid ").append(action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
				sb.append(")");
			}
			sb.append("[");
			for (int i = 0; i < event.getPointerCount(); i++) {
				sb.append("#").append(i);
				sb.append("(pid ").append(event.getPointerId(i));
				sb.append(")=").append((int) event.getX(i));
				sb.append(",").append((int) event.getY(i));
				if (i + 1 < event.getPointerCount())
					sb.append(";");
			}
			sb.append("]");
			// Log.d(TAG, sb.toString());
		}

		private float spacing(MotionEvent event) {
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}

		private void midPoint(PointF point, MotionEvent event) {
			float x = event.getX(0) + event.getX(1);
			float y = event.getY(0) + event.getY(1);
			point.set(x / 2, y / 2);
		}

	}

}
