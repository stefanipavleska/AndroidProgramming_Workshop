package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EnrollSubjectActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Spinner spinnerEnroll;
    String predmet[], selectedValue;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_subject);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

        spinnerEnroll = (Spinner) findViewById(R.id.enrollspinner);
        button = (Button) findViewById(R.id.button);

        Cursor c = db.rawQuery("select *from predmeti", null);

        predmet = new String[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i < predmet.length; i++) {
            predmet[i] = c.getString(0);
            c.moveToNext();
        }

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, predmet);
        spinnerEnroll.setAdapter(adp);


        spinnerEnroll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyCalendarActivity.class);
                intent.putExtra("predmet",selectedValue);
                startActivity(intent);
            }
        });

        SharedPreferences preferences = getSharedPreferences("shared_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("shared_key", selectedValue);
        editor.apply();

    }
}