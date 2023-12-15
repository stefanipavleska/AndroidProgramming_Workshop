package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    String predmet, den;
    Integer odterminHour, odterminMinute, doterminHour, doterminMinute;
    String predmet1, den1;
    Integer odterminHour1, odterminMinute1, doterminHour1, doterminMinute1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);


        String selectedSubject = getIntent().getStringExtra("predmet");
        Cursor cursor = db.rawQuery("SELECT * FROM termin WHERE predmet = '"+selectedSubject+"'", null);

        if (cursor.moveToFirst()) {
            do {
                // Assuming you have a column named "your_column"
                predmet = cursor.getString(cursor.getColumnIndex("predmet"));
                den = cursor.getString(cursor.getColumnIndex("den"));
                odterminHour = cursor.getInt(cursor.getColumnIndex("odterminHour"));
                odterminMinute = cursor.getInt(cursor.getColumnIndex("odterminMinute"));
                doterminHour = cursor.getInt(cursor.getColumnIndex("doterminHour"));
                doterminMinute = cursor.getInt(cursor.getColumnIndex("doterminMinute"));


                ContentValues values = new ContentValues();
                values.put("predmet", predmet);
                values.put("den", den);
                values.put("odterminHour", odterminHour);
                values.put("odterminMinute", odterminMinute);
                values.put("doterminHour", doterminHour);
                values.put("doterminMinute", doterminMinute);

                db.insert("dodadenip", null, values);

            } while (cursor.moveToNext());

        }

        List<String> dataList = fetchDataFromDatabase();

        listView = findViewById(R.id.lvenroll);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);



    }

    private List<String> fetchDataFromDatabase() {
        List<String> dataList = new ArrayList<>();

        // Retrieve data from the database
        Cursor cursor = db.rawQuery("SELECT * FROM dodadenip", null);

        // Check if there is data in the cursor
        if (cursor.moveToFirst()) {
            do {
                // Assuming you have a column named "your_column"
                predmet1 = cursor.getString(cursor.getColumnIndex("predmet"));
                den1 = cursor.getString(cursor.getColumnIndex("den"));
                odterminHour1 = cursor.getInt(cursor.getColumnIndex("odterminHour"));
                odterminMinute1 = cursor.getInt(cursor.getColumnIndex("odterminMinute"));
                doterminHour1 = cursor.getInt(cursor.getColumnIndex("doterminHour"));
                doterminMinute1 = cursor.getInt(cursor.getColumnIndex("doterminMinute"));

                String data = "Предмет:" + predmet1 + "\n"+
                        "Ден: " + den1 + "\n"
                        + "Од: " + odterminHour1 + ":" + odterminMinute1 + "\n"
                        + "До: " + doterminHour1 + ":" + doterminMinute1  + "\n";
                dataList.add(data);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return dataList;
    }

    }
