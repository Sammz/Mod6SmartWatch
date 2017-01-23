package com.example.sam.mod6good;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StressPopup extends Activity implements View.OnClickListener {

    private TextView mTextView;
    Button yes;
    Button no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_stress_popup);
        yes = (Button) findViewById(R.id.yes);
        yes.setOnClickListener(this);
        no = (Button) findViewById(R.id.no);
        no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.yes:
                buttonYesClick();
                break;
            case R.id.no:
                buttonNoClick();
                break;
        }
    }

    private void buttonYesClick() {


    }

    private void buttonNoClick() {
        this.finish();

    }
}