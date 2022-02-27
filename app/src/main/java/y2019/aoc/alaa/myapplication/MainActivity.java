package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
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

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener //implements ממשק onLongClickListener (Long Click) onLongClick.
{
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);





        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);

            Pair[] pairs = new Pair[6];
            pairs[0] = new Pair<View, String>(rentoGo, "logo_text");
            pairs[1] = new Pair<View, String>(sloganName, "logo_desc");
            pairs[2] = new Pair<View, String>(email, "email_tran");
            pairs[3] = new Pair<View, String>(password, "password_tran");
            pairs[4] = new Pair<View, String>(go, "button_tran");
            pairs[5] = new Pair<View, String>(buttonSignUp, "login_signup_tran");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
            startActivity(intent, options.toBundle());//to bundle carry the animation.
        });


        // returns a reference to the instance of the project firebase (console) and connect it to the project.
        mAuth = FirebaseAuth.getInstance();
        rentoGo = findViewById(R.id.rentoGo);
        sloganName = findViewById(R.id.sloganName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.Password);
        smallLogoImage = findViewById(R.id.smallLogoImage);
        buttonForgetPass = findViewById(R.id.buttonForgetPass);
        go = findViewById(R.id.go);

        //sets the require button to response to long click, otherwise it wont
        go.setOnLongClickListener(this);


        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE); // creates a localFile which saves the SharedPreferences.
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
            editor.putString("email", email.getText().toString());//keyword , email what the user put.
            editor.putString("password", password.getText().toString());//keyword, password what the user put.

            //save and close file
            editor.commit();

            intent.putExtra("email", email.getText().toString());

            login(email.getText().toString(), password.getText().toString());
        }
    }


    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password) //instance of the firebase.
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)//onComplete method waits for the firebase to finish his job then it gives us the result.
                    {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(MainActivity.this, ArrayListActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException()); //getException has description for why the information email or password are wrong.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
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

}