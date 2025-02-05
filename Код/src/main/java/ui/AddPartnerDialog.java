package ui;

import entities.Partner;
import service.PartnerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddPartnerDialog extends JDialog {
    private final JTextField partnerTypeField;
    private final JTextField companyNameField;
    private final JTextField legalAddressField;
    private final JTextField innField;
    private final JTextField directorNameField;
    private final JTextField phoneField;
    private final JTextField emailField;
    private final JTextField logoField;
    private final JSpinner ratingSpinner;

    private final JTextArea salesLocationsArea;
    private final PartnerService partnerService;
    private final DataUpdater dataUpdater;

    public AddPartnerDialog(JFrame parent, PartnerService partnerService, DataUpdater dataUpdater) {
        super(parent, "Добавление партнера", true);
        this.partnerService = partnerService;
        this.dataUpdater = dataUpdater;
        setSize(400, 500);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel partnerTypeLabel = new JLabel("Тип партнера");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(partnerTypeLabel, gbc);
        partnerTypeField = new JTextField(20);
        gbc.gridx = 1;
        add(partnerTypeField, gbc);

        JLabel companyNameLabel = new JLabel("Название компании");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(companyNameLabel, gbc);
        companyNameField = new JTextField(20);
        gbc.gridx = 1;
        add(companyNameField, gbc);

        JLabel legalAddressLabel = new JLabel("Юр. адрес");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(legalAddressLabel, gbc);
        legalAddressField = new JTextField(20);
        gbc.gridx = 1;
        add(legalAddressField, gbc);

        JLabel innLabel = new JLabel("ИНН");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(innLabel, gbc);
        innField = new JTextField(20);
        gbc.gridx = 1;
        add(innField, gbc);

        JLabel directorNameLabel = new JLabel("Директор");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(directorNameLabel, gbc);
        directorNameField = new JTextField(20);
        gbc.gridx = 1;
        add(directorNameField, gbc);

        JLabel phoneLabel = new JLabel("Телефон");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(phoneLabel, gbc);
        phoneField = new JTextField(20);
        gbc.gridx = 1;
        add(phoneField, gbc);

        JLabel emailLabel = new JLabel("Email");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(emailLabel, gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        JLabel logoLabel = new JLabel("Логотип");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(logoLabel, gbc);
        logoField = new JTextField(20);
        gbc.gridx = 1;
        add(logoField, gbc);

        JLabel ratingLabel = new JLabel("Рейтинг");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(ratingLabel, gbc);
        ratingSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 5.0, 0.1));
        gbc.gridx = 1;
        add(ratingSpinner, gbc);

        JLabel salesLocationsLabel = new JLabel("Места продаж");
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(salesLocationsLabel, gbc);
        salesLocationsArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(salesLocationsArea);
        gbc.gridx = 1;
        add(scrollPane, gbc);

        JButton saveButton = new JButton("Сохранить");
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        add(saveButton, gbc);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePartner();
            }
        });

    }

    private void savePartner() {
        String partnerType = partnerTypeField.getText();
        String companyName = companyNameField.getText();
        String legalAddress = legalAddressField.getText();
        String inn = innField.getText();
        String directorName = directorNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String logo = logoField.getText();
        double rating = (double) ratingSpinner.getValue();
        String salesLocations = salesLocationsArea.getText();
        if (partnerType.isEmpty() || companyName.isEmpty() || legalAddress.isEmpty() || inn.isEmpty() || directorName.isEmpty() || phone.isEmpty() || email.isEmpty() || salesLocations.isEmpty()) {
            JOptionPane.showMessageDialog(AddPartnerDialog.this, "Заполните все поля",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Partner partner = new Partner(partnerType, companyName, legalAddress, inn, directorName, phone, email, logo, rating, salesLocations);
        try {
            boolean added = partnerService.addPartner(partner);
            if (added) {
                JOptionPane.showMessageDialog(AddPartnerDialog.this, "Партнер успешно добавлен",
                        "Успех", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                dataUpdater.updateData();

            } else {
                JOptionPane.showMessageDialog(AddPartnerDialog.this, "Не удалось добавить партнера",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(AddPartnerDialog.this, "Ошибка добавления партнера" + exception.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
