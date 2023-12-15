package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class StudentHomeActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button buttonAdd, buttonView, buttonMap, buttonPrisustvo, buttonAnketa;
    TextView LogOut;
    String usern;
    Double latitude, longitude;
    String vrednost;
    String[] days;
    String currentDay, selected;
    Integer currentHour, currentMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

        latitude = getIntent().getDoubleExtra("LATITUDE_EXTRA", 0.0);
        longitude = getIntent().getDoubleExtra("LONGITUDE_EXTRA", 0.0);


        Intent intent = getIntent();
        usern = intent.getStringExtra("username");

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        LogOut = (TextView) findViewById(R.id.logOut);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonPrisustvo = (Button) findViewById(R.id.buttonPrisustvo);
        buttonAnketa = (Button) findViewById(R.id.buttonAnketa);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EnrollSubjectActivity.class);
                startActivity(intent);
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyCalendarActivity.class);
                startActivity(intent);
            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });


        buttonPrisustvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yourConditionIsMet()){
                    if(latitude == 42.0049783 && longitude == 21.408305){
                        Intent intent = new Intent(getApplicationContext(), PrisustvoActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Не можеш да потврдиш присуство не си на ФЕИТ", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Не можеш да потврдиш присуство не си на ФЕИТ", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonAnketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(prisustvo()){
                   Intent intent = new Intent(getApplicationContext(), AnketaActivity.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(getApplicationContext(), "Не можеш да пополниш анкета немаш потврдено присуство", Toast.LENGTH_SHORT).show();
               }
            }
        });


        Calendar calendar = Calendar.getInstance();
        days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thurstday", "Friday", "Saturday"};
         currentDay = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
         currentHour = calendar.get(Calendar.HOUR_OF_DAY);
         currentMinute = calendar.get(Calendar.MINUTE);

    }

    private boolean yourConditionIsMet() {
        Cursor c1 = db.rawQuery("SELECT * FROM dodadenip WHERE  den = '" + currentDay + "' AND odterminHour <= '" + currentHour + "' AND doterminHour >= '" + currentHour + "'", null);

        boolean isTrue = c1.getCount() > 0;
        c1.close();
        return isTrue;
    }

    private boolean prisustvo(){
        Cursor cursor = db.rawQuery("SELECT * FROM prisustvo WHERE  den = '" + currentDay + "' AND odterminHour <= '" + currentHour + "'AND doterminHour >= '" + currentHour + "'AND selektirano = '" + "Присуствував на часот" + "'", null);
        boolean isTrue = cursor.getCount() > 0;
        cursor.close();
        return isTrue;


    }
}