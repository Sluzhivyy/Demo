package ui;

import service.ProductionCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MaterialCalculationFrame extends JFrame {
    private final JTextField productIdField;
    private final JTextField materialIdField;
    private final JTextField productQuantityField;
    private final JTextField parameter1Field;
    private final JTextField parameter2Field;
    private final JLabel resultLabel;
    private final ProductionCalculator productionCalculator;
    public MaterialCalculationFrame() {
        setTitle("Расчет количества материала");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        this.productionCalculator = new ProductionCalculator();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel productIdLabel = new JLabel("ID продукта");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(productIdLabel,gbc);
        productIdField = new JTextField(10);
        gbc.gridx = 1;
        mainPanel.add(productIdField,gbc);

        JLabel materialIdLabel = new JLabel("ID материала");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(materialIdLabel,gbc);
        materialIdField = new JTextField(10);
        gbc.gridx = 1;
        mainPanel.add(materialIdField,gbc);

        JLabel productQuantityLabel = new JLabel("Количество продукции");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(productQuantityLabel,gbc);
        productQuantityField = new JTextField(10);
        gbc.gridx = 1;
        mainPanel.add(productQuantityField,gbc);

        JLabel parameter1Label = new JLabel("Параметр 1");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(parameter1Label,gbc);
        parameter1Field = new JTextField(10);
        gbc.gridx = 1;
        mainPanel.add(parameter1Field,gbc);

        JLabel parameter2Label = new JLabel("Параметр 2");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(parameter2Label,gbc);
        parameter2Field = new JTextField(10);
        gbc.gridx = 1;
        mainPanel.add(parameter2Field,gbc);

        JButton calculateButton = new JButton("Рассчитать");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        mainPanel.add(calculateButton,gbc);

        resultLabel = new JLabel("Результат: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(resultLabel,gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Назад");
        buttonPanel.add(backButton);
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(buttonPanel, gbc);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                dispose();
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        add(mainPanel);
    }

    private void calculate(){
        try{
            int productId = Integer.parseInt(productIdField.getText());
            int materialId = Integer.parseInt(materialIdField.getText());
            int productQuantity = Integer.parseInt(productQuantityField.getText());
            double parameter1 = Double.parseDouble(parameter1Field.getText());
            double parameter2 = Double.parseDouble(parameter2Field.getText());

            int result = productionCalculator.calculateMaterialQuantity(productId,materialId,productQuantity,parameter1,parameter2);
            if(result == -1){
                resultLabel.setText("Результат: Некорректные данные");
            }else {
                resultLabel.setText("Результат: " + result);
            }

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(MaterialCalculationFrame.this,"Некорректный формат данных",
                    "Ошибка",JOptionPane.ERROR_MESSAGE);
        }catch (SQLException e){
            JOptionPane.showMessageDialog(MaterialCalculationFrame.this, "Ошибка при расчете: " + e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
