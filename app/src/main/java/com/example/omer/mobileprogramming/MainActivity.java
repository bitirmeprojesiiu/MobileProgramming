package com.example.omer.mobileprogramming;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omer.mobileprogramming.model.hava.Hava;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//https://api.nasa.gov/planetary/apod?api_key=jCGyx9nR5vW6y28vjvowA4TXHVmwkxb8kTIZfsGp


public class MainActivity extends AppCompatActivity implements LocationListener {
   ArrayList<String> s=new ArrayList<>();
    protected Context context;
    int latitude, longitude;
    TextView tw,tw2;
    String provider;
    Bitmap bitmap;
    int a;

    String ab;
    Hava hava=new Hava();
    LocationManager locationManager;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         Button mute=findViewById(R.id.mute);
         Button chngeWallp=findViewById(R.id.lovemom);
         Button havatah = findViewById(R.id.havatah);
          tw2 = (TextView)findViewById(R.id.tw2);
          tw = (TextView)findViewById(R.id.tw);

      locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria=new Criteria();
      provider=locationManager.getBestProvider(criteria,false);
      Location location=locationManager.getLastKnownLocation(provider);
      if(location!=null){
       onLocationChanged(location);
      }
      else{
          //lokasyon unavailable yada null olması durumunda
          latitude=0;
          longitude=0;
      }

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        havatah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(latitude!=0 && longitude!=0 ){
                    new GetForeCastAsync().execute(latitude,longitude);

                    Intent i=new Intent(MainActivity.this,Show_Forecast.class);

                    i.putExtra("temp",s.get(2));
                    i.putExtra("humidity",s.get(1));
                    i.putExtra("temp_max",s.get(0));
                    i.putExtra("temp_min",s.get(3));
                    i.putExtra("wind_speed",s.get(4));
                    i.putExtra("cloud",s.get(5));
                    startActivity(i);
                }
               else{
                    Toast.makeText(context, "KONUM BULUNAMIYOR!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }




    public  class GetForeCastAsync  extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {

            int  latitude=integers[0];
            int longitude=integers[1];
             String APIKEY="8b2525dd7637ddbf2721a9b16ec34dbb";
            ApiClient api=ApiClientInstance.getApiService();
            Call<Hava> call=api.getHava(latitude,longitude,APIKEY);

            call.enqueue(new Callback<Hava>() {
                @Override
                public void onResponse(Call<Hava> call, Response<Hava> response) {
                    String tempmax = Float.toString(response.body().getMain().getTempMax());
                    String Humidity=Integer.toString(response.body().getMain().getHumidity());
                    String temp =Float.toString(response.body().getMain().getTemp());
                    String tempmin=Float.toString(response.body().getMain().getTempMin());
                    String speed=Float.toString(response.body().getWind().getSpeed());
                    String all=Float.toString(response.body().getClouds().getAll());

                    s.add(tempmax);
                    s.add(Humidity);
                    s.add(temp);
                    s.add(tempmin);
                    s.add(speed);
                    s.add(all);

                }

                @Override
                public void onFailure(Call<Hava> call, Throwable t) {
                    Log.d("of","girmiyor");

                }
            });

           return null;
        }




    }



    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(provider,100,50,this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

      latitude= (int) location.getLatitude();
      longitude= (int) location.getLongitude();         // String ---> String.ValueOf(longitude)
        tw.setText(" "+latitude);
        tw2.setText(" "+longitude);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
     Toast.makeText(this,"enable provider",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this,"disable provider",Toast.LENGTH_SHORT).show();
    }









/*


 havatah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new GetForeCastAsync().execute(latitue,longitude);
               String s=hava.getMain().getHumidity().toString()+"off";
               tw.setText(s);
            }
        });






      //wallpaperı anamı sevyorum yap
        WallpaperManager wpManager=WallpaperManager.getInstance(getApplicationContext());
        try {
            wpManager.setResource(+R.drawable.pic);
        } catch (IOException e) {
            e.printStackTrace();
        }

        txtLat = (TextView) findViewById(R.id.tw);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }







    //set ringtone

    /*
    *
     *private AudioManager myAudioManager;
      myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    *
    *myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);  titreşim modu
    *myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);    sesli
    *myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);    sessiz
    *
    *
    * Picasso.get().load("http://i.imgur.com/DvpvklR.png").into();

    *
    *
    *
    *
    *
    *









    public class Forecast{
        Hava hava=new Hava();
        final String APIKEY="8b2525dd7637ddbf2721a9b16ec34dbb";

        public Hava getForecast(int latitue,int longitude){
            ApiClientInstance restInterface=ApiClient.getRetrofitInstance().create(ApiClientInstance.class);
            Call<Hava> call=restInterface.GetForecast(latitue,longitude,APIKEY);
            call.enqueue(new Callback<Hava>() {
                @Override
                public void onResponse(Call<Hava> call, Response<Hava> response) {
                    hava=response.body();

                }

                @Override
                public void onFailure(Call<Hava> call, Throwable t) {

                }
            });

            return hava;

        }


    }
*/
}


