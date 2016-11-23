package com.example.usersfiles.moveapp.JSON;

import com.example.usersfiles.moveapp.Models.MainData;
import com.example.usersfiles.moveapp.Models.ReviewData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by UsersFiles on 4/8/2016.
 */
public class Parsing_Json
{
    String Json="";
    final  static String json_array="results";
    final static String Json_date="release_date";
    final static String Json_title="original_title";
    final static String Json_overview="overview";
    final static String Json_imgPath="poster_path";
    final static String Json_author="author";
    final static String Json_url="content";
    final static String Json_content="url";
    final static String Json_Id="id";
    final static String Json_vote="vote_average";

    final static String Json_backdrop_path="backdrop_path";


    ArrayList<MainData>arrayList;
    public Parsing_Json(String JSonObject)
    {
        this.Json+=JSonObject;
        arrayList=new ArrayList<>();
    }
    public ArrayList<MainData> getListOfMoves(){
     try {
    JSONObject mainobject=new JSONObject(Json);

    JSONArray  array =mainobject.getJSONArray(json_array);

     for (int i=0;i<array.length();i++){

         JSONObject branchOject =array.getJSONObject(i);

         arrayList.add(new MainData(
                 branchOject.getInt(Json_Id) ,
                 branchOject.getString(Json_title),
                 branchOject.getString(Json_date) ,
                branchOject.getString(Json_imgPath),
                 branchOject.getString(Json_overview)
                 ,branchOject.getString(Json_backdrop_path)
                 ,branchOject.getString(Json_vote))
        );
      }
}
catch (Exception ex){

}
        return  arrayList;
    }

    public ArrayList<ReviewData>GetReview(String data) {
        ArrayList<ReviewData> arr = new ArrayList<>();
       try {

            JSONObject mainobject = new JSONObject(data);

            JSONArray array1 = mainobject.getJSONArray(json_array);

            for (int i = 0; i < array1.length(); i++) {

                JSONObject branchOject = array1.getJSONObject(i);
                arr.add(new ReviewData(branchOject.getString(Json_author),
                        branchOject.getString(Json_url),
                        branchOject.getString(Json_content)));

            }
           return  arr;
        } catch (Exception ex) {
          return  null;
        }

    }
}
