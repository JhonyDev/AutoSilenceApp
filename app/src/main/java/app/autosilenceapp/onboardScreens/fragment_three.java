package app.autosilenceapp.onboardScreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.autosilenceapp.R;


public class fragment_three  extends Fragment {

    TextView btnNextFinishthree, imageview_dot_02, imageview_dot_03, imageview_dot_04, imageview_main_picture;

    int index_count;

    public fragment_three() {



    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.onboardscreen_three, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        btnNextFinishthree = (TextView) rootView.findViewById(R.id.btnNextFinishthree);
        btnNextFinishthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WelcomeActivity.viewPager.setCurrentItem(3);
            }
        });

      /*  if(index_count == 0)
        {
            imageview_main_picture.setBackgroundResource(R.mipmap.official_welcome_01);
        }
        else if(index_count == 1)
        {
            imageview_main_picture.setBackgroundResource(R.mipmap.official_welcome_02);
        }
        else if(index_count == 2)
        {
            imageview_main_picture.setBackgroundResource(R.mipmap.official_welcome_03);
        }
        else if(index_count == 3)
        {
            imageview_main_picture.setBackgroundResource(R.mipmap.official_welcome_04);
        }*/

        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();

    }


}
