package com.idnp.samplephysicalsensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : sensorList) {
            Log.d(TAG, "Name:"+s.getName()
                    + ", Type:" + s.getStringType()
                    + ", Vendor:" + s.getVendor()
                    + ", Version:" + s.getVersion()
                    + ", MaximumRange:" + s.getMaximumRange()
                    + ", MinDelay:" + s.getMinDelay()
                    + ", MaxDelay:" + s.getMaxDelay()
                    + ", Power:" + s.getPower());
        }

        //sampleAnySensor(Sensor.TYPE_LIGHT);
        //sampleAnySensor(Sensor.TYPE_PROXIMITY);
        //sampleAnySensor(Sensor.TYPE_PRESSURE); //no supported
        //sampleAnySensor(Sensor.TYPE_RELATIVE_HUMIDITY); //no supported
        //sampleAnySensor(Sensor.TYPE_AMBIENT_TEMPERATURE);//no supported
        sampleOrientation();
    }

    public void sampleAnySensor(int TYPE_SENSOR){
        if(isSensorSupported(TYPE_SENSOR)==false){
            Log.d(TAG,"no supported");
        }

        MySensorEventListener mySensorEventListener=new MySensorEventListener();

        Sensor sensor=sensorManager.getDefaultSensor(TYPE_SENSOR);
        sensorManager.registerListener(
                mySensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void sampleOrientation(){

        OrientationSensorEventListener mySensorEventListener=new OrientationSensorEventListener();

        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(
                mySensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        Sensor sensor1=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(
                mySensorEventListener,
                sensor1,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public boolean isSensorSupported(int SENSOR_TYPE)
    {
        SensorManager sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(SENSOR_TYPE);
        return sensors.size() > 0;
    }
}
