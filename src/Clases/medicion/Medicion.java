package Clases.medicion;

import java.time.LocalDate;

interface medicionAdapter {
    medicionResultado medir(double altura , int peso , char sexo);
}
public class Medicion {
    private Double _peso;
    private Double _grasa;
    private Double _masa;
    private LocalDate _fecha;
    private medicionAdapter _medidor;

}

