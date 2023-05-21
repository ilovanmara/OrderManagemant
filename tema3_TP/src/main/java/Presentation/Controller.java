package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aceasta clasa face legatura dintre toate partile interfatei grafice
 */
public class Controller {
    View gui = new View();

    Controller(){
        gui.clientAddListener(new Controller.clientListener());
        gui.productAddListener(new Controller.productListener());
        gui.orderAddListener(new Controller.orderListener());
    }
    class clientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerClient client = new ControllerClient("client");
            gui.setVisible(false);
        }
    }

    class productListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ControllerProduct client = new ControllerProduct();
            gui.setVisible(false);
        }
    }

    class orderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderView order = new OrderView();
            gui.setVisible(false);
        }
    }

    public static void main(String argc[]){
        Controller c = new Controller();
    }
}
