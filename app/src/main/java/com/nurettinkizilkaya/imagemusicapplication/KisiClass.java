package com.nurettinkizilkaya.imagemusicapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 16.12.2017.
 */

public class KisiClass extends ArrayAdapter<String> {
    private final ArrayList<String> isim;
    private final ArrayList<String> tel;
    private final ArrayList<Bitmap> resim;

    public View customView;

    private final Activity context;
    KisiClass(ArrayList<String> isim,ArrayList<String> tel,ArrayList<Bitmap> resim,Activity context){
        super(context,R.layout.custom_listview,tel);
        this.isim=isim;
        this.tel=tel;
        this.resim=resim;
        this.context=context;

    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=context.getLayoutInflater();
        customView=layoutInflater.inflate(R.layout.custom_listview,null,true);

        TextView isimText=(TextView)customView.findViewById(R.id.name);
        TextView telText=(TextView)customView.findViewById(R.id.tel);
        ImageView imageView=(ImageView)customView.findViewById(R.id.imageView);


        isimText.setText(isim.get(position));
        telText.setText(tel.get(position));
        imageView.setImageBitmap(resim.get(position));

        return customView;
    }








}
