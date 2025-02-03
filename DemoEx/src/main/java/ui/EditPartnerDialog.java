
package ui;

import entities.Partner;
import service.PartnerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditPartnerDialog extends JDialog {
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
    private final Partner partner;
    private final DataUpdater dataUpdater;

    public EditPartnerDialog(JFrame parent, PartnerService partnerService, Partner partner, DataUpdater dataUpdater) {
        super(parent, "Редактирование партнера", true);
        this.partnerService = partnerService;
        this.partner = partner;
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
        partnerTypeField.setText(partner.getPartnerType());
        gbc.gridx = 1;
        add(partnerTypeField, gbc);

        JLabel companyNameLabel = new JLabel("Название компании");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(companyNameLabel, gbc);
        companyNameField = new JTextField(20);
        companyNameField.setText(partner.getCompanyName());
        gbc.gridx = 1;
        add(companyNameField, gbc);

        JLabel legalAddressLabel = new JLabel("Юр. адрес");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(legalAddressLabel, gbc);
        legalAddressField = new JTextField(20);
        legalAddressField.setText(partner.getLegalAddress());
        gbc.gridx = 1;
        add(legalAddressField, gbc);

        JLabel innLabel = new JLabel("ИНН");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(innLabel, gbc);
        innField = new JTextField(20);
        innField.setText(partner.getInn());
        gbc.gridx = 1;
        add(innField, gbc);

        JLabel directorNameLabel = new JLabel("Директор");
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(directorNameLabel, gbc);
        directorNameField = new JTextField(20);
        directorNameField.setText(partner.getDirectorName());
        gbc.gridx = 1;
        add(directorNameField, gbc);

        JLabel phoneLabel = new JLabel("Телефон");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(phoneLabel, gbc);
        phoneField = new JTextField(20);
        phoneField.setText(partner.getPhone());
        gbc.gridx = 1;
        add(phoneField, gbc);

        JLabel emailLabel = new JLabel("Email");
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(emailLabel, gbc);
        emailField = new JTextField(20);
        emailField.setText(partner.getEmail());
        gbc.gridx = 1;
        add(emailField, gbc);

        JLabel logoLabel = new JLabel("Логотип");
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(logoLabel, gbc);
        logoField = new JTextField(20);
        logoField.setText(partner.getLogo());
        gbc.gridx = 1;
        add(logoField, gbc);

        JLabel ratingLabel = new JLabel("Рейтинг");
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(ratingLabel, gbc);
        ratingSpinner = new JSpinner(new SpinnerNumberModel(partner.getRating(), 0.0, 5.0, 0.1));
        gbc.gridx = 1;
        add(ratingSpinner, gbc);

        JLabel salesLocationsLabel = new JLabel("Места продаж");
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(salesLocationsLabel, gbc);
        salesLocationsArea = new JTextArea(5, 20);
        salesLocationsArea.setText(partner.getSalesLocations());
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
                updatePartner();
            }
        });
    }

    private void updatePartner() {
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
            JOptionPane.showMessageDialog(EditPartnerDialog.this, "Заполните все поля",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        partner.setPartnerType(partnerType);
        partner.setCompanyName(companyName);
        partner.setLegalAddress(legalAddress);
        partner.setInn(inn);
        partner.setDirectorName(directorName);
        partner.setPhone(phone);
        partner.setEmail(email);
        partner.setLogo(logo);
        partner.setRating(rating);
        partner.setSalesLocations(salesLocations);
        try {
            boolean updated = partnerService.updatePartner(partner);
            if (updated) {
                JOptionPane.showMessageDialog(EditPartnerDialog.this, "Партнер успешно обновлен",
                        "Успех", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                dataUpdater.updateData();

            } else {
                JOptionPane.showMessageDialog(EditPartnerDialog.this, "Не удалось обновить партнера",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(EditPartnerDialog.this, "Ошибка обновления партнера" + exception.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
