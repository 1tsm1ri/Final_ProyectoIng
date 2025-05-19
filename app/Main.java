package app;

import javax.swing.SwingUtilities;
import logica.*;

public class Main {
    // Declarar ATRIBUTO estatico 'calc' de tipo CalculadoraComisiones
    // Instanciar un objeto CalculadoraComisiones para calcular comisiones
    private static CalculadoraComisiones calc = new CalculadoraComisiones();

    // Declarar un ATRIBUTO estatico 'gestorCotizacion' de tipo GestorDeCotizacion
    // Instanciar un objeto GestorDeCotizacion pasando la calculadora de comisiones 'calc'
    // Gestionar la agregacion y eliminacion de dibujos y manejar la cotización con la calculadora
    private static GestorDeCotizacion gestorCotizacion = new GestorDeCotizacion(calc);

    public static void main(String[] args) {
        // Invocar el método 'invokeLater' de SwingUtilities para ejecutar el código de interfaz gráfica
        // Crear una nueva ventana CotizadorUI pasando la calculadora y el gestor de cotización
        // Mostrar la interfaz gráfica para evitar problemas
        SwingUtilities.invokeLater(() -> {
            new CotizadorUI(calc, gestorCotizacion);
        });
    }
}
