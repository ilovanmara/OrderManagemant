package DataAccess;

import Model.Bill;
import Model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.ConnectionFactory;

public class OrderDAO extends AbstractDAO<Order>{

        public String updateQueryProd(){
            String constr = "UPDATE product SET productsNum = productsNum - ? WHERE idProduct = ?";
            return constr;
        }

        public String priceQuery(){
            String q = "SELECT price FROM product WHERE idProduct = ?";
            return q;
        }

        public String nrProdQuery(){
             String q = "SELECT productsNum FROM product WHERE idProduct = ?";
             return q;
        }
        public Order insertObject(Order order){
            Connection connection = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
            try {
                int clientId = order.getIdClient();
                int productId = order.getIdProduct();
                int quantity = order.getQuantity();
                connection = ConnectionFactory.getConnection();

                String priceQuery = priceQuery();
                PreparedStatement priceStatement = connection.prepareStatement(priceQuery);
                priceStatement.setInt(1, productId);
                rs = priceStatement.executeQuery();
                if (rs.next()) {
                    int price = rs.getInt("price");

                    String nrProdQuery = nrProdQuery();
                    PreparedStatement Statement3 = connection.prepareStatement(nrProdQuery);
                    Statement3.setInt(1, productId);
                    rs2 = Statement3.executeQuery();
                    if (rs2.next()) {
                        int productsNum = rs2.getInt("productsNum");
                        if(productsNum < quantity){
                            return order;
                        }

                        String updateQuery = updateQueryProd();
                        PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                        updateStatement.setInt(1, quantity);
                        updateStatement.setInt(2, productId);
                        updateStatement.executeUpdate();

                        String insertQuery = insertQuery(order);
                        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                        insertStatement.setInt(1, clientId);
                        insertStatement.setInt(2, productId);
                        insertStatement.setInt(3, quantity);
                        insertStatement.setInt(4, price * quantity);
                        insertStatement.executeUpdate();

                        int id = 1;
                        String insertQuery2 = "INSERT INTO bill (idProduct, idClient, price) VALUES (?, ?, ?)";
                        PreparedStatement insertStatement2 = connection.prepareStatement(insertQuery2);
                        insertStatement2.setInt(1, productId);
                        insertStatement2.setInt(2, clientId);
                        insertStatement2.setInt(3, price);
                        insertStatement2.executeUpdate();

                        insertStatement.close();
                        insertStatement2.close();
                        updateStatement.close();
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
}
