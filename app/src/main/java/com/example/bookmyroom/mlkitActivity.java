package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
//import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.IOException;

public class mlkitActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlkit);
        textView=findViewById(R.id.text);
        button=findViewById(R.id.selectImage);
        imageView=findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().start(mlkitActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result=CropImage.getActivityResult(data);
            if(requestCode==RESULT_OK){
                if(result!=null){
                    Uri uri=result.getUri();
                    imageView.setImageURI(uri);
                    try{
                        textView.setText("");
                        extractTextFromImage(uri);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private void extractTextFromImage(Uri uri)throws IOException{
        FirebaseVisionImage image=FirebaseVisionImage.fromFilePath(mlkitActivity.this,uri);
        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>(){
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                for(FirebaseVisionText.TextBlock block:firebaseVisionText.getTextBlocks()){
                    String blocktext=block.getText();
                    textView.append(blocktext+"\n\n");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
