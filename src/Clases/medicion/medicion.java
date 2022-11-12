package Clases.medicion;

import Enums.Sexo;

public class medicion implements medicionAdapter {
    private final medicionAdapter _medidor;

    public medicionResultadoIdeal medir(double altura, int peso, Sexo sexo) {
        return _medidor.medir(altura, peso, sexo);}

    public medicion(medicionAdapter medidor) {
        this._medidor = medidor;
    }
}



