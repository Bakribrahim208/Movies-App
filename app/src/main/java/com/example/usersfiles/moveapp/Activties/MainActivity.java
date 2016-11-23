package com.example.usersfiles.moveapp.Activties;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.communication.communication;
import com.example.usersfiles.moveapp.fragments.movedeatials;

public class MainActivity extends AppCompatActivity  implements communication {
      @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void pass_id(int id, String titel, String date, String review, String posterimg, String vote, String backdrop_path) {
        FragmentManager maanger=getFragmentManager();
        movedeatials frag=new movedeatials();
        frag= (movedeatials) maanger.findFragmentById(R.id.fragment6);
        if(frag!=null){
            if(frag.isVisible()){
                 frag.changetextviews(id,titel,date,review,posterimg,backdrop_path,vote) ;
            }
        }
        else
        {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main2Activity.class);
               intent.putExtra("id",id);
               intent.putExtra("titel",titel );
               intent.putExtra("date", date );
               intent.putExtra("review", review );
               intent.putExtra("posterimg",posterimg );
               intent.putExtra("vote", vote);
               intent.putExtra("backdrop_path",backdrop_path );
               this.startActivity(intent);
        }
    }
}
