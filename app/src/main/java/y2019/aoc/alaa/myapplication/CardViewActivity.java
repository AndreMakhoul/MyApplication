package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> titels;
    private List<Integer> img;
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private String category;
    private  String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);



        String UID = mFirebaseAuth.getUid();
        category = getIntent().getStringExtra("category");
        type = getIntent().getStringExtra("type");

        DatabaseReference myRef = database.getReference("Cars/" + category + "/List");//getReference returns a root/message.

        recyclerView = findViewById(R.id.recycleview);
        titels = new ArrayList<>();
        img = new ArrayList<>();

        if (category.equals("Audi")) {


            img.add(R.drawable.audia3);
            img.add(R.drawable.audia6);
            img.add(R.drawable.audiq5);
            img.add(R.drawable.audiq7);

            titels.add("A3");
            titels.add("A6");
            titels.add("Q5");
            titels.add("Q7");

        }
        adapter = new MyAdapter(this, titels, img);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);



/*
        Car c1 = new Car("A3","Audi",2020, 20000,2,R.drawable.audia3,2,false);
        Car c2 = new Car("A6","Audi",2022, 40000,4,R.drawable.audia6,4,true);
        Car c3 = new Car("Q5","Audi",2021, 50000,2,R.drawable.audiq5,4,true);
        Car c4 = new Car("Q7","Audi",2018, 55000,4,R.drawable.audiq7,2,false);
        myRef.push().setValue(c1);
        myRef.push().setValue(c2);
        myRef.push().setValue(c3);
        myRef.push().setValue(c4);

*/
//        if (category.equals("Audi")) {
//            cvname1.setText("A3");
//            cvname2.setText("A6");
//            cvname3.setText("Q5");
//            cvname4.setText("Q7");
//        }



    }


    public void toArrayList(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, InformationActivity.class);
        startActivity(intent);


    }
}
