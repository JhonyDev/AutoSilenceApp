package app.autosilenceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import app.autosilenceapp.sensors.BroadcastService;

public class NamazTimeList extends Activity
{

    String countryName,cityName;
    String url;
    String fajr,zuhar,asar,maghrib,esha;
    TextView fajrtime,duhurtime,asartime,maghribtime,ishatime;
    Button mute,vibrate,general;
    private static final String TAG = "hellllll";
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namaz_time_list);
        fajrtime = findViewById(R.id.fajrtime);
        startService(new Intent(this, BroadcastService.class));
        Log.i(TAG, "Started service");
        duhurtime = findViewById(R.id.dhurtime);
        asartime = findViewById(R.id.asartime);
        maghribtime = findViewById(R.id.maghribtime);
        ishatime = findViewById(R.id.ishatime);
        mute = findViewById(R.id.mute);
        general = findViewById(R.id.general);
        vibrate = findViewById(R.id.vibrate);
        Intent intent = getIntent();
        countryName = intent.getStringExtra("Countryname");
        cityName = intent.getStringExtra("Cityname");
        Toast.makeText(this, countryName  + cityName, Toast.LENGTH_SHORT).show();
        volleyGet();

        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager am;
                NotificationManager notificationManager =
                        (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !notificationManager.isNotificationPolicyAccessGranted()) {

                    Intent intent = new Intent(
                            android.provider.Settings
                                    .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

                    startActivity(intent);
                }
                am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                //For Silent mode
                am.setRingerMode(AudioManager.RINGER_MODE_SILENT);

            }

        });

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager am;
                NotificationManager notificationManager =
                        (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !notificationManager.isNotificationPolicyAccessGranted()) {

                    Intent intent = new Intent(
                            android.provider.Settings
                                    .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

                    startActivity(intent);
                }
                am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                //For Silent mode
                am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            }
        });

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager am;
                NotificationManager notificationManager =
                        (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && !notificationManager.isNotificationPolicyAccessGranted()) {

                    Intent intent = new Intent(
                            android.provider.Settings
                                    .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

                    startActivity(intent);
                }
                am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                //For Silent mode
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        });


    }

    public void volleyGet()
    {
        List<String> jsonResponses = new ArrayList<>();
        // url = "http://api.aladhan.com/v1/timingsByCity?city=Karachi&country=Pakistan&method=8";

        url = "http://api.aladhan.com/v1/timingsByCity?"+"city="+cityName+"&country="+countryName+"&method=8";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // Log.d("Response",response+"");

                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    // Log.d("Response",jsonObject+"");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("timings");
                    // Log.d("Response",jsonObject1+"");
                    fajr =  jsonObject1.getString("Fajr");
                    fajrtime.setText(fajr);
                    Log.d("Response",fajr+"");
                    zuhar =  jsonObject1.getString("Dhuhr");
                    duhurtime.setText(zuhar);
                    Log.d("Response",zuhar+"");
                    asar =  jsonObject1.getString("Asr");
                    asartime.setText(asar);
                    Log.d("Response",asar+"");
                    maghrib =  jsonObject1.getString("Maghrib");
                    maghribtime.setText(maghrib);
                    Log.d("Response",maghrib+"");
                    esha =  jsonObject1.getString("Isha");
                    ishatime.setText(esha);
                    Log.d("Response",esha+"");



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Response",error+"");

                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
    private BroadcastReceiver br = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };

    @Override
    public void onResume()
    {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        //unregisterReceiver(br);
        Log.i(TAG, "registered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            // unregisterReceiver(br);
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }
    @Override
    public void onDestroy() {
        // stopService(new Intent(this, BroadcastService.class));
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        Log.i(TAG, "Stopped service");
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            Log.i(TAG, "Countdown seconds remaining: " +  millisUntilFinished / 1000);
        }
    }

}