package modelo;

// Clase Badge que hereda de la clase abstracta Dibujo
public class EmoteEstatico extends Dibujo {

    // Constructor que recibe la complejidad y la pasa al constructor de la clase padre Dibujo
    public EmoteEstatico(String complejidad) {
        super(complejidad); // Llama al constructor de Dibujo con la complejidad recibida
    }

    // Implementación del METODO abstracto calcularPrecio de la clase Dibujo
    @Override
    public double calcularPrecio() {
        return switch (complejidad) {
            case "media" -> 12.5;
            case "alta" -> 15;
            default -> 10;
        };
    }

    // METODO que devuelve el nombre del tipo de dibujo
    @Override
    public String getNombre() {
        return "Emote Estatico";
    }
}