package com.mycompany.happyfarm;

import com.mycompany.happyfarm.ConnectionFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabelaGerenciamentodePesticidasNaturais extends JFrame {

    public TabelaGerenciamentodePesticidasNaturais() {
        setTitle("Tabela Gerenciamento de Pesticidas Naturais");
            setSize(800, 400);
        setLocationRelativeTo(null); // Centralize the frame

        // Create a Safras object to access the database
        GerenciamentodePesticidasNaturais g = new GerenciamentodePesticidasNaturais();

        // Fetch data from database
        List<Object[]> data = g.getAllGerenciamentodePesticidasNaturais();

        // Define column names
        String[] columnNames = {"CodPest", "regPest", "regPragas", "regDoencas", "regEfeitoEsperado", "regEfeitoObtido"};

        // Create table model with the data and column names
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Add rows to the table model
        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        // Create JTable with the table model
        JTable table = new JTable(tableModel);

        // Create JScrollPane to add scroll functionality to the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add JScrollPane to the frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Create and display the frame
        SwingUtilities.invokeLater(() -> {
            new TabelaGerenciamentodePesticidasNaturais().setVisible(true);
        });
    }


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
}

