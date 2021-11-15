package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;//4 SECONDS
    //variable
    Animation topAnim,middleAnim, bottomAnim;
    ImageView logoimagie;
    TextView logo, underlogo;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Animation
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        middleAnim = AnimationUtils.loadAnimation(this, R.anim.middle_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        //id
        logoimagie = findViewById(R.id.logoimagie);
        logo = findViewById(R.id.logo);
        underlogo = findViewById(R.id.underlogo);

        logoimagie.setAnimation(topAnim);
        logo.setAnimation(middleAnim);
        underlogo.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(AnimationActivity.this, MainActivity.class);

            Pair [] pairs = new Pair[2];
            pairs[0] = new Pair<View,String>(logoimagie, "logo_image");
            pairs[1] = new Pair<View,String>(logo, "logo_text");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AnimationActivity.this, pairs);
            startActivity(intent,options.toBundle());//will carry our animation (bundle);

        }, SPLASH_SCREEN);
    }

}

