package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(username VARCHAR UNIQUE, password VARCHAR, sid INTEGER PRIMARY KEY);");
        db.execSQL("CREATE TABLE IF NOT EXISTS profesor(username VARCHAR UNIQUE, password VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS predmeti(predmet VARCHAR, pid INTEGER PRIMARY KEY)");
        db.execSQL("CREATE TABLE IF NOT EXISTS termin(den VARCHAR, odterminHour INTEGER, odterminMinute INTEGER, doterminHour INTEGER, doterminMinute INTEGER, predmet VARCHAR, tid INTEGER PRIMARY KEY)");
        db.execSQL("CREATE TABLE IF NOT EXISTS odgovori(prasanje1 VARCHAR, prasanje2 VARCHAR, prasanje3 VARCHAR, predmet VARCHAR, pid INTEGER PRIMARY KEY)");
        db.execSQL("CREATE TABLE IF NOT EXISTS dodadenip(predmet VARCHAR, den VARCHAR, odterminHour INTEGER, odterminMinute INTEGER, doterminHour INTEGER, doterminMinute INTEGER, did INTEGER PRIMARY KEY)");
        db.execSQL("CREATE TABLE IF NOT EXISTS prisustvo(selektirano VARCHAR, predmet VARCHAR, den VARCHAR, odterminHour INTEGER, odterminMinute INTEGER, doterminHour INTEGER, doterminMinute INTEGER, prid INTEGER PRIMARY KEY)");



        String s2 = "profesor";
        String s1 = "student";

        String p1 = "Android Programiranje";
        String p2 = "Sistemi za presmetki so visoki performansi";
        String p3 = "Sovremeni procesorski arhitekturi";
        String p4 = "Mrezno programiranje";
        String p5 = "Inteligentni agenti";



        Cursor c1 = db.rawQuery("SELECT * FROM student WHERE  username = '"+s1+"'", null);
        Cursor c2 = db.rawQuery("SELECT * FROM profesor WHERE  username = '"+s2+"'", null);

        Cursor c3 = db.rawQuery("SELECT * FROM predmeti WHERE  predmet = '"+p1+"'", null);
        Cursor c4 = db.rawQuery("SELECT * FROM predmeti WHERE  predmet = '"+p2+"'", null);
        Cursor c5 = db.rawQuery("SELECT * FROM predmeti WHERE  predmet = '"+p3+"'", null);
        Cursor c6 = db.rawQuery("SELECT * FROM predmeti WHERE  predmet = '"+p4+"'", null);
        Cursor c7 = db.rawQuery("SELECT * FROM predmeti WHERE  predmet = '"+p5+"'", null);

        if(c1.getCount()>0 && c2.getCount()>0) {
            c1.close();
            c2.close();
        }
        else {
            db.execSQL("INSERT INTO student (username,password) VALUES('student', '12345');");
            db.execSQL("INSERT INTO profesor (username,password) VALUES('profesor', '12345');");
            c1.close();
            c2.close();
        }

        if(c3.getCount()>0){
            c3.close();
        }else{
            db.execSQL("INSERT INTO predmeti (predmet) VALUES('Android Programiranje');");
            c3.close();
        }

        if(c4.getCount()>0){
            c4.close();
        }else{
            db.execSQL("INSERT INTO predmeti (predmet) VALUES('Sistemi za presmetki so visoki performansi');");
            c4.close();
        }

        if(c5.getCount()>0){
            c5.close();
        }else{
            db.execSQL("INSERT INTO predmeti (predmet) VALUES('Sovremeni procesorski arhitekturi');");
            c5.close();
        }

        if(c6.getCount()>0){
            c6.close();
        }else{
            db.execSQL("INSERT INTO predmeti (predmet) VALUES('Mrezno programiranje');");
            c6.close();
        }

        if(c7.getCount()>0){
            c7.close();
        }else{
            db.execSQL("INSERT INTO predmeti (predmet) VALUES('Inteligentni agenti');");
            c7.close();
        }

    }
    public void ClickLogin(View view) {
        Intent intent = null;
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    public void ClickRegister(View view) {
        Intent intent = null;
        intent = new Intent(this, StudentRegisterActivity.class);
        startActivity(intent);

    }

    public void ClickRegister1(View view) {
        Intent intent = null;
        intent = new Intent(this, ProfessorRegisterActivity.class);
        startActivity(intent);

    }
}