package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

public class PrisustvoActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Button button;
    RadioButton radioButton;
    String selected;
    String predmet, den;
    Integer odterminHour, odterminMinute, doterminHour, doterminMinute, currentHour, currentMinute;
    String currentDay;
    String[] days;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prisustvo);

        button = (Button) findViewById(R.id.button);
        radioButton = (RadioButton) findViewById(R.id.radioButton1);
        proveri();

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

        Calendar calendar = Calendar.getInstance();
        days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thurstday", "Friday", "Saturday"};
        currentDay = days[calendar.get(Calendar.DAY_OF_WEEK)-1];
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        Cursor cursor = db.rawQuery("SELECT * FROM dodadenip WHERE den = '"+currentDay+"'AND odterminHour <= '"+currentHour+"'AND doterminHour >= '"+currentHour+"'", null);

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
                values.put("selektirano", selected);
                values.put("predmet", predmet);
                values.put("den", den);
                values.put("odterminHour", odterminHour);
                values.put("odterminMinute", odterminMinute);
                values.put("doterminHour", doterminHour);
                values.put("doterminMinute", doterminMinute);

                db.insert("prisustvo", null, values);

            } while (cursor.moveToNext());

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentHomeActivity.class);
                startActivity(intent);
            }
        });

    }

    private boolean proveri(){
        if(radioButton != null){
             selected = radioButton.getText().toString();
            return true;
        }else{
            return false;
        }
    }



}