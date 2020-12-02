package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Floor2Activity extends AppCompatActivity {
    Button mbtn1,mbtn2,mbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor2);

        mbtn1=(Button)findViewById(R.id.btn_1);
        mbtn2=(Button)findViewById(R.id.btn_2);
        mbtn3=(Button)findViewById(R.id.btn_3);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Floor2Activity.this,GrndFlrRoomsActivity.class);
                startActivity(i);
            }
        });
        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(Floor2Activity.this,Floor1RoomsActivity.class);
            }
        });
        mbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k=new Intent(Floor2Activity.this,Floor2RoomsActivity.class);
                startActivity(k);
            }
        });
    }
}
