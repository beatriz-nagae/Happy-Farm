package com.mycompany.happyfarm;

import com.mycompany.happyfarm.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class GerenciamentodePesticidasNaturais {
private int codPest;
//private String dadosPragDoencas;
//private String selPestNat;
//private String orientPestNat;
private String regPest;
private String regPragas;
private String regDoencas;
private String regEfeitoEsperado;
private String regEfeitoObtido;

    public GerenciamentodePesticidasNaturais() {
        this.codPest = codPest;
        this.regPest = regPest;
        this.regPragas = regPragas;
        this.regDoencas = regDoencas;
        this.regEfeitoEsperado = regEfeitoEsperado;
        this.regEfeitoObtido = regEfeitoObtido;
    }

    public String getRegDoencas() {
        return regDoencas;
    }

    public void setRegDoencas(String regDoencas) {
        this.regDoencas = regDoencas;
    }

    public int getCodPest() {
        return codPest;
    }

    public void setCodPest(int codPest) {
        this.codPest = codPest;
    }

   
    public String getRegPest() {
        return regPest;
    }

    public void setRegPest(String regPest) {
        this.regPest = regPest;
    }

    public String getRegPragas() {
        return regPragas;
    }

    public void setRegPragas(String regPragas) {
        this.regPragas = regPragas;
    }
       public String getRegEfeitoEsperado() {
        return regEfeitoEsperado;
    }

    public void setRegEfeitoEsperado(String regEfeitoEsperado) {
        this.regEfeitoEsperado = regEfeitoEsperado;
    }

    public String getRegEfeitoObtido() {
        return regEfeitoObtido;
    }

    public void setRegEfeitoObtido(String regEfeitoObtido) {
        this.regEfeitoObtido = regEfeitoObtido;
    }

    
    public void inserir() {
    String sql = "INSERT INTO TbGerenciamentodePesticidasNaturais(regPest, regPragas, regDoencas, regEfeitoEsperado, regEfeitoObtido) VALUES (?, ?, ?, ?, ?)";
    ConnectionFactory factory = new ConnectionFactory();
    try (Connection c = factory.obtemConexao()) {
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, regPest); 
        ps.setString(2, regPragas);
        ps.setString(3, regDoencas);
        ps.setString(4, regEfeitoEsperado);
        ps.setString(5, regEfeitoObtido);
        ps.execute();
        System.out.println("sql --- " + sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void apagar (){
                   String sql = "DELETE FROM TbGerenciamentodePesticidasNaturais WHERE codPest = ?";
                   ConnectionFactory factory = new ConnectionFactory();
                    try (Connection c = factory.obtemConexao()){
                           PreparedStatement ps = c.prepareStatement(sql);
                              ps.setInt(1, codPest);
                              ps.execute();
                                             }  
                    catch (Exception e){ 
                    e.printStackTrace();   
                                    }
                    }
public void listar() {
    String sql = "SELECT * FROM TbGerenciamentodePesticidasNaturais";
    ConnectionFactory factory = new ConnectionFactory();
    try (Connection c = factory.obtemConexao()) {
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int codPest = rs.getInt("codPest");
            String regPest = rs.getString("regPest");
            String regPragas = rs.getString("regPragas");
            String regDoencas = rs.getString("regDoencas");
            String regEfeitoEsperado = rs.getString("regEfeitoEsperado");
            String regEfeitoObtido = rs.getString("regEfeitoObtido");

            String aux = String.format("codPest: %d, regPest: %s, regPragas: %s, regDoencas: %s, regEfeitoEsperado: %s, regEfeitoObtido: %s",
                    codPest,
                    regPest,
                    regPragas,
                    regDoencas,
                    regEfeitoEsperado,
                    regEfeitoObtido);

            JOptionPane.showMessageDialog(null, aux);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
//Teste Retirado do Safras para inserir
public List<Object[]> getAllGerenciamentodePesticidasNaturais() {
        List<Object[]> GerenciamentodePesticidasNaturaisList = new ArrayList<>();
        String sql = "SELECT * FROM TbGerenciamentodePesticidasNaturais";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("codPest"),
                    rs.getString("regPest"),
                    rs.getString("regPragas"),
                    rs.getString("regDoencas"),
                    rs.getString("regEfeitoEsperado"),
                    rs.getString("regEfeitoObtido"),
                };
                GerenciamentodePesticidasNaturaisList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return GerenciamentodePesticidasNaturaisList;
    }




public void atualizar() throws SQLException {
    ConnectionFactory factory = new ConnectionFactory();
    String sql = "UPDATE TbGerenciamentodePesticidasNaturais SET regPest = ?, regPragas = ?, regDoencas = ?, regEfeitoEsperado = ?, regEfeitoObtido = ? WHERE codPest = ?";
    try (Connection c = factory.obtemConexao (); PreparedStatement ps = c.prepareStatement(sql)) {
        ps.setString(1, regPest);
        ps.setString(2, regPragas);
        ps.setString(3, regDoencas);
        ps.setString(4, regEfeitoEsperado);
        ps.setString(5, regEfeitoObtido);
        ps.setInt(6, codPest);
        ps.executeUpdate();
    }
}
public boolean buscar(int codPest) {
    String sql = "SELECT * FROM TbGerenciamentodePesticidasNaturais WHERE codPest = ?";
    ConnectionFactory factory = new ConnectionFactory();
    try (Connection c = factory.obtemConexao(); 
         PreparedStatement ps = c.prepareStatement(sql)) {
        
        ps.setInt(1, codPest);
        
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                this.codPest = rs.getInt("codPest");
                this.regPest = rs.getString("regPest");
                this.regPragas = rs.getString("regPragas");
                this.regDoencas = rs.getString("regDoencas");
                this.regEfeitoEsperado = rs.getString("regEfeitoEsperado");
                this.regEfeitoObtido = rs.getString("regEfeitoObtido");
                return true;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
}
