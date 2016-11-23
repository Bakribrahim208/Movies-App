package com.example.usersfiles.moveapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usersfiles.moveapp.Models.MainData;
import com.example.usersfiles.moveapp.Models.ReviewData;
import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.links.Links_class;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by UsersFiles on 8/8/2016.
 */
public class Review_adapter extends RecyclerView.Adapter<Review_adapter.viewholder> {

    ArrayList<ReviewData> arraydata;
    LayoutInflater inflater;
    Context context;

    public Review_adapter(ArrayList<ReviewData> arrayList, Context con) {
        this.arraydata = arrayList;
        this.context = con;
        inflater = LayoutInflater.from(con);
    }

    @Override
    public Review_adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_review, parent, false);
        viewholder holder = new viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Review_adapter.viewholder holder, int position) {

        holder.author.setText(arraydata.get(position).getAuthor());
        holder.utltxt.setText(arraydata.get(position).getUrltxt());
        holder.review.setText(arraydata.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return arraydata.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView author, utltxt, review;


        public viewholder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.txtauthor);
            utltxt = (TextView) itemView.findViewById(R.id.txtUrl);
            // if (utltxt != null) {
            //     utltxt.setMovementMethod(LinkMovementMethod.getInstance());
            // }
            review = (TextView) itemView.findViewById(R.id.txtReview);


        }
    }
}
