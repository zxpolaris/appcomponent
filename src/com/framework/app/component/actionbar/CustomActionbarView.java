package com.framework.app.component.actionbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framework.app.component.R;

/**
 * 通用ActionBar
 * 
 * @ClassName: CommonActionBar.java
 * 
 * @author Xun.Zhang
 * 
 * @date 2014-12-8 上午11:48:35
 */
public class CustomActionbarView extends RelativeLayout {

	private View mActionBarLayout;
	/**
	 * 左边图标按钮
	 */
	private ImageView mIvLeft;

	/**
	 * 左边文字按钮
	 */
	private Button mBtnLeft;

	/**
	 * 中间文字标题
	 */
	private TextView mTvTitle;

	/**
	 * 右边图标按钮
	 */
	private ImageView mIvRight;

	/**
	 * 右边文字按钮
	 */
	private Button mTxtBtnRight;

	public CustomActionbarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initViews(context);
	}

	public CustomActionbarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public CustomActionbarView(Context context) {
		super(context);
		initViews(context);
	}

	private void initViews(Context context) {
		View rootLayout = LayoutInflater.from(context).inflate(R.layout.actionbar_view, this);
		mBtnLeft = (Button) rootLayout.findViewById(R.id.btn_left);
		mIvLeft = (ImageView) rootLayout.findViewById(R.id.img_btn_left);
		mTvTitle = (TextView) rootLayout.findViewById(R.id.tv_title);
		mTxtBtnRight = (Button) rootLayout.findViewById(R.id.btn_right);
		mIvRight = (ImageView) rootLayout.findViewById(R.id.img_btn_right);
		mActionBarLayout = rootLayout.findViewById(R.id.action_bar_layout);
		mActionBarLayout.setBackgroundResource(R.color.actionbar_bg_color);
	}

	/**
	 * 设置ActionBar标题
	 * 
	 * @param txtResId
	 */
	public void setActionBarTitle(int txtResId) {
		mTvTitle.setText(txtResId);
	}

	/**
	 * 对ActionBar标题增加右边小图标
	 * 
	 * @param imgResId
	 */
	public void addActionBarTitleWithRightDrawable(int imgResId) {
		Drawable drawable = getResources().getDrawable(imgResId);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		mTvTitle.setCompoundDrawables(null, null, drawable, null);
		mTvTitle.setCompoundDrawablePadding(6);
	}

	/**
	 * 设置ActionBar标题
	 * 
	 * @param strTitle
	 */
	public void setActionBarTitle(String strTitle) {
		mTvTitle.setText(strTitle);
	}

	/**
	 * 设置左边文字按钮及事件
	 * 
	 * @param imageResId
	 *            资源ID
	 * @param onClickListener
	 *            事件
	 */
	public void setLeftBtn(int txtResId, OnClickListener onClickListener) {
		mBtnLeft.setVisibility(View.VISIBLE);
		mBtnLeft.setText(txtResId);
		mBtnLeft.setOnClickListener(onClickListener);
		// 隐藏左边图标按钮
		mIvLeft.setVisibility(View.GONE);
	}

	/**
	 * 设置左边图标按钮及事件
	 * 
	 * @param imageResId
	 *            资源ID
	 * @param onClickListener
	 *            事件
	 */
	public void setLeftImgBtn(int imageResId, OnClickListener onClickListener) {
		mIvLeft.setVisibility(View.VISIBLE);
		mIvLeft.setImageResource(imageResId);
		mIvLeft.setOnClickListener(onClickListener);
		// 隐藏左边文字按钮
		mBtnLeft.setVisibility(View.GONE);
	}

	/**
	 * 设置右边图标按钮及事件
	 * 
	 * @param imageResId
	 *            资源ID
	 * @param onClickListener
	 *            事件
	 */
	public void setRightImgBtn(int imageResId, OnClickListener onClickListener) {
		mIvRight.setVisibility(View.VISIBLE);
		mIvRight.setImageResource(imageResId);
		mIvRight.setOnClickListener(onClickListener);
		// 隐藏右边文字按钮
		mTxtBtnRight.setVisibility(View.GONE);
	}

	public void setRightImgBtn(int imageResId) {
		mIvRight.setVisibility(View.VISIBLE);
		mIvRight.setImageResource(imageResId);
		mTxtBtnRight.setVisibility(View.GONE);
	}

	/**
	 * 设置右边图标按钮是否显示
	 * 
	 * @param visibility
	 */
	public void setRightImgBtnVisibility(int visibility) {
		mIvRight.setVisibility(visibility);
	}

	/**
	 * 设置标题点击事件
	 * 
	 * @param onClickListener
	 */
	public void setTitleOnClickListener(OnClickListener onClickListener) {
		mTvTitle.setOnClickListener(onClickListener);
	}

	/**
	 * 设置右边文字按钮及事件
	 * 
	 * @param txtResId
	 *            资源ID
	 * @param onClickListener
	 *            事件
	 */
	public void setRightBtn(int txtResId, OnClickListener onClickListener) {
		mTxtBtnRight.setVisibility(View.VISIBLE);
		mTxtBtnRight.setText(txtResId);
		mTxtBtnRight.setOnClickListener(onClickListener);
		// 隐藏右边图标按钮
		mIvRight.setVisibility(View.GONE);
	}

	public void setRightBtnTxt(String strText) {
		mTxtBtnRight.setText(strText);
	}

	public void setRightBtnTxt(int txtResId) {
		mTxtBtnRight.setText(txtResId);
	}

	/**
	 * 设置右边文字按钮是否显示
	 * 
	 * @param visibility
	 */
	public void setRightBtnVisibility(int visibility) {
		mTxtBtnRight.setVisibility(visibility);
	}

	public void setActionBarBackgroundColor(int color) {
		mActionBarLayout.setBackgroundColor(color);
	}

	public void setActionBarBackgroundResource(int resid) {
		mActionBarLayout.setBackgroundResource(resid);
	}

}
