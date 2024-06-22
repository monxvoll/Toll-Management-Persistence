package co.edu.uptc.view.controller;

import co.edu.uptc.persistence.ManagementToll;
import co.edu.uptc.view.controller.listeners.ListenersCalculationPanel;
import co.edu.uptc.view.controller.listeners.ListenersResultsPanel;

import co.edu.uptc.view.panels.MainPanel;


public class ViewController {

   private ManagementToll managementToll ;
   private MainPanel mainPanel;
   private ListenersCalculationPanel listenersUpdateMenu;
   private ListenersResultsPanel listenersResultsPanel;

   /**
    * @author monxvoll
    **/

   public ViewController (){
      this.mainPanel = new MainPanel();
      this.listenersUpdateMenu = new ListenersCalculationPanel(this);
      this.listenersResultsPanel = new ListenersResultsPanel(this);
   }

   //Getter
   public MainPanel getMainPanel() {
      return mainPanel;
   }
}