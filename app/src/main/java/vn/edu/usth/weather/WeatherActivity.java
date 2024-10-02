package vn.edu.usth.weather;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WeatherActivity extends AppCompatActivity {

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_weather);

        ViewPager2 pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        HomeFragmentPagerAdapter adapter = new HomeFragmentPagerAdapter(this);

        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, pager, (tab, position) -> tab.setText(adapter.getPageTitle(position))).attach();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        toolbar.showOverflowMenu();

        MediaPlayer music = MediaPlayer.create(WeatherActivity.this, R.raw.merry_go_round_of_life_howls_moving_castle);
        music.start();
        music.setLooping(true);


//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.container, weatherFragment)
//                .add(R.id.container, forecastFragment)
//                .commit();
//
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.container, forecastFragment)
//                .commit();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int duration = Toast.LENGTH_SHORT;
        CharSequence refreshToast = "Refreshing";

        if (item.getItemId() == R.id.refresh) {
            final Handler handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    String content = msg.getData().getString("Server_response");
                    if(content!=null){
                        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "Cannot get response from the server", Toast.LENGTH_LONG).show();
                    }

//                        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
                }
            };

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Bundle bundle = new Bundle();
                    bundle.putString("Server_response", "some json here");

                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            });
            t.start();

            return true;
        } else if (item.getItemId() == R.id.settings) {
            Intent prefActivityIntent = new Intent(this, PrefActivity.class);
            startActivity(prefActivityIntent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }
}