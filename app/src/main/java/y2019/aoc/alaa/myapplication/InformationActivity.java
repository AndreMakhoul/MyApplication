package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformationActivity extends AppCompatActivity {


    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }
}