/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Althon Vader
 */
public class DAOTrabajador implements CRUD {

    Trabajador tra = new Trabajador();// instanciando una clase Trabajador utilizando el constructor vacio

    //patron singleton
    Conexion conectar = Conexion.getInstance(); // instancia una clase pero con un metodo getInstance
    Connection con;

    @Override
    public boolean Agregar(Object obj) {
        tra = (Trabajador) obj;
        String sql = "INSERT INTO trabajadores (rut,nombres,apellido_paterno,apellido_materno,cargo_fk) VALUES(?,?,?,?,(select  id_cargo FROM cargos where nombre = ?))";
        PreparedStatement pst;

        try {
            con = (Connection) conectar.conectar();
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, tra.getRutTrabajador());
            pst.setString(2, tra.getNombreTrabajador());
            pst.setString(3, tra.getApellidoPater());
            pst.setString(4, tra.getApellidoMater());
            pst.setString(5, tra.getCargoTrabajador());
            int filas = pst.executeUpdate();
            if (filas > 0) {

                //con.close();
                conectar.cerrarConexion();
                return true;
            } else {

                conectar.cerrarConexion();
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un errror : " + e.getMessage());
        }
        return false;

    }

    @Override
    public boolean Modificar(Object obj) {
        tra = (Trabajador) obj;
        String sql = "UPDATE trabajadores SET rut = ?,nombres = ?,apellido_paterno =?, apellido_materno = ?, cargo_fk = (select id_cargo FROM cargos where nombre = ?) WHERE id_trabajador =?";
        Connection con;
        PreparedStatement pst;

        try {
            con = (Connection) conectar.conectar();
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, tra.getRutTrabajador());
            pst.setString(2, tra.getNombreTrabajador());
            pst.setString(3, tra.getApellidoPater());
            pst.setString(4, tra.getApellidoMater());            
            pst.setString(5, tra.getCargoTrabajador());
            pst.setInt(6, tra.getIdTrabajador());
            int filas = pst.executeUpdate();
            if (filas > 0) {

                //con.close();
                conectar.cerrarConexion();
                return true;
            } else {

                conectar.cerrarConexion();
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un errror : " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean Eliminar(Object obj) {
      tra = (Trabajador) obj;
        String sql = "DELETE FROM trabajadores where id_trabajador=?";
        Connection con;
        PreparedStatement pst;

        try {
            con = (Connection) conectar.conectar();
            pst = (PreparedStatement) con.prepareStatement(sql);            
            pst.setInt(1, tra.getIdTrabajador());                     
            int filas = pst.executeUpdate();
            con.close();
            
            if (filas > 0) {
                con.close();
                return true;
                
            } else {
                return false;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un errror :" + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Object[]> consultar() {
      String sql = "select t.id_trabajador, t.rut, t.nombres, t.apellido_paterno, t.apellido_materno, c.nombre from cargos c INNER JOIN trabajadores t on c.id_cargo  = t.cargo_fk order by id_trabajador";
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        ResultSetMetaData meta;
        ArrayList<Object[]> datos = new ArrayList<>();

        try {
            con = (Connection) conectar.conectar();
            pst = (PreparedStatement) con.prepareStatement(sql);

            rs = pst.executeQuery();
            meta = rs.getMetaData();
            while (rs.next()) {
                Object[] fila = new Object[meta.getColumnCount()];
                for (int i = 0; i < fila.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                datos.add(fila);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un errror" + e.getMessage());

        }
        return datos;
    }
public DefaultComboBoxModel ob_cargo() throws SQLException{
         con = (Connection) conectar.conectar();
        Statement st  = con.createStatement();
        DefaultComboBoxModel listaModelo= new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione Cargo");
        ResultSet rs = st.executeQuery("select nombre from cargos");
          try {
            while (rs.next()) {
                listaModelo.addElement(rs.getString(1));
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un errror" + ex.getMessage());
        }
        return listaModelo;
}
}
