package westbank.acceso;

import westbank.utils.Validations;
import westbank.negocio.*;
import westbank.interfaz.TransferHistoryView;
import westbank.interfaz.NavBarView;

import java.util.List;

public class TransferHistoryController {
        private Client srcClient;
        private TransferHistoryView transferView;
        private NavBarView navbar;
        private SqlTransferHistory sqlTransferHist;

        public TransferHistoryController(TransferHistoryView transferV, NavBarView navbarV, Client client) {
                this.transferView = transferV;
                this.sqlTransferHist = new SqlTransferHistory();
                this.srcClient = client;
                this.navbar = navbarV;

                this.navbar.showHistorialTransferencias(e -> {
                        int accountNum = client.getAccount().getAccountNumber();
                        List<TransferHistory> histList = sqlTransferHist.getHistoryBySrcAccount(accountNum);
                        transferView.displayData(histList);
                });
        }
}

