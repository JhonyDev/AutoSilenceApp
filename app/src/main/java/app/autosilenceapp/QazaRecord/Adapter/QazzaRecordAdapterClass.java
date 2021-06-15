package app.autosilenceapp.QazaRecord.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import app.autosilenceapp.AsmaUlHusna.model.NamesModel;
import app.autosilenceapp.QazaRecord.Model.AllRecords;
import app.autosilenceapp.R;

public class QazzaRecordAdapterClass extends   RecyclerView.Adapter<QazzaRecordAdapterClass.ViewHolder> {
    private AllRecords[] listdata;

    public QazzaRecordAdapterClass(AllRecords[] listdata) {
        this.listdata = listdata;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_all_records, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final AllRecords myListData = listdata[position];
        holder.fajr.setText(listdata[position].isFajar()+"");
        holder.duhur.setText(listdata[position].isDuhur()+"");
        holder.isha.setText(listdata[position].isIsha()+"");
        holder.maghrib.setText(listdata[position].isMaghrib()+"");
        holder.asar.setText(listdata[position].isAsar()+"");
        holder.date.setText(listdata[position].getDate()+"");
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView fajr;
        public TextView duhur;
        public TextView isha;
        public TextView maghrib;
        public TextView asar;
        public TextView date;



        public ViewHolder(View itemView) {
            super(itemView);
            this.fajr = (TextView) itemView.findViewById(R.id.displayFajrRecord);
            this.duhur = (TextView) itemView.findViewById(R.id.displayduhurRecord);
            this.asar = (TextView) itemView.findViewById(R.id.displayAsarRecord);
            this.maghrib = (TextView) itemView.findViewById(R.id.displayMaghribRecord);
            this.isha = (TextView) itemView.findViewById(R.id.displayIshaRecord);
            this.date = (TextView) itemView.findViewById(R.id.displayDate);

        }
    }
}