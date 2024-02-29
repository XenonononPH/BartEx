package com.example.bartex;

import android.util.DisplayMetrics;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Rates extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);
        getSupportActionBar().hide();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int w = dm.widthPixels;
        int h = dm.heightPixels;

        getWindow().setLayout((int) (w * .8), (int) (h * .65));
    }
}