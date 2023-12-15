package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentRegisterActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText usern;
    EditText passw;
    EditText confirmpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        db = openOrCreateDatabase("proektevidencija", MODE_PRIVATE, null);

    }

    public void ClRegister(View view) {

        usern = (EditText) findViewById(R.id.regusername);
        passw = (EditText) findViewById(R.id.regpassword);
        confirmpass = (EditText) findViewById(R.id.regpassword2);

        if(!usern.getText().toString().isEmpty() && !passw.getText().toString().isEmpty() && !confirmpass.getText().toString().isEmpty()) {
            if (passw.getText().toString().equals(confirmpass.getText().toString())) {

                Cursor c1 = db.rawQuery("SELECT * FROM student WHERE  username = '" + usern.getText().toString() + "'", null);
                Cursor c2 = db.rawQuery("SELECT * FROM profesor WHERE  username = '" + usern.getText().toString() + "'", null);
                if (c1.getCount() > 0 || c2.getCount() > 0) {
                    Toast.makeText(this, "Username is already taken.", Toast.LENGTH_LONG).show();
                    c1.close();
                    c2.close();
                } else {
                    db.execSQL("INSERT INTO student(username,password) VALUES('" + usern.getText().toString() + "','" + passw.getText().toString() + "' );");
                    Toast.makeText(this,"Register like student",Toast.LENGTH_LONG).show();
                    c1.close();
                    c2.close();
                    Intent intent = null;
                    intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
            }else {
                Toast.makeText(this,"Password doesn't match.",Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this,"Please input all fields.",Toast.LENGTH_LONG).show();
        }

    }
}