package Clases.objetivo;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import Clases.objetivo.ObjetivoStrategy;
import Enums.Exigencia;
import Enums.TipoMuscular;
import ListaEjercicios.Ejercicio;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ObjetivoTonificar extends ObjetivoStrategy {
    // dias :  2 horas hasta 2 horas y 30 minutos
    private final int _duracionMinima = 120;
    // ejercicios: aerobico entre <= 4 y ejercitacion muscular fuerte
    private final int _duracionMaxima = 150;

    private final Socio _socio;

    public ObjetivoTonificar(Socio socio) {
        this._socio = socio;
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

            if ((nivelAerobico <= 4) && exigencia == Exigencia.ALTO && tipoMuscular == tipo) {
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

                if ((duracionActual + ejercicio.getDuracion()) > _duracionMaxima) {
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

    public boolean cumplioObjetivo() {
        var cumplio = _socio.getPeso() == this._pesoIdeal;
        if (!cumplio) {
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Felicidades, cumpliste tu objetivo de perder peso :).");

        while (true) {
            System.out.println("Si queres cambiar tu objetivo a 'Mantener Figura' escribi 'SI', caso contrario escribi 'NO'.");
            String cambiarObjetivo = scanner.nextLine();

            switch (cambiarObjetivo) {
                case "SI":
                    System.out.println("Cambiando objetivo a 'Mantener Figura.");
                    this._socio.getObjetivo().cambiarEstrategia(new ObjetivoMantener());
                    return true;
                case "NO":
                    System.out.println("Tu objetivo no fue modificado.");
                    return true;
                default:
                    System.out.println("Opción invalida, intente nuevamente");
            }
        }

    }

}