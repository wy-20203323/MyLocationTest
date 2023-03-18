package com.application.mylocationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    EditText show;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = findViewById(R.id.show);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000, 8, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

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

                });
        Location location = locationManager.getLastKnownLocation(
                LocationManager.GPS_PROVIDER
        );
        locationUpdates(location);
    }

    @SuppressLint("SetTextI18n")
    private void locationUpdates(Location location) {
        if (location != null) {
            String sb = "实时位置信息：\n" +
                    "经度：" +
                    location.getLongitude() +
                    "\n纬度" +
                    location.getLatitude() +
                    "\n高度" +
                    location.getAltitude() +
                    "\n速度" +
                    location.getSpeed() +
                    "\n方向" +
                    location.getBearing();
            show.setText(sb);
        } else {
            show.setText("It's difficult to get.");
        }
    }
}
