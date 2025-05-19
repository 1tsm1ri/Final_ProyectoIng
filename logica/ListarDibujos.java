package logica;

import modelo.*;

public class ListarDibujos {

    /* METODO estático y público que recibe una instancia de CalculadoraComisiones, su función es imprimir todos los productos (dibujos) actualmente añadidos en la cotizacion */
    public static void listarProductos(CalculadoraComisiones calc) {
        if (calc.getDibujos().isEmpty()) {
            System.out.println("\nNo hay productos en la cotizacion.");
            return;
        }

        System.out.println("\nPRODUCTOS EN LA COTIZACION:");
        for (int i = 0; i < calc.getDibujos().size(); i++) {
            Dibujo d = calc.getDibujos().get(i);
            System.out.printf("%d. %s (%s) - $%.2f\n", i + 1, d.getNombre(), d.getComplejidad(), d.calcularPrecio());
        }
    }
}
