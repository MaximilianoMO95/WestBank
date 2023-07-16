package westbank.acceso;

import westbank.negocio.Client;
import westbank.negocio.MysqlConnect;
import westbank.negocio.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private MysqlConnect mysqlConnect;

    public LoginDAO() {
        mysqlConnect = new MysqlConnect();
    }

    public Client authenticate(String run, String clave) {
        String query = "SELECT * FROM cliente WHERE run = ? AND clave = ?";
        Client client = null;

        try (Connection connection = mysqlConnect.connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, run);
            statement.setString(2, clave);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Obtener los datos del cliente de la base de datos
                int clientId = resultSet.getInt("id");
                String clientRun = resultSet.getString("run");
                String clientDv = resultSet.getString("dv");
                String clientClave = resultSet.getString("clave");
                String clientNombre = resultSet.getString("nombre");
                String clientApPaterno = resultSet.getString("ap_paterno");
                String clientApMaterno = resultSet.getString("ap_materno");
                String clientAddress = resultSet.getString("domicilio");
                String clientComuna = resultSet.getString("comuna");
                String clientTel = resultSet.getString("tel");
                int accountNumber = resultSet.getInt("numero_cuenta");
                int accountBalance = resultSet.getInt("saldo_cuenta");
                String accountType = resultSet.getString("tipo_cuenta");

                // Crear una instancia de Client y Account con los datos obtenidos
                Account account = new Account(accountNumber, accountBalance, accountType);
                client = new Client(clientId, clientRun, clientDv, clientClave, clientNombre, clientApPaterno,
                        clientApMaterno, clientAddress, clientComuna, clientTel, account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }
}
