package com.example.omer.mobileprogramming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.omer.mobileprogramming.model.hava.Hava;

public class Show_Forecast extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__forecast);


        TextView temp=findViewById(R.id.temp);
        TextView humidity=findViewById(R.id.humidity);
        TextView temp_max=findViewById(R.id.temp_max);
        TextView temp_min=findViewById(R.id.temp_min);
        TextView wind=findViewById(R.id.wind_speed);
        TextView cloud=findViewById(R.id.cloud);



        temp.setText("SICAKLIK: "+getIntent().getExtras().getString("temp"));
        humidity.setText("NEM :"+getIntent().getExtras().getString("humidity"));
        temp_max.setText("MAX SICAKLIK :"+getIntent().getExtras().getString("temp_max"));
        temp_min.setText("MIN SICAKLIK :"+getIntent().getExtras().getString("temp_min"));
        cloud.setText("BULUT ORANI :"+getIntent().getExtras().getString("cloud"));
        wind.setText("RÜZAR HIZI :"+getIntent().getExtras().getString("wind_speed"));






        }




    }

