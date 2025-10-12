/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.sql.Date;

/**
 *
 * @author Marcelo
 */
public class Cuenta {

    private String nroCta;
    private String tipoCta;
    private String moneda;
    private double saldo;
    private Date fechaApertura;
    private String estado;

    // Getters y setters
    public String getNroCta() {
        return nroCta;
    }

    public void setNroCta(String nroCta) {
        this.nroCta = nroCta;
    }

    public String getTipoCta() {
        return tipoCta;
    }

    public void setTipoCta(String tipoCta) {
        this.tipoCta = tipoCta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getTipoDescripcion() {
    if (tipoCta == null) return "";
    switch (tipoCta) {
        case "AH":
            return "Cuenta de ahorros";
        case "CO":
            return "Cuenta corriente";
        case "PF":
            return "Plazo fijo";
        default:
            return tipoCta; // por si en el futuro aparece otro tipo
    }
}
}
