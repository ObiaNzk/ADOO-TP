package Clases;

import ListaEjercicios.Ejercicio;

import java.util.ArrayList;

public class DiaEjercicio {
    private String _dia;
    private ArrayList<EjercicioRutina> _ejercicios = new ArrayList<EjercicioRutina>();

    public DiaEjercicio(String dia,ArrayList<EjercicioRutina> ejercicios ){
        this._dia = dia;
        this._ejercicios = ejercicios;
    }

    public void agregarEjercicio(Ejercicio ejercicio, int series, int repeticiones, double peso){

        EjercicioRutina ejercicioRutina = new EjercicioRutina(ejercicio, series, repeticiones, peso);
        _ejercicios.add(ejercicioRutina);
    }

    public String getDia() {
        return _dia;
    }
}
