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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class registrar extends AppCompatActivity {

    EditText usuario;
    EditText contrasenia;
    EditText correo;

    Button btonRegistrar;

    String usuariotxt,contraseniatxt,correotxt,id;
    List<NameValuePair> params;
    List<NameValuePair> params1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        usuario=(EditText)findViewById(R.id.editTextLoginUsuario);
        contrasenia=(EditText)findViewById(R.id.editTextContraseña);
        correo=(EditText)findViewById(R.id.editTextCorreo);

        btonRegistrar=(Button)findViewById(R.id.btonRegistrar);

        btonRegistrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                usuariotxt = usuario.getText().toString();
                contraseniatxt = contrasenia.getText().toString();
                correotxt = correo.getText().toString();


                params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("usuario", usuariotxt));
                params.add(new BasicNameValuePair("contrasenia", contraseniatxt));
                params.add(new BasicNameValuePair("correo", correotxt));

                params1 = new ArrayList<NameValuePair>();
                params1.add(new BasicNameValuePair("usuario", usuariotxt));

                ServerRequest sr = new ServerRequest();

                JSONObject json1 = sr.getJSON("http://192.168.100.118/punto/usuarioExiste",params1);
                if(json1 == null){


                    JSONObject json = sr.getJSON("http://192.168.100.118/punto/registrarUsuario",params);
                    if(json != null){
                        try{
                            String jsonstr = json.getString("id");

                            Toast.makeText(getApplication(),jsonstr, Toast.LENGTH_LONG).show();
                            id=jsonstr;
                            Log.d("Hello", jsonstr);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }




                    Intent explicit_intent;
                    explicit_intent = new Intent(registrar.this, datospersonales.class);
                    explicit_intent.putExtra("id", id);

                    startActivity(explicit_intent);

                }else {
                    error();

                }



            }


        });



    }

    public void  error(){
        Toast toast = Toast.makeText(this,"el usuario ya existe",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }
}
