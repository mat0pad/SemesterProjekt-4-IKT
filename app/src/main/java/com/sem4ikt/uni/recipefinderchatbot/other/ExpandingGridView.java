package com.sem4ikt.uni.recipefinderchatbot.other;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by mathiaslykkepedersen on 27/03/2017.
 */

public class ExpandingGridView extends GridView {


    public ExpandingGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandingGridView(Context context) {
        super(context);
    }

    public ExpandingGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
