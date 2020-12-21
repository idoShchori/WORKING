package com.example.hw1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Fragment_map extends Fragment implements  OnMapReadyCallback {

    private MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_map,container,false);
        findviews(view);
        initViews();
        mMapView.getMapAsync(this) ;
        return view;
    }

    private void findviews(View view) {
        mMapView = (MapView) view.findViewById(R.id.mapView);
    }

    private void initViews() {
        mMapView.onCreate(null);
        mMapView.onResume(); // needed to get the map to display immediately

    }
    public void showLocation(double latitude,double longitude){
        LatLng point = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(point).title("current location"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(point));
    }

    @Override
    public void onMapReady(GoogleMap mMap) {
        this.googleMap = mMap;
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
