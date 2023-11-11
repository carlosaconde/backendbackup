package iMaquinate20;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CambioTipoDeDato {

    public static void main(String[] args) {
        // Paso 1: Establece la conexión a la base de datos
        String url = "jdbc:mysql://db.ctd.academy:3306/1023c02_GRUPO1";
        String usuario = "1023c02_GRUPO1";
        String contraseña = "nie4Baif";

        try {
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Paso 2: Crea la sentencia SQL para modificar el tipo de dato de la columna
            String sql = "ALTER TABLE products MODIFY image_Url VARBINARY(4000)";

            // Paso 3: Crea una instancia de Statement y ejecuta la sentencia SQL
            try {
                Statement statement = conexion.createStatement();
                statement.executeUpdate(sql);
                System.out.println("La columna se ha modificado con éxito.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error al ejecutar la sentencia SQL.");
            }

            // Paso 4: Cierra la conexión a la base de datos
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error al cerrar la conexión a la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al establecer la conexión a la base de datos.");
        }
    }
}