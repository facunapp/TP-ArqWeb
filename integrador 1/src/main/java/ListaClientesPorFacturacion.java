import dao.ClienteDAO;
import dao.DAOFactory;
import entidades.Cliente;

import java.util.List;

public class ListaClientesPorFacturacion {

    public static void main(String[] args) {

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

        ClienteDAO clienteDAO = factory.getClienteDAO();

        List<Cliente> clientes = clienteDAO.obtenerClientesOrdenadosPorFacturacion();

        System.out.println("Clientes ordenados por mayor facturacion:");
        System.out.println("------------------------------------------");

        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente() +
                               " | Nombre: " + cliente.getNombre() +
                               " | Email: " + cliente.getEmail());
        }
    }
}
