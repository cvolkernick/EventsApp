package com.example.cvolk.eventsapp.view.main;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cvolk.eventsapp.managers.*;

import com.example.cvolk.eventsapp.R;
import com.example.cvolk.eventsapp.model.Pagination;

public class MainActivity extends AppCompatActivity implements MainContract.View , PermissionManager.IPermissionInteraction {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";

    //private PermissionManager permissionManager;
    //private FusedLocationProviderClient locationProviderClient;

    private TextView tvLocation;

    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        mainPresenter = new MainPresenter(
                PermissionManager.getDefault(this),
                LocationManager.getDefault(this)
        );

//        permissionManager = new PermissionManager(this);
//        permissionManager.checkPermission();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mainPresenter.attachView(this);
        mainPresenter.checkPermission();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachView();
    }

    private void bindViews() {

        tvLocation = findViewById(R.id.tvLocation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionManager.getDefault(this).checkResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onCheckPermission(boolean isEnabled) {
        if (isEnabled) {
            mainPresenter.getLocation();
        }
    }

    @Override
    public void onGetLocation(Location location) {

        String locationString = location.getLatitude() + " : " + location.getLongitude();
        tvLocation.setText(locationString);
    }

    @Override
    public void onGetEvents(Pagination pagination) {


    }

    @Override
    public void showError(String error) {

        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionResult(boolean isGranted) {
        Log.d(TAG, "onPermissionResult: " + isGranted);
        if (mainPresenter != null && isGranted) {
            mainPresenter.getLocation();
        }
    }
}
