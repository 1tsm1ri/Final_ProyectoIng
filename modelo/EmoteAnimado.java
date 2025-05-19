package modelo;

// Clase Badge que hereda de la clase abstracta Dibujo
public class EmoteAnimado extends Dibujo {

    // Constructor que recibe la complejidad y la pasa al constructor de la clase padre Dibujo
    public EmoteAnimado(String complejidad) {
        super(complejidad); // Llama al constructor de Dibujo con la complejidad recibida
    }

    // Implementación del METODO abstracto calcularPrecio de la clase Dibujo
    @Override
    public double calcularPrecio() {
        return switch (complejidad) {
            case "media" -> 17.5;
            case "alta" -> 20;
            default -> 15;
        };
    }

    // METODO que devuelve el nombre del tipo de dibujo
    @Override
    public String getNombre() {
        return "Emote Animado";
    }
}