package modelo;

// Clase Badge que hereda de la clase abstracta Dibujo
public class Badge extends Dibujo {

    // Constructor que recibe la complejidad y la pasa al constructor de la clase padre Dibujo
    public Badge(String complejidad) {
        super(complejidad); // Llama al constructor de Dibujo con la complejidad recibida
    }

    // Implementación del METODO abstracto calcularPrecio de la clase Dibujo
    @Override
    public double calcularPrecio() {
        // Retorna el precio base del badge según la complejidad
        return switch (complejidad) {
            case "media" -> 10;
            case "alta" -> 12;
            default -> 8;
        };
    }

    // METODO que devuelve el nombre del tipo de dibujo
    @Override
    public String getNombre() {
        return "Badge";
    }
}
