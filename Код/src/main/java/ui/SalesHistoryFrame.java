package ui;

import entities.SalesHistory;
import service.SalesHistoryService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalesHistoryFrame extends JFrame {

    private JTable salesHistoryTable;
    private DefaultTableModel tableModel;
    private final SalesHistoryService salesHistoryService;
    private  final int partnerId;
    public SalesHistoryFrame(int partnerId){
        setTitle("История продаж партнера");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/app_icon.png"));
        setIconImage(icon.getImage());
        this.salesHistoryService = new SalesHistoryService();
        this.partnerId = partnerId;

        JPanel mainPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Дата продажи");
        tableModel.addColumn("Наименование продукции");
        tableModel.addColumn("Количество");
        salesHistoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(salesHistoryTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Назад");
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartnersFrame partnersFrame = new PartnersFrame();
                partnersFrame.setVisible(true);
                dispose();
            }
        });

        add(mainPanel);
        loadSalesHistory();
    }

    private void loadSalesHistory() {
        try{
            tableModel.setRowCount(0);
            List<SalesHistory> salesHistoryList = salesHistoryService.getSalesHistoryByPartnerId(partnerId);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            for (SalesHistory history : salesHistoryList){
                Object[] rowData = {
                        history.getSaleDate().format(formatter),
                        history.getProductName(),
                        history.getQuantity()
                };
                tableModel.addRow(rowData);
            }

        }catch (SQLException e){
            JOptionPane.showMessageDialog(this, "Ошибка загрузки истории продаж" + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
