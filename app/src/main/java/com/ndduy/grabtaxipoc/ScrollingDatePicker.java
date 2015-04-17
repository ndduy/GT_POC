package com.ndduy.grabtaxipoc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ndduy on 24/3/15.
 */
public class ScrollingDatePicker extends FrameLayout implements ObservableHorizontalScrollView.OnScrollChangedListener {
    private View mLeftSpacer;
    private View mRightSpacer;
    private View mLeftSpacerHighlight;
    private View mRightSpacerHighlight;
    private ObservableHorizontalScrollView mScrollView;
    private PassThroughHorizontalScrollView mScrollViewHighlight;
    private ArrayList<DateItemView> dateItemViews;
    private ArrayList<DateItemView> dateItemViewsHighlight;
    private View backgroundImage;
    private ImageView overay;

    public ScrollingDatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Create our internal scroll view
        mScrollView = new ObservableHorizontalScrollView(context);
        mScrollView.setHorizontalScrollBarEnabled(false);
        mScrollView.setOnScrollChangedListener(this);
        addView(mScrollView);

        backgroundImage = new View(context);
        backgroundImage.setBackgroundColor(context.getResources().getColor(R.color.greenbt));
        addView(backgroundImage);

        mScrollViewHighlight = new PassThroughHorizontalScrollView(context);
        mScrollViewHighlight.setHorizontalScrollBarEnabled(false);
        addView(mScrollViewHighlight);

        overay = new ImageView(context);
        overay.setImageDrawable(context.getDrawable(R.drawable.overlay));
        overay.setScaleType(ImageView.ScaleType.FIT_XY);
        addView(overay);

        // Create a horizontal (by default) LinearLayout as our child container
        final LinearLayout container = new LinearLayout(context);
        mScrollView.addView(container);

        // Create a horizontal (by default) LinearLayout as our child container
        final LinearLayout container2 = new LinearLayout(context);
        mScrollViewHighlight.addView(container2);

        dateItemViews = new ArrayList<>();
        dateItemViewsHighlight = new ArrayList<>();

        for (int i=0; i<7; i++){
            Calendar cal = GregorianCalendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DAY_OF_YEAR, i );
            Date itemDate = cal.getTime();

            DateItemView view = new DateItemView(context);
            view.setDate(itemDate);

            DateItemView view2 = new DateItemView(context);
            view2.setDate(itemDate);

            dateItemViews.add(view);
            dateItemViewsHighlight.add(view2);

            container.addView(view);
            container2.addView(view2);

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
            final int width = getWidth();

            final ViewGroup.LayoutParams selfLayout = this.getLayoutParams();
            selfLayout.height = width/5;
            this.setLayoutParams(selfLayout);

            final FrameLayout.LayoutParams backgroundLayout = (LayoutParams) backgroundImage.getLayoutParams();
            backgroundLayout.height = width/5;
            backgroundLayout.width = width/5;
            backgroundLayout.gravity = Gravity.CENTER;
            backgroundImage.setLayoutParams(backgroundLayout);

            final FrameLayout.LayoutParams imageOverlayLayout = (LayoutParams) overay.getLayoutParams();
            imageOverlayLayout.height = width/5;
            imageOverlayLayout.width = width/5;
            imageOverlayLayout.gravity = Gravity.CENTER;
            overay.setLayoutParams(imageOverlayLayout);

            final ViewGroup.LayoutParams leftParams = mLeftSpacer.getLayoutParams();
            leftParams.width = 2*width / 5;
            mLeftSpacer.setLayoutParams(leftParams);

            final ViewGroup.LayoutParams rightParams = mRightSpacer.getLayoutParams();
            rightParams.width = 2*width / 5;
            mRightSpacer.setLayoutParams(rightParams);

            final FrameLayout.LayoutParams scrollViewParam = (LayoutParams) mScrollView.getLayoutParams();
            scrollViewParam.gravity = Gravity.CENTER_VERTICAL;
            mScrollView.setLayoutParams(scrollViewParam);

            final FrameLayout.LayoutParams highlightParam = (LayoutParams) mScrollViewHighlight.getLayoutParams();
            highlightParam.width = width/5;
            highlightParam.gravity = Gravity.CENTER;
            mScrollViewHighlight.setLayoutParams(highlightParam);

            for (int i=0; i<dateItemViews.size(); i++){
                final LinearLayout.LayoutParams itemParams = (LinearLayout.LayoutParams) dateItemViews.get(i).getLayoutParams();
                itemParams.width = width / 5;
                itemParams.gravity = Gravity.CENTER_VERTICAL;
                dateItemViews.get(i).setLayoutParams(itemParams);
            }

            for (int i=0; i<dateItemViewsHighlight.size(); i++){
                dateItemViewsHighlight.get(i).setBiggerFont();
                final LinearLayout.LayoutParams itemParams = (LinearLayout.LayoutParams) dateItemViewsHighlight.get(i).getLayoutParams();
                itemParams.width = width / 5;
                itemParams.gravity = Gravity.CENTER_VERTICAL;
                dateItemViewsHighlight.get(i).setLayoutParams(itemParams);
            }
        }
    }

    @Override
    public void onScrollChanged(ObservableHorizontalScrollView view, int l, int t) {
        mScrollViewHighlight.scrollTo(l,t);
    }

    // do stuff with the scroll listener we created early to make our values usable.
}
