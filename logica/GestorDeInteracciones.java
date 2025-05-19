package logica;

import java.util.Scanner;

public class GestorDeInteracciones {

    private Scanner sc;
    public GestorDeInteracciones(Scanner sc) {
        this.sc = sc;
    }

    /* METODO público para mostrar el menú de opciones
    Este método permite mostrar un menú interactivo que varía según si es la primera vez que el usuario interactúa
    o si ya ha agregado dibujos previamente. Llama a ValidadorEntrada para asegurar que la opción seleccionada sea válida */
    public int mostrarMenu(boolean primeraVez) {
        System.out.println("\n¿Qué tipo de dibujo quieres agregar?");
        System.out.println("1. Emote Estatico");
        System.out.println("2. Emote Animado");
        System.out.println("3. Badge");

        // En la primera vez, ofrece la opción de no agregar un dibujo
        if (primeraVez) {
            System.out.println("4. Ninguno"); // Solo se muestra en la primera ejecucion
        } else {
            // Si ya se han agregado dibujos, ofrece opciones adicionales como eliminar dibujos y ver cotizacion (desde segunda ejecucion)
            System.out.println("4. Eliminar Dibujo");
            System.out.println("5. Listar Productos");
            System.out.println("6. Ver Cotizacion"); 
        }

        // Llama a ValidadorEntrada para obtener un valor válido entre 1 y 4 (o hasta 6 si no es la primera vez)
        return ValidadorEntrada.leerEntero(sc, 1, (primeraVez ? 4 : 6), "Elige una opción: ");
    }

    /* METODO público para obtener confirmación del usuario
    Este método solicita al usuario una respuesta de confirmación (si o no) usando el ValidadorEntrada,
    garantizando que se manejen respuestas válidas y evitando entradas incorrectas */
    public String obtenerConfirmacion(String mensaje) {
        return ValidadorEntrada.leerConfirmacion(sc, mensaje);
    }

    /* METODO público para elegir la complejidad del dibujo
    Este método presenta opciones de complejidad para el dibujo y valida la entrada del usuario
    Se aseguran respuestas válidas entre 1 y 3, correspondientes a las opciones "Normal", "Media" y "Alta" */
    public int elegirComplejidad() {
        System.out.println("\n¿Qué tan complejo es el dibujo que deseas?");
        System.out.println("1. Normal");
        System.out.println("2. Media");
        System.out.println("3. Alta");
        
        // Llama a ValidadorEntrada para obtener un valor válido entre 1 y 3
        return ValidadorEntrada.leerEntero(sc, 1, 3, "Elige una opción (1-3): ");
    }
}
