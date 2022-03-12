package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);


        String UID = mFirebaseAuth.getUid();
        category = getIntent().getStringExtra("category");
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

        if (category.equals("BMW")) {
            img.add(R.drawable.bmw1series);
            img.add(R.drawable.bmwx6);
            img.add(R.drawable.bmwx7);
            img.add(R.drawable.bmw2series);

            titels.add("1 Series");
            titels.add("X6");
            titels.add("X7");
            titels.add("2 Series");
        }

        adapter = new MyAdapter(this, titels, img, category);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        Car c1 = new Car("Audi", "A3", 2020, 350, R.drawable.audia3, 5, false);
        Car c2 = new Car("Audi", "A6", 2022, 700, R.drawable.audia6, 5, true);
        Car c3 = new Car("Audi", "Q5", 2021, 1100, R.drawable.audiq5, 5, true);
        Car c4 = new Car("Audi", "Q7", 2018, 1600, R.drawable.audiq7, 5, false);
        Car b1 = new Car("BMW", "1 Series", 2018, 150, R.drawable.bmw1series, 5, false);
        Car b2 = new Car("BMW", "X6", 2022, 900, R.drawable.bmwx6, 5, true);
        Car b3 = new Car("BMW", "X7", 2021, 1100, R.drawable.bmwx7, 5, false);
        Car b4 = new Car("BMW", "2 Series", 2022, 1500, R.drawable.bmw2series, 5, false);


//        myRef = database.getReference("Cars/" + category + "/List/2 Series");//getReference returns a root/message.
//        myRef.setValue(b4);
    }

    public void toArrayList(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        startActivity(intent);

    }


}
