package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class retrieveguides extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieveguides);

        listView = findViewById(R.id.listView);

        Query query = FirebaseDatabase.getInstance().getReference().child("Guids");
        FirebaseListOptions<model> options = new FirebaseListOptions.Builder<model>()
                .setLayout(R.layout.guide_info)
                .setLifecycleOwner(retrieveguides.this)
                .setQuery(query,model.class)
                .build();


        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView name = v.findViewById(R.id.name);
                TextView dob = v.findViewById(R.id.dob);
                TextView nic = v.findViewById(R.id.nic);
                TextView address = v.findViewById(R.id.address);
                TextView contact = v.findViewById(R.id.contact);

                model guide = (model) model;
                name.setText("Guide Name : "+guide.getName().toString());
                dob.setText("Date of Birth :"+guide.getDate().toString());
                nic.setText("NIC Number : "+guide.getNic().toString());
                address.setText("Address : "+guide.getAddress().toString());
                contact.setText("Contact number : "+guide.getCnumber().toString());
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                model gd = (model) adapterView.getItemAtPosition(position);
                String name = gd.getName();
                String address = gd.getAddress();
                String contact = gd.getCnumber();
                //Pass to the payment Activity intent

                Intent intent = new Intent(retrieveguides.this,billguide.class);
                intent.putExtra("guideName",name);
                intent.putExtra("guideAddress",address);
                intent.putExtra("guideContact",contact);
                startActivity(intent);
                Toast.makeText(retrieveguides.this, "Your selected Guide Name is "+name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}