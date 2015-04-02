package com.framework.app.component.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framework.app.component.R;

/**
 * 控件 - HTTP请求页面时显示的DataLoadingView
 * 
 * @ClassName: DataLoadingLayout.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 上午11:47:34
 */
public class DataLoadingView extends RelativeLayout {

	/**
	 * 数据加载中的布局
	 */
	private ViewGroup mLayoutDataLoading;

	/**
	 * 数据加载失败的布局
	 */
	private ViewGroup mLayoutDataLoadFailed;

	/**
	 * 数据加载失败原因（输出调试信息到界面）
	 */
	private TextView mTvDebugInfo;

	public DataLoadingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews(context);
	}

	public DataLoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public DataLoadingView(Context context) {
		super(context);
		initViews(context);
	}

	private void initViews(Context context) {
		View rootLayout = LayoutInflater.from(context).inflate(R.layout.widget_data_loading_view, this);

		mLayoutDataLoading = (ViewGroup) rootLayout.findViewById(R.id.ll_network_loading);
		mLayoutDataLoading.setVisibility(View.GONE);

		mLayoutDataLoadFailed = (ViewGroup) rootLayout.findViewById(R.id.ll_loading_failed);
		mLayoutDataLoadFailed.setVisibility(View.GONE);

		mTvDebugInfo = (TextView) mLayoutDataLoadFailed.findViewById(R.id.tv_debug_info);
		mTvDebugInfo.setVisibility(View.GONE);
	}

	/**
	 * 数据获取中（HTTP请求开始时调用）
	 */
	public void showDataLoading() {
		this.setVisibility(View.VISIBLE);
		mLayoutDataLoading.setVisibility(View.VISIBLE);
		mLayoutDataLoadFailed.setVisibility(View.GONE);
	}

	/**
	 * 数据获取成功（HTTP请求获得数据成功时调用）
	 */
	public void showDataLoadSuccess() {
		this.setVisibility(View.GONE);
	}

	/**
	 * 数据获取失败（HTTP请求失败时调用）
	 * 
	 * @param reloadListener
	 * @param reasons
	 */
	public void showDataLoadFailed(View.OnClickListener reloadListener, String... reasons) {
		this.setVisibility(View.VISIBLE);
		mLayoutDataLoading.setVisibility(View.GONE);
		mLayoutDataLoadFailed.setVisibility(View.VISIBLE);
		mLayoutDataLoadFailed.setOnClickListener(reloadListener);

		// 根据可变参数判断是否显示调试信息
		if (null != reasons && reasons.length > 0) {
			mTvDebugInfo.setVisibility(View.VISIBLE);
			mTvDebugInfo.setText(getResources().getString(R.string.txt_debug_info, reasons[0]));
		} else {
			mTvDebugInfo.setVisibility(View.GONE);
		}

	}

}
