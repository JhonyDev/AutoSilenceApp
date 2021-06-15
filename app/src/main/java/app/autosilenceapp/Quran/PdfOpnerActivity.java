package app.autosilenceapp.Quran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;

import app.autosilenceapp.R;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PdfOpnerActivity extends AppCompatActivity {
    PDFView mypdfviewer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_opner);
        mypdfviewer=(PDFView)findViewById(R.id.pdfviewer);

        String getitem=getIntent().getStringExtra("pdfFileName");

        AssetManager assetManager = getAssets();
        try {
            for (String file : assetManager.list("")) {
                if (file.endsWith(".pdf"))
                    if(getitem.equalsIgnoreCase(file)){
                        mypdfviewer.fromAsset(file)
                                .enableSwipe(true)
                                .enableDoubletap(false)
                                .enableAnnotationRendering(false)
                                .password(null)
                                .scrollHandle(null)
                                .enableAntialiasing(true)
                                .spacing(0)
                                .load();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
