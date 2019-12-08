package com.example.bryan.assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_message)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
