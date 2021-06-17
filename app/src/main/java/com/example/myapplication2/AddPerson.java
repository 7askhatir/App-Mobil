package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPerson extends AppCompatActivity {
      EditText nom,prenom,age;
      Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        nom=findViewById(R.id.nom1);
        prenom=findViewById(R.id.prenom1);
        age=findViewById(R.id.age1);
        button2=findViewById(R.id.button1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(AddPerson.this);
                myDatabaseHelper.AjouterP(nom.getText().toString().trim(),
                                          prenom.getText().toString().trim()
                                          ,Integer.parseInt(age.getText().toString().trim()));


            }
        });
    }
}