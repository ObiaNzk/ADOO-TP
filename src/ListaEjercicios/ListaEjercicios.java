package ListaEjercicios;

import Enums.Exigencia;
import Enums.TipoMuscular;

import java.util.ArrayList;

public class ListaEjercicios {
    private static ArrayList<Ejercicio> listaEjercicios = new ArrayList<Ejercicio>();

    public static void agregarEjercicio(TipoMuscular tipoMuscular, String nombre, Exigencia exigencia, int nivelAerobico, String video, int duracion ){
        listaEjercicios.add(new Ejercicio(tipoMuscular, nombre, exigencia, nivelAerobico, video, duracion));
    }

    public static ArrayList<Ejercicio> getEjercicios(){
        return listaEjercicios;
    }

}
