package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, mLight, mPressure, mTemp, mHumi;

    TextView xValue, yValue, zValue;
    TextView xGValue, yGValue, zGValue;
    TextView xMValue, yMValue, zMValue;
    TextView light, pressure, temperature, humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGValue = (TextView) findViewById(R.id.xGValue);
        yGValue = (TextView) findViewById(R.id.yGValue);
        zGValue = (TextView) findViewById(R.id.zGValue);

        xMValue = (TextView) findViewById(R.id.xMValue);
        yMValue = (TextView) findViewById(R.id.yMValue);
        zMValue = (TextView) findViewById(R.id.zMValue);

        light = (TextView) findViewById(R.id.light);
        pressure = (TextView) findViewById(R.id.pressure);
        temperature = (TextView) findViewById(R.id.temp);
        humidity = (TextView) findViewById(R.id.humidity);

        Log.d(TAG, "onCreate: Initializing Sensor Services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(accelerometer!=null){
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Accelerometer Listener Registered");
        }
        else{
            xValue.setText("Null");
            yValue.setText("Null");
            zValue.setText("Null");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(mGyro!=null){
            sensorManager.registerListener(MainActivity.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Gyroscope Listener Registered");
        }
        else{
            xGValue.setText("Null");
            yGValue.setText("Null");
            zGValue.setText("Null");
        }

        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mMagno!=null){
            sensorManager.registerListener(MainActivity.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Magnetometer Listener Registered");
        }
        else{
            xMValue.setText("Null");
            yMValue.setText("Null");
            zMValue.setText("Null");
        }

        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(mLight!=null){
            sensorManager.registerListener(MainActivity.this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Light Listener Registered");
        }
        else{
            light.setText("Light: Null");
        }

        mPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if(mPressure!=null){
            sensorManager.registerListener(MainActivity.this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Pressure Listener Registered");
        }
        else{
            pressure.setText("Pressure: Null");
        }

        mTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if(mTemp!=null){
            sensorManager.registerListener(MainActivity.this, mTemp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Temperature Listener Registered");
        }
        else{
            temperature.setText("Temp: Null");
        }

        mHumi = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if(mHumi!=null){
            sensorManager.registerListener(MainActivity.this, mHumi, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Humidity Listener Registered");
        }
        else{
            humidity.setText("Humidity: Null");
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);

            xValue.setText(" X: " + sensorEvent.values[0]  + " m/s2");
            yValue.setText(" Y: " +  sensorEvent.values[1] + " m/s2");
            zValue.setText(" Z: " + sensorEvent.values[2] + " m/s2");
        }

        else if(sensor.getType()==Sensor.TYPE_GYROSCOPE){
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);

            xGValue.setText(" X: " + sensorEvent.values[0] +" rad/s");
            yGValue.setText(" Y: " +  sensorEvent.values[1] + " rad/s");
            zGValue.setText(" Z: " + sensorEvent.values[2] + " rad/s");
        }

        else if(sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            Log.d(TAG, "onSensorChanged: X: " + sensorEvent.values[0] + " Y: " + sensorEvent.values[1] + " Z: " + sensorEvent.values[2]);

            xMValue.setText(" X: " + sensorEvent.values[0] + " uT");
            yMValue.setText(" Y: " +  sensorEvent.values[1] + " uT");
            zMValue.setText(" Z: " + sensorEvent.values[2] + " uT");
        }

        else if(sensor.getType()==Sensor.TYPE_LIGHT){
            light.setText("Light: " + sensorEvent.values[0] + " lx");
        }

        else if(sensor.getType()==Sensor.TYPE_PRESSURE){
            pressure.setText("Pressure: " + sensorEvent.values[0]);
        }

        else if(sensor.getType()==Sensor.TYPE_AMBIENT_TEMPERATURE){
            temperature.setText("Temperature: " + sensorEvent.values[0]);
        }

        else if(sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY){
            humidity.setText("Humidity: " + sensorEvent.values[0]);
        }

    }
}
