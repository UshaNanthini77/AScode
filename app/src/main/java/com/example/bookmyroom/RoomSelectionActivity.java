package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomSelectionActivity extends AppCompatActivity {

    Button mbtn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        mbtn1 = (Button)findViewById(R.id.btn201);

        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(RoomSelectionActivity.this,FloorActivity.class);
                startActivity(i);
            }
        });
    }
}
