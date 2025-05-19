package logica;

import javax.swing.*;
import modelo.Badge;
import modelo.Dibujo;
import modelo.EmoteAnimado;
import modelo.EmoteEstatico;

public class GestorDeCotizacion {

    /* ATRIBUTO privado: instancia de la clase CalculadoraComisiones
    Se declara como private porque solo se debe acceder a esta desde dentro de la clase GestorDeCotizacion.
    Se mantiene como referencia a la calculadora que gestiona las cotizaciones */
    private CalculadoraComisiones calc;

    /* Constructor que recibe la instancia de la calculadora
    Este constructor permite inicializar el objeto GestorDeCotizacion con una instancia de CalculadoraComisiones,
    lo que permite delegar las acciones de la cotizacion a este gestor */
    public GestorDeCotizacion(CalculadoraComisiones calc) {
        this.calc = calc;
    }

    /* METODO publico para agregar dibujo a la cotizacion
    Este metodo recibe una opción y una complejidad, crea un objeto Dibujo según la opcion, y lo agrega a la calculadora.
    Utiliza un bloque try-catch para manejar posibles excepciones relacionadas con la creación del dibujo */
    public void agregarDibujo(int opcion, String complejidad) {
        Dibujo dibujo = null;

        try {
            // Dependiendo de la opción seleccionada, se crea un tipo específico de dibujo
            if (opcion == 1) {
                dibujo = new EmoteEstatico(complejidad);
            } else if (opcion == 2) {
                dibujo = new EmoteAnimado(complejidad);
            } else if (opcion == 3) {
                dibujo = new Badge(complejidad);
            }

            // Se agrega el dibujo creado a la calculadora
            calc.agregarDibujo(dibujo);

            // Mostrar mensaje con la información del dibujo agregado
            System.out.printf("- %s (Complejidad: %s) | Precio base: $%.2f\n", 
                dibujo.getNombre(), dibujo.getComplejidad(), dibujo.getPrecioBase());

        } catch (IllegalArgumentException e) {
            // Manejo de excepciones si el tipo de dibujo es inválido
            System.out.println("Error: " + e.getMessage() + " Intenta de nuevo.");
        }
    }

    /* METODO público para eliminar dibujo o reiniciar cotizacion
    Este método permite eliminar dibujos de la cotizacion o reiniciar la cotizacion desde cero 
    Utiliza ventanas emergentes de confirmación para la interacción con el usuario */
    public void eliminarDibujo() {
        // Validar si hay productos antes de continuar
        if (calc.getDibujos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay productos para eliminar.");
            return;
        }

        // Mostrar resumen de dibujos actuales antes de realizar cualquier acción
        System.out.println("PRODUCTOS ACTUALES:");
        calc.mostrarResumen();

        // Confirmar si se desea eliminar un dibujo
        int option = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar algún dibujo?", "Eliminar Dibujo", JOptionPane.YES_NO_OPTION);

        // Mientras se desee eliminar, se ejecuta el ciclo
        while (option == JOptionPane.YES_OPTION) {
            String[] options = {"Eliminar un dibujo", "Reiniciar la cotizacion", "Volver a cotizar sin eliminar nada"};
            int opcionEliminar = JOptionPane.showOptionDialog(null, "¿Qué prefieres hacer?", "Eliminar Dibujo", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (opcionEliminar == 0) {
                int total = calc.getDibujos().size();
                System.out.println("\nLista de dibujos:");
                for (int i = 0; i < total; i++) {
                    Dibujo d = calc.getDibujos().get(i);
                    System.out.printf("%d. %s (%s) - $%.2f\n", i + 1, d.getNombre(), d.getComplejidad(), d.calcularPrecioConComision());
                }

                int indiceAEliminar;
                while (true) {
                    try {
                        indiceAEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el numero del dibujo que deseas eliminar (1-" + total + "): "));
                        if (indiceAEliminar < 1 || indiceAEliminar > total) {
                            throw new NumberFormatException();
                        }
                        break;
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor ingresa un numero valido entre 1 y " + total + ".");
                    }
                }

                calc.getDibujos().remove(indiceAEliminar - 1);
                System.out.println("\nDibujo eliminado correctamente.");
                ListarDibujos.listarProductos(calc);  // Asegúrate de actualizar la interfaz

                if (calc.getDibujos().isEmpty()) {
                    System.out.println("\nYa no quedan dibujos.");
                    break;
                }

                option = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar otro dibujo?", "Eliminar Otro Dibujo", JOptionPane.YES_NO_OPTION);

            } else if (opcionEliminar == 1) {
                // Solo reiniciar la cotización si se elige esta opción y la confirmación es positiva
                int confirmacionReinicio = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas reiniciar la cotización?", "Reiniciar Cotización", JOptionPane.YES_NO_OPTION);
                if (confirmacionReinicio == JOptionPane.YES_OPTION) {
                    calc.reiniciarCotizacion();  // Usar reiniciarCotizacion en lugar de crear una nueva instancia
                    System.out.println("Cotizacion reiniciada.");
                    ListarDibujos.listarProductos(calc);  // Asegúrate de actualizar la interfaz
                    break;
                }
            } else if (opcionEliminar == 2) {
                System.out.println("\nVolviendo al menu de cotizacion sin eliminar nada.");
                break;
            }
        }

        // Mostrar resumen si quedan productos
        if (!calc.getDibujos().isEmpty()) {
            System.out.println("\nRESUMEN DE COTIZACION:");
            calc.mostrarResumen();
        } else {
            System.out.println("No hay productos para mostrar.");
        }
    }   
}