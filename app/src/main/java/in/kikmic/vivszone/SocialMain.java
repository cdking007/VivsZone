package in.kikmic.vivszone;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

public class SocialMain extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alrtBox = new AlertDialog.Builder(this);
        alrtBox.setMessage("Are You Sure?");
        alrtBox.setCancelable(true);
        alrtBox.setNegativeButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SocialMain.super.onBackPressed();
            }
        });
        alrtBox.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog aleart = alrtBox.create();
        aleart.show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_main);
        btnLogOut = findViewById(R.id.btnLogOut);
        Toast.makeText(SocialMain.this,ParseUser.getCurrentUser().getUsername().toString(),Toast.LENGTH_LONG).show();
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ParseUser.getCurrentUser() == null)
                {

                    Intent intent = new Intent(SocialMain.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    ParseUser.logOutInBackground();
                    finish();

                }
            }
        });
    }


}
