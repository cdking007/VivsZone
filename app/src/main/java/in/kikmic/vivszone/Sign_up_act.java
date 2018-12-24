package in.kikmic.vivszone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Sign_up_act extends AppCompatActivity {

    private Button btnSignupS;
    private EditText edtUsernameS,edtPasswordS,edtEmailS;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        btnSignupS = findViewById(R.id.btnSignUpS);
        edtEmailS = findViewById(R.id.edtEmailS);
        edtPasswordS = findViewById(R.id.edtPasswordS);
        edtUsernameS = findViewById(R.id.edtUsernameS);

        edtPasswordS.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == keyEvent.ACTION_DOWN){

                    btnSignupS.callOnClick();
                }

                return false;
            }
        });
        btnSignupS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsernameS.getText().toString().equals("")
                        || edtEmailS.getText().toString().equals("")
                        || edtPasswordS.getText().toString().equals("")) {
                    Toast.makeText(Sign_up_act.this, "Empty Field Is Not Allowed", Toast.LENGTH_LONG).show();

                } else {
                    final ParseUser parseUser = new ParseUser();
                    parseUser.setEmail(edtEmailS.getText().toString());
                    parseUser.setUsername(edtUsernameS.getText().toString());
                    parseUser.setPassword(edtPasswordS.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(Sign_up_act.this);
                    progressDialog.setMessage("Signing ");
                    progressDialog.show();
                    parseUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {

                                Toast.makeText(Sign_up_act.this, parseUser.getUsername() + "Signed Sucessfully", Toast.LENGTH_LONG).show();
                                finish();

                            } else {
                                Toast.makeText(Sign_up_act.this, e.getMessage() + " ", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();

                        }

                    });


                }
            }
        });


    }

    public void rootIsClickedS(View view){

        try{

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

        }catch (Exception e){

            e.printStackTrace();

        }

    }


}
