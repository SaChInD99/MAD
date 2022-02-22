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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EmailRet extends AppCompatActivity {
    TextView ab;
    EditText Update;
    DatabaseReference reff;
    Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_ret);

        ab=(TextView) findViewById(R.id.emailRet);
        Update=(EditText)findViewById(R.id.btnup);
        btnUpdate =(Button) findViewById(R.id.btnUpdate);


                reff= FirebaseDatabase.getInstance().getReference().child("PayPal").child("1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Email =dataSnapshot.child("email").getValue().toString();
                        ab.setText(Email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email =Update.getText().toString().trim();
                        HashMap hashMap=new HashMap();
                        hashMap.put("email",email);


                        reff.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(EmailRet.this,  "Update success",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });


        }
    public void loadweb(View view){

        Intent intent= new Intent( EmailRet.this, WebAct.class);
        startActivity(intent);
    }
}