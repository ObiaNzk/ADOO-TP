package Clases;

import java.time.LocalDate;
import java.util.ArrayList;

public class Rutina {
    private LocalDate _fechaInicio = LocalDate.now();
    private static double porcentajeRefuerzo =0.1;
    private ArrayList<DiaEjercicio> _dias;

    private int _id;
    private static int _countId = 0;

    public Rutina(ArrayList<DiaEjercicio> diaEjercicios){
        this._dias = diaEjercicios;
        this._id = _countId;
        _countId++;
    }

    public ArrayList<DiaEjercicio> getDiaEjercicios() {
        return this._dias;
    }

    public int getId(){
        return _id;
    }

}
