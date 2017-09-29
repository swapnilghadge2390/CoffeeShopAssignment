package com.suma.cofeeshopfinder.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import com.suma.cofeeshopfinder.R;

import permission.auron.com.marshmallowpermissionhelper.ActivityManagePermission;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;

public class SplashActivity extends ActivityManagePermission {
    private AlertDialog alert;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void myThread() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MapsActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(alert!=null)
            alert.dismiss();
        checkPermission();

    }

    private void showGPSDisabledAlertToUser(){
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getString(R.string.gps_status_disabled_msg))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.gps_instruction_msg),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent callGPSSettingIntent = new Intent(
                                            android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivity(callGPSSettingIntent);
                                }
                            });
            alertDialogBuilder.setNegativeButton(getString(R.string.dialog_cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            alert = alertDialogBuilder.create();
            alert.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    //Check Premission for marshmallow
    public void checkPermission()
    {

        final String permissionAsk[] = {PermissionUtils.Manifest_ACCESS_FINE_LOCATION, PermissionUtils.Manifest_ACCESS_COARSE_LOCATION};
        askCompactPermissions(permissionAsk, new PermissionResult() {

            public void permissionGranted() {
                if(checkGpsAccessibleOrNot()) {
                    myThread();
                }
                else
                {
                    showGPSDisabledAlertToUser();
                }
            }


            public void permissionDenied() {

            }

            @Override
            public void permissionForeverDenied() {
                {
                    boolean isGranted = isPermissionsGranted(SplashActivity.this,permissionAsk);
                    if(!isGranted){
                        // showDialog();
                    }else {
                        System.out.println("IS GRANTED -- " + isGranted);
                    }
                }
            }
        });
    }


    //check gps is accessible or not
    private boolean checkGpsAccessibleOrNot()
    {
        boolean gpsOn = false;
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            gpsOn = true;
        }
        return gpsOn;
    }
}
