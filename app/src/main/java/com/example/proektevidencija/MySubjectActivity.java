package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MySubjectActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    String predmet, den;
    Integer odterminHour, odterminMinute, doterminHour, doterminMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_subject);
        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);


        List<String> dataList = fetchDataFromDatabase();

        listView = findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }


    private List<String> fetchDataFromDatabase() {
//        String predmet, den;
//        Integer odterminHour, odterminMinute, doterminHour, doterminMinute;
        List<String> dataList = new ArrayList<>();

        // Retrieve data from the database
        Cursor cursor = db.rawQuery("SELECT * FROM termin", null);

        // Check if there is data in the cursor
        if (cursor.moveToFirst()) {
            do {
                // Assuming you have a column named "your_column"
                 predmet = cursor.getString(cursor.getColumnIndex("predmet"));
                 den = cursor.getString(cursor.getColumnIndex("den"));
                 odterminHour = cursor.getInt(cursor.getColumnIndex("odterminHour"));
                 odterminMinute = cursor.getInt(cursor.getColumnIndex("odterminMinute"));
                 doterminHour = cursor.getInt(cursor.getColumnIndex("doterminHour"));
                 doterminMinute = cursor.getInt(cursor.getColumnIndex("doterminMinute"));

                String data = "Предмет:" + predmet + "\n"+
                        "Ден: " + den + "\n"
                        + "Од: " + odterminHour + ":" + odterminMinute + "\n"
                        + "До: " + doterminHour + ":" + doterminMinute  + "\n";
                dataList.add(data);

            } while (cursor.moveToNext());

        }
        cursor.close();

        return dataList;
    }
}