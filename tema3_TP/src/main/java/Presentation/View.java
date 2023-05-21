package Presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame{
    Border border = BorderFactory.createLineBorder(Color.white);
    private JPanel panel = new JPanel();
    private JButton clientBtn = new JButton ("Client");
    private JButton productBtn = new JButton("Product");
    private JButton orderBtn = new JButton("Order");

    View(){
        this.setName("Client");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);

        panel.setLayout(null);
        panel.setBackground(new Color(201, 225, 198));
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        addBtn();

        this.add(panel, BorderLayout.CENTER);
        this.setContentPane(panel);
        this.setVisible(true);
    }
    private void createButton(JButton button){
        Color buttonColor = new Color(148, 106, 106);
        button.setFont(new Font("Century",Font.CENTER_BASELINE,30));
        button.setForeground(Color.white);
        button.setBackground(buttonColor);
        button.setBorder(border);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public void addBtn(){
        panel.add(clientBtn);
        createButton(clientBtn);
        clientBtn.setBounds(120, 80, 150, 40);
        //addBtn.addActionListener(new ClientView.AddListener());
        panel.add(clientBtn);

        panel.add(productBtn);
        createButton(productBtn);
        productBtn.setBounds(120, 140, 150, 40);
        //addBtn.addActionListener(new ClientView.AddListener());
        panel.add(productBtn);

        panel.add(orderBtn);
        createButton(orderBtn);
        orderBtn.setBounds(120, 200, 150, 40);
        //addBtn.addActionListener(new ClientView.AddListener());
        panel.add(orderBtn);

    }
    public void clientAddListener(ActionListener e){
        clientBtn.addActionListener(e);
    }
    public void productAddListener(ActionListener e){
        productBtn.addActionListener(e);
    }
    public void orderAddListener(ActionListener e){
        orderBtn.addActionListener(e);
    }

}
