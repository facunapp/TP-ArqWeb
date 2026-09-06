package dao.MySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.ClienteDAO;
import entidades.Cliente;


public class MySqlClienteDAO implements ClienteDAO {

    private Connection conn;

    public MySqlClienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
