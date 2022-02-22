package com.example.project;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class Module extends Application {

    public ArrayList <String>  currList = new ArrayList<>();
    public ArrayAdapter <String> curradap;

    public String getRoomID;

    public String getGetRoomID() {
        return getRoomID;
    }

    public void setGetRoomID(String getRoomID) {
        this.getRoomID = getRoomID;
    }
}
