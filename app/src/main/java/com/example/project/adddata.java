package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity {
    EditText name, date, nic, address, cnumber, url;
    Button submit, back;
    int max = 0;

    //Newly Added
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        name = (EditText) findViewById(R.id.add_name);
        date = (EditText) findViewById(R.id.add_date);
        nic = (EditText) findViewById(R.id.add_nic);
        url = (EditText) findViewById(R.id.add_purl);
        address = (EditText) findViewById(R.id.add_address);
        cnumber = (EditText) findViewById(R.id.add_number);

        back = (Button) findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        submit = (Button) findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();


            }
        });

        //Newly Added
        dbref = FirebaseDatabase.getInstance().getReference().child("Guids");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                max = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void processinsert()
    {
        if (TextUtils.isEmpty(name.getText().toString())){
            name.setError("name is Required");
            name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(date.getText().toString())){
            date.setError("date is Required");
            date.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(nic.getText().toString())){
            nic.setError("NIC is Required");
            nic.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(url.getText().toString())){
            url.setError("URL is Require");
            url.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(address.getText().toString())){
            address.setError("Address is Require");
            address.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(cnumber.getText().toString())){
            cnumber.setError("nic is Require");
            cnumber.requestFocus();
            return;
        }


        Map<String,Object> map=new HashMap<>();
        map.put("name",name.getText().toString().trim());
        map.put("date",date.getText().toString().trim());
        map.put("nic",nic.getText().toString().trim());
        map.put("url",url.getText().toString().trim());
        map.put("address",address.getText().toString().trim());
        map.put("cnumber",cnumber.getText().toString().trim());
        map.put("id",String.valueOf(max+1));



        //Initial Connection deleted
        /*FirebaseDatabase.getInstance().getReference().child("students").child(String.valueOf(max+1)).push()*/

        //Newly Added
        dbref.child(String.valueOf(max+1)).setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        name.setText("");
                        date.setText("");
                        nic.setText("");
                        url.setText("");
                        address.setText("");
                        cnumber.setText("");


                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });




    }

}


