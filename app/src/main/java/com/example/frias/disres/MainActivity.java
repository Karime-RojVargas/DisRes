package com.example.frias.disres;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences almacena;
    Button btnAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView etUsu = findViewById(R.id.etUsu);
        final TextView etCon = findViewById(R.id.etCon);
        final Button btnAcc = findViewById(R.id.btnAcc);
        btnAcc.setText(R.string.acceder);
        almacena=getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = almacena.edit();
        editor.commit();

        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((etUsu.getText().toString().equals(almacena.getString("usu","usu")))&&
                        (etCon.getText().toString().equals(almacena.getString("con","con")))){
                    Intent intent =new Intent(MainActivity.this,Inicio.class);
                    intent.putExtra("usu",etUsu.getText().toString());
                    startActivity(intent);
                }
                else {
                    btnAcc.setText(R.string.registrar);
                    editor.putString("usu",etUsu.getText().toString());
                    editor.putString("con",etCon.getText().toString());
                    editor.commit();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        btnAcc.setText(R.string.acceder);
    }

}
