package dao.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ProductoDAO;
import entidades.Producto;


public class MySqlProductoDAO implements ProductoDAO {

    private Connection conn;

    public MySqlProductoDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Producto producto) {
        String sql = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getValor());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
