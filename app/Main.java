package app;

import javax.swing.SwingUtilities;
import logica.*;

public class Main {
    private static CalculadoraComisiones calc = new CalculadoraComisiones();
    private static GestorDeCotizacion gestorCotizacion = new GestorDeCotizacion(calc);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CotizadorUI(calc, gestorCotizacion);
        });
    }
}
