package com.example.bartex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Double Amount;
    private int getId1;
    private int getId2;
    private String Currentcy;
    private String Currency;
    private Double Converted;
    public String sendHistory;
    private ArrayList<String> Arrays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ImageButton Ay = findViewById(R.id.ins);
        Ay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Instructions.class));
            }
        });

        Button Ra = findViewById(R.id.rate);
        Ra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Rates.class));
            }
        });

        Button btn = findViewById(R.id.Swap);
        final Spinner dropdown = findViewById(R.id.spinOne);
        String[] option = getResources().getStringArray(R.array.Currencies);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, option);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getId1 = parent.getSelectedItemPosition();
                if (getId1 == 1){
                    Currency = " AED";
                }
                if (getId1 == 2){
                    Currency = " GBP";
                }
                if (getId1 == 3){
                    Currency = " USD";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner dropdown2 = findViewById(R.id.spinTwo);
        String[] option2 = getResources().getStringArray(R.array.Currencies);
        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, option2);
        dropdown2.setAdapter(adapter2);

        dropdown2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getId2 = parent.getSelectedItemPosition();
                if (getId2 == 1){
                    Currentcy = " AED";
                }
                if (getId2 == 2){
                    Currentcy = " GBP";
                }
                if (getId2 == 3){
                    Currentcy = " USD";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1 = getId1;
                int n2 = getId2;

                dropdown.setSelection(n2);
                dropdown2.setSelection(n1);
            }
        });
        Con();

        Button his = findViewById(R.id.history);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent his = new Intent(MainActivity.this, History.class);
                startActivity(his);
            }
        });
    }

    private void Con() {
        final TextView set = findViewById(R.id.Answer);
        Button btn = findViewById(R.id.Converter);

        final EditText money = findViewById(R.id.ConvertMoney);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!money.getText().toString().equals("")) {
                    String val = money.getText().toString();
                    Amount = Double.valueOf(val);
                }
                else {
                    money.setError("Invalid. Please select an amount.");
                }

                if (getId1 == 0 || getId2 == 0){
                    Toast.makeText(getApplicationContext(), "Invalid. Please select a currency.", Toast.LENGTH_LONG).show();
                }
                if (getId1 == getId2){
                    Toast.makeText(getApplicationContext(), "Invalid. Please select another different currency.", Toast.LENGTH_LONG).show();
                }
                if (getId1 == 1 && getId2 == 2) {
                    double op = 1 * Amount * 0.20;
                    Converted = op;
                    PrefMode();
                    set.setText(op + Currentcy);
                }
                if (getId1 == 1 && getId2 == 3) {
                    double op = 1 * Amount * 0.27;
                    Converted = op;
                    PrefMode();
                    set.setText(op + Currentcy);
                }
                if (getId1 == 2 && getId2 == 1) {
                    double op = 1 * Amount * 4.94;
                    Converted = op;
                    PrefMode();
                    set.setText(op + Currentcy);
                }
                if (getId1 == 2 && getId2 == 3) {
                    double op = 1 * Amount * 1.34;
                    Converted = op;
                    PrefMode();
                    set.setText(op + Currentcy);
                }
                if (getId1 == 3 && getId2 == 1) {
                    double op = 1 * Amount * 3.67;
                    Converted = op;
                    PrefMode();
                    set.setText(op + Currentcy);
                }
                if (getId1 == 3 && getId2 == 2) {
                    double op = 1 * Amount * 0.74;
                    Converted = op;
                    PrefMode();
                    set.setText(op + Currentcy);
                }
            }
        });
    }

    public void PrefMode(){
        SharedPreferences info = getSharedPreferences("List", MODE_PRIVATE);
        SharedPreferences.Editor editor = info.edit();

        sendHistory = (Currency + " " + Amount + " = " + Currentcy + " " + Converted);
        Arrays.add(sendHistory);
        Set<String> ihniwti = new HashSet<String>(Arrays);

        editor.putStringSet("History", ihniwti);
        editor.apply();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

