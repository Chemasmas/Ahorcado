package com.chemasmas.ahorcado.entidades;

public class Problema {

    public Problema() {
        estatus=false;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    boolean estatus;
    String problema;
}
