package com.athudong.video.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 * 图片放大
 */
public class ZoomImageView extends View {
	private Context mContext;
	private int mScreenWidth;
	private int mScreenHeight;
	private float mCurrentRate = 1;
	private float mOldRate = 1;
	private boolean mIsFirst = true;
	private float mOriginalLength;
	private float mCurrentLength;
	
	private Bitmap myBitmap;

	public ZoomImageView(Context context, Bitmap myBitmap) {
		super(context);
		this.mContext = context;
		init(context);
		this.myBitmap = myBitmap;
	}

	public ZoomImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		init(context);
	}

	private void init(Context context) {
		DisplayMetrics display = context.getResources().getDisplayMetrics();
		mScreenWidth = display.widthPixels;
		mScreenHeight = display.heightPixels;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			if (event.getPointerCount() == 2) {
				if (mIsFirst) {
					mOriginalLength = (float) Math.sqrt(Math.pow(event.getX(0) - event.getX(1), 2) + Math.pow(event.getY(0) - event.getY(1), 2));
					mIsFirst = false;
				} else {
					mCurrentLength = (float) Math.sqrt(Math.pow(event.getX(0) - event.getX(1), 2) + Math.pow(event.getY(0) - event.getY(1), 2));
					mCurrentRate = (float) (mOldRate * (mCurrentLength / mOriginalLength));
					invalidate();
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			mOldRate = mCurrentRate;
			mIsFirst = true;
			break;
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		Bitmap bitmap = myBitmap;
		canvas.scale(mCurrentRate, mCurrentRate, mScreenWidth / 2f, mScreenHeight / 2f);
		int left = (int)((mScreenWidth / 2) - (bitmap.getWidth() / 2));
		int top = (int)((mScreenHeight / 2) - (bitmap.getHeight() / 2));
		canvas.drawBitmap(bitmap, left, top, null);
	}

}
