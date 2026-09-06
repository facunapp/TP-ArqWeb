package dao.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.FacturaProductoDAO;
import entidades.FacturaProducto;

public class MySqlFacturaProductoDAO implements FacturaProductoDAO {

    private Connection conn;

    public MySqlFacturaProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(FacturaProducto facturaProducto) {
        String sql = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, facturaProducto.getIdFactura());
            ps.setInt(2, facturaProducto.getIdProducto());
            ps.setInt(3, facturaProducto.getCantidad());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
