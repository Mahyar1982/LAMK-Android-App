package com.example.mahya.lamk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FrameLayout frameLayout;
    Spinner spinner;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeFragment();
                    return true;
                case R.id.navigation_schedule_rooms:
                    scheduleFragment();
                    return true;
                case R.id.navigation_lunch:
                    lunchFragment();
                    return true;
                case R.id.navigation_events:
                    eventFragment();
                    return true;
                case R.id.navigation_route_guide:
                    routeFragment();
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
        frameLayout.setBackgroundResource(R.mipmap.lamk_home_background_new);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        spinner = (Spinner) findViewById(R.id.spinner);
    }

    private void scheduleFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_schedule_background_new);
        getSupportActionBar().setTitle("Schedule and rooms");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void homeFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_home_background_new);
        getSupportActionBar().setTitle("LAMK");
        spinner.setVisibility(View.INVISIBLE);
    }
    private void lunchFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_lunch_background_new);
        getSupportActionBar().setTitle("Lunch");
        spinner.setVisibility(View.INVISIBLE);
    }
    private void eventFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_event_background_new);
        getSupportActionBar().setTitle("Reppu events");
        spinner.setVisibility(View.INVISIBLE);
    }
    private void routeFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_route_background_new);
        getSupportActionBar().setTitle("Route guide");
        spinner.setVisibility(View.INVISIBLE);
    }
}
