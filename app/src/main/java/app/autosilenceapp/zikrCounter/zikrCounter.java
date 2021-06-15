package app.autosilenceapp.zikrCounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.autosilenceapp.R;
import android.content.Context;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class zikrCounter extends AppCompatActivity {
    // Vibrator 1
    Vibrator mVibrator;
    Window window;
    boolean isTureVibrator =true;

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // FULL SCREEN
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // -- FULL SCREEN

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zikr_counter);


        final TextView myTextView = (TextView) findViewById(R.id.textAd);

        ImageButton btnVibrate=(ImageButton)findViewById(R.id.CounntAdd);
        final ImageButton resftz = (ImageButton)findViewById(R.id.restz);
        final ImageButton BTureVibrator = (ImageButton)findViewById(R.id.addvibrator);
        // Vibrator 2
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        btnVibrate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates 3 : for 100 Milliseconds
                if(isTureVibrator){ mVibrator.vibrate(100);}

                count++;
                myTextView.setText( ""+count);

            }
        });

        resftz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates 3 : for 100 Milliseconds
                if(isTureVibrator){ mVibrator.vibrate(100);}
                count = 0;
                myTextView.setText( ""+count);

                //screenBrightness
                //  WindowManager.LayoutParams layout = getWindow().getAttributes();
                //  layout.screenBrightness = 1F; // 0.0F to 1.0F -- 20% = 0.2f
                ///  getWindow().setAttributes(layout);
            }
        });

        BTureVibrator.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Vibrates 3 : for 100 Milliseconds
                mVibrator.vibrate(100);
                if(isTureVibrator){
                    isTureVibrator=false;
                    BTureVibrator.setImageResource(R.drawable.vibrate_off);
                }
                else {
                    isTureVibrator=true;
                    BTureVibrator.setImageResource(R.drawable.vibrate_on);
                }

            }
        });



    }

}