package com.nurettinkizilkaya.imagemusicapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DuzenleActivity extends AppCompatActivity {
    EditText nametxt;
    EditText teltxt;
    Button update;
    String temp;
    static String nameUpdate="";
    static String telUpdate="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duzenle);

        nametxt=(EditText)findViewById(R.id.nameEditText);
        teltxt=(EditText)findViewById(R.id.telEditText);
        update=(Button)findViewById(R.id.UpdateButton);





        final Intent intent=getIntent();
        temp=intent.getStringExtra("tel");
        nametxt.setText(intent.getStringExtra("name"));
        teltxt.setText(intent.getStringExtra("tel"));


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameUpdate=nametxt.getText().toString();
                telUpdate=teltxt.getText().toString();
                ContentValues data=new ContentValues();
                data.put("name",nametxt.getText().toString());
                data.put("tel",teltxt.getText().toString());
                Arama_SmsActivity.name="";
                Arama_SmsActivity.tel="";
                EkleActivity.database.update("kisiler1",data,"tel=?",new String[]{temp});
                Toast.makeText(getApplicationContext(),"Güncellendi..",Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(getApplicationContext(),Arama_SmsActivity.class);


                startActivity(intent1);
                finish();

            }
        });

    }
}
