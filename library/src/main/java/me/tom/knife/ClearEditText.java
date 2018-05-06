package me.tom.knife;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ClearEditText extends AppCompatEditText implements TextWatcher {

    private int mClearIconWidth;
    private int mClearIconHeight;
    private Drawable mClearIcon;

    public ClearEditText(Context context) {
        super(context);
        init(null, 0);
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int lengthBefore, int lengthAfter) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(charSequence, start, lengthBefore, lengthAfter);
        showClearIcon(charSequence.length() > 0);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            showClearIcon(getText().length() > 0);
        } else {
            showClearIcon(false);
        }
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean xTouchable = event.getX() > (getWidth() - getPaddingRight() - mClearIconWidth)
                        && (event.getX() < (getWidth() - getPaddingRight()));
                boolean yTouchable = event.getY() > (getHeight() - mClearIconHeight) / 2
                        && event.getY() < (getHeight() + mClearIconHeight) / 2;
                if (xTouchable && yTouchable) {
                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    public void setClearIcon(Drawable drawable) {
        mClearIcon = drawable;
    }

    public void setClearIconWidth(int width) {
        mClearIconWidth = width;
    }

    public void setClearIconHeight(int height) {
        mClearIconHeight = height;
    }

    protected void init(AttributeSet attrs, int defStyle) {
        if (attrs == null) {
            mClearIconWidth = getResources().getDimensionPixelSize(R.dimen.clear_edit_text_clear_icon_width);
            mClearIconHeight = getResources().getDimensionPixelSize(R.dimen.clear_edit_text_clear_icon_height);
            mClearIcon = ContextCompat.getDrawable(getContext(), R.mipmap.clear);
        } else {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ClearEditText, defStyle, 0);
            mClearIconWidth = typedArray.getDimensionPixelSize(
                    R.styleable.ClearEditText_clearIconWidth,
                    getResources().getDimensionPixelSize(R.dimen.clear_edit_text_clear_icon_width)
            );
            mClearIconHeight = typedArray.getDimensionPixelSize(
                    R.styleable.ClearEditText_clearIconHeight,
                    getResources().getDimensionPixelSize(R.dimen.clear_edit_text_clear_icon_height)
            );
            mClearIcon = typedArray.getDrawable(R.styleable.ClearEditText_clearIcon);
            if (mClearIcon == null) {
                mClearIcon = ContextCompat.getDrawable(getContext(), R.mipmap.clear);
            }
            typedArray.recycle();
        }
        setFocusable(true);
        setCompoundDrawablePadding(getResources().getDimensionPixelSize(R.dimen.edit_text_compound_drawable_padding));

    }

    private void showClearIcon(boolean isShowIcon) {
        Drawable[] compoundDrawables = getCompoundDrawables();
        if (isShowIcon) {
            mClearIcon.setBounds(0, 0, mClearIconWidth, mClearIconHeight);
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], mClearIcon, compoundDrawables[3]);
        } else {
            setCompoundDrawables(compoundDrawables[0], compoundDrawables[1], null, compoundDrawables[3]);
        }
    }
}
