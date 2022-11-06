package Clases;

import Enums.Sexo;
import Trofeos.Trofeo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Socio {
    private int _dni;
    private String _psw;
    private String _nombre;
    private String _apellido;
    private LocalDate _fechaNacimiento;
    private Sexo _sexo;

    private Double _altura;

    private Objetivo _objetivo;
    private ArrayList<Integer> _diasEntrenamiento = new ArrayList<Integer>();

    private ArrayList<Trofeo> _trofeos = new ArrayList<Trofeo>();

    public Socio(String nombre, String apellido, int dni, LocalDate fechaNacimiento, Sexo sexo ){
        this._dni = dni;
        this._nombre = nombre;
        this._apellido = apellido;
        this._fechaNacimiento = fechaNacimiento;
        this._sexo = sexo;

    }

    public void cambiarObjetivo(String objetivo){
        switch(objetivo){
            case "1": _objetivo.cambiarEstrategia(new ObjetivoPerderPeso());
            case "2": _objetivo.cambiarEstrategia(new ObjetivoTonificar());
            case "3": _objetivo.cambiarEstrategia(new ObjetivoMantener());
        }

    }

    public int edad(){

        return Math.toIntExact(ChronoUnit.YEARS.between(_fechaNacimiento, LocalDate.now()));
    }

    public void registrarMedicion(){

    }

    public Medicion getValoresIdeales(){
        Medicion medicion = null;
        return medicion;
    }


    public String toString(){
        return "Socio " + _nombre + " " + _apellido + ". DNI número " + _dni + ".";

    }


    public int getDNI(){
        return this._dni;
    }


    public void setPsw(String _psw) {
        this._psw = _psw;
    }

    public String getPsw() {
        return this._psw;
    }

    public Objetivo getObjetivo() {
        return _objetivo;

    }
}
