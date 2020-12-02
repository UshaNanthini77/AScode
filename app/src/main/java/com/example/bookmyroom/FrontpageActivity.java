package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FrontpageActivity extends AppCompatActivity {

    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mtv1;
    FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontpage);


        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);
        mLoginBtn = findViewById(R.id.btn_login);
        mtv1 = findViewById(R.id.tv1);
        fAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = fAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(FrontpageActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(FrontpageActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(FrontpageActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (email.isEmpty()) {
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                } else if (password.isEmpty()) {
                    mPassword.setError("Password is required");
                    mPassword.requestFocus();
                } else if (password.length() < 6) {
                    mPassword.setError("Password must be greater than 6 characters");
                    mPassword.requestFocus();
                } else if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(FrontpageActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && password.isEmpty())) {
                    fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(FrontpageActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {

                                Toast.makeText(FrontpageActivity.this, "Unsuccessful logged in.Please try again later", Toast.LENGTH_SHORT).show();
                            } else {

                                Intent intToHome = new Intent(FrontpageActivity.this, LoginActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                } else {
                    Toast.makeText(FrontpageActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(FrontpageActivity.this, RegisterActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(mAuthStateListener);
    }
}
