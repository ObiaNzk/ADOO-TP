package Clases.medicion;

import java.time.LocalDate;

interface medicionAdapter {
    medicionResultado medir(double altura, int peso, char sexo);
}

public class Medicion implements medicionAdapter {
    private medicionAdapter _medidor;

    public medicionResultado medir(double altura, int peso, char sexo) {
        return _medidor.medir(altura, peso, sexo);}

    public Medicion(medicionAdapter medidor) {
        this._medidor = medidor;
    }

}



