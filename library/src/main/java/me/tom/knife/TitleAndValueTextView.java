package me.tom.knife;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleAndValueTextView extends TitleLayout {

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

    public TextView getValueTextView() {
        return mValueTextView;
    }

    @Override
    protected void init(AttributeSet attrs, int defStyle) {
        super.init(attrs, defStyle);


        Context context = getContext();
        Resources resources = getResources();

        int defaultFontColor = Color.BLACK;
        int defaultFontSize = resources.getDimensionPixelSize(R.dimen.title_layout_required_text_font_size);


        String valueText = null;
        int valueFontSize = defaultFontSize;
        int valueFontColor = defaultFontColor;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleAndValueTextView, defStyle, 0);
            valueText = typedArray.getString(R.styleable.TitleAndValueTextView_valueText);
            valueFontSize = typedArray.getDimensionPixelSize(R.styleable.TitleAndValueTextView_valueFontSize, defaultFontSize);
            valueFontColor = typedArray.getColor(R.styleable.TitleAndValueTextView_valueFontColor, defaultFontColor);
            typedArray.recycle();
        }
        mValueTextView = new TextView(context);
        mValueTextView.setText(valueText);
        mValueTextView.setTextColor(valueFontColor);
        mValueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, valueFontSize);
        mValueTextView.setIncludeFontPadding(false);
        mValueTextView.setGravity(Gravity.RIGHT);
        mValueTextView.setLayoutParams(getValueTextLayoutParams());
        addView(mValueTextView);
    }

    protected LayoutParams getValueTextLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.addRule(RIGHT_OF, R.id.title_text_view);
        return layoutParams;
    }
}
