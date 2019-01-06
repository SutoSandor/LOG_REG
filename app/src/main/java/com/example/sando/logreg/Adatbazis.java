package com.example.sando.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Adatbazis extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Adatbazis.db";
    public static final String FELH_TABLENAME = "Felhasznalok_tabla";
    public static final String FELH_COL_1 = "ID";
    public static final String FELH_COL_2 = "FELHASZNALONEV";
    public static final String FELH_COL_3 = "JELSZO";
    public static final String FELH_COL_4 = "TELJES_NEV";
    public static final String FELH_COL_5 = "TELEFONSZAM";

    Adatbazis(Context context){
        super(context,DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + FELH_TABLENAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FELHASZNALONEV TEXT unique, JELSZO TEXT, TELJES_NEV TEXT, TELEFONSZAM TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FELH_TABLENAME);
    }
    public Cursor getAdatok(String tablanev){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor adatok = db.rawQuery("Select * from " + tablanev,null);
        return adatok;
    }
    public boolean Regisztracio(String felhasznalonev, String jelszo,String teljesnev,String telefonszam){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FELH_COL_2, felhasznalonev);
        contentValues.put(FELH_COL_3, jelszo);
        contentValues.put(FELH_COL_4, teljesnev);
        contentValues.put(FELH_COL_5, telefonszam);

        long eredmeny = db.insert(FELH_TABLENAME, null, contentValues);

        if (eredmeny == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
