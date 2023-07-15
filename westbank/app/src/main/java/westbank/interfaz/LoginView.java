package westbank.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginView extends JPanel {
        private JTextField runField;
        private JPasswordField passwordField;
        private JButton submitButton;

        public LoginView() {
                super(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();

                // Title
                JLabel titleLabel = new JLabel("Bienvenido(a) a Westbank");
                Font titleFont = titleLabel.getFont();
                Font newFont = titleFont.deriveFont(titleFont.getStyle() | Font.BOLD, 30f);
                titleLabel.setFont(newFont);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.insets = new Insets(0, 0, 50, 0);
                gbc.anchor = GridBagConstraints.CENTER;
                add(titleLabel, gbc);

                // Run field
                JLabel runLabel = new JLabel("Run");
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(0, 0, 5, 5);
                gbc.anchor = GridBagConstraints.LINE_END;
                add(runLabel, gbc);

                runField = new JTextField(20);
                gbc.gridx = 1;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(0, 0, 5, 0);
                gbc.anchor = GridBagConstraints.LINE_START;
                add(runField, gbc);

                // Password field
                JLabel passwordLabel = new JLabel("Clave");
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(0, 0, 5, 5);
                gbc.anchor = GridBagConstraints.LINE_END;
                add(passwordLabel, gbc);

                passwordField = new JPasswordField(20);
                gbc.gridx = 1;
                gbc.gridy = 2;
                gbc.gridwidth = 1;
                gbc.insets = new Insets(0, 0, 5, 0);
                gbc.anchor = GridBagConstraints.LINE_START;
                add(passwordField, gbc);

                // Submit button
                submitButton = new JButton("Ingresar");
                gbc.gridx = 0;
                gbc.gridy = 3;
                gbc.gridwidth = 2;
                gbc.insets = new Insets(20, 0, 0, 0);
                gbc.anchor = GridBagConstraints.CENTER;
                add(submitButton, gbc);
        }

        public String getRun() {
                return runField.getText();
        }

        public String getPassword() {
                return new String(passwordField.getPassword());
        }

        public void submitData(ActionListener actionListener) {
                submitButton.addActionListener(actionListener);
        }

        public void reset() {
                runField.setText("");
                passwordField.setText("");
        }
}
