package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AnketaActivity extends AppCompatActivity {
    SQLiteDatabase db;
    EditText tekst1, tekst2, tekst3;
    Button submitButton;
    String s1, s2, s3, selectedValue1;
    TextView textView;
    String predmet[];
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anketa);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

        tekst1 = (EditText) findViewById(R.id.tekst1);
        tekst2 = (EditText) findViewById(R.id.tekst2);
        tekst3 = (EditText) findViewById(R.id.tekst3);
        submitButton = (Button) findViewById(R.id.submitButton);

        spinner = (Spinner) findViewById(R.id.spinner);

        //gi vnesuvam predmetite od bazata vo spinnerot

        Cursor c = db.rawQuery("select *from predmeti", null);

        predmet = new String[c.getCount()];
        c.moveToFirst();
        for (int i = 0; i < predmet.length; i++) {
            predmet[i] = c.getString(0);
            c.moveToNext();
        }

        ArrayAdapter<String> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, predmet);
        spinner.setAdapter(adp);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedValue1 = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void insertData(View view){
        ContentValues values = new ContentValues();
         s1 = tekst1.getText().toString();
         s2 = tekst2.getText().toString();
         s3 = tekst3.getText().toString();
        values.put("prasanje1", s1);
        values.put("prasanje2", s2);
        values.put("prasanje3", s3);
        values.put("predmet", selectedValue1);

        db.insert("odgovori", null, values);

    }
}