package com.example.mahya.lamk;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.ViewGroup.*;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    frameLayout.setBackgroundResource(R.mipmap.lamk_home_background);
                    getSupportActionBar().setTitle("LAMK");
                    return true;
                case R.id.navigation_schedule_rooms:
                    frameLayout.setBackgroundResource(R.mipmap.lamk_schedule_background);
                    getSupportActionBar().setTitle("Schedule and rooms");
                    return true;
                case R.id.navigation_lunch:
                    frameLayout.setBackgroundResource(R.mipmap.lamk_lunch_background);
                    getSupportActionBar().setTitle("Lunch");
                    return true;
                case R.id.navigation_events:
                    frameLayout.setBackgroundResource(R.mipmap.lamk_event_background);
                    getSupportActionBar().setTitle("Reppu events");
                    return true;
                case R.id.navigation_route_guide:
                    frameLayout.setBackgroundResource(R.mipmap.lamk_route_background);
                    getSupportActionBar().setTitle("Route guide");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("LAMK");
//        LinearLayout lr = (LinearLayout) findViewById(R.id.container);
//        ActionBar ab = getActionBar();
//        TextView tv = new TextView(getApplicationContext());
//        LayoutParams lp = new LinearLayout.LayoutParams
//                (LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        tv.setLayoutParams(lp);
//        tv.setText("LAMK");
//        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        ab.setCustomView(tv);
//        tv.setGravity(Gravity.CENTER);

        frameLayout = (FrameLayout) findViewById(R.id.content);
        frameLayout.setBackgroundResource(R.mipmap.lamk_home_background);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void searchRoom() {

    }

}
