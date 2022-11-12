package Clases;

import Clases.medicion.Medicion;
import Clases.objetivo.Objetivo;
import Clases.objetivo.ObjetivoMantener;
import Clases.objetivo.ObjetivoPerderPeso;
import Clases.objetivo.ObjetivoTonificar;
import Enums.Sexo;
import Trofeos.Trofeo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;

public class Socio {
    private int _dni;
    private String _psw;
    private String _nombre;
    private String _apellido;
    private LocalDate _fechaNacimiento;
    private Sexo _sexo;
    private Double _altura;
    private String[] _diasEntrenamiento;
    private Objetivo _objetivo;
    private ArrayList<Trofeo> _trofeos = new ArrayList<Trofeo>();
    private ArrayList<EjercicioRutina> _historialEjercicios = new ArrayList<>();
    private int _peso;


    public Socio(String nombre, String apellido, int dni, LocalDate fechaNacimiento, Sexo sexo) {
        this._dni = dni;
        this._nombre = nombre;
        this._apellido = apellido;
        this._fechaNacimiento = fechaNacimiento;
        this._sexo = sexo;
        this._objetivo = new Objetivo();
    }

    public void cambiarObjetivo(String objetivo) {
        if (objetivo.equals("1")) {
            System.out.println("Nuevo objetivo: Bajar de peso");
            System.out.println();
            _objetivo.cambiarEstrategia(new ObjetivoPerderPeso(this));
        } else if (objetivo.equals("2")) {
            System.out.println("Nuevo objetivo: Tonificar cuerpo");
            System.out.println();
            _objetivo.cambiarEstrategia(new ObjetivoTonificar(this));
        } else if (objetivo.equals("3")) {
            System.out.println("Nuevo objetivo: Mantener la figura");
            System.out.println();
            _objetivo.cambiarEstrategia(new ObjetivoMantener(this));
        } else {
            System.out.println("Opcion incorrecta.");
        }
    }

    public void setPeso(int peso) {
        this._peso = peso;
    }

    public int getPeso() {
        return this._peso;
    }

    public int edad() {
        return Math.toIntExact(ChronoUnit.YEARS.between(_fechaNacimiento, LocalDate.now()));
    }

    public void registrarMedicion() {

    }

    public Medicion getValoresIdeales() {
        Medicion medicion = null;
        return medicion;
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

    // MOVER getDiaHoy() A CLASE DE UTILS
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
        this.getObjetivo().crearRutina(this._diasEntrenamiento);
        boolean diaEntrenamiento = hayDiaEntrenamiento();
        if (diaEntrenamiento) {
            System.out.println("Ejercicios del día:");
            ArrayList<DiaEjercicio> rutina = this.getObjetivo().getRutina().getDiaEjercicios();
            for (DiaEjercicio ejercicio : rutina) {
                if (ejercicio.getDia().equals(hoy)) {
                    for (EjercicioRutina ejercicioRutina : ejercicio.getEjerciciosRutina()) {
                        System.out.print("* ");
                        System.out.println(ejercicioRutina.getNombre());
                    }
                }
            }
            this.cargarEjercicios();
        } else {
            System.out.println("Tu rutina no tiene ejercicios para hoy");
        }
    }

    public void cargarEjercicios() {
        ArrayList<DiaEjercicio> rutina = this.getObjetivo().getRutina().getDiaEjercicios();
        Scanner scanner = new Scanner(System.in);
        for (DiaEjercicio ejercicio : rutina) {
            for (EjercicioRutina ejercicioRutina : ejercicio.getEjerciciosRutina()) {
                ejercicioRutina.mostrarDatos();
                System.out.println("Cantidad de series realizadas:");
                String series = scanner.nextLine();
                System.out.println("Cantidad de repeticiones realizadas:");
                String repeticiones = scanner.nextLine();
                System.out.println("Peso levantado:");
                String peso = scanner.nextLine();
                EjercicioRutina ejercicioActual = new EjercicioRutina(ejercicioRutina.getEjercicio(),
                        Integer.parseInt(series), Integer.parseInt(repeticiones), Integer.parseInt(peso));
                this._historialEjercicios.add(ejercicioActual);
            }
        }
    }

    public void getProgeso() {
        System.out.println("comenzando entrenamiento...");
    }

    public void getTrofeos() {
        if (this._trofeos.size() > 0) {
            for (Trofeo trofeo : this._trofeos) {
                System.out.println(trofeo);
            }
        } else {
            System.out.println("Aún no tiene ningún trofeo");
        }
    }

    public boolean cumplioObjetivo() {
        return this.getObjetivo().getEstrategia().cumplioObjetivo();
    }

}
