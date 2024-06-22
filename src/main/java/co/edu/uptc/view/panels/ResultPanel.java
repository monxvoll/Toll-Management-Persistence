package co.edu.uptc.view.panels;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private JLabel totalPeajesLabel;
    private JLabel totalDistanciaLabel;
    private JLabel totalPrecioLabel;
    private JLabel totalTiempoLabel;
    private JLabel totalPeajesValue;
    private JLabel totalDistanciaValue;
    private JLabel totalPrecioValue;
    private JLabel totalTiempoValue;
    private JButton retrocederButton;

    public ResultPanel() {
        // Inicialización de componentes
        initializer();

        // Diseño del panel usando GridBagLayout
        setLayout(new GridBagLayout());

        // Borde y color de fondo del panel de resultados
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        new LineBorder(new Color(64, 193, 208), 2),
                        "Resultados",
                        SwingConstants.CENTER,
                        SwingConstants.CENTER,
                        new Font("Lato", Font.BOLD, 12),
                        new Color(64, 193, 208)),
                new EmptyBorder(10, 10, 10, 10)
        ));
        setBackground(new Color(50, 43, 45));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dimensiones para etiquetas y valores
        Dimension labelDimension = new Dimension(150, 30);
        Dimension valueDimension = new Dimension(200, 30); // Aumento del tamaño de los valores
        Dimension buttonDimension = new Dimension(200, 40); // Aumento del tamaño del botón

        // Configuración de los estilos similares a CalculationPanel
        configureResultLabel(totalPeajesLabel, new Color(226, 175, 199));
        totalPeajesLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        configureResultLabel(totalDistanciaLabel, new Color(240, 200, 214));
        totalDistanciaLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        configureResultLabel(totalPrecioLabel, new Color(113, 15, 61));
        totalPrecioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        configureResultLabel(totalTiempoLabel, new Color(75, 4, 32));
        totalTiempoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Total de peajes
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.weightx = 0.5;
        add(totalPeajesLabel, gbc);

        gbc.gridx = 1;
        add(totalPeajesValue, gbc);

        // Total de distancia
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(totalDistanciaLabel, gbc);

        gbc.gridx = 1;
        add(totalDistanciaValue, gbc);

        // Total precio
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(totalPrecioLabel, gbc);

        gbc.gridx = 1;
        add(totalPrecioValue, gbc);

        // Total tiempo
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(totalTiempoLabel, gbc);

        gbc.gridx = 1;
        add(totalTiempoValue, gbc);

        // Botón Retroceder
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 20, 0);
        add(retrocederButton, gbc);
    }

    private void initializer() {
        // Configuración de las etiquetas para los nombres de los resultados
        this.totalPeajesLabel = createLabel("Total de peajes");
        this.totalDistanciaLabel = createLabel("Total de distancia");
        this.totalPrecioLabel = createLabel("Total de precio");
        this.totalTiempoLabel = createLabel("Total de tiempo");

        // Configuración de las etiquetas para mostrar los valores de los resultados
        this.totalPeajesValue = createValueLabel("XXX");
        this.totalDistanciaValue = createValueLabel("YYYY");
        this.totalPrecioValue = createValueLabel("ZZZZ");
        this.totalTiempoValue = createValueLabel("AAAA");

        // Configuración del botón Retroceder
        this.retrocederButton = createStyledButton("Retroceder", new Dimension(200, 40));
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER); // Center alignment
        label.setFont(new Font("Montserrat", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JLabel createValueLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER); // Center alignment
        label.setFont(new Font("Montserrat", Font.PLAIN, 14));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(200, 30));
        return label;
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

    private void configureResultLabel(JLabel label, Color backgroundColor) {
        label.setFont(new Font("Montserrat", Font.BOLD, 14));
        label.setForeground(Color.BLACK); // Color de letra negro
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 60, 61)),
                BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        label.setOpaque(true);
        label.setBackground(backgroundColor);
        label.setPreferredSize(new Dimension(150, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
    }

    private void configureResultValue(JLabel label, Color fontColor, Color backgroundColor) {
        label.setFont(new Font("Montserrat", Font.PLAIN, 14));
        label.setForeground(fontColor); // Color de letra negro
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        label.setOpaque(true);
        label.setBackground(backgroundColor);
        label.setPreferredSize(new Dimension(200, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER); // Center alignment
    }

    // Getters
    public JLabel getTotalPeajesValue() {
        return totalPeajesValue;
    }

    public JLabel getTotalDistanciaValue() {
        return totalDistanciaValue;
    }

    public JLabel getTotalPrecioValue() {
        return totalPrecioValue;
    }

    public JLabel getTotalTiempoValue() {
        return totalTiempoValue;
    }

    public JButton getRetrocederButton() {
        return retrocederButton;
    }
}
