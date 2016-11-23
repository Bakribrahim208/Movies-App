package com.example.usersfiles.moveapp.Activties;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.usersfiles.moveapp.R;

/**
 * Created by UsersFiles on 10/8/2016.
 */
public class SplashScreen extends Activity {

 ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        img=(ImageView)findViewById(R.id.imageView3);
        Animation anim= AnimationUtils.loadAnimation(getBaseContext(),R.anim.splashanimation);
        img.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent inten=new Intent(getBaseContext(),MainActivity.class);
                startActivity(inten);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
