package com.jagteshwar.tablayoutwithpageviwer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.jagteshwar.tablayoutwithpageviwer.MainActivity;
import com.jagteshwar.tablayoutwithpageviwer.R;
import com.jagteshwar.tablayoutwithpageviwer.fragments.FirstFragment;

public class MyProfileActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 9001;
    private static final int PLAY_SERVIES_ERROR_CODE = 9002;
    public static final String MY_TAG = "my tag";
    Toolbar toolbar;
    private GoogleMap googleMap;
    private boolean locationPermissionGranted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Log.d("my tag", "onCreated in myprofileactivity");

        this.getWindow().getDecorView().getWindowInsetsController().hide(
                android.view.WindowInsets.Type.statusBars());
        toolbar=findViewById(R.id.myProfileToolbar);

        toolbar.setNavigationIcon(R.drawable.ic_backarrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void initGoogleMap() {
        if(isServicesOk()){
            if(checkLocationPermission()){
                Toast.makeText(this, "Ready to mao", Toast.LENGTH_SHORT).show();
                Log.d(MY_TAG, "ready to map");
            }else{
                requestLocationPermission();
            }
        }
    }

    private boolean checkLocationPermission() {
            return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED;
    }

    private boolean isServicesOk() {

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int result= apiAvailability.isGooglePlayServicesAvailable(this);

        if(result== ConnectionResult.SUCCESS){
            return true;
        }else if(apiAvailability.isUserResolvableError(result)){
            Dialog dialog = apiAvailability.getErrorDialog(this, result, PLAY_SERVIES_ERROR_CODE, task->
                    Toast.makeText(this, "Dialog is cancelled by user.", Toast.LENGTH_SHORT).show());
            dialog.show();
        }else{
            Toast.makeText(this, "Play services are required by this application", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    private void requestLocationPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            locationPermissionGranted=true;
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            Log.d(MY_TAG, "Permission granted");
        }else{
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            Log.d(MY_TAG, "Permission denied");
        }
    }
    public void getLocation(View view) {
        initGoogleMap();
        Toast.makeText(this, "locationAAA", Toast.LENGTH_SHORT).show();
    }
}