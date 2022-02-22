package com.example.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private TextView proName,proEmail,proPhone,proAddress,btnHome;
    private TextView proEditBtn,changepassword,deleteAccount,LOGOUT;

    private String email, password;

    String userId;

    TextView txt;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference reference = database.getReference().child("users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        final Intent intent = getIntent();
        email = intent.getStringExtra("email");

        proName = findViewById(R.id.USERNAME);
        proEmail = findViewById(R.id.USEREMAIL);
        proPhone = findViewById(R.id.USERPHONE);
        proAddress = findViewById(R.id.USERADDRESS);
        proEditBtn = findViewById(R.id.userEditprofile);
        changepassword = findViewById(R.id.PASSWORD_CHANGE);
        deleteAccount= findViewById(R.id.DELETE_ACCOUNT);
        LOGOUT = findViewById(R.id.logout);
        btnHome = findViewById(R.id.HomeBtn);

        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        DatabaseReference userRef = reference.child(auth.getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                regClass1 RegClass = snapshot.getValue(regClass1.class);
                proName.setText(RegClass.getName());
                proEmail.setText(RegClass.getEmail());
                proPhone.setText(RegClass.getPhone());
                proAddress.setText(RegClass.getEAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, error.getCode(),Toast.LENGTH_SHORT).show();
            }

        });

        proEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Intent intent1 = new Intent(UserProfile.this,updateProfile.class);
              intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
              intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
              startActivity(intent1);
            }
        });

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(UserProfile.this,ChangeUserPassword.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
            }
        });

        LOGOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent3 = new Intent(UserProfile.this,Login_Activity.class);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent3);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent10 = new Intent(UserProfile.this,main_homepage.class);
                intent10.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent10.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent10);
            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(UserProfile.this);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this Account will result is completely removing your Account " +
                        "from the system and you won't be able to access the app.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(UserProfile.this, "Account Deleted...",Toast.LENGTH_SHORT).show();
                                    Intent intent4 = new Intent(UserProfile.this,Login_Activity.class);
                                    intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent4);
                                }else{
                                    Toast.makeText(UserProfile.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
    }
}