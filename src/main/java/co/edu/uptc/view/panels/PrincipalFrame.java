package co.edu.uptc.view.panels;

import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.List;

public class PrincipalFrame extends JFrame {

    private ViewController viewController = new ViewController();
    private MainPanel mainPanel;

    public PrincipalFrame() {
        // Obtener el panel principal desde el controlador
        this.mainPanel = viewController.getMainPanel();
        panelConfig();
    }


    // Configura y muestra el JFrame principal
    public void panelConfig() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Operación al cerrar la ventana
        add(mainPanel); // Añadir el panel principal al frame
        pack(); // Ajustar el tamaño del frame según el contenido del panel
        setLocationRelativeTo(null); // Centrar el frame en la pantalla
        // Hacer visible el frame después de todas las configuraciones
        setVisible(true);
    }





    public static void main(String[] args) {
        // Ejemplo de cómo instanciar y mostrar PrincipalFrame
        SwingUtilities.invokeLater(() -> {
            PrincipalFrame frame = new PrincipalFrame();
        });
    }
}