package com.example.usersfiles.moveapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by UsersFiles on 3/8/2016.
 */
public class MainData  implements Parcelable{
    public String getMove_title() {
        return move_title;
    }

    public void setMove_title(String move_title) {
        this.move_title = move_title;
    }



    public String getMove_Date() {
        return move_Date;
    }

    public void setMove_Date(String move_Date) {
        this.move_Date = move_Date;
    }

    String move_title,move_Date;

    public String getMove_image() {
        return move_image;
    }

    public void setMove_image(String move_image) {
        this.move_image = move_image;
    }

    String move_image;

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    String Review  ;

    public int getMove_id() {
        return move_id;
    }

    public void setMove_id(int move_id) {
        this.move_id = move_id;
    }

    int move_id;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    String backdrop_path ;

    public String getRate() {
        return rate;
    }

    String rate;

    public MainData(int move_id,String move_title,String move_Date,String move_image
            , String Review,String backdrop_path,String rate){
        this.move_title=move_title;
        this.move_image=move_image;
        this.move_Date=move_Date;
        this.Review=Review;
        this.move_id=move_id;
        this.backdrop_path=backdrop_path;
        this.rate=rate;
    }


    public MainData(Parcel in) {

        this.move_id =in.readInt();
        this.move_title=in.readString();
        this.move_Date=in.readString();
        this.move_image=in.readString();
        this.Review=in.readString();
        this.backdrop_path=in.readString();
        this.rate=in.readString();

     }





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

         parcel.writeInt(this.move_id);
        parcel.writeString( this.move_title);
        parcel.writeString(this.move_Date);
        parcel.writeString(this.move_image);
        parcel.writeString(this.Review);
        parcel.writeString(this.backdrop_path);
         parcel.writeString(this.rate);

    }
    public static final Parcelable.Creator<MainData> CREATOR
            = new Parcelable.Creator<MainData>() {
        public MainData createFromParcel(Parcel in) {
            return new MainData(in);
        }

        public MainData[] newArray(int size) {
            return new MainData[size];
        }
    };
}
