package com.example.sam.mod6good;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener, SensorEventListener {


    private static final String TAG = "MainActivity";
    private TextView mTextViewHeart;
    Button button2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_main);


        button2 = (Button) findViewById(R.id.ButtonOptions);
        button2.setOnClickListener(this);
        mTextViewHeart = (TextView) findViewById(R.id.heart);
        SensorManager mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i(TAG, "LISTENER REGISTERED.");
        mTextViewHeart.setText("Something here");

        for (Sensor sensor : mSensorManager.getSensorList(Sensor.TYPE_ALL)) {
            Log.e("HB Service", sensor.getName() + ": " + sensor.getType());
        }
       mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ButtonOptions:
                button2Click();
                break;
        }
    }

    private void button2Click() {
        startActivity(new Intent("com.example.sam.mod6good.Options"));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_HEART_RATE) {
            String msg = "" + (int)event.values[0];
            mTextViewHeart.setText(msg);
            Log.d(TAG, msg);
        }
        else
            Log.d(TAG, "Unknown sensor type");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged - accuracy: " + accuracy);
    }

    public void onResume(){
        super.onResume();
    }
}
