package Clases;

import Enums.Sexo;
import Trofeos.Trofeo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Socio {
    private int _dni;
    private String _psw;
    private String _nombre;
    private String _apellido;
    private LocalDate _fechaNacimiento;
    private Sexo _sexo;

    private Double _altura;

    private String[]_diasEntrenamiento;

    private Objetivo _objetivo;

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

    public void setDiasEntrenamiento(String[] dias) {
        this._diasEntrenamiento = dias;
    }

    public void elegirObjetivo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija un objetivo. Ingrese el número de la opción que corresponda.\n" +
                "\n" +
                "1. Bajar de peso\n" +
                "\n" +
                "2. Tonificar cuerpo\n" +
                "\n" +
                "3. Mantener la figura");
        String elegido = scanner.nextLine();
        while(!elegido.equals("1") && !elegido.equals("2") && !elegido.equals("3")){
            System.out.println("Ingrese un número de objetivo válido.");
            elegido = scanner.nextLine();
        }

        this.cambiarObjetivo(elegido);

    }

    public void elegirDiasEntrenamiento(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija qué días desea seleccionar como días de entrenamiento, separados por coma.\n LU, MA, MI, JU, VI, SA, DO. ");
        String dias = scanner.nextLine();

        this.setDiasEntrenamiento(dias.split(","));
    }

    public void entrenar() {
    }

    public void getProgeso() {
    }

    public void getTrofeos() {
    }
}
