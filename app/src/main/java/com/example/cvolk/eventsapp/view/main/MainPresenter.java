package com.example.cvolk.eventsapp.view.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;
import android.util.Log;
import com.example.cvolk.eventsapp.managers.*;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.content.ContentValues.TAG;

public class MainPresenter implements MainContract.Presenter{

    MainContract.View view;

    PermissionManager permissionManager;
    LocationManager locationManager;
    FusedLocationProviderClient locationProviderClient;
    Location currentLocation;

    private String oAuthToken = "56NCFXDAETBIW35BIU3E";

    public MainPresenter(PermissionManager permissionManager, LocationManager locationManager) {

        permissionManager.attach(this);
        locationManager.attach(this);

        this.permissionManager = permissionManager;
        this.locationManager = locationManager;

        permissionManager.checkPermission();
    }

    @Override
    public void attachView(MainContract.View view) {

        this.view = view;
    }

    @Override
    public void detachView() {

        this.view = null;
    }

    @Override
    public void checkPermission() {

        permissionManager.checkPermission();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void getLocation() {

        Log.d(TAG, "getLocation: ");

        // think this is not good practice...? (casting as activity)
        locationProviderClient = LocationServices.getFusedLocationProviderClient((Activity)view);

        locationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess(Location location) {
                        Log.d(TAG, "onSuccess: " + location.toString());

                        currentLocation = location;

                        view.onGetLocation(location);
                    }
                });
    }

    @Override
    public void getEvents() {

        Log.d(TAG, "getEvents: ");


    }
}
