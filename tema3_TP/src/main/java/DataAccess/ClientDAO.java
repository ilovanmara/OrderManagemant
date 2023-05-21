package DataAccess;

import Connection.ConnectionFactory;
import Model.Client;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client>{
    /*
    public Client insertObject(Client c) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = insertQuery(c);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int parameterIndex = 1;
            statement.setString(parameterIndex++, c.getNameClient());
            statement.setString(parameterIndex++, c.getEmail());
            statement.setString(parameterIndex++, c.getAddress());

            statement.executeUpdate();
        } catch (SQLException e) {
            //LOGGER.log(Level.WARNING, c.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
     */
}
