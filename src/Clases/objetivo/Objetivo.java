package Clases.objetivo;

import Clases.DiaEjercicio;
import Clases.EjercicioRutina;
import Clases.Rutina;

import java.util.ArrayList;

public class Objetivo {
    private ObjetivoStrategy _estrategia;
    private Rutina _rutina;

    public void cambiarEstrategia(ObjetivoStrategy estrategia){
        this._estrategia = estrategia;
    }

    public void crearRutina(String[] dias){
        this._rutina = this._estrategia.crearRutina(dias);
    }

    public Rutina getRutina(){
        return this._rutina;
    }

    public ObjetivoStrategy getEstrategia() {
        return _estrategia;
    }

    public void reforzarRutina() {
        var diasEjercicio = new ArrayList<DiaEjercicio>();
        for (DiaEjercicio ejercicio : _rutina.getDiaEjercicios()) {
            ArrayList<EjercicioRutina> ejerciciosElegidos = new ArrayList<EjercicioRutina>();
            var dia = ejercicio.getDia();
            System.out.println();
            System.out.println("[" + dia + "]");
            for (EjercicioRutina ejercicioRutina : ejercicio.getEjerciciosRutina()) {
                System.out.println();
                System.out.println("Ejercicio previo: ");
                ejercicioRutina.mostrarDatos();
                var ejercicioReforzado = new EjercicioRutina(
                        ejercicioRutina.getEjercicio(),
                        ejercicioRutina.getSeries() * 2,
                        ejercicioRutina.getRepeticiones() * 2,
                        ejercicioRutina.getPesoAsignado() * 2);
                ejerciciosElegidos.add(ejercicioReforzado);
                System.out.println();
                System.out.println("Ejercicio reforzado: ");
                ejercicioReforzado.mostrarDatos();
            }
            diasEjercicio.add(new DiaEjercicio(dia, ejerciciosElegidos));
        }
        this._rutina = new Rutina(diasEjercicio);
        System.out.println();
        System.out.println("[FIN]");
        System.out.println("Rutina reforzada exitosamente");
        System.out.println();
    }
}