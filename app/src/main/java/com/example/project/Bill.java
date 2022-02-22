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

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.*;

public class Bill extends AppCompatActivity {

    TextView txt1,txt2,txt3,txt4,txt5;
    EditText edt1;
    public Button button,btn1;

    FirebaseListAdapter adapter;
    DatabaseReference dbref;
    PaymentModel paymentModel;
    int maxvalue = 001;

    String locat,price,roomID;
    double totprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill);

        paymentModel = new PaymentModel();

        button = (Button) findViewById(R.id.btnCardPay);
        btn1 = (Button) findViewById(R.id.tot);
        txt1 = findViewById(R.id.id);
        txt2 = findViewById(R.id.location);
        txt3 = findViewById(R.id.price);
        txt4 = findViewById(R.id.totprice);
        txt5 = findViewById(R.id.date);

        edt1 = findViewById(R.id.qty);

        Intent secintent = getIntent();
        roomID = secintent.getStringExtra("roomID");
        locat = secintent.getStringExtra("roomlocation");
        price = secintent.getStringExtra("roomPrice");

        //final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",Locale.getDefault());
        final String date = String.valueOf(Calendar.getInstance().get(Calendar.DATE)) + "/" +  String.valueOf(Calendar.getInstance().get(Calendar.MONTH)) + "/" + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) ;
        txt5.setText(date);
        txt1.setText(roomID);
        txt2.setText(locat);
        txt3.setText(price);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totprice = calcPrice(Double.valueOf(price),Integer.valueOf(edt1.getText().toString()));
                txt4.setText(String.valueOf(totprice));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Payment");

                paymentModel.setPaymentID(String.valueOf(maxvalue+1));
                paymentModel.setPayCategory("Rooms");
                paymentModel.setPayDate(date);
                paymentModel.setAmount(txt4.getText().toString());

                String finalPrice = txt4.getText().toString();

                dbref.push().setValue(paymentModel);

                Toast.makeText(getApplicationContext(), "Navigating to Payment Mode", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Bill.this,Payment.class);
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

    //Calculate Total price
    public double calcPrice(double price,int qty){
        return price * qty(qty);
    }

    public int qty(int qt){
        if(qt < 0){
            return -1;
        }else{
            return qt;
        }
    }
}