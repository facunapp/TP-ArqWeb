import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreadorEsquema {

    public static void crearTablas(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (" +
                    "idCliente INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(500) NOT NULL, " +
                    "email VARCHAR(150) NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS producto (" +
                    "idProducto INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(45) NOT NULL, " +
                    "valor FLOAT NOT NULL)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS factura (" +
                    "idFactura INT AUTO_INCREMENT PRIMARY KEY, " +
                    "idCliente INT NOT NULL, " +
                    "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS factura_producto (" +
                    "idFactura INT NOT NULL, " +
                    "idProducto INT NOT NULL, " +
                    "cantidad INT NOT NULL, " +
                    "PRIMARY KEY (idFactura, idProducto), " +
                    "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), " +
                    "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))");
        }
    }
}
