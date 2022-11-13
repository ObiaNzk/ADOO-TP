package Controllers;

import Clases.Socio;
import Clases.Medicion.Medicion;
import Clases.Medicion.MedicionAdapter;
import Clases.Medicion.MedidorExterno;
import Enums.Sexo;
import Login.AdapterLogin;
import Login.IAdapterLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerSocio {
    private ArrayList<Socio> _socios = new ArrayList<Socio>();
    private static ControllerSocio _controllerSocio = ControllerSocio.getInstancia();
    private Socio _socioLogueado = null;
    private IAdapterLogin adapterLogin = new AdapterLogin();

    private final MedicionAdapter _balanza = new Medicion(new MedidorExterno());

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

    public void iniciarSesion() {

        System.out.println("\n¡Bienvenido al ARNOLD FITNESS CENTER!\nIngrese sus credenciales para iniciar sesión.");
        System.out.println("DNI: ");
        String dniIngresado = scanner.nextLine();
        Socio s = getSocioByDNI(Integer.parseInt(dniIngresado));

        while (s == null) {
            System.out.println("El dni ingreado no fue encontrado. Ingrese su dni: ");
            dniIngresado = scanner.nextLine();
            s = getSocioByDNI(Integer.parseInt(dniIngresado));
        }

        System.out.println("Contraseña: ");
        String pswIngresada = scanner.nextLine();

        while (!pswIngresada.equals(s.getPsw())) {
            System.out.println("Contraseña incorrecta. Ingrese su contraseña: ");
            pswIngresada = scanner.nextLine();
        }

        if (adapterLogin.login(dniIngresado, pswIngresada)) {
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


        if(_socioLogueado != null) {
            if (_socioLogueado.getObjetivo().getRutina() == null) {
                _socioLogueado.elegirDiasEntrenamiento();
                _socioLogueado.elegirObjetivo();
            }

            System.out.println();
            System.out.println("Para continuar, ingrese el número de la opción que corresponda.\n" +
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
                    _socioLogueado.entrenar();
                    menu();
                case "2":
                    _socioLogueado.elegirObjetivo();
                    menu();
                case "3":
                    _socioLogueado.elegirDiasEntrenamiento();
                    menu();
                case "4":
                    _socioLogueado.reforzarRutina();
                    menu();
                case "5":
                    _socioLogueado.getProgeso();
                    menu();
                case "6":
                    _socioLogueado.getTrofeos();
                    menu();
                case "7":
                    // _socioLogueado.setMedicion(this._balanza.medir(_socioLogueado.getAltura(),_socioLogueado.getSexo()));
                    menu();
                case "8":
                    _socioLogueado = null;
                    menu();
                default:
                    System.out.println("La opción ingresada es incorrecta");
                    menu();
            }
        } else{
            _controllerSocio.iniciarSesion();
            menu();
        }
    }
}
