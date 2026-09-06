import dao.DAOFactory;
import dao.ProductoDAO;
import entidades.Producto;

public class ProductoMayorRecaudacion {

    public static void main(String[] args) {

        DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

        ProductoDAO productoDAO = factory.getProductoDAO();

        Producto producto = productoDAO.obtenerProductoMayorRecaudacion();

        System.out.println("Producto que más recaudó:");
        System.out.println("ID: " + producto.getIdProducto());
        System.out.println("Nombre: " + producto.getNombre());
        System.out.println("Valor: " + producto.getValor());
    }
}
