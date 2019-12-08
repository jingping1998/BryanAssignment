package com.example.bryan.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Button bCountCalories;
    Button bTrackEating, bSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Buttons for main menu
        bCountCalories = findViewById(R.id.countCalories);
        bTrackEating = findViewById(R.id.trackEating);
        bSummary = findViewById(R.id.summary);

        //Click on Count Calories
        bCountCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), CountCalories.class);
                startActivityForResult(intent, 0);
            }
        });

        //Click on Track Eating
        bTrackEating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, TrackEating.class);
                startActivity(intent);
            }
        });

        bSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, SummryActivity.class);
                startActivity(intent);
            }
        });
    }
}
