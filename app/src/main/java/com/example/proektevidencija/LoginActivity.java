package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText usern;
    EditText passw;
    String Uid;
    CountDownTimer cdt;
    long start_millis;
    long end_millis;
    long total_millis;
    boolean TimerRunning = true;
    Cursor c3;
    Cursor c;
    ArrayList<String> pids = new ArrayList<>();
    ArrayList<String> pnames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);


    }

    public void ClLogin(View view) {
        usern = (EditText) findViewById(R.id.loginemail);
        passw = (EditText) findViewById(R.id.loginpassword);
        String s1 = usern.getText().toString();
        String s2 = passw.getText().toString();

        Cursor c1 = db.rawQuery("SELECT * FROM student WHERE  username = '" + usern.getText().toString() + "' AND password = '" + passw.getText().toString() + "'", null);
        Cursor c2 = db.rawQuery("SELECT * FROM profesor WHERE  username = '" + usern.getText().toString() + "' AND password = '" + passw.getText().toString() + "'", null);

        if (c1.moveToFirst()) {
            Intent intent = null;
            intent = new Intent(this, StudentHomeActivity.class);
            intent.putExtra("username", usern.getText().toString());
            intent.putExtra("userid", c1.getString(2));
            Uid = c1.getString(2);
            c1.close();
            c2.close();
            startActivity(intent);
        } else if (c2.moveToFirst()) {
            Intent intent = null;
            intent = new Intent(this, ProfessorHomeActivity.class);
            intent.putExtra("username", usern.getText().toString());
            c2.close();
            c1.close();
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_LONG).show();
        }
    }
}