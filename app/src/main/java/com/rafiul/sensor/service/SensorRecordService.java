package com.rafiul.sensor.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Handler;
import android.os.Looper;

import com.rafiul.sensor.db.SensorDBHelper;
import com.rafiul.sensor.db.SensorData;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SensorRecordService extends Service {

    private static final String CHANNEL_ID = "sensor_channel";
    private static final int NOTIFICATION_ID = 123;

    private SensorDBHelper dbHelper;
    private CompositeDisposable disposables = new CompositeDisposable();

    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable recordRunnable = new Runnable() {
        @Override
        public void run() {
            disposables.add(
                    Observable.fromCallable(() -> {
                        recordSensorData();
                        return true;
                    })
                            .subscribeOn(Schedulers.io())
                            .subscribe()
            );
            handler.postDelayed(this, TimeUnit.MINUTES.toMillis(5));
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, createNotification());
        dbHelper = new SensorDBHelper(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(recordRunnable);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }

    public void recordSensorData(float proximityValue,float lightValue, float[] accelerometerValues, float[] gyroscopeValues) {
        // Obtain sensor values
        long timestamp = System.currentTimeMillis();

        // Insert into the database using RxJava
        SensorData sensorData = new SensorData();
        sensorData.setTimestamp(timestamp);
        sensorData.setProximity(proximityValue);
        sensorData.setLight(lightValue);
        sensorData.setAccelerometerX(accelerometerValues[0]);
        sensorData.setAccelerometerY(accelerometerValues[1]);
        sensorData.setAccelerometerZ(accelerometerValues[2]);
        sensorData.setGyroscopeX(gyroscopeValues[0]);
        sensorData.setGyroscopeY(gyroscopeValues[1]);
        sensorData.setGyroscopeZ(gyroscopeValues[2]);

        disposables.add(
                dbHelper.insertSensorData(sensorData)
                        .subscribe()
        );
    }

    private Notification createNotification() {
        // Create and return a notification here
        // You can customize the notification based on your requirements
        return null;
    }

    private void createNotificationChannel() {
        // Create a notification channel for Android Oreo and above
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Sensor Channel",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
