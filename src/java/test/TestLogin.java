package test;

import servicio.UsuarioServicio;

public class TestLogin {

    public static void main(String[] args) {
        UsuarioServicio s = new UsuarioServicio();
        System.out.println("Hash de admin1234 = " + s.testHash("admin1234"));

    }
}
