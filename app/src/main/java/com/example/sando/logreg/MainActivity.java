package com.example.sando.logreg;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText felh,psw;
    Button belep,reg;
    Adatbazis db;
    View reg_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        belep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beJelentkezes(felh.getText().toString(),psw.getText().toString());
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void init(){
        felh = findViewById(R.id.felh);
        psw = findViewById(R.id.jelszo);
        belep = findViewById(R.id.belep);
        db = new Adatbazis(this);
        reg = findViewById(R.id.reg_dialog);
    }
    public void beJelentkezes(String felhasznalo, String jelszo){
        Cursor adatok = db.getAdatok("Felhasznalok_tabla");
        boolean letezo_felhasznalo = false;
        boolean helyes_jelszo = false;
        if (felhasznalo.length()>0&&jelszo.length()>0) {
            if (adatok != null && adatok.getCount() > 0) {
                while (adatok.moveToNext()) {
                    if (Objects.equals(adatok.getString(1), felhasznalo)) {
                        letezo_felhasznalo = true;
                        if (Objects.equals(adatok.getString(2), jelszo)) {
                            helyes_jelszo = true;
                        }
                    }
                }
            }
            if (letezo_felhasznalo && helyes_jelszo) {
                Toast.makeText(this, "Sikeres bejelentkezés!", Toast.LENGTH_SHORT).show();
            } else if (!letezo_felhasznalo) {
                Toast.makeText(this, "Nem létezik ilyen felhasználó!", Toast.LENGTH_SHORT).show();
            } else if (!helyes_jelszo) {
                Toast.makeText(this, "Helytelen jelszó!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ha ezt írja ki nagy a baj", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "A felhasználó név és jelszó nem lehet üres!", Toast.LENGTH_SHORT).show();
        }
    }
}
