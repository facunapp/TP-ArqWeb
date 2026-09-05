package dao.MySql;

import dao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJDBCDAOFactory extends DAOFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URI = "jdbc:mysql://localhost:3306/integrador_db";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URI, USER, PASS);
    }

    @Override
    public ClienteDAO getClienteDAO() {

        try {
            return new MySqlClienteDAO(createConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProductoDAO getProductoDAO() {

        try{
            return new MySqlProductoDAO(createConnection());
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FacturaDAO getFacturaDAO() {

        try{
            return new MySqlFacturaDAO(createConnection());
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public FacturaProductoDAO getFacturaProductoDAO() {

        try{
            return new MySqlFacturaProductoDAO(createConnection());
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}