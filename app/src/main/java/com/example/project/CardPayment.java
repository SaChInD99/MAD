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

public class CardPayment extends AppCompatActivity {

    EditText CardNum,SeqNum,ExDate,phnNum,crdName;
    Button btnCardPay;
    DatabaseReference reff;
    FirebaseDatabase Database;
    int maxId=0;
    CardPay CPay;
    public Button button;

    String total;

    TextView txttot,dis,finalvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_pay);

        Intent paypal = getIntent();
        total = paypal.getStringExtra("Total");

        txttot = findViewById(R.id.iamount);
        dis = findViewById(R.id.discount);
        finalvalue = findViewById(R.id.famount);

        txttot.setText(total);



        double disamount = Double.parseDouble(dis.getText().toString());
        double amount = Double.parseDouble(txttot.getText().toString());

        double damount = amount - disamount;
        finalvalue.setText(String.valueOf(damount));

        CardNum = (EditText)findViewById(R.id.CardNum);
        SeqNum = (EditText)findViewById(R.id.SeqNum);
        ExDate = (EditText)findViewById(R.id.ExDate);
        phnNum = (EditText)findViewById(R.id.phnNum);
        crdName = (EditText)findViewById(R.id.crdName);
        btnCardPay = (Button)findViewById(R.id.btnCardPay);

        CPay = new CardPay();

        reff = Database.getInstance().getReference().child("CardPay");

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

        btnCardPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SeqNum.length()==0){
                    SeqNum.setError("Enter CVV");
                }
                else if (ExDate.length()==0){
                    ExDate.setError("Enter Expire Date");
                }
                else if (phnNum.length()==0){
                    phnNum.setError("Enter Phone Number");
                }
                else if (crdName.length()==0){
                    crdName.setError("Enter Name On Card");
                }
                else{

                    Float CardNu = Float.parseFloat(CardNum.getText().toString().trim());
                    CPay.setCardNum(CardNu);
                    CPay.setSeqNum(SeqNum.getText().toString().trim());
                    CPay.setExDate(ExDate.getText().toString().trim());
                    CPay.setPhnNum(phnNum.getText().toString().trim());
                    CPay.setCrdName(crdName.getText().toString().trim());
                    reff.child(String.valueOf(maxId+1)).setValue(CPay);

                    Toast.makeText(CardPayment.this,  "Payment Details added successfully",Toast.LENGTH_LONG).show();
                }

            }
        });


        button = (Button) findViewById(R.id.buttoncdret);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CardPayment.this,cardRet.class);
                startActivity(intent);
            }
        });



    }
}