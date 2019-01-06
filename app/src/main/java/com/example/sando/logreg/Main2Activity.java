package com.example.sando.logreg;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

    private EditText reg_felh,reg_psw1,reg_psw2,reg_nev,reg_tel;
    private Button regisztral_btn;
    private Adatbazis db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        regisztral_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (reg_felh.getText().toString().length()>0&&reg_psw1.getText().toString().length()>0&&reg_psw2.getText().toString().length()>0&&reg_nev.getText().toString().length()>0&&reg_tel.getText().toString().length()>0) {
                    if (Objects.equals(reg_psw1.getText().toString(), reg_psw2.getText().toString())) {
                        if(db.Regisztracio(reg_felh.getText().toString(), reg_psw1.getText().toString(), reg_nev.getText().toString(), reg_tel.getText().toString())){
                            Toast.makeText(Main2Activity.this, "Sikeres regisztráció", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Main2Activity.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    } else {
                        Toast.makeText(Main2Activity.this, "A jelszavaknak nem egyeznek!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(Main2Activity.this, "Egyik mező sem lehet üres!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void init(){
        reg_felh = findViewById(R.id.reg_felh);
        reg_psw1 = findViewById(R.id.reg_jelszo1);
        reg_psw2 = findViewById(R.id.reg_jelszo2);
        reg_nev = findViewById(R.id.reg_teljesnev);
        reg_tel = findViewById(R.id.reg_telefonszam);
        regisztral_btn = findViewById(R.id.regisztral_btn);
        db = new Adatbazis(this);
    }
}
