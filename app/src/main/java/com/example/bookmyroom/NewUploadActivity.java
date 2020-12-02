package com.example.bookmyroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;

public class NewUploadActivity extends AppCompatActivity {

    private  static  final  int PICK_FILE=1;
    ArrayList<Uri> FileList=new ArrayList<Uri>();
    ProgressDialog progressDialog;
    private StorageReference folder;
    FirebaseAuth fAuth = FirebaseAuth.getInstance();
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_upload);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing Please wait......");
        folder = FirebaseStorage.getInstance().getReference();
        mDatabase=FirebaseDatabase.getInstance().getReference(fAuth.getUid());
    }

    public void ChooseFiles(View view) {

        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,PICK_FILE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_FILE)
        {
            if(resultCode==RESULT_OK){
                if(data.getClipData()!=null){
                    int count = data.getClipData().getItemCount();
                    int i=0;
                    if (i<count) {

                        Uri File = data.getClipData().getItemAt(i).getUri();
                        FileList.add(File);
                        i++;
                    }
                    Toast.makeText(this, "You have selected files", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    public void UploadFiles(View view) {
        progressDialog.show();
        Toast.makeText(this,"if takes time, you can press again",Toast.LENGTH_SHORT).show();

        for (int j=0;j<FileList.size();j++){
            Uri PerFile=FileList.get(j);
           StorageReference folder = FirebaseStorage.getInstance().getReference().child("Files");
           final StorageReference filename=folder.child("file"+PerFile.getLastPathSegment());
           filename.putFile(PerFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   filename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                           mDatabase= FirebaseDatabase.getInstance().getReference().child("Uploaded Receipt");
                           HashMap<String,String> hashMap=new HashMap<>();
                           hashMap.put("Link",String.valueOf(uri));
                           mDatabase.push().setValue(hashMap);
                           progressDialog.dismiss();
                           FileList.clear();
                       }
                   });
               }
           });

        }
    }
}
