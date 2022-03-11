package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    private TextView profileEmail, profilePassword, profileFullName, profileNumber, booking_label, payment_label;
    private ImageView profile_image;
    private Button reportbtn;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://andre-2e345-default-rtdb.europe-west1.firebasedatabase.app/");
    private FirebaseUser user;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        profileEmail = findViewById(R.id.profileEmail);
        profilePassword = findViewById(R.id.profilePassword);
        profileFullName = findViewById(R.id.profileFullName);
        profileNumber = findViewById(R.id.profileNumber);
        profile_image = findViewById(R.id.profile_image);
        booking_label = findViewById(R.id.booking_label);
        payment_label = findViewById(R.id.payment_label);
        database = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();
        myRef = database.getReference("user/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (user.getUid().equals(dataSnapshot.getKey())) {
                        User u = dataSnapshot.getValue(User.class);
                        updateUserData(u);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void updateUserData(User user) {
        profileFullName.setText((user.getName()));
        profileEmail.setText((user.getEmail()));
        profilePassword.setText((user.getPassword()));
        profileNumber.setText((user.getPhoneNumber()));
        booking_label.setText((user.getLabel() + ""));
        payment_label.setText(user.getMoney()+"$");
    }

    public void Picture(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}