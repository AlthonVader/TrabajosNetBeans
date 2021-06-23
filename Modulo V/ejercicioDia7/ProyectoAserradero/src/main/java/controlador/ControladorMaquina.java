package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DAOMaquina;
import modelo.Maquina;
import vista.VistaMaquina;

/**
 *
 * @author Althon Vader
 */
public class ControladorMaquina implements ActionListener, MouseListener {

    private Maquina ma;
    private DAOMaquina daom;
    private VistaMaquina vista;

    public ControladorMaquina(Maquina ma, DAOMaquina daom, VistaMaquina vista) {
        this.ma = ma;
        this.daom = daom;
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this); // agregar boton "agregar"
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        vista.jTableAserradero.addMouseListener(this);
    }

    public void iniciarFormulaMaquina() {

        vista.setTitle("Formulario Máquinas");// titulo
        vista.setLocationRelativeTo(null);// ubicacion
        vista.setVisible(true); // mostrar el formulario
        vista.jTableAserradero.setModel(modelo); // muestra la tabla
        vista.txtIdMaquina.setEnabled(false);//bloquea el campo "txtIdMaquina"
        vista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //dispose , permite cerrar solo la ventana seleccionada
        llenarTabla();
    }

    String[] columnas = {"id", "Nombre", "ubicacion", "tipo"};
    ArrayList<Object[]> datos = new ArrayList<>();
    DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

    @Override
    public void actionPerformed(ActionEvent e) {

        if (vista.btnAgregar == e.getSource()) { //detecta la pulsacion del boton agregar

            ma.setNombreMaquina(vista.txtNomMaquina.getText()); // toma el usuario ingresa en el formulario y lo guarda en el atributo nombre maquina
            ma.setUbicacionMaquina(vista.txtUbiMaquina.getText());
            ma.setTipoMaquina(vista.txtTipoMaquina.getText());

            if (daom.Agregar(ma)) { // se agraga el objaeto maquina "ma"
                JOptionPane.showMessageDialog(null, "agregado exitoso");

                llenarTabla();
                limpiar();

            } else {
                JOptionPane.showMessageDialog(null, "agregado fallida");
            }

        }
        // detectar btn Modificar o actualizar

        if (vista.btnActualizar == e.getSource()) {

            ma.setIdMaquina(Integer.parseInt(vista.txtIdMaquina.getText()));
            ma.setNombreMaquina(vista.txtNomMaquina.getText());
            ma.setUbicacionMaquina(vista.txtUbiMaquina.getText());
            ma.setTipoMaquina(vista.txtTipoMaquina.getText());

            if (daom.Modificar(ma)) {
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
                llenarTabla();
                limpiar();

            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
        }

        // btn eliminar 
        if (e.getSource() == vista.btnEliminar) {

            ma.setIdMaquina(Integer.parseInt(vista.txtIdMaquina.getText()));
            if (daom.Eliminar(ma)) {
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
        datos = daom.consultar();

        // for each
        for (Object[] obj : datos) {
            modelo.addRow(obj);
        }
        vista.jTableAserradero.setModel(modelo);

    }

    public void limpiar() {

        vista.txtNomMaquina.setText("");
        vista.txtUbiMaquina.setText("");
        vista.txtTipoMaquina.setText("");
        vista.txtIdMaquina.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.jTableAserradero) {
            vista.txtNomMaquina.setText(String.valueOf(vista.jTableAserradero.getValueAt(vista.jTableAserradero.getSelectedRow(), 1)));
            vista.txtUbiMaquina.setText(String.valueOf(vista.jTableAserradero.getValueAt(vista.jTableAserradero.getSelectedRow(), 2)));
            vista.txtTipoMaquina.setText(String.valueOf(vista.jTableAserradero.getValueAt(vista.jTableAserradero.getSelectedRow(), 3)));
            vista.txtIdMaquina.setText(String.valueOf(vista.jTableAserradero.getValueAt(vista.jTableAserradero.getSelectedRow(), 0)));

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

}
