package com.example.proektevidencija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfessorHomeActivity extends AppCompatActivity {

    Button buttonAdd, buttonView, buttonAnketa, buttonPrisustvo, buttonTermini, buttonPrisustvoSite;
    TextView LogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);
        LogOut = (TextView) findViewById(R.id.logOut);
        buttonAnketa = (Button) findViewById(R.id.buttonAnketa);
        buttonPrisustvo = (Button) findViewById(R.id.buttonPrisustvo);
        buttonTermini = (Button) findViewById(R.id.buttonTermini);
        buttonPrisustvoSite = (Button) findViewById(R.id.buttonPrisustvoSite);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddSubjectActivity.class);
                startActivity(intent);
            }
        });

        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MySubjectActivity.class);
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

        buttonAnketa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProveriAnketaActivity.class);
                startActivity(intent);
            }
        });

        buttonPrisustvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProveriPrisustvoActivity.class);
                startActivity(intent);
            }
        });

        buttonTermini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MomentalniTerminiAcitivity.class);
                startActivity(intent);
            }
        });

        buttonPrisustvoSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProveriPrisustvoSiteActivity.class);
                startActivity(intent);
            }
        });
    }
}