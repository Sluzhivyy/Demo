
package ui;

import entities.Partner;
import service.PartnerService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
public class PartnersFrame extends JFrame implements DataUpdater {
    private JTable partnersTable;
    private DefaultTableModel tableModel;
    private final PartnerService partnerService;
    public PartnersFrame(){
        setTitle("Список партнеров");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);

        this.partnerService = new PartnerService();
        JPanel mainPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Тип");
        tableModel.addColumn("Название компании");
        tableModel.addColumn("Юр. адрес");
        tableModel.addColumn("ИНН");
        tableModel.addColumn("Директор");
        tableModel.addColumn("Телефон");
        tableModel.addColumn("Email");
        tableModel.addColumn("Рейтинг");

        partnersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(partnersTable);
        mainPanel.add(scrollPane,BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Редактировать");
        JButton deleteButton = new JButton("Удалить");
        JButton backButton = new JButton("Назад");
        JButton historyButton = new JButton("История продаж");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel,BorderLayout.NORTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddPartnerDialog addPartnerDialog = new AddPartnerDialog(PartnersFrame.this,partnerService,PartnersFrame.this);
                addPartnerDialog.setVisible(true);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = partnersTable.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(PartnersFrame.this, "Выберите строку для редактирования",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int partnerId = (int) partnersTable.getValueAt(selectedRow,0);
                try{
                    Partner partner = partnerService.getPartnerById(partnerId);
                    if(partner != null){
                        EditPartnerDialog editPartnerDialog = new EditPartnerDialog(PartnersFrame.this,partnerService,partner,PartnersFrame.this);
                        editPartnerDialog.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(PartnersFrame.this,"Партнер не найден","Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }catch (SQLException exception){
                    JOptionPane.showMessageDialog(PartnersFrame.this,"Ошибка при поиске партнера" + exception.getMessage(),"Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = partnersTable.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(PartnersFrame.this, "Выберите строку для удаления",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int partnerId = (int) partnersTable.getValueAt(selectedRow,0);
                int confirm = JOptionPane.showConfirmDialog(PartnersFrame.this, "Удалить партнера?",
                        "Подтверждение", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    try{
                        boolean deleted = partnerService.deletePartner(partnerId);
                        if(deleted){
                            loadPartners();
                            JOptionPane.showMessageDialog(PartnersFrame.this,"Партнер успешно удален",
                                    "Успех",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(PartnersFrame.this,"Не удалось удалить партнера","Ошибка",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (SQLException exception){
                        JOptionPane.showMessageDialog(PartnersFrame.this,"Ошибка удаления партнера" + exception.getMessage(),"Ошибка",JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = partnersTable.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(PartnersFrame.this, "Выберите партнера для просмотра истории",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int partnerId = (int) partnersTable.getValueAt(selectedRow,0);
                SalesHistoryFrame salesHistoryFrame = new SalesHistoryFrame(partnerId);
                salesHistoryFrame.setVisible(true);
                dispose();

            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                dispose();
            }
        });

        add(mainPanel);
        loadPartners();
    }

    private void loadPartners() {
        try {
            tableModel.setRowCount(0);
            List<Partner> partners = partnerService.getAllPartners();
            for(Partner partner : partners){
                Object[] rowData = {
                        partner.getPartnerId(),
                        partner.getPartnerType(),
                        partner.getCompanyName(),
                        partner.getLegalAddress(),
                        partner.getInn(),
                        partner.getDirectorName(),
                        partner.getPhone(),
                        partner.getEmail(),
                        partner.getRating()
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки партнеров" + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void updateData() {
        loadPartners();
    }
}