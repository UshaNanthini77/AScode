package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Room101roomatesActivity extends AppCompatActivity {

   ArrayList<String> ds1;
   Query dbdetails;
    TextView mtvnames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room101roomates);

        ds1 = new ArrayList<>();
        mtvnames = (TextView)findViewById(R.id.tv1);
       // dbdetails = FirebaseDatabase.getInstance().getReference("Details").child(fA).child("Student Details").child("Room no").equalTo("101");
        //dbdetails.addValueEventListener(valueEventListener1);

        Query query1 = FirebaseDatabase.getInstance().getReference("Details").orderByChild("Room no").equalTo(101);
        query1.addValueEventListener(valueEventListener1);

    }

    ValueEventListener valueEventListener1= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot1 : dataSnapshot.getChildren()){
                    String name=  String.valueOf(snapshot1.getValue());
                   // String name1= snapshot1.child("Name").getValue().toString();
                   // mtvnames.setText(name);
                    ds1.add(name);


                for (int i=0; i<ds1.size(); i++){
                    mtvnames.setText(ds1.get(i));
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
