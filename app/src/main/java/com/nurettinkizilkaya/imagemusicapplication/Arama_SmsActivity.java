package com.nurettinkizilkaya.imagemusicapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Arama_SmsActivity extends AppCompatActivity {
    TextView nameTxt;
    TextView telTxt;
    Button arama;
    Button sms;
    Intent callIntent;
    static String tel;
    EditText smsTxt;
    static String name;


    @Override
    public void onBackPressed() {
        Intent intent2=new Intent(getApplicationContext(),KisilerActivity.class);
        startActivity(intent2);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama__sms);
        nameTxt=(TextView)findViewById(R.id.name);
        telTxt=(TextView)findViewById(R.id.tel);
        arama=(Button)findViewById(R.id.aramaButton);
        sms=(Button)findViewById(R.id.smsButton);
        smsTxt=(EditText)findViewById(R.id.smsText);


        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        tel=intent.getStringExtra("tel");
        if(name!="" && tel!=""){
            nameTxt.setText(name);
            telTxt.setText(tel);

        }


        if(DuzenleActivity.nameUpdate!="" && DuzenleActivity.telUpdate!=""){

            nameTxt.setText(DuzenleActivity.nameUpdate);
            telTxt.setText(DuzenleActivity.telUpdate);
            DuzenleActivity.nameUpdate="";
            DuzenleActivity.telUpdate="";
        }









        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Arama_SmsActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

                }else{

                    callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+telTxt.getText().toString()));
                    startActivity(callIntent);
                }
            }
        });



        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Arama_SmsActivity.this,new String[]{Manifest.permission.READ_PHONE_STATE},2);

                }else{


                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(telTxt.getText().toString(), null, smsTxt.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();

                    smsTxt.setText("");

                }
            }
        });




    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0){
                if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
                    callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+telTxt.getText().toString()));
                    startActivity(callIntent);
                }
            }
        }

        if(requestCode==2){
            if(grantResults.length>0){
                if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_PHONE_STATE)==PackageManager.PERMISSION_GRANTED){

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(telTxt.getText().toString(), null, smsTxt.getText().toString(), null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.update,menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.update){
            Intent intent=new Intent(getApplicationContext(),DuzenleActivity.class);
            intent.putExtra("name",nameTxt.getText().toString());
            intent.putExtra("tel",telTxt.getText().toString());
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
