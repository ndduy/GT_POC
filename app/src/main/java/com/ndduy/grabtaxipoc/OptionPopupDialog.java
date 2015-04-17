package com.ndduy.grabtaxipoc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ndduy on 2/4/15.
 */
public class OptionPopupDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.view_popup, null);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view);

        final Button amButton = (Button) view.findViewById(R.id.amButton);
        final Button pmButton = (Button) view.findViewById(R.id.pmButton);

        amButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amButton.setSelected(true);
                pmButton.setSelected(false);
            }
        });

        pmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pmButton.setSelected(true);
                amButton.setSelected(false);
            }
        });

        return builder.create();
    }
}
