package y2019.aoc.alaa.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AboutUsActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//inflate put the menu on the screen above
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) // switch checks the id then go to the case that related to it
        {
            case R.id.camera:
                Toast.makeText(AboutUsActivity.this, "Camera", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AboutUsActivity.this,ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                finish();
                break;//close application.
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == dialogInterface.BUTTON_POSITIVE) {
            //goes back to the page before
            super.onBackPressed();
            //close the dialog
            dialogInterface.cancel();
            //closing the app
            this.finishAffinity();

        }
        if (i == dialogInterface.BUTTON_NEGATIVE) {
            //close the dialog
            dialogInterface.cancel();
        }
    }

    public void onBackPressed() //method from implement DialogInterface.OnClickListener
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // create the dialog(builder).
        builder.setMessage("Are you sure?");
        //cancel not showing in the dialog
        builder.setCancelable(false); //no cancel on screen
        builder.setPositiveButton("YES", this);
        builder.setNegativeButton("NO", this);
        //creating it
        AlertDialog dialog = builder.create();
        //showing it
        dialog.show();
    }

    public void moveToMain(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);

    }


}