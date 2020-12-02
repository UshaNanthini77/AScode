package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class newattendanceActivity extends AppCompatActivity {
    private Button btnTake;
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newattendance);
        btnTake=(Button)findViewById(R.id.btntake);
        btnShow=(Button)findViewById(R.id.btnshow);

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(newattendanceActivity.this,takeattendanceActivity.class);
                startActivity(i);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(newattendanceActivity.this,showattendanceActivity.class);
                startActivity(j);

            }
        });
    }

}
