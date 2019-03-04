package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    ArrayList<Portal> portalNames = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.rvPortals);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecyclerViewAdapter(this, portalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        FloatingActionButton addButton = findViewById(R.id.toAddPortal);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivityForResult(new Intent(MainActivity.this, AddPortal.class), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d( "tag", "hallo");
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String url = data.getStringExtra("newUrl");
                String title = data.getStringExtra("newTitle");
                addToList(url, title);
                updateUI();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    public void addToList(String url, String title) {
        portalNames.add(new Portal(url, title));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateUI() {

        if (adapter == null) {
            adapter = new RecyclerViewAdapter(this, portalNames);
            recyclerView.setAdapter(adapter);
        } else {
            Log.d("hay", "Hai");
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
