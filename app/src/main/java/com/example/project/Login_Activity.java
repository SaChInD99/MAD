package com.example.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Login_Activity extends AppCompatActivity {
    private EditText userloginName;
    private EditText userloginpassword;
    private TextView usercreateAcc;
    private TextView userforgotpsw;
    private Button userloginButton;
    private ProgressDialog userprogress;
    private DatabaseReference dbref;

    private FirebaseAuth mAuth;

    String emailpattern = "[a-zA-z0-9._=]+@[a-z]+\\.+[a-z]+";

    private void clearControls(){
        userloginName.setText("");
        userloginpassword.setText("");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        userprogress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {

            // startActivity(new Intent(getApplicationContext(),Profile.class));
        }
        userlogin();
    }
    private  void userlogin(){
        userloginName = findViewById(R.id.login_yourname);
        userloginpassword = findViewById(R.id.login_password);
        userloginButton = findViewById(R.id.Login_Btn);
        userforgotpsw = findViewById(R.id.forgot_Password);
        usercreateAcc = findViewById(R.id.sign_to_Customer);

        userloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String LoginEmail = userloginName.getText().toString().trim();
                final String PasswordLogin = userloginpassword.getText().toString().trim();

                if (TextUtils.isEmpty(LoginEmail)){
                    userloginName.setError("Email is Required");
                    userloginName.requestFocus();
                    return;
                }
                if(!LoginEmail.matches(emailpattern)){
                    userloginName.setError("invalid email pattern");
                    userloginName.requestFocus();
                    return;


                } if (TextUtils.isEmpty(PasswordLogin)) {
                    userloginpassword.setError("Password is Required");
                    userloginpassword.requestFocus();
                    return;
                }

                if(LoginEmail.contains("admin123@gmail.com") && PasswordLogin.contains("admin123")){
                    Intent intent = new Intent(Login_Activity.this,adminpage.class);
                    startActivity(intent);
                }

                userprogress.setMessage("Login in...");
                userprogress.show();


                mAuth.signInWithEmailAndPassword(LoginEmail,PasswordLogin).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            userprogress.dismiss();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Welcome.....\nOne Click Travel Sri Lanka",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login_Activity.this,UserProfile.class);
                            intent.putExtra("id",LoginEmail);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            userprogress.dismiss();
                        }
                    }
                });


                mAuth.signInWithEmailAndPassword("Admin12@gmail.com","admin123").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            userprogress.dismiss();
                            clearControls();
                            Toast.makeText(getApplicationContext(),"Welcome To\nTour Assist Sri Lanka",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Login_Activity.this,ChangeUserPassword.class);
                          // intent.putExtra("id",LoginEmail);
                           // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }else{
                            userprogress.dismiss();
                        }
                    }
                });
            }
        });

        userforgotpsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent4 = new Intent(Login_Activity.this,UserForgetPassword.class);
                 intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 startActivity(intent4);
            }
        });

        usercreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent9= new Intent(Login_Activity.this, MainActivityLogin.class);
                intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent9.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent9);
            }
        });

    }
}