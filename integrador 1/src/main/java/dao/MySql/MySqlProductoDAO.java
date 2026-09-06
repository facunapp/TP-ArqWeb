package dao.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
   	@Override
	public Producto obtenerProductoMayorRecaudacion() {

	    String sql = "SELECT p.idProducto, p.nombre, p.valor " +
		         "FROM producto p " +
		         "JOIN factura_producto fp ON p.idProducto = fp.idProducto " +
		         "GROUP BY p.idProducto, p.nombre, p.valor " +
		         "ORDER BY SUM(fp.cantidad) * p.valor DESC " +
		         "LIMIT 1";

	    try (PreparedStatement ps = conn.prepareStatement(sql);
		 ResultSet rs = ps.executeQuery()) {

		if (rs.next()) {

		    int idProducto = rs.getInt("idProducto");
		    String nombre = rs.getString("nombre");
		    float valor = rs.getFloat("valor");

		    return new Producto(idProducto, nombre, valor);
		}

	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	    return null;
	}

}
