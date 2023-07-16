package westbank.interfaz;

import westbank.negocio.TransferHistory;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.lang.reflect.Field;

public class TransferHistoryView extends JPanel {
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;
        private JLabel noDataLabel;

        public TransferHistoryView() {
                super(new BorderLayout());

                noDataLabel = new JLabel("Historial Vacio");
                noDataLabel.setFont(noDataLabel.getFont().deriveFont(Font.BOLD, 20f));
                noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(noDataLabel, BorderLayout.CENTER);
                noDataLabel.setVisible(false);

                Field[] fields = TransferHistory.class.getFields();
                String[] fieldNames = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                        fieldNames[i] = convertToSnakeCase(fields[i].getName());
                }
                tableModel = new DefaultTableModel(fieldNames, 0);

                table = new JTable(tableModel);
                table.setFont(table.getFont().deriveFont(Font.PLAIN));
                table.setRowHeight(30);

                // Center align the data in the table cells
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                for (int i = 0; i < table.getColumnCount(); i++) {
                        table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }

                scrollPane = new JScrollPane(table);
                add(scrollPane, BorderLayout.CENTER);
                scrollPane.setVisible(false);
        }

        public void displayData(List<TransferHistory> historyList) {
                if (historyList == null || historyList.isEmpty()) {
                        scrollPane.setVisible(false);
                        noDataLabel.setVisible(true);
                } else {
                        noDataLabel.setVisible(false);
                        scrollPane.setVisible(true);
                        clearTable();

                        for (TransferHistory history : historyList) {
                                Object[] rowData = {
                                        history.idTransaccion,
                                        history.cuentaOrigen,
                                        history.getMonto(),
                                        history.cuentaDestino,
                                        history.getFechaHora()
                                };

                                tableModel.addRow(rowData);
                        }
                }

                revalidate();
                repaint();
        }

        public void clearTable() {
                tableModel.setRowCount(0);
        }

        private static String convertToSnakeCase(String input) {
                // Use regular expression to insert an underscore before each capital letter
                String snakeCase = input.replaceAll("(.)(\\p{Upper})", "$1_$2");

                // Convert the result to lowercase
                snakeCase = snakeCase.toLowerCase();

                return snakeCase;
        }
}
