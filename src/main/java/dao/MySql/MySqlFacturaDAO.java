package dao.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.FacturaDAO;
import entidades.Factura;


public class MySqlFacturaDAO implements FacturaDAO {
    private Connection conn;

    public MySqlFacturaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Factura factura) {
        String sql = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdCliente());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
