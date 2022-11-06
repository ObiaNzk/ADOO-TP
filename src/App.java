import Clases.Socio;
import Controllers.ControllerSocio;
import Enums.Exigencia;
import Enums.Sexo;
import Enums.TipoMuscular;
import ListaEjercicios.ListaEjercicios;
import ListaEjercicios.Ejercicio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {

        //instanciarEjerciciosLista();
        //otras instancias basicas

        ControllerSocio cs = ControllerSocio.getInstancia();
        cs.crearSocio("Facundo", "Gironte", 1, "29/07/2001" , Sexo.HOMBRE);
        cs.crearSocio("Juan", "López",2, "15/06/1989", Sexo.HOMBRE);

        cs.getSocioByDNI(1).setPsw("contraseña");
        cs.getSocioByDNI(2).setPsw("contraseña");

        cs.bienvenida();

        System.out.println("Logueado: " + cs.getLogueado().toString());
    }

    public static void instanciarEjerciciosLista(){
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.MEDIO, 2, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.MEDIO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.MEDIO, 2, "url",20);
    }

}
