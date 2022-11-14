package Clases.Objetivo;

import Clases.*;
import Clases.Medicion.Medicion;
import Clases.Medicion.MedicionAdapter;
import Clases.Medicion.MedidorExterno;
import Enums.Sexo;
import Enums.TipoMuscular;
import ListaEjercicios.Ejercicio;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ObjetivoPerderPeso extends ObjetivoStrategy {
    // dias : 1 hora hasta 1 hora y 30 minutos
    // ejercicios: aerobico mayor igual a 3 y ejercitacion muscular cualquiera
    private final int _duracionMaxima = 90;

    private int _pesoIdeal;

    private final MedicionAdapter _balanza = new Medicion(new MedidorExterno());

    private final Socio _socio;


    public ObjetivoPerderPeso(Socio socio){
        this._socio = socio;
    }

    public int calcularPesoIdeal(int peso, double altura, Sexo sexo) {
        var pesoIdeal =  this._balanza.medir(altura, sexo);

        this._pesoIdeal = pesoIdeal.getPeso();
        return this._pesoIdeal;
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
      var cumplio = _socio.getMedicion().getPeso() == this._pesoIdeal;
        if (!cumplio){
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
                    this._socio.getObjetivo().cambiarEstrategia(new ObjetivoMantener(_socio));
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
