package vn.edu.usth.weather;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.i("create" , "onCreate called");
    }

    public void onStart() {
        super.onStart();


        Log.i("start" , "onStart called");
    }

    public void onPause() {
        super.onPause();


        Log.i("pause" , "onPause called");
    }

    public void onResume() {
        super.onResume();

        Log.i("resume" , "onResume called");
    }

    public void onStop() {
        super.onStop();

        Log.i("stop" , "onStop called");

    }

    public void onDestroy() {
        super.onDestroy();

        Log.i("Destroy" , "onDestroy called");
    }




}