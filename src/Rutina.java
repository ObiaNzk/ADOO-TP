import java.util.Date;
import java.util.ArrayList;

public class Rutina {
    private Date _fechaInicio = new Date();
    private static double porcentajeRefuerzo =0.1;
    private ArrayList<DiaRutina> _dias;

    public Rutina(){
        
    }

    public void agregarDias(ArrayList<Integer> dias){
        _dias = new ArrayList<DiaRutina>();
        for(int dia: dias){
            _dias.add(new DiaRutina(dia));
        }
    }

    public void agregarEjercicio(Ejercicio ejercicio, int dia, int series, int repeticiones, double peso){

        for(DiaRutina _dia: _dias){
            if(_dia.getDia() == dia ){
                _dia.agregarEjercicio(ejercicio, series, repeticiones, peso);
            }
        }
    }


}
