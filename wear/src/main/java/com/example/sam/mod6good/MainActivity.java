package com.example.sam.mod6good;


import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends WearableActivity implements View.OnClickListener, SensorEventListener {


    private static final String TAG = "MainActivity";
    private TextView mTextViewHeart;
    Button button2;
    private long currentTime;

// adb -e logcat MainActivity:i *:S > mod6log.txt




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAmbientEnabled();

        setContentView(R.layout.round_activity_main);

        button2 = (Button) findViewById(R.id.ButtonOptions);
        button2.setOnClickListener(this);
        mTextViewHeart = (TextView) findViewById(R.id.heart);
        SensorManager mSensorManager = ((SensorManager)getSystemService(SENSOR_SERVICE));
        Sensor mHeartRateSensor = mSensorManager.getDefaultSensor(21);
        mSensorManager.registerListener(this, mHeartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.i("", "LISTENER REGISTERED.");
        mTextViewHeart.setText("Stressmeter");

        currentTime = System.currentTimeMillis();
        Log.d("timestamp: ", ""+(currentTime/1000000));
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
            String msg = "" + (int)event.values[0] + " ";
            mTextViewHeart.setText(msg);
            String time = "" + (System.currentTimeMillis() - currentTime)/1000;
            Log.i(TAG, msg + " " + time);

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
