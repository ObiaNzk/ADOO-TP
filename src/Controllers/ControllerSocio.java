package Controllers;

import Clases.Socio;
import Clases.Medicion.medicion;
import Clases.Medicion.medicionAdapter;
import Clases.Medicion.medidorExterno;
import Enums.Sexo;
import Login.AdapterLogin;
import Login.IAdapterLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerSocio {
    private ArrayList<Socio> _socios = new ArrayList<Socio>();
    private static ControllerSocio _controllerSocio = null;
    private Socio _socioLogueado = null;
    private IAdapterLogin adapterLogin = new AdapterLogin();

    private final medicionAdapter _balanza = new medicion(new medidorExterno());

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
        System.out.println();
        System.out.println("¡Bienvenido al ARNOLD FITNESS CENTER!\nIngrese sus credenciales para iniciar sesión.");
        System.out.println("DNI: ");
        String dniIngresado = scanner.nextLine();
        Socio s = getSocioByDNI(Integer.parseInt(dniIngresado));

        while(s == null){
            System.out.println("El dni ingreado no fue encontrado. Ingrese su dni: ");
            dniIngresado = scanner.nextLine();
            s = getSocioByDNI(Integer.parseInt(dniIngresado));
        }

        System.out.println("Contraseña: ");
        String pswIngresada = scanner.nextLine();

        while(!pswIngresada.equals(s.getPsw())){
            System.out.println("Contraseña incorrecta. Ingrese su contraseña: ");
            pswIngresada = scanner.nextLine();
        }

        if(adapterLogin.login(dniIngresado, pswIngresada)){
            _socioLogueado = s;
        }

        menu();
    }

    private void registrar() {
        // preguntar
    }


    public Socio getLogueado() {
        return this._socioLogueado;
    }

    public void menu() {
        if(_socioLogueado.getObjetivo().getRutina() == null){
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
                break;
            case "2":
                _socioLogueado.elegirObjetivo();
                break;
            case "3":
                _socioLogueado.elegirDiasEntrenamiento();
                break;
            case "4":
                _socioLogueado.reforzarRutina();
                break;
            case "5":
                _socioLogueado.getProgeso();
                break;
            case "6":
                _socioLogueado.getTrofeos();
                break;
            case "7":
                // _socioLogueado.setMedicion(this._balanza.medir(_socioLogueado.getAltura(),_socioLogueado.getSexo()));
                break;
            case "8":
                return;
            default:
                System.out.println("La opción ingresada es incorrecta");
                menu();
        }
    }
}
