package Clases.Medicion;

import Enums.Sexo;


public class MedidorExterno implements MedicionAdapter {
    public MedicionResultado medir(double altura, Sexo sexo) {
        var resultado = new MedicionResultado();
        resultado.setGrasaCorporal(8);
        resultado.setMasaMuscular(20);
        resultado.setPeso(300);
        return resultado;
    }
}
