/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Conexion;

/**
 * DAO para operaciones con cuentas bancarias (depósitos, consultas, etc.)
 */
public class CuentaDAO {

    /**
     * Realiza un depósito en una cuenta
     * @param nroCuenta número de cuenta destino
     * @param monto monto a depositar
     * @return true si se actualizó el saldo correctamente
     */
    public boolean depositar(String nroCuenta, double monto) {
        String sql = "UPDATE cuentas SET Saldo = Saldo + ? WHERE NroCta = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, monto);
            ps.setString(2, nroCuenta);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene el saldo actual de una cuenta
     */
    public double obtenerSaldo(String nroCuenta) {
        String sql = "SELECT Saldo FROM cuentas WHERE NroCta = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nroCuenta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("Saldo");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // error
    }

    /**
     * Verifica si existe una cuenta con ese número
     */
    public boolean existeCuenta(String nroCuenta) {
        String sql = "SELECT 1 FROM cuentas WHERE NroCta = ?";
        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nroCuenta);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double obtenerSaldo(String numeroCuenta, String tipoCuenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean depositar(String numeroCuenta, String tipoCuenta, double monto, String moneda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
