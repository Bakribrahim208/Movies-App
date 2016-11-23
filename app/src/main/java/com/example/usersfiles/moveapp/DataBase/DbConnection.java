package com.example.usersfiles.moveapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import com.example.usersfiles.moveapp.Models.MainData;
import com.example.usersfiles.moveapp.Models.favouriteMove;
import com.example.usersfiles.moveapp.sharedPreference.sharedPreferenceFavourte;

import java.util.ArrayList;

/**
 * Created by UsersFiles on 5/8/2016.
 */
public class DbConnection extends SQLiteOpenHelper {

    public static final String name = "move.db";
    public static final int version = 1;


    public DbConnection(Context con) {
        super(con, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQl_MoveTable =
                "CREATE TABLE " + Move_data.TABLE_NAME +
                        "(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
                        Move_data.COLUMN_ID + "  INTEGER," +
                        Move_data.COLUMN_Title + " TEXT," +
                        Move_data.COLUMN_Date + "  TEXT, " +
                        Move_data.COLUMN_Rate + "  TEXT," +
                          Move_data.COLUMN_moveimgae + "  TEXT," +
                        Move_data.COLUMN_backdrop_path + "  TEXT," +
                        Move_data.COLUMN_overview + "  TEXT)";
        /*final String SQL_Review_Table="CREATE TABLE "+Move_data.Table_review+"( id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"+
               Move_data.Review_owner+"  TEXT,"+
                Move_data.Review_Review+"  TEXT,"+
                Move_data.Review_url+"  TEXT,"+
                " FOREIGN KEY("+  Move_data.Move_id+  ") REFERENCES  "+Move_data.TABLE_NAME+
                " (  ID_move) "+")";*/

             final String SQl_MoveTable2 =
                    "CREATE TABLE " + Move_data.TABLE_NAME_pupular +
                            "(id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
                            Move_data.COLUMN_ID + "  INTEGER," +
                            Move_data.COLUMN_Title + " TEXT," +
                            Move_data.COLUMN_Date + "  TEXT, " +
                            Move_data.COLUMN_Rate + "  TEXT,"
                            + Move_data.COLUMN_moveimgae + "  TEXT," +
                            Move_data.COLUMN_backdrop_path + "  TEXT," +
                            Move_data.COLUMN_overview + "  TEXT)";
        sqLiteDatabase.execSQL(SQl_MoveTable);
        sqLiteDatabase.execSQL(SQl_MoveTable2);

        // sqLiteDatabase.execSQL(SQL_Review_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // sqLiteDatabase.execSQL("Drop TABLE if   exists"+Move_data.TABLE_NAME);
        // onCreate(sqLiteDatabase);

    }

    public void deletetable( int i  ) {
        if(i==1){
            SQLiteDatabase dp = this.getWritableDatabase();
            dp.delete(Move_data.TABLE_NAME,null,null);
            dp.close();
        }
        else {
            SQLiteDatabase dp = this.getWritableDatabase();
            dp.delete(Move_data.TABLE_NAME_pupular,null,null);
            dp.close();
        }


    }

    public void insert_data(int ID,
                            String Title,
                            String Move_Date,
                            String Rate,
                            String Move_overview
            ,String moveimage
            ,String backdrop_path ,int type) {
        SQLiteDatabase dp = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(Move_data.COLUMN_ID, ID);
        data.put(Move_data.COLUMN_Title, Title);
        data.put(Move_data.COLUMN_Date, Move_Date);
        data.put(Move_data.COLUMN_Rate, Rate);
        data.put(Move_data.COLUMN_moveimgae, moveimage);
        data.put(Move_data.COLUMN_backdrop_path, backdrop_path);
        data.put(Move_data.COLUMN_overview, Move_overview);
        if(type==1)

        dp.insert(Move_data.TABLE_NAME, null, data);
        else
            dp.insert(Move_data.TABLE_NAME_pupular, null, data);


    }

    //using this fucntion to get   movies when no internet connection
    public ArrayList<MainData> ALLRecords(int type ) {

        ArrayList<MainData> Movees = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String table;
        if(type==1)
          table="select * from "+Move_data.TABLE_NAME;
        else
            table="select * from "+Move_data.TABLE_NAME_pupular;

        Cursor dataset = db.rawQuery(table, null);
        dataset.moveToFirst();
        while (dataset.isAfterLast() == false) {
            //dataset.getString(dataset.getColumnIndex("move_title")
            MainData entry = new MainData(dataset.getInt(dataset.getColumnIndex(Move_data.COLUMN_ID)),
                    dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Title)),
                    dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Date)),
                    dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_moveimgae)),
                    dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Date)),
                    dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_backdrop_path)),
                    dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Rate)));
            Movees.add(entry);
            dataset.moveToNext();
        }
        return Movees;


    }




//using this function to get favourite movies
    public ArrayList<MainData> ALLRecords_id(ArrayList<favouriteMove> moveis) {

        ArrayList<MainData> Movees = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String where_str="";
        for ( int i=0;i<moveis.size();i++)
        {
            Cursor dataset = db.rawQuery("select * from Top_Rated WHERE "+ Move_data.COLUMN_ID+"="+moveis.get(i).getId(), null);

            dataset.moveToFirst();
            while (dataset.isAfterLast() == false) {
                //dataset.getString(dataset.getColumnIndex("move_title")
                MainData entry = new MainData(dataset.getInt(dataset.getColumnIndex(Move_data.COLUMN_ID)),
                        dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Title)),
                        dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Date)),
                        dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_moveimgae)),
                        dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Date)),
                        dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_backdrop_path)),
                        dataset.getString(dataset.getColumnIndex(Move_data.COLUMN_Rate)));
                Movees.add(entry);
                dataset.moveToNext();
            }



        }
        return Movees;
    }
    public static final class Move_data implements BaseColumns {

        public static final String TABLE_NAME = "Top_Rated";
        public static final String COLUMN_ID = "ID_move";
        public static final String COLUMN_Title = "Title";
        public static final String COLUMN_Date = "Date";
        public static final String COLUMN_Rate = "Rate";
        public static final String COLUMN_overview = "overview";
        public static final String COLUMN_moveimgae= "Move_image";
        public static final String COLUMN_backdrop_path= "backdrop_path";


        public static final String TABLE_NAME_pupular = "pupular";

      /*  public static final  String Table_review="ReviewTable";
        public static final String Review_id="id";
        public static final String Review_owner="owner";
        public static final String Review_Review="Review";
        public static final String Review_url ="URl";
        public static final String Move_id="ID_move"; */

    }


}
