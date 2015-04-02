package com.framework.app.component.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.framework.app.component.crouton.Crouton;
import com.framework.app.component.crouton.Style;
import com.framework.app.component.utils.ExitAppUtil;
import com.framework.app.component.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * 本程序Activity的基类，继承SherlockActivity，实现抽象方法，动态显示或隐藏Action Bar
 * 
 * @ClassName: BaseActivity.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 上午11:18:46
 */
public abstract class BaseActivity extends SherlockActivity {

	/**
	 * TAG为每一个Activity的类名
	 */
	protected String TAG = BaseActivity.class.getSimpleName();

	protected ActionBar mSupportActionBar;
	protected Activity mActivity;

	private InputMethodManager mInputMethodManager;
	private ProgressDialog mProgressDialog;

	/**
	 * 是否不显示Action Bar
	 * 
	 * @return true 隐藏 false 显示
	 */
	protected abstract boolean isHideActionbar();

	/**
	 * 初始化全局数据和Intent传递过来的数据
	 */
	protected abstract void initExtras();

	/**
	 * 初始化ActionBar
	 */
	protected abstract void initActionBar();

	/**
	 * 初始化控件
	 * 
	 * @param savedInstanceState
	 */
	protected abstract void initViews(Bundle savedInstanceState);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mActivity = this;
		TAG = this.getClass().getSimpleName();

		// 初始化数据
		initExtras();

		mInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mSupportActionBar = getSupportActionBar();

		if (isHideActionbar()) {
			mSupportActionBar.hide();
		} else {
			// 隐藏Home图标和Title文字
			mSupportActionBar.setDisplayShowHomeEnabled(false);
			mSupportActionBar.setDisplayShowTitleEnabled(false);
			// 对ActionBar启用自定义View
			mSupportActionBar.setDisplayShowCustomEnabled(true);
			// 初始化ActionBar
			initActionBar();
		}
		// 记录到退出栈
		ExitAppUtil.getInstance().addActivity(this);
		// 初始化控件
		initViews(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 友盟统计
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 友盟统计
		MobclickAgent.onPause(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 从退出栈中移除
		ExitAppUtil.getInstance().removeActivity(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// 点击空白区域隐藏输入法
		if (MotionEvent.ACTION_DOWN == event.getAction()) {
			if (null != getCurrentFocus() && null != getCurrentFocus().getWindowToken()) {
				mInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 隐藏输入法
	 */
	protected boolean hideSoftInput(View v) {
		return mInputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 开关输入法
	 */
	protected void toggleSoftInput() {
		mInputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 显示ProgressDialog
	 * 
	 * @param hint
	 *            要显示的文字
	 */
	public void showProgreessDialog(String hint) {
		if (null == mProgressDialog) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setOnKeyListener(new OnKeyListener() {
				@Override
				public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
					if (keyCode == KeyEvent.KEYCODE_BACK) {
						try {
							dismissProgressDialog();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					return false;
				}
			});
		}

		if (!mProgressDialog.isShowing()) {
			mProgressDialog.setMessage(hint);
			mProgressDialog.show();
		}

	}

	/**
	 * 关闭ProgressDialog
	 */
	public void dismissProgressDialog() {
		if (null != mProgressDialog && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	/**
	 * 显示系统Toast
	 * 
	 * @param msg
	 */
	public void showToastMsg(String msg) {
		ToastUtil.showMessage(this, msg);
	}

	/**
	 * 显示Crouton提示
	 * 
	 * @param msg
	 */
	public void showCroutonMsg(String msg) {
		Crouton.makeText(this, msg, Style.ALERT).show();
	}

}
