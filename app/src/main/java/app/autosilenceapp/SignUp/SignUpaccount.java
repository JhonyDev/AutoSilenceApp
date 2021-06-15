package app.autosilenceapp.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.autosilenceapp.MainActivity;
import app.autosilenceapp.R;


public class SignUpaccount extends Activity {

    private static final String TAG = "hlllll";
    EditText enterEmail, enterPassword, enterPhoneNumber, enterName;
    String emailText;
    String passwordText;
    String PhoneNumberText;
    Button signUp;
    boolean isValid = true;
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upaccount);
        mAuth = FirebaseAuth.getInstance();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(SignUpaccount.this, R.color.white));// set status background white
        enterEmail = findViewById(R.id.enterEmail);
        enterPassword = findViewById(R.id.enterPassword);
        enterPhoneNumber = findViewById(R.id.enterPhoneNumber);
        enterName = findViewById(R.id.enterName);
        signUp = findViewById(R.id.signUp);


        signUp.setOnClickListener(v -> {
            emailText = enterEmail.getText().toString();
            passwordText = enterPassword.getText().toString();

            if (isEmpty(enterEmail)) {
                Toast.makeText(SignUpaccount.this, "Plz enter email", Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            if (isEmpty(enterPhoneNumber)) {
                Toast.makeText(SignUpaccount.this, "Plz enter Phone Number", Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            if (isEmpty(enterPassword)) {
                Toast.makeText(SignUpaccount.this, "Plz enter Password", Toast.LENGTH_SHORT).show();
                isValid = false;
            }
            if (isEmpty(enterName)) {
                Toast.makeText(SignUpaccount.this, "Plz enter Name", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else {
                Toast.makeText(SignUpaccount.this, "Your account is created", Toast.LENGTH_SHORT).show();
                firebaseSignUp();
            }


        });
    }

    public void firebaseSignUp() {
        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(SignUpaccount.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        //  updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignUpaccount.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                        Log.i(TAG, "firebaseSignUp: " + task.getException());
                        //   updateUI(null);
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //reload();
        }
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


}