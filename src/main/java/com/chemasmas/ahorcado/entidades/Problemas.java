package com.chemasmas.ahorcado.entidades;

import java.util.ArrayList;

/**
 * Created by Adry on 30/10/2015.
 */
public class Problemas {
    ArrayList<Problema> problemas;

    public ArrayList<Problema> getProblemas() {
        return problemas;
    }

    public void setProblemas(ArrayList<Problema> problemas) {
        this.problemas = problemas;
    }

    public Problemas()
    {
        problemas=new ArrayList<>();
    }

}
