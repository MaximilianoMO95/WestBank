package westbank.negocio;

import java.util.Date;

public class TransferHistory {
        public int idTransaccion;
        public int cuentaOrigen;
        public int monto;
        public int cuentaDestino;
        public Date fechaHora;

        public TransferHistory(int srcAccount, int amount, int destAccount) {
                this.cuentaOrigen = srcAccount;
                this.monto = amount;
                this.cuentaDestino = destAccount;
        }

        public TransferHistory(int id, int srcAccount, int amount, int destAccount, Date timestamp) {
                this.idTransaccion = id;
                this.cuentaOrigen = srcAccount;
                this.monto = amount;
                this.cuentaDestino = destAccount;
                this.fechaHora = timestamp;
        }

        public int getMonto() {
                return monto;
        }

        public Date getFechaHora() {
                return fechaHora;
        }
}
