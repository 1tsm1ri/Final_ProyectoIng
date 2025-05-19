package logica;

import java.util.Scanner; 

// Clase para validar entradas del usuario
public class ValidadorEntrada {

    /* METODO estatico para leer un entero dentro de un rango especifico
    Es 'static' porque no depende de ningun atributo de instancia */
    public static int leerEntero(Scanner sc, int min, int max, String mensaje) {
        int valor;
        while (true) { 
            System.out.print(mensaje); 
            if (sc.hasNextInt()) { 
                valor = sc.nextInt(); 
                sc.nextLine(); // Limpia el salto de linea pendiente del buffer
                if (valor >= min && valor <= max) { 
                    return valor;
                } else {
                    System.out.println("Opcion fuera de rango. Intenta de nuevo."); 
                }
            } else {
                System.out.println("Entrada invalida. Debes escribir un numero.");
                sc.nextLine(); // Limpia el buffer para evitar errores al volver a leer
            }
        }
    }

    // METODO estatico para leer una confirmacion (solo acepta 's' o 'n')
    public static String leerConfirmacion(Scanner sc, String mensaje) {
        String input; // Variable para almacenar la entrada
        while (true) {
            System.out.print(mensaje);
            input = sc.nextLine().trim().toLowerCase(); // Lee, elimina espacios y convierte a minuscula
            if (input.equals("s") || input.equals("n")) { // Acepta solo 's' o 'n'
                return input;
            } else {
                System.out.println("Entrada invalida. Escribe 's' para si o 'n' para no.");
            }
        }
    }

    // METODO estatico para leer una opcion valida dentro de un conjunto definido de strings
    public static String leerOpcionRango(Scanner sc, String[] opciones, String mensaje) {
        while (true) {
            System.out.print(mensaje); 
            String opcion = sc.nextLine().trim(); // Lee y elimina espacios
            for (String op : opciones) { // Recorre las opciones validas
                if (opcion.equals(op)) { 
                    return opcion; 
                }
            }
            System.out.println("Opcion no valida. Intenta de nuevo."); 
        }
    }
}
