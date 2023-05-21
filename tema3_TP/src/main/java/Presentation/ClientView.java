package Presentation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ClientView extends JFrame{
    Border border = BorderFactory.createLineBorder(Color.white);
    JPanel panel = new JPanel();
    private JLabel title;
    private JButton addBtn = new JButton("Add new client");
    private JButton editBtn = new JButton("Edit client");
    private JButton deleteBtn = new JButton("Delete client");
    private JButton viewBtn = new JButton("View all clients");
    private JLabel nameLbl = new JLabel("Name:");
    private JLabel name2Lbl = new JLabel("Name:");
    private JLabel name3Lbl = new JLabel("Name:");
    private JLabel emailLbl = new JLabel("Email:");
    private JLabel addressLbl = new JLabel("Address:");
    private JTextField nameTxt = new JTextField(10);
    private JTextField name2Txt = new JTextField(10);
    private JTextField name3Txt = new JTextField(10);
    private JTextField emailTxt = new JTextField(10);
    private JTextField addressTxt = new JTextField(10);
    private JTextField msg = new JTextField(10);
    private JButton ok1Btn = new JButton("OK");
    private JButton ok2Btn = new JButton("OK");
    private JButton ok3Btn = new JButton("OK");
    private JButton searchBtn = new JButton("Search");
    private JButton search2Btn = new JButton("Search");
    private JButton backBtn = new JButton("Back");
    public JTable table;
    public ClientView(){
        this.setName("Client");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(680, 700);
        this.setLocationRelativeTo(null);

        title = new JLabel("Client");
        title.setBounds(290, 10, 400, 50);
        title.setFont(new Font("Georgia",Font.BOLD,30));
        panel.setLayout(null);
        panel.setBackground(new Color(201, 225, 198));
        panel.add(title);
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        addBtn();

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
        panel.add(addBtn);
        createButton(addBtn);
        addBtn.setBounds(230, 100, 200, 30);
        addBtn.addActionListener(new AddListener());
        panel.add(editBtn);
        createButton(editBtn);
        editBtn.setBounds(230, 140, 200, 30);
        editBtn.addActionListener(new EditListener());
        panel.add(deleteBtn);
        createButton(deleteBtn);
        deleteBtn.setBounds(230, 180, 200, 30);
        //deleteBtn.addActionListener(new DeleteListener());
        panel.add(viewBtn);
        createButton(viewBtn);
        viewBtn.setBounds(230, 220, 200, 30);

        panel.add(backBtn);
        createButton(backBtn);
        backBtn.setBounds(500, 600, 150, 30);
        panel.add(backBtn);
    }

    public void insertPanelAdd(){
        nameLbl.setBounds(120, 310, 200, 25);
        nameLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        emailLbl.setBounds(120, 340, 200, 25);
        emailLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        addressLbl.setBounds(120, 370, 200, 25);
        addressLbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));

        nameTxt.setBounds(220, 310, 300, 25);
        emailTxt.setBounds(220, 340, 300, 25);
        addressTxt.setBounds(220, 370, 300, 25);
    }
    public void insertPanelEdit(){
        name2Lbl.setBounds(80, 550, 200, 25);
        name2Lbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        name2Txt.setBounds(150, 550, 300, 25);
    }

    public void insertPanelDelete(){
        name3Lbl.setBounds(80, 550, 200, 25);
        name3Lbl.setFont(new Font("Century",Font.CENTER_BASELINE,16));
        name3Txt = new JTextField(10);
        name3Txt.setBounds(150, 550, 300, 25);
        msg = new JTextField(10);
        msg.setBounds(150, 450, 300, 25);
    }

    public void addAddListener(ActionListener e){
        addBtn.addActionListener(e);
    }
    public void editAddListener(ActionListener e){
        editBtn.addActionListener(e);
    }
    public void deleteAddListener(ActionListener e){
        deleteBtn.addActionListener(e);
    }

    public void viewAddListener(ActionListener e){
        viewBtn.addActionListener(e);
    }
    public void backAddListener(ActionListener e){
        backBtn.addActionListener(e);
    }

    public void ok1AddListener(ActionListener e){
        ok1Btn.addActionListener(e);
    }
    public void ok2AddListener(ActionListener e){
        ok2Btn.addActionListener(e);
    }
    public void ok3AddListener(ActionListener e){
        ok3Btn.addActionListener(e);
    }
    public void searchAddListener(ActionListener e){
        searchBtn.addActionListener(e);
    }
    public void setSearch2AddListener(ActionListener e){
        search2Btn.addActionListener(e);
    }

    class AddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            insertPanelAdd();
            createButton(ok1Btn);
            ok1Btn.setBounds(270, 410, 120, 30);
            panel.add(ok1Btn);
            panel.add(nameLbl);
            panel.add(nameTxt);
            panel.add(emailLbl);
            panel.add(emailTxt);
            panel.add(addressLbl);
            panel.add(addressTxt);
        }
    }

    class EditListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            insertPanelAdd();
            insertPanelEdit();
            createButton(ok2Btn);
            ok2Btn.setBounds(270, 410, 120, 30);
            createButton(searchBtn);
            searchBtn.setBounds(460, 547, 120, 30);
            panel.add(name2Lbl);
            panel.add(name2Txt);
            panel.add(searchBtn);
            panel.add(ok2Btn);
            panel.add(nameLbl);
            panel.add(nameTxt);
            panel.add(emailLbl);
            panel.add(emailTxt);
            panel.add(addressLbl);
            panel.add(addressTxt);
        }
    }
/*
    class DeleteListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            insertPanelDelete();
            createButton(search2Btn);
            search2Btn.setBounds(460, 547, 120, 30);
            panel.add(name3Lbl);
            panel.add(name3Txt);
            panel.add(search2Btn);
            panel.add(msg);
        }
    }

 */

    public JTextField getNameTxt() {
        return nameTxt;
    }

    public void setNameTxt(JTextField nameTxt) {
        this.nameTxt = nameTxt;
    }

    public JTextField getName2Txt() {
        return name2Txt;
    }

    public void setName2Txt(JTextField name2Txt) {
        this.name2Txt = name2Txt;
    }

    public JTextField getName3Txt() {
        return name3Txt;
    }

    public void setName3Txt(JTextField name3Txt) {
        this.name3Txt = name3Txt;
    }

    public JTextField getEmailTxt() {
        return emailTxt;
    }

    public void setEmailTxt(JTextField emailTxt) {
        this.emailTxt = emailTxt;
    }

    public JTextField getAddressTxt() {
        return addressTxt;
    }

    public void setAddressTxt(JTextField addressTxt) {
        this.addressTxt = addressTxt;
    }

    public JTextField getMsg() {
        return msg;
    }

    public void setMsg(JTextField msg) {
        this.msg = msg;
    }
    public String getUserInput(JTextField text){
        return text.getText();
    }


}
