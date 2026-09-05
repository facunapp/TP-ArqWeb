package dao.MySql;

import dao.ClienteDAO;
import entidades.Cliente;

import java.sql.Connection;


public class MySqlClienteDAO implements ClienteDAO {

    private Connection conn;

    public MySqlClienteDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Cliente cliente) {

    }

}
