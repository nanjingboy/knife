package me.tom.knife;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;

public class TitleEditText extends TitleLayout {

    private ClearEditText mEditText;

    public TitleEditText(Context context) {
        super(context);
    }

    public TitleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ClearEditText getEditText() {
        return mEditText;
    }

    protected void init(AttributeSet attrs, int defStyle) {
        super.init(attrs, defStyle);
        Context context = getContext();
        Resources resources = getResources();
        int defaultFontColor = Color.BLACK;
        int defaultFontSize = resources.getDimensionPixelSize(R.dimen.title_layout_required_text_font_size);

        String valueText = null;
        String valueHint = null;
        int valueFontSize = defaultFontSize;
        int valueFontColor = defaultFontColor;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleEditText, defStyle, 0);
            valueText = typedArray.getString(R.styleable.TitleEditText_editTextValue);
            valueHint = typedArray.getString(R.styleable.TitleEditText_editTextHint);
            valueFontSize = typedArray.getDimensionPixelSize(R.styleable.TitleEditText_editTextFontSize, defaultFontSize);
            valueFontColor = typedArray.getColor(R.styleable.TitleEditText_editTextFontColor, defaultFontColor);
            typedArray.recycle();
        }
        mEditText = new ClearEditText(context);
        if (valueText != null) {
            mEditText.setText(valueText);
            mEditText.setSelection(valueText.length());
        }
        mEditText.setHint(valueHint);
        mEditText.setTextColor(valueFontColor);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueFontSize);
        mEditText.setGravity(Gravity.END);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        layoutParams.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.addRule(RIGHT_OF, R.id.title_text_view);
        mEditText.setLayoutParams(layoutParams);
        mEditText.setBackgroundColor(Color.WHITE);
        addView(mEditText);
    }
}
