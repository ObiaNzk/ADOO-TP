package Clases.medicion;

public class medicionResultado {
    private int _masaMuscular;
    private int _grasaCorporal;

    private int _peso;

    public int getmasaMuscular() {
        return this._masaMuscular;

    }

    public int getgrasaCorporal() {
        return this._grasaCorporal;
    }

    public void setmasaMuscular(int masa) {
        this._masaMuscular = masa;
    }

    public void setgrasaCorporal(int grasa) {
        this._grasaCorporal = grasa;
    }

    public void setPeso(int peso) {
        this._peso = peso;
    }

    public int getPeso() {
        return this._peso;
    }
}

