package Clases.medicion;

import Enums.Sexo;

public class medicion implements medicionAdapter {
    private final medicionAdapter _medidor;

    public medicionResultado medir(double altura, Sexo sexo) {
        return _medidor.medir(altura, sexo);}


    public medicion(medicionAdapter medidor) {
        this._medidor = medidor;
    }
}



