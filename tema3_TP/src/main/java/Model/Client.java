package Model;

/**
 * Clasa Client conine informatiile specifice unui client
 */
public class Client {
    private String nameClient;
    private String email;
    private String address;
    private int idClient;
    Client(){

    }
    Client (int id, String name, String email, String address){
        this.idClient = id;
        this.nameClient = name;
        this.email = email;
        this.address = address;
    }
    public Client(String name, String email, String address){
        this.nameClient = name;
        this.email = email;
        this.address = address;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
