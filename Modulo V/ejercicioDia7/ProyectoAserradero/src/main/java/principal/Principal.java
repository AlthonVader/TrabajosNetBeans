/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.jtattoo.plaf.bernstein.BernsteinLookAndFeel;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import controlador.Controlador;
import controlador.ControladorMaquina;
import controlador.ControladorTrabajador;
import java.sql.SQLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Conexion;
import modelo.DAOMaquina;
import modelo.DAOTrabajador;
import modelo.DAOsuario;
import modelo.Maquina;
import modelo.Trabajador;
import modelo.Usuario;
import vista.FormLogin;
import vista.FormTrabajador;
import vista.VistaMaquina;

/**
 *
 * @author Althon Vader
 */
public class Principal {

    public static void main(String[] args) throws SQLException, UnsupportedLookAndFeelException {

//        VistaMaquina vista = new VistaMaquina();
//        Maquina ma = new Maquina();
//        DAOMaquina daom = new DAOMaquina();
//        ControladorMaquina ctrm = new ControladorMaquina(ma, daom, vista);
//        ctrm.iniciarFormulaMaquina();
// inicia desde login
        UIManager.setLookAndFeel(new GraphiteLookAndFeel());
        FormLogin log = new FormLogin();
        Usuario usu = new Usuario();
        DAOsuario dusu = new DAOsuario();

        Controlador ctrl = new Controlador(log, usu, dusu);
        ctrl.iniciarFormLogin();

//inicia Trabajadores
//        FormTrabajador vistat = new FormTrabajador();
//        Trabajador tra = new Trabajador();
//        DAOTrabajador daot = new DAOTrabajador();
//        ControladorTrabajador ctrt = new ControladorTrabajador(tra, daot, vistat);
//        ctrt.iniciarFormulaTrabajadores();
    }

}
