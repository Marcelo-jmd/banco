package test;

import servicio.UsuarioServicio;

public class TestLogin {

    public static void main(String[] args) {
        UsuarioServicio s = new UsuarioServicio();
        System.out.println("Hash de 87654321 = " + s.testHash("87654321"));

    }
}
