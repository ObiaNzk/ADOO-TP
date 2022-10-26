package Clases;

import Enums.Sexo;
import Trofeos.Trofeo;

import java.util.ArrayList;

public class Socio {
    static int contadorSocios = 0;
    private int _id;
    private String _nombre;
    private String _apellido;
    private int _edad;
    private Sexo _sexo;

    private double _peso;
    private double _altura;
    private Double _masa;
    private Double _grasa;

    private Rutina _rutina;
    private Objetivo _objetivo;
    private ArrayList<Integer> _diasEntrenamiento = new ArrayList<Integer>();

    private ArrayList<Trofeo> _trofeos = new ArrayList<Trofeo>();

    public Socio(String nombre, String apellido, int edad, Sexo sexo ){
        this._id = contadorSocios;
        contadorSocios++;
        this._nombre = nombre;
        this._apellido = apellido;
        this._edad = edad;
        this._sexo = sexo;

    }



    public String toString(){
        return "Socio " + _nombre + " " + _apellido + ". Número " + _id + ".";

    }

    public void setRutina(){
        this._rutina = _objetivo.crearRutina();
    }

}
