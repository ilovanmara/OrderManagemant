package Presentation;

import BusinessLogic.OrderBLL;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Order;
import Model.Product;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderView extends JFrame{
    Border border = BorderFactory.createLineBorder(Color.white);
    private JPanel panel = new JPanel();
    private JLabel title;
    private JLabel productLbl = new JLabel("Choose a product:");
    private JLabel clientLbl = new JLabel("Choose a client:");
    private JButton createBtn = new JButton("Create Order");
    private JLabel cantLbl = new JLabel("Quantity");
    private JTextField cantTxt = new JTextField(10);
    private JTextField msg = new JTextField(10);
    private JButton backBtn = new JButton("Back");
    JTable clientsTable;
    JTable productsTable;
    JScrollPane sp1;
    ListSelectionModel cellSelectionModel2;
    ListSelectionModel cellSelectionModel1;
    JScrollPane sp2;
    private int selected1;
    private int selected2;
    ProductDAO productDAO = new ProductDAO();
    ClientDAO clientDAO = new ClientDAO();
    OrderBLL orderBLL = new OrderBLL();
    OrderDAO orderDAO = new OrderDAO();
    public OrderView(){
        this.setName("Orders management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(680, 700);
        this.setLocationRelativeTo(null);

        title = new JLabel("Orders Management");
        title.setBounds(180, 10, 400, 50);
        title.setFont(new Font("Georgia",Font.BOLD,30));
        panel.setLayout(null);
        panel.setBackground(new Color(201, 225, 198));
        panel.add(title);
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        addBtn();
        insertTab();
        backAddListener(new backListener());

        this.add(panel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }
    private void createButton(JButton button){
        Color buttonColor = new Color(148, 106, 106);
        button.setFont(new Font("Century",Font.CENTER_BASELINE,20));
        button.setForeground(Color.white);
        button.setBackground(buttonColor);
        button.setBorder(border);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }
    public void addBtn(){
        panel.add(createBtn);
        createButton(createBtn);
        createBtn.setBounds(230, 600, 200, 30);

        msg.setBounds(180, 570, 300, 25);
        panel.add(msg);

        panel.add(backBtn);
        createButton(backBtn);
        backBtn.setBounds(500, 600, 150, 30);
        panel.add(backBtn);
    }

    public void insertTab(){
        productLbl.setBounds(120, 70, 200, 25);
        productLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        clientLbl.setBounds(120, 290, 200, 25);
        clientLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        cantLbl.setBounds(100, 540, 200, 25);
        cantLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));

        cantTxt.setBounds(180, 540, 300, 25);

        List<Product> prod = productDAO.findAll();
        productsTable = productDAO.createTable(prod);
        sp1 = new JScrollPane(this.productsTable);
        sp1.setBounds(90,320,500, 180);
        panel.add(sp1);

        List<Client> cl = clientDAO.findAll();
        clientsTable = clientDAO.createTable(cl);
        sp2 = new JScrollPane(this.clientsTable);
        sp2.setBounds(90,100,500, 180);
        panel.add(sp2);

        panel.add(productLbl);
        panel.add(clientLbl);
        panel.add(cantLbl);
        panel.add(cantTxt);
        selection();
        addCreateListener(new SelectListener());
    }
    public void backAddListener(ActionListener e){
        backBtn.addActionListener(e);
    }

    public void selection(){
        cellSelectionModel1 = clientsTable.getSelectionModel();
        cellSelectionModel1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                int selectedRow = clientsTable.getSelectedRow();
                int selectedColumn = clientsTable.getSelectedColumn();
                selected1 = (Integer) clientsTable.getValueAt(selectedRow, selectedColumn);
                System.out.println("Selected1: " + selected1);
            }
        });

        cellSelectionModel2 = productsTable.getSelectionModel();
        cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel2.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                int selectedRow = productsTable.getSelectedRow();
                int selectedColumn = productsTable.getSelectedColumn();
                selected2 = (Integer) productsTable.getValueAt(selectedRow, selectedColumn);
                System.out.println("Selected2: " + selected2);
            }
        });
    }
    public void addCreateListener(ActionListener e){
        createBtn.addActionListener(e);
    }

    public class SelectListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int cant = Integer.parseInt(getUserInput(getCantTxt()));
            System.out.println("Cant: " + cant);
            //Order or = new Order(selected1, selected2, cant);
            //Order o = orderDAO.insertObject(or);
            Order o = orderBLL.insertClient(selected1, selected2, cant);
            if(o != null){
               // msg.setText("Quantity is to large");
                JOptionPane.showMessageDialog(null, "Quantity is to large!");
            } else JOptionPane.showMessageDialog(null, "The order has been placed!");
        }
    }
    class backListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Controller c =  new Controller();
        }
    }
    public String getUserInput(JTextField text){
        return text.getText();
    }

    public JTextField getCantTxt() {
        return cantTxt;
    }

    public void setCantTxt(JTextField cantTxt) {
        this.cantTxt = cantTxt;
    }

    public static void main(String args[]){
        JFrame frame = new OrderView();
    }

}
