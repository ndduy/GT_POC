package com.ndduy.grabtaxipoc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ndduy on 7/4/15.
 */
public class PaymentOptionPopupDialog extends android.support.v4.app.DialogFragment {

    View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.view_paymentpopup, null);

        view.findViewById(R.id.cashButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPaymentButtonSelected(true);
            }
        });
        view.findViewById(R.id.cardButtonLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPaymentButtonSelected(false);
            }
        });

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);

        return builder.create();
    }

    public void setPaymentButtonSelected(boolean isCash) {
        LinearLayout cashLayout = (LinearLayout) view.findViewById(R.id.cashButtonLayout);
        LinearLayout cardLayout = (LinearLayout) view.findViewById(R.id.cardButtonLayout);

        TextView cashText = (TextView) cashLayout.findViewById(R.id.cashTextView);
        TextView cardText = (TextView) cardLayout.findViewById(R.id.cardTextView);

        ImageView cashImageView = (ImageView) cashLayout.findViewById(R.id.cashImageView);
        ImageView cardImageView = (ImageView) cardLayout.findViewById(R.id.cardImageView);

        cashLayout.setSelected(isCash);
        cashText.setSelected(isCash);

        cardLayout.setSelected(!isCash);
        cardText.setSelected(!isCash);

        if (isCash) {
            cashImageView.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint
            cardImageView.setColorFilter(Color.argb(255, 0, 0, 0)); // White Tint
        }else{
            cardImageView.setColorFilter(Color.argb(255, 255, 255, 255)); // White Tint
            cashImageView.setColorFilter(Color.argb(255, 0, 0, 0)); // White Tint
        }
    }

}
