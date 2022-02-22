package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cardRet extends AppCompatActivity {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    private Button btndelete;
    DatabaseReference reff;
    private Button btnpri;

    String Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_ret);

        Intent secIntent = getIntent();

        Id=secIntent.getStringExtra("childID");

        a=(TextView) findViewById(R.id.cardNumR);
        b=(TextView) findViewById(R.id.seqNumR);
        c=(TextView) findViewById(R.id.exDateR);
        d=(TextView) findViewById(R.id.crdNameR);
        e=(TextView) findViewById(R.id.phnNumR);
        btndelete=(Button) findViewById(R.id.btndelete);
        btnpri=(Button)findViewById(R.id.btnpri);

        reff= FirebaseDatabase.getInstance().getReference().child("CardPay").child("1");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String cardN =dataSnapshot.child("cardNum").getValue().toString();
                String cardNam =dataSnapshot.child("crdName").getValue().toString();
                String exDa =dataSnapshot.child("exDate").getValue().toString();
                String phnNu =dataSnapshot.child("phnNum").getValue().toString();
                String seqNu =dataSnapshot.child("seqNum").getValue().toString();
                a.setText(cardN);
                b.setText(seqNu);
                c.setText(exDa);
                d.setText(cardNam);
                e.setText(phnNu);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

     btndelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               FirebaseDatabase.getInstance().getReference().child("CardPay").child("1").removeValue();
               Toast.makeText(cardRet.this,  "Delete success",Toast.LENGTH_LONG).show();
               Intent intent = new Intent(cardRet.this,CardPayment.class);
               startActivity(intent);
            }
        });

        btnpri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cardRet.this,main_homepage.class);
                startActivity(intent);
                Toast.makeText(cardRet.this,  "Payment Successful",Toast.LENGTH_LONG).show();
            }
        });


    }
}