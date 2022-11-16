package Trofeos;

import Clases.Socio;
import NotificacionTrofeo.Notificador;

import java.text.MessageFormat;

public class TrofeoDedicacion extends Trofeo {
    private Notificador _notificador;
    private Socio _socio;

    public TrofeoDedicacion() {
        this.setNombre("Trofeo a la Dedicación");
    }

    public void chequearPremio(Socio socio) {
        var trofeo = new TrofeoDedicacion();
        var recibio = socio.recibirTrofeo(trofeo);
        if (recibio) {
            new Notificador().enviar(MessageFormat.format("¡Felicitaciones!\nGanaste el {0}.", trofeo.getNombre()));
        }
    }
}