package westbank.interfaz;

import westbank.negocio.TransferHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.lang.reflect.Field;

public class TransferHistoryView extends JPanel {
        private JTable table;
        private DefaultTableModel tableModel;
        private JScrollPane scrollPane;
        private JLabel noDataLabel;

        public TransferHistoryView() {
                super(new GridBagLayout());

                GridBagConstraints constraints = new GridBagConstraints();
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.fill = GridBagConstraints.BOTH;

                noDataLabel = new JLabel("Historial Vacio");
                noDataLabel.setFont(noDataLabel.getFont().deriveFont(Font.BOLD, 20f));
                noDataLabel.setVisible(false);
                add(noDataLabel, constraints);

                constraints.gridy++;
                constraints.weightx = constraints.weighty = 1.0;

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
                scrollPane.setVisible(false);
                add(scrollPane, constraints);
        }

        public void displayData(List<TransferHistory> historyList) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                if (historyList == null || historyList.isEmpty()) {
                        noDataLabel.setVisible(true);
                        scrollPane.setVisible(false);
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
                                        dateFormat.format(history.getFechaHora())
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
