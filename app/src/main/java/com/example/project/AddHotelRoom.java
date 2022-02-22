package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class AddHotelRoom extends AppCompatActivity {

    ViewFlipper view_flipper;
    CheckBox chk1,chk2,chk3,chk4,chk5;
    EditText price,descrip,locat;
    Button add,img,logout;
    DatabaseReference dbref;
    RoomModel room;
    int maxvalue = 001;
    ImageView imageView;

    private Uri filepath;
    private final int PICK_IMAGE_REQUEST = 71;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    public void clearFields(){
        price.setText("");
        descrip.setText("");
        locat.setText("");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hotel_room);

        view_flipper = findViewById(R.id.v_flipper);
        int images[] = {R.drawable.ht1,R.drawable.ht2,R.drawable.ht3};
        for(int image:images){
            flipImages(image);
        }
        add = findViewById(R.id.add);
        imageView = findViewById(R.id.img);
        img = findViewById(R.id.imgbutton);
        logout = findViewById(R.id.button10);
        /*String Value Inputs*/
        price = findViewById(R.id.txt1);
        descrip = findViewById(R.id.txt2);
        locat = findViewById(R.id.txt3);

        /*CheckBox Inputs*/
        chk1 = (CheckBox)findViewById(R.id.checkBox);
        chk2 = (CheckBox)findViewById(R.id.checkBox2);
        chk3 = (CheckBox)findViewById(R.id.checkBox3);
        chk4 = (CheckBox)findViewById(R.id.checkBox4);
        chk5 = (CheckBox)findViewById(R.id.checkBox5);

        //j = i;
        room = new RoomModel();

        dbref = FirebaseDatabase.getInstance().getReference().child("Rooms");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxvalue = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref = FirebaseDatabase.getInstance().getReference().child("Rooms");
                    try {
                        if (TextUtils.isEmpty(price.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Enter the price", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(locat.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Enter the Location", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(descrip.getText().toString())) {
                            Toast.makeText(getApplicationContext(), "Enter the description", Toast.LENGTH_SHORT).show();
                        } else if(imageView.getDrawable() == null){
                            Toast.makeText(getApplicationContext(), "Please Add image of room", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //Getting Inputs from the check Box
                            String result = " ";
                            if (chk1.isChecked()) {
                                result += "Free Wifi ,";
                            }
                            if (chk2.isChecked()) {
                                result += "Television ,";
                            }
                            if (chk3.isChecked()) {
                                result += "Air Conditioned ,";
                            }
                            if (chk4.isChecked()) {
                                result += "Shower ,";
                            }
                            if (chk5.isChecked()) {
                                result += "Tea-Maker ,";
                            }
                            room.setId(maxvalue+1);
                            room.setFeatures(result.trim());
                            room.setPrice(price.getText().toString().trim());
                            room.setLocat(locat.getText().toString().trim());
                            room.setDescrip(descrip.getText().toString().trim());
                            room.setImg(downloaduri.toString());

                            dbref.child(String.valueOf(maxvalue+1)).setValue(room);

                            Toast.makeText(getApplicationContext(), "Data added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AddHotelRoom.this,ManageRooms.class);
                            startActivity(intent);
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddHotelRoom.this,Login_Activity.class);
                startActivity(intent);
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
                imageView.setImageBitmap(bitmap);
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

        final String randomkey = room.getId()+UUID.randomUUID().toString();
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
                                //room.setImg(downloaduri.toString());
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