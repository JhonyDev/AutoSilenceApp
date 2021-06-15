package app.autosilenceapp.QazaRecord;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import app.autosilenceapp.QazaRecord.Model.Records;
import app.autosilenceapp.R;

public class QazaNamazRecord extends Activity {


    TextView date;
    String dateText;
    CheckBox cbFajar, cbDuhur, cbAsar, cbMaghrib, cbIsha;
    Button submit, getRecords;
    boolean cbA, cbM, cbI, cbF, cbD = false;
    String dates;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qaza_namaz_record);
        db = FirebaseFirestore.getInstance();
        date = findViewById(R.id.date);
        getRecords = findViewById(R.id.getRecords);
        cbFajar = findViewById(R.id.cbFajar);
        cbDuhur = findViewById(R.id.cbduhur);
        cbAsar = findViewById(R.id.cbAsar);
        cbMaghrib = findViewById(R.id.cbMaghrib);
        cbIsha = findViewById(R.id.cbIsha);
        submit = findViewById(R.id.submit);
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        dates = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        // textView is the TextView view that should display it
        date.setText(dates);


        submit.setOnClickListener(v -> runOnceADay());

        getRecords.setOnClickListener(v -> {
            Intent intent = new Intent(QazaNamazRecord.this, AllNamazRecord.class);
            startActivity(intent);
        });

    }

    public boolean runOnceADay() {
        SharedPreferences shp = getSharedPreferences("hello", MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        long lastCheckedMillis = shp.getLong("Hello", 0); // "KEY" you may change yhe value
        long now = System.currentTimeMillis();
        long diffMillis = now - lastCheckedMillis;
        if (diffMillis >= (3600000 * 24)) { // set up your time circulation
            editor.putLong("Hello", now);
            editor.commit();
            //    Util.showMessage(c, "Once a Day Test");
            Toast.makeText(this, "Your's today Record is saved", Toast.LENGTH_SHORT).show();
            Log.d("testtttt", "Once a Day Test");
            if (cbAsar.isChecked()) {
                cbA = true;
                Log.d("cbvalue", cbA + "");
            }

            if (cbMaghrib.isChecked()) {
                cbM = true;
                Log.d("cbvalue", cbM + "");
            }
            if (cbDuhur.isChecked()) {
                cbD = true;
                Log.d("cbvalue", cbD + "");
            }
            if (cbFajar.isChecked()) {
                cbF = true;
                Log.d("cbvalue", cbF + "");
            }
            if (cbIsha.isChecked()) {
                cbI = true;
                Log.d("cbvalue", cbI + "");
            }
            addDataToFirestore(dates, cbF, cbD, cbA, cbM, cbI);

            return false;
        } else {
            Toast.makeText(this, "Your's have already saved today's Record", Toast.LENGTH_SHORT).show();
            Log.d("testtttt", "Too Early");
            Log.d("cbvalue", cbI + "");

            cbAsar.setChecked(false);
            cbDuhur.setChecked(false);
            cbFajar.setChecked(false);
            cbIsha.setChecked(false);
            cbMaghrib.setChecked(false);
            return true;
        }
    }

    private void addDataToFirestore(String dated, boolean fajar, boolean duhur, boolean Asar, boolean Maghrib, boolean Isha) {

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

                Toast.makeText(QazaNamazRecord.this, "Your record has been added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(QazaNamazRecord.this, "Fail to add record" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

