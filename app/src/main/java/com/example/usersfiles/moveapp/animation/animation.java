package com.example.usersfiles.moveapp.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by UsersFiles on 6/8/2016.
 */
public class animation {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void animate(RecyclerView.ViewHolder holder, boolean scroll) {
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(holder.itemView, "translationY", scroll == true ? 1000 : -1000, 0);
        //ObjectAnimator animatorTranslateX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -70, 70, -50, 50, -30, 30, -10, 10, 0);
       // animatorTranslateX.setDuration(1000);
        animatorTranslateY.setDuration(1500);
        set.playTogether(animatorTranslateY);
        set.start();


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void animateimage(ImageView img, boolean scroll) {
        ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(img,
                "rotation", scroll == true ? 0f : 360f, scroll == true ? 360f : 0f);
        imageViewObjectAnimator.setDuration(1000); // miliseconds
        imageViewObjectAnimator.start();
    }
}
