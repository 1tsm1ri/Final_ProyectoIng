package app;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.ActionListener;

// Creacion de la clase BotonPersonalizado, que tiene relacion de herencia con JButton para personalizarlo
public class BotonPersonalizado extends JButton {

    // Crear un boton personalizado con estilos y comportamiento propios
    public BotonPersonalizado(String texto, ActionListener evento) {
        super(texto); // Establecer el texto del boton

        // Configurar estilos generales del boton
        setFocusPainted(false); // Evitar el borde de enfoque
        setForeground(Color.WHITE); // Establecer color del texto
        setBackground(new Color(0x80354A)); // Establecer color de fondo
        setFont(new Font("Segoe UI", Font.BOLD, 14)); // Establecer fuente
        setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar sobre el boton
        setContentAreaFilled(false); // Evitar que se rellene el area por defecto
        setOpaque(false); // Permitir que el fondo sea transparente

        // Definir tamaño preferido y minimo del boton
        setPreferredSize(new Dimension(120, 20));
        setMinimumSize(new Dimension(120, 20));

        // Registrar el evento asociado al boton
        addActionListener(evento);
    }

    // Personalizar la apariencia interna del boton
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Dibujar fondo redondeado con el color de fondo actual
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // Dibujar el texto centrado dentro del boton
        FontMetrics fm = g2.getFontMetrics(getFont());
        String text = getText();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int x = (getWidth() - textWidth) / 2;
        int y = (getHeight() + textHeight) / 2 - 2;

        g2.setColor(Color.WHITE);
        g2.setFont(getFont());
        g2.drawString(text, x, y);

        g2.dispose();
    }

    // Dibujar el borde redondeado del boton
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground().darker());
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }
}