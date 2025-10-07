package negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DAO encargado de interactuar con la base de datos para operaciones de Usuario
 */
public class UsuarioDAO {

    // Método para obtener conexión con MySQL (XAMPP)
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el driver JDBC
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/proyectobanco?useSSL=false&serverTimezone=UTC",
                "root", // usuario de MySQL en XAMPP
                "" // contraseña (vacía por defecto en XAMPP)
        );
    }

    /**
     * Login de usuario: busca en la base por usuario + password encriptado
     */
    public Usuario login(String usuario, String passwordHash) {
        try (Connection con = getConnection()) {
            String sql = "SELECT * FROM usuarios WHERE usuario=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, passwordHash);

            // 🚨 Depuración
            System.out.println("🔎 Ejecutando SQL: " + sql);
            System.out.println("   Usuario buscado: [" + usuario + "]");
            System.out.println("   Hash buscado:   [" + passwordHash + "]");
            System.out.println("   PS completo:    [" + ps.toString() + "]");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setDni(rs.getString("dni"));
                u.setNombres(rs.getString("nombres"));
                u.setUsuario(rs.getString("usuario"));
                u.setEmail(rs.getString("email"));

                System.out.println("✅ Login exitoso para usuario: " + u.getUsuario());
                return u;
            } else {
                System.out.println("⚠️ No se encontró el usuario con esas credenciales.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error en login(): " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Registrar un nuevo usuario en la base
     */
    public boolean registrar(Usuario u) {
        try (Connection con = getConnection()) {
            String sql = "INSERT INTO usuarios(dni, nombres, usuario, password, email) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, u.getDni());
            ps.setString(2, u.getNombres());
            ps.setString(3, u.getUsuario());
            ps.setString(4, u.getPassword()); // ya viene encriptada desde UsuarioServicio
            ps.setString(5, u.getEmail());

            System.out.println("🔎 Registrando usuario con SQL: " + sql);
            System.out.println("   Usuario:  [" + u.getUsuario() + "]");
            System.out.println("   Password: [" + u.getPassword() + "] (hash)");
            System.out.println("   Email:    [" + u.getEmail() + "]");

            ps.executeUpdate();
            System.out.println("✅ Usuario registrado correctamente: " + u.getUsuario());
            return true;
        } catch (Exception e) {
            System.out.println("❌ Error en registrar(): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
