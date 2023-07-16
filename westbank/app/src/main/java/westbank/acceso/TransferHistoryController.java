package westbank.acceso;

import westbank.utils.Validations;
import westbank.negocio.*;
import westbank.interfaz.TransferHistoryView;

import java.util.List;

public class TransferHistoryController {
        private TransferHistoryView transferView;
        private SqlClients sqlClients;
        private SqlTransferHistory sqlTransferHist;

        public TransferHistoryController(TransferHistoryView transferV) {
                this.transferView = transferV;
                this.sqlClients = new SqlClients();
                this.sqlTransferHist = new SqlTransferHistory();

                // List<TransferHistory> histList = sqlTransferHist.getHistoryBySrcAccount(123456789);
                // transferView.displayData(histList);
        }
}

