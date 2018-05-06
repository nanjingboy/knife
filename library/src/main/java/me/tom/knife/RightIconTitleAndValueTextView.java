package me.tom.knife;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RightIconTitleAndValueTextView extends TitleAndValueTextView {

    private ImageView mImageView;

    public RightIconTitleAndValueTextView(Context context) {
        super(context);
    }

    public RightIconTitleAndValueTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RightIconTitleAndValueTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ImageView getImageView() {
        return mImageView;
    }

    @Override
    protected void init(AttributeSet attrs, int defStyle) {
        super.init(attrs, defStyle);
        Drawable rightIcon;
        int rightIconWidth;
        int rightIconHeight;
        int rightIconLeftMargin;
        Context context = getContext();
        Resources resources = getResources();
        Drawable defaultIcon = ContextCompat.getDrawable(context, R.mipmap.arrow_right);
        int defaultWith = resources.getDimensionPixelSize(R.dimen.title_and_value_text_view_right_icon_width);
        int defaultHeight = resources.getDimensionPixelSize(R.dimen.title_and_value_text_view_right_icon_height);
        int defaultLeftMargin = resources.getDimensionPixelSize(R.dimen.title_and_value_text_view_right_icon_left_margin);
        if (attrs == null) {
            rightIcon = defaultIcon;
            rightIconWidth = defaultWith;
            rightIconHeight = defaultHeight;
            rightIconLeftMargin = defaultLeftMargin;
        } else {
            TypedArray typedArray = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.RightIconTitleAndValueTextView,
                    defStyle,
                    0
            );
            rightIcon = typedArray.getDrawable(R.styleable.RightIconTitleAndValueTextView_rightIcon);
            if (rightIcon == null) {
                rightIcon = defaultIcon;
            }
            rightIconWidth = typedArray.getDimensionPixelSize(
                    R.styleable.RightIconTitleAndValueTextView_rightIconWidth,
                    defaultWith
            );
            rightIconHeight = typedArray.getDimensionPixelSize(
                    R.styleable.RightIconTitleAndValueTextView_rightIconHeight,
                    defaultHeight
            );
            rightIconLeftMargin = typedArray.getDimensionPixelSize(
                    R.styleable.RightIconTitleAndValueTextView_rightIconLeftMargin,
                    defaultLeftMargin
            );
            typedArray.recycle();
        }
        mImageView = new ImageView(context);
        mImageView.setImageDrawable(rightIcon);
        mImageView.setId(R.id.right_icon_view);
        LayoutParams layoutParams = new LayoutParams(rightIconWidth, rightIconHeight);
        layoutParams.leftMargin = rightIconLeftMargin;
        layoutParams.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.addRule(ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        mImageView.setLayoutParams(layoutParams);
        addView(mImageView);
    }

    @Override
    protected LayoutParams getValueTextLayoutParams() {
        LayoutParams layoutParams = super.getValueTextLayoutParams();
        layoutParams.addRule(LEFT_OF, R.id.right_icon_view);
        return layoutParams;
    }
}
