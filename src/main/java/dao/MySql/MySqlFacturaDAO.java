package dao.MySql;

import dao.FacturaDAO;

import entidades.Factura;

import java.sql.Connection;


public class MySqlFacturaDAO implements FacturaDAO {
    private Connection conn;

    public MySqlFacturaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Factura factura) {

    }
}
