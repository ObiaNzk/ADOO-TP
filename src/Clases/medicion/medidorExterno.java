package Clases.medicion;

import Enums.Sexo;


public class medidorExterno implements medicionAdapter {
    public medicionResultadoIdeal medir(double altura, int peso, Sexo sexo) {
        var resultado = new medicionResultadoIdeal();
        resultado.setgrasaCorporal(8);
        resultado.setmasaMuscular(20);
        return resultado;
    }
}
