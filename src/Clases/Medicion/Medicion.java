package Clases.Medicion;

import Enums.Sexo;

public class Medicion implements MedicionAdapter {


    public MedicionResultado medir(double altura, Sexo sexo) {
        var resultado = new MedicionResultado();
        resultado.setGrasaCorporal(8);
        resultado.setMasaMuscular(20);
        resultado.setPeso(80);
        return resultado;
    }

}



