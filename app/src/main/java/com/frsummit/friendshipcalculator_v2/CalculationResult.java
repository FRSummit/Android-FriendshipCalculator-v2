package com.frsummit.friendshipcalculator_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculationResult extends Activity {
    TextView calcResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_result);

        String arg = getIntent().getExtras().getString("result");

        calcResult = (TextView) findViewById(R.id.calculation_result_text);
        calcResult.setText("Your friendship result is " + arg + "%");
    }

    public void newCalcBtn(View view) {
        Intent intent = new Intent(this, Calculation.class);
        startActivity(intent);
    }
}
