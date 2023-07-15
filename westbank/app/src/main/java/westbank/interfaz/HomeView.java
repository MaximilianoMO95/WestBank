package westbank.interfaz;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
        private String clientName = "undefined";
        private String balance = "0";

        public HomeView() {
                setLayout(new BorderLayout());

                // Main panel
                JPanel mainPanel = new JPanel(new GridBagLayout());

                // Logo panel
                JPanel logoPanel = new JPanel(new GridBagLayout());
                ImageIcon logoIcon = new ImageIcon("src/main/java/westbank/assets/favicon.png");
                JLabel logoLabel = new JLabel(logoIcon);
                logoPanel.add(logoLabel);

                // Text panel
                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                JLabel titleLabel = new JLabel("Bienvenido(a): " + clientName);
                JLabel balanceLabel = new JLabel("Tu saldo es: " + balance);
                titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 26f));
                balanceLabel.setFont(balanceLabel.getFont().deriveFont(Font.BOLD, 23f));

                textPanel.add(titleLabel);
                textPanel.add(balanceLabel);

                // Set alignment for logo and text panels
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(10, 10, 100, 10);
                gbc.anchor = GridBagConstraints.CENTER;
                mainPanel.add(logoPanel, gbc);
                gbc.gridy = 1;
                mainPanel.add(textPanel, gbc);

                add(mainPanel, BorderLayout.CENTER);
        }

        public void setClientName(String name) {
                this.clientName = name;
        }

        public void setClientName(int balance) {
                this.balance = Integer.toString(balance);
        }
}
