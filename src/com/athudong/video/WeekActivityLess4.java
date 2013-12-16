package com.athudong.video;

import com.athudong.video.bean.User;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 如果版本号小于3，则对周赛界面进行如此优化
 * 由于3.0以下组件实现移动效果后，
 * 事件绑定位置依旧不变
 */
public class WeekActivityLess4 implements OnClickListener{

	private WeekActivity act;
	

	private View ringLayout;

	/**
	 * 左选手的组件布局（三个按钮）
	 */
	private View leftComLayout;

	/**
	 * 右选手的组件布局（三个按钮）
	 */
	private View rightComLayout;

	/**
	 * 中间的PK环
	 */
	private View pkRing;

	/**
	 * 左选手的背景布局
	 */
	private View leftBg;

	/**
	 * 右选手的背景布局
	 */
	private View rightBg;

	/**
	 * 左选手名称
	 */
	private TextView leftName;

	/**
	 * 右选手名称
	 */
	private TextView rightName;

	/**
	 * 投票按钮
	 */
	private Button thumbBtn;

	/**
	 * 左选手
	 */
	private User leftUser;

	/**
	 * 右选手
	 */
	private User rightUser;

	/**
	 * 左选手背景图片(包括在左选手的背景布局中)
	 */
	private ImageView leftBgImg;

	/**
	 * 右选手背景图片(包括在右选手的背景布局)
	 */
	private ImageView rightBgImg;

	/**
	 * 剩余票数
	 */
	private static int voteCount = 2;

	
	
	public WeekActivityLess4(WeekActivity act){
		this.act = act;
		act.setContentView(R.layout.activity_week_pk_less4);
		act.findViewById(R.id.realLeftBtn).setOnClickListener(this);
		act.findViewById(R.id.realRightBtn).setOnClickListener(this);
	}




	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id==R.id.realLeftBtn){
			//移动左边的选手
			
		}else if(id==R.id.realRightBtn){
			//移动右边选手
		}
	}
}
