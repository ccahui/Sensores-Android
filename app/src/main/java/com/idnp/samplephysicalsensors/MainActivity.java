package com.idnp.samplephysicalsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Sonido {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor sensor;
    private MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mp = MediaPlayer.create(this, R.raw.sonido);
        sampleOrientation();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.release();
        mp = null;
    }

    public void sampleOrientation(){

        OrientationSensorEventListener mySensorEventListener = new OrientationSensorEventListener(this);

        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(
                mySensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        Sensor sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(
                mySensorEventListener,
                sensor1,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void reproducir() {
        Log.i(TAG, "Lanzando Sonido");
        mp.start();

    }
}
