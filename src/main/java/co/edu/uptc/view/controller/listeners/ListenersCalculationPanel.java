package co.edu.uptc.view.controller.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Toll;
import co.edu.uptc.persistence.ManagementToll;
import co.edu.uptc.view.controller.ViewController;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class ListenersCalculationPanel {
    //Respectivas Instancias
    private final ViewController viewController;
    private ManagementToll managementToll ;
    //Un checker para las verificaciones
    private boolean checker = false ;
    //Se utiliza una coleccion que no permite elementos duplicados para los destinos del csv
    private final Set<String> destinations = new HashSet<>();
    //Se utiliza una coleccion que no permite elementos duplicados para las vias
    private final Set<String> roads = new HashSet<>();
    //Se utiliza una coleccion que no permite elementos duplicados para las categorias
    private final Set<String> categories = new HashSet<>();


    //Constructor de la clase
    public ListenersCalculationPanel(ViewController viewController) {
        managementToll = new ManagementToll();
        this.viewController = viewController;
        verifyOriginAndFate();
    }

    /**
     * @author monxvoll
     **/

    //Metodo que verifica los datos de los JtextFields y almacena los respectivos elementos a las colecciones
    public void verifyOriginAndFate() {
        // Se cargan los datos a la lista de peajes
        managementToll.loadToll(ETypeFile.CSV);
        //listener que detecta un click sobre el JcomboBox
        viewController.getMainPanel().getCalculationPanel().getViaComboBox().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    listenerJComboBox();
                }
            }
        });

        viewController.getMainPanel().getCalculationPanel().getCategoriaComboBox().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    listenerJComboBox();
                }
            }
        });

    }

    //Metodo que actualiza los JComboBox
    public void listenerJComboBox(){
        //Itera la lista y almacena los posibles destinos en la coleccion
        for (Toll toll : managementToll.getTollList()) {
            destinations.add(toll.getDestinationCity());
        }
        //Almacena en strings lo que se digita en los textfield
        String origin = viewController.getMainPanel().getCalculationPanel().getOrigenField().getText().toUpperCase().trim();

        String fate = viewController.getMainPanel().getCalculationPanel().getDestinoField().getText().toUpperCase().trim();

        // Verificar si "origin" es "BOGOTA" y si "fate" esta en la coleccion de destinos
        if (Objects.equals(origin, "BOGOTA") && destinations.contains(fate)) {
            //Se limpian los JComboBox de las vias y catergorias
            viewController.getMainPanel().getCalculationPanel().getViaComboBox().removeAllItems();
            viewController.getMainPanel().getCalculationPanel().getCategoriaComboBox().removeAllItems();
            //Se llama al metodo que actualiza el JcomboBox de las vias y de la categorias
            actionListenerShowRoad(origin, fate);
            //Se actualiza el estado del checker
            checker = true;
        } else {
            //Se actualiza el estado del checker
            checker = false;
            JOptionPane.showMessageDialog(null, "<html><p style='font-size:14px;'>" + "Ingrese los campos correctamente" + "</p></html>", "Error", JOptionPane.ERROR_MESSAGE);
            //Se borran todos los elementos actuales de los JComboBoxs
            viewController.getMainPanel().getCalculationPanel().getViaComboBox().removeAllItems();
            viewController.getMainPanel().getCalculationPanel().getCategoriaComboBox().removeAllItems();
        }
    }

    /**
     * @author monxvoll
     **/

    //Metodo que actualiza el JComboBox de las vias y de las categorias
    private void actionListenerShowRoad(String origin, String fate) {
        //Se limpian las colecciones antes de añadir nuevas vias y categorias
        roads.clear();
        categories.clear();
        //Se itera cada objeto de la lista
        for (Toll toll : managementToll.getTollList()) {
            if (Objects.equals(toll.getOrigin(), origin) && Objects.equals(toll.getDestinationCity(), fate)) {
                //obtiene la via  y categorias encontradas en el archivo para cada objeto que cumpla la condicion
                //Tras esto se almacenan los strings en sus respectivas colecciones
                roads.add(toll.getRoad());
                categories.add(toll.getCategory());
            }
        }
        //Una vez llena cada coleccion . se llaman a los metodos que ubican cada string de las colecciones en los JcomboBox
        addRoadsToJComboBox();
        addCategoriesToJComboBox();
    }

    /**
     * @author monxvoll
     **/

    //Metodo que añade las rutas al JComboBox
    private void addRoadsToJComboBox(){
        //Trae el JComboBox
        JComboBox<String> vias = viewController.getMainPanel().getCalculationPanel().getViaComboBox();
        // Se crea otra coleccion para tener un registro
        Set<String> addedItems = new HashSet<>();
        // Itera sobre la colección roads y añade cada elemento al JComboBox si no existe
        for (String via : roads) {
            if (!addedItems.contains(via)) {
                vias.addItem(via);//añade al jcombo
                addedItems.add(via);//tambien añade a la coleccion donde se tiene el registro
            }
        }
    }

    /**
     * @author monxvoll
     **/

    //Metodo que añade las categorias al JComboBox
    private void addCategoriesToJComboBox(){
        //Trae el combo box
        JComboBox<String> categorias = viewController.getMainPanel().getCalculationPanel().getCategoriaComboBox();
        // Se crea otra coleccion para tener un registro
        Set<String> addedItems = new HashSet<>();
        // Itera sobre la colección categories y añade cada elemento al JComboBox si no existe
        for (String categoria : categories) {
            if (!addedItems.contains(categoria)) {
               categorias.addItem(categoria);
               addedItems.add(categoria);
            }
        }
    }


}
