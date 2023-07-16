package westbank.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JPanel {
    private JLabel titleLabel;
    private JLabel balanceLabel;

    public HomeView() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoPanel.setOpaque(false); // Establecer el panel del logo como transparente
        ImageIcon logoIcon = new ImageIcon("src/main/java/westbank/assets/favicon.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoPanel.add(logoLabel);

        JPanel textPanel = new JPanel(new GridBagLayout());
        textPanel.setBackground(Color.WHITE);

        titleLabel = new JLabel("Bienvenido(a):");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        balanceLabel = new JLabel("Tu saldo es: 0");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 23));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        textPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        textPanel.add(balanceLabel, gbc);

        mainPanel.add(logoPanel);
        mainPanel.add(textPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    public void setClientName(String name) {
        titleLabel.setText("Bienvenido(a): " + name);
    }

    public void setBalance(int balance) {
        balanceLabel.setText("Tu saldo es: " + balance);
    }
}
