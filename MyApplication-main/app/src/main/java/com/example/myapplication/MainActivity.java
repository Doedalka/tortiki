package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kyanogen.signatureview.SignatureView;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {
    private static final int INITIAL_SEEKBAR_VALUE = 5;
    private static final int MIN_SEEKBAR_VALUE = 1;
    private static final int MAX_SEEKBAR_VALUE = 100;
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;
    SignatureView signatureView;
    ImageButton btnEraser, btnColor, btnPickCake;
    SeekBar seekbarPenSize;
    TextView txtPenSize;
    int defaultColor;
    int selectedCakeId = 0;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (selectedCakeId != 0) {
            inflateCake();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signatureView = findViewById(R.id.signature_view);
        btnColor = findViewById(R.id.btnColor);
        btnEraser = findViewById(R.id.btnEraser);
        btnPickCake = findViewById(R.id.btnPickCake);
        seekbarPenSize = findViewById(R.id.seekbarPenSize);
        txtPenSize = findViewById(R.id.txtPenSize);

        defaultColor = ContextCompat.getColor(MainActivity.this, R.color.black);

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        seekbarPenSize.setMin(MIN_SEEKBAR_VALUE);
        seekbarPenSize.setMax(MAX_SEEKBAR_VALUE);
        seekbarPenSize.setProgress(INITIAL_SEEKBAR_VALUE);
        txtPenSize.setText(String.valueOf(INITIAL_SEEKBAR_VALUE));
        seekbarPenSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                signatureView.setPenSize(i);
                txtPenSize.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


        btnEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateCake();
            }
        });

        btnPickCake.setOnClickListener(v -> {
            Intent intent = new Intent(this, CakeListActivity.class);


            startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY && resultCode == RESULT_OK && data != null) {
            // Получение данных из SecondActivity
            selectedCakeId = data.getIntExtra("selectedCakeId", 0);

        }

    }

    private void openColorPicker() {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultColor = color;
                signatureView.setPenColor(color);

            }
        });
        ambilWarnaDialog.show();
    }

    private void inflateCake() {
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), selectedCakeId);
        int w = signatureView.getWidth();
        Bitmap mutableBitmap = originalBitmap.copy(Bitmap.Config.RGB_565, true);

        signatureView.setBitmap(Bitmap.createScaledBitmap(mutableBitmap, signatureView.getWidth(), signatureView.getHeight(), false));
    }
}