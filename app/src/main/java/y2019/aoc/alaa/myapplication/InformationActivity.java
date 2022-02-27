package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InformationActivity extends AppCompatActivity {

    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    private Button verifybtn;
    private String myText;
//    private TextView category, type, year, price, electric, numofseats, stock;
//    private ImageView img;
    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private String type1;
    private String category1;
    private String random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
//        category = findViewById(R.id.category);
//        type = findViewById(R.id.type);
//        year = findViewById(R.id.year);
//        price = findViewById(R.id.price);
//        electric = findViewById(R.id.electric);
//        numofseats = findViewById(R.id.numofseats);
//        stock = findViewById(R.id.stock);
//        img = findViewById(R.id.img);

        random = getIntent().getStringExtra("random");
        verifybtn = findViewById(R.id.verifybtn);
        category1 = getIntent().getStringExtra("category");
        Toast.makeText(InformationActivity.this, "category: " + category1 + "Random: "+random, Toast.LENGTH_SHORT).show();

        Car c1 = new Car("A3", "Audi", 2020, 20000, 2, R.drawable.audia3, 2, false);
        Car c2 = new Car("A6", "Audi", 2022, 40000, 4, R.drawable.audia6, 4, true);
        Car c3 = new Car("Q5", "Audi", 2021, 50000, 2, R.drawable.audiq5, 4, true);
        Car c4 = new Car("Q7", "Audi", 2018, 55000, 4, R.drawable.audiq7, 2, false);
        if (category1.equals("A3")) {

        }


        Intent notifyIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        verifybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarmManager.set(AlarmManager.RTC_WAKEUP, 100, pendingIntent);
                AlertDialog.Builder myDialog = new AlertDialog.Builder(InformationActivity.this);
                myDialog.setTitle("Write the verification Code Please");

                final EditText ver = new EditText(InformationActivity.this);
                ver.setInputType(InputType.TYPE_CLASS_NUMBER);
                myDialog.setView(ver);
                myDialog.setPositiveButton("verify", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        myText = ver.getText().toString();
                        Toast.makeText(InformationActivity.this, "Verify Code Is: " + myText + "random: " + random, Toast.LENGTH_LONG).show();
                    }

                });

                myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                myDialog.show();
            }
        });

    }

}
