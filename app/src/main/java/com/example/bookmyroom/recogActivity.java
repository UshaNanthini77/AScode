package com.example.bookmyroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
//import com.google.firebase.ml.vision.text.FirebaseVisionTextDetector;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;

import java.io.IOException;
import java.util.List;

public class recogActivity extends AppCompatActivity {
    private Button snapBtn;
    private Button detectBtn;
    private ImageView imageView;
    private TextView textView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recog);
        imageView = (ImageView)findViewById(R.id.imageView);
        textView = (TextView)findViewById(R.id.textView);
      /*  snapBtn=findViewById(R.id.snapBtn);
        detectBtn=findViewById(R.id.detectBtn);
        imageView=findViewById(R.id.imageView);
        txtView=findViewById(R.id.txtView);
       snapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
              //  pick_image();
               // txtView.setText("");

            }
        });
        detectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                       detectTxt();
            }
        });*/
    }
    public void detect(View v){
        if(bitmap == null){
            Toast.makeText(getApplicationContext(),"Bitmap is null", Toast.LENGTH_LONG).show();
        }
        else {
            FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
            FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
            detector.processImage(firebaseVisionImage).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                @Override
                public void onSuccess(FirebaseVisionText firebaseVisionText) {
                    for (FirebaseVisionText.TextBlock block:firebaseVisionText.getTextBlocks()){
                        //process_text(firebaseVisionText);
                        //String blockText = block.getText();
                        //textView.append(blockText+"\n\n");
                        textprocess(firebaseVisionText);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }
    }
    private void textprocess(FirebaseVisionText firebaseVisionText){
        List<FirebaseVisionText.TextBlock> blocks = firebaseVisionText.getTextBlocks();
        if(blocks.size() == 0){
            Toast.makeText(getApplicationContext(),"No text detected",Toast.LENGTH_LONG).show();
        }
        else{
            for(int i=0; i<blocks.size();i++){
                List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
                for (int j=0; j<lines.size();j++){
                    List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                    for (int k=0; k<elements.size();k++){
                        textView.append(elements.get(k).getText());
                        textView.append("\n");
                    }
                }
            }
        }
    }

    public void pick_image(View v){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try{
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    //   static final int REQUEST_IMAGE_CAPTURE = 1;

  /*  public void pick_image(View v)
    {
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            try{
               Bitmap bitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }*/



  /*  private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
    private void detectTxt(){
        FirebaseVisionImage image=FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer detector=FirebaseVision.getInstance().getOnDeviceTextRecognizer();
        detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                //processTxt(firebaseVisionText);
                List<FirebaseVisionText.TextBlock> blocks=firebaseVisionText.getTextBlocks();
                // txtView.setText(blocks.size());
                if(blocks.size()==0)
                {
                    Toast.makeText(recogActivity.this,"No text",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    for (int i=0;i<blocks.size();i++) {
                        List<FirebaseVisionText.Line> lines=blocks.get(i).getLines();
                        for(int j=0;j<lines.size();j++)
                        {
                          List<FirebaseVisionText.Element> elements=lines.get(j).getElements();
                          for(int k=0;k<elements.size();k++) {
                              txtView.append(elements.get(k).getText());
                              txtView.append("\n");
                              //  txtView.setText(txt);

                          }
                        }
                    }

                }



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(recogActivity.this,"Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                Log.d("Error",e.getMessage());

            }
        });
    }
  /*  private void processTxt(FirebaseVisionText text){
        List<FirebaseVisionText.TextBlock> blocks=text.getTextBlocks();
       // txtView.setText(blocks.size());
        if(blocks.size()==0)
        {
            Toast.makeText(recogActivity.this,"No text",Toast.LENGTH_LONG).show();
            return;
        }


        else {

            for (FirebaseVisionText.TextBlock block : text.getTextBlocks()) {

                String txt = block.getText();
               // StringBuilder result=new StringBuilder();
              //  result.append(txt);
             //   txtView.setText(result);
              //  txtView.setTextSize(24);
               // result.append(txt);
                txtView.append(txt);
                txtView.setText(txt);
            }

        }
      else{
            for (FirebaseVisionText.TextBlock block : text.getTextBlocks()) {

                String txt = blocks.getText();
               // StringBuilder result=new StringBuilder();
              //  result.append(txt);
             //   txtView.setText(result);
              //  txtView.setTextSize(24);
               // result.append(txt);
                txtView.append(txt);
                txtView.setText(txt);
            }

        }


     /* else{
            String resultText = text.getText();
            for (FirebaseVisionText.TextBlock block: text.getTextBlocks()) {
                String blockText = block.getText();
                Float blockConfidence = block.getConfidence();
                List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();
                Point[] blockCornerPoints = block.getCornerPoints();
                Rect blockFrame = block.getBoundingBox();
                txtView.setText(blockText);
                for (FirebaseVisionText.Line line: block.getLines()) {
                    String lineText = line.getText();
                    Float lineConfidence = line.getConfidence();
                    List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                    Point[] lineCornerPoints = line.getCornerPoints();
                    Rect lineFrame = line.getBoundingBox();
                    txtView.setText(lineText);
                    for (FirebaseVisionText.Element element: line.getElements()) {
                        String elementText = element.getText();
                        Float elementConfidence = element.getConfidence();
                        List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                        Point[] elementCornerPoints = element.getCornerPoints();
                        Rect elementFrame = element.getBoundingBox();
                        txtView.append(elementText);
                        txtView.setText(elementText);
                       // txtView.setText(resultext);
                    }
                }
            }
        }












    }*/
}
