package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/proyectobanco";
    private static final String USER = "root";      // tu usuario MySQL
    private static final String PASS = "";          // tu contraseña si tiene

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Conexión exitosa a MySQL, todo good");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
        return con;
    }
}
