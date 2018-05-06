package me.tom.knife;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleLayout extends RelativeLayout {

    private TextView mRequiredTextView;
    private TextView mTitleTextView;

    public TitleLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public TextView getTitleTextView() {
        return mTitleTextView;
    }

    public void setIsRequired(boolean isRequired) {
        if (isRequired) {
            mRequiredTextView.setVisibility(VISIBLE);
        } else {
            mRequiredTextView.setVisibility(GONE);
        }
    }

    protected void init(AttributeSet attrs, int defStyle) {
        Context context = getContext();
        Resources resources = getResources();

        int defaultFontColor = Color.BLACK;
        int defaultFontSize = resources.getDimensionPixelSize(R.dimen.title_layout_required_text_font_size);
        int defaultRequiredTextFontSize = resources.getDimensionPixelSize(R.dimen.title_layout_required_text_font_size);
        int defaultRequiredTextRightMargin = resources.getDimensionPixelSize(R.dimen.title_layout_required_text_right_margin);


        boolean isRequired = false;
        int requiredTextFontSize = defaultRequiredTextFontSize;
        int requiredTextRightMargin = defaultRequiredTextRightMargin;

        String titleText = null;
        int titleFontSize = defaultFontSize;
        int titleFontColor = defaultFontColor;
        int titleWidth = LayoutParams.WRAP_CONTENT;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleLayout, defStyle, 0);
            isRequired = typedArray.getBoolean(R.styleable.TitleLayout_isRequired, false);
            requiredTextFontSize = typedArray.getDimensionPixelSize(
                    R.styleable.TitleLayout_requiredTextFontSize,
                    defaultRequiredTextFontSize
            );
            requiredTextRightMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleLayout_requiredTextRightMargin,
                    defaultRequiredTextRightMargin
            );

            titleText = typedArray.getString(R.styleable.TitleLayout_titleText);
            titleFontSize = typedArray.getDimensionPixelSize(R.styleable.TitleLayout_titleFontSize, defaultFontSize);
            titleFontColor = typedArray.getColor(R.styleable.TitleLayout_titleFontColor, defaultFontColor);
            titleWidth = typedArray.getDimensionPixelSize(R.styleable.TitleLayout_titleWidth, LayoutParams.WRAP_CONTENT);
            typedArray.recycle();
        }

        mRequiredTextView = new TextView(context);
        mRequiredTextView.setId(R.id.required_text_view);
        mRequiredTextView.setText("*");
        mRequiredTextView.setTextColor(Color.RED);
        mRequiredTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, requiredTextFontSize);
        LayoutParams requiredLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        requiredLayoutParams.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        requiredLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        requiredLayoutParams.rightMargin = requiredTextRightMargin;
        mRequiredTextView.setLayoutParams(requiredLayoutParams);
        addView(mRequiredTextView);

        mTitleTextView = new TextView(context);
        mTitleTextView.setId(R.id.title_text_view);
        mTitleTextView.setText(titleText);
        mTitleTextView.setTextColor(titleFontColor);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
        mTitleTextView.setIncludeFontPadding(false);
        LayoutParams titleTextViewLayoutParams = new LayoutParams(titleWidth, LayoutParams.WRAP_CONTENT);
        titleTextViewLayoutParams.addRule(RIGHT_OF, R.id.required_text_view);
        titleTextViewLayoutParams.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        mTitleTextView.setLayoutParams(titleTextViewLayoutParams);
        addView(mTitleTextView);

        setIsRequired(isRequired);
    }
}
