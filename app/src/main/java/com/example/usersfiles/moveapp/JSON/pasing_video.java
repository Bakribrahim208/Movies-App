package com.example.usersfiles.moveapp.JSON;

import com.example.usersfiles.moveapp.Models.MainData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by UsersFiles on 11/8/2016.
 */
public class pasing_video {
  final String json_array="results";

    final String json_key="key";


    public ArrayList<String> vidoeurls(String data){
        ArrayList<String>arrayList=new ArrayList<>();
            try {
                JSONObject mainobject=new JSONObject(data);

                JSONArray array =mainobject.getJSONArray(json_array);

                for (int i=0;i<array.length();i++){

                    JSONObject branchOject =array.getJSONObject(i);

                    arrayList.add(branchOject.getString(json_key));
                }
            }
            catch (Exception ex){

            }
            return  arrayList;
        }


}
