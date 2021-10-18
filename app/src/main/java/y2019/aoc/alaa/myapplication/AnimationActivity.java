package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimationActivity extends AppCompatActivity {
//variable
    Animation topAnim, bottomAnim;
    ImageView logoimagie;
    TextView logo,underlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_animation);
        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //id
        logoimagie= findViewById(R.id.logoimagie);
        logo= findViewById(R.id.logo);
        underlogo= findViewById(R.id.underlogo);

        logoimagie.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        underlogo.setAnimation(bottomAnim);

    }

}