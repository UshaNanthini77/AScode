package com.example.bookmyroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class FinalUploadActivity extends AppCompatActivity {

    private static final int PICK_FILE = 1;
    private DatabaseReference databaseReference;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
   private StorageReference storageReference;
   private Button mbtn,mbtnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_upload);
        mbtn = (Button)findViewById(R.id.button);
        mbtnnext=(Button)findViewById(R.id.btn_next);
        databaseReference = FirebaseDatabase.getInstance().getReference().child(fAuth.getUid());
        storageReference = FirebaseStorage.getInstance().getReference();
        mbtn.addTextChangedListener(btnTextWatcher);
      //  mbtnnext.setEnabled(false);
      /*  mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // mbtnnext.setEnabled(true);
                Intent i=new Intent(FinalUploadActivity.this, YearActivity.class);
                startActivity(i);
            }
        });*/

        mbtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FinalUploadActivity.this, YearActivity.class);
                startActivity(i);
            }
        });
    }

    private TextWatcher btnTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String transidinput=mbtn.getText().toString().trim();
            mbtnnext.setEnabled(!transidinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void FileUpload(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,PICK_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_FILE){
            if(resultCode == RESULT_OK){


                Uri FileUri = data.getData();
                StorageReference Folder = FirebaseStorage.getInstance().getReference().child("Receipts");
                final StorageReference file_name = Folder.child("file"+FileUri.getLastPathSegment());

                file_name.putFile(FileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        file_name.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("filelink", String.valueOf(uri));

                                databaseReference.child("Your Receipt").setValue(hashMap);
                                Toast.makeText(FinalUploadActivity.this,"File Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        }
    }
}
