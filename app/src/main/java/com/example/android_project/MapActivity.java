package com.example.android_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.android_project.modle.StationCarburant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private StationCarburant selectedStation;
    private List<StationCarburant> allStations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Check what data was passed
        selectedStation = getIntent().getParcelableExtra("SELECTED_STATION");
        allStations = getIntent().getParcelableArrayListExtra("ALL_STATIONS");

        // Initialize the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Handle single station case
        if (selectedStation != null && selectedStation.getGeoPoint() != null) {
            LatLng stationLocation = new LatLng(selectedStation.getGeoPoint().getLat(), selectedStation.getGeoPoint().getLon());
            googleMap.addMarker(new MarkerOptions().position(stationLocation).title("Station: " + selectedStation.getNom()));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(stationLocation, 15));
        }

        // Handle multiple stations case
        if (allStations != null) {
            for (StationCarburant station : allStations) {
                if (station.getGeoPoint() != null) {
                    LatLng stationLocation = new LatLng(station.getGeoPoint().getLat(), station.getGeoPoint().getLon());
                    googleMap.addMarker(new MarkerOptions().position(stationLocation).title("Station: " + station.getNom()));
                }
            }
            // Optionally move the camera to a suitable location
            if (!allStations.isEmpty() && allStations.get(0).getGeoPoint() != null) {
                LatLng firstStationLocation = new LatLng(allStations.get(0).getGeoPoint().getLat(), allStations.get(0).getGeoPoint().getLon());
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstStationLocation, 10));
            }
        }
    }
}