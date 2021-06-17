package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePerson extends AppCompatActivity {
     EditText nom,prenom,age;
     Button update_button,sup_button;
     String nom_s , prenom_s ,age_s,id_s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_person);
        nom=findViewById(R.id.nom1);
        prenom=findViewById(R.id.prenom1);
        age=findViewById(R.id.age1);
        update_button=findViewById(R.id.button1);
        sup_button=findViewById(R.id.button2);
        sup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(UpdatePerson.this);
                myDatabaseHelper.deleteOneRow(id_s);
                UpdatePerson.super.onBackPressed();
            }
        });
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom_s = nom.getText().toString();
                prenom_s = prenom.getText().toString();
                age_s = age.getText().toString();
                MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(UpdatePerson.this);
                myDatabaseHelper.updateData(id_s,nom_s,prenom_s,age_s);
            }
        });
        getIntelData();
    }
    void getIntelData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") && getIntent().hasExtra("prenom") && getIntent().hasExtra("age")){
            //get data in intent
            id_s=getIntent().getStringExtra("id");
            nom_s=getIntent().getStringExtra("nom");
            prenom_s=getIntent().getStringExtra("prenom");
            age_s=getIntent().getStringExtra("age");

            //set data in page update

            nom.setText(nom_s);
            prenom.setText(prenom_s);
            age.setText(age_s);
        }else{
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
    }
}