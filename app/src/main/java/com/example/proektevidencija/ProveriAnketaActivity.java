package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ProveriAnketaActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveri_anketa);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);


        List<String> dataList = fetchDataFromDatabase();

        listView = findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
    }

    private List<String> fetchDataFromDatabase() {
        String prasanje1, prasanje2, prasanje3, predmet;
        List<String> dataList = new ArrayList<>();

        // Retrieve data from the database
        Cursor cursor = db.rawQuery("SELECT * FROM odgovori", null);


        // Check if there is data in the cursor
        if (cursor.moveToFirst()) {
            do {
                // Assuming you have a column named "your_column"
                prasanje1 = cursor.getString(cursor.getColumnIndex("prasanje1"));
                prasanje2 = cursor.getString(cursor.getColumnIndex("prasanje2"));
                prasanje3 = cursor.getString(cursor.getColumnIndex("prasanje3"));
                predmet = cursor.getString((cursor.getColumnIndex("predmet")));

                String data = "Предмет: " + predmet + "\n"
                        + "Одговор на прашање1:" + prasanje1 + "\n"+
                        "Одговор на прашање2: " + prasanje2 + "\n"
                        + "Одговор на прашање3 " + prasanje3 + "\n";
                dataList.add(data);

            } while (cursor.moveToNext());


        }
        cursor.close();

        return dataList;
    }
}