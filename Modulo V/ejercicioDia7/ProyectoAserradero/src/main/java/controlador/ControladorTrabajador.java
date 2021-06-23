/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAOTrabajador;
import modelo.Trabajador;
import vista.FormTrabajador;

/**
 *
 * @author Althon Vader
 */
public class ControladorTrabajador implements ActionListener, MouseListener, KeyListener {

    private Trabajador tra;
    private DAOTrabajador daot;
    private FormTrabajador vistat;
    

    public ControladorTrabajador(Trabajador tra, DAOTrabajador daot, FormTrabajador vistat) {
        this.tra = tra;
        this.daot = daot;
        this.vistat = vistat;
        this.vistat.btnAgreTrabajdor.addActionListener(this); // agregar boton "agregar"
        this.vistat.btnModiTrabajador.addActionListener(this);
        this.vistat.btnEliTrabajador.addActionListener(this);
        vistat.jttbTrabajador.addMouseListener(this);
        vistat.txtNombre.addKeyListener(this);
        vistat.txtApPaterno.addKeyListener(this);
        vistat.txtApMaterno.addKeyListener(this);
    }

    public void iniciarFormulaTrabajadores() throws SQLException {

        vistat.setTitle("Formulario Trabajadores");// titulo
        vistat.setLocationRelativeTo(null);// ubicacion
        vistat.setVisible(true); // mostrar el formulario
        vistat.jttbTrabajador.setModel(modelo); // muestra la tabla
        vistat.txtIdTrabajador.setEnabled(false);//bloquea el campo "txtIdMaquina"
        vistat.jttbTrabajador.addMouseListener(this);
        vistat.cbxCargo.setModel(daot.ob_cargo());
        vistat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dispose , permite cerrar solo la ventana seleccionada
        llenarTabla();
    }

    String[] columnas = {"id_trabajador", "rut", "nombres", "apellido_paterno", "apellido_materno", "cargo_fk"};
    ArrayList<Object[]> datos = new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    @Override
    public void actionPerformed(ActionEvent e) {
        //boton agregar
        if (vistat.btnAgreTrabajdor == e.getSource()) { //detecta la pulsacion del boton agregar

            tra.setRutTrabajador(vistat.txtRut.getText()); // toma el usuario ingresa en el formulario y lo guarda en el atributo nombre maquina
            tra.setNombreTrabajador(vistat.txtNombre.getText());
            tra.setApellidoPater(vistat.txtApPaterno.getText());
            tra.setApellidoMater(vistat.txtApMaterno.getText());
            tra.setCargoTrabajador(vistat.cbxCargo.getSelectedItem().toString());

//            if (daot.Agregar(tra)) { // se agraga el objaeto maquina "ma"
//                JOptionPane.showMessageDialog(null, "agregado exitoso");
//
//                llenarTabla();
//                limpiar();
//
//            } else {
//                JOptionPane.showMessageDialog(null, "agregado fallida");
//            }
            if (tra.validarRut(vistat.txtRut.getText())) {
                if (daot.Agregar(tra)) { // se agraga el objaeto maquina "ma"
                    JOptionPane.showMessageDialog(null, "agregado exitoso");

                    llenarTabla();
                    limpiar();

                } else {
                    JOptionPane.showMessageDialog(null, "agregado fallida");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Rut Invalido");
            }

            if (tra.validarCamposVacios()) {

            } else {
                JOptionPane.showMessageDialog(null, "No debe dejar campos  vacios");

            }

        }

        // boton modificar
        if (vistat.btnModiTrabajador == e.getSource()) {

            tra.setRutTrabajador(vistat.txtRut.getText());
            tra.setNombreTrabajador(vistat.txtNombre.getText());
            tra.setApellidoPater(vistat.txtApPaterno.getText());
            tra.setApellidoMater(vistat.txtApMaterno.getText());
            tra.setCargoTrabajador(vistat.cbxCargo.getSelectedItem().toString());
            tra.setIdTrabajador(Integer.parseInt(vistat.txtIdTrabajador.getText()));
            if (daot.Modificar(tra)) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                llenarTabla();
                limpiar();

            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
        }

        //boton eliminar
        if (e.getSource() == vistat.btnEliTrabajador) {

            tra.setIdTrabajador(Integer.parseInt(vistat.txtIdTrabajador.getText()));
            if (daot.Eliminar(tra)) {
                JOptionPane.showMessageDialog(null, "Eliminado");
                llenarTabla();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }

        }
    }

    public void llenarTabla() {  //carga los datos de la bace de datos a la tabla
        modelo.setRowCount(0);
        datos = daot.consultar();

        // for each
        for (Object[] obj : datos) {
            modelo.addRow(obj);
        }
        vistat.jttbTrabajador.setModel(modelo);

    }

    public void limpiar() {

        vistat.txtIdTrabajador.setText("");
        vistat.txtRut.setText("");
        vistat.txtNombre.setText("");
        vistat.txtApPaterno.setText("");
        vistat.txtApMaterno.setText("");

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistat.jttbTrabajador) {
            vistat.txtIdTrabajador.setText(String.valueOf(vistat.jttbTrabajador.getValueAt(vistat.jttbTrabajador.getSelectedRow(), 0)));
            vistat.txtRut.setText(String.valueOf(vistat.jttbTrabajador.getValueAt(vistat.jttbTrabajador.getSelectedRow(), 1)));
            vistat.txtNombre.setText(String.valueOf(vistat.jttbTrabajador.getValueAt(vistat.jttbTrabajador.getSelectedRow(), 2)));
            vistat.txtApPaterno.setText(String.valueOf(vistat.jttbTrabajador.getValueAt(vistat.jttbTrabajador.getSelectedRow(), 3)));
            vistat.txtApMaterno.setText(String.valueOf(vistat.jttbTrabajador.getValueAt(vistat.jttbTrabajador.getSelectedRow(), 4)));
            vistat.cbxCargo.setSelectedItem(String.valueOf(vistat.jttbTrabajador.getValueAt(vistat.jttbTrabajador.getSelectedRow(), 5)));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent evt) {
        
     if (evt.getSource() == vistat.txtNombre) {
            char C = evt.getKeyChar();

            if (Character.isDigit(C)) {

                vistat.getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
                vistat.txtNombre.setCursor(null); // nombre del campo

            } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                    || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                    || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                    || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {

                vistat.getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
                vistat.txtNombre.setCursor(null); // nombre del campo

            }

        }

        if (evt.getSource() == vistat.txtApPaterno) {
            char C = evt.getKeyChar();

            if (Character.isDigit(C)) {

                vistat.getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
                vistat.txtApPaterno.setCursor(null); // nombre del campo

            } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                    || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                    || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                    || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {

                vistat.getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
                vistat.txtApPaterno.setCursor(null); // nombre del campo

            }

        }

        if (evt.getSource() == vistat.txtApMaterno) {
            char C = evt.getKeyChar();

            if (Character.isDigit(C)) {

                vistat.getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
                vistat.txtApMaterno.setCursor(null); // nombre del campo

            } else if ((int) evt.getKeyChar() > 32 && (int) evt.getKeyChar() <= 47
                    || (int) evt.getKeyChar() >= 58 && (int) evt.getKeyChar() <= 64
                    || (int) evt.getKeyChar() >= 91 && (int) evt.getKeyChar() <= 96
                    || (int) evt.getKeyChar() >= 123 && (int) evt.getKeyChar() <= 255) {

                vistat.getToolkit().beep();
                evt.consume();
                JOptionPane.showMessageDialog(null, "Ingrese solo Letras");
                vistat.txtApMaterno.setCursor(null); // nombre del campo

            }

        }

    }  
        
        
    

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
