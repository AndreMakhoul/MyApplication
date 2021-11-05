package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "FIREBASE";
    private Button submitButton,buttonSignIn;
    private EditText  etEmail,etPassWord,etFullName,etPhoneNumber;
    private DatePickerDialog.OnDateSetListener mOnDateSetListener;
    private FirebaseAuth mAuth;

    private ImageView suSmallLogoImage;
    private TextView suRentoGo,suSloganName,tvDateOfBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        suRentoGo=findViewById(R.id.suRentoGo);
        suSloganName=findViewById(R.id.suSloganName);
        suSmallLogoImage=findViewById(R.id.suSmallLogoImage);
        //Initialize Firebase Auth.
        mAuth = FirebaseAuth.getInstance();//gets the instance of the firebase connected to the project.
        etEmail = findViewById(R.id.etEmail);
        etPassWord = findViewById(R.id.etPassWord);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        etFullName=findViewById(R.id.etFullName);
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
        buttonSignIn=findViewById(R.id.buttonSignIn);

        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mOnDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mOnDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = "month/day/year: " + month + "/" + day + "/" + year;
                tvDateOfBirth.setText(date);
            }
        };

    }


    public void signup(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)//instance of the firebase.
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(SignUpActivity.this,ArrayListActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
    public void ToMain(View view)
    {
        Intent intent = new Intent (SignUpActivity.this,MainActivity.class);
                startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        signup(etEmail.getText().toString(), etPassWord.getText().toString() );
    }
}