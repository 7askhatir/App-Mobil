package com.example.myapplication2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<String> person_id,person_nom,person_prenom,person_age;
    CustomAdapter customAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.RecyclerView);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddPerson.class);
                startActivity(intent);
            }
        });
        myDB =new MyDatabaseHelper(MainActivity.this);
        person_id=new ArrayList<>();
        person_nom=new ArrayList<>();
        person_prenom=new ArrayList<>();
        person_age =new ArrayList<>();
        afficheData();
        customAdapter=new CustomAdapter(MainActivity.this,this,person_id,person_nom,person_prenom,person_age);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }



    void afficheData(){
        Cursor cursor=myDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this,"Non data" ,Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                person_id.add(cursor.getString(0));
                person_nom.add(cursor.getString(1));
                person_prenom.add(cursor.getString(2));
                person_age.add(cursor.getString(3));


            }
        }
    }
}