package in.kikmic.vivszone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.w3c.dom.Text;

import java.time.Instant;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnLogIn,btnSignUp;
    private TextView edtPassword,edtUserName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Tracking Installation
        ParseInstallation.getCurrentInstallation().saveInBackground();
        //Initiallizing Value Of components

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtPassword = findViewById(R.id.edtPassword);
        edtUserName = findViewById(R.id.edtUserName);

        if (ParseUser.getCurrentUser() !=null){
            IntentIsCalled();
            finish();
        }

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    btnLogIn.callOnClick();
                }

                return false;
            }
        });
        //LogIn Button Code

        btnLogIn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                ParseUser logInParse = new ParseUser();
                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Loging...");
                progressDialog.show();
                logInParse.logInInBackground(edtUserName.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if (user != null && e == null){

                            Toast.makeText(MainActivity.this,"Login Sucess",Toast.LENGTH_SHORT).show();
                                IntentIsCalled();
                                finish();


                        }
                        else {

                            Toast.makeText(MainActivity.this,e.getMessage()+"",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();

                    }

                });



            }
        });



        //SignUp Button Code To Transfer It
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Sign_up_act.class);
                startActivity(intent);
            }
        });



    }

    public void rootIsClickedL(View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception d){

            d.printStackTrace();
        }
    }
    public void IntentIsCalled(){

        Intent intent = new Intent(MainActivity.this,SocialMain.class);
        startActivity(intent);

    }
}
