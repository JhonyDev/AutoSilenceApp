package app.autosilenceapp.onboardScreens;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.autosilenceapp.MainActivity;
import app.autosilenceapp.R;
import app.autosilenceapp.SignUp.Login;


public class fragment_four  extends Fragment

  {

    TextView btnNextFinishfour, imageview_dot_02, imageview_dot_03, imageview_dot_04, imageview_main_picture;

    int index_count;

    public fragment_four()
    {
        // this.index_count = index_count;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.onboardscreen_four, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        btnNextFinishfour = (TextView) rootView.findViewById(R.id.btnNextFinishfour);
        btnNextFinishfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // WelcomeActivity.viewPager.setCurrentItem(3);
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);


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
