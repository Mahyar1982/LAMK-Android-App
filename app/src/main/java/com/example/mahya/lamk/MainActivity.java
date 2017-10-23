package com.example.mahya.lamk;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FrameLayout frameLayout;
    Spinner spinner;
    Calendar myCalendar;
    EditText editTextDate, editTextTime;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("LAMK");
        frameLayout = (FrameLayout) findViewById(R.id.content);
        frameLayout.setBackgroundResource(R.mipmap.lamk_home_background_new);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextDate = (EditText) findViewById(R.id.datePicker);
        editTextTime = (EditText) findViewById(R.id.timePicker);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void scheduleFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_schedule_background_new);
        getSupportActionBar().setTitle("Schedule and rooms");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setVisibility(View.VISIBLE);
        spinner.setOnItemSelectedListener(this);

        editTextDate.setVisibility(View.VISIBLE);
        editTextTime.setVisibility(View.VISIBLE);
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editTextDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
    }
    private void lunchFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_lunch_background_new);
        getSupportActionBar().setTitle("Lunch");
        spinner.setVisibility(View.INVISIBLE);
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
    }
    private void eventFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_event_background_new);
        getSupportActionBar().setTitle("Reppu events");
        spinner.setVisibility(View.INVISIBLE);
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
    }
    private void routeFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_route_background_new);
        getSupportActionBar().setTitle("Route guide");
        spinner.setVisibility(View.INVISIBLE);
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.N)
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

}
