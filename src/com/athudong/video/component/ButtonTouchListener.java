package com.athudong.video.component;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import static com.nineoldandroids.view.ViewPropertyAnimator.animate;

/**
 * 按钮的点击事件 ， 主要用于立体变扁平的效果
 */
public abstract class ButtonTouchListener implements OnTouchListener{

	private int distance ;
	
	public ButtonTouchListener(int distance){
		this.distance = distance;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int action = event.getAction();
		
		if(action==MotionEvent.ACTION_DOWN){
			//按下的动画
			animate(view).x(view.getX()+distance).y(view.getY()+distance);
		}
		if(action==MotionEvent.ACTION_UP){
			//松开的动画
			animate(view).y(view.getY()-distance).y(view.getY()-distance);
			onClick(view);
		}
		return false;
	}
	
	
	public abstract void onClick(View view);
}
