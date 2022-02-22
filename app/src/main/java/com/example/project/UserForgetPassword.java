package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class UserForgetPassword extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forget_password);

        passwordEmail = (EditText)findViewById(R.id.FORGET_EMAIL);
        resetPassword = (Button)findViewById(R.id.FORGET_PASSWORD);
        firebaseAuth =  FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(UserForgetPassword.this,"Please Enter your Registration Email ID",Toast.LENGTH_SHORT).show();

                }else{

                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                Toast.makeText(UserForgetPassword.this,"password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent5 = new Intent(UserForgetPassword.this,Login_Activity.class);
                                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent5);

                            }else{

                                Toast.makeText(UserForgetPassword.this,"Error in Sending Password to reset email....",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}