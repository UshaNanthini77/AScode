package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class YearActivity extends AppCompatActivity {
    Button mbtn1,mbtn2,mbtn3;
    private Spinner msp1,msp2a,msp2b,msp3,msp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year);
       // mbtn1=(Button)findViewById(R.id.btn_1);
        mbtn2=(Button)findViewById(R.id.btn_2);
        mbtn3=(Button)findViewById(R.id.btn_3);
       msp4=findViewById(R.id.sp4);
        List<String> categories4 = new ArrayList<>();
        categories4.add(0,"Choose your floor");
        categories4.add("Ground floor");
        categories4.add("1st floor");
        categories4.add("2nd floor");
        ArrayAdapter<String> dataAdapter4;
        dataAdapter4 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories4);
        dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        msp4.setAdapter(dataAdapter4);
        msp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose your floor")){

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    if(parent.getItemAtPosition(position).equals("Ground floor")){
                        Intent i1=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i1);
                    }
                    if(parent.getItemAtPosition(position).equals("1st floor")){
                        Intent i2=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i2);
                    }
                    if(parent.getItemAtPosition(position).equals("2nd floor")){
                        Intent i3=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i3);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });







        msp3=findViewById(R.id.sp3);
        List<String> categories3 = new ArrayList<>();
        categories3.add(0,"Choose your floor");
        categories3.add("Ground floor");
        categories3.add("1st floor");
        categories3.add("2nd floor");
        ArrayAdapter<String> dataAdapter3;
        dataAdapter3 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories3);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        msp3.setAdapter(dataAdapter3);
        msp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose your floor")){

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    if(parent.getItemAtPosition(position).equals("Ground floor")){
                        Intent i1=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i1);
                    }
                    if(parent.getItemAtPosition(position).equals("1st floor")){
                        Intent i2=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i2);
                    }
                    if(parent.getItemAtPosition(position).equals("2nd floor")){
                        Intent i3=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i3);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });






        msp2b=findViewById(R.id.sp2b);
        List<String> categories2 = new ArrayList<>();
        categories2.add(0,"Main Hostel");
        categories2.add("Ground floor");
        categories2.add("1st floor");
        categories2.add("2nd floor");
        ArrayAdapter<String> dataAdapter2;
        dataAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        msp2b.setAdapter(dataAdapter2);
        msp2b.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Main Hostel")){

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    if(parent.getItemAtPosition(position).equals("Ground floor")){
                        Intent i1=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i1);
                    }
                    if(parent.getItemAtPosition(position).equals("1st floor")){
                        Intent i2=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i2);
                    }
                    if(parent.getItemAtPosition(position).equals("2nd floor")){
                        Intent i3=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i3);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });






        msp2a=findViewById(R.id.sp2a);
        List<String> categories1 = new ArrayList<>();
        categories1.add(0,"New Hostel");
        categories1.add("Ground floor");
        categories1.add("1st floor");
        categories1.add("2nd floor");
        ArrayAdapter<String> dataAdapter1;
        dataAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories1);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        msp2a.setAdapter(dataAdapter1);
        msp2a.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("New Hostel")){

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    if(parent.getItemAtPosition(position).equals("Ground floor")){
                        Intent i1=new Intent(YearActivity.this,Floor2Activity.class);
                        startActivity(i1);
                    }
                    if(parent.getItemAtPosition(position).equals("1st floor")){
                        Intent i2=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i2);
                    }
                    if(parent.getItemAtPosition(position).equals("2nd floor")){
                        Intent i3=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i3);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });







        msp1=findViewById(R.id.sp1);
        List<String> categories = new ArrayList<>();
        categories.add(0,"Choose your floor");
        categories.add("2nd floor");
        categories.add("3rd floor");
        categories.add("4th floor");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        msp1.setAdapter(dataAdapter);
        msp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).equals("Choose your floor")){

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();
                    if(parent.getItemAtPosition(position).equals("2nd floor")){
                        Intent i1=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i1);
                    }
                    if(parent.getItemAtPosition(position).equals("3rd floor")){
                        Intent i2=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i2);
                    }
                    if(parent.getItemAtPosition(position).equals("4th floor")){
                        Intent i3=new Intent(YearActivity.this,FloorActivity.class);
                        startActivity(i3);
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        mbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(YearActivity.this,NeworMainActivity.class);
                startActivity(i2);
            }
        });




    }
}
