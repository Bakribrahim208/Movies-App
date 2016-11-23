package com.example.usersfiles.moveapp.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usersfiles.moveapp.Activties.MainActivity;
import com.example.usersfiles.moveapp.Adapters.RecyclerView_adapter;
import com.example.usersfiles.moveapp.DataBase.DbConnection;
import com.example.usersfiles.moveapp.JSON.Parsing_Json;
import com.example.usersfiles.moveapp.Models.MainData;
import com.example.usersfiles.moveapp.Models.favouriteMove;
import com.example.usersfiles.moveapp.Network.DownloadWebpageTask;
import com.example.usersfiles.moveapp.Network.updateUI;
import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.communication.communication;
import com.example.usersfiles.moveapp.links.Links_class;
import com.example.usersfiles.moveapp.sharedPreference.sharedPreferenceFavourte;

import java.util.ArrayList;

/**
 * Created by UsersFiles on 8/8/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class mainfragment extends Fragment     {
    public static String Top="Top_Rated",popular="Popular_move";
    RecyclerView Recycle;
    communication comm;
     RecyclerView_adapter recyclerViewAdapter;

  public  static   ArrayList<MainData> Moves_array=new ArrayList<>();

    public static final String Move_KEy="Move_Key";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mainfragment, container, false);
        Recycle = (RecyclerView) view.findViewById(R.id.recycle);
          if(savedInstanceState!=null){
        Moves_array = savedInstanceState.getParcelableArrayList(Move_KEy);
              recyclerViewAdapter = new RecyclerView_adapter(getActivity(), Moves_array);
               Recycle.setAdapter(recyclerViewAdapter);
               GridLayoutManager grid = new GridLayoutManager(getActivity(),3);
              Recycle.setLayoutManager(grid);
              recyclerViewAdapter.notifyDataSetChanged();
     Toast.makeText(getActivity(), "savedInstanceState +size array"+Moves_array.size()
             , Toast.LENGTH_SHORT).show();



          }
else{
     updateGU(   new Links_class().getGetTOpRatePath(),1);

 }
         return view;
    }
    public void updateGU(String url, final int type){

        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            DownloadWebpageTask down = new DownloadWebpageTask(getActivity());
            down.execute( url);
            down.setterui(new updateUI() {
                @Override
                public void reciveddata(String data) {

                    Toast.makeText(getActivity(), "get json from internet"
                            , Toast.LENGTH_SHORT).show();
                      // put data in recylview
                      Moves_array = new Parsing_Json(data).getListOfMoves();
                    recyclerViewAdapter = new RecyclerView_adapter(getActivity(), Moves_array);
                    Recycle.setAdapter(recyclerViewAdapter);
                    GridLayoutManager grid = new GridLayoutManager(getActivity(),3);
                    Recycle.setLayoutManager(grid);
                    recyclerViewAdapter.notifyDataSetChanged();

                    //when there is internet connection take the array of data and put it in
                    //database to use it when no internet connection
                     DbConnection database = new DbConnection(getActivity());
                       database.deletetable(type);

                         for (int i=0;i<Moves_array.size();i++){
                           database.insert_data(Moves_array.get(i).getMove_id(),
                                      Moves_array.get(i).getMove_title()
                                        ,Moves_array.get(i).getMove_Date()
                                       ,Moves_array.get(i).getRate(),
                                   Moves_array.get(i).getReview()
                                   , Moves_array.get(i).getMove_image()
                                   ,Moves_array.get(i).getBackdrop_path()
                                    ,type );

                     }
                  // Toast.makeText(getActivity(),String.valueOf(database.ALLRecords(type).size())
                     //     , Toast.LENGTH_SHORT).show();

                }});


        } else {
 Toast.makeText(getActivity(), "data base"
             , Toast.LENGTH_SHORT).show();
           DbConnection db=new DbConnection(getActivity());
            // Toast.makeText(getActivity(),"number of record"+String.valueOf(new sharedPreferenceFavourte(getActivity()).favouriteMove_list().size())
              //      , Toast.LENGTH_SHORT).show();
         //   Toast.makeText(getActivity(),"number of record"
                 //   , Toast.LENGTH_SHORT).show();
            Moves_array=db.ALLRecords(type);
             recyclerViewAdapter = new RecyclerView_adapter(getActivity(),Moves_array);
            GridLayoutManager grid = new GridLayoutManager(getActivity(),3);
            Recycle.setLayoutManager(grid);
           Recycle.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }


    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.linearViewHorizontal:
                LinearLayoutManager liner = new LinearLayoutManager(getActivity());
                liner.setOrientation(LinearLayoutManager.HORIZONTAL);
                Recycle.setLayoutManager(liner);
                break;
            case R.id.linearViewVertical:
                LinearLayoutManager hor = new LinearLayoutManager(getActivity());
                hor.setOrientation(LinearLayoutManager.VERTICAL);
                Recycle.setLayoutManager(hor);
                break;
            case R.id.gridView:
                GridLayoutManager grid = new GridLayoutManager(getActivity(),3);
                Recycle.setLayoutManager(grid);
                break;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager sthor = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
                Recycle.setLayoutManager(sthor);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager vthor = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                Recycle.setLayoutManager(vthor);
                break;
            case R.id.toprate:
            updateGU(  new Links_class().getGetTOpRatePath(),1);

                break;
            case R.id.popular:
                updateGU( new Links_class().getPopularMovePath(),2);

                break;
            case R.id.Favourt:
                DbConnection db=new DbConnection(getActivity());

                recyclerViewAdapter = new RecyclerView_adapter(getActivity(),db.ALLRecords_id(new sharedPreferenceFavourte(getActivity()).favouriteMove_list()));
                 GridLayoutManager gride = new GridLayoutManager(getActivity(),3);
                Recycle.setLayoutManager(gride);
                Recycle.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();


                break;
            case R.id.Refresh:

                updateGU(  new Links_class().getGetTOpRatePath(),1);


                break;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Move_KEy,Moves_array);

    }
}





//Read tables and colulmns from database
// Toast.makeText(getActivity(),String.valueOf( database.ALLRecords().size()),Toast.LENGTH_LONG).show();
// SQLiteDatabase da=database.getReadableDatabase();
// Cursor c = da.rawQuery(" PRAGMA table_info( Move_data );", null);

//      if(c.moveToFirst()) {
//     while ( !c.isAfterLast() ) {
//  Toast.makeText(getActivity(), "Table Name=> "+c.getString(1), Toast.LENGTH_LONG).show();
//      c.moveToNext();
//  }
//  }



//database.insert_data(2,"bakr1"
// ,"bakr1","bakr1","bakr1");