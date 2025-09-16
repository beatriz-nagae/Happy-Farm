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

public class TabelaSafras extends JFrame {

    public TabelaSafras() {
        setTitle("Tabela Safras");
            setSize(800, 400);
        setLocationRelativeTo(null); // Centralize the frame

        // Create a Safras object to access the database
        Safras safras = new Safras();

        // Fetch data from database
        List<Object[]> data = safras.getAllSafras();

        // Define column names
        String[] columnNames = {"CodSafra", "Nome Alimento", "Tempo Colheita", "Epoca Cultivo", "Qtd Plantou", "Qtd Colheu", "Estação","ind. Pluvio Mês","Cond. Clima Extrema"};

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
            new TabelaSafras().setVisible(true);
        });
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
                        rs.getString("alertaCondClimaExt")
                };
                safrasList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return safrasList;
    }
}

