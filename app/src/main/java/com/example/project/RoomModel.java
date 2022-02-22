package com.example.project;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

public class RoomModel {
    private int id;
    private String features, descrip, locat;
    private String price;
    private String img;

    public RoomModel() {
    }

    public RoomModel(int id, String features, String descrip, String locat, String price,String img) {
        this.id = id;
        this.features = features;
        this.descrip = descrip;
        this.locat = locat;
        this.price = price;
        this.img = img;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getLocat() {
        return locat;
    }

    public void setLocat(String locat) {
        this.locat = locat;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails(){
        String details = "Room ID : "+ this.getId() +"\n\n" +"2. Room Features : "+this.getFeatures() + "\n\n" +"3. Room Location : "+ this.getLocat() + "\n\n" + "4. Room Price : "+ this.getPrice() + "\n\n" +"5. Room Description : "+ this.getDescrip() + "\n\n";
        return details;
    }

    /*load the image
    private Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    private void load(){
        Picasso.get().load(this.getImg()).into(target);
    }*/

}