package app.autosilenceapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.WorkManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import app.autosilenceapp.AsmaUlHusna.NamesofALLAH;
import app.autosilenceapp.Mosque.findMosque;
import app.autosilenceapp.Notifications.MyReceiver_Not;
import app.autosilenceapp.QazaRecord.Model.Records;
import app.autosilenceapp.QazaRecord.QazaNamazRecord;
import app.autosilenceapp.QiblaDirection.CompassActivity;
import app.autosilenceapp.Quran.quran;
import app.autosilenceapp.serviceexample.MyReceiverexample;
import app.autosilenceapp.serviceexample.MyRecieverexample2;
import app.autosilenceapp.serviceexample.SetToNormalService;
import app.autosilenceapp.zikrCounter.zikrCounter;

public class MainActivity extends Activity implements SensorEventListener {

    private static final String TAG = "Mainvvvv";
    private static final String WORK_TAG = "Mainnnnn";
    private final int SAMPLE_SIZE = 50; // change this sample size as you want, higher is more precise but slow measure.
    private final double THRESHOLD = 0.2; //
    double yy = 0;
    boolean fflag = false;
    boolean zflag = false;
    boolean aflag = false;
    boolean mflag = false;
    boolean iflag = false;
    LinearLayout NamazReminder, QazaNamazRcord, alQuran, QiblaDirection, zikerCounter, namesofALLAH, findMosquee;
    TextView nmx;
    String dates = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    int timer = 3000;
    int timer2 = 2000;
    private SensorManager sensorMan;
    private Sensor accelerometer;
    private float[] mGravity;
    private double mAccel;
    private double mAccelCurrent;
    private double mAccelLast;
    private int hitCount = 0;
    private double hitSum = 0;
    private double hitResult = 0;
    private boolean sensorRegistered = false;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;

    public static void scheduleWork(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        long nowMillis = calendar.getTimeInMillis();

        if (calendar.get(Calendar.HOUR_OF_DAY) > hour ||
                (calendar.get(Calendar.HOUR_OF_DAY) == hour && calendar.get(Calendar.MINUTE) + 1 >= minute)) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long diff = calendar.getTimeInMillis() - nowMillis;

        WorkManager mWorkManager = WorkManager.getInstance();
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        mWorkManager.cancelAllWorkByTag(WORK_TAG);
       /*  OneTimeWorkRequest mRequest = new OneTimeWorkRequest.Builder(NotificationWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(diff, TimeUnit.MILLISECONDS)
                .addTag(WORK_TAG)
                .build();*/
        //mWorkManager.enqueue(mRequest);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NamazReminder = findViewById(R.id.namazReminder);
        nmx = findViewById(R.id.nmz);

        NotificationManager notificationManager =
                (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }

        //fajrNamaz();
        //DuhurNamaz();
        //AsarNamaz();
        //MaghribNamaz();
        //IshaNamaz();
        //getId();

        QazaNamazRcord = findViewById(R.id.qazaNamazRecord);
        alQuran = findViewById(R.id.quranPak);
        namesofALLAH = findViewById(R.id.namesofALLAH);
        QiblaDirection = findViewById(R.id.qibladirection);
        zikerCounter = findViewById(R.id.zikarCounter);
        findMosquee = findViewById(R.id.findMosque);
        sensorMan = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;
        sendNotification();
        sensorMan.registerListener((SensorEventListener) this, accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorRegistered = true;

        NamazReminder.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NamazReminder.class);
            startActivity(intent);
        });


        alQuran.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, quran.class);
            startActivity(intent);
        });

        findMosquee.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, findMosque.class);
            startActivity(intent);
        });

        zikerCounter.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, zikrCounter.class);
            startActivity(intent);
        });

        QiblaDirection.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CompassActivity.class);
            startActivity(intent);
        });
        QazaNamazRcord.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QazaNamazRecord.class);
            startActivity(intent);
        });
        namesofALLAH.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NamesofALLAH.class);
            startActivity(intent);
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mGravity = event.values.clone();
            // Shake detection
            double x = mGravity[0];
            double y = mGravity[1];
            double z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = Math.sqrt(x * x + y * y + z * z);


            Log.d("TAG", String.valueOf(y));
            Log.d("TAGY", String.valueOf(yy));

            Calendar fajr = Calendar.getInstance();
            fajr.set(Calendar.HOUR_OF_DAY, 12);
            fajr.set(Calendar.MINUTE, 37);
            fajr.set(Calendar.AM_PM, Calendar.PM);


            Calendar zuhr = Calendar.getInstance();
            zuhr.set(Calendar.HOUR_OF_DAY, 12);
            zuhr.set(Calendar.MINUTE, 04);
            zuhr.set(Calendar.AM_PM, Calendar.PM);

            Calendar asr = Calendar.getInstance();
            asr.set(Calendar.HOUR_OF_DAY, 12);
            asr.set(Calendar.MINUTE, 06);
            asr.set(Calendar.AM_PM, Calendar.PM);

            Calendar maghrib = Calendar.getInstance();
            maghrib.set(Calendar.HOUR_OF_DAY, 12);
            maghrib.set(Calendar.MINUTE, 10);
            maghrib.set(Calendar.AM_PM, Calendar.PM);


            Calendar isha = Calendar.getInstance();
            isha.set(Calendar.HOUR_OF_DAY, 12);
            isha.set(Calendar.MINUTE, 12);
            isha.set(Calendar.AM_PM, Calendar.PM);


            boolean fsameYear = Calendar.getInstance().get(Calendar.HOUR) == fajr.get(Calendar.HOUR);
            boolean fsameMonth = Calendar.getInstance().get(Calendar.MINUTE) == fajr.get(Calendar.MINUTE);
            boolean fsameDay = Calendar.getInstance().get(Calendar.SECOND) == fajr.get(Calendar.SECOND);

            boolean zsameYear = Calendar.getInstance().get(Calendar.HOUR) == zuhr.get(Calendar.HOUR);
            boolean zsameMonth = Calendar.getInstance().get(Calendar.MINUTE) == zuhr.get(Calendar.MINUTE);
            boolean zsameDay = Calendar.getInstance().get(Calendar.SECOND) == zuhr.get(Calendar.SECOND);

            boolean asameYear = Calendar.getInstance().get(Calendar.HOUR) == asr.get(Calendar.HOUR);
            boolean asameMonth = Calendar.getInstance().get(Calendar.MINUTE) == asr.get(Calendar.MINUTE);
            boolean asameDay = Calendar.getInstance().get(Calendar.SECOND) == asr.get(Calendar.SECOND);

            boolean msameYear = Calendar.getInstance().get(Calendar.HOUR) == maghrib.get(Calendar.HOUR);
            boolean msameMonth = Calendar.getInstance().get(Calendar.MINUTE) == maghrib.get(Calendar.MINUTE);
            boolean msameDay = Calendar.getInstance().get(Calendar.SECOND) == maghrib.get(Calendar.SECOND);

            boolean isameYear = Calendar.getInstance().get(Calendar.HOUR) == isha.get(Calendar.HOUR);
            boolean isameMonth = Calendar.getInstance().get(Calendar.MINUTE) == isha.get(Calendar.MINUTE);
            boolean isameDay = Calendar.getInstance().get(Calendar.SECOND) == isha.get(Calendar.SECOND);


            if (fsameYear && fsameMonth && fsameDay) {
                Log.d("TAGOut", "true" + y);
                if ((y >= yy + 0.5 || y <= yy - 0.5) && !fflag) {


                    final Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        //Write whatever to want to do after delay specified (1 sec)
                        Log.d("Timer", "5 sec");
                        if ((y >= yy + 0.5 || y <= yy - 0.5) && !fflag) {

                            final Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Write whatever to want to do after delay specified (1 sec)
                                    Log.d("Timer", "3 sec");
                                    if ((y >= yy + 0.5 || y <= yy - 0.5) && !fflag) {
                                        Log.d("True", String.valueOf(y));
                                        fajrNamaz(fajr);

                                        if (!getId(1))
                                            addDataToFirestore(dates, true, false, false, false, false);

                                        fflag = true;
                                    }
                                }
                            }, timer2);
                        }
                    }, timer);
                }
            } else if (zsameDay && zsameYear && zsameMonth) {
                //Log.d("TAGOut","true");
                if ((y >= yy + 1 || y <= yy - 1) && !zflag) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Write whatever to want to do after delay specified (1 sec)
                            Log.d("Timer", "5 sec");
                            if ((y >= yy + 0.5 || y <= -yy - 0.5) && !zflag) {


                                final Handler handler = new Handler();
                                handler.postDelayed(() -> {
                                    //Write whatever to want to do after delay specified (1 sec)
                                    Log.d("True", "3 sec");
                                    if ((y >= yy + 0.5 || y <= yy - 0.5) && !zflag) {
                                        //Log.d("TAGIn",String.valueOf(y));
                                        DuhurNamaz(zuhr);
                                        getId(2);
                                        zflag = true;
                                    }
                                }, timer2);
                            }
                        }
                    }, timer);
                }
            } else if (asameDay && asameMonth && asameYear) {
                //Log.d("TAGOut","true");
                if ((y >= yy + 0.5 || y <= yy - 0.5) && !aflag) {
                    final Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        //Write whatever to want to do after delay specified (1 sec)
                        Log.d("Timer", "5 sec");
                        if ((y >= yy + 0.5 || y <= yy - 0.5) && !aflag) {
                            final Handler handler12 = new Handler();
                            handler12.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Write whatever to want to do after delay specified (1 sec)
                                    Log.d("True", "3 sec");
                                    if ((y >= yy + 0.5 || y <= yy - 0.5) && !aflag) {
                                        //Log.d("TAGIn",String.valueOf(y));
                                        AsarNamaz(asr);
                                        getId(3);
                                        aflag = true;
                                    }
                                }
                            }, timer2);
                        }
                    }, timer);
                }
            } else if (msameDay && msameMonth && msameYear) {
                //Log.d("TAGOut","true");
                if ((y >= yy + 0.5 || y <= yy - 0.5) && !mflag) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Write whatever to want to do after delay specified (1 sec)
                            Log.d("Timer", "5 sec");
                            if ((y >= yy + 0.5 || y <= yy - 0.5) && !mflag) {


                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Write whatever to want to do after delay specified (1 sec)
                                        Log.d("True", "3 sec");
                                        if ((y >= yy + 0.5 || y <= yy - 0.5) && !mflag) {
                                            //Log.d("TAGIn",String.valueOf(y));
                                            MaghribNamaz(maghrib);
                                            getId(4);
                                            mflag = true;
                                        }
                                    }
                                }, timer2);
                            }
                        }
                    }, timer);
                }
            } else if (isameDay && isameMonth && isameYear) {
                //Log.d("TAGOut","true");
                if ((y >= yy + 0.5 || y <= yy - 0.5) && !iflag) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Write whatever to want to do after delay specified (1 sec)
                            Log.d("True", "5 sec");
                            if ((y >= yy + 0.5 || y <= yy - 0.5) && !iflag) {
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Write whatever to want to do after delay specified (1 sec)
                                        Log.d("Timer", "3 sec");
                                        if ((y >= yy + 0.5 || y <= yy - 0.5) && !iflag) {
                                            //Log.d("TAGIn",String.valueOf(y));
                                            IshaNamaz(isha);
                                            getId(5);
                                            iflag = true;
                                        }
                                    }
                                }, timer2);
                            }
                        }
                    }, timer);
                }
            }
            yy = y;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void SilentAndSetAlarm(Context context, long triggerAtMillis, int intervalMillis) {
        // set the phone to silent
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);

        }
        AudioManager mAudioManager = (AudioManager) MainActivity.this.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        // Initialize the AlarmManager
        Intent intent = new Intent(context, SetToNormalService.class);
        PendingIntent pIntent = PendingIntent.getService(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, intervalMillis, pIntent);

    }

    private Boolean getId(int numberofnamaz) {
        final boolean[] what = {false};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbRecords = db.collection("NamazRecord");
        dbRecords
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData().get("date"));
                                if (dates.equalsIgnoreCase(document.getData().get("date").toString())) {
                                    Log.d("aaaaaa", document.getId() + " => " + document.getData().get("date"));
                                    if (numberofnamaz == 1) {
                                        dbRecords
                                                .document(document.getId())
                                                .update(
                                                        "fajar", true);
                                        what[0] = true;
                                    } else if (numberofnamaz == 2) {
                                        dbRecords
                                                .document(document.getId())
                                                .update(
                                                        "duhur", true);
                                        what[0] = true;
                                    } else if (numberofnamaz == 3) {
                                        dbRecords
                                                .document(document.getId())
                                                .update(
                                                        "asar", true);
                                        what[0] = true;
                                    } else if (numberofnamaz == 4) {
                                        dbRecords
                                                .document(document.getId())
                                                .update(
                                                        "maghrib", true);
                                        what[0] = true;
                                    } else if (numberofnamaz == 5) {
                                        dbRecords
                                                .document(document.getId())
                                                .update(
                                                        "isha", true);
                                        what[0] = true;
                                    }
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return what[0];
    }


    private void addDataToFirestore(String dated, boolean fajar, boolean duhur, boolean Asar, boolean Maghrib, boolean Isha) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbRecords = db.collection("NamazRecord");
        //   DocumentReference d = db.document(dates);
        // adding our data to our courses object class.
        Records records = new Records(dated, fajar, duhur, Asar, Maghrib, Isha);

        // below method is use to add data to Firebase Firestore.
        dbRecords.add(records).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.

                Toast.makeText(MainActivity.this, "Your record has been added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(MainActivity.this, "Fail to add record" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void sendNotification() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 13);
        calendar.set(Calendar.SECOND, 0);
        Intent intent1 = new Intent(MainActivity.this, MyReceiver_Not.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(MainActivity.this.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public void fajrNamaz(Calendar fajr) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(MainActivity.this, MyReceiverexample.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, fajr.getTimeInMillis(), pendingIntent);
        //After 30 minutes Unsilent
        fajr.set(Calendar.MINUTE, fajr.getTime().getMinutes() + 1);
        Intent myIntent1 = new Intent(MainActivity.this, MyRecieverexample2.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, fajr.getTimeInMillis(), pendingIntent);

    }

    public void DuhurNamaz(Calendar zuhr) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(MainActivity.this, MyReceiverexample.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, zuhr.getTimeInMillis(), pendingIntent);
        zuhr.set(Calendar.MINUTE, zuhr.getTime().getMinutes() + 1);
        Intent myIntent1 = new Intent(MainActivity.this, MyReceiverexample.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, zuhr.getTimeInMillis(), pendingIntent);

    }

    public void AsarNamaz(Calendar asr) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(MainActivity.this, MyReceiverexample.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, asr.getTimeInMillis(), pendingIntent);
        asr.set(Calendar.MINUTE, asr.getTime().getMinutes() + 1);
        Intent myIntent1 = new Intent(MainActivity.this, MyRecieverexample2.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, asr.getTimeInMillis(), pendingIntent);

    }

    public void MaghribNamaz(Calendar maghrib) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(MainActivity.this, MyReceiverexample.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, maghrib.getTimeInMillis(), pendingIntent);
        maghrib.set(Calendar.MINUTE, maghrib.getTime().getMinutes() + 1);
        Intent myIntent1 = new Intent(MainActivity.this, MyRecieverexample2.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, maghrib.getTimeInMillis(), pendingIntent);

    }

    public void IshaNamaz(Calendar isha) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(MainActivity.this, MyReceiverexample.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, isha.getTimeInMillis(), pendingIntent);
        isha.set(Calendar.MINUTE, isha.getTime().getMinutes() + 1);
        Intent myIntent1 = new Intent(MainActivity.this, MyRecieverexample2.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, (int) System.currentTimeMillis(), myIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, isha.getTimeInMillis(), pendingIntent);


    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finishAffinity();
    }
}