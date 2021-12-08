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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ArrayListActivity extends AppCompatActivity  {

    //the object of the view - design
    private ListView myListView;
    //the object for the adaptor connection the data to the view
    private CustomAdapter myAdapter;
    //object containing the item to be displayed - Data
    private ArrayList<Item> list;

    String carsName[] ={"Audi,BMW,Ferrari,Tesla,Jeep,Volkswagen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);
        list = new ArrayList<>();

        list.add(new Item("Audi", R.drawable.audilogo));
        list.add(new Item("BMW", R.drawable.bmwlogo));
        list.add(new Item("Ferrari", R.drawable.ferrarilogo));
        list.add(new Item("Jeep", R.drawable.jeeplogo));
        list.add(new Item("Tesla", R.drawable.teslalogo));
        list.add(new Item("Volkswagen", R.drawable.volkswagen));


        //reference to the list view so it can programed
        myListView = findViewById(R.id.myListView);
        // connect adapter with Data
        myAdapter = new CustomAdapter(this, R.layout.car_row, list);
        //connect adapter with view
        myListView.setAdapter(myAdapter);

        Intent intent = new Intent(this, CardViewActivity.class);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(intent);
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
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s)//when user type.
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)//when the user change or type more.
            {
                myAdapter.getFilter().filter(s);
                return false;
            }
        });
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