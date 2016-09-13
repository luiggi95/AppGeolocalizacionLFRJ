package com.facci.geolocalizacionlfrj;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class GeoLocalizadorLFRJ extends AppCompatActivity {
    LocationManager locManager;
    private double latitudLR;
    private double longitudLR;
    private double altitudLR;
    private float velocidadLR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_localizador_lfrj);


        locManager=(LocationManager)getSystemService(LOCATION_SERVICE);

        List<String> listaProviders=locManager.getAllProviders();

        LocationProvider provider=locManager.getProvider(listaProviders.get(0));
    }
    public void ActualizarLatLongClick(View v){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
        )!= PackageManager.PERMISSION_GRANTED){
        }
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);
    }
    private final LocationListener locationListenerGPS =new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            longitudLR = location.getLongitude();
            latitudLR = location.getLatitude();
            altitudLR = location.getAltitude();
            velocidadLR =location.getSpeed();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText)findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText)findViewById(R.id.txtLatitud);
                    EditText txtAltitud = (EditText)findViewById(R.id.txtAltitud);
                    EditText txtVelocidad = (EditText)findViewById(R.id.txtVelocidad);
                    txtLatitud.setText(latitudLR+"");
                    txtLongitud.setText(String.valueOf(longitudLR));
                    txtAltitud.setText(String.valueOf(altitudLR));
                    txtVelocidad.setText(String.valueOf(velocidadLR));
                }
            });
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
