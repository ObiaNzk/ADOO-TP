package NotificacionTrofeo;

public class Notificador implements PushAdapter {
    private static PushAdapter _notificador = Firebase.getInstance();
   public void enviar(String mensaje){
        _notificador.enviar(mensaje);
   }
}
