package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.DrawFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class CakeListActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_list);

        List<Cake> cakeList = Arrays.asList(
                new Cake(R.drawable.cake_1, AppCompatResources.getDrawable(getApplicationContext(), R.drawable.cake_1)),
                new Cake(R.drawable.cake_2, AppCompatResources.getDrawable(getApplicationContext(), R.drawable.cake_2)),
                new Cake(R.drawable.cake_3, AppCompatResources.getDrawable(getApplicationContext(), R.drawable.cake_3)),
                new Cake(R.drawable.cake_4, AppCompatResources.getDrawable(getApplicationContext(), R.drawable.cake_4)),
                new Cake(R.drawable.cake_5, AppCompatResources.getDrawable(getApplicationContext(), R.drawable.cake_5))
        );

        listView = findViewById(R.id.listView);

        CakeAdapter cakeAdapter = new CakeAdapter(this, R.layout.cake_item, cakeList);
        listView.setAdapter(cakeAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cake selectedCake = (Cake) parent.getItemAtPosition(position);
            Intent intent = new Intent();
            intent.putExtra("selectedCakeId", selectedCake.getId());
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}