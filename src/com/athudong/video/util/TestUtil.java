package com.athudong.video.util;

import com.athudong.video.R;

public class TestUtil {

	
	private static int[] drs = new int[]{
			R.drawable.test_head_01,
			R.drawable.test_head_02,
			R.drawable.test_head_03,
			R.drawable.test_head_04,
			R.drawable.test_head_05,
			R.drawable.test_head_06,
			R.drawable.test_head_07,
			R.drawable.test_head_08
	};
	
	
	public static int getRandomHead(){
		int i = drs[getRandom(0, drs.length)];
		return i;
	}
	
	private static int getRandom(int start, int end) {
		return (int) (Math.random() * end + start);
	}
}
