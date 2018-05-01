package me.tom.knife;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

public class SearchEditText extends ClearEditText {

    private Drawable mSearchIcon;
    private int mSearchIconWidth;
    private int mSearchIconHeight;

    private ISearchEditListener mListener;

    public interface ISearchEditListener {

        void onSubmit(String value);
    }

    public SearchEditText(Context context) {
        super(context);
    }

    public SearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setSearchIcon(Drawable drawable) {
        mSearchIcon = drawable;
        showSearchIcon();
    }

    public void setSearchIconWidth(int width) {
        mSearchIconWidth = width;
        showSearchIcon();
    }

    public void setSearchIconHeight(int height) {
        mSearchIconHeight = height;
        showSearchIcon();
    }

    public void setListener(ISearchEditListener listener) {
        mListener = listener;
    }

    @Override
    protected void init(AttributeSet attrs, int defStyle) {
        super.init(attrs, defStyle);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SearchEditText, defStyle, 0);
        mSearchIcon = typedArray.getDrawable(R.styleable.SearchEditText_searchIcon);
        if (mSearchIcon == null) {
            mSearchIcon = ContextCompat.getDrawable(getContext(), R.mipmap.search);
        }
        mSearchIconWidth = typedArray.getDimensionPixelSize(
                R.styleable.SearchEditText_searchIconWidth,
                getResources().getDimensionPixelSize(R.dimen.search_edit_text_icon_width)
        );
        mSearchIconHeight = typedArray.getDimensionPixelSize(
                R.styleable.SearchEditText_searchIconHeight,
                getResources().getDimensionPixelSize(R.dimen.search_edit_text_icon_height)
        );
        typedArray.recycle();
        setBackgroundResource(R.drawable.search_edit_text_bg);
        setSingleLine(true);
        setImeActionLabel(getResources().getString(R.string.search), KeyEvent.KEYCODE_ENTER);
        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        showSearchIcon();
        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == KeyEvent.KEYCODE_ENTER) {
                    if (mListener != null) {
                        mListener.onSubmit(textView.getText().toString());
                    }
                }
                return false;
            }
        });
    }

    private void showSearchIcon() {
        Drawable[] compoundDrawables = getCompoundDrawables();
        mSearchIcon.setBounds(0, 0, mSearchIconWidth, mSearchIconHeight);
        setCompoundDrawables(mSearchIcon, compoundDrawables[1], compoundDrawables[2], compoundDrawables[2]);
    }
}
