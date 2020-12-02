package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrndFlrRoomsActivity extends AppCompatActivity {
    Button mbtnroom101, mbtnsubmit101 , mbtnnext,mbtnmates;
    TextView mtvcount;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase, roomref, dbref;
    ArrayList<String> ds;
   //String arr[]=new String[10];
   // String cuid;
    int count=0;
   // List<String> ds = new ArrayList<String>(Arrays.asList(arr));
    //ArrayList<String> ds = new ArrayList<String>();
    String value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grnd_flr_rooms);
        ds = new ArrayList<>();

        //final List<String> ds = new ArrayList<String>(Arrays.asList(arr));

      //  mbtnroom101 = (Button) findViewById(R.id.btn_roomno101);
        mbtnmates= (Button) findViewById(R.id.button3);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Details").child(fAuth.getUid());
        /*cuid = fAuth.getCurrentUser().getUid();
        roomref = FirebaseDatabase.getInstance().getReference().child("Details");
        roomref.keepSynced(true);*/


        mbtnsubmit101 = (Button) findViewById(R.id.btn_submit);
        mbtnnext = (Button) findViewById(R.id.btn_next);
       // mbtnnext.setEnabled(false);
       // mtvcount = (TextView) findViewById(R.id.tv_count);

       Query query = FirebaseDatabase.getInstance().getReference("Details").orderByChild("Room no").equalTo("101");
       query.addValueEventListener(valueEventListener);

       //query.addListenerForSingleValueEvent(valueEventListener);



       /* roomref.child("Room Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = String.valueOf(dataSnapshot.child("Room no").getValue());
                mtvcount.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/


        mbtnsubmit101.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mbtnnext.setEnabled(true);
              //  Intent intent1 = new Intent(GrndFlrRoomsActivity.this, Room101bed1Activity.class);
              //  startActivity(intent1);
                count = ds.size();
                if (count > 3) {
                    Toast.makeText(getApplicationContext(), "This room is filled", Toast.LENGTH_SHORT).show();
                   // mbtnroom101.setEnabled(false);
                    mbtnsubmit101.setEnabled(false);
                } else {
                    Toast.makeText(getApplicationContext(), "ur room is booked", Toast.LENGTH_SHORT).show();
                    mDatabase.child("Room no").setValue("101");
                    Intent intent = new Intent(GrndFlrRoomsActivity.this, Room101bed1Activity.class);
                    startActivity(intent);

                }

            }
        });

        mbtnmates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mates=new Intent(GrndFlrRoomsActivity.this,Room101roomatesActivity.class);
                startActivity(mates);
            }
        });

       mbtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(GrndFlrRoomsActivity.this, Room101bed1Activity.class);
                startActivity(intent);
          }
       });
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                   //String room = String.valueOf(snapshot.getValue(String.class));
                   ds.add("room");
                }
            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };


}
