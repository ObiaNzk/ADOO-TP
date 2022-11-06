package Clases;

import Enums.Exigencia;
import ListaEjercicios.Ejercicio;

import java.util.ArrayList;

public class ObjetivoMantener extends ObjetivoStrategy {

    private final int _duracionMinima = 45;

    private final int _duracionMaxima = 80;

    public Rutina crearRutina(String[] dias) {
        // dias :  45 min hasta 1 hora y 20 minutos
        // ejercicios: aerobico entre 2 y 4 y ejercitacion muscular medio o bajo
        ArrayList<Ejercicio> ejercicios = ListaEjercicios.ListaEjercicios.getEjercicios();
        ArrayList<Ejercicio> ejerciciosDisponibles = new ArrayList<Ejercicio>();

        for (Ejercicio ejercicio : ejercicios) {
            var nivelAerobico = ejercicio.getNivelAerobico();
            var exigencia = ejercicio.getExigencia();

            if ((nivelAerobico >= 2 && nivelAerobico <= 4) && (exigencia == Exigencia.MEDIO || exigencia == Exigencia.BAJO)) {
                ejerciciosDisponibles.add(ejercicio);
            }
        }

        var diasEjercicio = new ArrayList<DiaEjercicio>();
        for (String dia : dias) {
            var duracionActual = 0;
            ArrayList<EjercicioRutina> ejerciciosElegidos = new ArrayList<EjercicioRutina>();

            for (Ejercicio ejercicio : ejerciciosDisponibles) {
                if (duracionActual > _duracionMaxima) {
                    break;
                }

                var ejercicioRutina = new EjercicioRutina(ejercicio, 4, 10, 15);

                ejerciciosElegidos.add(ejercicioRutina);
                duracionActual += ejercicio.getDuracion();
            }

            diasEjercicio.add(new DiaEjercicio(dia, ejerciciosElegidos));
        }
        return new Rutina(diasEjercicio);
    }
}
