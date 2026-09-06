import dao.MySql.MySqlJDBCDAOFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = MySqlJDBCDAOFactory.createConnection()) {
            // creacion del esquema
            CreadorEsquema.crearTablas(conn);
            System.out.println("Esquema verificado o creado con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
