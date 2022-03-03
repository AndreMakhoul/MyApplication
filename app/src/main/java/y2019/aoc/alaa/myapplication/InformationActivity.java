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
    private Button verifyBtn;
    private String myText;
    private TextView category, type, year, price, electric, numofseats, stock;
    private ImageView img;
    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;
    private String type1;
    private String category1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        category = findViewById(R.id.category);
        type = findViewById(R.id.type);
        year = findViewById(R.id.year);
        price = findViewById(R.id.price);
        electric = findViewById(R.id.electric);
        numofseats = findViewById(R.id.numofseats);
        stock = findViewById(R.id.stock);
        img = findViewById(R.id.img);
        verifyBtn = findViewById(R.id.verifybtn);
        category1 = getIntent().getStringExtra("category");
        type1 = getIntent().getStringExtra("type");

        DatabaseReference myRef ;//getReference returns a root/message.



        if (category1.equals("A3")) {
            myRef = database.getReference("Cars/"+ type1 + "List/" + category1);

//            type.setText(c1.getDescription());
//            category.setText(c1.getType());
//            year.setText(c1.getYear());
//            price.setText(c1.getPrice());
//            electric.setText((c1.isElectric()));
//            numofseats.setText(c1.getNoOfSeats());
//            stock.setText(c1.getLeft());
//            img.setImageResource(c1.getImage());


        }


        Intent notifyIntent = new Intent(this, NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myDialog = new AlertDialog.Builder(InformationActivity.this);
                myDialog.setTitle("Are you Sure You Want To Rent The Car!");

                final EditText ver = new EditText(InformationActivity.this);
                myDialog.setPositiveButton("verify", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, 100, pendingIntent);
                        myText = ver.getText().toString();
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
