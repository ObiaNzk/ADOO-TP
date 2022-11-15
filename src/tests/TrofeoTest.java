package tests;

import Clases.Medicion.MedicionResultado;
import Clases.Socio;
import Enums.Sexo;
import Trofeos.Trofeo;
import Trofeos.TrofeoConstancia;
import Trofeos.TrofeoCreido;
import Trofeos.TrofeoDedicacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class TrofeoTest {

    @Test
    void Trofeos_GetNombre_OK(){
        var trofeoConstancia = new TrofeoConstancia();
        Assertions.assertEquals("Trofeo a la Constancia",trofeoConstancia.getNombre());

        var trofeoCreido = new TrofeoCreido();
        Assertions.assertEquals("Trofeo al Creído",trofeoCreido.getNombre());

        var trofeoDedicacion = new TrofeoDedicacion();
        Assertions.assertEquals("Trofeo a la Dedicación",trofeoDedicacion.getNombre());
    }

    @Test
    void TrofeoAlCreido_OK(){
        var socio = new Socio("","",1, LocalDate.now(), Sexo.HOMBRE);

        Assertions.assertEquals(socio.getTrofeos().size(), 0);

        var medicion1 = new MedicionResultado();
        var medicion2 = new MedicionResultado();
        var medicion3 = new MedicionResultado();

        socio.setMedicion(medicion1);
        socio.setMedicion(medicion2);
        socio.setMedicion(medicion3);

        Assertions.assertEquals(socio.getTrofeos().size(), 1);
    }

    @Test
    void TrofeoAlaDedicacion_OK(){
        var socio = new Socio("","",1, LocalDate.now(), Sexo.HOMBRE);
        var medicion = new MedicionResultado();
        medicion.setPeso(10);

        socio.setMedicion(medicion);
        Assertions.assertEquals(socio.getTrofeos().size(), 0);

        socio.setDiasEntrenamiento(new String[]{"LU","MA"});
        socio.cambiarObjetivo("3");

        medicion = new MedicionResultado();
        medicion.setPeso(13);

        socio.setMedicion(medicion);

        Assertions.assertEquals(socio.getTrofeos().size(), 1);
    }

    @Test
    void TrofeoAlaConstancia_OK(){
        var socio = new Socio("","",1, LocalDate.now(), Sexo.HOMBRE);
        Assertions.assertEquals(socio.getTrofeos().size(), 0);

        socio.setDiasEntrenamiento(new String[]{"MA"});
        socio.cambiarObjetivo("3");

        socio.entrenar();

        Assertions.assertEquals(socio.getTrofeos().size(), 1);
    }
}
