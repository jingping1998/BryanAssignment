package com.example.bryan.assignment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SummryActivity extends AppCompatActivity {
    private Button btn_return;
    private TextView Cal, Height, Weight, Age, Gender;
    private String mCal, mHeight, mWeight, mAge, mGender;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summry);

        mDatabaseHelper = new DatabaseHelper(this);

        Cal = (TextView) findViewById(R.id.calories_load);
        Height = (TextView) findViewById(R.id.height_load);
        Weight = (TextView) findViewById(R.id.weight_load);
        Age = (TextView) findViewById(R.id.age_load);
        Gender = (TextView) findViewById(R.id.gender_load);
        btn_return = (Button) findViewById(R.id.back_btn);

        Cursor data = mDatabaseHelper.getInfo();
        while (data.moveToNext()){
            mHeight = data.getString(1);
            mWeight = data.getString(2);
            mAge = data.getString(3);
            mGender = data.getString(4);
            mCal = data.getString(5);
        }

        Cal.setText(mCal);
        Height.setText(mHeight);
        Weight.setText(mWeight);
        Age.setText(mAge);
        Gender.setText(mGender);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummryActivity.this,MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
