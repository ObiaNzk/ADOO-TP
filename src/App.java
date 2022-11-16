import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import Controllers.ControllerSocio;
import Enums.Exigencia;
import Enums.Sexo;
import Enums.TipoMuscular;
import ListaEjercicios.ListaEjercicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class App {

    private static Socio _socioLogueado;
    private static Scanner scanner = new Scanner(System.in);
    private static ControllerSocio cs = ControllerSocio.getInstancia();

    public static void main(String[] args) throws Exception {

        instanciarEjerciciosLista();

        cs.crearSocio("Facundo", "Gironte", 1, "29/07/2001" , Sexo.HOMBRE);
        cs.crearSocio("Juan", "López",2, "15/06/1989", Sexo.HOMBRE);

        cs.getSocioByDNI(1).setPsw("contraseña");
        cs.getSocioByDNI(2).setPsw("contraseña");

        iniciarSesion();

        menu();
    }

    public static void iniciarSesion() {
        System.out.println();
        System.out.println("¡Bienvenido al ARNOLD FITNESS CENTER!\nIngrese sus credenciales para iniciar sesión.");
        System.out.println("DNI: ");
        String dniIngresado = scanner.nextLine();
        Socio s = cs.getSocioByDNI(Integer.parseInt(dniIngresado));

        while (s == null) {
            System.out.println("El dni ingreado no fue encontrado. Ingrese su dni: ");
            dniIngresado = scanner.nextLine();
            s = cs.getSocioByDNI(Integer.parseInt(dniIngresado));
        }

        System.out.println("Contraseña: ");
        String pswIngresada = scanner.nextLine();

        while (!pswIngresada.equals(s.getPsw())) {
            System.out.println("Contraseña incorrecta. Ingrese su contraseña: ");
            pswIngresada = scanner.nextLine();
        }

        if (cs.login(dniIngresado, pswIngresada)) {
            _socioLogueado = s;
        }
    }

    public static void menu() {
        if(_socioLogueado != null) {

            if (cs.obtenerRutina(_socioLogueado) == null) {
                String dias = inputElegirDiasEntrenamiento();
                cs.elegirDiasEntrenamiento(_socioLogueado, dias);

                String elegido = inputElegirObjetivo();
                cs.elegirObjetivo(_socioLogueado, elegido);
            }

            System.out.println("\nPara continuar, ingrese el número de la opción que corresponda.\n" +
                    "1. Comenzar entrenamiento del día\n" +
                    "2. Modificar objetivo\n" +
                    "3. Modificar días de entrenamiento\n" +
                    "4. Reforzar rutina\n" +
                    "5. Ver progreso\n" +
                    "6. Ver trofeos\n" +
                    "7. Pesar\n" +
                    "8. Salir");

            switch (scanner.nextLine()) {
                case "1":
                    if(cs.hayDiaEntrenamiento(_socioLogueado)){
                        ArrayList<EjercicioRutina> ejercicios = cs.getEjerciciosDelDia(_socioLogueado);

                        System.out.println("Ejercicios del día: ");
                        for(EjercicioRutina ejercicioRutina: ejercicios){
                            System.out.println("* " + ejercicioRutina.getNombre());
                        }

                        DiaEjercicio diaEjercicio = new DiaEjercicio(getDiaHoy(), cs.getIdRutina(_socioLogueado));

                        for(EjercicioRutina ejercicio: ejercicios){
                            ArrayList<String> valores = inputCargarEjercicio(ejercicio);
                            EjercicioRutina ejercicioRealizado = new EjercicioRutina(ejercicio.getEjercicio(), Integer.parseInt(valores.get(0)), Integer.parseInt(valores.get(1)), Integer.parseInt(valores.get(2)));
                            diaEjercicio.agregarEjercicio(ejercicioRealizado);
                        }


                        cs.cargarDiaEjercitacion(_socioLogueado, diaEjercicio);


                    } else {
                        System.out.println("Hoy no hay entrenamiento.");
                    }



                    menu();
                case "2":
                    String elegido = inputElegirObjetivo();
                    cs.elegirObjetivo(_socioLogueado, elegido);
                    menu();
                case "3":
                    String dias = inputElegirDiasEntrenamiento();
                    cs.elegirDiasEntrenamiento(_socioLogueado, dias);
                    menu();
                case "4":
                    cs.reforzarRutina(_socioLogueado);
                    menu();
                case "5":
                    cs.getProgreso(_socioLogueado);
                    menu();
                case "6":
                    cs.mostrarTrofeos(_socioLogueado);
                    menu();
                case "7":
                    cs.setMedicion(_socioLogueado);
                    menu();
                case "8":
                    _socioLogueado = null;
                    iniciarSesion();
                    menu();
                default:
                    System.out.println("La opción ingresada es incorrecta");
                    menu();
            }
        }else{
            iniciarSesion();
            menu();
        }
    }

    public static String inputElegirObjetivo() {
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
        return elegido;
    }

    public static String inputElegirDiasEntrenamiento() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elija qué días desea seleccionar como días de entrenamiento, separados por coma y sin espacios.\n LU, MA, MI, JU, VI, SA, DO. ");
        String dias = scanner.nextLine();

        return dias;
    }

    public static ArrayList<String> inputCargarEjercicio(EjercicioRutina ejercicio) {

        ArrayList<String> valores = new ArrayList<>();

        ejercicio.instrucciones();

        System.out.println("Cantidad de series realizadas:");
        String series = scanner.nextLine();
        System.out.println("Cantidad de repeticiones realizadas:");
        String repeticiones = scanner.nextLine();
        System.out.println("Peso levantado:");
        String peso = scanner.nextLine();

        valores.add(series);
        valores.add(repeticiones);
        valores.add(peso);

        return valores;
    }

    public static void instanciarEjerciciosLista(){
        // EJERCICIOS PERDER PESO
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.MEDIO, 5, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.MEDIO, 5, "url",20);
        // EJERCICIOS MANTENER
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.BAJO, 2, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.BAJO, 2, "url",20);
        // EJERCICIOS TONIFICAR
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.ALTO, 2, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.ALTO, 2, "url",20);
    }


    public static String getDiaHoy() {
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

}
