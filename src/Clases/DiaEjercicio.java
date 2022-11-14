package Clases;

import ListaEjercicios.Ejercicio;

import java.util.ArrayList;

public class DiaEjercicio {
    private String _dia;
    private ArrayList<EjercicioRutina> _ejercicios = new ArrayList<EjercicioRutina>();
    private int _idRutina = -1;
    private boolean _realizado = false;

    public DiaEjercicio(String dia,ArrayList<EjercicioRutina> ejercicios ){
        this._dia = dia;
        this._ejercicios = ejercicios;
    }

    public DiaEjercicio(String dia, int idRutina) {
        this._dia = dia;
        this._idRutina = idRutina;
    }

    public void agregarEjercicio(Ejercicio ejercicio, int series, int repeticiones, double peso){

        EjercicioRutina ejercicioRutina = new EjercicioRutina(ejercicio, series, repeticiones, peso);
        _ejercicios.add(ejercicioRutina);
    }

    public String getDia() {
        return _dia;
    }

    public ArrayList<EjercicioRutina> getEjerciciosRutina() {
        return this._ejercicios;
    }

    public void agregarEjercicio(EjercicioRutina ejercicio){
        _ejercicios.add(ejercicio);
    }

    public void setIdRutina(int id){
        this._idRutina = id;
    }
    public int getIdRutina(){
        return _idRutina;
    }

    public ArrayList<EjercicioRutina> getEjercicios(){
        return _ejercicios;
    }

    public void setRealizado() {
        this._realizado = true;
    }

    public boolean realizado() {
        return _realizado;
    }
}
