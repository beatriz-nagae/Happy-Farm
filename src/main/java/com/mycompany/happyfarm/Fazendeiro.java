package com.mycompany.happyfarm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Fazendeiro {
    private int codigo;
    private String cnpj;
    private String emailCorporativo;
    private String fone;
    private String endereco;
    private String bairro;
    private int numeroEndereco;
    private String cidade;
    private String estado;
    private String cep;
    private String senha;
    
    public Fazendeiro() {
    }

    public Fazendeiro(int codigo, String cnpj, String emailCorporativo, String fone, String endereco, String bairro, int numeroEndereco, String cidade, String estado, String cep, String senha, String usuario) {
        this.codigo = codigo;
        this.cnpj = cnpj;
        this.emailCorporativo = emailCorporativo;
        this.fone = fone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numeroEndereco = numeroEndereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.senha = senha;
    }

    public Fazendeiro(String cnpj, String emailCorporativo, String fone, String endereco, String bairro, int numeroEndereco, String cidade, String estado, String cep, String senha, String usuario) {
        this.cnpj = cnpj;
        this.emailCorporativo = emailCorporativo;
        this.fone = fone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numeroEndereco = numeroEndereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.senha = senha;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmailCorporativo() {
        return emailCorporativo;
    }

    public void setEmailCorporativo(String emailCorporativo) {
        this.emailCorporativo = emailCorporativo;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

 
    public void inserir (){
         String sql = "INSERT INTO tbusuario(cnpj,emailCorp,fone,endereco,bairro,numEnd,cidade,estado,cep,senha)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
           ConnectionFactory factory = new ConnectionFactory();
             try (Connection c = factory.obtemConexao()){
                  PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1, cnpj); 
                    ps.setString(2, emailCorporativo);
                    ps.setString(3, fone);
                    ps.setString(4, endereco);
                    ps.setString(5, bairro); 
                    ps.setInt(6, numeroEndereco);
                    ps.setString(7, cidade);
                    ps.setString(8, estado); 
                    ps.setString(9, cep);
                    ps.setString(10, senha); 
                    ps.execute(); //raio do SQL
                    System.out.println("sql --- " + sql);
             }
                         catch (Exception e){
                         e.printStackTrace();
                                 }
    }
             public boolean apagar() {
    String sql = "DELETE FROM tbusuario WHERE cod = ?";
    ConnectionFactory factory = new ConnectionFactory();
    try (Connection c = factory.obtemConexao()) {
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, codigo);
        int rowsAffected = ps.executeUpdate(); // retorna o número de linhas afetadas
        return rowsAffected > 0; // se alguma linha foi afetada, retorna true
    } catch (Exception e) {
        e.printStackTrace();
        return false; // em caso de erro, retorna false
    }
}

             public void listar (){
                 String sql = "SELECT * FROM TbUsuario ";
                     ConnectionFactory factory = new ConnectionFactory();
                      try (Connection c = factory.obtemConexao()){
                          PreparedStatement ps = c.prepareStatement(sql);
                          ResultSet rs = ps.executeQuery();
                          while (rs.next()){
                              int cod = rs.getInt("cod");
                                 String cnpj = rs.getString("cnpj");
                                 String fone = rs.getString("fone");
                                 String emailCorp = rs.getString("emailCorp");
                                 String endereco = rs.getString("endereco");
                                 String bairro = rs.getString("bairro");
                                 int numEnd = rs.getInt("numEnd");
                                 String cidade = rs.getString("cidade");
                                 String estado = rs.getString("estado");
                                 String cep = rs.getString("cep");
                                 String senha = rs.getString("senha");
                                 String aux = String.format( "cod: %d, cnpj: %s, fone: %s, emailCorp: %s, endereco: %s, bairro: %s, numEnd: %d, cidade: %s, estado: %s, cep: %s, senha: %s",
                                          cod,                   
                                          cnpj,
                                          fone,
                                          emailCorp,
                                          endereco,
                                          bairro,
                                          numEnd,
                                          cidade,
                                          estado,
                                          cep,
                                          senha);
                                  
                                  JOptionPane.showMessageDialog (null, aux);
                          }
                      }
                      catch (Exception e){
                      e.printStackTrace();
                      }
             } 
             
    public boolean login(String cnpj, String senha) throws SQLException {
       NewTelaGeral g = new NewTelaGeral();
        ConnectionFactory factory = new ConnectionFactory();
        String sql = "SELECT cnpj,senha FROM TbUsuario Where cnpj = '"+cnpj+"' AND senha = '"+senha+"'";
        System.out.println(sql);
        Connection c = factory.obtemConexao();
        PreparedStatement ps = c.prepareStatement(sql);
       ResultSet rs = ps.executeQuery();
       
       if (rs.next()) {
           g.setVisible(true);
           return true; // Login successful
        } else {
            return false; // Login failed
        }
    }
    
    public void atualizar() throws SQLException {
            ConnectionFactory factory = new ConnectionFactory();
            String sql = "UPDATE TbUsuario SET cnpj = ?, emailCorp = ?, fone = ?, endereco = ?, bairro = ?, numEnd = ?, cidade =?, estado = ?, cep = ?, senha = ? WHERE cod = ?";        
            try (Connection c = factory.obtemConexao();                
            PreparedStatement ps = c.prepareStatement(sql)){            
                ps.setString(1, cnpj); 
                    ps.setString(2, emailCorporativo);
                    ps.setString(3, fone);
                    ps.setString(4, endereco);
                    ps.setString(5, bairro); 
                    ps.setInt(6, numeroEndereco);
                    ps.setString(7, cidade);
                    ps.setString(8, estado); 
                    ps.setString(9, cep);
                    ps.setString(10, senha);
                    ps.setInt(11, codigo);
                    ps.execute();       
            }  

        }
}


                     
                      
             
         
    

