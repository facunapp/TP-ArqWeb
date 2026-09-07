package dao.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import entidades.Cliente;


public class MySqlClienteDAO implements ClienteDAO {

    private Connection conn;

    public MySqlClienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> obtenerClientesOrdenadosPorFacturacion() {

        String sql = "SELECT c.idCliente, c.nombre, c.email " +
                     "FROM cliente c " +
                     "JOIN factura f ON c.idCliente = f.idCliente " +
                     "JOIN factura_producto fp ON f.idFactura = fp.idFactura " +
                     "JOIN producto p ON fp.idProducto = p.idProducto " +
                     "GROUP BY c.idCliente, c.nombre, c.email " +
                     "ORDER BY SUM(fp.cantidad * p.valor) DESC";

        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int idCliente = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");

                clientes.add(new Cliente(idCliente, nombre, email));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

}
