package com.example.usersfiles.moveapp.Activties;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usersfiles.moveapp.R;
import com.example.usersfiles.moveapp.fragments.movedeatials;

public class Main2Activity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent=getIntent();

        movedeatials b= (movedeatials) getFragmentManager().findFragmentById(R.id.fragment2);
       b.changetextviews(intent.getIntExtra("id",278),intent.getStringExtra("titel"),
               intent.getStringExtra("date"),intent.getStringExtra("review")
               ,intent.getStringExtra("backdrop_path"),intent.getStringExtra("posterimg")
               ,intent.getStringExtra("vote"));
    }
}
