package westbank.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavBarView extends JMenuBar {
        private CustomJMenuItem home = new CustomJMenuItem("Inicio");
        private CustomJMenuItem exit = new CustomJMenuItem("Salir");

        private CustomJMenuItem a1 = new CustomJMenuItem("Transferir");
        private CustomJMenuItem a2 = new CustomJMenuItem("Historial Transferencias");

        public NavBarView() {
                setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

                add(home);
                add(a1);
                add(a2);
                add(exit);

                setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }

        public void showHome(ActionListener actionListener) {
                home.addActionListener(actionListener);
        }

        public void showTransferir(ActionListener actionListener) {
                a1.addActionListener(actionListener);
        }

        public void showHistorialTransferencias(ActionListener actionListener) {
                a2.addActionListener(actionListener);
        }

        public void exitApp(ActionListener actionListener) {
                exit.addActionListener(actionListener);
        }
}

class CustomJMenu extends JMenu {
        private static final int MENU_WIDTH = 200;
        private static final int MENU_HEIGHT = 45;

        public CustomJMenu(String text) {
                super(text);
                setHorizontalAlignment(SwingConstants.CENTER);
        }
    
        @Override
        public Dimension getPreferredSize() {
                return new Dimension(MENU_WIDTH, MENU_HEIGHT);
        }
}

class CustomJMenuItem extends JMenuItem {
        private static final int ITEM_WIDTH = 200;
        private static final int ITEM_HEIGHT = 45;
    
        public CustomJMenuItem(String text) {
                super(text);
                setHorizontalAlignment(SwingConstants.CENTER);
                setFont(getFont().deriveFont(Font.BOLD));
        }

        @Override
        public Dimension getPreferredSize() {
                return new Dimension(ITEM_WIDTH, ITEM_HEIGHT);
        }
}
