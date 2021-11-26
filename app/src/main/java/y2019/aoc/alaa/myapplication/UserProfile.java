package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    private TextView full_name, payment_label, booking_label, profileEmail, profilePassword, profileFullName, profileNumber;
    private ImageView profile_image;
    private String email, password;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        full_name = findViewById(R.id.full_name);
        payment_label = findViewById(R.id.payment_label);
        booking_label = findViewById(R.id.booking_label);
        profileEmail = findViewById(R.id.profileEmail);
        profilePassword = findViewById(R.id.profilePassword);
        profileFullName = findViewById(R.id.profileFullName);
        profileNumber = findViewById(R.id.profileNumber);
        profile_image = findViewById(R.id.profile_image);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference(USER);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(email)) {
                        profileFullName.setText(ds.child("fullName").getValue(String.class));
                        profileEmail.setText(ds.child("email").getValue(String.class));
                        profileNumber.setText(ds.child("number").getValue(String.class));
                        profilePassword.setText(ds.child("password").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}