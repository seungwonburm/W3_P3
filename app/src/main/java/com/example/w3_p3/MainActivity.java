package com.example.w3_p3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {


    SeekBar celsiusSeekBar;
    SeekBar farenSeekBar;
    TextView celsiusText;
    TextView farenText;
    TextView message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.celsiusSeekBar = (SeekBar)findViewById(R.id.celsiusSeekBar);
        this.farenSeekBar = (SeekBar)findViewById(R.id.farenSeekBar);
        this.celsiusText = (TextView) findViewById(R.id.celsiusText);
        this.farenText = (TextView) findViewById(R.id.farenText);
        this.message =(TextView)findViewById(R.id.message);


        int celsius =0;
        int faren=32;


        this.celsiusSeekBar.setMax(100);
        this.farenSeekBar.setMax(212);

        this.celsiusSeekBar.setProgress(0);
        this.farenSeekBar.setProgress(32);

        this.celsiusText.setText(celsius + ".00 °C");
        this.farenText.setText(faren + ".00 °F");
        this.message.setText("Interesting Message");

        celsiusSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int result;
                    int celsiusFinal;
                    int stepSize =1;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        i = ((int) Math.round(i/stepSize))*stepSize;
                        seekBar.setProgress(i);

                        celsiusFinal = i + celsius;
                        celsiusText.setText((celsiusFinal) + ".00 °C");
//                        celsiusText.setText(String.format("%.2f", ((celsiusFinal) + " °C")));
                        result = i;
                        double fraction = 9d/5d;
                        farenSeekBar.setProgress((int)((celsiusFinal)*(fraction))+32);
                        farenText.setText(String.format("%.2f", (celsiusFinal)*(fraction)+32) + " °F");

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (celsius + result < 20){
                            message.setText("I wish it were warmer.");
                        }else{
                            message.setText("I wish it were colder.");
                        }
                    }
                }
        );

        farenSeekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int result, stepSize=1;
                    @Override

                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                        i = ((int) Math.round(i/stepSize))*stepSize;
//                        seekBar.setProgress(i);


                        int farenFinal = faren+(i-faren);
                        farenText.setText((farenFinal) + ".00 °F");
                        result=i;
                        double fraction = 5d/9d;
                        celsiusSeekBar.setProgress((int)((farenFinal-32)*(fraction)));
//                        celsiusText.setText(String.format("%.2f", (farenFinal-32)*(fraction)) + " °C");



                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        if (faren+(result-faren) < 32){
                            farenSeekBar.setProgress(32);
                            farenText.setText(32 + ".00 °F");
                        }

                        if (faren+(result-faren) < 68){
                            message.setText("I wish it were warmer.");
                        }else {
                            message.setText("I wish it were colder.");
                        }


                    }
                }
        );

    }


}