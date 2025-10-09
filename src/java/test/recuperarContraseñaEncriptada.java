// TestHash.java
package test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Scanner;

public class recuperarContraseñaEncriptada {

    // Genera SHA-256 en hexadecimal (minúsculas)
    public static String sha256(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(2 * digest.length);
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Uso desde línea de comandos
    public static void main(String[] args) {
        if (args.length >= 1) {
            String mode = args[0].toLowerCase();
            if ("hash".equals(mode) && args.length == 2) {
                String texto = args[1];
                System.out.println("Texto: " + texto);
                System.out.println("SHA-256: " + sha256(texto));
                return;
            } else if ("check".equals(mode) && args.length == 3) {
                String hash = args[1];
                String texto = args[2];
                String generado = sha256(texto);
                System.out.println("Hash esperado: " + hash);
                System.out.println("Hash generado: " + generado);
                System.out.println("Coincide: " + generado.equalsIgnoreCase(hash));
                return;
            } else if ("interactive".equals(mode)) {
                Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
                System.out.print("Introduce el hash (hex): ");
                String hash = sc.nextLine().trim();
                System.out.print("Introduce el texto a probar: ");
                String texto = sc.nextLine();
                String generado = sha256(texto);
                System.out.println("Hash generado: " + generado);
                System.out.println("Coincide: " + generado.equalsIgnoreCase(hash));
                sc.close();
                return;
            }
        }

        // Ayuda si no se pasó argumento correcto
        System.out.println("Uso:");
        System.out.println("  java test.TestHash hash <texto>        -> imprime SHA-256 del texto");
        System.out.println("  java test.TestHash check <hash> <texto> -> compara hash con texto");
        System.out.println("  java test.TestHash interactive         -> modo interactivo");
    }
}
