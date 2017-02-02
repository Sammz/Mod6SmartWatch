package com.example.sam.mod6good;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends Activity implements View.OnClickListener {

    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.round_activity_options);

        buttonBack = (Button) findViewById(R.id.ButtonBack);
        buttonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ButtonBack:
                buttonBackClick();
                break;
        }
    }

    private void buttonBackClick() {
        this.finish();
    }
}
