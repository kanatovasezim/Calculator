package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.calculator.Model.Calculator;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator;
    private TextView text;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int [] numberIDs = new int[]{
                R.id.b0,
                R.id.b1,
                R.id.b2,
                R.id.b3,
                R.id.b4,
                R.id.b5,
                R.id.b6,
                R.id.b7,
                R.id.b8,
                R.id.b9
        };

        int [] actionIDs = new int[]{
                R.id.minus,
                R.id.plus,
                R.id.multiplication,
                R.id.division,
                R.id.AC,
                R.id.equals
        };
        text = findViewById(R.id.calculation);
        result = findViewById(R.id.result);
        calculator = new Calculator();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.numPressed(view.getId());
                text.setText(calculator.getText());
            }
        };

        View.OnClickListener actionOnclickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.actionPressed(view.getId());
                if (view.getId()==R.id.equals){
                    result.setText(calculator.getResult());
                }
                text.setText(calculator.getText());
            }
        };

        for (int i = 0; i < numberIDs.length; i++) {
            findViewById(numberIDs[i]).setOnClickListener(numberClickListener);
        }

        for (int i = 0; i < actionIDs.length; i++) {
            findViewById(actionIDs[i]).setOnClickListener(actionOnclickListener);
        }

        findViewById(R.id.AC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.reset();
                result.setText("");
                text.setText(calculator.getText());
            }
        });
    }
}
