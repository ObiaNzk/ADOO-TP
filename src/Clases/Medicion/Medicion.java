package Clases.Medicion;

import Enums.Sexo;

public class Medicion implements MedicionAdapter {
    private final MedicionAdapter _medidor;

    public MedicionResultado medir(double altura, Sexo sexo) {
        return _medidor.medir(altura, sexo);}


    public Medicion(MedicionAdapter medidor) {
        this._medidor = medidor;
    }
}



