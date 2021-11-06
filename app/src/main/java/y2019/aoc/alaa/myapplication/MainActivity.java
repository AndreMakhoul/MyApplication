package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    private static final String TAG = "FIREBASE";
    private TextView rentoGo, sloganName;
    private EditText email, password;
    private ImageView smallLogoImage;
    private Button buttonForgetPass, go, buttonSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // returns a reference to the instance of the project fire base.
        mAuth = FirebaseAuth.getInstance();
        rentoGo = findViewById(R.id.rentoGo);
        sloganName = findViewById(R.id.sloganName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        smallLogoImage = findViewById(R.id.smallLogoImage);
        buttonForgetPass = findViewById(R.id.buttonForgetPass);
        go = findViewById(R.id.go);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        //sets the require button to response to long click, otherwise it wont
        //go.setOnLongClickListener(this);


        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        String email2 = sp.getString("email", "");
        String password2 = sp.getString("password", "");

        if (!email.equals("") && !password2.equals("")) {
            email.setText(email2);
            password.setText(password2);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        String pass = password.getText().toString();
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";

        if (!email.getText().toString().equals("") && email.getText().toString().contains("@") && email.getText().toString().contains(".")) {
            //saving email and password of user in a local file for future use
            //create sp file
            SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);

            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();

            //write the wanted settings
            editor.putString("email", email.getText().toString());
            editor.putString("password", password.getText().toString());

            //save and close file
            editor.commit();
            intent.putExtra("email", email.getText().toString());

            login(email.getText().toString(),password.getText().toString());

           // startActivity(intent);

        }
        // startActivity(intent);

        //   Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();


    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        email.setText("");
        password.setText("");
        return true;
    }

 public void login(String email, String password){
     mAuth.signInWithEmailAndPassword(email, password)
             .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()) {
                         // Sign in success, update UI with the signed-in user's information
                         Log.d(TAG, "signInWithEmail:success");
                         FirebaseUser user = mAuth.getCurrentUser();
                         Intent i = new Intent(MainActivity.this, ArrayListActivity.class);
                         startActivity(i);
                     } else {
                         // If sign in fails, display a message to the user.
                         Log.w(TAG, "signInWithEmail:failure", task.getException());
                         Toast.makeText(MainActivity.this, "Authentication failed.",
                                 Toast.LENGTH_SHORT).show();
                     }

                     // ...
                 }
             });
 }

}