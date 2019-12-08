package com.example.bryan.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class CountCalories extends AppCompatActivity  {
    Details details;
    double activeLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_calories);

        RadioButton bMale = findViewById(R.id.genderMale);
        RadioButton bFemale = findViewById(R.id.genderFemale);
        Button bCalculate = findViewById(R.id.calculate);
        final EditText mWeight = findViewById(R.id.weight);
        final EditText mHeight = findViewById(R.id.height);
        final EditText mAge = findViewById(R.id.age);

        details = new Details();

        //Spinner
        final Spinner activenessSpinner = findViewById(R.id.activeness);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.activeness, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activenessSpinner.setAdapter(adapter);

        activenessSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        activeLevel = 1.3;
                        details.setdActiveness(activeLevel);
                        break;
                    case 1:
                        activeLevel = 1.55;
                        details.setdActiveness(activeLevel);
                        break;
                    case 2:
                        activeLevel = 1.65;
                        details.setdActiveness(activeLevel);
                        break;
                    case 3:
                        activeLevel = 1.80;
                        details.setdActiveness(activeLevel);
                        break;
                    case 4:
                        activeLevel = 2.0;
                        details.setdActiveness(activeLevel);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        //If user chooses male
        bMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setdGender(5.0);
            }
        });

        //If user choose female
        bFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setdGender(-161.0);
            }
        });



        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strWeight = mWeight.getText().toString();
                String strHeight = mHeight.getText().toString();
                String strAge = mAge.getText().toString();

                if (TextUtils.isEmpty(strHeight)){
                    //if user does not fill up height
                    mHeight.setError("Please fill up your height");

                } else if (TextUtils.isEmpty(strWeight)){
                    //if user does not fill up weight
                    mWeight.setError("Please fill up your weight");

                } else if (TextUtils.isEmpty(strAge)){
                    //if user does not fill up age
                    mAge.setError("Please fill up age");

                } else {
                    int finalAge = Integer.parseInt(strAge);
                    double finalHeight = Double.parseDouble(strHeight);
                    double finalWeight = Double.parseDouble(strWeight);

                    details.setdAge(finalAge);
                    details.setdHeight(finalHeight);
                    details.setdWeight(finalWeight);
                    double gender = details.getdGender();

                    double bmr = 10*finalWeight + 6.25*finalHeight - 5*finalAge + gender;


                    double activeness = details.getdActiveness();

                    double calories = bmr * activeness;
                    details.setdCalories(calories);


                    Intent intent = new Intent(CountCalories.this,DataDisplay.class);
                    intent.putExtra("EXTRA_CALORIES", calories);
                    intent.putExtra("EXTRA_HEIGHT", finalHeight);
                    intent.putExtra("EXTRA_WEIGHT", finalWeight);
                    intent.putExtra("EXTRA_AGE", finalAge);
                    intent.putExtra("EXTRA_GENDER", gender);

                    startActivity(intent);
                }
            }
        });

        mHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mHeight.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mHeight.setError(null);
            }
        });

        mWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWeight.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mWeight.setError(null);
            }
        });

        mAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAge.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                mAge.setError(null);
            }
        });
    }
}
