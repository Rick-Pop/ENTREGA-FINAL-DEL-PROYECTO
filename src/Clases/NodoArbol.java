/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author VICTUS
 */
public class NodoArbol {

    String nombre, depa, municipio, lugVacuna, FVacuna1, FVacuna2, FVacuna3;
    
    long dpi;
    int NumDosis;
    NodoArbol hijoIzquierdo, hijoDerecho;

    public NodoArbol(String nom, long dpi) {
        this.nombre = nom;
        this.dpi = dpi;
        this.depa = null;
        this.municipio = null;
        this.lugVacuna = null;
        this.NumDosis = 0;
        this.FVacuna1 = null;
        this.FVacuna2 = null;
        this.FVacuna3 = null;
        this.hijoDerecho = null;
        this.hijoIzquierdo = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepa() {
        return depa;
    }

    public void setDepa(String depa) {
        this.depa = depa;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getLugVacuna() {
        return lugVacuna;
    }

    public void setLugVacuna(String lugVacuna) {
        this.lugVacuna = lugVacuna;
    }

    public String getFVacuna1() {
        return FVacuna1;
    }

    public void setFVacuna1(String FVacuna1) {
        this.FVacuna1 = FVacuna1;
    }

    public String getFVacuna2() {
        return FVacuna2;
    }

    public void setFVacuna2(String FVacuna2) {
        this.FVacuna2 = FVacuna2;
    }

    public String getFVacuna3() {
        return FVacuna3;
    }

    public void setFVacuna3(String FVacuna3) {
        this.FVacuna3 = FVacuna3;
    }

    public long getDpi() {
        return dpi;
    }

    public void setDpi(long dpi) {
        this.dpi = dpi;
    }


    public int getNumDosis() {
        return NumDosis;
    }

    public void setNumDosis(int NumDosis) {
        this.NumDosis = NumDosis;
    }

}
