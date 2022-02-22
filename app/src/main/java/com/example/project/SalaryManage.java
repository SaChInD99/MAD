package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SalaryManage extends AppCompatActivity {

    EditText gid,bSal,bRate,NOT,totSal;
    Button btn,btn2;
    double totsal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary_manage);

        btn = findViewById(R.id.salCalBut);
        btn2 = findViewById(R.id.checkSal);

        gid = (EditText) findViewById(R.id.salRegNo);
        bSal = (EditText) findViewById(R.id.salBasSal);
        bRate = (EditText) findViewById(R.id.salBonRate);
        NOT = (EditText) findViewById(R.id.salNoOfT);
        totSal = (EditText) findViewById(R.id.TotSal);



    }

    @Override
    protected void onResume() {
        super.onResume();


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(SalaryManage.this,adminpage.class);
                startActivity(intent2);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(gid.getText().toString())){
                    gid.setError("Employee ID is Required");
                    gid.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(bSal.getText().toString())){
                    bSal.setError("Basic Salary is Required");
                    bSal.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(bRate.getText().toString())){
                    bRate.setError("Bonus Rate is Required");
                    bRate.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(NOT.getText().toString())) {
                    NOT.setError("NO Of Trips are Require");
                    NOT.requestFocus();
                    return;
                }

                totsal = calctotsalary(Double.parseDouble(bSal.getText().toString()),Double.parseDouble(bRate.getText().toString()),Integer.parseInt(NOT.getText().toString()));
                totSal.setText(String.valueOf(totsal));

            }
        });

    }

    public double calctotsalary(double bassal, double rate, int trips){

        double initialsal;

        initialsal = bassal + (bassal*(rate/100));

        if(trips >=100 && trips<200){
            initialsal+=1000;
        }if(trips>=200){
            initialsal+=1500;
        }

        return initialsal;

    }


}