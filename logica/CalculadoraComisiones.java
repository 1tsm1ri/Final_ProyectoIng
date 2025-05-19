package logica;

import modelo.*;
import java.util.ArrayList;

public class CalculadoraComisiones {

    /* ATRIBUTO privado: lista de objetos de tipo Dibujo, representa los productos agregados */
    private final ArrayList<Dibujo> dibujos = new ArrayList<>();

    /* METODO publico para agregar un dibujo a la lista */
    public void agregarDibujo(Dibujo dibujo) {
        dibujos.add(dibujo);
        // Imprimir solo lo esencial
        System.out.println("\nProducto agregado: " + dibujo.getNombre());
    }

    /* METODO publico para reiniciar la cotizacion */
    public void reiniciarCotizacion() {
        dibujos.clear();  // Limpia la lista de dibujos
        System.out.println("\nProductos eliminados.");
    }

    /* METODO publico para obtener la lista completa de dibujos agregados */
    public ArrayList<Dibujo> getDibujos() {
        return dibujos;
    }

    /* METODO publico para calcular el total que se debe enviar */
    public double calculartotal(double subtotal) {
        double comisionPorcentaje = 0.054;  // Porcentaje de comision (5.4%)
        double comisionFija = 0.30;         // Comision fija ($0.30 USD)

        double total = (subtotal + comisionFija) / (1 - comisionPorcentaje);
        return total;
    }

    /* METODO publico para mostrar un resumen detallado de la cotizacion en consola */
    public void mostrarResumen() {
        if (dibujos.isEmpty()) {
            System.out.println("\nNo hay productos en la cotizacion.");
            return;
        }

        double subtotal = 0;
        System.out.println("\nLista de productos:");
        for (Dibujo dibujo : dibujos) {
            double base = dibujo.getPrecioBase();
            subtotal += base;
            System.out.printf("- %s (%s): $%.2f\n", dibujo.getNombre(), dibujo.getComplejidad(), base);
        }

        double total = calculartotal(subtotal);
        double comision = total - subtotal;

        subtotal = Math.round(subtotal * 100.0) / 100.0;
        total = Math.round(total * 100.0) / 100.0;
        comision = Math.round(comision * 100.0) / 100.0;

        // Mostrar el subtotal, el total con la comisión, y la comisión de PayPal
        System.out.printf("\nSubtotal: $%.2f\n", subtotal);
        System.out.printf("Total (con comision): $%.2f\n", total);
        System.out.printf("Comision de PayPal: $%.2f\n", comision);
    }
}
