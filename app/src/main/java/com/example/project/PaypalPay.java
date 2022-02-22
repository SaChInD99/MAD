package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PaypalPay extends AppCompatActivity {

    EditText Email;
    Button btnPal;
    DatabaseReference reff;
    FirebaseDatabase Database;
    int maxId=0;
    PayPal Pay;
    public Button button;

    String total;

    TextView txttot,dis,finalvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal_pay);

        Intent paypal = getIntent();
        total = paypal.getStringExtra("Total");

        txttot = findViewById(R.id.tot);
        dis = findViewById(R.id.discount);
        finalvalue = findViewById(R.id.totPrice);

        txttot.setText(total);

        double disamount = Double.parseDouble(dis.getText().toString());
        double amount = Double.parseDouble(txttot.getText().toString());

        double damount = amount - disamount;
        finalvalue.setText(String.valueOf(damount));


        Email = (EditText)findViewById(R.id.Email);
        btnPal = (Button)findViewById(R.id.btnPal);
        Pay = new PayPal();

        reff = Database.getInstance().getReference().child("PayPal");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {
                    maxId = (int)dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnPal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Email.length()==0){
                    Email.setError("Etner Email");
                }
                else{
                    Toast.makeText(PaypalPay.this,  "Data insert success",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(PaypalPay.this,EmailRet.class);
                    startActivity(intent);
                }

                PayPal.setEmail(Email.getText().toString().trim());

                reff.child(String.valueOf(maxId+1)).setValue(Pay);
            }


        });

    }

}