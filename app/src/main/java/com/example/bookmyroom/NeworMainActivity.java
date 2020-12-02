package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NeworMainActivity extends AppCompatActivity {
    Button mbtn1,mbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newor_main);

        mbtn1=(Button)findViewById(R.id.btn_1);
        mbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(NeworMainActivity.this,Floor2Activity.class);
                startActivity(i);
            }
        });
    }
}
