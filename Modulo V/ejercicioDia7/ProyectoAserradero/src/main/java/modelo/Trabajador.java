/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.swing.JOptionPane;

/**
 *
 * @author Althon Vader
 */
public class Trabajador {
    // atributos 

    private int idTrabajador;
    private String rutTrabajador;
    private String nombreTrabajador;
    private String apellidoPater;
    private String apellidoMater;
    private String cargoTrabajador;

    public String getCargoTrabajador() {
        return cargoTrabajador;
    }

    public void setCargoTrabajador(String cargoTrabajador) {
        this.cargoTrabajador = cargoTrabajador;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getRutTrabajador() {
        return rutTrabajador;
    }

    public void setRutTrabajador(String rutTrabajador) {
        this.rutTrabajador = rutTrabajador;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }

    public void setNombreTrabajador(String nombreTrabajador) {
        this.nombreTrabajador = nombreTrabajador;
    }

    public String getApellidoPater() {
        return apellidoPater;
    }

    public void setApellidoPater(String apellidoPater) {
        this.apellidoPater = apellidoPater;
    }

    public String getApellidoMater() {
        return apellidoMater;
    }

    public void setApellidoMater(String apellidoMater) {
        this.apellidoMater = apellidoMater;
    }

    public boolean validarRut(String rut) {

        boolean validacion = false;
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

            char dv = rut.charAt(rut.length() - 1);

            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }

        } catch (java.lang.NumberFormatException e) {
        } catch (Exception e) {
        }
        return validacion;
    }
// validacion para campos vacios
    
    public boolean validarCamposVacios() {
        boolean ok = true;
        if (this.getRutTrabajador().isEmpty()) {
            ok = false;
            JOptionPane.showMessageDialog(null, "El campo Rut no puede estar vacío");
        }
        if (this.getNombreTrabajador().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Nombres no puede estar vacío");
            ok = false;
        }
        if (this.getApellidoPater().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Apellido Paterno no puede estar vacío");
            ok = false;
        }
        if (this.getApellidoMater().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo Apellido Materno no puede estar vacío");
            ok = false;
        }
        if (this.getCargoTrabajador()== "Seleccione Cargo") {
            JOptionPane.showMessageDialog(null, "El campo Cargo no puede estar vacío");
            ok = false;
        }
        return ok;
    }
}
