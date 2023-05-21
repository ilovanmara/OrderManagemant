package BusinessLogic;

import DataAccess.ClientDAO;
import Model.Client;

import java.util.NoSuchElementException;

/**
 * Clasa ClientBLL implementeaza logica operatiilor pe client
 */
public class ClientBLL {
    private ClientDAO clientDAO;
    public ClientBLL(){
        clientDAO = new ClientDAO();
    }

    /**
     * gasete un client dupa id
     * @param id
     * @return
     */
    public Client findClientById(int id){
        Client cl = clientDAO.findById(id, "idClient");
        if(cl == null){
            throw new NoSuchElementException("The student with id = " + id + " was not found");
        }
        return cl;
    }

    /**
     * editeaza datele unui client
     * @param name
     * @param email
     * @param address
     * @param id
     */
    public void updateClient(String name, String email, String address, int id){
        Client cl = new Client(name, email, address);
        clientDAO.updateObject(cl, "idClient", id);
    }

    /**
     * insereaza un nou client
     * @param name
     * @param email
     * @param address
     */
    public void insertClient(String name, String email, String address){
        Client cl =  new Client (name, email, address);
        clientDAO.insertObject(cl);
    }

    /**
     * sterge un client dupa id
     * @param id
     */
    public void deleteClient(int id){
        clientDAO.deleteObject(id, "idClient");
    }
}
