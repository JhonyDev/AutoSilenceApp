package app.autosilenceapp.Quran;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import app.autosilenceapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class quran extends AppCompatActivity {
    ListView pdflistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);



        pdflistview=(ListView)findViewById(R.id.surah_names);


        String[] PdfFileNames={
     "Al-Fatihah.pdf","Al-Baqarah.pdf","Aale-Imran.pdf","An-Nisa.pdf","Al-Maidah.pdf","Al-Anam.pdf","Al-Araf.pdf","Al-Anfal.pdf","At-Tawbah.pdf","Yunus.pdf",
     "Hud.pdf","Yusuf.pdf","Ar-Rad.pdf","Ibrahim.pdf","Al-Hijr.pdf","An-Nahl.pdf","Al-Isra.pdf","Al-Kahf.pdf","Maryam.pdf","Taha.pdf",
     "Al-Anbiya.pdf","Al-Hajj.pdf","Al-Muminun.pdf","An-Nur.pdf","Al-Furqan.pdf","Ash-Shuara.pdf","An-Naml.pdf","Al-Qasas.pdf","Al-Ankabut.pdf","Ar-Rum.pdf",
     "Luqman.pdf","As-Sajdah.pdf","Al-Ahzab.pdf","Saba.pdf","Fatir.pdf","Yaseen.pdf","As-Saffat.pdf","Saad.pdf","Az-Zumar.pdf","Al-Ghafir.pdf",
     "Fussilat.pdf","Ash-Shura.pdf","Az-Zukhruf.pdf","Ad-Dukhan.pdf","Al-Jathiyah.pdf","Al-Ahqaf.pdf","Al-Muhammad.pdf","Al-Fath.pdf","Al-Hujurat.pdf","Qaf.pdf",
     "Adh-Dhariyat.pdf","At-Tur.pdf","An-Najm.pdf","Al-Qamar.pdf","Ar-Rahman.pdf","Al-Waqiah.pdf","Al-Hadid.pdf","Al-Mujadilah.pdf","Al-Hashr.pdf","Al-Mumtahinah.pdf",
     "As-Saf.pdf","Al-Jumuah.pdf","Al-Munafiqun.pdf","At-Taghabun.pdf","Al-Talaq.pdf","Al-Tahrim.pdf","Al-Mulk.pdf","Al-Qalam.pdf","Al-Haqqah.pdf","Al-Maarij.pdf",
     "Nuh.pdf","Al-Jinn.pdf","Al-Muzzammil.pdf","Al-Muddaththir.pdf","Al-Qiyamah.pdf","Al-Dahr.pdf","Al-Mursalat.pdf","Al-Naba.pdf","Al-Naziat.pdf","Al-Abasa.pdf",
     "Al-Takwir.pdf","Al-Infitar.pdf","Al-Mutaffifin.pdf","Al-Inshiqaq.pdf","Al-Buruj.pdf","Al-Tariq.pdf","Al-Aala.pdf","Al-Ghashiyah.pdf","Al-Fajr.pdf","Al-Balad.pdf",
     "Al-Shams.pdf","Al-Layl.pdf","Al-Duha.pdf","Al-Inshirah.pdf","Al-Tin.pdf","Al-Alaq.pdf","Al-Qadr.pdf","Al-Bayyinah.pdf","Al-Zalzalah.pdf","Al-Adiyat.pdf",
     "Al-Qariah.pdf","Al-Takathur.pdf","Al-Asr.pdf","Al-Humazah.pdf","Al-Fil.pdf","Al-Quraysh.pdf","Al-Maun.pdf","Al-Kawthar.pdf","Al-Kafirun.pdf","Al-Nasr.pdf",
     "Al-Masad.pdf","Al-Ikhlas.pdf","Al-Falaq.pdf","Al-Nas.pdf"

        };
//Arrayadapter demand 3 things this,temp|ate,Array
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,PdfFileNames){

//Alt + insert   use override method


            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)

            {
                View view=super.getView(position, convertView, parent);
                TextView myText=(TextView) view.findViewById(android.R.id.text1);
                myText.setTextColor(Color.WHITE);
                return view;
            }
        };

        pdflistview.setAdapter(adapter);
        //Ready for just |ist showing
        //Ready



        pdflistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String item=pdflistview.getItemAtPosition(position).toString();

                Intent intent=new Intent(getApplicationContext(),PdfOpnerActivity.class);
                intent.putExtra("pdfFileName",item);
                startActivity(intent);
            }
        });



    }
}
