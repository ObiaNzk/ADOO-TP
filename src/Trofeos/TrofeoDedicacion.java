package Trofeos;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Socio;
import NotificacionTrofeo.Notificador;

import java.util.ArrayList;

public class TrofeoDedicacion extends Trofeo{
    private Notificador _notificador;
    private Socio _socio;

    public static boolean chequearPremio(ArrayList<DiaEjercicio> rutina, ArrayList<EjercicioRutina> historial){
        for (EjercicioRutina ejercicio : historial) {
            ejercicio.mostrarDatos();
        }
        return true;
    }

    public void notificadoPor(Socio socio){
        this._socio = socio;
    }
}
