package app;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import logica.*;

public class CotizadorUI {
    private CalculadoraComisiones calc;
    private GestorDeCotizacion gestorCotizacion;

    public CotizadorUI(CalculadoraComisiones calc, GestorDeCotizacion gestorCotizacion) {
        this.calc = calc;
        this.gestorCotizacion = gestorCotizacion;
        crearYMostrarGUI();
    }

    private void crearYMostrarGUI() {
        JFrame frame = new JFrame("Cotizador de Comisiones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.getContentPane().setBackground(new Color(0x2f292b));

        UIManager.put("OptionPane.background", new Color(0x2f292b));
        UIManager.put("Panel.background", new Color(0x2f292b));
        UIManager.put("OptionPane.buttonBackground", new Color(0x80354A));
        UIManager.put("OptionPane.buttonForeground", Color.WHITE);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", new Color(0x80354A));
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.focus", new Color(0x80354A));

        // Panel título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        panelTitulo.setBackground(new Color(0x2f292b));
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel titulo = new JLabel("COTIZADOR DE COMISIONES", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        panelTitulo.add(titulo, BorderLayout.CENTER);
        frame.add(panelTitulo, BorderLayout.NORTH);

        // Panel central con selección y botones
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(0x2f292b));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel selección
        JPanel panelSeleccion = new JPanel(new GridLayout(2, 2, 10, 10));
        panelSeleccion.setBackground(new Color(0x2f292b));
        JLabel labelTipo = new JLabel("Tipo de producto:");
        JLabel labelComp = new JLabel("Complejidad:");
        labelTipo.setForeground(Color.WHITE);
        labelComp.setForeground(Color.WHITE);
        String[] tipos = {"Seleccionar opcion", "Emote Estatico", "Emote Animado", "Badge"};
        String[] complejidades = {"Seleccionar opcion", "Normal", "Media", "Alta"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);
        JComboBox<String> comboComp = new JComboBox<>(complejidades);
        Dimension comboSize = new Dimension(150, 28);
        Color fondoCombo = new Color(0x4a4547);
        comboTipo.setPreferredSize(comboSize);
        comboComp.setPreferredSize(comboSize);
        comboTipo.setBackground(fondoCombo);
        comboTipo.setForeground(Color.WHITE);
        comboComp.setBackground(fondoCombo);
        comboComp.setForeground(Color.WHITE);
        panelSeleccion.add(labelTipo);
        panelSeleccion.add(comboTipo);
        panelSeleccion.add(labelComp);
        panelSeleccion.add(comboComp);
        panelCentro.add(panelSeleccion);
        panelCentro.add(Box.createVerticalStrut(15));

        // Panel botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 4, 10, 10));
        panelBotones.setBackground(new Color(0x2f292b));
        JButton btnAgregar = new BotonPersonalizado("Agregar", null);
        JButton btnEliminar = new BotonPersonalizado("Eliminar", null);
        JButton btnMostrar = new BotonPersonalizado("Visualizar", null);
        JButton btnResumen = new BotonPersonalizado("Ver Total", null);
        Dimension botonSize = new Dimension(120, 30);
        btnAgregar.setPreferredSize(botonSize);
        btnEliminar.setPreferredSize(botonSize);
        btnMostrar.setPreferredSize(botonSize);
        btnResumen.setPreferredSize(botonSize);
        btnAgregar.setMaximumSize(botonSize);
        btnEliminar.setMaximumSize(botonSize);
        btnMostrar.setMaximumSize(botonSize);
        btnResumen.setMaximumSize(botonSize);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnResumen);
        panelCentro.add(panelBotones);

        // Área de texto salida
        JTextArea areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        areaSalida.setBackground(new Color(0x1f1b1d));
        areaSalida.setForeground(Color.WHITE);
        areaSalida.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(areaSalida);
        scrollPane.setPreferredSize(new Dimension(580, 250));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelCentro, scrollPane);
        splitPane.setDividerLocation(0.5);
        splitPane.setResizeWeight(0.5);
        frame.add(splitPane, BorderLayout.CENTER);

        // Redirigir salida estándar
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                areaSalida.append(String.valueOf((char) b));
                areaSalida.setCaretPosition(areaSalida.getDocument().getLength());
            }
        });
        System.setOut(printStream);

        // Eventos botones

        btnAgregar.addActionListener(e -> {
            if (validSelection(comboTipo, comboComp)) {
                int tipo = comboTipo.getSelectedIndex();
                String complejidad = switch (comboComp.getSelectedIndex()) {
                    case 2 -> "media";
                    case 3 -> "alta";
                    default -> "normal";
                };
                gestorCotizacion.agregarDibujo(tipo, complejidad);

                int r = JOptionPane.showConfirmDialog(null, "¿Deseas agregar otro dibujo?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.NO_OPTION) {
                    int verResumen = JOptionPane.showConfirmDialog(null, "¿Deseas ver el resumen del cálculo de la comisión?", "Confirmar", JOptionPane.YES_NO_OPTION);
                    if (verResumen == JOptionPane.YES_OPTION) {
                        calc.mostrarResumen();
                    } else {
                        mostrarCerrarPrograma();
                    }
                }
                comboTipo.setSelectedIndex(0);
                comboComp.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una opcion valida.");
            }
            ListarDibujos.listarProductos(calc);
        });

        btnEliminar.addActionListener(e -> {
            if (calc.getDibujos().size() > 0) {
                gestorCotizacion.eliminarDibujo();
            } else {
                JOptionPane.showMessageDialog(null, "No hay dibujos para eliminar.");
            }
        });

        btnMostrar.addActionListener(e -> ListarDibujos.listarProductos(calc));

        btnResumen.addActionListener(e -> {
            calc.mostrarResumen();
            int r = JOptionPane.showConfirmDialog(null, "¿Quieres agregar más dibujos?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.NO_OPTION) {
                mostrarCerrarPrograma();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private boolean validSelection(JComboBox<String> comboTipo, JComboBox<String> comboComp) {
        return comboTipo.getSelectedIndex() > 0 && comboComp.getSelectedIndex() > 0;
    }

    private void mostrarCerrarPrograma() {
        int r = JOptionPane.showConfirmDialog(null, "¿Deseas cerrar el programa?", "Cerrar", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
