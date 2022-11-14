package Trofeos;

import Clases.MedicionHistorial;
import Clases.Socio;
import NotificacionTrofeo.Notificador;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;

public class TrofeoCreido extends Trofeo{
    private Notificador _notificador;
    private Socio _socio;

    public TrofeoCreido() {
        this.setNombre("Trofeo al Creído");
    }


    public static boolean chequearPremio(Socio socio){
        boolean cumplido = true;
        ArrayList<MedicionHistorial> historialMediciones = socio.getHistorialMedicion();

        int contadorMediciones = 0;

        for(MedicionHistorial medicion:historialMediciones){
            Date fecha = medicion.getFecha();
            int dias = Math.toIntExact(ChronoUnit.DAYS.between((Temporal) fecha, LocalDate.now()));
            System.out.println(dias);
            if(dias <= 31){
                contadorMediciones++;
            }

        }

        if(contadorMediciones < 3){
            cumplido = false;
        }


        return cumplido;
    }

    public void notificadoPor(Socio socio){
        _socio = socio;
    }
}
