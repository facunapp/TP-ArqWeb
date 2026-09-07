package dao;

import entidades.Cliente;

import java.util.List;

public interface ClienteDAO {

    void insertar(Cliente cliente);

    List<Cliente> obtenerClientesOrdenadosPorFacturacion();

}

