package app.autosilenceapp.onboardScreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.autosilenceapp.R;


public class fragment_one  extends Fragment {

    TextView btnNextFinishOne, text ,imageview_dot_02, imageview_dot_03, imageview_dot_04, imageview_main_picture;

    int index_count;

    public fragment_one()
    {

    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.onboardscreen_one, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        btnNextFinishOne = (TextView) rootView.findViewById(R.id.btnNextFinishOne);
        text = (TextView) rootView.findViewById(R.id.text);
        btnNextFinishOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WelcomeActivity.viewPager.setCurrentItem(1);
            }
        });
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            text.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }*/
        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();

    }


}
