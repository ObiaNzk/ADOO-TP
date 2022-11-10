package Clases.objetivo;

import Clases.Rutina;

import java.util.ArrayList;

public abstract class ObjetivoStrategy {
    abstract public Rutina crearRutina(String[] dias);

}
