package dao;

import dao.MySql.MySqlJDBCDAOFactory;

public abstract class DAOFactory {
    public static final int MYSQL_JDBC = 1;

    public abstract ClienteDAO getClienteDAO();
    public abstract ProductoDAO getProductoDAO();
    public abstract FacturaDAO getFacturaDAO();
    public abstract FacturaProductoDAO getFacturaProductoDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL_JDBC : return new MySqlJDBCDAOFactory();
            default: return null;
        }
    }
}
