package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InformationActivity extends AppCompatActivity {


    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        type = getIntent().getStringExtra("type");
        Car c1 = new Car("A3","Audi",2020, 20000,2,R.drawable.audia3,2,false);
        Car c2 = new Car("A6","Audi",2022, 40000,4,R.drawable.audia6,4,true);
        Car c3 = new Car("Q5","Audi",2021, 50000,2,R.drawable.audiq5,4,true);
        Car c4 = new Car("Q7","Audi",2018, 55000,4,R.drawable.audiq7,2,false);
        if(type.equals("A3")) {
          c1.setYear(2000);
        }


        }

    }
