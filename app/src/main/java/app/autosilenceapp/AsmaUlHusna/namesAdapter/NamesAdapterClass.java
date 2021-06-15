package app.autosilenceapp.AsmaUlHusna.namesAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import app.autosilenceapp.AsmaUlHusna.model.NamesModel;
import app.autosilenceapp.R;

public class NamesAdapterClass extends RecyclerView.Adapter<NamesAdapterClass.ViewHolder> {
    private NamesModel[] listdata;

    // RecyclerView recyclerView;
    public NamesAdapterClass(NamesModel[] listdata) {
        this.listdata = listdata;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.row_itemss, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NamesModel myListData = listdata[position];
        holder.item_name.setText(listdata[position].getName());
        holder.item_meaning.setText(listdata[position].getMeaning());
        holder.description.setText(listdata[position].getDescription());
        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + myListData.getDescription(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView item_meaning;
        public TextView item_name;
        public TextView description;


        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.img);
            this.item_name = (TextView) itemView.findViewById(R.id.name);
            this.item_meaning = (TextView) itemView.findViewById(R.id.meaning);
            this.description = (TextView) itemView.findViewById(R.id.Description);
        }
    }
}