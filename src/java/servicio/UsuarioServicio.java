package servicio;

import negocio.Usuario;
import negocio.UsuarioDAO;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Servicio que contiene la lógica de negocio de los usuarios
 */
public class UsuarioServicio {

    private UsuarioDAO dao = new UsuarioDAO();

    /**
     * Método privado que encripta contraseñas usando SHA-256
     */
    private String encriptar(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // Usar StringBuilder para convertir a hexadecimal
            StringBuilder hexString = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registra usuario en la base de datos, encriptando la contraseña antes de
     * guardar
     */
    public boolean registrarUsuario(Usuario u) {
        u.setPassword(encriptar(u.getPassword()));
        return dao.registrar(u);
    }

    /**
     * Realiza login verificando usuario y contraseña encriptada
     */
    public Usuario login(String usuario, String password) {
        String hash = encriptar(password);

        System.out.println("👉 Usuario ingresado: [" + usuario + "]");
        System.out.println("👉 Contraseña en texto: [" + password + "]");
        System.out.println("👉 Hash generado: [" + hash + "]");

        return dao.login(usuario, hash);
    }

    public String testHash(String password) {
        return encriptar(password);
    }

}
