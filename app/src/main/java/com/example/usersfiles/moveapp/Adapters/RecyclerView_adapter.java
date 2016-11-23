package com.example.usersfiles.moveapp.Adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usersfiles.moveapp.Activties.Main2Activity;
import com.example.usersfiles.moveapp.Activties.MainActivity;
import com.example.usersfiles.moveapp.Models.MainData;
import com.example.usersfiles.moveapp.Models.favouriteMove;
import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.animation.animation;
import com.example.usersfiles.moveapp.communication.communication;
import com.example.usersfiles.moveapp.fragments.mainfragment;
import com.example.usersfiles.moveapp.fragments.movedeatials;
import com.example.usersfiles.moveapp.sharedPreference.sharedPreferenceFavourte;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UsersFiles on 6/8/2016.
 */
public class RecyclerView_adapter extends RecyclerView.Adapter<RecyclerView_adapter.viewholder> {
    sharedPreferenceFavourte shared_PreferenceFavourte_;
    ArrayList<MainData> arrayList = new ArrayList<>();
    Context con;
    LayoutInflater inflater;
    int scroll = 0;
    final String Path = "http://image.tmdb.org/t/p/w185";
    public RecyclerView_adapter(Context cons, ArrayList<MainData> data) {
        this.con = cons;

        arrayList = data;
        inflater = LayoutInflater.from(cons);
        shared_PreferenceFavourte_=new sharedPreferenceFavourte(cons);
    }
    @Override
    public RecyclerView_adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View views = inflater.inflate(R.layout.row_list, parent, false);
        viewholder holder = new viewholder(views, con, arrayList);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView_adapter.viewholder holder, final int position) {
        holder.title.setText(arrayList.get(position).getMove_title());
        holder.datetxt.setText(arrayList.get(position).getMove_Date());

      ArrayList<favouriteMove>  favouriteMoves= shared_PreferenceFavourte_.favouriteMove_list();
         for (int i=0;i<favouriteMoves.size();i++){
            if(Integer.parseInt(favouriteMoves.get(i).getId())==arrayList.get(position).getMove_id()){
                shared_PreferenceFavourte_.remvoe(favouriteMoves.get(i).getId());
                holder.checkBox.setChecked(true);
            }
         }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    shared_PreferenceFavourte_.add(arrayList.get(position).getMove_title(),
                            String.valueOf(arrayList.get(position).getMove_id() ));
                    sharedPreferenceFavourte sh=new sharedPreferenceFavourte(con );
                     ArrayList<favouriteMove> arrayList=sh.favouriteMove_list();
                    String s="";
                    for (int i=0;i<arrayList.size();i++){
                        s+=arrayList.get(i).getKey_title()+"    "+arrayList.get(i).getId()+"\n";
                    }

                }
                else
                {
                    shared_PreferenceFavourte_.remvoe(arrayList.get(position).getMove_title());
                 }
            }
        });

        final ConnectivityManager connMgr = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            Picasso.with(con).load(Path + arrayList.get(position).getMove_image()).into(holder.img);
         }
        else{
             holder.img.setImageDrawable(ContextCompat.getDrawable(con, R.drawable.no_image));

        }



        animation animate = new animation();
        if (scroll < position) {
            animate.animate(holder, true);
            animate.animateimage(holder.img, true);
        } else {
            animate.animate(holder, false);
            animate.animateimage(holder.img, false);

        }


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, datetxt;
        ImageView img;
        CheckBox checkBox ;

        Context con;
        ArrayList<MainData> arraydata;
        communication comm;

        public viewholder(View view, Context con, ArrayList<MainData> arraydata) {
            super(view);
            this.con = con;
            this.arraydata = arraydata;
            view.setOnClickListener(this);
            title = (TextView) view.findViewById(R.id.txttitle);
            datetxt = (TextView) view.findViewById(R.id.txtdate);
            img = (ImageView) view.findViewById(R.id.imageView);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);


            comm=(communication)con;
        }
        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public void onClick(View view) {

            int postion = getAdapterPosition();
            MainData data = this.arraydata.get(postion);
               comm.pass_id(data.getMove_id(),data.getMove_title(), data.getMove_Date(),
              data.getReview(), data.getMove_image(),data.getRate(),
           data.getBackdrop_path()  );







          //
          //  movedeatials movedeatialsfragment=new movedeatials();
        // FragmentManager    manger=((Activity)con).getFragmentManager();
           //  FragmentTransaction transaction=manger.beginTransaction();
           // transaction.replace(R.id.fragment,movedeatialsfragment,"detailsfragment");
           // transaction.commit();
        }
    }

}
