package Clases;

import ListaEjercicios.Ejercicio;

public class EjercicioRutina {
    private String _nombre;

    private Ejercicio _ejercicio;
    private int _series;
    private int _repeticiones;
    private double _pesoAsignado;

    public EjercicioRutina(Ejercicio ejercicio, int series, int repeticiones, double pesoAsignado){
        this._nombre = ejercicio.getNombre();
        this._ejercicio = ejercicio;
        this._series = series;
        this._repeticiones = repeticiones;
        this._pesoAsignado = pesoAsignado;

    }
}
