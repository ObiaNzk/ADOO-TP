package Clases.Objetivo;

import Clases.Rutina;

public abstract class ObjetivoStrategy {
    abstract public Rutina crearRutina(String[] dias);

    abstract public boolean cumplioObjetivo();

}
