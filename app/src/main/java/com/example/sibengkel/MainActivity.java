package com.example.sibengkel;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sibengkel.controllers.BookController;
import com.example.sibengkel.models.BookModels;
import com.example.sibengkel.utils.Database;
import com.example.sibengkel.utils.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    TextView txt_profile_name;
    Button btn_logout;

    SharedPreferences sharedpreferences;
    Intent intent;

    private DatabaseHelper db;
    private List<BookModels> listBookings = new ArrayList<BookModels>();
    private RecyclerView recyclerView;
    private BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateCoreApp();

        txt_profile_name = (TextView) findViewById(R.id.txt_profile_name);
        txt_profile_name.setText(getIntent().getExtras().get("name").toString());

        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        db = new DatabaseHelper(this);

//        listBookings(db.allBookings());
        recyclerView = (RecyclerView) findViewById(R.id.rv_bookings);
        adapter = new BookingAdapter(this, db.allBookings(getIntent().getExtras().get("email").toString()));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        recyclerView.addItemDecoration(decoration);        recyclerView.setAdapter(adapter);


//        Log.d("book",String.valueOf(values));


    }

    public void bookNow(View view){
        intent = new Intent(MainActivity.this, ScheduleActivity.class);
        intent.putExtra("email", getIntent().getExtras().get("email").toString());
        startActivity(intent);
    }

    private void logout() {
        sharedpreferences = getSharedPreferences(
                LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(LoginActivity.session_status, false);
        editor.putString(LoginActivity.TAG_ID, null);
        editor.putString(LoginActivity.TAG_EMAIL, null);
        editor.putString(LoginActivity.TAG_NAME, null);
        editor.putString(LoginActivity.TAG_PHONE, null);
        editor.putString(LoginActivity.TAG_ADDRESS, null);
        editor.commit();

        intent = new Intent(MainActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

    private void initiateCoreApp() {
        Database database = new DatabaseHelper(this);
        BookController.setDatabase(database);
    }

}
