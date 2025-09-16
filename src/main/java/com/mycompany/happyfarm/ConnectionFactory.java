package com.mycompany.happyfarm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionFactory {
    private String usuario = "root"; 
    private String senha = "t21bd";    
    private String host = "localhost";   
    private String porta = "3306";   
    private String bd = "DBHappyFarm";
    
    public Connection obtemConexao (){ 
        try{         
            Connection c = DriverManager.getConnection( 
            "jdbc:mysql://" + host + ":" + porta + "/" + bd + "?serverTimezone=UTC",
             usuario,
             senha);
            System.out.println("Conexao efetuada com muito sucesso");
        return c;
        }   
            catch (Exception e){    
            e.printStackTrace(); 
                System.out.println("Erro. Conexao falhou");
            return null;   
        }   
    }

}