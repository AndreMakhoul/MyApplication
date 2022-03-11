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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InformationActivity extends AppCompatActivity {

    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
    private Button verifyBtn;
    private TextView category, type, year, price, electric, numofseats;
    private ImageView img;
    //Get instance of Authentication Project In FB console
    private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private DatabaseReference myRef;//getReference returns a root/message.
    private String type1;
    private String category1;

    private FirebaseUser user;
    private DatabaseReference reference;
    Car c;


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
        img = findViewById(R.id.img);
        verifyBtn = findViewById(R.id.verifybtn);
        category1 = getIntent().getStringExtra("category");
        type1 = getIntent().getStringExtra("type");

        user = mFirebaseAuth.getCurrentUser();
        String uid = user.getUid();

        reference = database.getReference("user/");

        myRef = database.getReference("Cars/" + category1 + "/List");

//        Toast.makeText(InformationActivity.this, category1 + ": " + type1, Toast.LENGTH_LONG).show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    c = dataSnapshot.getValue(Car.class);
                    if (type1.equals(c.getType())) {
                        type.setText(c.getType());
                        category.setText(c.getDescription());
                        year.setText(c.getYear() + "");
                        price.setText(c.getPrice() + "$");
                        electric.setText(c.isElectric() + "");
                        numofseats.setText(c.getNoOfSeats() + "");
                        img.setBackgroundResource(c.getImage());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    if (user.getUid().equals(dataSnapshot.getKey())) {
                                        User u = dataSnapshot.getValue(User.class);

                                        String n = u.getLabel();
                                        String m = u.getMoney();
                                        int money = Integer.parseInt(m);
                                        int num = Integer.parseInt(n);
                                        u.setLabel((num + 1) + "");
                                        u.setMoney((money - c.getPrice()) + "");
                                        reference.child(uid).setValue(u);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
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
