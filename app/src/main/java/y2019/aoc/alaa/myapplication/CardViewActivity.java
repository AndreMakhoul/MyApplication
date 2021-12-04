package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
    }


    public void toArrayList(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);

    }
}