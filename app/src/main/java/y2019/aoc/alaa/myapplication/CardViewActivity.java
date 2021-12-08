package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CardViewActivity extends AppCompatActivity {

    private TextView cvname1, cvname2, cvname3, cvname4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        cvname1 = findViewById(R.id.cvname1);
        cvname2 = findViewById(R.id.cvname2);
        cvname3 = findViewById(R.id.cvname3);
        cvname4 = findViewById(R.id.cvname4);
    }


    public void toArrayList(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);

    }
}