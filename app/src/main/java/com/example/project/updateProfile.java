package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updateProfile extends AppCompatActivity {

    private EditText newUserName,newUserEmail,newUserAddress,newUserPhone,newPassword;
    private ImageView back;
    private TextView updateBtn;
    String Newemailpattern = "[a-zA-z0-9._=]+@[a-z]+\\.+[a-z]+";



    private FirebaseAuth auth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        newUserName = findViewById(R.id.UPDATE_yourname);
        newUserEmail = findViewById(R.id.UPDATE_email);
        newUserAddress = findViewById(R.id.UPDATE_Address);
        newUserPhone = findViewById(R.id.UPDATE_Phone);
        newPassword= findViewById(R.id.UPDATE_Password);
        back = findViewById(R.id.ARROW_BACK);


        updateBtn = findViewById(R.id.UPDATEprofileBTN);

        auth = FirebaseAuth.getInstance();
        final DatabaseReference userRef = reference.child(auth.getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                regClass1 RegClass = snapshot.getValue(regClass1.class);
                newUserName.setText(RegClass.getName());
                newUserEmail.setText(RegClass.getEmail());
                newUserPhone.setText(RegClass.getPhone());
                newUserAddress.setText(RegClass.getEAddress());
                newPassword.setText(RegClass.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(updateProfile.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }

        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = newUserName.getText().toString();
                String email = newUserEmail.getText().toString();
                String address = newUserAddress.getText().toString();
                String phone = newUserPhone.getText().toString();
                String password = newPassword.getText().toString();

                if(TextUtils.isEmpty(name)){
                    newUserName.setError("Name is required");
                    newUserName.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    newUserEmail.setError("Email is Required");
                    newUserEmail.requestFocus();
                    return;
                }
                if(!email.matches(Newemailpattern)){
                    newUserEmail.setError("invalid email pattern");
                    newUserEmail.requestFocus();
                    return;

                } if (TextUtils.isEmpty(password)) {
                    newPassword.setError("Password is Required");
                    newPassword.requestFocus();
                    return;
                }

                regClass1 userprofile = new regClass1(name,email,phone,address,password);

                userRef.setValue(userprofile);

                finish();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(updateProfile.this,UserProfile.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

}