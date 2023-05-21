package BusinessLogic;

import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

import java.util.NoSuchElementException;
/**
 * Clasa ProductBLL implementeaza logica operatiilor pe produs
 */
public class ProductBLL {
    private ProductDAO productDAO;
    public ProductBLL(){
        productDAO = new ProductDAO();
    }

    /**
     * gasete un produs dupa id
     * @param id
     * @return
     */
    public Product findProductById(int id){
        Product prod = productDAO.findById(id, "idProduct");
        if(prod == null){
            throw new NoSuchElementException("The product with id = " + id + " was not found");
        }
        return prod;
    }

    /**
     * editeaza datele unui produs
     * @param name
     * @param price
     * @param total
     * @param id
     */
    public void updateProduct(String name, int price, int total, int id){
        Product prod = new Product(name, price, total);
        productDAO.updateObject(prod, "idProduct", id);
    }

    /**
     * insereaza un nou produs
     * @param name
     * @param price
     * @param total
     */
    public void insertProduct(String name, int price, int total){
        Product prod =  new Product (name, price, total);
        productDAO.insertObject(prod);
    }

    /**
     * sterge un produs cu un id dat
     * @param id
     */
    public void deleteProduct(int id){
        productDAO.deleteObject(id, "idProduct");
    }
}
