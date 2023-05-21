package Presentation;

import BusinessLogic.ClientBLL;
import DataAccess.ClientDAO;
import Model.Client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ControllerClient {
    ClientView clientFrame = new ClientView();
    ClientDAO clientDAO = new ClientDAO();
    ClientBLL clientBLL = new ClientBLL();
    ListSelectionModel cellSelectionModel1;
    int selected1;
    static int id;
    JScrollPane sp;
    public ControllerClient(String s){
        clientFrame.ok1AddListener(new ok1Listener());
        clientFrame.searchAddListener(new searchListener());
        clientFrame.ok2AddListener(new ok2Listener());
        clientFrame.viewAddListener(new viewListener());
        clientFrame.backAddListener(new backListener());
       // clientDAO.deleteObject(6);
        clientFrame.deleteAddListener(new DeleteListener());
    }
    class ok1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = clientFrame.getNameTxt().getText();
            String email = clientFrame.getUserInput(clientFrame.getEmailTxt());
            String address = clientFrame.getUserInput(clientFrame.getAddressTxt());
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            clientBLL.insertClient(name, email, address);
            JOptionPane.showMessageDialog(null, "The client has been successfully added!");

        }
    }

    class ok2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = clientFrame.getNameTxt().getText();
            String email = clientFrame.getUserInput(clientFrame.getEmailTxt());
            String address = clientFrame.getUserInput(clientFrame.getAddressTxt());
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Address: " + address);
            clientBLL.updateClient(name, email, address, id);
            JOptionPane.showMessageDialog(null, "The client has been successfully edited!");
        }
    }

    class searchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String id1 = clientFrame.getUserInput(clientFrame.getName2Txt());
            System.out.println("id: " + id1);
            id = Integer.valueOf(id1);
            Client cl = clientBLL.findClientById(id);
            clientFrame.getNameTxt().setText(cl.getNameClient());
            clientFrame.getEmailTxt().setText(cl.getEmail());
            clientFrame.getAddressTxt().setText(cl.getAddress());
        }
    }

    class viewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Client> cl = clientDAO.findAll();
            clientFrame.table = clientDAO.createTable(cl);
            sp = new JScrollPane(clientFrame.table);
            sp.setBounds(90,300,500, 300);
            clientFrame.panel.add(sp);

            cellSelectionModel1 = clientFrame.table.getSelectionModel();
            cellSelectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModel1.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {

                    int selectedRow = clientFrame.table.getSelectedRow();
                    int selectedColumn = clientFrame.table.getSelectedColumn();
                    selected1 = (Integer) clientFrame.table.getValueAt(selectedRow, selectedColumn);
                    System.out.println("Selected1: " + selected1);
                }
            });
        }
    }

    class backListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            clientFrame.setVisible(false);
            Controller c =  new Controller();
        }
    }

    class DeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //clientDAO.deleteObject(selected1, "idClient");
            clientBLL.deleteClient(selected1);
            JOptionPane.showMessageDialog(null, "The client has been successfully deleted!");
        }
    }

    public void list(){
        List<Client> list = clientDAO.findAll();
        System.out.println(list);
    }

    public static void main(String args[]){
        ControllerClient c =  new ControllerClient("client");
    }

}
