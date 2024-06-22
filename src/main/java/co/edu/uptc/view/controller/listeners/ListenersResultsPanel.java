package co.edu.uptc.view.controller.listeners;

import co.edu.uptc.enums.ETypeFile;
import co.edu.uptc.model.Toll;
import co.edu.uptc.persistence.ManagementToll;
import co.edu.uptc.view.controller.ViewController;

import javax.swing.*;
import java.util.*;

public class ListenersResultsPanel {

    private final ViewController viewController;
    private ManagementToll managementToll ;
    private List<String> peajes = new ArrayList<>();
    private List<Integer> distancias = new ArrayList<>();
    private List<Integer> precios = new ArrayList<>();
    private List<String> tiempos = new ArrayList<>();
    private Set<String> destinations = new HashSet<>();

    public ListenersResultsPanel(ViewController viewController) {
        managementToll = new ManagementToll();
        this.viewController =  viewController;
        actionListenerShowResultPanel();
        backToCalculationPanel();
        quantityOfTolls();
    }

    /**
     * @author monxvoll
     **/

    //Metodo que cambia entre paneles
    public void actionListenerShowResultPanel() {
        //Action listener para el boton de calcular
        viewController.getMainPanel().getCalculationPanel().getCalcularButton().addActionListener(e -> {
            //Itera la lista y almacena los posibles destinos en la coleccion
            for (Toll toll : managementToll.getTollList()) {
                destinations.add(toll.getDestinationCity());
            }
            //Almacena en strings lo que se digita en los textfield
            String origin = viewController.getMainPanel().getCalculationPanel().getOrigenField().getText().toUpperCase().trim();
            String fate = viewController.getMainPanel().getCalculationPanel().getDestinoField().getText().toUpperCase().trim();

            JComboBox<String> vias = viewController.getMainPanel().getCalculationPanel().getViaComboBox();
            JComboBox<String> categorias = viewController.getMainPanel().getCalculationPanel().getCategoriaComboBox();

            if (Objects.equals(origin, "BOGOTA") && destinations.contains(fate) &&
                    vias.getItemCount() == 0 && categorias.getItemCount() == 0) {
                // Mostrar mensaje de error con HTML y título personalizado
                JOptionPane.showMessageDialog(null, "<html><p style='font-size:14px;'>Por favor rellene todos los campos.</p></html>", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (Objects.equals(origin, "BOGOTA") && destinations.contains(fate) &&
                    vias.getItemCount() != 0 && categorias.getItemCount() != 0) {

                viewController.getMainPanel().getCalculationPanel().setVisible(false);
                // Se borran todos los elementos actuales de los JComboBoxs
                vias.removeAllItems();
                categorias.removeAllItems();

                viewController.getMainPanel().getResultPanel().setVisible(true);
            }

        });
    }


    /**
     * @author monxvoll
     **/


    public void quantityOfTolls(){
        // Cargar peajes desde el archivo CSV
        managementToll.loadToll(ETypeFile.CSV);
        //Listener del boton calcular
        viewController.getMainPanel().getCalculationPanel().getCalcularButton().addActionListener(e -> {
            //Se recojen los valores de los JTextFields y de los label.
        String origin = viewController.getMainPanel().getCalculationPanel().getOrigenField().getText().toUpperCase();;
        String fate = viewController.getMainPanel().getCalculationPanel().getDestinoField().getText().toUpperCase();
        String category = (String) viewController.getMainPanel().getCalculationPanel().getCategoriaComboBox().getSelectedItem();
        String via = (String) viewController.getMainPanel().getCalculationPanel().getViaComboBox().getSelectedItem();
            for(Toll peaje : managementToll.getTollList()){
                if(peaje.getOrigin().equals(origin) && peaje.getDestinationCity().equals(fate)
                        && Objects.equals(peaje.getCategory(), category) && Objects.equals(peaje.getRoad(), via)){
                    //Se extraen los datos del objeto que cumpla la condicion
                    String roadType = peaje.getRoadType();
                    int distance = peaje.getDistance();
                    int precio = peaje.getCost();
                    String  tiempo = peaje.getTime();
                    //Se guardan los peajes encontrados en la lista
                    peajes.add(roadType);
                    //Ademas se guardan las distancias encontradas en la lista
                    distancias.add(distance);
                    //Se guardan los precios encontrados en la lista
                    precios.add(precio);
                    //Finalmente se guardan los tiempos encontrados en la lista
                    tiempos.add(tiempo);
                }
            }
            //Llamadas a metodos
            countTolls();
            sumDistance();
            sumCost();
            sumTime();
        });
    }

    /**
     * @author monxvoll
     **/

    //Metodo que calcula el total de peajes y lo añade al Jlabel
    private void countTolls(){
        int cantPeajes =0;
        //La cantidad de peajes sera el tamaño de la lista
            cantPeajes=peajes.size();
        //Se actualiza el Jlabel de la cantidad de peajes
        viewController.getMainPanel().getResultPanel().getTotalPeajesValue().setText(String.valueOf(cantPeajes));
        //Se limpia la lista despues de añadir los peajes encontrados
        peajes.clear();
    }

    /**
     * @author monxvoll
     **/

    //Metodo que calcula el total de distancia y lo añade al Jlabel
    private void sumDistance(){
        int totalDistance=0;
        //Itera cada distancia y la va sumando
        for(int distance : distancias){
             totalDistance+=distance;
        }
        //Se actualiza el Jlabel de la distancia
        viewController.getMainPanel().getResultPanel().getTotalDistanciaValue().setText(String.valueOf(totalDistance));
        //Se limpia la lista despues de añadir las distancias
        distancias.clear();
    }

    /**
     * @author monxvoll
     **/

    private void sumCost() {
        //Itera cada costo y lo va sumando
        int totalCost=0;
        for(int cost : precios){
            totalCost+=cost;
        }
        //Se actualiza el Jlabel de los costos
        viewController.getMainPanel().getResultPanel().getTotalPrecioValue().setText(String.valueOf(totalCost));
        //Se limpia la lista despues de añadir los costos
        precios.clear();
    }

    /**
     * @author monxvoll
     **/

    private void sumTime() {
        int totalTime = 0;
        for (String tiempo : tiempos) {
            //Convierte el tiempo en formato de cadena a minutos usando el método 'timeManager'
            int tiempoMinutos = timeManager(tiempo);
            //Suma los minutos obtenidos al tiempo total
            totalTime += tiempoMinutos;
        }
        int horas = totalTime / 60; //Calcula cuantas horas hay en el tiempo total
        int minutos = totalTime % 60; //Calcula los minutos restantes después de extraer las horas
        //Formatea el tiempo total en una cadena con el formato "X h Y min"
        String tiempoFormato = String.format("%d h %d min", horas, minutos);
        //Se actualiza el Jlabel de los tiempos
        viewController.getMainPanel().getResultPanel().getTotalTiempoValue().setText(String.valueOf(tiempoFormato));
        //Se limpia la lista despues de añadir los tiempos
        tiempos.clear();
    }

    /**
     * @author monxvoll
     **/

    //Metodo que realiza la conversion del tiempo
    private int timeManager(String time) {
        //Divide la cadena de tiempo en partes usando el espacio como separador
        String[] partes = time.split(" ");
        //Convierte la primera parte (horas) de la cadena a un entero
        int horas = Integer.parseInt(partes[0]);
        //Convierte la tercera parte (minutos) de la cadena a un entero
        int minutos = Integer.parseInt(partes[2]);
        //Calcula el total de minutos y lo devuelve
        return horas * 60 + minutos;
    }

    /**
     * @author monxvoll
     **/

    //Metodo que regresa al panel principal
    public void backToCalculationPanel(){
        //Listener del boton de retroceder
        viewController.getMainPanel().getResultPanel().getRetrocederButton().addActionListener(e -> {
            //Cambia visibilidades
            viewController.getMainPanel().getCalculationPanel().setVisible(false);
            viewController.getMainPanel().getCalculationPanel().setVisible(true);
        });
    }
}
