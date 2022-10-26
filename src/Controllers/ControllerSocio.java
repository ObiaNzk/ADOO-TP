package Controllers;

import Clases.Socio;
import Enums.Sexo;

import java.util.ArrayList;

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

    public void crearSocio(String nombre, String apellido, int edad, Sexo sexo ){
        _socios.add(new Socio(nombre, apellido, edad, sexo));
    }

    public void printSocios(){
        for(Socio s: _socios){
            System.out.println(s.toString());
        }
    }


}
