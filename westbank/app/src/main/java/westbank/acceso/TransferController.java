package westbank.acceso;

import westbank.utils.Validations;
import westbank.negocio.*;
import westbank.interfaz.TransferView;

public class TransferController {
        private TransferView transferView;
        private SqlClients sqlClients;
        private SqlTransferHistory sqlTransferHist;

        public TransferController(TransferView transferV) {
                this.transferView = transferV;
                this.sqlClients = new SqlClients();
                this.sqlTransferHist = new SqlTransferHistory();

                this.transferView.transfer(e -> {
                        String srcAccountNum = "123";
                        String dstAccountNum = transferView.getDestAccountField();

                        if (dstAccountNum.isEmpty()) {
                                transferView.displayErrorMessage("cuenta de destino vacia");
                                return;
                         } else if (!Validations.validateAccountNumber(dstAccountNum)) {
                                transferView.displayErrorMessage("cuenta de destino invalida");
                                return;
                        }

                        Client srcClient = sqlClients.searchByAccountNumber(srcAccountNum);
                        Client dstClient = sqlClients.searchByAccountNumber(dstAccountNum);

                        if (dstClient == null) {
                                transferView.displayErrorMessage("Cuenta de destino no encontrada");
                                return;
                        } else if (!isCurrentAccount(srcClient)) {
                                transferView.displayErrorMessage("Cuenta de origen debe ser cuenta corriente");
                                return;
                        } else if (!isCurrentAccount(dstClient)) {
                                transferView.displayErrorMessage("Cuenta de destino debe ser cuenta corriente");
                                return;
                        }

                        int amount = Integer.parseInt(transferView.getAmountField());

                        if (srcClient.getAccount().checkBalance() < amount) {
                                transferView.displayErrorMessage("Fondos insuficientes");
                                return;
                        } else if (amount <= 0) {
                                transferView.displayErrorMessage("Cantidad invalida");
                                return;
                        }

                        if (sqlClients.transfer(srcClient, dstClient, amount)) {
                                int srcAccount = Integer.parseInt(srcAccountNum);
                                int destAccount = Integer.parseInt(dstAccountNum);

                                TransferHistory transferHist = new TransferHistory(srcAccount, amount, destAccount);
                                sqlTransferHist.newTransfer(transferHist);
                                
                                srcClient.getAccount().moneyTransfer(amount, dstClient.getAccount());
                                transferView.displayMessage("Transferencia realizada con éxito. Nuevo saldo: " + srcClient.getAccount().checkBalance());
                        } else {
                                transferView.displayErrorMessage("Problemas con la base de datos");
                        }

                });
                
        }

        private boolean isCurrentAccount(Client client) {
                Account account = client.getAccount();
                if (account.getDescription().equals("Corriente")) {
                        return true;
                }

                return false;
        }
}
