package com.example.calculator.Model;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.calculator.Enum.State;
import com.example.calculator.R;

public class Calculator {
    double val1;
    double val2;
    private State state;
    private int btn;
    StringBuilder inputString = new StringBuilder();
    double result;

    public Calculator(){
        state = State.VAL1;
    }

    public void numPressed(int btnID){
        if (state == State.RESULT) {
            state = State.VAL1;
            inputString.setLength(0);
        }

        if (state == State.OPERATION) {
            state = State.VAL2;
            inputString.setLength(0);
        }

        if (inputString.length() < 9) {
            switch (btnID) {
                case R.id.b0:
                    if (inputString.length() != 0) {
                        inputString.append("0");
                    }
                    break;
                case R.id.b1:
                    inputString.append("1");
                    break;
                case R.id.b2:
                    inputString.append("2");
                    break;
                case R.id.b3:
                    inputString.append("3");
                    break;
                case R.id.b4:
                    inputString.append("4");
                    break;
                case R.id.b5:
                    inputString.append("5");
                    break;
                case R.id.b6:
                    inputString.append("6");
                    break;
                case R.id.b7:
                    inputString.append("7");
                    break;
                case R.id.b8:
                    inputString.append("8");
                    break;
                case R.id.b9:
                    inputString.append("9");
                    break;
            }
        }
    }

    public void actionPressed(int btnID){
        if (btnID == R.id.equals && state == State.VAL2 && inputString.length() > 0) {
            val2 = Integer.parseInt(inputString.toString());
            state = State.RESULT;
            Log.v("state", state.toString());
            inputString.setLength(0);
            switch (btn) {
                case R.id.plus:
                    result = val1 + val2;
                    break;
                case R.id.minus:
                    result = val1 - val2;
                    break;
                case R.id.multiplication:
                    result = val1 * val2;
                    break;
                case R.id.division:
                    result = val1 / val2;
                    break;
            }

        } else if (inputString.length() > 0 && state == State.VAL1) {
            val1 = Integer.parseInt(inputString.toString());
            state = State.OPERATION;
            btn = btnID;
        }
    }

    public String getText() {
        StringBuilder str = new StringBuilder();
        switch (state) {
            default:
                return inputString.toString();
            case OPERATION:
                return str.append(String.format("%.0f", val1))
                        .append(' ')
                        .append(getOperationChar())
                        .toString();
            case VAL2:
                return str.append(String.format("%.0f", val1))
                        .append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(inputString)
                        .toString();
            case RESULT:
                return str.append(String.format("%.0f", val1))
                        .append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(String.format("%.0f", val1))
                        .toString();
        }
    }

    public String getResult(){
        return String.format("%.4f", result);
    }

    private char getOperationChar() {
        switch (btn) {
            case R.id.plus:
                return '+';
            case R.id.minus:
                return '-';
            case R.id.multiplication:
                return '*';
            case R.id.division:
                return '/';
            default:
                return ' ';
        }
    }

    public void reset() {
        state = State.VAL1;
        inputString.setLength(0);
    }
}
