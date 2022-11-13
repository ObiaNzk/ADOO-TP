import Controllers.ControllerSocio;
import Enums.Exigencia;
import Enums.Sexo;
import Enums.TipoMuscular;
import ListaEjercicios.ListaEjercicios;

public class App {
    public static void main(String[] args) throws Exception {
            instanciarEjerciciosLista();

            ControllerSocio cs = ControllerSocio.getInstancia();
            cs.crearSocio("Facundo", "Gironte", 1, "29/07/2001" , Sexo.HOMBRE);
            cs.crearSocio("Juan", "López",2, "15/06/1989", Sexo.HOMBRE);

            cs.getSocioByDNI("1").setPsw("contraseña");
            cs.getSocioByDNI("2").setPsw("contraseña");//comment

            cs.iniciarSesion();

            System.out.println("Logueado: " + cs.getLogueado().toString());
    }

    public static void instanciarEjerciciosLista(){
        // EJERCICIOS PERDER PESO
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.MEDIO, 5, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.MEDIO, 5, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.MEDIO, 5, "url",20);
        // EJERCICIOS MANTENER
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.BAJO, 2, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.BAJO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.BAJO, 2, "url",20);
        // EJERCICIOS TONIFICAR
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Biceps", Exigencia.ALTO, 2, "url", 20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.BRAZOS, "Triceps", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Sentadillas", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PIERNAS, "Estocadas", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Mariposa", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.PECHO, "Banco plano", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Polea alta", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.ESPALDA, "Pull face", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Press militar", Exigencia.ALTO, 2, "url",20);
        ListaEjercicios.agregarEjercicio(TipoMuscular.HOMBROS, "Vuelos laterales", Exigencia.ALTO, 2, "url",20);
    }

}
