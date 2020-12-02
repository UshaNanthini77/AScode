package com.example.bookmyroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GirlsFeesLinkActivity extends AppCompatActivity {

    Button mbtnnext;
    private EditText ettransid;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girls_fees_link);
        mbtnnext = (Button)findViewById(R.id.btn_next);
        ettransid=(EditText)findViewById(R.id.et_transid);
        ettransid.addTextChangedListener(idTextWatcher);

        mbtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextbutton = new Intent(GirlsFeesLinkActivity.this, FinalUploadActivity.class);
                startActivity(nextbutton);
            }
        });
    }
    private TextWatcher idTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String transidinput=ettransid.getText().toString().trim();
            mbtnnext.setEnabled(!transidinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

        public void browser1(View View)
        {
            Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://eazypay.icicibank.com"));
            startActivity(browserIntent);
        }



    }

