package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CardViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView cvname1, cvname2, cvname3, cvname4;
    private Button a1Button;
    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private String category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        cvname1 = findViewById(R.id.cvname1);
        cvname2 = findViewById(R.id.cvname2);
        cvname3 = findViewById(R.id.cvname3);
        cvname4 = findViewById(R.id.cvname4);

        String UID = mFirebaseAuth.getUid();
        category = getIntent().getStringExtra("category");

        DatabaseReference myRef = database.getReference("Cars/"+category+"/List");//getReference returns a root/message.
        a1Button = findViewById(R.id.a1Button);
        a1Button.setOnClickListener(this);

/*
        Car c1 = new Car("A3","Audi",2020, 20000,3,R.drawable.audia3,2,false);
        Car c2 = new Car("A6","Audi",2022, 40000,3,R.drawable.audia6,2,true);
        Car c3 = new Car("Q5","Audi",2021, 50000,3,R.drawable.audiq5,2,true);
        Car c4 = new Car("Q7","Audi",2018, 55000,3,R.drawable.audiq7,2,false);
        myRef.push().setValue(c1);
        myRef.push().setValue(c2);
        myRef.push().setValue(c3);
        myRef.push().setValue(c4);

*/
        if(category.equals("Audi")) {
            cvname1.setText("A3");
            cvname2.setText("A6");
            cvname3.setText("Q7");
            cvname4.setText("RS3");
        }
    }



    public void toArrayList(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        intent.putExtra("category", category);
        if(view.getId() == a1Button.getId())
            intent.putExtra("model", a1Button.getText().toString());
        startActivity(intent);

    }
}
