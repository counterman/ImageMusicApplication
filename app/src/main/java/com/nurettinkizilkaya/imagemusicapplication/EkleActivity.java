package com.nurettinkizilkaya.imagemusicapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class EkleActivity extends AppCompatActivity {
    EditText name;
    EditText tel;
    ImageView kisiImage;
    Button ekle;
    Bitmap selectedImage;
    static SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekle);
        name=(EditText)findViewById(R.id.isimEditText);
        tel=(EditText)findViewById(R.id.telEditText);
        kisiImage=(ImageView)findViewById(R.id.imageView10);
        ekle=(Button)findViewById(R.id.ekleButton);




        kisiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(EkleActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

                    }else{
                        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent,2);

                    }

            }
        });








    }

    public void insertKisi(View view){
         String isim=name.getText().toString();
         String telefon=tel.getText().toString();

        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        selectedImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] byteArray=outputStream.toByteArray();

        try{
            database=this.openOrCreateDatabase("Kisiler1",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS kisiler1(name VARCHAR, tel VARCHAR,image BLOB)");
            String sqlString="INSERT INTO kisiler1(name,tel,image) VALUES (?,?,?)";
            SQLiteStatement statement=database.compileStatement(sqlString);

            statement.bindString(1,isim);
            statement.bindString(2,telefon);
            statement.bindBlob(3,byteArray);
            statement.execute();


        }catch (Exception e){

            e.printStackTrace();

        }

        Intent intent=new Intent(getApplicationContext(),KisilerActivity.class);
        startActivity(intent);
        finish();


    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0){
                if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                    Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,2);
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==2 && resultCode == RESULT_OK && data!=null){
            Uri image=data.getData();
            try{
                selectedImage=MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
                kisiImage.setImageBitmap(selectedImage);

            }catch (Exception e){
                e.printStackTrace();

            }

        }



        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        Intent s=new Intent(getApplicationContext(),KisilerActivity.class);
        startActivity(s);
        super.onBackPressed();
    }
}
