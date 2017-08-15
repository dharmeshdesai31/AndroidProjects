package com.crazycrystalstudio.evidentid;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Calendar;
import 	java.util.Date;

public class DatePickerFrag extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener)
                        getActivity(), year, month, day);
        dialog.getDatePicker().setMaxDate(new Date().getTime()+100000);
        return dialog;
    }
}
