package com.example.winuser.discapphelp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.content.pm.PackageManager;

import android.location.LocationListener;
import android.location.LocationManager;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


public class datospersonales extends AppCompatActivity {
    EditText cedula;
    EditText nombre;
    EditText apellido;
    EditText edad;
    EditText sexo;
    EditText direccion;
    EditText telefono;
    EditText celular;

    ImageButton btonGuardar;
    String cedulatxt,nombretxt,apellidotxt,edadtxt,sexotxt,direciontxt,telefonotxt,celulartxt,lat,lon,geom,id_usuario,id,mensaje1;
    List<NameValuePair> params;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datospersonales);


        cedula=(EditText)findViewById(R.id.editTextCedula);
        nombre=(EditText)findViewById(R.id.editTextCedula);
        apellido=(EditText)findViewById(R.id.editTextCedula);
        edad=(EditText)findViewById(R.id.editTextCedula);
        sexo=(EditText)findViewById(R.id.editTextCedula);
        direccion=(EditText)findViewById(R.id.editTextCedula);
        telefono=(EditText)findViewById(R.id.editTextCedula);
        celular=(EditText)findViewById(R.id.editTextCedula);

        btonGuardar=(ImageButton)findViewById(R.id.imageButtonGuardar);




        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {//ver si contiene datos
            id_usuario=(String)extras.get("id");//Obtengo el nombre
              // cedula.setText(datoCedulae);
        }


        btonGuardar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
            cedulatxt=cedula.getText().toString();
            nombretxt=nombre.getText().toString();
            apellidotxt=apellido.getText().toString();
                edadtxt=edad.getText().toString();
                sexotxt=sexo.getText().toString();
                direciontxt=direccion.getText().toString();
                telefonotxt=telefono.getText().toString();
                celulartxt=celular.getText().toString();


                params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("id_usuario", id_usuario));
                params.add(new BasicNameValuePair("cedula", cedulatxt));
                params.add(new BasicNameValuePair("nombre", nombretxt));
                params.add(new BasicNameValuePair("apelido", apellidotxt));
                params.add(new BasicNameValuePair("edad", edadtxt));
                params.add(new BasicNameValuePair("sexo", sexotxt));
                params.add(new BasicNameValuePair("direccion", direciontxt));
                params.add(new BasicNameValuePair("telefono_convencional", telefonotxt));
                params.add(new BasicNameValuePair("telefono_celuar", cedulatxt));
                params.add(new BasicNameValuePair("lat", lat));
                params.add(new BasicNameValuePair("lon", lon));
                params.add(new BasicNameValuePair("geom", geom));

               if(mensaje1=="ubicacion encontrada"){
                   ServerRequest sr = new ServerRequest();
                   JSONObject json = sr.getJSON("http://192.168.100.118/punto/registrarPersona",params);
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
                   explicit_intent = new Intent(datospersonales.this, datosespeciales.class);
                   explicit_intent.putExtra("id",id);

                   startActivity(explicit_intent);
               }else {
                   mensaje1="espere que encuentre la ubicacion";
                   error();
               }




            }
        });

   	/* Uso de la clase LocationManager para obtener la localizacion del GPS */
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new  Localizacion();
        Local.setMainActivity(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        //simulador
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                //telefono
                //    mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                (LocationListener) Local);




    }

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        datospersonales datospersonales;

        public datospersonales getMainActivity() {
            return datospersonales;
        }

        public void setMainActivity(datospersonales datospersonales) {
            this.datospersonales = datospersonales;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();
            lat=( ""+loc.getLatitude());
            lon=( ""+loc.getLongitude());
            geom =("SRID=32717;POINT("+loc.getLongitude()+" "+loc.getLatitude()+")");
            mensaje1="ubicacion encontrada";
            error();

        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
           mensaje1=("GPS Desactivado");
            error();
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            mensaje1=("GPS Activado");
            error();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este metodo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizacion (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temporalmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }

    }/* Fin de la clase localizacion */

    public void  error(){
        Toast toast = Toast.makeText(this,mensaje1,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

}
