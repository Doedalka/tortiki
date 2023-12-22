package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Cake implements Serializable {
    private int id;
    private Drawable cake;

    public Cake(int id, Drawable cake) {
        this.id = id;
        this.cake = cake;
    }

    public Drawable getCake() {
        return cake;
    }

    public int getId() {
        return id;
    }
}
