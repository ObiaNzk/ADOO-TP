package tests;

import NotificacionTrofeo.Firebase;
import NotificacionTrofeo.Notificador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotificacionTest {
    @Test
    void notificacion_OK(){
        var notificador = new Notificador();

        notificador.enviar("pepito");
    }

    @Test
    void notificacion_GetSingleton(){
        var notificador1 = Firebase.getInstance();
        var notificador2 = Firebase.getInstance();

        Assertions.assertEquals(notificador1, notificador2);
    }
}
