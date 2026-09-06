import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import dao.ClienteDAO;
import dao.DAOFactory;
import dao.FacturaDAO;
import dao.FacturaProductoDAO;
import dao.ProductoDAO;

import entidades.Producto;
import entidades.Cliente;
import entidades.Factura;
import entidades.FacturaProducto;

public class CargarDatos {

    public static void main(String[] args) {

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

        ProductoDAO productoDAO = factory.getProductoDAO();
        ClienteDAO clienteDAO = factory.getClienteDAO();
        FacturaDAO facturaDAO = factory.getFacturaDAO();
        FacturaProductoDAO facturaProductoDAO = factory.getFacturaProductoDAO();

        try {

            CSVParser parserProductos = CSVFormat.DEFAULT
                    .withHeader()
                    .parse(new FileReader("productos.csv"));

            for (CSVRecord row : parserProductos) {

                int idProducto = Integer.parseInt(row.get("idProducto"));
                String nombre = row.get("nombre");
                float valor = Float.parseFloat(row.get("valor"));

                Producto producto = new Producto(idProducto, nombre, valor);

                productoDAO.insertar(producto);
            }

            parserProductos.close();

            System.out.println("Productos cargados correctamente.");


            CSVParser parserClientes = CSVFormat.DEFAULT
                    .withHeader()
                    .parse(new FileReader("clientes.csv"));

            for (CSVRecord row : parserClientes) {

                int idCliente = Integer.parseInt(row.get("idCliente"));
                String nombre = row.get("nombre");
                String email = row.get("email");

                Cliente cliente = new Cliente(idCliente, nombre, email);

                clienteDAO.insertar(cliente);
            }

            parserClientes.close();

            System.out.println("Clientes cargados correctamente.");


            CSVParser parserFacturas = CSVFormat.DEFAULT
                    .withHeader()
                    .parse(new FileReader("facturas.csv"));

            for (CSVRecord row : parserFacturas) {

                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idCliente = Integer.parseInt(row.get("idCliente"));

                Factura factura = new Factura(idFactura, idCliente);

                facturaDAO.insertar(factura);
            }

            parserFacturas.close();

            System.out.println("Facturas cargadas correctamente.");


            CSVParser parserFacturaProducto = CSVFormat.DEFAULT
                    .withHeader()
                    .parse(new FileReader("facturas-productos.csv"));

            for (CSVRecord row : parserFacturaProducto) {

                int idFactura = Integer.parseInt(row.get("idFactura"));
                int idProducto = Integer.parseInt(row.get("idProducto"));
                int cantidad = Integer.parseInt(row.get("cantidad"));

                FacturaProducto facturaProducto =
                        new FacturaProducto(idFactura, idProducto, cantidad);

                facturaProductoDAO.insertar(facturaProducto);
            }

            parserFacturaProducto.close();

            System.out.println("Facturas-productos cargadas correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}