package Login;

import Controllers.ControllerSocio;

import java.util.Scanner;

public class AdapterLogin implements IAdapterLogin{
    @Override
    public boolean login(String dniIngresado, String pswIngresada) {
        return true;
    }
}
