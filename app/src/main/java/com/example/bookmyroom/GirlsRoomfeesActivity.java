package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GirlsRoomfeesActivity extends AppCompatActivity {


    Button mbtnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_roomfees);

        mbtnnext=(Button)findViewById(R.id.btn_next);

        mbtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent girlsfees = new Intent(GirlsRoomfeesActivity.this,GirlsFeesLinkActivity.class);
                startActivity(girlsfees);

            }
        });
    }
}
