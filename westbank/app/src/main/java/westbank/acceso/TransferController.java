package westbank.acceso;

import westbank.utils.Validations;
import westbank.negocio.*;
import westbank.interfaz.TransferView;

public class TransferController {
        private TransferView transferView;
        private SqlClients sqlClients;
        private SqlTransferHistory sqlTransferHist;
        private Client srcClient;
        private Client dstClient;

        public TransferController(TransferView transferV, Client client) {
                this.transferView = transferV;
                this.sqlClients = new SqlClients();
                this.sqlTransferHist = new SqlTransferHistory();
                this.srcClient = client;
                this.transferView.setSrcAccountNumber(client.getAccount().getAccountNumber());

                this.transferView.transfer(e -> {
                        int srcAccountNum = client.getAccount().getAccountNumber();
                        String dstAccountNum = transferView.getDestAccountField();

                        if (dstAccountNum.isEmpty()) {
                                transferView.displayErrorMessage("cuenta de destino vacia");
                                return;
                         } else if (!Validations.validateAccountNumber(dstAccountNum)) {
                                transferView.displayErrorMessage("cuenta de destino invalida");
                                return;
                        } else if (Integer.toString(srcAccountNum).equals(dstAccountNum)) {
                                transferView.displayErrorMessage("cuenta de destino debe ser diferente a la de origen");
                                return;
                        }

                        dstClient = sqlClients.searchByAccountNumber(dstAccountNum);

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
                                int destAccount = Integer.parseInt(dstAccountNum);

                                TransferHistory transferHist = new TransferHistory(srcAccountNum, amount, destAccount);
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
                String description = account.getDescription().toUpperCase();

                if (description.equals("CORRIENTE")) {
                        return true;
                }

                return false;
        }
}
