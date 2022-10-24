import java.util.ArrayList;

public class DiaRutina {
    private int _dia;
    private ArrayList<EjercicioRutina> _ejercicios = new ArrayList<EjercicioRutina>();

    public DiaRutina(int dia){
        this._dia = dia;
    }

    public void agregarEjercicio(Ejercicio ejercicio, int series, int repeticiones, double peso){

        EjercicioRutina ejercicioRutina = new EjercicioRutina(ejercicio, series, repeticiones, peso);
        _ejercicios.add(ejercicioRutina);
    }

    public int getDia() {
        return _dia;
    }
}
