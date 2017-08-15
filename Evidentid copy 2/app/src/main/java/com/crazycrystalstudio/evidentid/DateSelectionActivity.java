package com.crazycrystalstudio.evidentid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class DateSelectionActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static String KEY= "result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_selection);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        //month starts from 0
        month++;
        String result=""+year;
        if(month > 9)
            result += month;
        else
            result += ("0"+month);

        if(day > 9)
            result += day;
        else
            result += ("0"+day);

        setDate(result);
    }

    private void setDate(String result)
    {
        Intent intent = new Intent(DateSelectionActivity.this, MainActivity.class);
        intent.putExtra(KEY, result);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    //called on button click
    public void datePicker(View view)
    {
        DatePickerFrag fragment = new DatePickerFrag();
        fragment.show(getSupportFragmentManager(), "DateSelectionActivity");
    }
}
