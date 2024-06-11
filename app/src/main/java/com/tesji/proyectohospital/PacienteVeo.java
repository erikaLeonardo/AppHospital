package com.tesji.proyectohospital;

public class PacienteVeo {

    private String nombre;
    private String ap1, ap2;
    private String telefono;
    private String email;
    private int fotop;

    public void lpacientesVer(){

    }

    public PacienteVeo(String nombre, String ap1, String ap2, String telefono, String email, int fotop) {
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.telefono = telefono;
        this.email = email;
        this.fotop = fotop;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFotop() {
        return fotop;
    }

    public void setFotop(int fotop) {
        this.fotop = fotop;
    }
}
