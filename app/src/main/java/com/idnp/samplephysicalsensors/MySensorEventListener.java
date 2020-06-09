package com.idnp.samplephysicalsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class MySensorEventListener implements SensorEventListener {
    private static final String TAG = "MySensorEventListener";
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d(TAG,"Type Sensor:"+sensorEvent.sensor.getStringType());
        Log.d(TAG,"timestamp Sensor:"+sensorEvent.timestamp);
        Log.d(TAG,"accuracy Sensor:"+sensorEvent.accuracy);

        for (int i=0;i < sensorEvent.values.length;i++) {
            Log.d(TAG,"i:"+i+" value:"+sensorEvent.values[i]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

        Log.d(TAG, "onAccuracyChanged:"+sensor.getName());

    }
}
