/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author HeberHerraz
 */
public class Conexion {
    
    
    //variables de conexion
    public String bd;
    public String url;
    public String user;
    public String pass;
    public String driver = "org.gjt.mm.mysql.Driver";
    
    //Variable para manipulacion de JSON
    JSONParser parser = new JSONParser();
    
    public void LectorJason(){
        try {
            Object objeto = parser.parse(new FileReader("src/Logica/Seguridad/seguridad.json"));
            //Object objeto = parser.parse(new FileReader("src\\Logica\\Seguridad\\seguridad.json"));
            
            JSONObject objetoJSON = (JSONObject) objeto;
            
            bd = (String)objetoJSON.get("namebd");
            url = (String)objetoJSON.get("url")+bd;
            user =(String)objetoJSON.get("usuario");
            pass = (String)objetoJSON.get("psw");
            
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Conexion()
    {}
    
    public Connection Conectar(){
       Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(this.url,this.user,this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showConfirmDialog(null,e);
        }
        return con;
    }
}
