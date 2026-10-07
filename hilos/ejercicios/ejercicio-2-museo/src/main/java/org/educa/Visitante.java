package org.educa;

public class Visitante {
    private String nombre="";
    private long tiempo=0;

    public Visitante(String nombre){
        this.nombre=nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public long getTiempo() {
        return tiempo;
    }
    public void setTiempo(long tiempo) {
        this.tiempo=tiempo;
    }
}
