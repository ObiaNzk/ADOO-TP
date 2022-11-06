package Controllers;

import Clases.Socio;
import Enums.Sexo;
import Login.AdapterLogin;
import Login.IAdapterLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ControllerSocio {
    private ArrayList<Socio> _socios = new ArrayList<Socio>();
    private static ControllerSocio _controllerSocio = null;
    private Socio _socioLogueado = null;
    private IAdapterLogin adapterLogin = new AdapterLogin();

    Scanner scanner = new Scanner(System.in);

    private ControllerSocio(){
    }

    public static ControllerSocio getInstancia(){
        if(_controllerSocio == null){
            return new ControllerSocio();
        }
        else {
            return _controllerSocio;
        }
    }

    public void crearSocio(String nombre, String apellido, int dni, String fecha, Sexo sexo ){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);

        _socios.add(new Socio(nombre, apellido, dni, fechaNac, sexo));
    }

    public void printSocios(){
        for(Socio s: _socios){
            System.out.println(s.toString());
        }
    }

    public Socio getSocioByDNI(int dni){
        Socio socio = null;
        for(Socio s: _socios){
            if(s.getDNI() ==  dni){
                socio = s;
                break;
            }
        }
        return socio;
    }


    public void bienvenida() {
        System.out.println("¡Bienvenido al ARNOLD FITNESS CENTER! \n1 para registrarse.\n2 para iniciar sesión.");
        String option = scanner.nextLine();

        while(!option.equals("1") && !option.equals("2")){
            System.out.println("Elija una de las opciones disponibles:\n1 para registrarse.\n2 para iniciar sesión.");
            option = scanner.nextLine();
        }

        if(option.equals("1")){
            registrar();
        } else if (option.equals("2")){
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        System.out.println("Ingrese su dni: ");
        String dniIngresado = scanner.nextLine();
        Socio s = getSocioByDNI(Integer.parseInt(dniIngresado));

        while(s == null){
            System.out.println("El dni ingreado no fue encontrado. Ingrese su dni: ");
            dniIngresado = scanner.nextLine();
            s = getSocioByDNI(Integer.parseInt(dniIngresado));
        }

        System.out.println("Ingrese su contraseña: ");
        String pswIngresada = scanner.nextLine();

        while(!pswIngresada.equals(s.getPsw())){
            System.out.println("Contraseña incorrecta. Ingrese su contraseña: ");
            pswIngresada = scanner.nextLine();
        }

        if(adapterLogin.login(dniIngresado, pswIngresada)){
            _socioLogueado = s;
        }

    }

    private void registrar() {
        // preguntar
    }


    public Socio getLogueado() {
        return this._socioLogueado;
    }

    public void menu() {
        if(_socioLogueado.getObjetivo() == null){
            elegirObjetivo();
        }
        System.out.println("¿Cómo le gustaría empezar? Ingrese el número de la opción que corresponda.\n" +
                "1. Comenzar entrenamiento del día\n" +
                "2. Modificar objetivo\n" +
                "3. Modificar días de entrenamiento\n" +
                "4. Ver progreso\n" +
                "5. Ver trofeos");
    }

    private void elegirObjetivo() {
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

        _socioLogueado.cambiarObjetivo(elegido);


    }
}
