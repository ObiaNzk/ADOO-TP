package Clases.medicion;

import Enums.Sexo;


public class medidorExterno implements medicionAdapter {
    public medicionResultado medir(double altura, Sexo sexo) {
        var resultado = new medicionResultado();
        resultado.setgrasaCorporal(8);
        resultado.setmasaMuscular(20);
        resultado.setPeso(300);
        return resultado;
    }
}
