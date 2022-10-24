import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        Socio socio = new Socio("Facundo", "Gironte", 21, Sexo.HOMBRE);
        System.out.println(socio.toString());

        Socio socio2 = new Socio("Juan", "López", 21, Sexo.HOMBRE);
        System.out.println(socio2.toString());

        instanciarEjerciciosLista();

        for(Ejercicio ej: ListaEjercicios.getEjercicios()){
            System.out.println(ej.getNombre());
        }
    }

    public static void instanciarEjerciciosLista(){
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.MEDIO, 2, "url");
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.MEDIO, 2, "url");
    }

}
