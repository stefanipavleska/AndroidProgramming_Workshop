package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProveriPrisustvoActivity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    String currentDay;
    String []days;
    String predmet, den, selected;
    Integer odterminHour, odterminMinute, doterminHour, doterminMinute, currentHour, currentMinute;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveri_prisustvo);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

        List<String> dataList = fetchDataFromDatabase();

        button = (Button) findViewById(R.id.button);
        listView = findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Присуството е потврдено", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private List<String> fetchDataFromDatabase() {
        List<String> dataList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thurstday", "Friday", "Saturday"};
        currentDay = days[calendar.get(Calendar.DAY_OF_WEEK)-1];
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        // Retrieve data from the database
        Cursor cursor = db.rawQuery("SELECT * FROM prisustvo WHERE  den = '" + currentDay + "' AND odterminHour <= '" + currentHour + "'AND doterminHour >= '" + currentHour + "'", null);


        // Check if there is data in the cursor
        if (cursor.moveToFirst()) {
            do {
                // Assuming you have a column named "your_column"
                selected = cursor.getString(cursor.getColumnIndex("selektirano"));
                predmet = cursor.getString(cursor.getColumnIndex("predmet"));
                den = cursor.getString(cursor.getColumnIndex("den"));
                odterminHour = cursor.getInt(cursor.getColumnIndex("odterminHour"));
                odterminMinute = cursor.getInt(cursor.getColumnIndex("odterminMinute"));
                doterminHour = cursor.getInt(cursor.getColumnIndex("doterminHour"));
                doterminMinute = cursor.getInt(cursor.getColumnIndex("doterminMinute"));

                String data = "Предмет:" + predmet + "\n"+
                        "Ден: " + den + "\n"
                        + "Од: " + odterminHour + ":" + odterminMinute + "\n"
                        + "До: " + doterminHour + ":" + doterminMinute + "\n"
                        + "student селектирал: " + selected + "\n";
                dataList.add(data);


            } while (cursor.moveToNext());

        }
        cursor.close();

        return dataList;
    }
}