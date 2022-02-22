package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Payment extends AppCompatActivity {
    public Button button,button1;
    String total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        Intent pay = getIntent();
        total = pay.getStringExtra("totprice");

        button = (Button) findViewById(R.id.btnCardPay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this,PaypalPay.class);
                intent.putExtra("Total",total);
                startActivity(intent);
            }
        });

        button1 = (Button) findViewById(R.id.btnCard);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Payment.this,CardPayment.class);
                intent.putExtra("Total",total);
                startActivity(intent);
            }
        });

    }
}