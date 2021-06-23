/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.DAOMaquina;
import modelo.DAOTrabajador;
import modelo.Maquina;
import modelo.Trabajador;
import vista.FormTrabajador;
import vista.VistaBienvenido;
import vista.VistaMaquina;

/**
 *
 * @author Althon Vader
 */
public class ControladorBienvenido implements ActionListener {

    private VistaBienvenido vistabie;

    public ControladorBienvenido(VistaBienvenido vistabie) {
        this.vistabie = vistabie;
        this.vistabie.jMenuItemMaquina.addActionListener(this);
        this.vistabie.jMenuTrabajador.addActionListener(this);
    }

    public void iniciarVistaBienvenida() {
        vistabie.setTitle("Menu de Bienvenida");
        vistabie.setLocationRelativeTo(null);
        vistabie.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vistabie.jMenuItemMaquina == e.getSource()) {
            VistaMaquina vistam = new VistaMaquina();
            Maquina ma = new Maquina();
            DAOMaquina daom = new DAOMaquina();
            ControladorMaquina ctrm = new ControladorMaquina(ma, daom, vistam);
            ctrm.iniciarFormulaMaquina();
        }

        if (vistabie.jMenuTrabajador == e.getSource()) {
            FormTrabajador vistat = new FormTrabajador();
            Trabajador tra = new Trabajador();
            DAOTrabajador daot = new DAOTrabajador();
            ControladorTrabajador ctrt = new ControladorTrabajador(tra, daot, vistat);
            try {
                ctrt.iniciarFormulaTrabajadores();
            } catch (SQLException ex) {
                Logger.getLogger(ControladorBienvenido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
