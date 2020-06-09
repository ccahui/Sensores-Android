package com.idnp.samplephysicalsensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class OrientationSensorEventListener implements SensorEventListener {
    private static final String TAG = "OrientationSensorEventListener";
    private float[] accelValues;
    private float[] magValues;
    private float[] orientationVals = new float[3];
    private int counter = 0;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Log.d(TAG, "counter " + counter);
        counter++;

        switch (sensorEvent.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
            {
                accelValues = sensorEvent.values.clone();
                break;
            }
            case Sensor.TYPE_MAGNETIC_FIELD:
            {
                magValues = sensorEvent.values.clone();
                break;
            }
        }

        float[] rotationMatrix = new float[16];
        if (magValues != null && accelValues != null) {

            SensorManager.getRotationMatrix(rotationMatrix, null, accelValues, magValues);

            SensorManager.getOrientation(rotationMatrix, orientationVals);

            orientationVals[0] = (float) Math.toDegrees(orientationVals[0]);
            orientationVals[1] = (float) Math.toDegrees(orientationVals[1]);
            orientationVals[2] = (float) Math.toDegrees(orientationVals[2]);



            Log.d(TAG, "z:"+orientationVals[0]
                    + ", x:" + orientationVals[1]
                    + ", y" + orientationVals[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "" + sensor.getName());

    }
}
