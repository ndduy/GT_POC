package com.ndduy.grabtaxipoc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by ndduy on 24/3/15.
 */
public class ObservableHorizontalScrollView extends HorizontalScrollView {

    private static final int SWIPE_MIN_DISTANCE = 5;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;

    private GestureDetector mGestureDetector;
    private int mActiveFeature = 0;

    public ObservableHorizontalScrollView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * Interface definition for a callback to be invoked with the scroll
     * position changes.
     */
    public interface OnScrollChangedListener {
        /**
         * Called when the scroll position of <code>view</code> changes.
         *
         * @param view The view whose scroll position changed.
         * @param l    Current horizontal scroll origin.
         * @param t    Current vertical scroll origin.
         */
        void onScrollChanged(ObservableHorizontalScrollView view, int l, int t);
    }

    private OnScrollChangedListener mOnScrollChangedListener;

    public ObservableHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //If the user swipes
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    int scrollX = getScrollX() + getMeasuredWidth()/2;
                    View layout = getChildForOffset(scrollX);
                    if (layout != null){
                        smoothScrollTo(layout.getLeft() - getMeasuredWidth()*2/5, 0);
                    }
                    return true;
                } else {
                    return false;
                }
            }
        });

        //Set the gesture detector for detecting a swipe
        mGestureDetector = new GestureDetector(context, new MyGestureDetector());
    }

    private View getChildForOffset(int offset) {

        LinearLayout container = (LinearLayout)getChildAt(0);

        for (int i=0; i<container.getChildCount(); i++){
            View layout = (View) container.getChildAt(i);
            int left = layout.getLeft();
            int right = layout.getRight();
            if (layout.getLeft() <= offset && layout.getRight() >= offset){
                return layout;
            }
        }

        return null;
    }


    public void setOnScrollChangedListener(OnScrollChangedListener l) {
        mOnScrollChangedListener = l;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener.onScrollChanged(this, l, t);
        }
    }


    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                //right to left
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    int scrollX = getScrollX() + getMeasuredWidth()/2;
                    View layout = getChildForOffset(scrollX);
                    if (layout != null){
                        smoothScrollTo(layout.getLeft() - getMeasuredWidth()*2/5, 0);
                    }
                    return true;
                }
                //left to right
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    int scrollX = getScrollX() + getMeasuredWidth()/2;
                    View layout = getChildForOffset(scrollX);
                    if (layout != null){
                        smoothScrollTo(layout.getLeft() - getMeasuredWidth()*2/5, 0);
                    }
                    return true;
                }
            } catch (Exception e) {
                Log.e("Fling", "There was an error processing the Fling event:" + e.getMessage());
            }
            return false;
        }
    }
}
