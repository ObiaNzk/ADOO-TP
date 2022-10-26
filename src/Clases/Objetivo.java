package Clases;

public class Objetivo {
    private ObjetivoStrategy _estrategia;
    private Rutina _rutina;

    public void cambiarObjetivo(Objetivo objetivo){

    }

    public void crearRutina(){
        this._rutina = _estrategia.crearRutina();
    }


}
