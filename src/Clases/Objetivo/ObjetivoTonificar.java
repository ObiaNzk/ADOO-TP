package Clases.Objetivo;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import Clases.Medicion.medicion;
import Clases.Medicion.medicionAdapter;
import Clases.Medicion.medicionResultado;
import Clases.Medicion.medidorExterno;
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

    private final medicionResultado _medicionIdeal;

    private final medicionAdapter _medidor = new medicion(new medidorExterno());

    public ObjetivoTonificar(Socio socio) {
        this._socio = socio;
        this._medicionIdeal  = this._medidor.medir(socio.getAltura(), socio.getSexo());
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

                var ejercicioRutina = new EjercicioRutina(ejercicio, 2, 12, 4);
                ejerciciosElegidos.add(ejercicioRutina);
                duracionActual += ejercicio.getDuracion();
            }

            diasEjercicio.add(new DiaEjercicio(dia, ejerciciosElegidos));
        }
        return new Rutina(diasEjercicio);
    }

    public boolean cumplioObjetivo() {
        var medicionActual = this._socio.getMedicion();


        if ((medicionActual.getmasaMuscular() != this._medicionIdeal.getmasaMuscular())||(medicionActual.getgrasaCorporal() != this._medicionIdeal.getgrasaCorporal())){
            return false;
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("Felicidades, cumpliste tu objetivo de Tonificar :).");

        while (true) {
            System.out.println("Si queres cambiar tu objetivo a 'Mantener Figura' escribi 'SI', caso contrario escribi 'NO'.");
            String cambiarObjetivo = scanner.nextLine();

            switch (cambiarObjetivo) {
                case "SI":
                    System.out.println("Cambiando objetivo a 'Mantener Figura.");
                    this._socio.getObjetivo().cambiarEstrategia(new ObjetivoMantener(this._socio));
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