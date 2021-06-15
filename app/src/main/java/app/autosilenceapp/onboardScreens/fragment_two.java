package app.autosilenceapp.onboardScreens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.autosilenceapp.R;


public class fragment_two extends Fragment {

    TextView btnNextFinishtwo;

    int index_count;

    public fragment_two()
    {
        //this.index_count = index_count;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.onboardscreen_two, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        btnNextFinishtwo = (TextView) rootView.findViewById(R.id.btnNextFinishtwo);
        btnNextFinishtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WelcomeActivity.viewPager.setCurrentItem(2);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
