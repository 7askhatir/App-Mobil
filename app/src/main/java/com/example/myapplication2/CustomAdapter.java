package com.example.myapplication2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    Activity activity;
    private ArrayList person_id,person_nom,person_prenom,person_age;
    int position;

    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList person_id,
                  ArrayList person_nom,
                  ArrayList person_prenom ,
                  ArrayList person_age ){
        this.activity=activity;
        this.context=context;
        this.person_age=person_age;
        this.person_id=person_id;
        this.person_nom=person_nom;
        this.person_prenom=person_prenom;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position=position;
        holder.person_age_txt.setText(String.valueOf(person_age.get(position))+" ans");
        holder.person_nom_txt.setText(String.valueOf(person_nom.get(position)));
        holder.person_id_txt.setText(String.valueOf(person_id.get(position)));
        holder.person_prenom_txt.setText(String.valueOf(person_prenom.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent=new Intent(context,UpdatePerson.class);
             intent.putExtra("id",String.valueOf(person_id.get(position)));
             intent.putExtra("nom",String.valueOf(person_nom.get(position)));
             intent.putExtra("prenom",String.valueOf(person_prenom.get(position)));
             intent.putExtra("age",String.valueOf(person_age.get(position)));
             activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView person_id_txt,person_age_txt,person_nom_txt,person_prenom_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            person_id_txt=itemView.findViewById(R.id.person_id_txt);
            person_age_txt=itemView.findViewById(R.id.person_age_txt);
            person_nom_txt=itemView.findViewById(R.id.person_nom_txt);
            person_prenom_txt=itemView.findViewById(R.id.person_prenom_txt);
            mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}
