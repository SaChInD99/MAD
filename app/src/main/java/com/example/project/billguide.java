package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class billguide extends AppCompatActivity {

    String name,address,contact;

    TextView txt5,txt1,txt2,txt3,txt4,txt6;
    EditText edt1;
    Button btn1,button;
    double totprice;

    FirebaseListAdapter adapter;
    DatabaseReference dbref;
    PaymentModel paymentModel;
    int maxvalue = 001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billguide);

        paymentModel = new PaymentModel();

        txt5 = findViewById(R.id.date);
        txt1 = findViewById(R.id.id);
        txt2 = findViewById(R.id.location);
        txt3 = findViewById(R.id.price);
        txt4 = findViewById(R.id.totprice);
        txt6 = findViewById(R.id.unitprice);

        edt1 = findViewById(R.id.qty);

        btn1 = findViewById(R.id.tot);
        button = findViewById(R.id.btnCardPay);

        Intent secintent = getIntent();
        name = secintent.getStringExtra("guideName");
        address = secintent.getStringExtra("guideAddress");
        contact = secintent.getStringExtra("guideContact");

        final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        txt5.setText(df.toString());
        txt1.setText(name);
        txt2.setText(address);
        txt3.setText(contact);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totprice = calcPrice(Double.valueOf(txt6.getText().toString()),Integer.valueOf(edt1.getText().toString()));
                txt4.setText(String.valueOf(totprice));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Payment");

                paymentModel.setPaymentID(String.valueOf(maxvalue+1));
                paymentModel.setPayCategory("Guides");
                paymentModel.setPayDate(df.toString());
                paymentModel.setAmount(txt4.getText().toString());

                String finalPrice = txt4.getText().toString();

                dbref.push().setValue(paymentModel);

                Toast.makeText(getApplicationContext(), "Navigating to Payment Mode", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(billguide.this,Payment.class);
                intent.putExtra("totprice",finalPrice);
                startActivity(intent);
            }
        });

        dbref = FirebaseDatabase.getInstance().getReference().child("Payment");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxvalue = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //calculate Total price
    public double calcPrice(double price,int qty){
        return price * qty;
    }
}