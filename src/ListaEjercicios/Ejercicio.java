package ListaEjercicios;

import Enums.Exigencia;
import Enums.TipoMuscular;

public class Ejercicio {
    private TipoMuscular _tipoMuscular;
    private String _nombre;
    private Exigencia _exigencia;
    private int _nivelAerobico;
    private String _video;

    public Ejercicio(TipoMuscular tipoMuscular, String nombre, Exigencia exigencia, int nivelAerobico, String video){
        this._tipoMuscular = tipoMuscular;
        this._nombre = nombre;
        this._exigencia = exigencia;
        this._nivelAerobico = nivelAerobico;
        this._video = video;
    }

    public String getNombre() {
        return _nombre;
    }
}
