package com.example.usersfiles.moveapp.Models;

/**
 * Created by UsersFiles on 18/8/2016.
 */
public class favouriteMove {

    private  String id;

    public String getKey_title() {
        return Key_title;
    }

    public String getId() {
        return id;
    }

    private String Key_title;
    public  favouriteMove(String titlekey,String id){
        this.id=id;
        this.Key_title=titlekey;

    }
}
