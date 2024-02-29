package com.example.bartex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class History extends AppCompatActivity {
Set<String> ihniwti = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().hide();
        final SharedPreferences info = getSharedPreferences("List", MODE_PRIVATE);
        final SharedPreferences.Editor editor = info.edit();

        List<String> Persist = new ArrayList<>();

        ImageButton back = findViewById(R.id.Back);
        Button clear = findViewById(R.id.del);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bk = new Intent(History.this, MainActivity.class);
                startActivity(bk);
            }
        });

        ihniwti = info.getStringSet("History", null);
        List<String> storeList = new ArrayList<>();

        if (ihniwti == null){
            storeList.add("");
        }
        else {
            for (String a: ihniwti){
                storeList = Arrays.asList(String.valueOf(ihniwti).split(","));
            }
        }

        Persist.addAll(storeList);

        ListView list = findViewById(R.id.historyList);

        ArrayAdapter<String> adapt = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Persist);
        list.setAdapter(adapt);

        final List<String> finalPersist = Persist;
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.putString("", String.valueOf(ihniwti));
                editor.apply();

                finalPersist.removeAll(finalPersist);

                Toast.makeText(getApplicationContext(), "History Deleted. Please exit History.", Toast.LENGTH_LONG).show();
            }
        });
    }
}