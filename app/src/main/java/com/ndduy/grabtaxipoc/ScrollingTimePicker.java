package com.ndduy.grabtaxipoc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ndduy on 2/4/15.
 */
public class ScrollingTimePicker extends FrameLayout {

    private ObservableVerticalScrollView mScrollView;
    private View mLeftSpacer;
    private View mRightSpacer;
    private ArrayList<TextView> dateItemViews;

    public ScrollingTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create our internal scroll view
        mScrollView = new ObservableVerticalScrollView(context);
        mScrollView.setVerticalScrollBarEnabled(false);
        addView(mScrollView);

        // Create a horizontal (by default) LinearLayout as our child container
        final LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);
        mScrollView.addView(container);

        dateItemViews = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            TextView textView = new TextView(context);
            textView.setText("" + i);
            container.addView(textView);
            textView.setGravity(Gravity.CENTER);
            dateItemViews.add(textView);

        }

        // Create the left and right spacers, don't worry about their dimesnions, yet.
        mLeftSpacer = new View(context);
        container.addView(mLeftSpacer, 0);

        mRightSpacer = new View(context);
        container.addView(mRightSpacer);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            // Layout the spacers now that we are measured
            final int height = getHeight();
            final int cellSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics());

            final ViewGroup.LayoutParams leftParams = mLeftSpacer.getLayoutParams();
            leftParams.width = height / 3;
            leftParams.height = height / 3;
            mLeftSpacer.setLayoutParams(leftParams);

            final ViewGroup.LayoutParams rightParams = mRightSpacer.getLayoutParams();
            rightParams.width = height / 3;
            rightParams.height = height / 3;
            mRightSpacer.setLayoutParams(rightParams);

            for (int i = 0; i < dateItemViews.size(); i++) {
                final LinearLayout.LayoutParams itemParams = (LinearLayout.LayoutParams) dateItemViews.get(i).getLayoutParams();
                itemParams.width = height / 3;
                itemParams.height = height / 3;
                dateItemViews.get(i).setLayoutParams(itemParams);
            }

        }
    }
}