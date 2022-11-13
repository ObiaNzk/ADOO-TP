package Clases.Medicion;

public class MedicionResultado {
    private int _masaMuscular;
    private int _grasaCorporal;

    private int _peso;

    public int getmasaMuscular() {
        return this._masaMuscular;

    }

    public int getgrasaCorporal() {
        return this._grasaCorporal;
    }

    public void setMasaMuscular(int masa) {
        this._masaMuscular = masa;
    }

    public void setGrasaCorporal(int grasa) {
        this._grasaCorporal = grasa;
    }

    public void setPeso(int peso) {
        this._peso = peso;
    }

    public int getPeso() {
        return this._peso;
    }
}

