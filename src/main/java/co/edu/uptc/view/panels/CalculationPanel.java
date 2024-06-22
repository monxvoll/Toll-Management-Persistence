package co.edu.uptc.view.panels;

import co.edu.uptc.view.controller.listeners.MyFocusListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;

public class CalculationPanel extends JPanel {
    private JTextField origenField;
    private JTextField destinoField;
    private JComboBox<String> viaComboBox;
    private JComboBox<String> categoriaComboBox;
    private JButton calcularButton;
    private JLabel tituloLabel;
    private JLabel origenLabel;
    private JLabel destinoLabel;
    private JLabel viaLabel;
    private JLabel categoriaLabel;
    private MyFocusListener focusListener;


    public CalculationPanel() {
        // Inicialización de componentes
        initializeComponents();

        // Diseño del panel usando GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dimensiones para etiquetas y campos de texto
        Dimension labelDimension = new Dimension(120, 30);
        Dimension fieldDimension = new Dimension(150, 30);

        // Configuración del título
        tituloLabel.setPreferredSize(new Dimension(200, 30));
        tituloLabel.setForeground(new Color(64, 193, 208));
        tituloLabel.setOpaque(true);
        tituloLabel.setBackground(new Color(70, 60, 61));
        tituloLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        tituloLabel.setFont(new Font("Lato", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(tituloLabel, gbc);

        // Configuración de Origen
        origenLabel.setPreferredSize(labelDimension);
        origenLabel.setBackground(new Color(226, 175, 199));
        origenLabel.setOpaque(true);
        origenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        origenLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 5);
        add(origenLabel, gbc);

        origenField.setPreferredSize(fieldDimension);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 10);
        add(origenField, gbc);
        origenField.addFocusListener(focusListener);

        // Configuración de Destino
        destinoLabel.setPreferredSize(labelDimension);
        destinoLabel.setBackground(new Color(240, 200, 214));
        destinoLabel.setOpaque(true);
        destinoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        destinoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 5);
        add(destinoLabel, gbc);

        destinoField.setPreferredSize(fieldDimension);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 10);
        add(destinoField, gbc);
        destinoField.addFocusListener(focusListener);

        // Configuración de Vía
        viaLabel.setPreferredSize(labelDimension);
        viaLabel.setBackground(new Color(113, 15, 61));
        viaLabel.setOpaque(true);
        viaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        viaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 5);
        add(viaLabel, gbc);

        viaComboBox.setPreferredSize(fieldDimension);
        configureComboBox(viaComboBox);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 10);
        add(viaComboBox, gbc);

        // Configuración de Categoría
        categoriaLabel.setPreferredSize(labelDimension);
        categoriaLabel.setBackground(new Color(75, 4, 32));
        categoriaLabel.setOpaque(true);
        categoriaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        categoriaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 5);
        add(categoriaLabel, gbc);

        categoriaComboBox.setPreferredSize(fieldDimension);
        configureComboBox(categoriaComboBox);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 10);
        add(categoriaComboBox, gbc);

        // Configuración de Botones
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(calcularButton);
        add(buttonPanel, gbc);

        // Configuración de colores generales del panel
        setBackground(new Color(50, 43, 45));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }


    private void initializeComponents() {
        // Inicialización del focus listener
        focusListener = new MyFocusListener();

        // Configuración del título
        tituloLabel = new JLabel("CSV Route Analyzer", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Lato", Font.BOLD, 24));

        // Configuración de las etiquetas
        origenLabel = createMinimalLabel("    ORIGEN");
        destinoLabel = createMinimalLabel("    DESTINO");
        viaLabel = createMinimalLabel("    VIA");
        categoriaLabel = createMinimalLabel("    CATEGORIA");

        // Configuración de los campos de texto
        origenField = createMinimalTextField(20);
        origenField.setName("originField");
        origenField.setBackground(new Color(152, 139, 148));
        destinoField = createMinimalTextField(20);
        destinoField.setName("destinationField");
        destinoField.setBackground(new Color(152, 139, 148));

        // Configuración de los JComboBox
        viaComboBox = new JComboBox<>();
        viaComboBox.setBackground(new Color(152, 139, 148));
        categoriaComboBox = new JComboBox<>();
        categoriaComboBox.setBackground(new Color(152, 139, 148));

        // Configuración de los botones
        Dimension buttonDimension = new Dimension(150, 30);
        calcularButton = createStyledButton("Calcular", buttonDimension);

    }


    private JLabel createMinimalLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER); // Centrar el texto
        label.setFont(new Font("Montserrat", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 60, 61)),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        label.setOpaque(true);
        label.setBackground(new Color(225, 193, 158));
        label.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
        label.setVerticalAlignment(SwingConstants.CENTER); // Centrar verticalmente
        return label;
    }


    private JTextField createMinimalTextField(int columns) {
        JTextField textField = new JTextField(columns);
        textField.setFont(new Font("Montserrat", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return textField;
    }


    private JButton createStyledButton(String text, Dimension dimension) {
        JButton button = new JButton(text);
        button.setPreferredSize(dimension);
        button.setFont(new Font("Montserrat", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 60, 61));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        return button;
    }


    private static class ArrowIcon implements Icon {
        private static final int WIDTH = 9;
        private static final int HEIGHT = 5;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setColor(Color.BLACK); // Color negro fijo para la flecha

            int midPointX = WIDTH / 2;
            int[] xPoints = {x, x + WIDTH, x + midPointX};
            int[] yPoints = {y, y, y + HEIGHT};

            g2.fillPolygon(xPoints, yPoints, 3);
            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return WIDTH;
        }

        @Override
        public int getIconHeight() {
            return HEIGHT;
        }
    }

    // Asegúrate de que en el método configureComboBox, estableces el icono correctamente
    private void configureComboBox(JComboBox<String> comboBox) {
        // Configuración básica del JComboBox
        comboBox.setPreferredSize(new Dimension(150, 30));
        comboBox.setFont(new Font("Montserrat", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));

        // Personalización de la flecha del JComboBox
        comboBox.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrowButton = super.createArrowButton();
                arrowButton.setBackground(new Color(152, 139, 148)); // Color de fondo opcional
                arrowButton.setBorder(BorderFactory.createEmptyBorder()); // Borde vacío para evitar resaltado al pasar el mouse
                arrowButton.setContentAreaFilled(false); // No rellenar el área para evitar efectos de resaltado

                // Cambiar el icono del botón a una flecha simple
                arrowButton.setIcon(new ArrowIcon());

                return arrowButton;
            }

            @Override
            protected ComboPopup createPopup() {
                // Sobrescribir createPopup para ajustar el estilo del popup si es necesario
                BasicComboPopup popup = (BasicComboPopup) super.createPopup();
                // Configurar colores u otros estilos del popup si es necesario
                return popup;
            }
        });
    }

    // Getters
    public JTextField getOrigenField() {
        return origenField;
    }

    public JTextField getDestinoField() {
        return destinoField;
    }

    public JComboBox<String> getViaComboBox() {
        return viaComboBox;
    }

    public JComboBox<String> getCategoriaComboBox() {
        return categoriaComboBox;
    }

    public JButton getCalcularButton() {
        return calcularButton;
    }


}
