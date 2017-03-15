package com.dtu.jacopomattia.ghw;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
// Test 1
public class HovedMenuen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoved_menuen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Opret ny opgave", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

                startActivity(new Intent(getApplication(), OpretOpgave.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        LinearLayout headerprofil = (LinearLayout) findViewById(R.id.header_profil);
        headerprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Videre til profilen", Toast.LENGTH_SHORT).show();

//                startActivity(new Intent(getApplication(), OpretOpgave.class));
//                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {

//            Fragment fragment = new HovedMenuen();
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.fragmentos, fragment)  // tom container i layout
//                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hoved_menuen, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.fag1) {
                if (item.isChecked()){
                    item.setChecked(false);
                    Toast.makeText(this, "Check fag 1", Toast.LENGTH_SHORT).show();
                }
                else if (item.isChecked()==false) item.setChecked(true);

        } else if (id == R.id.fag2) {
                if (item.isChecked()){
                    Toast.makeText(this, "Check fag 2", Toast.LENGTH_SHORT).show();
                    item.setChecked(false);
                }
                else if (item.isChecked()==false) item.setChecked(true);


        } else if (id == R.id.semester1) {
                if (item.isChecked()){
                    Toast.makeText(this, "Check 1. semester", Toast.LENGTH_SHORT).show();
                    item.setChecked(false);
                }
                else if (item.isChecked()==false) item.setChecked(true);

        } else if (id == R.id.semester2) {
                if (item.isChecked()){
                    Toast.makeText(this, "Check 2. semester", Toast.LENGTH_SHORT).show();
                    item.setChecked(false);
                }
                else if (item.isChecked()==false) item.setChecked(true);
        } else if (id == R.id.institut1) {
                if (item.isChecked()){
                    Toast.makeText(this, "Check Elektronik Instituttet", Toast.LENGTH_SHORT).show();
                    item.setChecked(false);
                }
                else if (item.isChecked()==false) item.setChecked(true);

        } else if (id == R.id.institut2) {
                if (item.isChecked()){
                    Toast.makeText(this, "Check IT Instituttet", Toast.LENGTH_SHORT).show();
                    item.setChecked(false);
                }
                else if (item.isChecked()==false) item.setChecked(true);
        }

        else if (id == R.id.fag_vis_alle) {
                Toast.makeText(this, "Viser Alle", Toast.LENGTH_SHORT).show();


        }
        else if (id == R.id.semester_vis_alle) {
            Toast.makeText(this, "Viser Alle", Toast.LENGTH_SHORT).show();


        }
        else if (id == R.id.institut_vis_alle) {
            Toast.makeText(this, "Viser Alle", Toast.LENGTH_SHORT).show();


        }
        if (id == R.id.action_settings) {
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}
// Hej Abid
