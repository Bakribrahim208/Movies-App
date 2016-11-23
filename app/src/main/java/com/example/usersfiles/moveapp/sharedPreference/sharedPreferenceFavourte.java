package com.example.usersfiles.moveapp.sharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.usersfiles.moveapp.Models.favouriteMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by UsersFiles on 18/8/2016.
 */
public class sharedPreferenceFavourte {

    String Preference_name = "favourite_Preference";
    SharedPreferences sharedPreference;

    public sharedPreferenceFavourte(Context context) {
        sharedPreference = context.getSharedPreferences(Preference_name, Context.MODE_PRIVATE);

    }


    public  void add(String key,String value){
         SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public void remvoe(String key){
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.remove(key);
        editor.apply();
    }
    public ArrayList<favouriteMove> favouriteMove_list() {

        Map<String, ?> favouriteMoveMap = sharedPreference.getAll();

        SortedSet<String> keys = new TreeSet<String>(favouriteMoveMap.keySet());

        ArrayList<favouriteMove> favouriteMovelist = new ArrayList<>();
        for (String key : keys) {
            favouriteMove row = new favouriteMove(key,(String) favouriteMoveMap.get(key));
            favouriteMovelist.add(row);
        }

        return favouriteMovelist;
    }
}
