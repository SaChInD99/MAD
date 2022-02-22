package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class main_homepage extends AppCompatActivity {

    ViewFlipper view_flipper;
    Button btnRooms,btnGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_homepage);

        btnRooms = findViewById(R.id.btnrooms);
        btnGuide = findViewById(R.id.btnguide);

        view_flipper = findViewById(R.id.v_flipper);
        int images[] = {R.drawable.guidebook,R.drawable.hotelbook};
        for(int image:images){
            flipImages(image);
        }
    }

    public void flipImages(int image){
        ImageView imgview = new ImageView(this);
        imgview.setBackgroundResource(image);

        view_flipper.addView(imgview);
        view_flipper.setFlipInterval(4000);
        view_flipper.setAutoStart(true);

        view_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        view_flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_homepage.this,retrieverooms.class);
                startActivity(intent);
            }
        });

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(main_homepage.this,retrieveguides.class);
                startActivity(intent);
            }
        });
    }
}