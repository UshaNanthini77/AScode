package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HostelActivity extends AppCompatActivity {
    private TextView mFirebaseTv,mFirebaseTv2;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
        mFirebaseTv=(TextView)findViewById(R.id.tv2);
        mFirebaseTv2=(TextView)findViewById(R.id.tv3);
        mDatabase=FirebaseDatabase.getInstance().getReference().child("Details").child(fAuth.getUid());
        mFirebaseTv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("Hostel Details").child("Hostel").setValue("Boys Hostel");
                Intent boyshostel =  new Intent(HostelActivity.this,BoysroomActivity.class);
                startActivity(boyshostel);

            }
        });

        mFirebaseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child("Hostel Details").child("Hostel").setValue("Girls Hostel");
                Intent girlshostel =  new Intent(HostelActivity.this,LadiesroomActivity.class);
                startActivity(girlshostel);

            }
        });
    }
}
