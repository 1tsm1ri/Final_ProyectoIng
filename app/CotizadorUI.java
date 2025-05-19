package app;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import logica.*;

public class CotizadorUI {
    // Declarar ATRIBUTO para calcular comisiones
    private CalculadoraComisiones calc;
    // Declarar ATRIBUTO para gestionar la cotizacion de dibujos
    private GestorDeCotizacion gestorCotizacion;

    // Constructor que recibe la calculadora y gestor, asignarlos a atributos
    // Llamar a crear y mostrar la interfaz grafica
    public CotizadorUI(CalculadoraComisiones calc, GestorDeCotizacion gestorCotizacion) {
        this.calc = calc;
        this.gestorCotizacion = gestorCotizacion;
        crearYMostrarGUI();
    }

    // METODO para crear y mostrar la interfaz gráfica
    private void crearYMostrarGUI() {
        // Crear ventana JFrame con título
        JFrame frame = new JFrame("Cotizador de Comisiones");
        // Definir operacion para cerrar ventana al salir
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Definir tamaño de ventana
        frame.setSize(600, 500);
        // Establecer color de fondo de la ventana
        frame.getContentPane().setBackground(new Color(0x2f292b));

        // Configurar colores y estilos para diálogos y botones
        UIManager.put("OptionPane.background", new Color(0x2f292b));
        UIManager.put("Panel.background", new Color(0x2f292b));
        UIManager.put("OptionPane.buttonBackground", new Color(0x80354A));
        UIManager.put("OptionPane.buttonForeground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(0x80354A));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.focus", new Color(0x80354A));

        // Crear panel para titulo con layout BorderLayout
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(0x2f292b));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Crear etiqueta para titulo, configurar fuente y color
        JLabel titulo = new JLabel("COTIZADOR DE COMISIONES", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        // Agregar titulo al panel en la posicion central
        panelTitulo.add(titulo, BorderLayout.CENTER);
        // Agregar panel del titulo a la ventana arriba
        frame.add(panelTitulo, BorderLayout.NORTH);

        // Crear panel central para seleccion y botones con BoxLayout vertical
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(0x2f292b));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear panel para seleccion con GridLayout 2x2 con espacio entre componentes
        JPanel panelSeleccion = new JPanel(new GridLayout(2, 2, 10, 10));
        panelSeleccion.setBackground(new Color(0x2f292b));
        // Crear etiquetas para las opciones "Tipo de producto" y "Complejidad"
        JLabel labelTipo = new JLabel("Tipo de producto:");
        JLabel labelComp = new JLabel("Complejidad:");
        labelTipo.setForeground(Color.WHITE);
        labelComp.setForeground(Color.WHITE);

        // Crear arreglos con las opciones para los combos
        String[] tipos = {"Seleccionar opcion", "Emote Estatico", "Emote Animado", "Badge"};
        String[] complejidades = {"Seleccionar opcion", "Normal", "Media", "Alta"};

        // Crear comboBox para tipo y complejidad con las opciones
        JComboBox<String> comboTipo = new JComboBox<>(tipos);
        JComboBox<String> comboComp = new JComboBox<>(complejidades);

        // Definir tamaño y colores para los combos
        Dimension comboSize = new Dimension(150, 28);
        Color fondoCombo = new Color(0x4a4547);
        comboTipo.setPreferredSize(comboSize);
        comboComp.setPreferredSize(comboSize);
        comboTipo.setBackground(fondoCombo);
        comboTipo.setForeground(Color.WHITE);
        comboComp.setBackground(fondoCombo);
        comboComp.setForeground(Color.WHITE);

        // Agregar etiquetas y combos al panel de seleccion
        panelSeleccion.add(labelTipo);
        panelSeleccion.add(comboTipo);
        panelSeleccion.add(labelComp);
        panelSeleccion.add(comboComp);

        // Agregar panel seleccion al panel central
        panelCentro.add(panelSeleccion);
        // Agregar espacio vertical entre paneles
        panelCentro.add(Box.createVerticalStrut(15));

        // Creacion del panel con botones, distribucion horizontal
        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 10));
        panelBotones.setBackground(new Color(0x2f292b));

        // Crear botones personalizados para agregar, eliminar, mostrar y ver resumen
        JButton btnAgregar = new BotonPersonalizado("Agregar", null);
        JButton btnEliminar = new BotonPersonalizado("Eliminar", null);
        JButton btnMostrar = new BotonPersonalizado("Visualizar", null);
        JButton btnResumen = new BotonPersonalizado("Ver Total", null);

        // Definir tamaño fijo para los botones
        Dimension botonSize = new Dimension(120, 30);
        btnAgregar.setPreferredSize(botonSize);
        btnEliminar.setPreferredSize(botonSize);
        btnMostrar.setPreferredSize(botonSize);
        btnResumen.setPreferredSize(botonSize);
        btnAgregar.setMaximumSize(botonSize);
        btnEliminar.setMaximumSize(botonSize);
        btnMostrar.setMaximumSize(botonSize);
        btnResumen.setMaximumSize(botonSize);

        // Agregar botones al panel de botones
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnResumen);

        // Agregar panel de botones al panel central
        panelCentro.add(panelBotones);

        // Crear area de texto para mostrar salida de texto (tipo consola, no es editable)
        JTextArea areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setBackground(new Color(0x1f1b1d));
        areaSalida.setForeground(Color.WHITE);
        areaSalida.setFont(new Font("Consolas", Font.PLAIN, 13));
        // Agregar scroll al área de texto
        JScrollPane scrollPane = new JScrollPane(areaSalida);
        scrollPane.setPreferredSize(new Dimension(580, 250));

        // Crear panel dividido vertical entre panel central y el area de texto
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelCentro, scrollPane);
        splitPane.setDividerLocation(0.5); // Por defecto ocupa mitad y mitad
        splitPane.setResizeWeight(0.5);

        // Añadir el panel dividido al centro del frame
        frame.add(splitPane, BorderLayout.CENTER); // Ajuste proporcional al tamaño de ventana

        // Redirigir System.out a la consola gráfica
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                areaSalida.append(String.valueOf((char) b));
                areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
            }
        });
        System.setOut(printStream);

        // Accion del boton Agregar
        btnAgregar.addActionListener(e -> {
            // Validar que las opciones seleccionadas sean válidas
            if (validSelection(comboTipo, comboComp)) {
                // Obtener tipo seleccionado
                int tipo = comboTipo.getSelectedIndex();
                // Obtener complejidad seleccionada según índice
                String complejidad = switch (comboComp.getSelectedIndex()) {
                    case 2 -> "media";
                    case 3 -> "alta";
                    default -> "normal";
                };
                // Llamar al gestor para agregar dibujo con tipo y complejidad
                gestorCotizacion.agregarDibujo(tipo, complejidad);

                // Preguntar si desea agregar otro dibujo (se realiza una confirmacion)
                int r = JOptionPane.showConfirmDialog(null, "¿Deseas agregar otro dibujo?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.NO_OPTION) {
                    // Si no desea agregar mas dibujos, preguntar si desea ver resumen
                    int verResumen = JOptionPane.showConfirmDialog(null, "¿Deseas ver el resumen del cálculo de la comisión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (verResumen == JOptionPane.YES_OPTION) {
                        // Mostrar resumen de calculo en la salida
                        calc.mostrarResumen();
                    } else {
                        // Mostrar dialogo para cerrar programa si no desea ver resumen
                        mostrarCerrarPrograma();
                    }
                }
                // Reinicia la seleccion a la opcion por defecto
                comboTipo.setSelectedIndex(0);
                comboComp.setSelectedIndex(0);
            } else {
                // Mostrar mensaje de error si la selección es invalida
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una opcion valida.");
            }
            // Actualiza interfaz despues de agregar un dibujo, muestra la lista de dibujos actualizada
            ListarDibujos.listarProductos(calc);
        });

        // Accion del boton Eliminar
        btnEliminar.addActionListener(e -> {
            // Validar que haya dibujos para eliminar
            if (calc.getDibujos().size() > 0) {
                // Llamar al gestor para eliminar un dibujo
                gestorCotizacion.eliminarDibujo();
            } else {
                // Mostrar mensaje si no hay dibujos para eliminar
                JOptionPane.showMessageDialog(null, "No hay dibujos para eliminar");
            }
        });

        // Definir evento para boton Mostrar
        btnMostrar.addActionListener(e -> ListarDibujos.listarProductos(calc));

        // Definir evento para boton Resumen
        btnResumen.addActionListener(e -> {
            // Mostrar resumen de la cotizacion
            calc.mostrarResumen();
            // Pregunta al usuario si desea agregar mas dibujos
            int r = JOptionPane.showConfirmDialog(null, "¿Quieres agregar mas dibujos?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.NO_OPTION) {
                // Mostrar dialogo para cerrar programa si no desea agregar mas dibujos
                mostrarCerrarPrograma();
            }
        });

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar la ventana
        frame.setVisible(true);
    }

    // METODO para validar que las opciones seleccionadas en ambos JComboBox sean válidas 
    private boolean validSelection(JComboBox<String> comboTipo, JComboBox<String> comboComp) {
        return comboTipo.getSelectedIndex() > 0 && comboComp.getSelectedIndex() > 0;
    }

    // METODO para mostrar dialogo para confirmar cerrar programa y cerrar si confirma
    private void mostrarCerrarPrograma() {
        int r = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar el programa?", "Cerrar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
