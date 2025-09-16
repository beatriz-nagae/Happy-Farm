package com.mycompany.happyfarm;

import com.mycompany.happyfarm.ConnectionFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TabelaFazendeiros extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public TabelaFazendeiros() {
        setTitle("Tabela de Fazendeiros");
        setSize(800, 600);
        setLocationRelativeTo(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        // Adicione as colunas à tabela
        model.addColumn("Código");
        model.addColumn("CNPJ");
        model.addColumn("Email Corporativo");
        model.addColumn("Telefone");
        model.addColumn("Endereço");
        model.addColumn("Bairro");
        model.addColumn("Número");
        model.addColumn("Cidade");
        model.addColumn("Estado");
        model.addColumn("CEP");
        model.addColumn("Senha");

        // Preencha a tabela com os dados do banco de dados
        preencherTabela();

        setVisible(true);
    }

    private void preencherTabela() {
        ConnectionFactory factory = new ConnectionFactory();
        String sql = "SELECT * FROM TbUsuario";
        
        try (Connection c = factory.obtemConexao();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                // Adicione os dados da linha ao modelo da tabela
                model.addRow(new Object[]{
                        rs.getInt("cod"),
                        rs.getString("cnpj"),
                        rs.getString("emailCorp"),
                        rs.getString("fone"),
                        rs.getString("endereco"),
                        rs.getString("bairro"),
                        rs.getInt("numEnd"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("cep"),
                        rs.getString("senha")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TabelaFazendeiros::new);
    }
}
