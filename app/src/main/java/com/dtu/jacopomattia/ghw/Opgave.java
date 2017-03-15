package com.dtu.jacopomattia.ghw;

import android.widget.ImageView;

/**
 * Created by JacopoMattia on 06-03-2017.
 */

public class Opgave {

    String opgave_beskrivelse;
    String instituttet;
    String semestret;
    String faget;

    Boolean TaskPronto;

    String OpgaveProblemet;

    ImageView Billede;
    // FIL OBJEKT


    public String getOpgave_beskrivelse() {
        return opgave_beskrivelse;
    }

    public void setOpgave_beskrivelse(String opgave_beskrivelse) {
        this.opgave_beskrivelse = opgave_beskrivelse;
    }

    public String getInstituttet() {
        return instituttet;
    }

    public void setInstituttet(String instituttet) {
        this.instituttet = instituttet;
    }

    public String getSemestret() {
        return semestret;
    }

    public void setSemestret(String semestret) {
        this.semestret = semestret;
    }

    public String getFaget() {
        return faget;
    }

    public void setFaget(String faget) {
        this.faget = faget;
    }
    public Boolean getTaskPronto() {
        return TaskPronto;
    }

    public void setTaskPronto(Boolean taskPronto) {
        TaskPronto = taskPronto;
    }
    public ImageView getBillede() {
        return Billede;
    }

    public void setBillede(ImageView billede) {
        Billede = billede;
    }

    public String getOpgaveProblemet() {
        return OpgaveProblemet;
    }

    public void setOpgaveProblemet(String opgaveProblemet) {
        OpgaveProblemet = opgaveProblemet;
    }





}
