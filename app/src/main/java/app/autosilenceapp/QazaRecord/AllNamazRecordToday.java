package app.autosilenceapp.QazaRecord;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import app.autosilenceapp.QazaRecord.Adapter.QazzaRecordAdapterClass;
import app.autosilenceapp.QazaRecord.Model.AllRecords;
import app.autosilenceapp.R;

public class AllNamazRecordToday extends Activity {

    RecyclerView recyclerView;
    QazzaRecordAdapterClass qazzaRecordAdapterClass;
    LinearLayoutManager linearLayoutManager;
    TextView totalQaza, totalofferednamaz;
    int count = 0;
    int offeredCount = 0;
    boolean cbA, cbM, cbI, cbF, cbD = false;
    Button btnToday, btnMonthly;
    Boolean Today = false;
    Boolean Monthly = false;
    private FirebaseFirestore db;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_namaz_record);

        btnToday = findViewById(R.id.today);
        btnMonthly = findViewById(R.id.monthly);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = df.format(c);

        btnMonthly.setOnClickListener(v -> {
            Log.d("TAG", "INMONTHLY");
            finish();
            startActivity(new Intent(AllNamazRecordToday.this, AllNamazRecord.class));

        });

        btnToday.setOnClickListener(v -> {
            Log.d("TAG", "INDAILY");
            finish();
            startActivity(new Intent(AllNamazRecordToday.this, AllNamazRecordToday.class));
        });


        recyclerView = findViewById(R.id.displayAllrecords);
        totalQaza = findViewById(R.id.totalQaza);
        totalofferednamaz = findViewById(R.id.totaloffered);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        init();
        Query query = db.collection("NamazRecord").whereEqualTo("date", formattedDate);
        getList(query);


    }


    private void init() {
        linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        db = FirebaseFirestore.getInstance();
    }


    private void getList(Query query) {


        FirestoreRecyclerOptions<AllRecords> response = new FirestoreRecyclerOptions.Builder<AllRecords>()
                .setQuery(query, AllRecords.class)
                .build();

        Log.i("TAG", "getList: " + response.getSnapshots());

        adapter = new FirestoreRecyclerAdapter<AllRecords, ViewHolderr>(response) {
            @Override
            public void onBindViewHolder(ViewHolderr holder, int position, AllRecords model) {
                String date = model.getDate();
                Log.d("dateToday", date);
                holder.date.setText(date);
                if (model.isFajar() == true) {
                    holder.fajr.setText("Yes");
                    offeredCount = offeredCount + 1;
                    holder.updateFajrRecord.setVisibility(View.INVISIBLE);
                    cbF = true;

                } else {
                    holder.fajr.setText("No");
                    count = count + 1;
                    holder.updateFajrRecord.setVisibility(View.VISIBLE);
                    cbF = false;

                }
                if (model.isDuhur() == true) {
                    offeredCount = offeredCount + 1;
                    holder.duhur.setText("Yes");
                    holder.updateDuhurRecord.setVisibility(View.INVISIBLE);
                    cbD = true;

                } else {
                    holder.duhur.setText("No");
                    count = count + 1;
                    holder.updateDuhurRecord.setVisibility(View.VISIBLE);
                    cbD = false;

                }
                if (model.isAsar() == true) {
                    offeredCount = offeredCount + 1;
                    holder.asar.setText("Yes");
                    holder.updateAsarRecord.setVisibility(View.INVISIBLE);
                    cbA = true;

                } else {
                    holder.asar.setText("No");
                    count = count + 1;
                    holder.updateAsarRecord.setVisibility(View.VISIBLE);
                    cbA = false;
                }
                if (model.isMaghrib() == true) {
                    offeredCount = offeredCount + 1;
                    holder.maghrib.setText("Yes");
                    holder.updateMaghribRecord.setVisibility(View.INVISIBLE);
                    cbM = true;
                } else {
                    holder.maghrib.setText("No");
                    count = count + 1;
                    holder.updateMaghribRecord.setVisibility(View.VISIBLE);
                    cbM = false;

                }
                if (model.isIsha() == true) {
                    offeredCount = offeredCount + 1;
                    holder.isha.setText("Yes");
                    holder.updateIshaRecord.setVisibility(View.INVISIBLE);
                    cbI = true;
                } else {
                    holder.isha.setText("No");
                    count = count + 1;
                    holder.updateIshaRecord.setVisibility(View.VISIBLE);
                    cbI = false;
                }
                Log.d("countt", count + "qaza");
                Log.d("countt", offeredCount + "offered");
                // adapter.notifyDataSetChanged();
                totalofferednamaz.setText(offeredCount + "");
                totalQaza.setText(count + "");


                holder.update.setOnClickListener(v -> {
                    DocumentSnapshot snapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
                    String id = snapshot.getId();
                    Log.d("cbvalue", id);
                    if (holder.updateFajrRecord.isChecked()) {
                        cbF = true;
                        Log.d("cbvalue", cbF + "");
                        db.collection("NamazRecord").document(id).update("fajar", cbF);
                        Log.d("cbvalue", cbF + "");
                    }
                    if (holder.updateDuhurRecord.isChecked()) {
                        cbD = true;
                        Log.d("cbvalue", cbD + "");
                        db.collection("NamazRecord").document(id).update("duhur", cbD);
                    }
                    if (holder.updateAsarRecord.isChecked()) {
                        cbA = true;
                        Log.d("cbvalue", cbA + "");
                        db.collection("NamazRecord").document(id).update("asar", cbA);
                    }
                    if (holder.updateMaghribRecord.isChecked()) {
                        cbM = true;
                        Log.d("cbvalue", cbM + "");
                        db.collection("NamazRecord").document(id).update("maghrib", cbM);
                    }
                    if (holder.updateIshaRecord.isChecked()) {
                        cbI = true;
                        Log.d("cbvalue", cbI + "");
                        db.collection("NamazRecord").document(id).update("isha", cbI);
                    }
                    Toast.makeText(AllNamazRecordToday.this, "Record Updated", Toast.LENGTH_SHORT).show();
                    // adapter.notifyDataSetChanged();


                });


            }

            @Override
            public int getItemCount() {
                Log.d("adaptercount", "getItemCount: " + super.getItemCount());
                return super.getItemCount();
            }

            @Override
            public ViewHolderr onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_all_records, parent, false);
                return new ViewHolderr(view);
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                Log.e("error", e.getMessage());
            }
        };

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    count = 0;
                    offeredCount = 0;
                }

            }
        });
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        count = 0;
        offeredCount = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        count = 0;
        offeredCount = 0;
    }

    public static class ViewHolderr extends RecyclerView.ViewHolder {
        public TextView fajr;
        public TextView duhur;
        public TextView isha;
        public TextView maghrib;
        public TextView asar;
        public TextView date;
        CheckBox updateFajrRecord, updateDuhurRecord, updateAsarRecord, updateMaghribRecord, updateIshaRecord;
        Button update;

        public ViewHolderr(View itemView) {
            super(itemView);
            this.fajr = (TextView) itemView.findViewById(R.id.displayFajrRecord);
            this.duhur = (TextView) itemView.findViewById(R.id.displayduhurRecord);
            this.asar = (TextView) itemView.findViewById(R.id.displayAsarRecord);
            this.maghrib = (TextView) itemView.findViewById(R.id.displayMaghribRecord);
            this.isha = (TextView) itemView.findViewById(R.id.displayIshaRecord);
            this.date = (TextView) itemView.findViewById(R.id.displayDate);
            this.updateFajrRecord = itemView.findViewById(R.id.updatefajarCount);
            this.updateDuhurRecord = itemView.findViewById(R.id.updateDuhurCount);
            this.updateAsarRecord = itemView.findViewById(R.id.updateAsarCount);
            this.updateMaghribRecord = itemView.findViewById(R.id.updateMaghribCount);
            this.updateIshaRecord = itemView.findViewById(R.id.updateIshaCount);
            this.update = itemView.findViewById(R.id.update);

        }
    }


}
