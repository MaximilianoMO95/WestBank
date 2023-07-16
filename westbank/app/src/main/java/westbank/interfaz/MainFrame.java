package westbank.interfaz;

import westbank.acceso.*;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
        private static final int WINDOW_WIDTH = 1200;
        private static final int WINDOW_HEIGHT = 700;

        private CardLayout cardLayout;

        public MainFrame() {
                super("West Bank");

                cardLayout = new CardLayout();

                HomeView home = new HomeView();
                NavBarView navBar = new NavBarView();
                LoginView login = new LoginView();
                TransferView transfer = new TransferView();
                TransferHistoryView transferHistory = new TransferHistoryView();

                new TransferController(transfer);

                setLayout(cardLayout);
                setJMenuBar(navBar);
                
                add(login, "login");
                add(home, "home");
                add(transfer, "transfer");
                add(transferHistory, "transferHistory");
                
                navBar.showHome(e -> cardLayout.show(MainFrame.this.getContentPane(), "home"));
                navBar.showTransferir(e -> cardLayout.show(MainFrame.this.getContentPane(), "transfer"));
                navBar.showHistorialTransferencias(e -> cardLayout.show(MainFrame.this.getContentPane(), "transferHistory"));
                
                ImageIcon imageIcon = new ImageIcon("src/main/java/westbank/assets/favicon.png");
                setIconImage(imageIcon.getImage());

                setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setVisible(true);
        }
}
