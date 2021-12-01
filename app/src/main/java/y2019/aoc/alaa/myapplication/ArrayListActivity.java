package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArrayListActivity extends AppCompatActivity{

    //the object of the view - design
    private ListView myListView;
    //the object for the adaptor connection the data to the view
    private CustomAdapter myAdapter;
    //object containing the item to be displayed - Data
    private ArrayList<Item> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);
        list = new ArrayList<>();

        list.add(new Item("Audi A7", R.drawable.audi, "2016", "50₪","2"));
        list.add(new Item("Porsche GT2 RS", R.drawable.gt2rsnew, "2018", "50₪","2"));
        list.add(new Item("Audi R8", R.drawable.whiteaudi, "2020", "70₪","2"));

        //reference to the list view so it can programed
        myListView = findViewById(R.id.myListView);
        // connect adapter with Data
        myAdapter = new CustomAdapter(this,R.layout.car_row, list);
        //connect adapter with view
        myListView.setAdapter(myAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Item:" + list.get(i), Toast.LENGTH_LONG).show();
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                myAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//inflate put the menu on the screen above
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) // switch checks the id then go to the case that related to it
        {
            case R.id.camera:
                Toast.makeText(ArrayListActivity.this, "Camera", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ArrayListActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                this.finishAffinity();
                break;
            case R.id.profile:
                Intent intent1 = new Intent(ArrayListActivity.this, UserProfile.class);
                startActivity(intent1);

                break;//close application.


        }
        return super.onOptionsItemSelected(item);
    }

}