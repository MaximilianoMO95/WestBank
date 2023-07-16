package westbank.acceso;

import westbank.negocio.MysqlConnect;
import westbank.negocio.TransferHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlTransferHistory extends MysqlConnect {

        public boolean newTransfer(TransferHistory transferData) {
                boolean created = false;
                String query =  "INSERT INTO historial_transf "
                                + "(cuenta_origen, monto, cuenta_destino) "
                                + "VALUES (?, ?, ?);";

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, transferData.cuentaOrigen);
                        ps.setInt(2, transferData.getMonto());
                        ps.setInt(3, transferData.cuentaDestino);

                        ps.executeUpdate();

                        created = true;

                } catch (SQLException e) {
                        Logger.getLogger(SqlTransferHistory.class.getName()).log(Level.SEVERE, null, e);
                        created = false;
                }

                return created;
        }

        public List<TransferHistory> getHistoryBySrcAccount(int srcAccountNumber) {
                List<TransferHistory> histList = new ArrayList<>();

                String query = "SELECT * FROM historial_transf WHERE cuenta_origen = ?;";

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, srcAccountNumber);
                        ResultSet result = ps.executeQuery();

                        while (result.next()) {
                                int id = result.getInt("id_transaccion");
                                int srcAccount = result.getInt("cuenta_origen");
                                int amount = result.getInt("monto");
                                int destAccount = result.getInt("cuenta_destino");
                                Date timestamp = result.getDate("fecha_hora");

                                TransferHistory transferHist = new TransferHistory(id, srcAccount, amount, destAccount, timestamp);
                                histList.add(transferHist);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlTransferHistory.class.getName()).log(Level.SEVERE, null, e);
                }

                return histList;
        }
}
