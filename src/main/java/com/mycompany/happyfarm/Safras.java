package com.mycompany.happyfarm;
import com.mycompany.happyfarm.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Safras {
private int codSafra;    
 private String nomeAlimento; 
 private String tempoColheita; 
private String epocaCultivo; 
private int qtdPlantou; 
private int qtdColheu; 
private String colheita; 
private String posColheita;
private String estacao;
private String indicePluvioMes;
private String alertaCondClimaExt;

    public Safras() {
        this.codSafra = codSafra;
        this.nomeAlimento = nomeAlimento;
        this.tempoColheita = tempoColheita;
        this.epocaCultivo = epocaCultivo;
        this.qtdPlantou = qtdPlantou;
        this.qtdColheu = qtdColheu;
        this.colheita = colheita;
        this.posColheita = posColheita;
        this.estacao = estacao;
        this.indicePluvioMes=indicePluvioMes;
        this.alertaCondClimaExt=alertaCondClimaExt;
    }
  
      public String getalertaCondClimaExt() {
        return alertaCondClimaExt;
    }

    public void setalertaCondClimaExt(String alertaCondClimaExt) {
        this.alertaCondClimaExt = alertaCondClimaExt;
    }
  public String getIndicePluvioMes() {
        return indicePluvioMes;
    }

    public void setIndicePluvioMes(String indicePluvioMes) {
        this.indicePluvioMes = indicePluvioMes;
    }
    
    public String getPosColheita() {
        return posColheita;
    }

    public void setPosColheita(String posColheita) {
        this.posColheita = posColheita;
    }

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public String getTempoColheita() {
        return tempoColheita;
    }

    public void setTempoColheita(String tempoColheita) {
        this.tempoColheita = tempoColheita;
    }

    public String getEpocaCultivo() {
        return epocaCultivo;
    }

    public void setEpocaCultivo(String epocaCultivo) {
        this.epocaCultivo = epocaCultivo;
    }

    public int getQtdPlantou() {
        return qtdPlantou;
    }

    public void setQtdPlantou(int qtdPlantou) {
        this.qtdPlantou = qtdPlantou;
    }

    public int getQtdColheu() {
        return qtdColheu;
    }

    public void setQtdColheu(int qtdColheu) {
        this.qtdColheu = qtdColheu;
    }

    public String getColheita() {
        return colheita;
    }

    public void setColheita(String colheita) {
        this.colheita = colheita;
    }
     
    public int getCodSafra() {
        return codSafra;
    }

    public void setCodSafra(int codSafra) {
        this.codSafra = codSafra;
    }
    
    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }
    
    public void inserir (){
         String sql = "INSERT INTO TbGerenciamentodeSafras(nomeAlimento, tempoColheita, epocaCultivo, qtdPlantou, qtdColheu, estacao, indicePluvioMes, alertaCondClimaExt)" + "VALUES(?,?,?,?,?,?,?,?)";
           ConnectionFactory factory = new ConnectionFactory();
             try (Connection c = factory.obtemConexao()){
                  PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1, nomeAlimento); 
                    ps.setString(2, tempoColheita);
                    ps.setString(3, epocaCultivo);
                    ps.setInt(4, qtdPlantou);
                    ps.setInt(5, qtdColheu); 
                    ps.setString(6, estacao); 
                    ps.setString(7, indicePluvioMes); 
                    ps.setString(8, alertaCondClimaExt); 
                    ps.execute(); //raio do SQL
                    System.out.println("sql --- " + sql);
             }
                         catch (Exception e){
                         e.printStackTrace();
                                 }
    }   
                 public void apagar (){
                   String sql = "DELETE FROM TbGerenciamentodeSafras WHERE codSafra = ?";
                   ConnectionFactory factory = new ConnectionFactory();
                    try (Connection c = factory.obtemConexao()){
                           PreparedStatement ps = c.prepareStatement(sql);
                              ps.setInt(1, codSafra);
                              ps.execute();
                                             }  
                    catch (Exception e){ 
                    e.printStackTrace();   
                                    }
                    }

public List<Object[]> getAllSafras() {
        List<Object[]> safrasList = new ArrayList<>();
        String sql = "SELECT * FROM TbGerenciamentodeSafras";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("codSafra"),
                    rs.getString("nomeAlimento"),
                    rs.getString("tempoColheita"),
                    rs.getString("epocaCultivo"),
                    rs.getInt("qtdPlantou"),
                    rs.getInt("qtdColheu"),
                    rs.getString("estacao"),
                    rs.getString("indicePluvioMes"),
                    rs.getString("alertaCondClimaExt"),
                };
                safrasList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return safrasList;
    }
    
public void atualizar () throws SQLException {
            ConnectionFactory factory = new ConnectionFactory();
            String sql = "UPDATE TbGerenciamentodeSafras SET nomeAlimento = ?, tempoColheita = ?, epocaCultivo = ?, qtdPlantou = ?, qtdColheu = ? , estacao = ?,indicePluvioMes=?, alertaCondClimaExt=? WHERE codSafra = ?";        
            try (Connection c = factory.obtemConexao();                
            PreparedStatement ps = c.prepareStatement(sql)){            
                ps.setString(1, nomeAlimento); 
                    ps.setString(2, tempoColheita);
                    ps.setString(3, epocaCultivo);
                    ps.setInt(4, qtdPlantou);
                    ps.setInt(5, qtdColheu); 
                    ps.setString(6, estacao);
                    ps.setString(7, indicePluvioMes);
                    ps.setString(8, alertaCondClimaExt);
                    ps.setInt(9, codSafra);
                    ps.execute();       
            }    
    }
 public boolean buscar(int codSafra) {
        String sql = "SELECT * FROM TbGerenciamentodeSafras WHERE codSafra = ?";
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection c = factory.obtemConexao(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, codSafra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    this.codSafra = rs.getInt("codSafra");
                    this.nomeAlimento = rs.getString("nomeAlimento");
                    this.tempoColheita = rs.getString("tempoColheita");
                    this.epocaCultivo = rs.getString("epocaCultivo");
                    this.qtdPlantou = rs.getInt("qtdPlantou");
                    this.qtdColheu = rs.getInt("qtdColheu");
                    this.estacao = rs.getString("estacao");
                    this.indicePluvioMes = rs.getString("indicePluvioMes");
                    this.alertaCondClimaExt = rs.getString("alertaCondClimaExt");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }    
   
    
}
