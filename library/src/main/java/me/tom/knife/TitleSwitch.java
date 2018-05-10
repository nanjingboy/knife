package me.tom.knife;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class TitleSwitch extends TitleLayout {

    private SwitchCompat mSwitch;

    public TitleSwitch(Context context) {
        super(context);
    }

    public TitleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleSwitch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SwitchCompat getSwitch() {
        return mSwitch;
    }

    @Override
    protected void init(AttributeSet attrs, int defStyle) {
        super.init(attrs, defStyle);
        boolean isChecked = false;
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TitleSwitch, defStyle, 0);
            isChecked = typedArray.getBoolean(R.styleable.TitleSwitch_isSwitchChecked, false);
            typedArray.recycle();
        }

        mSwitch = new SwitchCompat(getContext());
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        layoutParams.addRule(CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.addRule(RIGHT_OF, R.id.title_text_view);
        mSwitch.setBackgroundResource(android.R.color.transparent);
        mSwitch.setChecked(isChecked);
        mSwitch.setLayoutParams(layoutParams);
        addView(mSwitch);
    }
}
