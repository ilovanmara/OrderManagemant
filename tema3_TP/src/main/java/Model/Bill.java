package Model;

/**
 * Clasa imutabila constrita folosind Java Records.
 * Aceasta clasa contine doar gettere, nu si settere
 * @param idProduct
 * @param idClient
 * @param price
 * @param idBill
 */
public record Bill (int idProduct, int idClient, int price, int idBill){

    @Override
    public int idProduct() {
        return idProduct;
    }

    @Override
    public int idClient() {
        return idClient;
    }

    @Override
    public int price() {
        return price;
    }

    @Override
    public int idBill() {
        return idBill;
    }
}
