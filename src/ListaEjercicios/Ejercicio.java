package ListaEjercicios;

import Enums.Exigencia;
import Enums.TipoMuscular;

public class Ejercicio {
    private TipoMuscular _tipoMuscular;
    private String _nombre;
    private Exigencia _exigencia;
    private int _nivelAerobico;
    private String _video;

    private int _duracion;

    public Ejercicio(TipoMuscular tipoMuscular, String nombre, Exigencia exigencia, int nivelAerobico, String video, int duracion){
        this._tipoMuscular = tipoMuscular;
        this._nombre = nombre;
        this._exigencia = exigencia;
        this._nivelAerobico = nivelAerobico;
        this._video = video;
        this._duracion = duracion;
    }

    public String getNombre() {
        return this._nombre;
    }

    public int getNivelAerobico() {
        return this._nivelAerobico;
    }

    public Exigencia getExigencia() {
        return this._exigencia;
    }

    public int getDuracion (){
        return this._duracion;
    }
}
