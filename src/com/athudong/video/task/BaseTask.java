package com.athudong.video.task;

import com.athudong.video.BaseActivity;

import android.os.AsyncTask;
import android.os.Handler;

/**
 * 公共任务基础类，封装基本方法
 */
public abstract class BaseTask extends AsyncTask<Void, Void, Void> {

	private BaseActivity act;
	
	
	private Handler handler;
	
	private boolean isFinished = false;
	
	private Runnable cancelRun;

	public BaseTask(BaseActivity act) {
		this.act = act;
		handler =act.getHandler();
		
		cancelRun = new Runnable() {
			@Override
			public void run() {
				stopTask();
			}
		};
		
		handler.postDelayed(cancelRun, 60*1000);
	}

	@Override
	protected Void doInBackground(Void... params) {
		if (!isCancelled() && !act.isFinished()) {
			doInBackground();
			isFinished = true;
		}
		return null;
	}
	
	
	

	/**
	 * 后台执行的任务
	 */
	protected abstract void doInBackground();

	@Override
	protected void onPostExecute(Void result) {
		if (!isCancelled() && act != null && !act.isFinished()) {
			if (act.isVisiable()) {
				act.executeTaskResult(this, onPostExecute());
			}
		}
	}

	public BaseActivity getActivity() {
		return act;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		if (!isCancelled() && act != null && !act.isFinished()) {
			onProgressUpdate();
		}
	}

	/**
	 * 主线程监听执行过程的工作
	 */
	protected abstract void onProgressUpdate();

	/**
	 * 执行完任务后主线程的工作
	 */
	protected abstract Object onPostExecute();

	/**
	 * 停止执行任务
	 */
	public void stopTask() {
		cancel(true);
	}
}
