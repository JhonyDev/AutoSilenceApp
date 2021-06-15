package app.autosilenceapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikelau.countrypickerx.Country;
import com.mikelau.countrypickerx.CountryPickerCallbacks;
import com.mikelau.countrypickerx.CountryPickerDialog;

public class NamazReminder extends Activity {


    CountryPickerDialog countryPicker;
    Button getCountryName,submit;
    EditText getCityName;
    String cityName = "Abottabad";
    String countryname = "Pakistan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namaz_reminder);
        getCountryName = findViewById(R.id.getCountryName);
        getCityName = findViewById(R.id.getCityName);
        submit = findViewById(R.id.submit);

        getCountryName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resourceId = getResources().getIdentifier("country_avail", "raw", getApplicationContext().getPackageName());

                countryPicker = new CountryPickerDialog(NamazReminder.this, new CountryPickerCallbacks() {
                    @Override
                    public void onCountrySelected(Country country, int flagResId) {


                        countryname = country.getCountryName(getApplicationContext());
                        Toast.makeText(NamazReminder.this, countryname, Toast.LENGTH_SHORT).show();
                        getCountryName.setText(countryname);
                        /* Get Country Name: country.getCountryName(context); */
                        /* Call countryPicker.dismiss(); to prevent memory leaks */
                    }


                }, false, 0);
                countryPicker.show();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = getCityName.getText().toString();
                Intent intent = new Intent(NamazReminder.this,NamazTimeList.class);
                intent.putExtra("Cityname",cityName);
                intent.putExtra("Countryname",countryname);

                startActivity(intent);

            }
        });


    }
}