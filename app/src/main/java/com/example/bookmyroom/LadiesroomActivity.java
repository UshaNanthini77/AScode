package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LadiesroomActivity extends AppCompatActivity {

    private TextView mTv2,mTv3;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladiesroom);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Details").child(fAuth.getUid());

        mTv2 = (TextView)findViewById(R.id.tv2);

        mTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("Room Type").child("Type").setValue("Room");
                Intent girlsroom = new Intent(LadiesroomActivity.this,GirlsRoomfeesActivity.class);
                startActivity(girlsroom);
            }
        });
    }
}
