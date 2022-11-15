package Trofeos;

import Clases.MedicionHistorial;
import Clases.Socio;
import NotificacionTrofeo.Notificador;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TrofeoCreido extends Trofeo {
    private Notificador _notificador;
    private Socio _socio;

    public TrofeoCreido() {
        this.setNombre("Trofeo al Creído");
    }


    public static void chequearPremio(Socio socio) {
        var historialMediciones = socio.getHistorialMedicion();
        var contadorMediciones = 0;
        var mesActual = Calendar.getInstance().get(Calendar.MONTH);


        for (MedicionHistorial medicion : historialMediciones) {
            var cal = Calendar.getInstance();
            cal.setTime(medicion.getFecha());
            var mesMedicion = cal.get(Calendar.MONTH);
            if (mesActual == mesMedicion) {
                contadorMediciones++;
            }
        }

        if (contadorMediciones >= 3) {
            var trofeo = new TrofeoCreido();
            socio.recibirTrofeo(trofeo);
            new Notificador().enviar(MessageFormat.format("¡Felicitaciones!\nGanaste el {0}.", trofeo.getNombre()));
        }

    }
}
