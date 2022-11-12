package Clases.objetivo;

import Clases.*;
import Clases.medicion.medicion;
import Clases.medicion.medicionAdapter;
import Clases.medicion.medidorExterno;
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

    private final medicionAdapter _balanza = new medicion(new medidorExterno());

    private final Socio _socio;


    public ObjetivoPerderPeso(Socio socio){
        this._socio = socio;
    }

    public int calcularPesoIdeal(int peso, double altura, Sexo sexo) {
        var pesoIdeal =  this._balanza.medir(altura, sexo);

        this._pesoIdeal = pesoIdeal.getPeso();
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

                var ejercicioRutina = new EjercicioRutina(ejercicio, 2, 15, 8);
                ejerciciosElegidos.add(ejercicioRutina);
                duracionActual += ejercicio.getDuracion();
            }

            diasEjercicio.add(new DiaEjercicio(dia, ejerciciosElegidos));
        }
        return new Rutina(diasEjercicio);
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
