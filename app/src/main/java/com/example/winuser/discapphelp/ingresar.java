package com.example.winuser.discapphelp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ingresar extends AppCompatActivity {





    EditText usuario;
    EditText contrasenia;
    Button btonLogin;



    String usuariotxt,contraseniatxt,id_usuario,id_persona;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);



        usuario=(EditText)findViewById(R.id.editTextLoginUsuario);
        contrasenia=(EditText)findViewById(R.id.editTextLoginContrasenia);

        btonLogin=(Button)findViewById(R.id.buttonLogin);



        findViewById(R.id.textView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ingresar.this, registrar.class));
            }
        });






        btonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                usuariotxt = usuario.getText().toString();
                contraseniatxt = contrasenia.getText().toString();


                params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("usuario", usuariotxt));
                params.add(new BasicNameValuePair("contrasenia", contraseniatxt));


                ServerRequest sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://192.168.100.118/punto/login", params);

                if (json != null) {
                    try {

                        id_usuario = json.getString("id_usuario");
                        id_persona = json.getString("id_persona");


                        Intent explicit_intent;
                        explicit_intent = new Intent(ingresar.this, menu.class);
                        explicit_intent.putExtra("id_usuario", id_usuario);
                        explicit_intent.putExtra("id_persona", id_persona);
                        startActivity(explicit_intent);


                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                } else {

                    //aqui va el mesnsaje de o dialo a mostrar q incorecta o usuario
                    Log.d("error login", "null");
                    error();


                }


            }

        });


    }
    public void  error(){
        Toast toast = Toast.makeText(this,"Los Datos Ingresados son Incorectos o Nulos",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }






}
