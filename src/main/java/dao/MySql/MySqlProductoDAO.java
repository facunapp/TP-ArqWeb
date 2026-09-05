package dao.MySql;

import dao.ProductoDAO;
import entidades.Producto;

import java.sql.Connection;


public class MySqlProductoDAO implements ProductoDAO {

    private Connection conn;

    public MySqlProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Producto producto) {

    }

}
