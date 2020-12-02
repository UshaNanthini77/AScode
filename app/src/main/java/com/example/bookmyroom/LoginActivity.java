package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    TextView mtv1,mtv2,mtv3,mtv4,mtv5;
    Button mbtnback;
   // private DrawerLayout mDrawerLayout;
    //private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      // mDrawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        //mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        //mDrawerLayout.addDrawerListener(mToggle);
      // mToggle.syncState();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mtv2=findViewById(R.id.tv2);
        mtv3=findViewById(R.id.tv3);
        mtv1=findViewById(R.id.tv1);
        mtv4=findViewById(R.id.tv4);
        mbtnback = (Button)findViewById(R.id.btn_back);
        mbtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        mtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent book= new Intent(LoginActivity.this,HostelActivity.class);
                startActivity(book);
            }
        });

        mtv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent faci= new Intent(LoginActivity.this,FacilityActivity.class);
                startActivity(faci);
            }
        });
        mtv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rule= new Intent(LoginActivity.this,RulesActivity.class);
                startActivity(rule);
            }
        });
        mtv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gal= new Intent(LoginActivity.this,GalleryActivity.class);
                startActivity(gal);
            }
        });


    }
 //  @Override
   // public boolean onOptionsItemSelected(@NonNull MenuItem item) {
     //   if(mToggle.onOptionsItemSelected(item)){
       //     return true;
        //}
        //return super.onOptionsItemSelected(item);
    //}
}
