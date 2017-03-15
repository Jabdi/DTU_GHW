package com.dtu.jacopomattia.ghw;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dtu.jacopomattia.ghw.Constants;
import com.dtu.jacopomattia.ghw.HovedMenuen;
import com.dtu.jacopomattia.ghw.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private AutoCompleteTextView email_field;
    private EditText password_field;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private ProgressDialog progressDialog;
    private static final int MY_PASSWORD_DIALOG_ID = 1;
    private boolean ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        email_field = (AutoCompleteTextView)findViewById(R.id.email_field);
        password_field  = (EditText)findViewById(R.id.password_field);

        pref = getSharedPreferences(Constants.SHARED_PREFERENCES, MODE_PRIVATE);
        editor = pref.edit();
        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    editor.putBoolean("logedIn", true);
                    editor.apply();
                    startApp();
                    finish();

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    editor.putBoolean("logedIn", false);
                    editor.apply();
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


    }
    private void attemptLogin(){
        final String email = email_field.getText().toString().trim();
        final String password = password_field.getText().toString().trim();
        progressDialog.setTitle("Logging in...");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        signIn(email,password);
    }

    private void createAccount(final String email, final String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            progressDialog.dismiss();
                            Log.d("HEJ", task.getResult().toString());

                        }else {
                            editor.putBoolean(Constants.KEY_PREF_LOGGED_IN, true);
                            editor.apply();
                            startApp();
                        }
                    }
                });

    }
    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithEmail", task.getException());
                            Toast.makeText(getBaseContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            editor.putBoolean(Constants.KEY_PREF_LOGGED_IN, true);
                            editor.apply();
                            progressDialog.dismiss();
                            startApp();
                        }

                        // ...
                    }
                });
    }

    public void onClick(View view) {

        switch (view.getId()){

            case  R.id.activity_login_submit:{

                attemptLogin();
                break;

            }
            case R.id.login_activity_register_btn:{

                progressDialog.setTitle("Logging in...");
                progressDialog.setMessage("Please wait...");
                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                showDialog(MY_PASSWORD_DIALOG_ID);

            }
        }
    }


    private void startApp(){
        Log.d("HEY", "Successfully logged in.");

        Intent intentMain = new Intent(this, HovedMenuen.class);
        startActivity(intentMain);
        finish();

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {

            case MY_PASSWORD_DIALOG_ID:
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                final View layout = inflater.inflate(R.layout.activity_register, (ViewGroup) findViewById(R.id.root));
                final LinearLayout elements = (LinearLayout) layout.findViewById(R.id.root);
                final EditText name = (EditText) layout.findViewById(R.id.EditText_name);
                final EditText email = (EditText) layout.findViewById(R.id.EditText_email);
                final EditText password1 = (EditText) layout.findViewById(R.id.EditText_Pwd1);
                final EditText password2 = (EditText) layout.findViewById(R.id.EditText_Pwd2);
                final TextView error = (TextView) layout.findViewById(R.id.TextView_PwdProblem);
                final TextView passLenghtError = (TextView) layout.findViewById(R.id.TextView_Password_Lenght_Error);
                final TextView nameError = (TextView) layout.findViewById(R.id.TextView_NameProblem);
                final TextView emailError = (TextView) layout.findViewById(R.id.TextView_EmailProblem);
                password1.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        String strPass = password1.getText().toString().trim();
                        if (strPass.length() < 6) {
                            passLenghtError.setText(R.string.dialog_password_lenght);
                            passLenghtError.setTextColor(Color.RED);
                            ok = false;
                        } else {
                            elements.removeView(passLenghtError);
                            ok = true;
                        }
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                });
                password2.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        String strPass1 = password1.getText().toString().trim();
                        String strPass2 = password2.getText().toString().trim();
                        if (strPass1.equals(strPass2)) {
                            error.setText(R.string.dialog_password_match);
                            error.setTextColor(Color.DKGRAY);
                            ok = true;
                        } else {
                            error.setText(R.string.dialog_password_not_match);
                            error.setTextColor(Color.RED);
                            ok= false;
                        }
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                });
                name.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        String nameString = name.getText().toString().trim();
                        if (nameString.isEmpty()) {
                            nameError.setText(R.string.dialog_empty_name);
                            nameError.setTextColor(Color.RED);
                            ok = false;
                        } else {
                            elements.removeView(nameError);
                            ok = true;
                        }
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                });
                email.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {
                        String emailString = email.getText().toString().trim();
                        if (emailString.isEmpty()) {
                            emailError.setText(R.string.dialog_empty_email);
                            emailError.setTextColor(Color.RED);
                            ok = false;
                        } else {
                            if (isValidEmail(emailString)){
                                elements.removeView(emailError);
                                ok = true;
                            }else {
                                emailError.setText(R.string.dialog_incorrect_email);
                                emailError.setTextColor(Color.RED);
                                ok = false;
                            }

                        }
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.dialog_title);
                builder.setView(layout);
                builder.setPositiveButton(android.R.string.ok, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                String pass = password1.getText().toString().trim();
                                String eml = email.getText().toString().trim();
                                String nameString = name.getText().toString().trim();


                                if (ok){
                                    editor.putString(Constants.PROFILE_NAME,nameString);
                                    editor.apply();
                                    createAccount(eml,pass);
                                    return;
                                }else{
                                    Toast.makeText(getApplicationContext(),
                                            "Please fill form correctly!", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } });

                builder.setNegativeButton(android.R.string.cancel, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Canceled!", Toast.LENGTH_SHORT).show();
                                return;
                            } });

                return builder.create();

        }
        return null;
    }
    private final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
