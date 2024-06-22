package co.edu.uptc.view.panels;

import co.edu.uptc.persistence.ManagementToll;

import javax.swing.*;
import java.awt.*;

public class MainPanel  extends JPanel {

    private CalculationPanel calculationPanel;
    private ResultPanel resultPanel;

    /**
     * @author monxvoll
     **/

    //Constructor de la clase
    public MainPanel() {

        //Se inicializan las visibilidades de los paneles , junto a sus tamaños
        calculationPanel = new CalculationPanel();
        calculationPanel.setVisible(true);
        add(calculationPanel);
        calculationPanel.setPreferredSize(new Dimension(600, 600));

        resultPanel = new ResultPanel();
        resultPanel.setVisible(false);
        add(resultPanel);
        resultPanel.setPreferredSize(new Dimension(600, 600));
    }

    //Getters
    public CalculationPanel getCalculationPanel() {
        return calculationPanel;
    }

    public ResultPanel getResultPanel() {
        return resultPanel;
    }

}
