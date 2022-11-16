package Controllers;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import Clases.Medicion.Medicion;
import Clases.Medicion.MedicionAdapter;
import Enums.Sexo;
import Login.AdapterLogin;
import Login.IAdapterLogin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ControllerSocio {
    private ArrayList<Socio> _socios = new ArrayList<Socio>();
    private static ControllerSocio _controllerSocio = null;
    private IAdapterLogin adapterLogin = new AdapterLogin();

    private final MedicionAdapter _balanza = new Medicion();

    private ControllerSocio() {
    }

    public ArrayList<Socio> getSocios(){
        return this._socios;
    }

    public static ControllerSocio getInstancia() {
        if (_controllerSocio == null) {
            return new ControllerSocio();
        } else {
            return _controllerSocio;
        }
    }

    public void crearSocio(String nombre, String apellido, int dni, String fecha, Sexo sexo) {

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);

        _socios.add(new Socio(nombre, apellido, dni, fechaNac, sexo));
    }

    public void printSocios() {
        for (Socio s : _socios) {
            System.out.println(s.toString());
        }
    }

    public Socio getSocioByDNI(int dni) {
        Socio socio = null;
        for (Socio s : _socios) {
            if (s.getDNI() == dni) {
                socio = s;
                break;
            }
        }
        return socio;
    }

    public boolean login(String dni, String psw){
        return adapterLogin.login(dni, psw);
    }




    public Rutina obtenerRutina(Socio socioLogueado) {
        return socioLogueado.getObjetivo().getRutina();
    }

    public void elegirDiasEntrenamiento(Socio socioLogueado, String dias) {
        socioLogueado.elegirDiasEntrenamiento(dias);
    }

    public void elegirObjetivo(Socio socioLogueado, String elegido) {
        socioLogueado.elegirObjetivo(elegido);
    }

    public void reforzarRutina(Socio socioLogueado) {
        socioLogueado.reforzarRutina();
    }

    public void getProgreso(Socio socioLogueado) {
        socioLogueado.getProgreso();
    }


    public void mostrarTrofeos(Socio socioLogueado) {
        socioLogueado.mostrarTrofeos();
    }

    public void setMedicion(Socio socioLogueado) {
        socioLogueado.setMedicion(_balanza.medir(socioLogueado.getAltura(), socioLogueado.getSexo()));
    }

    public ArrayList<EjercicioRutina> getEjerciciosDelDia(Socio socioLogueado) {
        return socioLogueado.getEjerciciosDelDia();
    }

    public boolean hayDiaEntrenamiento(Socio socioLogueado) {
        return socioLogueado.hayDiaEntrenamiento();
    }

    public void cargarDiaEjercitacion(Socio socioLogueado, DiaEjercicio diaEjercicio) {
        socioLogueado.agregarDiaDeEntrenamiento(diaEjercicio);
    }

    public int getIdRutina(Socio socioLogueado) {
        return socioLogueado.getIdRutina();
    }
}
