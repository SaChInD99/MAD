package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.UUID;

public class EditRooms extends AppCompatActivity {

    public String ID;
    TextView txt1;
    EditText edPrice,edlocat,eddescip;
    CheckBox chk1,chk2,chk3,chk4,chk5;
    Button btn1,btn2,btn3;
    RoomModel roomModel;
    ImageView image;

    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public String imageurl;

    Module module;
    DatabaseReference db,readdf,updateref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rooms);

        db = FirebaseDatabase.getInstance().getReference().child("Rooms");
        Intent secIntent = getIntent();
        ID = secIntent.getStringExtra("RoomID");

        image = findViewById(R.id.imgNew);

        txt1 = findViewById(R.id.txt);
        btn1 = findViewById(R.id.btndelete);
        btn2 = findViewById(R.id.btnupdate);
        btn3 = findViewById(R.id.imageadd);
        edPrice = findViewById(R.id.txtPrice);
        edlocat = findViewById(R.id.txtLocat);
        eddescip = findViewById(R.id.txtDes);

        chk1 = findViewById(R.id.checkBox15);//Free wifi
        chk2 = findViewById(R.id.checkBox16);//Air conditioned
        chk3 = findViewById(R.id.checkBox17);//Television
        chk4 = findViewById(R.id.checkBox18);//Shower
        chk5 = findViewById(R.id.checkBox19);//Tea maker

        roomModel = new RoomModel();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        txt1.setText(ID);
        final int childID = Integer.valueOf(ID.toString().trim());
        module = (Module)getApplicationContext();

        btn1.setOnClickListener(new View.OnClickListener() {

            String rmID = ID.toString().trim();
            @Override
            public void onClick(View view) {
                db.child("Rooms").child(rmID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        db.child(rmID).removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                Toast.makeText(EditRooms.this, "Deleted Successfully!!!!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditRooms.this,ManageRooms.class);
                startActivity(intent);
            }
        });

        String roomID = ID.toString().trim();
        //Read data and set to fields
        readdf = FirebaseDatabase.getInstance().getReference().child("Rooms").child(roomID);
        readdf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String features = dataSnapshot.child("features").getValue().toString();

                if(features.contains("Free Wifi")){
                    chk1.setChecked(true);
                }if(features.contains("Air Conditioned")){
                    chk2.setChecked(true);
                }if(features.contains("Television")){
                    chk3.setChecked(true);
                }if(features.contains("Shower")){
                    chk4.setChecked(true);
                }if(features.contains("Tea-Maker")){
                    chk5.setChecked(true);
                }

                edPrice.setText(dataSnapshot.child("price").getValue().toString());
                edlocat.setText(dataSnapshot.child("locat").getValue().toString());
                eddescip.setText(dataSnapshot.child("descrip").getValue().toString());
                Picasso.with(EditRooms.this).load(dataSnapshot.child("img").getValue().toString()).into(image);

                imageurl = dataSnapshot.child("img").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Update the value
        btn2.setOnClickListener(new View.OnClickListener() {

            String rmID2 = ID.toString().trim();
            String result = " ";
            @Override
            public void onClick(View view) {
                try {
                    if (TextUtils.isEmpty(edPrice.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Enter the price", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(edlocat.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Enter the Location", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(eddescip.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Enter the description", Toast.LENGTH_SHORT).show();
                    } else {
                        //Getting Inputs from the check Box
                        //String result = " ";
                        if (chk1.isChecked()) {
                            result += "Free Wifi ,";
                        }
                        if (chk2.isChecked()) {
                            result += "Air Conditioned ,";
                        }
                        if (chk3.isChecked()) {
                            result += "Television ,";
                        }
                        if (chk4.isChecked()) {
                            result += "Shower ,";
                        }
                        if (chk5.isChecked()) {
                            result += "Tea-Maker ,";
                        }

                        db.child("Rooms").child(rmID2).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                db = FirebaseDatabase.getInstance().getReference();
                                db.child("Rooms").child(rmID2).child("id").setValue(childID);
                                db.child("Rooms").child(rmID2).child("features").setValue(result.trim());
                                db.child("Rooms").child(rmID2).child("price").setValue(edPrice.getText().toString().trim());
                                db.child("Rooms").child(rmID2).child("locat").setValue(edlocat.getText().toString().trim());
                                db.child("Rooms").child(rmID2).child("descrip").setValue(eddescip.getText().toString().trim());

                                if(downloaduri.toString().isEmpty()){
                                    db.child("Rooms").child(rmID2).child("img").setValue(imageurl);
                                }else{
                                    db.child("Rooms").child(rmID2).child("img").setValue(downloaduri.toString());
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Toast.makeText(EditRooms.this, "Data Updated Successfully!!!!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditRooms.this,ViewRooms.class);
                        startActivity(intent);
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong"+e, Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    //Choose Image
    public void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try{
                filepath = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                image.setImageBitmap(bitmap);
                uploadImage();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public Uri downloaduri;

    public void uploadImage(){

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading Image.....");
        pd.show();

        final String randomkey = roomModel.getId()+ UUID.randomUUID().toString();
        final StorageReference riversRef = storageReference.child("images/"+randomkey);

        riversRef.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        pd.dismiss();
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded",Snackbar.LENGTH_LONG).show();
                        riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                downloaduri = uri;
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        pd.dismiss();
                        Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progresspercent = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Percentage: "+ (int)progresspercent + "%" );
                    }
                });
    }
}
