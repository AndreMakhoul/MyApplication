package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardViewActivity extends AppCompatActivity {

    private TextView cvname1, cvname2, cvname3, cvname4;
    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        cvname1 = findViewById(R.id.cvname1);
        cvname2 = findViewById(R.id.cvname2);
        cvname3 = findViewById(R.id.cvname3);
        cvname4 = findViewById(R.id.cvname4);

        String UID = mFirebaseAuth.getUid();
        String category = getIntent().getStringExtra("category");

        if(category.equals("Audi")) {
            cvname1.setText("A4");
            cvname2.setText("A6");
            cvname3.setText("Q7");
            cvname4.setText("RS3");
        }
        if(category.equals("BMW")) {
            cvname1.setText("M4 Competition");
            cvname2.setText("M8 Competition");
            cvname3.setText("X7");
            cvname4.setText("3 Series");
        }







    }


    public void toArrayList(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);

    }
}