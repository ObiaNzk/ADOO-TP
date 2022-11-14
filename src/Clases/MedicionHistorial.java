package Clases;

import Clases.Medicion.MedicionResultado;

import java.util.Date;

public class MedicionHistorial {
    private MedicionResultado _medicion;
    private Date _fecha;

    public MedicionHistorial(MedicionResultado medicion){
        this._medicion = medicion;
        this._fecha = new Date();
    }

    public Date getFecha(){
        return this._fecha;
    }

    public MedicionResultado getMedicion(){
        return this._medicion;
    }
}


