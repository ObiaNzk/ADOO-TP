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
        ArrayList<DiaEjercicio> diasEjercicio = new ArrayList<DiaEjercicio>();

        ArrayList<ArrayList<TipoMuscular>> separacion = separarGruposEnDias(dias);

        int i = 0;

        for (ArrayList<TipoMuscular> dia : separacion) {
            int duracionActual = 0;
            ArrayList<EjercicioRutina> ejerciciosElegidos = new ArrayList<EjercicioRutina>();
            for (TipoMuscular tipo : dia) {
                ArrayList<Ejercicio> ejerciciosDisponibles = elegirEjerciciosDisponibles(tipo);

                for (Ejercicio ejer : ejerciciosDisponibles) {
                    if(duracionActual<=_duracionMaxima+ejer.getDuracion()) {
                        EjercicioRutina ejercicioRutina = new EjercicioRutina(ejer, 2, 15, 8);
                        ejerciciosElegidos.add(ejercicioRutina);
                        duracionActual += ejer.getDuracion();
                    }
                }
            }
            DiaEjercicio diaEjercicio = new DiaEjercicio(dias[i], ejerciciosElegidos);
            diasEjercicio.add(diaEjercicio);
            i++;
            duracionActual = 0;
        }
        return new Rutina(diasEjercicio);
    }

    private ArrayList<ArrayList<TipoMuscular>> separarGruposEnDias(String[] dias) {
        TipoMuscular[] gruposMusculares = TipoMuscular.values();
        ArrayList<ArrayList<TipoMuscular>> matriz = new ArrayList<>();

        for(int i = 0; i < dias.length; i++){
            matriz.add(new ArrayList<TipoMuscular>());
        }

        int i = 0;
        for(TipoMuscular grupo: gruposMusculares){
            matriz.get(i).add(grupo);
            if(i < dias.length-1){
                i++;
            } else {
                i = 0;
            }
        }

        return matriz;
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
