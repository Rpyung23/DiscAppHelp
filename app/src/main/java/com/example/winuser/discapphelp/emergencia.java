package com.example.winuser.discapphelp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class emergencia extends AppCompatActivity {


    ImageButton btonEmergencia;
    ImageButton btonRegresarEmergencia;
    String lat,lon,geom,id_persona,estado,direccion,fecha_emergencia,mensaje1;
    List<NameValuePair> params;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);


        btonEmergencia=(ImageButton)findViewById(R.id.imageButtonEmergencia);



        btonRegresarEmergencia=(ImageButton)findViewById(R.id.imageButtonRegresarEmergencia);
       // String currentDateTimeString = DateFormat.getDateInstance().format(new Date());


        estado=("false");
        direccion=("movil direccion");
        fecha_emergencia=("2016-02-13");
        Intent intent=getIntent();
        Bundle extras =intent.getExtras();
        if (extras != null) {//ver si contiene datos
            id_persona=(String)extras.get("id");//Obtengo el nombre
            // cedula.setText(datoCedulae);
        }


        btonRegresarEmergencia.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {



                    Intent explicit_intent;
                    explicit_intent = new Intent(emergencia.this, menu.class);
                    explicit_intent.putExtra("id_persona",id_persona);

                    startActivity(explicit_intent);



            }
        });

        btonEmergencia.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {



                params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("id_persona", id_persona));
                params.add(new BasicNameValuePair("fecha", fecha_emergencia));
                params.add(new BasicNameValuePair("estado", estado));
                params.add(new BasicNameValuePair("direccion", direccion));
                params.add(new BasicNameValuePair("lat", lat));
                params.add(new BasicNameValuePair("lon", lon));
                params.add(new BasicNameValuePair("geom", geom));


                if(mensaje1=="ubicacion encontrada"){
                ServerRequest sr = new ServerRequest();
                sr.getJSON("http://192.168.100.118/api/emergencia",params);
                    mensaje1="la emregencia a sido enviada";
                    error();


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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
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
        emergencia emergencia;

        public emergencia getMainActivity() {
            return emergencia;
        }

        public void setMainActivity(emergencia emergencia) {
            this.emergencia = emergencia;
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
