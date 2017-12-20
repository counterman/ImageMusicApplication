package com.nurettinkizilkaya.imagemusicapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{
    public ImageView imageview1;
    public ImageView imageview2;
    public ImageView imageview3;
    public ImageView imageview4;
    public ImageView imageview5;
    public ImageView imageview6;
    public ImageView imageview7;
    public ImageView imageview8;
    public ImageView imageview9;
    public Object secilenMusic;
    public static int musicSelected;
    GridLayout gridLayout;
    Button button;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_features,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.listele){
            Intent intent=new Intent(getApplicationContext(),KisilerActivity.class);
            startActivity(intent);

        }
        if (item.getItemId()==R.id.webview){
             Intent intent=new Intent(getApplicationContext(),WebWiewActivity.class);
             startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageview1=(ImageView)findViewById(R.id.imageView1);
        imageview2=(ImageView)findViewById(R.id.imageView2);
        imageview3=(ImageView)findViewById(R.id.imageView3);
        imageview4=(ImageView)findViewById(R.id.imageView4);
        imageview5=(ImageView)findViewById(R.id.imageView5);
        imageview6=(ImageView)findViewById(R.id.imageView6);
        imageview7=(ImageView)findViewById(R.id.imageView7);
        imageview8=(ImageView)findViewById(R.id.imageView8);
        imageview9=(ImageView)findViewById(R.id.imageView9);
        button=(Button)findViewById(R.id.button1);


        button.setVisibility(View.INVISIBLE);
        View.OnClickListener clickListener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView=(ImageView)view;


                startService(new Intent(MainActivity.this,MyService.class));
                button.setVisibility(View.VISIBLE);
                secilenMusic=imageView.getTag();
                musicSelected=Integer.parseInt(secilenMusic.toString());
                System.out.println("secilenmusic= "+secilenMusic.toString());
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this,MyService.class));
                button.setVisibility(View.INVISIBLE);

            }
        });


        imageview1.setOnClickListener(clickListener);
        imageview2.setOnClickListener(clickListener);
        imageview3.setOnClickListener(clickListener);
        imageview4.setOnClickListener(clickListener);
        imageview5.setOnClickListener(clickListener);
        imageview6.setOnClickListener(clickListener);
        imageview7.setOnClickListener(clickListener);
        imageview8.setOnClickListener(clickListener);
        imageview9.setOnClickListener(clickListener);









    }






}
