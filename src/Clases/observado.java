package Clases;

import java.util.*;

public class observado {
    private List<IObservador> observadores;
    
    public observado() {
        observadores = new ArrayList<IObservador>();
    }
    
    public void SuscribirObservador(IObservador observador) {
        observadores.add(observador);
    }
    
    public void EliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }
    
    public void NotificarObservador(Socio socio) { }
}