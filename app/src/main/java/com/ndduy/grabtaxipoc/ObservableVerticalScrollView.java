package com.ndduy.grabtaxipoc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by ndduy on 2/4/15.
 */
public class ObservableVerticalScrollView extends ScrollView {

    private static final int SWIPE_MIN_DISTANCE = 5;
    private static final int SWIPE_THRESHOLD_VELOCITY = 300;

    private GestureDetector mGestureDetector;
    private int mActiveFeature = 0;

    public ObservableVerticalScrollView(Context context) {
        super(context);
        initView(context);
    }

    public ObservableVerticalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //If the user swipes
                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    int scrollY = getScrollY() + getMeasuredHeight()/2;
                    View layout = getChildForOffset(scrollY);
                    if (layout != null){
                        smoothScrollTo(0, layout.getTop() - getMeasuredHeight()*1/3);
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
            if (layout.getTop() <= offset && layout.getBottom() >= offset){
                return layout;
            }
        }

        return null;
    }


    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                //right to left
                if (e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                    int scrollY = getScrollY() + getMeasuredHeight()/2;
                    View layout = getChildForOffset(scrollY);
                    if (layout != null){
                        smoothScrollTo(0, layout.getTop() - getMeasuredHeight()*1/3);
                    }
                    return true;
                }
                //left to right
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    int scrollY = getScrollY() + getMeasuredHeight()/2;
                    View layout = getChildForOffset(scrollY);
                    if (layout != null){
                        smoothScrollTo(0, layout.getTop() - getMeasuredHeight()*1/3);
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
