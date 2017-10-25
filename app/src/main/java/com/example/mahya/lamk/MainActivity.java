package com.example.mahya.lamk;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FrameLayout frameLayout;
    Spinner spinner;
    Calendar myCalendar;
    EditText editTextDate, editTextTime;
    String myFormat;
    SimpleDateFormat sdf;
    Button searchButton;
    ListView listView;
    private ArrayList<HashMap<String, String>> roomsList;
    private ArrayList<HashMap<String, String>> contactList;
    TextView textViewHeader;
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
        searchButton = (Button) findViewById(R.id.searchButton);
        listView = (ListView) findViewById(R.id.listView);
        contactList = new ArrayList<>();
        textViewHeader = (TextView) findViewById(R.id.textViewHeading);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {
        myFormat = "dd/MM/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        Date currentDate = new Date();
        if((myCalendar).after(currentDate)) {
            editTextDate.setText(sdf.format(myCalendar.getTime()));

        }
        if((sdf.format(myCalendar.getTime())).equals(sdf.format(currentDate.getTime()))) {
            editTextDate.setText(sdf.format(myCalendar.getTime()));

        }
        else if((myCalendar).before(currentDate)) {
            editTextDate.setText("Perivous time is not acceptable");


        }
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
        searchButton.setVisibility(View.VISIBLE);
        listView.setVisibility(View.VISIBLE);

        roomsList = new ArrayList<>();

        Date currentDate = new Date();
        editTextDate.setHint("Select Date");
        editTextTime.setHint(currentDate.getHours() + ":" + currentDate.getMinutes());

        myFormat = "dd/MM/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);

        myCalendar = Calendar.getInstance();
//        Date currentDate = new Date();
//        if(currentDate.)
//        editTextTime.setText(myCalendar.set(Calendar.DATE, 0));
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

        editTextTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        Date currentDate = new Date();

//                        if((sdf.format(myCalendar.getTime())).equals(sdf.format(currentDate.getTime()))) {
//                            editTextDate.setText(sdf.format(myCalendar.getTime()));
                            if((selectedHour < currentDate.getHours() +1) && (sdf.format(myCalendar.getTime())).equals(sdf.format(currentDate.getTime())) || (selectedHour > 19) || (selectedHour < 8)) {
                                editTextTime.setText("Time is not acceptable");
                            }
                            else if ((selectedHour > 19) || (selectedHour < 8)){
                                editTextTime.setText("Time is not acceptable");
                            }
                            else {
                                editTextTime.setText( selectedHour + ":" + selectedMinute);
                            }
//                        }
//                        if(selectedHour > currentDate.getHours()) {
//                            editTextDate.setText("you can reserve class");
//                        }
//                        else {
//                            editTextDate.setText("You CANNNNNT");
//                        }



                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewHeader.setVisibility(View.VISIBLE);
                handlingJson();
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
        searchButton.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        textViewHeader.setVisibility(View.INVISIBLE);
    }
    private void lunchFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_lunch_background_new);
        getSupportActionBar().setTitle("Lunch");
        spinner.setVisibility(View.INVISIBLE);
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
        searchButton.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        textViewHeader.setVisibility(View.INVISIBLE);
    }
    private void eventFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_event_background_new);
        getSupportActionBar().setTitle("Reppu events");
        spinner.setVisibility(View.INVISIBLE);
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
        searchButton.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        textViewHeader.setVisibility(View.INVISIBLE);
    }
    private void routeFragment() {
        frameLayout.setBackgroundResource(R.mipmap.lamk_route_background_new);
        getSupportActionBar().setTitle("Route guide");
        spinner.setVisibility(View.INVISIBLE);
        editTextDate.setVisibility(View.INVISIBLE);
        editTextTime.setVisibility(View.INVISIBLE);
        searchButton.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.INVISIBLE);
        textViewHeader.setVisibility(View.INVISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void handlingJson() {
//        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
//        Network network = new BasicNetwork(new HurlStack());
//        RequestQueue mRequestQueue = new RequestQueue(cache, network);
//        mRequestQueue.start();
        RequestQueue queue = Volley.newRequestQueue(this);
//        userId = getIntent().getStringExtra("userId");
        final String url = "http://10.0.2.2:3000/api/rooms";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
//                        Log.d("Response", response.toString());
//                        String responseStr = response.toString();
//                        JSONObject jsonObj = new JSONObject(response);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            // Getting JSON Array node
                            JSONArray contacts = jsonObj.getJSONArray("data");
                            // looping through All Contacts
                            for (int i = 0; i < contacts.length(); i++) {
                                JSONObject contactJSONObject = contacts.getJSONObject(i);
                                String campus = contactJSONObject.getString("campus");
                                String class_number = contactJSONObject.getString("class_number");
//                                first_name = (first_name.equals("null")) ? "" : first_name;
                                String capacity = contactJSONObject.getString("capacity");
                                String floor = contactJSONObject.getString("floor");
                                editTextTime.setText(floor);
//                                last_name = (last_name.equals("null")) ? "" : last_name;
//                                statusFriend = contactJSONObject.getString("available");
                                HashMap<String, String> contact = new HashMap<>();
                                contact.put("campus", campus);
                                contact.put("class_number", class_number);
                                contact.put("capacity", capacity);
                                contact.put("floor", floor);
//                                statusFriend = (statusFriend.equals("true")) ? "Available" : "UnAvailable";
//                                contact.put("status", statusFriend);
                                contactList.add(contact);
                                //Sorting contactlist by status
//                                try {
////                                    Collections.sort(contactList, new Comparator<HashMap<String, String>>() {
////                                        @Override
////                                        public int compare(HashMap<String, String> stringHashMapEmail, HashMap<String, String> stringHashMapEmail1) {
////                                            return stringHashMapEmail.get("status").compareTo(stringHashMapEmail1.get("status"));
////                                        }
////                                    });
//                                } catch (Exception e) {
//                                    // TODO Auto-generated catch block
//                                    e.printStackTrace();
//                                }
                                ListAdapter adapter = new SimpleAdapter(MainActivity.this, contactList,
                                        R.layout.list_item, new String[]{"campus", "class_number", "capacity", "floor"},
                                        new int[]{R.id.campus, R.id.room_number, R.id.capacity, R.id.floor});
                                listView.setAdapter(adapter);
                            }
                        } catch (final JSONException e) {
//                            Log.e(TAG, "Json parsing error: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
////                tokenId = getActivity().getIntent().getStringExtra("tokenId");
////                headers.put("Authorization", tokenId);
//                return headers;
//            }
//        };
        queue.add(getRequest);
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