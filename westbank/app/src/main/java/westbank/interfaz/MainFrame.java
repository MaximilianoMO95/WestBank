package westbank.interfaz;

import westbank.acceso.*;
import java.awt.*;
import javax.swing.*;
import westbank.negocio.Client;

public class MainFrame extends JFrame {
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;

    private CardLayout cardLayout;
    private LoginView loginView;
    private LoginDAO loginDAO;
    private Client client;

    public MainFrame() {
        super("West Bank");

        cardLayout = new CardLayout();
        loginView = new LoginView();
        loginDAO = new LoginDAO();

        HomeView home = new HomeView();
        NavBarView navBar = new NavBarView();
        TransferView transferir = new TransferView();
        TransferHistoryView transferHistory = new TransferHistoryView();

        setLayout(cardLayout);
        getContentPane().add(loginView, "login");
        setJMenuBar(navBar);
        navBar.setVisible(false);

        add(loginView, "login");
        add(home, "home");
        add(transferir, "transferir");
        add(transferHistory, "transferHistory");

        navBar.showHome(e ->  {
                home.updateData();
                cardLayout.show(MainFrame.this.getContentPane(), "home");
        });
        navBar.showTransferir(e -> cardLayout.show(MainFrame.this.getContentPane(), "transferir"));
        navBar.showHistorialTransferencias(e -> cardLayout.show(MainFrame.this.getContentPane(), "transferHistory"));

        loginView.submitData(e -> {
            // Obtener el RUN y la clave ingresados por el usuario
            String run = loginView.getRun();
            String password = loginView.getPassword();

            // Autenticar al usuario utilizando el LoginDAO
            client = loginDAO.authenticate(run, password);

            if (client != null) {
                // Autenticación exitosa, mostrar la vista de inicio
                home.setClient(client);
                home.updateData();
                cardLayout.show(MainFrame.this.getContentPane(), "home");
                navBar.setVisible(true);

                new TransferController(transferir, client);
                new TransferHistoryController(transferHistory, navBar, client);

            } else {
                // Autenticación fallida, mostrar mensaje de error
                JOptionPane.showMessageDialog(loginView, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
                loginView.reset();
            }
        });

         navBar.exitApp(e -> {
         JOptionPane.showMessageDialog(MainFrame.this, "Has salido de la aplicación", "Salir", JOptionPane.INFORMATION_MESSAGE);
         System.exit(0); });

        ImageIcon imageIcon = new ImageIcon("src/main/java/westbank/assets/favicon.png");
        setIconImage(imageIcon.getImage());

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
