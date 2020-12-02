package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Room101Activity extends AppCompatActivity {
    Button mbtn1,mbtn2;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room101);
        mbtn1=(Button)findViewById(R.id.btn_1);
        mbtn2=(Button)findViewById(R.id.btn_2);
        mbtn1.setClickable(false);
        mDatabase= FirebaseDatabase.getInstance().getReference(fAuth.getUid());
        //mbtn1.setEnabled(false);
       // mbtn1.setBackgroundResource(R.drawable.ic_exit_to_app_black_24dp);
       mbtn2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              v.setEnabled(false);
                mDatabase.child("Room no").child("Roomn o").setValue("101");
            }
        });
        mbtn1.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                v.setEnabled(false);
                mDatabase.child("Room no").child("Roomn o").setValue("103");
            }
        });
    }

}
