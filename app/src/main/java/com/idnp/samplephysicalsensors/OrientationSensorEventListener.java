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
    private Sonido sonido;
    public OrientationSensorEventListener(Sonido sonido){
            this.sonido = sonido;
    }
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

            float z = orientationVals[0];
            float x = orientationVals[1];
            float y = orientationVals[2];

            Log.d(TAG, "z:"+z
                    + ", x:" + x
                    + ", y" + y);
            if(estaEnUnaSuperficiePlana(x,y)){
                sonido.reproducir();
            }
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "" + sensor.getName());
    }



    /* X si esta en una superficie plana perfecta cera 0*/
    /* Y si esta en una supefice plana perfecta cera 0 si esta BocaAbajo/BocaArriba/*/
    /* Y si esta en una supefice plana perfecta cera 180 si esta BocaAbajo/BocaArriba*/
    /*Se considera un margen de aceptacion de 5. Se aceptara como valido si esta ligeramente inclinado*/
    public boolean estaEnUnaSuperficiePlana(float x, float y){
        x = Math.abs(x);
        y = Math.abs(y);
        if(x >= 0 && x <= 5 && (y >= 0 && y <= 5 || y>=175 && y<=180))
            return true;
        return false;
    }

}
