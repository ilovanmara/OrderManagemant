package Model;

/**
 * Clasa Product contine informatii despre un prosus
 */
public class Product {
    private String productName;
    private int price;
    private int productsNum;
    private int idProduct;
    Product(){

    }
    public Product(String productName, int price, int productsNum){
        this.productName = productName;
        this.price = price;
        this.productsNum = productsNum;
    }

    Product(int idProduct, String nameProduct, int price, int products){
        this.idProduct = idProduct;
        this.productName = nameProduct;
        this.price = price;
        this.productsNum = products;
    }



    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String nameProduct) {
        this.productName = nameProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductsNum() {
        return productsNum;
    }

    public void setProductsNum(int productsNum) {
        this.productsNum = productsNum;
    }
}
