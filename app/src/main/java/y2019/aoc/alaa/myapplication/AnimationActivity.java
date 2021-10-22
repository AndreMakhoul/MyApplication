package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;//3.5 SECONDS
    //variable
    Animation topAnim, bottomAnim;
    ImageView logoimagie;
    TextView logo, underlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation);
        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        //id
        logoimagie = findViewById(R.id.logoimagie);
        logo = findViewById(R.id.logo);
        underlogo = findViewById(R.id.underlogo);

        logoimagie.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        underlogo.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(AnimationActivity.this, MainActivity.class);



            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }

}

