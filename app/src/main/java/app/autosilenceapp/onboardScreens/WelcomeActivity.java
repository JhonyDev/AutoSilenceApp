package app.autosilenceapp.onboardScreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import app.autosilenceapp.R;
import app.autosilenceapp.SignUp.Login;


public class WelcomeActivity extends AppCompatActivity {


    static ViewPagerCustomDuration viewPager;
    FragmentAdapter pagerAdapter;

    ImageView imageview_dot_01, imageview_dot_02, imageview_dot_03, imageview_dot_04;
    TextView btnNextFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //  SharedPrefManager.getInstance(WelcomeActivity.this).is_dashboard_first_time = true;
        // helperMethod.DialogSave("is_dashboard_first_time", "true");
       //getActionBar().hide();
        Interpolator interpolator = new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return 0;
            }
        };


        viewPager = (ViewPagerCustomDuration) findViewById(R.id.viewPager);
      //  ViewPagerCustomDuration vp = (ViewPagerCustomDuration) findViewById(R.id.viewPager);
        viewPager.setScrollDurationFactor(4); // make the animation twice as slow

        pagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        addingFragmentsTOpagerAdapter();
        viewPager.setAdapter(pagerAdapter);

    }


    @Override
    public void onBackPressed() {
       // Toast.makeText(WelcomeActivity.this, "Tap on image or click next button", Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
    }

    private void addingFragmentsTOpagerAdapter() {


        pagerAdapter.addFragments(new fragment_one());
        pagerAdapter.addFragments(new fragment_two());
        pagerAdapter.addFragments(new fragment_three());
        pagerAdapter.addFragments(new fragment_four());
        try {
            for (int i = 0; i < 4; i++)
            {
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }


}

