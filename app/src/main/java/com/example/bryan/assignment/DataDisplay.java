package com.example.bryan.assignment;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DataDisplay extends CountCalories {
    private double EXTRA_CALORIES,EXTRA_HEIGHT, EXTRA_WEIGHT, EXTRA_AGE, EXTRA_GENDER;
    private TextView mCalories, mAge, mHeight, mWeight, mGender;
    private Button back_btn, save_btn;
    String Gender;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        details = new Details();
        mDatabaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        EXTRA_CALORIES = intent.getDoubleExtra("EXTRA_CALORIES",-1);
        EXTRA_AGE = intent.getIntExtra("EXTRA_AGE", -1);
        EXTRA_HEIGHT = intent.getDoubleExtra("EXTRA_HEIGHT", -1);
        EXTRA_WEIGHT = intent.getDoubleExtra("EXTRA_WEIGHT", -1);
        EXTRA_GENDER = intent.getDoubleExtra("EXTRA_GENDER", -1);

        mCalories = (TextView) findViewById(R.id.calories_load);
        mAge = (TextView) findViewById(R.id.age_load);
        mHeight = (TextView) findViewById(R.id.height_load);
        mWeight = (TextView) findViewById(R.id.weight_load);
        mGender = (TextView) findViewById(R.id.tv_gender);
        back_btn = (Button) findViewById(R.id.back_btn);
        save_btn = (Button) findViewById(R.id.save_btn);

        if (EXTRA_GENDER == 5.0){
            Gender = "Male";
        }else{
            Gender = "Female";
        }

        mCalories.setText(Double.toString(EXTRA_CALORIES) + " burnt");
        mAge.setText(Integer.toString((int)EXTRA_AGE));
        mHeight.setText(Double.toString(EXTRA_HEIGHT) + " cm");
        mWeight.setText(Double.toString(EXTRA_WEIGHT) + " kg");
        mGender.setText(Gender);


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataDisplay.this, MainMenu.class);
                startActivity(intent);
            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.addData(Double.toString(EXTRA_HEIGHT),Double.toString(EXTRA_WEIGHT),Integer.toString((int)EXTRA_AGE),Gender,Double.toString(EXTRA_CALORIES));
                Toast.makeText(DataDisplay.this, "Saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
