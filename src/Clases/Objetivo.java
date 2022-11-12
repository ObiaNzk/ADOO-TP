package Clases;

public class Objetivo {
    private ObjetivoStrategy _estrategia;
    private Rutina _rutina;

    public void cambiarEstrategia(ObjetivoStrategy estrategia){
        this._estrategia = estrategia;
    }

    public void crearRutina(String[] dias){
        this._rutina = this._estrategia.crearRutina(dias);
    }

    public Rutina getRutina(){
        return this._rutina;
    }

    public void mostrarRutina(int dia) {

    }
}