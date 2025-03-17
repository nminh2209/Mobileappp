package com.example.week9

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager
    private lateinit var sensorListTextView: TextView
    private lateinit var sensorValueTextView: TextView
    private var mSensors: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorListTextView = findViewById(R.id.sensor_list)
        sensorValueTextView = findViewById(R.id.sensor_value)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Get the list of all available sensors
        val sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL)
        val sensorNames = sensorList.joinToString("\n") { it.name }
        sensorListTextView.text = sensorNames.ifEmpty { "No Sensors Available" }


        mSensors = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onResume() {
        super.onResume()

        mSensors?.let {
            mSensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
            Log.v("SensorLifecycle", "Sensor listener registered")
        }
    }

    override fun onPause() {
        super.onPause()

        mSensorManager.unregisterListener(this)
        Log.v("SensorLifecycle", "Sensor listener unregistered")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val sensorType = it.sensor.type
            val sensorValue = it.values[0]

            if (sensorType == Sensor.TYPE_LIGHT) {
                sensorValueTextView.text = "Light Sensor Value: $sensorValue lx"
            }

            Log.v("SensorData", "Sensor Type: $sensorType, Value: $sensorValue")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.v("SensorAccuracy", "Sensor: ${sensor?.name}, Accuracy: $accuracy")
    }
}
