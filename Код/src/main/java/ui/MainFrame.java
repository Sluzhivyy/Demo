package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Система управления производством");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel logoLabel = new JLabel();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(logoLabel, gbc);


        JButton partnersButton = new JButton("Управление партнерами");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(partnersButton, gbc);

        JButton materialCalculationButton = new JButton("Расчет количества материала");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(materialCalculationButton, gbc);

        partnersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartnersFrame partnersFrame = new PartnersFrame();
                partnersFrame.setVisible(true);
                dispose();
            }
        });

        materialCalculationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialCalculationFrame materialCalculationFrame = new MaterialCalculationFrame();
                materialCalculationFrame.setVisible(true);
                dispose();
            }
        });

        add(mainPanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }catch(Exception e){
                e.printStackTrace();
            }
            mainFrame.setVisible(true);
        });
    }
}
