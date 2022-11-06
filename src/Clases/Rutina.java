package Clases;

import ListaEjercicios.Ejercicio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rutina {
    private LocalDate _fechaInicio = LocalDate.now();
    private static double porcentajeRefuerzo =0.1;
    private ArrayList<DiaEjercicio> _dias;

    public Rutina(ArrayList<DiaEjercicio> diaEjercicios){
        this._dias = diaEjercicios;
    }
}
