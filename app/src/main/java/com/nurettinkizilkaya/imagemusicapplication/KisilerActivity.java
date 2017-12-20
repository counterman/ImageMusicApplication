package com.nurettinkizilkaya.imagemusicapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class KisilerActivity extends AppCompatActivity {
    static ArrayList<String> names;
    static ArrayList<String> tels;
    static ArrayList<Bitmap> images;

    ListView kisiler;

    KisiClass adapter;

    @Override
    public void onBackPressed() {
        Intent intent3=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent3);
        finish();

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisiler);
        names=new ArrayList<String>();
        tels=new ArrayList<String>();
        images=new ArrayList<Bitmap>();



        kisiler=(ListView)findViewById(R.id.kisiler);

        adapter=new KisiClass(names,tels,images,this);
        kisiler.setAdapter(adapter);


       kisiler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent=new Intent(getApplicationContext(),Arama_SmsActivity.class);
              intent.putExtra("name", names.get(i));
              intent.putExtra("tel",tels.get(i));
              startActivity(intent);
              //finish();

           }
       });

       kisiler.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

               new AlertDialog.Builder(KisilerActivity.this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Delete")
                       .setMessage("Are you sure you want to delete")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               try{

                                   EkleActivity.database.delete("kisiler1","tel=?",new String[]{tels.get(i)});
                                   getData();
                                   adapter.notifyDataSetInvalidated();

                               }catch (Exception e){
                                   e.printStackTrace();
                               }
                           }
                       }).setNegativeButton("No", null).show();


               return true;
           }
       });

       getData();







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_kisi,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.kisiekle){
            Intent intent=new Intent(getApplicationContext(),EkleActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


    public void getData(){

        clearArrayList();
        try{
            EkleActivity.database=this.openOrCreateDatabase("Kisiler1",MODE_PRIVATE,null);
            EkleActivity.database.execSQL("CREATE TABLE IF NOT EXISTS kisiler1(name VARCHAR,tel VARCHAR,image BLOB)");
            //EkleActivity.database.execSQL("DROP TABLE Kisiler.kisiler");

            Cursor cursor=EkleActivity.database.rawQuery("SELECT * FROM kisiler1",null);

            int nameIx=cursor.getColumnIndex("name");
            int telIx=cursor.getColumnIndex("tel");
            int imageIx=cursor.getColumnIndex("image");

            cursor.moveToFirst();
            while(cursor!=null){



                names.add(cursor.getString(nameIx));
                System.out.println("name: "+cursor.getString(nameIx));
                tels.add(cursor.getString(telIx));
                System.out.println("tel: "+cursor.getString(telIx));
                byte[] byteArray=cursor.getBlob(imageIx);
                System.out.println("Image: "+cursor.getBlob(imageIx));
                Bitmap image= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
                images.add(image);
                cursor.moveToNext();

                adapter.notifyDataSetChanged();




            }


        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public void clearArrayList(){
        names.clear();
        tels.clear();
        images.clear();

    }


}
