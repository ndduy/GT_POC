package com.ndduy.grabtaxipoc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by ndduy on 7/4/15.
 */
public class PromotionPopupDialog extends DialogFragment {

    public enum PromotionPopupDialogStatus {
        PROMOTION_INPUT,
        PROMOTION_ERROR,
        PROMOTION_SUCCESS
    }

    View view;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        view = inflater.inflate(R.layout.view_promotionpopup, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);

        return builder.create();
    }

    private void setPromotionStatus(PromotionPopupDialogStatus status){
        switch (status){
            case PROMOTION_INPUT:
                break;
            case PROMOTION_ERROR:
                break;
            case PROMOTION_SUCCESS:
                break;
            default:
                break;
        }
    }

}
