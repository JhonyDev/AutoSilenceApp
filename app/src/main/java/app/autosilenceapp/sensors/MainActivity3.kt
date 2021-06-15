package app.autosilenceapp.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import app.autosilenceapp.R

class MainActivity3 : AppCompatActivity() {

    private lateinit var sensorManager: SensorManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

      /*  sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // getting the list of all the availabale sensors in the current device
        val deviceSensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

        deviceSensors.forEach { sensor ->
            Log.d("SENSORS", "Sensor Name: ${sensor.name}")
        }
    }*/}
}