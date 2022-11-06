package Clases;

public class Objetivo {
    private ObjetivoStrategy _estrategia;
    private Rutina _rutina;

    public void cambiarEstrategia(ObjetivoStrategy estrategia){
        this._estrategia = estrategia;
    }

    public void crearRutina(){
        this._rutina = _estrategia.crearRutina();
    }


}
