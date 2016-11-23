package com.example.usersfiles.moveapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.links.Links_class;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by UsersFiles on 11/8/2016.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.viewholder> {
   ArrayList<String> urlarray;
    Context context;
LayoutInflater inflater;
   public  ImageAdapter (Context con,ArrayList<String>arrayList){
       urlarray =new ArrayList<>();
       urlarray=arrayList;
       context=con;
       inflater=LayoutInflater.from(context);
   }
    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
View view =inflater.inflate(R.layout.youtubeimage,parent,false);
        viewholder holder=new viewholder(view,urlarray,context);

        return holder;
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        holder.img.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.imagea));

            String   vidioimag="http://img.youtube.com/vi/"+urlarray.get(position).toString()+"/3.jpg";
            Picasso.with(context).load(vidioimag).into(holder.img);




    }

    @Override
    public int getItemCount() {
        return urlarray.size();
    }



    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
ImageView img;
ArrayList<String> urls;
        Context context;
        public viewholder(View itemView,ArrayList<String>arrayList,Context con) {
            super(itemView);
            urls=arrayList;
            context=con;
            itemView.setOnClickListener(this);

            img=(ImageView)itemView.findViewById(R.id.imageView4);



        }

        @Override
        public void onClick(View view) {
             int postion =getAdapterPosition();
             Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(new Links_class().getYoutube()+urls.get(postion)));
              context.startActivity(intent);

        }
    }


}
