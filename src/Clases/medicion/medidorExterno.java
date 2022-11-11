package Clases.medicion;

public class medidorExterno {
    public medicionResultado medir(double altura, int peso, char sexo) {
        var resultado = new medicionResultado();
        resultado.setgrasaCorporal(8);
        resultado.setmasaMuscular(20);
        return resultado;
    }
}
