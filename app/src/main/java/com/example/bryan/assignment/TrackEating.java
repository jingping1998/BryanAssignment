package com.example.bryan.assignment;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TrackEating extends AppCompatActivity {

    private Button add, view;
    private DatePicker mDatePicker;
    Calendar c;
    DatePickerDialog mDatePickerDialog;
    DatabaseHelper2 mDatabaseHelper2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_eating);

        mDatabaseHelper2 = new DatabaseHelper2(this);

        add = (Button) findViewById(R.id.add_btnl);
        view = (Button) findViewById(R.id.view_btn);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TrackEating.this);
                View view = getLayoutInflater().inflate(R.layout.add_date_dialog, null);
                final EditText mCal = (EditText) view.findViewById(R.id.cal_got);
                final TextView mDate = (TextView) view.findViewById(R.id.date_tv2);
                Button mAddButton = (Button) view.findViewById(R.id.button_add);

                mDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       c = Calendar.getInstance();
                       int day = c.get(Calendar.DAY_OF_MONTH);
                       int month = c.get(Calendar.MONTH);
                       int year = c.get(Calendar.YEAR);

                        mDatePickerDialog = new DatePickerDialog(TrackEating.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                            }
                        }, day, month, year);

                        mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        mDatePickerDialog.show();
                    }
                });

                mAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mCal.getText().toString().isEmpty() && !mDate.getText().toString().isEmpty()){
                            String task = String.valueOf(mCal.getText());
                            String date = String.valueOf(mDate.getText());
                            mDatabaseHelper2.addData(date, task);
                            Toast.makeText(TrackEating.this, "Added", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(TrackEating.this, "Something Missing", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setView(view);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(TrackEating.this);
                View view = getLayoutInflater().inflate(R.layout.dialog2, null);
                final TextView mCal = (TextView) view.findViewById(R.id.cal_tv);
                final TextView mDate = (TextView) view.findViewById(R.id.date_tv);

                mDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        c = Calendar.getInstance();
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        int month = c.get(Calendar.MONTH);
                        int year = c.get(Calendar.YEAR);

                        mDatePickerDialog = new DatePickerDialog(TrackEating.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                                String date = String.valueOf(mDate.getText());
                                Cursor data = mDatabaseHelper2.getInfo();
                                while (data.moveToNext()){
                                    if (data.getString(1).equals(date)){

                                        mCal.setText(data.getString(2));
                                    }else{
                                        continue;
                                    }
                                }
                            }
                        }, day, month, year);

                        mDatePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                        mDatePickerDialog.show();
                    }
                });
                mBuilder.setView(view);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }
}
