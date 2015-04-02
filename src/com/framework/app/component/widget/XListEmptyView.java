package com.framework.app.component.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framework.app.component.R;

/**
 * 控件 - ListView没有数据时显示的视图
 * 
 * @ClassName: XListEmptyView.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2015-2-12 下午5:26:52
 */
public class XListEmptyView extends RelativeLayout {

	private TextView mTvEmtpyInfo;

	public XListEmptyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews(context);
	}

	public XListEmptyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public XListEmptyView(Context context) {
		super(context);
		initViews(context);
	}

	private void initViews(Context context) {
		View rootLayout = LayoutInflater.from(context).inflate(R.layout.component_xlist_empty_view, this);

		mTvEmtpyInfo = (TextView) rootLayout.findViewById(R.id.tv_empty_info);
	}

	public void setEmptyInfo(int txtResId) {
		mTvEmtpyInfo.setText(txtResId);
	}

}
