package com.chemasmas.ahorcado.entidades;

public class Problema {

    String problema;
    boolean estatus;
    String nivel;
    String tipo;
    String latex;
    String sol;

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

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLatex() {
        return latex;
    }

    public void setLatex(String latex) {
        this.latex = latex;
    }

    public String getSol() {
        return sol;
    }

    public void setSol(String sol) {
        this.sol = sol;
    }

    @Override
    public String toString() {
        return "Problema{" +
                "problema='" + problema + '\'' +
                ", estatus=" + estatus +
                ", nivel='" + nivel + '\'' +
                ", tipo='" + tipo + '\'' +
                ", latex='" + latex + '\'' +
                ", sol='" + sol + '\'' +
                '}';
    }
}
