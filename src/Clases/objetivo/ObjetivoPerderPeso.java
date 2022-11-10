package Clases.objetivo;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Enums.TipoMuscular;
import ListaEjercicios.Ejercicio;

import java.util.ArrayList;
import java.util.Random;


interface _calculadoraPesoIdeal {
    int calcular(int peso, double altura, char sexo);
}

public class ObjetivoPerderPeso extends ObjetivoStrategy {
    // dias : 1 hora hasta 1 hora y 30 minutos
    // ejercicios: aerobico mayor igual a 3 y ejercitacion muscular cualquiera
    private final int _duracionMaxima = 90;

    private int _pesoIdeal;

    public void calcularPesoIdeal(int peso, double altura, char sexo, _calculadoraPesoIdeal calculadora){
        this._pesoIdeal = calculadora.calcular(peso, altura, sexo);
    }

    public int getPesoIdeal(){
        return this._pesoIdeal;
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
            var tipoMuscular = ejercicio.getTipoMuscular();

            if ((nivelAerobico >= 3) && (tipoMuscular == tipo)) {
                ejerciciosDisponibles.add(ejercicio);
            }
        }
        return ejerciciosDisponibles;
    }

    public Rutina crearRutina(String[] dias) {
        var diasEjercicio = new ArrayList<DiaEjercicio>();
        for (String dia : dias) {
            var duracionActual = 0;
            TipoMuscular tipo = elegirGrupoMuscular();
            ArrayList<Ejercicio> ejerciciosDisponibles = elegirEjerciciosDisponibles(tipo);
            ArrayList<EjercicioRutina> ejerciciosElegidos = new ArrayList<EjercicioRutina>();

            for (Ejercicio ejercicio : ejerciciosDisponibles) {
                if ((duracionActual + ejercicio.getDuracion()) > this._duracionMaxima) {
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
