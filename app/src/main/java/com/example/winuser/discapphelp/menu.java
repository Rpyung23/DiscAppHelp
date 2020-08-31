package com.example.winuser.discapphelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class menu extends AppCompatActivity {


    ImageButton btonEmergencia;
    String id_persona,id_usuario;
    ImageButton imageButton;

    ImageButton imageButton6;
    ImageButton imageButton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btonEmergencia=(ImageButton) findViewById(R.id.imageButtonMenuEmergencia);



        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {//ver si contiene datos
            id_persona=(String)extras.get("id_persona");//Obtengo el nombre
            id_usuario=(String)extras.get("id_usuario");//Obtengo el nombre
            // cedula.setText(datoCedulae);
        }


        btonEmergencia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent explicit_intent;
                explicit_intent = new Intent(menu.this, emergencia.class);
                explicit_intent.putExtra("id",id_persona);

                startActivity(explicit_intent);

            }


        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emerinfo();
            }
        });




        imageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acercadeinfo();
            }
        });

        imageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ayudainfo();
            }
        });



}


    public void  emerinfo(){
        Toast toast = Toast.makeText(this,"Mandar alerta de auxilio",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }


    public void  ayudainfo(){
        Toast toast = Toast.makeText(this, "Ayuda en videos sobre la aplicacion",Toast.LENGTH_LONG);
        toast.setGravity (Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }

    public void  acercadeinfo(){
        Toast toast = Toast.makeText(this,"Informacion acerca de la aplicacion",Toast.LENGTH_LONG);
        toast.setGravity (Gravity.CENTER_VERTICAL,0,0);
        toast.show();
    }


}
