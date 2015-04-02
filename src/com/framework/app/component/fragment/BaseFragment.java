package com.framework.app.component.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragment;
import com.framework.app.component.crouton.Crouton;
import com.framework.app.component.crouton.Style;
import com.framework.app.component.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * Fragment基类
 * 
 * @ClassName: BaseFragment.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 上午11:15:12
 */
public class BaseFragment extends SherlockFragment {

	/**
	 * TAG为每一个Activity的类名
	 */
	protected String TAG = BaseFragment.class.getSimpleName();

	private ProgressDialog mProgressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TAG = this.getClass().getSimpleName();
	}

	public void onResume() {
		super.onResume();
		// 友盟统计
		MobclickAgent.onPageStart(TAG); // 统计页面
	}

	public void onPause() {
		super.onPause();
		// 友盟统计
		MobclickAgent.onPageEnd(TAG);
	}

	@Override
	public void setMenuVisibility(boolean menuVisible) {
		super.setMenuVisibility(menuVisible);

		if (null != this.getView()) {
			this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 显示ProgressDialog
	 * 
	 * @param hint
	 *            要显示的文字
	 */
	public void showProgreessDialog(String hint) {
		if (null == mProgressDialog) {
			mProgressDialog = new ProgressDialog(getActivity());
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
		ToastUtil.showMessageLong(getActivity(), msg);
	}

	/**
	 * 显示Crouton提示
	 * 
	 * @param msg
	 */
	public void showCroutonMsg(String msg) {
		Crouton.makeText(getActivity(), msg, Style.ALERT).show();
	}

}