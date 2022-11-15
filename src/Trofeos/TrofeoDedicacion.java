package Trofeos;

import Clases.Socio;
import NotificacionTrofeo.Notificador;

public class TrofeoDedicacion extends Trofeo {
    private Notificador _notificador;
    private Socio _socio;

    public TrofeoDedicacion() {
        this.setNombre("Trofeo a la Dedicación");
    }

    public static void chequearPremio(Socio socio) {
        if (socio.cumplioObjetivo()) {
            socio.recibirTrofeo(new TrofeoDedicacion());
        }
    }
}
