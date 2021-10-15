package y2019.aoc.alaa.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {
    private TextView rentoGo,signUpText;
    private EditText editTextTextEmailAddress, editTextPassword;
    private Button buttonLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rentoGo = findViewById(R.id.rentoGo);
        signUpText= findViewById(R.id.signUpText);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogIn = findViewById(R.id.buttonLogIn);
        //sets the require button to response to long click, otherwise it wont
        buttonLogIn.setOnLongClickListener(this);



        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
        String email= sp.getString("email","");
        String password = sp.getString("password", "");

        if(!email.equals("")&& !password.equals("")){
            editTextTextEmailAddress.setText(email);
            editTextPassword.setText(password);
        }
    }

    public void login(View view) {
        Intent intent = new Intent(this, ArrayListActivity.class);
        String pass = editTextPassword.getText().toString();
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";

        if (!editTextTextEmailAddress.getText().toString().equals("") && editTextTextEmailAddress.getText().toString().contains("@") && editTextTextEmailAddress.getText().toString().contains(".")) {
         //saving email and password of user in a local file for future use
            //create sp file
            SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);
            //open editor for editing
            SharedPreferences.Editor editor = sp.edit();
            //write the wanted settings
            editor.putString("email",editTextTextEmailAddress.getText().toString());
            editor.putString("password",editTextPassword.getText().toString());
            //save and close file
            editor.commit();
            intent.putExtra("email",editTextTextEmailAddress.getText().toString());
            startActivity(intent);

          //  if (pass.length() >= 8 && pass.contains(upperCaseChars) ) {

            {
                //intent.putExtra("email", editTextTextEmailAddress.getText().toString());

            }
            //}
        }
        startActivity(intent);

        //   Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();


    }

    public void signUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        editTextTextEmailAddress.setText("");
        editTextPassword.setText("");
        return true;
    }
}