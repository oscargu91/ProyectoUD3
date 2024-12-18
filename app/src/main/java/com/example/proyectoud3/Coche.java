package com.example.proyectoud3;

public class Coche {
    private int foto;
    private String modelo;
    private String precio;
    private String propulsion;
    private String autonomia;
    private Boolean booleanoFav = false;



    public Coche(int foto, String modelo, String precio, String propulsion, String autonomia, Boolean booleanoFav) {
        this.foto = foto;
        this.modelo = modelo;
        this.precio = precio;
        this.propulsion = propulsion;
        this.autonomia = autonomia;
        this.booleanoFav = booleanoFav;
    }



    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPropulsion() {
        return propulsion;
    }

    public void setPropulsion(String propulsion) {
        this.propulsion = propulsion;
    }

    public String getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(String autonomia) {
        this.autonomia = autonomia;
    }
    public Boolean getBooleanoFav() {
        return booleanoFav;
    }

    public void setBooleanoFav(Boolean booleanoFav) {
        this.booleanoFav = booleanoFav;
    }

}
