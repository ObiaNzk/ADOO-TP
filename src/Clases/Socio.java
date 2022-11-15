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
import java.util.concurrent.ThreadLocalRandom;

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

        _medicion.setPeso(ThreadLocalRandom
                .current()
                .nextInt(50,100));
        _medicion.setGrasaCorporal(ThreadLocalRandom
                .current()
                .nextInt(8,20));
        _medicion.setMasaMuscular(ThreadLocalRandom
                .current()
                .nextInt(35,45));
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
        TrofeoCreido.chequearPremio(this);
        TrofeoDedicacion.chequearPremio(this);
    }

    public MedicionResultado getMedicion() {
        return this._medicion;
    }

    public int edad() {
        return Math.toIntExact(ChronoUnit.YEARS.between(_fechaNacimiento, LocalDate.now()));
    }


    public String toString() {
        return "Socio " + _nombre + " " + _apellido + ". DNI número " + _dni + ".";
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
        while (!elegido.equals("1") && !elegido.equals("2") && !elegido.equals("3")) {
            System.out.println("Ingrese un número de objetivo válido.");
            elegido = scanner.nextLine();
        }

        this.cambiarObjetivo(elegido);
        this.getObjetivo().crearRutina(this._diasEntrenamiento);
    }

    public void elegirDiasEntrenamiento() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija qué días desea seleccionar como días de entrenamiento, separados por coma y sin espacios.\n LU, MA, MI, JU, VI, SA, DO. ");
        String dias = scanner.nextLine();

        this.setDiasEntrenamiento(dias.split(","));

        System.out.println();
        System.out.print("Días elegidos: ");
        for (String dia : this._diasEntrenamiento) {
            if (dia.equals("LU")) {
                System.out.print("Lunes, ");
            } else if (dia.equals("MA")) {
                System.out.print("Martes, ");
            } else if (dia.equals("MI")) {
                System.out.print("Miércoles, ");
            } else if (dia.equals("JU")) {
                System.out.print("Jueves, ");
            } else if (dia.equals("VI")) {
                System.out.print("Viernes, ");
            } else if (dia.equals("SA")) {
                System.out.print("Sábado, ");
            } else if (dia.equals("DO")) {
                System.out.print("Domingo, ");
            }
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
        // this.getObjetivo().mostrarRutina(dayOfWeek);
        String hoy = getDiaHoy();
        boolean diaEntrenamiento = false;
        for (String dia : this._diasEntrenamiento) {
            if (dia.equals(hoy)) {
                diaEntrenamiento = true;
            }
        }
        return diaEntrenamiento;
    }
    public void entrenar() {
        String hoy = getDiaHoy();
        boolean diaEntrenamiento = hayDiaEntrenamiento();
        Rutina rutina = _objetivo.getRutina();
        if (diaEntrenamiento) {
            ArrayList<DiaEjercicio> diaEjercicios = rutina.getDiaEjercicios();
            for (DiaEjercicio diaEjercicio : diaEjercicios) {
                if (diaEjercicio.getDia().equals(hoy) && diaEjercicio.realizado() == false) {
                    System.out.println("Ejercicios del día:");
                    for (EjercicioRutina ejercicioRutina : diaEjercicio.getEjerciciosRutina()) {
                        System.out.println("* " + ejercicioRutina.getNombre());
                    }
                    cargarEjercicios();
                    diaEjercicio.setRealizado();
                } else{
                    if(diaEjercicio.getDia().equals(hoy)) {
                        System.out.println("Ejercicios del dia realizados.");
                    }
                }
            }
        } else {
            System.out.println("Tu rutina no tiene ejercicios para hoy.");
        }
    }

    public void cargarEjercicios() {
        String hoy = getDiaHoy();
        Rutina rutina = _objetivo.getRutina();
        ArrayList<DiaEjercicio> rutinaEjercicios = rutina.getDiaEjercicios();
        Scanner scanner = new Scanner(System.in);
        for (DiaEjercicio ejercicio : rutinaEjercicios) {
            if (ejercicio.getDia().equals(hoy)) {
                DiaEjercicio diaActual = new DiaEjercicio(ejercicio.getDia(), rutina.getId());
                for (EjercicioRutina ejercicioRutina : ejercicio.getEjerciciosRutina()) {
                    ejercicioRutina.instrucciones();
                    System.out.println("Cantidad de series realizadas:");
                    String series = scanner.nextLine();
                    System.out.println("Cantidad de repeticiones realizadas:");
                    String repeticiones = scanner.nextLine();
                    System.out.println("Peso levantado:");
                    String peso = scanner.nextLine();
                    EjercicioRutina ejercicioActual = new EjercicioRutina(ejercicioRutina.getEjercicio(),
                            Integer.parseInt(series), Integer.parseInt(repeticiones), Integer.parseInt(peso));
                    diaActual.agregarEjercicio(ejercicioActual);
                }
                _historialEjercicios.add(diaActual);
            }
        }
        TrofeoConstancia.chequearPremio(this);
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
            System.out.println("Aún no realizaste ejercicios.");
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
            System.out.println("Aún no tiene ningún trofeo");
        }
        System.out.println();
    }

    public boolean cumplioObjetivo() {
        if (this.getObjetivo().getEstrategia() == null){
            return false;
        }

        return this.getObjetivo().getEstrategia().cumplioObjetivo();
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
            if (!t.getNombre().equals(trofeo.getNombre())){
                this._trofeos.add(trofeo);
                return true;
            }
        }
        return false;
    }
}
