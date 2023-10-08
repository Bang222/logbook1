package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    MaterialButton btn9, btn8, btn7, btn6, btn5, btn4, btn3, btn2, btn1, btn0;
    MaterialButton btnPlus, btnTimes, btnMinus, btnClear, btnEqual, btnDivide;

    //create contains variable input
    private String input, outPut, newOutPut;
    TextView resultsView, dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultsView = findViewById(R.id.resultsView);
        dataView = findViewById(R.id.dataView);
//
        btn9 = findViewById(R.id.btn9);
        btn8 = findViewById(R.id.btn8);
        btn7 = findViewById(R.id.btn7);
        btn6 = findViewById(R.id.btn6);
        btn5 = findViewById(R.id.btn5);
        btn4 = findViewById(R.id.btn4);
        btn3 = findViewById(R.id.btn3);
        btn2 = findViewById(R.id.btn2);
        btn1 = findViewById(R.id.btn1);
        btn0 = findViewById(R.id.btn0);

        btnPlus = findViewById(R.id.btnPlus);
        btnTimes = findViewById(R.id.btnTimes);
        btnMinus = findViewById(R.id.btnMinus);
        btnClear = findViewById(R.id.btnClear);
        btnEqual = findViewById(R.id.btnEqual);
        btnDivide = findViewById(R.id.btnDivide);
    }

    public void onButtonClicked(View view) {
        MaterialButton button = (MaterialButton) view;
        String data = button.getText().toString();
        switch (data) {
            case "C":
                input = null;
                outPut = null;
                newOutPut = null;
                resultsView.setText("");
                dataView.setText("");
                resultsView.setVisibility(View.GONE);
                break;
            case "=":
                solve();
                break;
            default:
                if (input == null) {
                    input = "";
                }
                if (data.equals("*") || data.equals("+") || data.equals("/") || data.equals("-")) {
                    if (resultsView.getText().toString().isEmpty()) {
                        solve();
                    } else {
                        solve();
                        input = resultsView.getText().toString();
                    }
                }
//                if (outPut == null && input.matches(".*[0-9].*") &&
//                        (input.split("\\+").length == 2 || input.split("\\-").length == 3
//                                || input.split("\\-").length == 2 || input.split("\\*").length == 2 || input.split("\\/").length == 2)) {
//                    System.out.println(outPut);
//                    input = "";
//                    outPut = "";
//                    dataView.setText("");
//                    resultsView.setText("");
//                }
                input += data;
        }
        if(data.equals("=")){
            if(newOutPut == null) {
                dataView.setText(input);
            }else{
                dataView.setText(newOutPut);
            }

        }else {
            dataView.setText(input);
        }
    }

    private void solve() {
        if (input == null) {
            input = "";
        }
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double num = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                outPut = Double.toString(num);
                resultsView.setText(outPut);
                newOutPut = numbers[0] + "+" + numbers[1];
                input = outPut;
                resultsView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                resultsView.setError(e.getMessage().toString());
            }
        } else if (input.split("\\-").length == 3) {
            try {
                String numbers[] = input.split("\\-");
                double num = Double.parseDouble(numbers[1]) + Double.parseDouble(numbers[2]);
                outPut = Double.toString(num);
                resultsView.setText("-" + outPut);
                newOutPut = "-" + numbers[1] + "-" + numbers[2];
                input = "-" + outPut;
                resultsView.setVisibility(View.VISIBLE);
            } catch(Exception e){
                System.out.println(e);
            }
        }
        else if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (!numbers[0].isEmpty() && Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])) {
                    double num = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    outPut = Double.toString(num);
                    resultsView.setText("-" + outPut);
                    input = "-" +outPut;
                    newOutPut = numbers[0] + "-" + numbers[1];
                    resultsView.setVisibility(View.VISIBLE);
                }
                if(numbers[1].split("\\/").length == 2 && numbers[0].isEmpty() == true){
                    String divisionNagative[] = numbers[1].split("\\/");
                    double num = Double.parseDouble(divisionNagative[0]) / Double.parseDouble(divisionNagative[1]);
                    outPut = Double.toString(num);
                    resultsView.setText("-"+outPut);
                    newOutPut = "-" + divisionNagative[0] + "/" + divisionNagative[1];
                    input = "-" + outPut;
                    resultsView.setVisibility(View.VISIBLE);
                }
                else if (Double.parseDouble(numbers[0]) > Double.parseDouble(numbers[1]) && numbers[0].isEmpty() == false) {
                    double num = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    outPut = Double.toString(num);
                    resultsView.setText(outPut);
                    input = outPut;
                    newOutPut = numbers[0] + "-" + numbers[1];
                    resultsView.setVisibility(View.VISIBLE);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double num = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                outPut = Double.toString(num);
                resultsView.setText(outPut);
                input = outPut;
                newOutPut = numbers[0] + "*" + numbers[1];
                resultsView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                resultsView.setError(e.getMessage().toString());
            }
        }
        else if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double num = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                outPut = Double.toString(num);
                resultsView.setText(outPut);
                newOutPut = numbers[0] + "/" + numbers[1];
                input = outPut;
                resultsView.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                resultsView.setError(e.getMessage().toString());
            }
        }
    }
}