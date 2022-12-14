package Clases;

import Clases.Medicion.MedicionResultado;
import Clases.Objetivo.Objetivo;
import Clases.Objetivo.ObjetivoMantener;
import Clases.Objetivo.ObjetivoPerderPeso;
import Clases.Objetivo.ObjetivoTonificar;
import Enums.Sexo;
import Trofeos.Trofeo;
import Trofeos.TrofeoConstancia;
import Trofeos.TrofeoCreido;
import Trofeos.TrofeoDedicacion;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Socio {
    private final int _dni;
    private String _psw;
    private final String _nombre;
    private final String _apellido;
    private final LocalDate _fechaNacimiento;
    private final Sexo _sexo;
    private final Double _altura = 1.75;
    private String[] _diasEntrenamiento;
    private Objetivo _objetivo;
    private ArrayList<Trofeo> _trofeos = new ArrayList<Trofeo>();
    private Trofeo _observerCreido = new TrofeoCreido();
    private Trofeo _observerConstancia = new TrofeoConstancia();
    private ArrayList<DiaEjercicio> _historialEjercicios = new ArrayList<>();

    private ArrayList<MedicionHistorial> _historialMedicion = new ArrayList<MedicionHistorial>();

    private MedicionResultado _medicion;


    public Socio(String nombre, String apellido, int dni, LocalDate fechaNacimiento, Sexo sexo) {
        this._dni = dni;
        this._nombre = nombre;
        this._apellido = apellido;
        this._fechaNacimiento = fechaNacimiento;
        this._sexo = sexo;
        this._objetivo = new Objetivo();

        setMedicionInicial();


    }

    private void setMedicionInicial() {
        _medicion = new MedicionResultado();
        _medicion.setPeso(85);
        _medicion.setGrasaCorporal(8);
        _medicion.setMasaMuscular(20);
    }

    public Sexo getSexo() {
        return this._sexo;
    }

    public double getAltura() {
        return this._altura;
    }

    public void cambiarObjetivo(String objetivo) {
        if (objetivo.equals("1")) {
            System.out.println("Nuevo objetivo: Bajar de peso");
            System.out.println();
            _objetivo.cambiarEstrategia(new ObjetivoPerderPeso(this));
            _objetivo.crearRutina(this._diasEntrenamiento);
        } else if (objetivo.equals("2")) {
            System.out.println("Nuevo objetivo: Tonificar cuerpo");
            System.out.println();
            _objetivo.cambiarEstrategia(new ObjetivoTonificar(this));
            _objetivo.crearRutina(this._diasEntrenamiento);
        } else if (objetivo.equals("3")) {
            System.out.println("Nuevo objetivo: Mantener la figura");
            System.out.println();
            _objetivo.cambiarEstrategia(new ObjetivoMantener(this));
            _objetivo.crearRutina(this._diasEntrenamiento);
        } else {
            System.out.println("Opcion incorrecta.");
        }
    }

    public void setMedicion(MedicionResultado resultado) {
        var nuevaMedicion = new MedicionHistorial(resultado);
        _historialMedicion.add(nuevaMedicion);
        _medicion = resultado;
        mostrarMedicion(nuevaMedicion);
        _observerCreido.chequearPremio(this);
        cumplioObjetivo();
    }

    public boolean cumplioObjetivo() {
        if (_objetivo.getEstrategia() == null){
            return false;
        }

        return _objetivo.getEstrategia().cumplioObjetivo();
    }

    public MedicionResultado getMedicion() {
        return this._medicion;
    }

    public int edad() {
        return Math.toIntExact(ChronoUnit.YEARS.between(_fechaNacimiento, LocalDate.now()));
    }


    public String toString() {
        return "Socio " + _nombre + " " + _apellido + ". DNI n??mero " + _dni + ".";
    }

    public int getDNI() {
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

    public void elegirObjetivo(String elegido) {
        this.cambiarObjetivo(elegido);
        this.getObjetivo().crearRutina(this._diasEntrenamiento);
    }

    public void elegirDiasEntrenamiento(String dias) {

        this.setDiasEntrenamiento(dias.split(","));

        System.out.println();
        System.out.print("D??as elegidos: ");
        for (String dia : this._diasEntrenamiento) {
            if (dia.equals("LU")) {
                System.out.print("Lunes, ");
            } else if (dia.equals("MA")) {
                System.out.print("Martes, ");
            } else if (dia.equals("MI")) {
                System.out.print("Mi??rcoles, ");
            } else if (dia.equals("JU")) {
                System.out.print("Jueves, ");
            } else if (dia.equals("VI")) {
                System.out.print("Viernes, ");
            } else if (dia.equals("SA")) {
                System.out.print("S??bado, ");
            } else if (dia.equals("DO")) {
                System.out.print("Domingo, ");
            }
        }
        if(_objetivo.getRutina() != null) {
            _objetivo.crearRutina(this._diasEntrenamiento);
        }
        System.out.println();
    }


    public String getDiaHoy() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String hoy = "";
        if (dayOfWeek == 1) {
            hoy = "DO";
        } else if (dayOfWeek == 2) {
            hoy = "LU";
        } else if (dayOfWeek == 3) {
            hoy = "MA";
        } else if (dayOfWeek == 4) {
            hoy = "MI";
        } else if (dayOfWeek == 5) {
            hoy = "JU";
        } else if (dayOfWeek == 6) {
            hoy = "VI";
        } else if (dayOfWeek == 7) {
            hoy = "SA";
        }
        return hoy;
    }
    // MOVER hayDiaEntrenamiento() A CLASE DE UTILS

    public boolean hayDiaEntrenamiento() {
        String hoy = getDiaHoy();
        boolean diaEntrenamiento = false;
        for (String dia : this._diasEntrenamiento) {
            if (dia.equals(hoy)) {
                diaEntrenamiento = true;
            }
        }
        return diaEntrenamiento;
    }

    public ArrayList<EjercicioRutina> getEjerciciosDelDia() {
        ArrayList<EjercicioRutina> ejercicios = new ArrayList<>();
        String hoy = getDiaHoy();
        Rutina rutina = _objetivo.getRutina();
        ArrayList<DiaEjercicio> rutinaEjercicios = rutina.getDiaEjercicios();
        for (DiaEjercicio ejercicio : rutinaEjercicios) {
            if (ejercicio.getDia().equals(hoy)) {
                if(!ejercicio.realizado()) {
                    ejercicio.setRealizado();
                    DiaEjercicio diaActual = new DiaEjercicio(ejercicio.getDia(), rutina.getId());
                    for (EjercicioRutina ejercicioRutina : ejercicio.getEjerciciosRutina()) {
                        ejercicios.add(ejercicioRutina);

                    }
                }
            }
        }
        return ejercicios;
    }

    public void mostrarMedicion(MedicionHistorial medicion) {
        String pattern = "yyyy-dd-MM HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        System.out.printf("El dia : %s, tu medicion fue: \n", simpleDateFormat.format( medicion.getFecha()));
        System.out.printf("Peso: %d \n", medicion.getMedicion().getPeso());
        System.out.printf("Grasa Corporal: %d \n", medicion.getMedicion().getgrasaCorporal());
        System.out.printf("Masa Muscular: %d \n", medicion.getMedicion().getmasaMuscular());
        System.out.println();
    }

    public ArrayList<DiaEjercicio> getHistorialEjercicios() {
        return this._historialEjercicios;
    }

    public Rutina getRutina(){
       return this.getObjetivo().getRutina();
    }

    public void getProgreso() {
        if(_historialEjercicios.size() == 0){
            System.out.println();
            System.out.println("A??n no realizaste ejercicios.");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Ejercicios realizados: ");
            for (DiaEjercicio ejercicio : _historialEjercicios) {
                for (EjercicioRutina ejercicioRutina : ejercicio.getEjerciciosRutina()) {
                    ejercicioRutina.mostrarDatos();
                }
            }
            System.out.println();
        }
        for (MedicionHistorial historico: _historialMedicion){
            mostrarMedicion(historico);
        }
    }

    public ArrayList<Trofeo> getTrofeos(){
        return this._trofeos;
    }

    public void mostrarTrofeos() {
        if (this._trofeos.size() > 0) {
            System.out.println();
            System.out.println("Obtuviste los siguientes trofeos: ");
            for (Trofeo trofeo : this._trofeos) {
                System.out.print("* ");
                System.out.println(trofeo.getNombre());
            }
        } else {
            System.out.println("A??n no tiene ning??n trofeo");
        }
        System.out.println();
    }



    public void reforzarRutina() {
        this.getObjetivo().reforzarRutina();
    }

    public ArrayList<MedicionHistorial> getHistorialMedicion() {
        return _historialMedicion;
    }

    public boolean recibirTrofeo(Trofeo trofeo){
        if (this._trofeos.size() == 0){
            this._trofeos.add(trofeo);
            return true;
        }

        for (Trofeo t: this._trofeos){
            if (t.getNombre().equals(trofeo.getNombre())){
                return false;
            }
        }

        this._trofeos.add(trofeo);
        return true;
    }

    public void agregarDiaDeEntrenamiento(DiaEjercicio diaEjercicio) {
        _historialEjercicios.add(diaEjercicio);
        _observerConstancia.chequearPremio(this);
    }

    public int getIdRutina() {
        return _objetivo.getRutina().getId();
    }
}
