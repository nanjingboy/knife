package me.tom.knife;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleAndValueTextView extends RelativeLayout {

    private TextView mTitleTextView;
    private TextView mValueTextView;

    public TitleAndValueTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public TitleAndValueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TitleAndValueTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setTitleText(String text) {
        mTitleTextView.setText(text);
    }

    public void setTitleWidth(int width) {
        LayoutParams layoutParams = (LayoutParams) mTitleTextView.getLayoutParams();
        layoutParams.width = width;
        mTitleTextView.setLayoutParams(layoutParams);
    }

    public void setTitleFontSize(int unit, float size) {
        mTitleTextView.setTextSize(unit, size);
    }

    public void setTitleFontSize(float size) {
        mTitleTextView.setTextSize(size);
    }

    public void setTitleFontColor(int color) {
        mTitleTextView.setTextColor(color);
    }

    public void setValueText(String text) {
        mValueTextView.setText(text);
    }

    public void setValueFontSize(int unit, float size) {
        mValueTextView.setTextSize(unit, size);
    }

    public void setValueFontSize(float size) {
        mValueTextView.setTextSize(size);
    }

    public void setValueFontColor(int color) {
        mValueTextView.setTextColor(color);
    }

    public void setValueAlignRight(boolean alignRight) {
        if (alignRight) {
            mValueTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        } else {
            mValueTextView.setGravity(Gravity.CENTER_VERTICAL);
        }
    }

    protected void init(AttributeSet attrs, int defStyle) {
        Context context = getContext();

        int defaultFontColor = Color.BLACK;
        int defaultFontSize = getResources().getDimensionPixelSize(R.dimen.title_and_value_text_view_font_size);

        String titleText = null;
        int titleFontSize = defaultFontSize;
        int titleFontColor = defaultFontColor;
        int titleWidth = LayoutParams.WRAP_CONTENT;

        String valueText = null;
        boolean valueAlignRight = true;
        int valueFontSize = defaultFontSize;
        int valueFontColor = defaultFontColor;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleAndValueTextView, defStyle, 0);
            titleText = typedArray.getString(R.styleable.TitleAndValueTextView_titleText);
            titleFontSize = typedArray.getDimensionPixelSize(R.styleable.TitleAndValueTextView_titleFontSize, defaultFontSize);
            titleFontColor = typedArray.getColor(R.styleable.TitleAndValueTextView_titleFontColor, defaultFontColor);
            titleWidth = typedArray.getDimensionPixelSize(R.styleable.TitleAndValueTextView_titleWidth, LayoutParams.WRAP_CONTENT);

            valueText = typedArray.getString(R.styleable.TitleAndValueTextView_valueText);
            valueFontSize = typedArray.getDimensionPixelSize(R.styleable.TitleAndValueTextView_valueFontSize, defaultFontSize);
            valueFontColor = typedArray.getColor(R.styleable.TitleAndValueTextView_valueFontColor, defaultFontColor);
            valueAlignRight = typedArray.getBoolean(R.styleable.TitleAndValueTextView_valueAlignRight, true);
            typedArray.recycle();
        }

        mTitleTextView = new TextView(context);
        mTitleTextView.setId(R.id.title_text_view);
        mTitleTextView.setText(titleText);
        mTitleTextView.setTextColor(titleFontColor);
        mTitleTextView.setGravity(Gravity.CENTER_VERTICAL);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
        LayoutParams titleTextViewLayoutParams = new LayoutParams(titleWidth, LayoutParams.MATCH_PARENT);
        titleTextViewLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        mTitleTextView.setLayoutParams(titleTextViewLayoutParams);
        addView(mTitleTextView);

        mValueTextView = new TextView(context);
        mValueTextView.setText(valueText);
        mValueTextView.setTextColor(valueFontColor);
        mValueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueFontSize);
        if (valueAlignRight) {
            mValueTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        } else {
            mValueTextView.setGravity(Gravity.CENTER_VERTICAL);
        }
        setValueTextLayoutParams();
        addView(mValueTextView);
    }

    protected void setValueTextLayoutParams() {
        LayoutParams valueTextViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        valueTextViewLayoutParams.addRule(RIGHT_OF, R.id.title_text_view);
        mValueTextView.setLayoutParams(valueTextViewLayoutParams);
    }
}
