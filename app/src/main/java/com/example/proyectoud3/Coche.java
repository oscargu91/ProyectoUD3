package com.example.proyectoud3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Coche implements Parcelable {
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


    protected Coche(Parcel in) {
        foto = in.readInt();
        modelo = in.readString();
        precio = in.readString();
        propulsion = in.readString();
        autonomia = in.readString();
        byte tmpBooleanoFav = in.readByte();
        booleanoFav = tmpBooleanoFav == 0 ? null : tmpBooleanoFav == 1;
    }

    public static final Creator<Coche> CREATOR = new Creator<Coche>() {
        @Override
        public Coche createFromParcel(Parcel in) {
            return new Coche(in);
        }

        @Override
        public Coche[] newArray(int size) {
            return new Coche[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(foto);
        dest.writeString(modelo);
        dest.writeString(precio);
        dest.writeString(propulsion);
        dest.writeString(autonomia);
        dest.writeByte((byte) (booleanoFav == null ? 0 : booleanoFav ? 1 : 2));
    }
}
