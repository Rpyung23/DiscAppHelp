package com.example.winuser.discapphelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class datosespeciales extends AppCompatActivity {


    EditText id_discapacidad;
    EditText seguro_iess;
    EditText contacto_nombre;
    EditText contacto_celular;

    ImageButton btonGuardar;

    String id_dicapacidadtxt,seguroIesstxt,contacto_emergenciatxt,contacto_celulattxt,id_persona;
    List<NameValuePair> params;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datosespeciales);



        seguro_iess =(EditText)findViewById(R.id.editTexSeguro_IESS);
        contacto_nombre =(EditText)findViewById(R.id.editTextContacto_Nombre);
        contacto_celular =(EditText)findViewById(R.id.editTextContactoCelular);

        btonGuardar=(ImageButton)findViewById(R.id.imageButtonDatosEspeciales);

        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {//ver si contiene datos
            id_persona=(String)extras.get("id");//Obtengo el nombre
            // cedula.setText(datoCedulae);
        }



        btonGuardar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                seguroIesstxt=seguro_iess.getText().toString();
                id_dicapacidadtxt="1";
                contacto_emergenciatxt=contacto_nombre.getText().toString();
                contacto_celulattxt=contacto_celular.getText().toString();



                params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("id_persona", id_persona));
                params.add(new BasicNameValuePair("id_discapacidad", id_dicapacidadtxt));
                params.add(new BasicNameValuePair("seguro_iess", seguroIesstxt));
                params.add(new BasicNameValuePair("nombre_contacto_emergencia", contacto_emergenciatxt));
                params.add(new BasicNameValuePair("celular_contacto_emergencia", contacto_celulattxt));


                ServerRequest sr = new ServerRequest();
                sr.getJSON("http://192.168.100.118/punto/registrarTipoDiscapacidad",params);

                startActivity(new Intent(datosespeciales.this, ingresar.class));

            }
        });
    }
}
