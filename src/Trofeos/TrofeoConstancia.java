package Trofeos;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import NotificacionTrofeo.Notificador;
import NotificacionTrofeo.PushAdapter;

import java.text.MessageFormat;
import java.util.ArrayList;

public class TrofeoConstancia extends Trofeo {
    private Socio _socio;

    public TrofeoConstancia() {
        this.setNombre("Trofeo a la Constancia");
    }

    public void chequearPremio(Socio socio) {
        var rutina = socio.getRutina();
        var historial = socio.getHistorialEjercicios();

        ArrayList<DiaEjercicio> diasProgramados = rutina.getDiaEjercicios();
        ArrayList<DiaEjercicio> diasRealizados = new ArrayList<>();

        for (DiaEjercicio dia : historial) {
            if (dia.getIdRutina() == rutina.getId()) {
                diasRealizados.add(dia);
            }
        }

        if (diasProgramados.size() != diasRealizados.size()) {
            return;
        }

        for (int i = 0; i < diasProgramados.size(); i++) {
            ArrayList<EjercicioRutina> ejerciciosProgramados = diasProgramados.get(i).getEjercicios();
            ArrayList<EjercicioRutina> ejerciciosRealizados = diasRealizados.get(i).getEjercicios();

            if (ejerciciosProgramados.size() != ejerciciosRealizados.size()) {
                return;
            }
        }

        var trofeo = new TrofeoConstancia();
        var recibio = socio.recibirTrofeo(trofeo);
        if (recibio) {
            new Notificador().enviar(MessageFormat.format("¡Felicitaciones!\nGanaste el {0}.", trofeo.getNombre()));
        }
    }

}
