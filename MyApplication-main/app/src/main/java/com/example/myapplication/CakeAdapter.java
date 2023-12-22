package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.nio.channels.CancelledKeyException;
import java.util.List;

public class CakeAdapter extends ArrayAdapter<Cake> {
    private LayoutInflater inflater;
    private List<Cake> cakeList;
    private int layout;

    public CakeAdapter(@NonNull Context context, int resource, List<Cake> cakeList) {
        super(context, resource, cakeList);
        this.cakeList = cakeList;
        layout = resource;
        inflater = LayoutInflater.from(context);
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(layout, parent, false);

        ImageView imageView = view.findViewById(R.id.cakeImage);
        Cake cake = cakeList.get(position);

        imageView.setImageDrawable(cake.getCake());

        return view;
    }
}
