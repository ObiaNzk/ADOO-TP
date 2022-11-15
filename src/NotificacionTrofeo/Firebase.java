package NotificacionTrofeo;

public class Firebase implements PushAdapter {
    private static Firebase _instance = null;
    @Override
    public void enviar(String mensaje) {
        System.out.println();
        System.out.println(mensaje);
    }

    public static Firebase getInstance(){
        if (_instance == null){
            _instance = new Firebase();
        }
        return _instance;
    }
}
