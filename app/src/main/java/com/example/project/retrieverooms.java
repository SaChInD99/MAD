package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class retrieverooms extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieverooms);

        listView = findViewById(R.id.listView);

        Query query = FirebaseDatabase.getInstance().getReference().child("Rooms");
        FirebaseListOptions<RoomModel> options = new FirebaseListOptions.Builder<RoomModel>()
                .setLayout(R.layout.room_info)
                .setLifecycleOwner(retrieverooms.this)
                .setQuery(query,RoomModel.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView id = v.findViewById(R.id.roomID);
                TextView features = v.findViewById(R.id.features);
                TextView price = v.findViewById(R.id.price);
                TextView location = v.findViewById(R.id.location);
                TextView descrip = v.findViewById(R.id.descrip);
                ImageView imageView = v.findViewById(R.id.imgview);

                RoomModel room = (RoomModel) model;
                id.setText("Room ID : "+room.getId());
                features.setText("Room Features :"+room.getFeatures().toString());
                price.setText("Room Price : "+room.getPrice().toString());
                location.setText("Room Location : "+room.getLocat());
                descrip.setText("Room Description : "+room.getDescrip());
                Picasso.with(retrieverooms.this).load(room.getImg().toString()).into(imageView);
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                RoomModel rm = (RoomModel) adapterView.getItemAtPosition(position);
                int ID = rm.getId();
                String price = rm.getPrice();
                String location = rm.getLocat();
                //Pass to the payment Activity intent

                Intent intent = new Intent(retrieverooms.this,Bill.class);
                intent.putExtra("roomID",String.valueOf(ID));
                intent.putExtra("roomlocation",location);
                intent.putExtra("roomPrice",price);
                startActivity(intent);
                Toast.makeText(retrieverooms.this, "Your selected room ID is"+ID, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}