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
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(AboutUsActivity.this, "Settings", Toast.LENGTH_LONG).show();
                break;
            case R.id.exit:
                finish();
                break;
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

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        //cancel not showing in the dialog
        builder.setCancelable(false);
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