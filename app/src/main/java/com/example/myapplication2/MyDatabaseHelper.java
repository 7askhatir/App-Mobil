package com.example.myapplication2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="maraton.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="person";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NOM="nom_person";
    private static final String COLUMN_PRENOM="prenom_person";
    private static final String COLUMN_AGE="age_person";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String query="CREATE TABLE "+ TABLE_NAME +
                 " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_NOM +" TEXT, " +
                 COLUMN_PRENOM +" TEXT, " +
                 COLUMN_AGE + " INTEGER);";
         db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
       onCreate(db);
    }
    void AjouterP(String nom,String prenom ,int age){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NOM,nom);
        contentValues.put(COLUMN_PRENOM,prenom);
        contentValues.put(COLUMN_AGE,age);
        long test=db.insert(TABLE_NAME,null,contentValues);
        if(test==-1) Toast.makeText(context,"Incorrect !!",Toast.LENGTH_SHORT).show();
        else Toast.makeText(context,"Ajouter successfully ",Toast.LENGTH_SHORT).show();

    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id,String nom,String prenom,String age){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NOM,nom);
        values.put(COLUMN_PRENOM,prenom);
        values.put(COLUMN_AGE,age);
        long result=db.update(TABLE_NAME,values,COLUMN_ID+"=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context,"modifie ne pas accepte",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"modifie succusfel",Toast.LENGTH_SHORT).show();
        }

    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

}
