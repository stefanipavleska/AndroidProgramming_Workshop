package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

public class AddSubjectActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Spinner spinner, spinner1;
    String predmet[];
    TimePicker picker, picker1;
    Button btnGet, btnGet1, saveButton;
    TextView tvw, tvw1;
    String selectedValue, selectedValue1, s1, s2;
    Integer hour1, hour2, minute1, minute2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

        saveButton = (Button) findViewById(R.id.button);

        picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);

        picker1=(TimePicker)findViewById(R.id.timePicker2);
        picker1.setIs24HourView(true);


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


        //selektiram od spinnerot za denovi
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Denovi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedValue = parent.getItemAtPosition(position).toString();
                //insertData(selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //selektiram start time
        picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                s1 = + hourOfDay + ":" + minute;
                hour1 = hourOfDay;
                minute1 = minute;
                showToast("Start Time: " + hourOfDay + ":" + minute);
            }
        });

        //selektiram end time
        picker1.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                s2 = + hourOfDay + ":" + minute;
                hour2 = hourOfDay;
                minute2 = minute;
                showToast("End Time: " + hourOfDay + ":" + minute);
            }
        });

        //klika na save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void insertData(){
        Intent intent = new Intent(getApplicationContext(), MySubjectActivity.class);

        ContentValues values = new ContentValues();
        values.put("den", selectedValue);
        values.put("odterminHour", hour1);
        values.put("odterminMinute", minute1);
        values.put("doterminHour", hour2);
        values.put("doterminMinute", minute2);
        values.put("predmet", selectedValue1);

        db.insert("termin", null, values);

        startActivity(intent);

    }

    private String getFormattedTime(TimePicker timePicker) {
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        return String.format("%02d:%02d", hour, minute);
    }

}