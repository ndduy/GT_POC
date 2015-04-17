package com.ndduy.grabtaxipoc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ndduy on 25/3/15.
 */
public class DateItemView extends LinearLayout {

    private TextView dateTypeTextView;
    private TextView dateTextView;

    private Date date;


    public DateItemView(Context context) {
        this(context, null);
    }

    public DateItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_dateitem, this);

        dateTypeTextView = (TextView) this.findViewById(R.id.dateTypeTextView);
        dateTextView = (TextView) this.findViewById(R.id.dateTextView);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;

        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");

        if (dateTypeTextView!=null) {
            dateTypeTextView.setText(sdf.format(date));
        }

        if (dateTextView!=null) {
            dateTextView.setText(sdf2.format(date));
        }

    }

    public void setBiggerFont(){
        dateTypeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
    }
}

