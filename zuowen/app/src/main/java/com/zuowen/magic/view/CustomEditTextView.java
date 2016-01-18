/**
 * Copyright ? 2014-2-21 eduu.com.Co.,Ltd
 * kaoyan 下午6:25:06
 * Version TODO
 * All right reserved.
 *
 */

package com.zuowen.magic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;

/**
 * 类描述： 创建者： yanshch 项目名称： kaoyan 创建时间： 2014-2-21 下午6:25:06 版本号： v1.0
 */
public class CustomEditTextView extends EditText
{

	/**
	 * @param context
	 */
	public CustomEditTextView(Context context)
	{
		super(context);
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomEditTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public CustomEditTextView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		// TODO 自动生成的构造函数存根
	}

	private OnFinishComposingListener mFinishComposingListener;

	public void setOnFinishComposingListener(OnFinishComposingListener listener)
	{
		this.mFinishComposingListener = listener;
	}

	@Override
	public InputConnection onCreateInputConnection(EditorInfo outAttrs)
	{
		return new MyInputConnection(super.onCreateInputConnection(outAttrs), false);
	}

	public class MyInputConnection extends InputConnectionWrapper
	{

		public MyInputConnection(InputConnection target, boolean mutable)
		{
			super(target, mutable);
		}

		@Override
		public boolean finishComposingText()
		{
			boolean finishComposing = super.finishComposingText();
			//Logger.e("------------------可能是关闭了");
			if (mFinishComposingListener != null)
			{
				mFinishComposingListener.finishComposing();
			}
			return finishComposing;
		}
	}

	public interface OnFinishComposingListener
	{
		public void finishComposing();
	}
}
