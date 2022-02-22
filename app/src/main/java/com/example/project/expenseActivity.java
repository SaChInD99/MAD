package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class expenseActivity extends AppCompatActivity {

    EditText income,expense,profit;

    Button calcTotal;

    String incomerec;
    double totprofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Intent intent = getIntent();
        incomerec = intent.getStringExtra("Income");

        income = findViewById(R.id.IncomeTotal);
        expense = findViewById(R.id.ExpenseTotal);
        profit = findViewById(R.id.totProfit);
        calcTotal = findViewById(R.id.calculateBtn);

        //income.setText(String.valueOf(incomerec));

        calcTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totprofit = calctotProfit(Double.parseDouble(income.getText().toString().trim()),Double.parseDouble(expense.getText().toString().trim()));

                profit.setText(String.valueOf(totprofit));
            }
        });
    }


//Calculate total Profit
    public double calctotProfit(double income,double expense){
        return income - expense;
    }
}