package com.rafiul.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.rafiul.sensor.databinding.ActivityMainBinding;
import com.rafiul.sensor.service.SensorRecordService;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ActivityMainBinding activityMainBinding;
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private Sensor accelerometer;
    private Sensor lightSensor;
    private Sensor gyroscope;

    private SensorRecordService sensorRecordService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float proximityValue=0;
        float lightSensorValue=0;
        float[] accelerometerValue={};
        float[] gyroscopeValue={};
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximityValue = sensorEvent.values[0];
            activityMainBinding.tvProximitySensor.setText("PROXIMITY SENSOR: " + proximityValue);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            lightSensorValue = sensorEvent.values[0];
            activityMainBinding.tvLightSensor.setText("LIGHT SENSOR: " + lightSensorValue);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
             accelerometerValue = sensorEvent.values;
            activityMainBinding.tvAccelerometer.setText("ACCELEROMETER: X=" + accelerometerValue[0] +
                    "\nY=" + accelerometerValue[1] +
                    "\nZ=" + accelerometerValue[2]);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyroscopeValue = sensorEvent.values;
            activityMainBinding.tvGyroscope.setText("GYROSCOPE: X=" + gyroscopeValue[0] +
                    "\nY=" + gyroscopeValue[1] +
                    "\nZ=" + gyroscopeValue[2]);
        }

        sensorRecordService.recordSensorData(proximityValue,lightSensorValue,accelerometerValue,gyroscopeValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (proximitySensor != null) {
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (gyroscope != null) {
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}