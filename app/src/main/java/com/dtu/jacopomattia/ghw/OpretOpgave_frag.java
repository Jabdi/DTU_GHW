package com.dtu.jacopomattia.ghw;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by JacopoMattia on 02-03-2017.
 */

public class OpretOpgave_frag extends Fragment implements View.OnClickListener, Runnable {

    Spinner spinner_fag;
    Spinner spinner_semester;
    Spinner spinner_institut;

    ArrayAdapter<CharSequence> adapter_fag;
    ArrayAdapter<CharSequence> adapter_semestre;
    ArrayAdapter<CharSequence> adapter_institutter;

    EditText opgavebeskrivelse;

    public View onCreateView(LayoutInflater i, ViewGroup container, Bundle savedInstanceState) {
        View rod = i.inflate(R.layout.ny_opgave_layout, container, false);

        spinner_fag = (Spinner) rod.findViewById(R.id.spinner_fag);
        spinner_semester = (Spinner) rod.findViewById(R.id.spinner_semester);
        spinner_institut = (Spinner) rod.findViewById(R.id.spinner_institut);

        adapter_fag = ArrayAdapter.createFromResource(getActivity(), R.array.fagene, android.R.layout.simple_spinner_item);
        adapter_semestre = ArrayAdapter.createFromResource(getActivity(), R.array.semestrerne, android.R.layout.simple_spinner_item);
        adapter_institutter = ArrayAdapter.createFromResource(getActivity(), R.array.institutterne, android.R.layout.simple_spinner_item);

        adapter_fag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_semestre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter_institutter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_fag.setAdapter(adapter_fag);
        spinner_semester.setAdapter(adapter_semestre);
        spinner_institut.setAdapter(adapter_institutter);



        return rod;
    }





    @Override
    public void onClick(View v) {

    }

    @Override
    public void run() {

    }
}
