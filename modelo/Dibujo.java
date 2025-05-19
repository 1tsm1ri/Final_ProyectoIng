package modelo;

// Clase abstracta Dibujo que sirve como base para otros tipos de dibujo
public abstract class Dibujo {

    // ATRIBUTO protegido accesible desde clases hijas
    protected String complejidad;

    // Constructor que recibe la complejidad del dibujo
    public Dibujo(String complejidad) {
        // Convierte la complejidad a minúsculas para facilitar validación
        this.complejidad = complejidad.toLowerCase();

        // Verifica si la complejidad es válida: solo puede ser "normal", "media" o "alta"
        if (!this.complejidad.equals("normal") && 
            !this.complejidad.equals("media") && 
            !this.complejidad.equals("alta")) {
            // Si no es válida, lanza una excepción con un mensaje de error
            throw new IllegalArgumentException("Complejidad invalida. Debe ser 'normal', 'media' o 'alta'.");
        }
    }

    // METODO abstracto que cada subclase debe implementar para definir su precio base
    public abstract double calcularPrecio();

    // METODO para obtener directamente el precio base (invoca calcularPrecio)
    public double getPrecioBase() {
        return calcularPrecio();
    }

    // METODO que puede ser sobrescrito para aplicar comisiones; por defecto devuelve solo el precio base
    public double calcularPrecioConComision() {
        return getPrecioBase();  // Actualmente no aplica comisión, pero permite extensión
    }

    // METODO abstracto para que cada tipo de dibujo retorne su nombre (Badge, EmoteEstatico, etc.)
    public abstract String getNombre();

    // METODO para acceder a la complejidad del dibujo
    public String getComplejidad() {
        return this.complejidad;
    }
}
