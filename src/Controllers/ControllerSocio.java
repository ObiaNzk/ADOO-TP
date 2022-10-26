package Controllers;

import Clases.Socio;
import Enums.Sexo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ControllerSocio {
    private ArrayList<Socio> _socios = new ArrayList<Socio>();
    private static ControllerSocio _controllerSocio = null;

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

    public void crearSocio(String nombre, String apellido, String fecha, Sexo sexo ){

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNac = LocalDate.parse(fecha, fmt);

        _socios.add(new Socio(nombre, apellido, fechaNac, sexo));
    }

    public void printSocios(){
        for(Socio s: _socios){
            System.out.println(s.toString());
        }
    }

    public Socio getSocioByID(int id){
        Socio socio = null;
        for(Socio s: _socios){
            if(s.getId() ==  id){
                socio = s;
                break;
            }
        }
        return socio;
    }


}
