package westbank.acceso;

import westbank.negocio.Client;
import westbank.interfaz.HomeView;
import westbank.interfaz.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private LoginDAO loginDAO;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginDAO = new LoginDAO();

        // Agregar el ActionListener al botón "Ingresar"
        loginView.submitData(new SubmitButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Obtener el RUN y la clave ingresados por el usuario
            String run = loginView.getRun();
            String clave = loginView.getPassword();

            // Autenticar al usuario utilizando el LoginDAO
            Client client = loginDAO.authenticate(run, clave);

            if (client != null) {
                // Autenticación exitosa, mostrar la vista de inicio
                showHomeView(client);
            } else {
                // Autenticación fallida, mostrar mensaje de error
                JOptionPane.showMessageDialog(loginView, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
                loginView.reset();
            }
        }
    }

    private void showHomeView(Client client) {
        // Crear la vista de inicio y pasar los datos del cliente
        HomeView homeView = new HomeView();
        homeView.setClient(client);

        // Crear el JFrame principal y agregar la vista de inicio
        JFrame mainFrame = new JFrame("West Bank");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(homeView);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        // Cerrar la vista de login
        loginView.getTopLevelAncestor().setVisible(false);
    }
}
