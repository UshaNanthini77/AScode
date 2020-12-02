package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText mName,mRollNo,mRegNo,mEmail,mPassword,mPhone;
    Button mRegisterbtn;
    TextView mLoginnTxt;
    DatabaseReference reff,myRef;
    String email,password,name,regno;
    int rollno;
    Long phoneno;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    StudentDetails sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName=findViewById(R.id.et_name);
        mRollNo=findViewById(R.id.et_rollno);
        mRegNo=findViewById(R.id.et_regno);
        mEmail=findViewById(R.id.et_email);
        mPassword=findViewById(R.id.et_password);
        mPhone=findViewById(R.id.et_phone);
        mRegisterbtn=findViewById(R.id.btn_register);
        mLoginnTxt=findViewById(R.id.tv_3);
       // StudentDetails sd=new StudentDetails(name,regno,rollno,email,password,phoneno);
        //reff= FirebaseDatabase.getInstance().getReference().child(fAuth.getUid());




        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=mEmail.getText().toString().trim();
                password=mPassword.getText().toString().trim();
                name=mName.getText().toString().trim();
                regno=mRegNo.getText().toString().trim();
                rollno=Integer.parseInt(mRollNo.getText().toString().trim());
                phoneno=Long.parseLong(mPhone.getText().toString().trim());

              // sd.setEmailID(email);
                //sd.setName(name);
               // sd.setRegno(regno);
               // sd.setPassword(password);
                //sd.setPhoneno(phoneno);
               // sd.setRollno(rollno);

                //reff.push().setValue(sd);
                Toast.makeText(RegisterActivity.this,"data inserted successfully",Toast.LENGTH_SHORT).show();
                if(email.isEmpty()){
                    mEmail.setError("Email is required");
                    mEmail.requestFocus();
                }
                else if(password.isEmpty()){
                    mPassword.setError("Password is required");
                    mPassword.requestFocus();
                }

                else if(password.length()<6){
                    mPassword.setError("Password must be greater than 6 characters");
                    mPassword.requestFocus();
                }
                else if(email.isEmpty()&&password.isEmpty()){
                    Toast.makeText(RegisterActivity.this,"Fields are empty.", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty()&&password.isEmpty())) {

                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Signup unsuccessful.Try again", Toast.LENGTH_SHORT).show();
                            } else {
                                sendUserData();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        mLoginnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }


    private void sendUserData(){
        myRef = firebaseDatabase.getReference().child("Details");
        //myRef = firebaseDatabase.getReference(fAuth.getUid()).child("Students Details");
       // DatabaseReference myRef=firebaseDatabase.getReference().child(fAuth.getUid());
        StudentDetails sd=new StudentDetails(name,regno,rollno,email,password,phoneno);
        myRef.child(fAuth.getUid()).child("Student Details").setValue(sd);
    }
}
