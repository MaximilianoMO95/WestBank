package westbank.interfaz;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public class TransferView extends JPanel {

        private JLabel titleLabel;
        private JLabel srcAccountLabel;
        private JLabel destAccountLabel;
        private JTextField destAccountField;
        private JLabel transferAmountLabel;
        private JTextField transferAmountField;
        private JButton transferButton;

        public TransferView() {
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                // "Transferir dinero"
                titleLabel = new JLabel("Transferir dinero");
                titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 26f));
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.insets = new Insets(50, 0, 50, 0);
                gbc.anchor = GridBagConstraints.CENTER;
                add(titleLabel, gbc);

                // Número de cuenta de origen
                gbc.insets = new Insets(10, 10, 60, 10);
                srcAccountLabel = new JLabel("Este es tu numero de cuenta: undefined");
                srcAccountLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18f));
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                add(srcAccountLabel, gbc);

                // Número de cuenta de destino
                gbc.insets = new Insets(10, 10, 10, 10);
                destAccountLabel = new JLabel("Número de cuenta de destino");
                gbc.gridy++;
                gbc.gridwidth = 2;
                add(destAccountLabel, gbc);

                destAccountField = new JTextField(10);
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                add(destAccountField, gbc);

                // Monto a transferir
                transferAmountLabel = new JLabel("Monto a transferir");
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.CENTER;
                add(transferAmountLabel, gbc);

                transferAmountField = new JTextField(10);
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                add(transferAmountField, gbc);

                // Botón Transferir
                transferButton = new JButton("Transferir");
                gbc.gridx = 0; // Cambiar el valor a 0
                gbc.gridy++; // Moverlo a la siguiente fila
                gbc.gridwidth = 2; // Abarcar 2 columnas
                add(transferButton, gbc);

                // Espacio
                gbc.gridx = 0;
                gbc.gridy++;
                gbc.gridwidth = 2;
                gbc.weighty = 0.2;
                gbc.fill = GridBagConstraints.BOTH;
                add(new JPanel(), gbc);
        }

        public void setSrcAccountNumber(int srcAccountNumber) {
                srcAccountLabel.setText("Este es tu numero de cuenta: " + Integer.toString(srcAccountNumber));
        }

        public String getDestAccountField() {
                return destAccountField.getText();
        }

        public  String getAmountField() {
                return transferAmountField.getText();
        }

        public void transfer(ActionListener actionListener) {
                transferButton.addActionListener(actionListener);
        }

        public void displayErrorMessage(String errorMessage) {
                JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }

        public void displayMessage(String message) {
                JOptionPane.showMessageDialog(this, message);
        }
}
