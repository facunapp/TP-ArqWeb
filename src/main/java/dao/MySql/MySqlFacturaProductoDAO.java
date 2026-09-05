package dao.MySql;

import dao.FacturaProductoDAO;
import entidades.FacturaProducto;

import java.sql.Connection;

public class MySqlFacturaProductoDAO implements FacturaProductoDAO {

    private Connection conn;

    public MySqlFacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(FacturaProducto facturaProducto) {

    }
}
