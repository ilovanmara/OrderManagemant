package Model;

/**
 * Clasa Order contine informatiile specifice ale unei comenzi
 */
public class Order {
    private int idClient;
    private int idProduct;
    private int quantity;
    private int price;
    private int idOrder;
    Order(){

    }

    Order(int idOrder, int idClient, int idProduct, int number){
        this.idClient = idClient;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = number;
    }

    public Order(int idClient, int idProduct, int number){
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity = number;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setNameClient(String nameClient) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
