package DataAccess;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * Clasa AbstractDAO ofera metode constrite folosind reflection
 * @param <T>
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * select pentru un field dat
     * @param field
     * @return
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        System.out.println(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * interogare
     * @return
     */
    public String findAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * aceasta este o funtie care ne da ca rezultat toate obiectele din tabel
     * @return
     */
    public List<T> findAll() {
        // TODO:
        Connection dbConnection = ConnectionFactory.getConnection();
        List<T> list = new ArrayList<T>();
        PreparedStatement findAllStatement = null;
        ResultSet rs = null;
        int insertedId = -1;
        String query = findAllQuery();
        try {
            dbConnection = ConnectionFactory.getConnection();
            findAllStatement = dbConnection.prepareStatement(query);
            rs = findAllStatement.executeQuery();
            list = createObjects(rs);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO: findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findAllStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

    /**
     * construim un tabel coare contine toate datele din baza de date
     * @param list
     * @return
     */
    public JTable createTable(List<T> list) {
        JTable table;
        Class<?> objectType = list.get(0).getClass();
        Field[] fields = objectType.getDeclaredFields();
        Vector columnNames = new Vector(fields.length);

        for (int i = 0; i < fields.length; i++) {
            columnNames.addElement(fields[i].getName());
        }
        Vector<Vector<Object>> data = new Vector<>();
        for (T object : list) {
            Vector<Object> rowData = new Vector(fields.length);
            for (int i = 0; i < fields.length; i++) {
                try {
                    fields[i].setAccessible(true);
                    rowData.addElement(fields[i].get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            data.addElement(rowData);
        }
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, renderer);
        return table;
    }

    /**
     * cautam un obiect dupa id
     * @param id
     * @param f
     * @return
     */
    public T findById(int id, String f) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(f);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * construim un obiect nou folosid refection
     * @param resultSet
     * @return
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
       // if(ctors.length > 0) {
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }
       // }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
               T instance; //= (T) ctor.newInstance();
                if (ctor.getParameterCount() == 0)
                    instance = (T)ctor.newInstance();
                else if (ctor.getParameterCount() == 2)
                    instance = (T)ctor.newInstance( "altceva",2);
                else
                    instance = (T)ctor.newInstance( "altceva", 2, 3);
                //else
                   // instance = (T)ctor.newInstance( "altceva", 2, 3, 4);

                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * construim un string
     * string-ul reprezinta o interogare care insereaza un obiect in baza de date
     * @param t
     * @return
     */
    public String insertQuery(T t) {
        // TODO:
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `");
        sb.append(type.getSimpleName() + "` ( ");
        int num = type.getDeclaredFields().length - 1;
        int i = 0;
        for (Field f : type.getDeclaredFields()) {
            if (i == type.getDeclaredFields().length - 1)
                break;
            if (i == type.getDeclaredFields().length - 2) {
                sb.append(f.getName() + ") ");
            } else sb.append(f.getName() + ", ");
            i++;
        }
        sb.append(" VALUES ( ");
        i = 0;
        for (Field f : type.getDeclaredFields()) {
            if (i == type.getDeclaredFields().length - 1)
                break;
            if (i == type.getDeclaredFields().length - 2) {
                sb.append(" ");
            } else sb.append(" ?, ");
            i++;
        }
        sb.append("? ) ");
        return sb.toString();
    }

    public String updateQuery(T t, String field, int id) {
        // TODO:
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        int i = 0;
        for (Field f : type.getDeclaredFields()) {
            if (i == type.getDeclaredFields().length - 1)
                break;
            if (i == type.getDeclaredFields().length - 2) {
                sb.append(f.getName() + " = ?");
            } else sb.append(f.getName() + " = ?, ");
            i++;
        }
        sb.append(" WHERE " + field + " = " + id);
        System.out.println(sb);
        return sb.toString();
    }

    /**
     * acseasta metoda updateaza un obiect in baza de date
     * @param t
     * @param field
     * @param id
     * @return
     */
    public T updateObject(T t, String field, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = updateQuery(t, field, id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Field f : type.getDeclaredFields()) {
                if (parameterIndex == 4) {
                    break;
                }
                f.setAccessible(true);
                Object value = f.get(t);
                statement.setObject(parameterIndex, value);
                parameterIndex++;
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * aceasta metoda insereaza un obiect in baza de date
     * @param t
     * @return
     */
    public T insertObject(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = insertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            int parameterIndex = 1;
            for (Field f : type.getDeclaredFields()) {
                f.setAccessible(true);
                Object value = f.get(t);
                statement.setObject(parameterIndex++, value);
                if (parameterIndex == 4)
                    break;
            }

            statement.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * interogare pentru stergerea unui element
     * @param field
     * @return
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * metoda pentru stergerea unui obiect
     * @param id
     * @param f
     * @return
     */
    public T deleteObject(int id, String f) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(f);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
