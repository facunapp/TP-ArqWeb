package dao;

import entidades.Cliente;
import entidades.Producto;

import java.util.List;

public interface ProductoDAO {

    void insertar(Producto producto);
    Producto obtenerProductoMayorRecaudacion();

}
