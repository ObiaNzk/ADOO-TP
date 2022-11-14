package Trofeos;

import Clases.Socio;
import NotificacionTrofeo.Notificador;

public class TrofeoDedicacion extends Trofeo{
    private Notificador _notificador;
    private Socio _socio;

    public TrofeoDedicacion() {
        this.setNombre("Trofeo a la Dedicación");
    }

    public static boolean chequearPremio(){
        boolean cumplido = true;
        // manu hace la logica de este premio
        return cumplido;
    }

    public void notificadoPor(Socio socio){

    }
}
