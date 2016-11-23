package com.example.usersfiles.moveapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by UsersFiles on 8/8/2016.
 */
public class ReviewData implements Parcelable {
    String author;

    protected ReviewData(Parcel in) {
        author = in.readString();
        urltxt = in.readString();
        review = in.readString();
    }

    public static final Creator<ReviewData> CREATOR = new Creator<ReviewData>() {
        @Override
        public ReviewData createFromParcel(Parcel in) {
            return new ReviewData(in);
        }

        @Override
        public ReviewData[] newArray(int size) {
            return new ReviewData[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUrltxt() {
        return urltxt;
    }

    public void setUrltxt(String urltxt) {
        this.urltxt = urltxt;
    }

    String urltxt;
    String review;

    public ReviewData(String aouthor,String urltxt ,String review){
        this.author=aouthor;
        this.urltxt=urltxt;
        this.review=review;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.author);
        parcel.writeString(this.urltxt);
        parcel.writeString(this.review);

    }
}
