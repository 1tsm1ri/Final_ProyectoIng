package app;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import logica.*;

public class Main {
    // Creacion de instancia de la clase CalculadoraComisiones
    private static CalculadoraComisiones calc = new CalculadoraComisiones();
    // Creamos un gestor de cotizacion y le pasamos la calculadora
    private static GestorDeCotizacion gestorCotizacion = new GestorDeCotizacion(calc);

    // METODO principal que lanza la aplicación
    public static void main(String[] args) {
        // SwingUtilities = ayuda a asegurarnos de que la interfaz se construya en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            // Creacion de ventana JFrame con título
            JFrame frame = new JFrame("Cotizador de Comisiones");
            // Estable que al cerrar la ventana se termine el programa
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Definir el tamaño de la ventana
            frame.setSize(600, 500);

            // Establece el color de fondo de toda la ventana
            frame.getContentPane().setBackground(new Color(0x2f292b));

            // Configurar los colores de las ventanas emergentes (JOptionPane)
            UIManager.put("OptionPane.background", new Color(0x2f292b));
            UIManager.put("Panel.background", new Color(0x2f292b));
            UIManager.put("OptionPane.buttonBackground", new Color(0x80354A));
            UIManager.put("OptionPane.buttonForeground", Color.WHITE);
            UIManager.put("OptionPane.messageForeground", Color.WHITE);

            // Personalizacion de la apariencia de los botones en los JOptionPane
            UIManager.put("Button.background", new Color(0x80354A));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.focus", new Color(0x80354A));

            // Creacion del panel para el titulo
            JPanel panelTitulo = new JPanel(new BorderLayout());
            panelTitulo.setBackground(new Color(0x2f292b));
            // Margenes al panel del titulo
            panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Creacion de la etiqueta del titulo centrado
            JLabel titulo = new JLabel("COTIZADOR DE COMISIONES", JLabel.CENTER);
            // Establecer fuente y color del texto
            titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
            titulo.setForeground(Color.WHITE);

            // Añadir el título al panel arriba en el frame
            panelTitulo.add(titulo, BorderLayout.CENTER);
            frame.add(panelTitulo, BorderLayout.NORTH);

            // Creacion del panel central
            JPanel panelCentro = new JPanel();
            panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
            panelCentro.setBackground(new Color(0x2f292b));
            panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Creacion del panel para seleccionar tipo y complejidad del producto
            JPanel panelSeleccion = new JPanel(new GridLayout(2, 2, 10, 10));
            panelSeleccion.setBackground(new Color(0x2f292b));

            // Creacion de etiquetas
            JLabel labelTipo = new JLabel("Tipo de producto:");
            JLabel labelComp = new JLabel("Complejidad:");
            labelTipo.setForeground(Color.WHITE);
            labelComp.setForeground(Color.WHITE);

            // Creacion de valores para los combos
            String[] tipos = {"Seleccionar opcion", "Emote Estatico", "Emote Animado", "Badge"};
            String[] complejidades = {"Seleccionar opcion", "Normal", "Media", "Alta"};

            // Creacion de los combos con las opciones
            JComboBox<String> comboTipo = new JComboBox<>(tipos);
            JComboBox<String> comboComp = new JComboBox<>(complejidades);

            // Ajustamos tamaño y colores de los combos
            Dimension comboSize = new Dimension(150, 28);
            Color fondoCombo = new Color(0x4a4547);
            Color textoBlanco = Color.WHITE;

            comboTipo.setPreferredSize(comboSize);
            comboComp.setPreferredSize(comboSize);
            comboTipo.setBackground(fondoCombo);
            comboTipo.setForeground(textoBlanco);
            comboComp.setBackground(fondoCombo);
            comboComp.setForeground(textoBlanco);

            // Añadir etiquetas y combos al panel de selección
            panelSeleccion.add(labelTipo);
            panelSeleccion.add(comboTipo);
            panelSeleccion.add(labelComp);
            panelSeleccion.add(comboComp);

            // Añadir el panel de selección al panel central
            panelCentro.add(panelSeleccion);
            // Agregar un espacio vertical entre secciones
            panelCentro.add(Box.createVerticalStrut(15));

            // Creacion del panel con botones, distribucion horizontal
            JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 10));
            panelBotones.setBackground(new Color(0x2f292b));

            // Creacion los botones personalizados
            JButton btnAgregar = new BotonPersonalizado("Agregar", null);
            JButton btnEliminar = new BotonPersonalizado("Eliminar", null);
            JButton btnMostrar = new BotonPersonalizado("Visualizar", null);
            JButton btnResumen = new BotonPersonalizado("Ver Total", null);

            // Ajustar tamaño de botones
            Dimension botonSize = new Dimension(120, 30);
            btnAgregar.setPreferredSize(botonSize);
            btnEliminar.setPreferredSize(botonSize);
            btnMostrar.setPreferredSize(botonSize);
            btnResumen.setPreferredSize(botonSize);

            // Limitar la altura máxima
            btnAgregar.setMaximumSize(botonSize);
            btnEliminar.setMaximumSize(botonSize);
            btnMostrar.setMaximumSize(botonSize);
            btnResumen.setMaximumSize(botonSize);

            // Añadir los botones al panel
            panelBotones.add(btnAgregar);
            panelBotones.add(btnEliminar);
            panelBotones.add(btnMostrar);
            panelBotones.add(btnResumen);

            // Añadir el panel de botones al panel central
            panelCentro.add(panelBotones);

            // Creacion un área de texto para simular la consola de salida
            JTextArea areaSalida = new JTextArea();
            areaSalida.setEditable(false);
            areaSalida.setBackground(new Color(0x1f1b1d));
            areaSalida.setForeground(Color.WHITE);
            areaSalida.setFont(new Font("Consolas", Font.PLAIN, 13));

            // Añadir scroll a la consola
            JScrollPane scrollPane = new JScrollPane(areaSalida);
            scrollPane.setPreferredSize(new Dimension(580, 250)); 

            // Creacion del panel dividido entre controles y consola
            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelCentro, scrollPane);
            splitPane.setDividerLocation(0.5); // Por defecto ocupa mitad y mitad
            splitPane.setResizeWeight(0.5); // Ajuste proporcional al tamaño de ventana

            // Añadir el panel dividido al centro del frame
            frame.add(splitPane, BorderLayout.CENTER);

            // Redirigir System.out a la consola gráfica
            PrintStream printStream = new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    areaSalida.append(String.valueOf((char) b));
                    areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
                }
            });
            System.setOut(printStream); // Esta línea redirige la salida estándar

            // Acción del botón Agregar
            btnAgregar.addActionListener(e -> {
                if (validSelection(comboTipo, comboComp)) {
                    int tipo = comboTipo.getSelectedIndex(); // Obtiene el tipo
                    String complejidad = switch (comboComp.getSelectedIndex()) {
                        case 2 -> "media";
                        case 3 -> "alta";
                        default -> "normal";
                    };

                    // Agrega el dibujo a la cotización
                    gestorCotizacion.agregarDibujo(tipo, complejidad);

                    // Pregunta si quiere seguir agregando
                    int r = JOptionPane.showConfirmDialog(null, "¿Deseas agregar otro dibujo?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (r == JOptionPane.NO_OPTION) {
                        int verResumen = JOptionPane.showConfirmDialog(null, "¿Deseas ver el resumen del cálculo de la comisión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                        if (verResumen == JOptionPane.YES_OPTION) {
                            calc.mostrarResumen(); // Muestra resumen si acepta
                        } else {
                            mostrarCerrarPrograma(); // Pregunta si desea cerrar
                        }
                    }

                    // Resetea la seleccion
                    comboTipo.setSelectedIndex(0);
                    comboComp.setSelectedIndex(0);
                } else {
                    // Muestra mensaje si no se selecciono bien
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una opcion valida.");
                }

                // Actualiza la interfaz después de agregar el dibujo
                ListarDibujos.listarProductos(calc);  // Esto muestra la lista de dibujos actualizada en la interfaz
            });

            // Acción del botón Eliminar
            btnEliminar.addActionListener(e -> {
                if (calc.getDibujos().size() > 0) {
                    gestorCotizacion.eliminarDibujo();
                } else {
                    JOptionPane.showMessageDialog(null, "No hay dibujos para eliminar.");
                }
            });

            // Acción del botón Mostrar
            btnMostrar.addActionListener(e -> ListarDibujos.listarProductos(calc));

            // Acción del botón Ver Total
            btnResumen.addActionListener(e -> {
                // Mostrar resumen sin reiniciar la cotización
                calc.mostrarResumen();  // Muestra el resumen de la cotización actual
            
                // Pregunta al usuario si quiere agregar más dibujos
                int r = JOptionPane.showConfirmDialog(null, "¿Quieres agregar más dibujos?", "Confirmar", JOptionPane.          YES_NO_OPTION);
                if (r == JOptionPane.NO_OPTION) {
                    mostrarCerrarPrograma(); // Si no, cierra el programa
                }
            });

            // Centrar la ventana en pantalla
            frame.setLocationRelativeTo(null);
            // Mostramos la ventana
            frame.setVisible(true);
        });
    }

    // Validacion de que se haya hecho una selección válida en ambos JComboBox
    private static boolean validSelection(JComboBox<String> comboTipo, JComboBox<String> comboComp) {
        return comboTipo.getSelectedIndex() > 0 && comboComp.getSelectedIndex() > 0;
    }

    // Pregunta al usuario si desea cerrar el programa
    private static void mostrarCerrarPrograma() {
        int r = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar el programa?", "Cerrar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
