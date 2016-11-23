package com.example.usersfiles.moveapp.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usersfiles.moveapp.Adapters.ImageAdapter;
import com.example.usersfiles.moveapp.Adapters.RecyclerView_adapter;
import com.example.usersfiles.moveapp.Adapters.Review_adapter;
import com.example.usersfiles.moveapp.JSON.Parsing_Json;
import com.example.usersfiles.moveapp.JSON.pasing_video;
import com.example.usersfiles.moveapp.Models.ReviewData;
import com.example.usersfiles.moveapp.Network.DownloadWebpageTask;
import com.example.usersfiles.moveapp.Network.updateUI;
import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.links.Links_class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UsersFiles on 8/8/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class movedeatials extends Fragment implements View.OnClickListener{
     ImageView img,posterimgae;
    TextView title,date,review,vote;
    Button reviewbtn;
    RecyclerView recyclerView,ImageRecycle;
    Review_adapter adapter;
    static int id=0;
    static String img_path,img_path_poster,img_path1,img_path_poster1;

    String url;
    public static final String TOP_RATED_MOVIES = "http://api.themoviedb.org/3/movie/278/reviews?api_key=777660159186d81259c9dcfa910ad0f1";
    static  final  String Detail_Key="Detail_Key",String_Array="String_Array";
    ArrayList<ReviewData> Review_list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.movedetailsfragment,container,false);
        img=(ImageView)view.findViewById(R.id.imageView2);
        posterimgae=(ImageView)view.findViewById(R.id.posterimg);
        title=(TextView)view.findViewById(R.id.txtilte);
        date=(TextView)view.findViewById(R.id.txtdate);
        review=(TextView)view.findViewById(R.id.txtreview);
        vote=(TextView)view.findViewById(R.id.votetxt);
        reviewbtn=(Button)view.findViewById(R.id.reviewbtn);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        ImageRecycle=(RecyclerView)view.findViewById(R.id.imagerecycle);
           reviewbtn.setOnClickListener(this);
        if(savedInstanceState!=null){
            Review_list=savedInstanceState.getParcelableArrayList(Detail_Key);
            adapter = new Review_adapter(Review_list ,getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter.notifyDataSetChanged();
            String[] arr=savedInstanceState.getStringArray(String_Array);

            changetextviews(id,arr[0],arr[1],arr[2],img_path1,img_path_poster1,arr[3]);
        }

        return  view;
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
    public void changetextviews(int Id, String Title, String Date, String Review, String backdrop_path
            , String posterimg, String vote)
    {
        title.setText(Title );
         date.setText(Date );
        review.setText( "Overview :\n"+Review );
        this.vote.setText(" "+vote);
        img_path1=backdrop_path;
        img_path_poster1=posterimg;
                img_path=new Links_class().getGetImgae()+backdrop_path;
        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            Picasso.with(getActivity()).load(img_path).into(img);
            img_path_poster=new Links_class().getGetImgae()+posterimg;
            Picasso.with(getActivity()).load(img_path_poster).into(posterimgae);
             this.id=Id;
              reviewbtn.callOnClick();
            reviewbtn.setVisibility(View.VISIBLE);

              url= new Links_class().getWebsite()+String.valueOf(Id)+new Links_class().getGetvideo();
            DownloadWebpageTask down = new DownloadWebpageTask(getActivity());
            down.execute(url);
            down.setterui(new updateUI() {
                @Override
                public void reciveddata(String data) {
                    ImageAdapter   adapter = new ImageAdapter(getActivity(),new pasing_video().vidoeurls(data));
                    ImageRecycle.setAdapter(adapter);
                    LinearLayoutManager liner = new LinearLayoutManager(getActivity());
                    liner.setOrientation(LinearLayoutManager.HORIZONTAL);
                    ImageRecycle.setLayoutManager(liner );
                    adapter.notifyDataSetChanged();
                }
            });
         }
        else{
            Toast.makeText(getActivity(), "no internet", Toast.LENGTH_SHORT).show();
            img.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.im1));
            posterimgae.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.im1));
        }


    }
    //get Reviews when click on show Review Button
    @Override
    public void onClick(View view) {
        final ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            DownloadWebpageTask down = new DownloadWebpageTask(getActivity());
            String url= new Links_class().getWebsite()+String.valueOf(id)+new Links_class().getReview();
            down.execute(url);
            down.setterui(new updateUI() {
                @Override
                public void reciveddata(String data) {
                      Review_list =new Parsing_Json("").GetReview(data);
                    if(Review_list.size()>=1){
                        adapter = new Review_adapter(Review_list ,getActivity());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        adapter.notifyDataSetChanged();
                    }

                }
            });

        }
        else{
            Toast.makeText(getActivity(),"you ara offline",Toast.LENGTH_LONG).show();

        }



    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Detail_Key,Review_list);
       // .setText(Title );
        //date.setText(Date );
        //.setText( "Overview :\n"+Review );
        //this.vote.setText(" "+vote);
        outState.putStringArray(String_Array,new String[]{
                title.getText().toString(),
                date.getText().toString(),
                review.getText().toString(),
                vote.getText().toString(),
        });
     }
}
