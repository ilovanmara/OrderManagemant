package BusinessLogic;

import DataAccess.OrderDAO;
import Model.Client;
import Model.Order;

public class OrderBLL {
    private OrderDAO orderDAO = new OrderDAO();

    public Order insertClient(int selected1, int selected2, int cant){
        Order or = new Order(selected1, selected2, cant);
        Order o = orderDAO.insertObject(or);
        return o;
    }
}
