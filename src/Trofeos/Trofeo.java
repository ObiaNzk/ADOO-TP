package Trofeos;

import Clases.Socio;
import NotificacionTrofeo.Notificador;

public abstract class Trofeo {
    private Notificador _notificador;
    private Socio _socio;
    private String _nombre;

    public void setNombre(String nombre){
        this._nombre = nombre;
    }

    public String getNombre(){
        return _nombre;
    }


}
