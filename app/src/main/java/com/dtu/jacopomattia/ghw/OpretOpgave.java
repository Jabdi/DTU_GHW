package com.dtu.jacopomattia.ghw;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by JacopoMattia on 02-03-2017.
 */

public class OpretOpgave extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner_fag;
    Spinner spinner_semester;
    Spinner spinner_institut;

    ArrayAdapter<CharSequence> adapter_fag;
    ArrayAdapter<CharSequence> adapter_semestre;
    ArrayAdapter<CharSequence> adapter_institutter;

    EditText opgavebeskrivelse;

    CheckBox uploadbillede;

    Button askforhelp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ny_opgave_layout);

        spinner_fag = (Spinner) findViewById(R.id.spinner_fag);
        spinner_semester = (Spinner) findViewById(R.id.spinner_semester);
        spinner_institut = (Spinner) findViewById(R.id.spinner_institut);

        adapter_fag = ArrayAdapter.createFromResource(this, R.array.fagene, android.R.layout.simple_spinner_item);
        adapter_semestre = ArrayAdapter.createFromResource(this, R.array.semestrerne, android.R.layout.simple_spinner_item);
        adapter_institutter = ArrayAdapter.createFromResource(this, R.array.institutterne, android.R.layout.simple_spinner_item);

        adapter_fag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_semestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_institutter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fag.setAdapter(adapter_fag);
        spinner_semester.setAdapter(adapter_semestre);
        spinner_institut.setAdapter(adapter_institutter);

        spinner_fag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_institut.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position)+" is selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        opgavebeskrivelse = (EditText) findViewById(R.id.opgave_beskrivelse);
        opgavebeskrivelse.setOnClickListener(this);

        uploadbillede = (CheckBox) findViewById(R.id.picture_upload);
        uploadbillede.setOnClickListener(this);

        askforhelp = (Button) findViewById(R.id.ask_for_help_button);
        askforhelp.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {

        if (v==opgavebeskrivelse) {
            if (opgavebeskrivelse.getText().toString().equals("Indsæt opgavebeskrivelse..."))
            opgavebeskrivelse.setText("");
        }
        if (v==uploadbillede) {
            uploadbillede.setChecked(false);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Upload fil (.pdf, .docx) \n eller Tag Billede");

            dialog.setPositiveButton("Tag Billede", new android.app.AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "DU TOG ET BILLED", Toast.LENGTH_SHORT).show();
                    uploadbillede.setChecked(true);

                }
            });
            dialog.setNegativeButton("Upload fil", new android.app.AlertDialog.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "DU UPLOADEDE EN FIL", Toast.LENGTH_SHORT).show();
                    uploadbillede.setChecked(true);

                }
            });
            dialog.show();
        }
        if (v==askforhelp) {
            Toast.makeText(getApplicationContext(), "Opslag lagt op", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(getApplication(), HovedMenuen.class));
            overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_in_left);


        }
    }


}

