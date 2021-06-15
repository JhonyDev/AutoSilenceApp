package app.autosilenceapp.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.autosilenceapp.MainActivity;
import app.autosilenceapp.R;

public class Login extends Activity {

    private static final String TAG = "hlllll";
    EditText enterEmail, enterPassword;
    Button login, SignUp;
    String emailText, passwordText;
    boolean isValid = true;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private FirebaseAuth mAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.enterPassword);
        login = findViewById(R.id.login);
        SignUp = findViewById(R.id.signUp);
        mAuth = FirebaseAuth.getInstance();

        pref = getSharedPreferences("mypref", MODE_PRIVATE);

//        if (pref.getBoolean("firststart", true)) {
//            Toast.makeText(Login.this, "Plz log in", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(Login.this, Login.class));
//            // update sharedpreference - another start wont be the first
//            // apply changes
//
//        } else {
//            startActivity(new Intent(Login.this, Login.class));
//        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logedin();
                if (isEmpty(enterEmail)) {
                    Toast.makeText(Login.this, "Plz enter email", Toast.LENGTH_SHORT).show();
                    isValid = false;
                }
                if (isEmpty(enterPassword)) {
                    Toast.makeText(Login.this, "Plz enter Password", Toast.LENGTH_SHORT).show();
                    isValid = false;
                } else {
                    firebaselogin();
                }


            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUpaccount.class);
                startActivity(intent);
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(Login.this, R.color.white));// set status background white
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void firebaselogin() {

        emailText = enterEmail.getText().toString();
        passwordText = enterPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "login successful",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            finish();
                            //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }


    public void logedin() {
        emailText = enterEmail.getText().toString();
        passwordText = enterPassword.getText().toString();
        if (isValid) {
            if (isEmpty(enterEmail)) {
                Toast.makeText(Login.this, "Plz enter email", Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            if (isEmpty(enterPassword)) {
                Toast.makeText(Login.this, "Plz enter Password", Toast.LENGTH_SHORT).show();
                isValid = false;
            }

            passwordText = enterPassword.getText().toString();
            emailText = enterEmail.getText().toString();
                   /* Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);*/
            // Log.d("emailll",emailText);
            //Log.d("emailll",passwordText);
            if (emailText.equals("usman55@gmail.com")) {
                Log.d("emailll", "fine");

                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);


            } else {
                Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                editor = pref.edit();
                editor.putBoolean("firststart", false);
                editor.commit();
            }


            if (emailText.equals("usman55@gmail.com") && passwordText.equals("usman1234")) {
                //everything checked we open new activity
                Log.d("emailll", emailText);
                Log.d("emailll", passwordText);

                //we close this activity
                finish();
            }
        }
    }
}
