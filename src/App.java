import Clases.Socio;
import Controllers.ControllerSocio;
import Enums.Exigencia;
import Enums.Sexo;
import Enums.TipoMuscular;
import ListaEjercicios.ListaEjercicios;
import ListaEjercicios.Ejercicio;

public class App {
    public static void main(String[] args) throws Exception {

        ControllerSocio cs = ControllerSocio.getInstancia();

        cs.crearSocio("Facundo", "Gironte", 21, Sexo.HOMBRE);
        cs.crearSocio("Juan", "López", 21, Sexo.HOMBRE);

        cs.printSocios();

        //instanciarEjerciciosLista();


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
