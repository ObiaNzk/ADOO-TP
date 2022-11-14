package Trofeos;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import NotificacionTrofeo.Notificador;

import java.util.ArrayList;

public class TrofeoConstancia extends Trofeo{
    private Notificador _notificador;
    private Socio _socio;

    public TrofeoConstancia() {
        this.setNombre("Trofeo a la Constancia");
    }

    public static boolean chequearPremio(Rutina rutina, ArrayList<DiaEjercicio> historial){
        boolean cumplido = true;

        ArrayList<DiaEjercicio> diasProgramados = rutina.getDiaEjercicios();
        ArrayList<DiaEjercicio> diasRealizados = new ArrayList<>();

        for(DiaEjercicio dia : historial){
            if(dia.getIdRutina() == rutina.getId()){
                diasRealizados.add(dia);
            }
        }

        if(diasProgramados.size() != diasRealizados.size()){
            cumplido = false;
        } else{
            for(int i = 0; i < diasProgramados.size(); i++){

                ArrayList<EjercicioRutina> ejerciciosProgramados = diasProgramados.get(i).getEjercicios();
                ArrayList<EjercicioRutina> ejerciciosRealizados = diasRealizados.get(i).getEjercicios();

                if(ejerciciosProgramados.size() != ejerciciosRealizados.size()){
                    cumplido = false;
                    break;
                }
            }
        }

        return cumplido;
    }

    public void notificadoPor(Socio socio){
        _socio = socio;
    }

}
