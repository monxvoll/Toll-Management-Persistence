package co.edu.uptc.view.controller.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Toll;
import co.edu.uptc.persistence.ManagementToll;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashSet;
import java.util.Set;

public class MyFocusListener implements FocusListener {

    private Set<String> destinations;
    private Timer timer;
    private JTextField lastTextField;

    public MyFocusListener() {
        ManagementToll managementToll = new ManagementToll();
        managementToll.loadToll(ETypeFile.CSV);

        destinations = new HashSet<>();
        for (Toll toll : managementToll.getTollList()) {
            destinations.add(toll.getDestinationCity());
        }

        // Configuración del temporizador con un ActionListener
        timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showErrorMessageIfNeeded();
            }
        });
        timer.setRepeats(false); // El temporizador se ejecuta una sola vez
    }

    @Override
    public void focusGained(FocusEvent e) {
        // No necesitamos hacer nada específico cuando se gana el foco
    }

    @Override
    public void focusLost(FocusEvent e) {
        // Obtener la ventana principal y el textField asociados al evento
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
        JTextField textField = (JTextField) e.getComponent();

        // Reiniciar el temporizador si se cambió el foco rápidamente
        if (lastTextField != null && !lastTextField.equals(textField)) {
            timer.restart();
        }

        if (frame != null && frame.isActive()) {
            lastTextField = textField; // Guardar el textField para mostrar el mensaje después
            timer.restart(); // Reiniciar el temporizador para posponer la validación
        }
    }

    private void showErrorMessageIfNeeded() {
        if (lastTextField != null) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(lastTextField);
            // Verificar si la ventana principal está activa y no minimizada
            if (frame != null && frame.isActive() && (frame.getExtendedState() & Frame.ICONIFIED) == 0) {
                String text = lastTextField.getText().toUpperCase().trim();

                // Validar el contenido del campo de texto según su nombre
                if ("originField".equals(lastTextField.getName())) {
                    validateOriginField(lastTextField, text);
                } else if ("destinationField".equals(lastTextField.getName())) {
                    validateDestinationField(lastTextField, text);
                }
            }
            lastTextField = null; // Reiniciar el campo después de mostrar el mensaje
        }
    }

    private void validateOriginField(JTextField textField, String text) {
        // Validar el campo de origen
        if (!text.equals("BOGOTA")) {
            textField.requestFocus();
            showDialogAndFocus(textField, "Digite un origen válido");
        }
    }

    private void validateDestinationField(JTextField textField, String text) {
        // Validar el campo de destino
        if (!destinations.contains(text)) {
            textField.requestFocus();
            showDialogAndFocus(textField, "Digite un destino válido");
        }
    }

    private void showDialogAndFocus(JTextField textField, String message) {
        // Mostrar un mensaje de error en un JOptionPane y limpiar el campo de texto
        JOptionPane.showMessageDialog(null, "<html><p style='font-size:14px;'>" + message + "</p></html>", "Error", JOptionPane.ERROR_MESSAGE);
        textField.setText(""); // Limpiar el contenido del JTextField después de mostrar el mensaje
    }
}
