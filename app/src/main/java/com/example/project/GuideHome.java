package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GuideHome extends AppCompatActivity {

    Button manGuide,manSal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_home);

        manGuide = (Button) findViewById(R.id.manageGuide);
        manSal = (Button) findViewById(R.id.salDetails);

        manGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(GuideHome.this,MainActivity.class);
                startActivity(intent2);
            }
        });
        manSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(GuideHome.this,SalaryManage.class);
                startActivity(intent3);
            }
        });

    }
}