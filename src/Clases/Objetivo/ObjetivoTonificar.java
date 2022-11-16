package Clases.Objetivo;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;
import Clases.Socio;
import Clases.Medicion.Medicion;
import Clases.Medicion.MedicionAdapter;
import Clases.Medicion.MedicionResultado;
import Enums.Exigencia;
import Enums.TipoMuscular;
import ListaEjercicios.Ejercicio;
import Trofeos.TrofeoDedicacion;

import java.util.ArrayList;
import java.util.Scanner;

public class ObjetivoTonificar extends ObjetivoStrategy {
    // dias :  2 horas hasta 2 horas y 30 minutos
    private final int _duracionMinima = 120;
    // ejercicios: aerobico entre <= 4 y ejercitacion muscular fuerte
    private final int _duracionMaxima = 150;

    private TrofeoDedicacion _observer = new TrofeoDedicacion();

    private final Socio _socio;

    private final MedicionResultado _medicionIdeal;

    private final MedicionAdapter _medidor = new Medicion();

    public ObjetivoTonificar(Socio socio) {
        this._socio = socio;
        this._medicionIdeal  = this._medidor.medir(socio.getAltura(), socio.getSexo());
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
                        EjercicioRutina ejercicioRutina = new EjercicioRutina(ejer, 2, 20, 8);
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
        var medicionActual = this._socio.getMedicion();


        if ((medicionActual.getmasaMuscular() != this._medicionIdeal.getmasaMuscular())||(medicionActual.getgrasaCorporal() != this._medicionIdeal.getgrasaCorporal())){
            return false;
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("Felicidades, cumpliste tu objetivo de Tonificar :).");
        _observer.chequearPremio(_socio);

        System.out.println("Si queres cambiar tu objetivo a 'Mantener Figura' elegí la opción 2, y luego la opción 3.");

        return true;

    }

}