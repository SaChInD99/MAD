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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeUserPassword extends AppCompatActivity {

    private EditText pswchange;
    private Button pswchangeBTN;

    private FirebaseUser firebaseUser;

    String passwordpattern ="(?=\\S+$).{8,20}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_password);
        pswchange = findViewById(R.id.CHANGE_USER_PASSWORD);
        pswchangeBTN = findViewById(R.id.CHANGE_USER_BTN);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        pswchangeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userpasswordnew = pswchange.getText().toString();

                if (TextUtils.isEmpty(userpasswordnew)) {
                    pswchange.setError("Password is Required");
                    pswchange.requestFocus();
                    return;
                }
                if(!userpasswordnew.matches(passwordpattern)){
                    pswchange.setError("invalid email pattern");
                    pswchange.requestFocus();
                    return;

                }

                firebaseUser.updatePassword(userpasswordnew).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(ChangeUserPassword.this,"Password Change",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangeUserPassword.this,UserProfile.class);
                            startActivity(intent);
                            finish();
                        }else{

                            Toast.makeText(ChangeUserPassword.this,"Password Change Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}