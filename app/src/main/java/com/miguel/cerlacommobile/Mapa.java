package com.miguel.cerlacommobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.miguel.cerlacommobile.Controladores.Alert_dialog;
import com.miguel.cerlacommobile.Controladores.Ctl_agendar;
import com.miguel.cerlacommobile.Controladores.Progress_dialog;
import com.miguel.cerlacommobile.Objetos.Agendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {

    private static Double LATITUD, LONGITUD;
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    Boolean mLocationPermissionsGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    FusedLocationProviderClient fusedLocationProviderClient;
    GoogleMap mMap;

    Ctl_agendar ctlAgendar;
    Alert_dialog alertDialog;
    Progress_dialog dialog;
    Button btn_agendar;
    EditText editText_inconveniente;

    Toolbar toolbar ;


    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setOnClickListener(view -> finish());

        btn_agendar = findViewById(R.id.btn_agendar);
        editText_inconveniente = findViewById(R.id.editText_inconveniente);

        ctlAgendar = new Ctl_agendar(MainActivity.databaseReference);

        dialog = new Progress_dialog(this);
        alertDialog = new Alert_dialog(this);

        firebaseUser = MainActivity.auth.getCurrentUser();

        getLocationPermission();

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (mLocationPermissionsGranted) {

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            getDeviceLocation();
            init();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        mLocationPermissionsGranted = true;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {

                    for (int result: grantResults) {
                        if(result != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            break;
                        }
                    }

                    if(mLocationPermissionsGranted){

                        activarGPS();

                        if(mLocationPermissionsGranted){

                            initMap();
                        }

                    }else{

                        alertDialog.crear_mensaje("Advertencia", "Debes ACTIVAR el Permiso de Ubicación", builder -> {
                            builder.setNeutralButton("Cambiar Permisos de Ubicación", (dialogInterface, i) -> {
                                finish();
                                startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:"+getPackageName())));
                            });
                            builder.setCancelable(false);
                            builder.create().show();
                        });

                    }

                }

            } break;

        }
    }

    private void init(){
        mMap.clear();
        getDeviceLocation();
    }

    private void initMap() {

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }

    private void moveCamera(LatLng latLng, float zoom, String title) {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
        MarkerOptions options = new MarkerOptions().position(latLng).title(title).draggable(false);
        Objects.requireNonNull(mMap.addMarker(options)).showInfoWindow();

    }

    //Activar permisos de locación
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                activarGPS();

                if(mLocationPermissionsGranted){
                    initMap();
                }

            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    //Activación del GPS
    private void activarGPS(){

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER )) {

            mLocationPermissionsGranted = false;

            alertDialog.crear_mensaje("Advertencia", "¡El GPS está DESACTIVADO!", builder -> {
                builder.setPositiveButton("Activar GPS", (dialogInterface, i) -> {
                    finish();
                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                });
                builder.setNegativeButton("Cancelar", (dialogInterface, i) -> {finish();});
                builder.setCancelable(false);
                builder.create().show();
            });

        }else{
            mLocationPermissionsGranted = true;

        }

    }


    //Obtener ubicacion del dispositivo
    private void getDeviceLocation() {
        try {

            if (mLocationPermissionsGranted) {

                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {

                    if (task.isSuccessful()) {

                        Location currLocation = (Location) task.getResult();

                        if(currLocation != null){

                            LATITUD = currLocation.getLatitude();
                            LONGITUD = currLocation.getLongitude();

                            moveCamera(new LatLng(currLocation.getLatitude(), currLocation.getLongitude()), DEFAULT_ZOOM,"Mi Ubicación");

                            if (firebaseUser != null) {

                                btn_agendar.setOnClickListener(v -> {

                                    if (!editText_inconveniente.getText().toString().isEmpty()){

                                        Agendar ob_agendar = new Agendar();
                                        ob_agendar.inconveniente = editText_inconveniente.getText().toString();

                                        ctlAgendar.agendar_cita(firebaseUser.getUid(), ob_agendar, LATITUD, LONGITUD);


                                        Toast.makeText(this,"Agendamiento realizado, un operador se pondra en contacto con usd dentro de pronto", Toast.LENGTH_SHORT).show();
                                    } else{

                                        Toast.makeText(this,"Complete los campos requeridos", Toast.LENGTH_SHORT).show();
                                    }


                                });

                            }

                        }else{

                            alertDialog.crear_mensaje("Error al Obtener la Ubicación", "Activa los permisos de ubicación", builder -> {
                                builder.setPositiveButton("Activar GPS", (dialogInterface, i) -> {
                                    finish();
                                    startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                });
                                builder.setNegativeButton("Cancelar", (dialogInterface, i) -> {finish();});
                                builder.setCancelable(false);
                                builder.create().show();
                            });

                        }
                    }

                });

            }

        } catch (SecurityException e) {
            alertDialog.crear_mensaje("Error al Obtener la Ubicación", Objects.requireNonNull(e.getLocalizedMessage()), builder -> {
                builder.setCancelable(false);
                builder.setNeutralButton("Aceptar", (dialogInterface, i) -> finish());
                builder.create().show();
            });
        }

    }


}