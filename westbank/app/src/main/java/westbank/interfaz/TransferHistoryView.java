package westbank.interfaz;

import westbank.negocio.TransferHistory;

import javax.swing.*;
import javax.swing.border.Border;
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
                super(new GridBagLayout());

                // Set up the grid bag constraints
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(6, 6, 6, 6);
                gbc.fill = GridBagConstraints.BOTH;

                noDataLabel = new JLabel("0 Transferencias Para Mostrar");
                noDataLabel.setFont(noDataLabel.getFont().deriveFont(Font.BOLD, 20f));
                noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
                add(noDataLabel, gbc);
                noDataLabel.setVisible(false);

                Field[] fields = TransferHistory.class.getFields();
                String[] fieldNames = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                        fieldNames[i] = fields[i].getName();
                }
                tableModel = new DefaultTableModel(fieldNames, 0);

                table = new JTable(tableModel);
                table.setFont(table.getFont().deriveFont(Font.PLAIN));
                table.setRowHeight(30);

                scrollPane = new JScrollPane(table);
                gbc.gridy++;
                add(scrollPane, gbc);
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
}
