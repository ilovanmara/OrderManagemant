package Presentation;

import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerProduct {
    ProductView productFrame = new ProductView();
    ProductDAO productDAO = new ProductDAO();
    ProductBLL productBLL = new ProductBLL();
    static int id;
    JScrollPane sp;
    ListSelectionModel cellSelectionModel1;
    int selected1;

    public ControllerProduct(){
        productFrame.ok1AddListener(new ok1Listener());
        productFrame.searchAddListener(new searchListener());
        productFrame.ok2AddListener(new ok2Listener());
        productFrame.viewAddListener(new viewListener());
        productFrame.backAddListener(new ControllerProduct.backListener());
        // clientDAO.deleteObject(6);
        productFrame.deleteAddListener(new ControllerProduct.DeleteListener());

    }
    class ok1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = productFrame.getNameTxt().getText();
            String price = productFrame.getUserInput(productFrame.getPriceTxt());
            String total = productFrame.getUserInput(productFrame.getTotalTxt());
            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Total: " + total);
            productBLL.insertProduct(name, Integer.parseInt(price), Integer.parseInt(total));
            JOptionPane.showMessageDialog(null, "The product has been successfully added!");
        }
    }

    class ok2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = productFrame.getNameTxt().getText();
            String price = productFrame.getUserInput(productFrame.getPriceTxt());
            String total = productFrame.getUserInput(productFrame.getTotalTxt());
            System.out.println("Name: " + name);
            System.out.println("Price: " + price);
            System.out.println("Total: " + total);
            productBLL.updateProduct(name, Integer.parseInt(price), Integer.parseInt(total), id);
            JOptionPane.showMessageDialog(null, "The product has been successfully edited!");
        }
    }

    class searchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String id1 = productFrame.getUserInput(productFrame.getName2Txt());
            System.out.println("id: " + id1);
            id = Integer.valueOf(id1);
            Product pro = productBLL.findProductById(Integer.valueOf(id1));
            System.out.println(pro.getProductName() + pro.getPrice() + pro.getProductsNum());
            productFrame.getNameTxt().setText(pro.getProductName());
            productFrame.getPriceTxt().setText(Integer.toString(pro.getPrice()));
            productFrame.getTotalTxt().setText(Integer.toString(pro.getProductsNum()));
            JOptionPane.showMessageDialog(null, "The product has been successfully deleted!");
        }
    }

    class viewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Product> prod = productDAO.findAll();
            productFrame.table = productDAO.createTable(prod);
            sp = new JScrollPane(productFrame.table);
            sp.setBounds(90,300,500, 300);
            productFrame.panel.add(sp);


            cellSelectionModel1 = productFrame.table.getSelectionModel();
            cellSelectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            cellSelectionModel1.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {

                    int selectedRow = productFrame.table.getSelectedRow();
                    int selectedColumn = productFrame.table.getSelectedColumn();
                    selected1 = (Integer) productFrame.table.getValueAt(selectedRow, selectedColumn);
                    System.out.println("Selected1: " + selected1);
                }
            });
        }
    }

    class backListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            productFrame.setVisible(false);
            Controller c =  new Controller();
        }
    }

    class DeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //productDAO.deleteObject(selected1, "idProduct");
            productBLL.deleteProduct(selected1);
        }
    }

    public static void main(String args[]){
        ControllerProduct c =  new ControllerProduct();
    }

}
