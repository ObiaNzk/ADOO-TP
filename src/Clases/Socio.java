package Clases;

import Enums.Sexo;
import Trofeos.Trofeo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Socio {
    static int contadorSocios = 0;
    private int _id;
    private String _nombre;
    private String _apellido;
    private LocalDate _fechaNacimiento;
    private Sexo _sexo;

    private Double _altura;

    private Objetivo _objetivo;
    private ArrayList<Integer> _diasEntrenamiento = new ArrayList<Integer>();

    private ArrayList<Trofeo> _trofeos = new ArrayList<Trofeo>();

    public Socio(String nombre, String apellido, LocalDate fechaNacimiento, Sexo sexo ){
        this._id = contadorSocios;
        contadorSocios++;
        this._nombre = nombre;
        this._apellido = apellido;
        this._fechaNacimiento = fechaNacimiento;
        this._sexo = sexo;

    }

    public void cambiarObjetivo(Objetivo objetivo){

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
        return "Socio " + _nombre + " " + _apellido + ". Número " + _id + ".";

    }


    public int getId(){
        return this._id;
    }

}
