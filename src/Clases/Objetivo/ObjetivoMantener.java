package Clases.Objetivo;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import Enums.Exigencia;
import Enums.TipoMuscular;
import ListaEjercicios.Ejercicio;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

public class ObjetivoMantener extends ObjetivoStrategy {
    // dias :  45 min hasta 1 hora y 20 minutos
    // ejercicios: aerobico entre 2 y 4 y ejercitacion muscular medio o bajo

    private final int _duracionMinima = 45;
    private final int _duracionMaxima = 80;

    private final Socio _socio;

    private int _pesoInicial;

    private int _variacionObjetivo = 4;

    public ObjetivoMantener(Socio socio) {
        this._socio = socio;
        this._pesoInicial = socio.getMedicion().getPeso();
    }

    private int getVariacionObjetivo() {
        return this._variacionObjetivo;
    }

    private void setvariacionObjetivo(int valor) {
        this._variacionObjetivo = valor;
    }

    public TipoMuscular elegirGrupoMuscular() {
        TipoMuscular[] values = TipoMuscular.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }

    public ArrayList<Ejercicio> elegirEjerciciosDisponibles(TipoMuscular tipo) {
        ArrayList<Ejercicio> ejercicios = ListaEjercicios.ListaEjercicios.getEjercicios();
        ArrayList<Ejercicio> ejerciciosDisponibles = new ArrayList<Ejercicio>();
        for (Ejercicio ejercicio : ejercicios) {
            var nivelAerobico = ejercicio.getNivelAerobico();
            var exigencia = ejercicio.getExigencia();
            var tipoMuscular = ejercicio.getTipoMuscular();

            if ((nivelAerobico >= 2 && nivelAerobico <= 4) &&
                    (exigencia == Exigencia.MEDIO || exigencia == Exigencia.BAJO) &&
                    (tipoMuscular == tipo)) {
                ejerciciosDisponibles.add(ejercicio);
            }
        }
        return ejerciciosDisponibles;
    }

    public Rutina crearRutina(String[] dias) {

        var diasEjercicio = new ArrayList<DiaEjercicio>();
        //El for de debajo, genera la rutina de una semana, deberia hacerlo
        // 4 veces (para las 4 semanas) pero sino no podríamos testear bien.
        for (String dia : dias) {

            var duracionActual = 0;
            TipoMuscular tipo = elegirGrupoMuscular();
            ArrayList<Ejercicio> ejerciciosDisponibles = elegirEjerciciosDisponibles(tipo);
            ArrayList<EjercicioRutina> ejerciciosElegidos = new ArrayList<EjercicioRutina>();

            for (Ejercicio ejercicio : ejerciciosDisponibles) {

                if ((duracionActual + ejercicio.getDuracion()) > _duracionMaxima) {
                    break;
                }

                var ejercicioRutina = new EjercicioRutina(ejercicio, 2, 10, 5);
                ejerciciosElegidos.add(ejercicioRutina);
                duracionActual += ejercicio.getDuracion();
            }

            diasEjercicio.add(new DiaEjercicio(dia, ejerciciosElegidos));
        }
        return new Rutina(diasEjercicio);
    }


    public boolean cumplioObjetivo() {

        if (abs(this._socio.getMedicion().getPeso() - this._pesoInicial) > this._variacionObjetivo) {
            return false;
        } else {
            System.out.println("Felicidades, cumpliste tu objetivo de Mantener tu peso :).");
            return true;
        }

    }
}
