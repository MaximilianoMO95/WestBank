package westbank;

import com.formdev.flatlaf.FlatIntelliJLaf;
import westbank.interfaz.MainFrame;

import javax.swing.*;


public class App {
        public static void main(String[] args) {
                FlatIntelliJLaf.setup();
                SwingUtilities.invokeLater(MainFrame::new);
        }
}

