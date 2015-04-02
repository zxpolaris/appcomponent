package com.framework.app.component.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.framework.app.component.utils.BroadCastUtil;
import com.framework.app.component.utils.LoggerUtil;

/**
 * 用户退出登录以后需要关闭的页面都需要继承它
 * 
 * @ClassName: UserLogOutFinishActivity.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 上午11:20:40
 */
public abstract class UserLogOutFinishActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LoggerUtil.d(TAG, "onCreate Bundle = " + savedInstanceState);

		LocalBroadcastManager.getInstance(mActivity).registerReceiver(mUserLogOutReceiver, new IntentFilter(BroadCastUtil.ACTION_USER_LOGOUT));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LoggerUtil.d(TAG, "onDestroy");

		LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(mUserLogOutReceiver);
	}

	/**
	 * 接收用户退出的广播
	 */
	private BroadcastReceiver mUserLogOutReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			LoggerUtil.d(TAG, "BroadcastReceiver onReceive");

			finish();
		}
	};

}
