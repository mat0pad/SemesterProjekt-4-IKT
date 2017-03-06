package com.sem4ikt.uni.recipefinderchatbot.other;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Created by mathiaslykkepedersen on 9/02/17.
 */
public class CanaroTextView extends android.support.v7.widget.AppCompatTextView {
    public CanaroTextView(Context context) {
        this(context, null);
    }

    public CanaroTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanaroTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(App.canaroExtraBold);
    }

}
