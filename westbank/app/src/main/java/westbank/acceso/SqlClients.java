package westbank.acceso;

import westbank.negocio.MysqlConnect;
import westbank.negocio.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.annotation.Nullable;

public class SqlClients extends MysqlConnect {

        @Nullable
        public Client searchByRun(String run) {
                Client client = null;
                String query = "SELECT * FROM cliente WHERE run = ?";

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, Integer.parseInt(run));
                        ResultSet result = ps.executeQuery();

                        if (result.next()) {
                                int id = result.getInt("id");
                                String dv = result.getString("dv");
                                String clave = result.getString("clave");
                                String name = result.getString("nombre");
                                String ap_paterno = result.getString("ap_paterno");
                                String ap_materno = result.getString("ap_materno");
                                String tel = result.getString("tel");
                                String address = result.getString("domicilio");
                                String comuna = result.getString("comuna");

                                String tipoCuenta = result.getString("tipo_cuenta");
                                int saldo = result.getInt("saldo_cuenta");
                                int numeroCuenta = result.getInt("numero_cuenta");

                                Account account = new Account(numeroCuenta, saldo, tipoCuenta);

                                client = new Client(id, run, dv, clave, name, ap_paterno, ap_materno, address, comuna, tel, account);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }

        @Nullable
        public Client searchByRun(String run, String dv) {
                String query = "SELECT * FROM cliente WHERE run = ? AND dv = ?";
                Client client = null;

                PreparedStatement preparedStatement = null;

                try {
                        Connection conn = connect();
                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setString(1, run);
                        preparedStatement.setString(2, dv);
                        ResultSet result = preparedStatement.executeQuery();

                        if (result.next()) {
                                int id = result.getInt("id");
                                String clave = result.getString("clave");
                                String name = result.getString("nombre");
                                String ap_paterno = result.getString("ap_paterno");
                                String ap_materno = result.getString("ap_materno");
                                String tel = result.getString("tel");
                                String address = result.getString("domicilio");
                                String comuna = result.getString("comuna");

                                String tipoCuenta = result.getString("tipo_cuenta");
                                int saldo = result.getInt("saldo_cuenta");
                                int numeroCuenta = result.getInt("numero_cuenta");

                                Account account = new Account(numeroCuenta, saldo, tipoCuenta);

                                client = new Client(id, run, dv, clave,  name, ap_paterno, ap_materno, address, comuna, tel, account);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }

        @Nullable
        public Client searchByAccountNumber(String number) {
                String query = "SELECT * FROM cliente WHERE numero_cuenta = ?";
                Client client = null;

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1, Integer.parseInt(number));
                        ResultSet result = ps.executeQuery();

                        if (result.next()) {
                                String run = Integer.toString(result.getInt("run"));
                                int id = result.getInt("id");
                                String dv = result.getString("dv");
                                String clave = result.getString("clave");
                                String name = result.getString("nombre");
                                String ap_paterno = result.getString("ap_paterno");
                                String ap_materno = result.getString("ap_materno");
                                String tel = result.getString("tel");
                                String address = result.getString("domicilio");
                                String comuna = result.getString("comuna");

                                String tipoCuenta = result.getString("tipo_cuenta");
                                int saldo = result.getInt("saldo_cuenta");
                                int numeroCuenta = result.getInt("numero_cuenta");

                                Account account = new Account(numeroCuenta, saldo, tipoCuenta);

                                client = new Client(id, run, dv, clave, name, ap_paterno, ap_materno, address, comuna, tel, account);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }

        @Nullable
        public Client searchByAccountNumber(int number) {
                String query = "SELECT * FROM cliente WHERE numero_cuenta = ?";
                Client client = null;

                try {
                        Connection conn = connect();
                        PreparedStatement ps = conn.prepareStatement(query);

                        ps.setInt(1,number);
                        ResultSet result = ps.executeQuery();

                        if (result.next()) {
                                String run = Integer.toString(result.getInt("run"));
                                int id = result.getInt("id");
                                String dv = result.getString("dv");
                                String clave = result.getString("clave");
                                String name = result.getString("nombre");
                                String ap_paterno = result.getString("ap_paterno");
                                String ap_materno = result.getString("ap_materno");
                                String tel = result.getString("tel");
                                String address = result.getString("domicilio");
                                String comuna = result.getString("comuna");

                                String tipoCuenta = result.getString("tipo_cuenta");
                                int saldo = result.getInt("saldo_cuenta");
                                int numeroCuenta = result.getInt("numero_cuenta");

                                Account account = new Account(numeroCuenta, saldo, tipoCuenta);

                                client = new Client(id, run, dv, clave, name, ap_paterno, ap_materno, address, comuna, tel, account);
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                }

                return client;
        }

        public boolean transfer(Client srcClient, Client dstClient, int amount) {
                String query1 = "UPDATE cliente SET saldo_cuenta = saldo_cuenta - ? WHERE id = ?";
                String query2 = "UPDATE cliente SET saldo_cuenta = saldo_cuenta + ? WHERE id = ?";
                boolean success = false;

                try {
                        Connection conn = connect();
                        PreparedStatement srcClientps = conn.prepareStatement(query1);
                        PreparedStatement dstClientps = conn.prepareStatement(query2);

                        srcClientps.setInt(1, amount);
                        srcClientps.setInt(2, srcClient.id);

                        dstClientps.setInt(1, amount);
                        dstClientps.setInt(2, dstClient.id);

                        int rowsAffected = srcClientps.executeUpdate();
                        rowsAffected += dstClientps.executeUpdate();
                        if (rowsAffected > 1) {
                            success = true;
                        }

                } catch (SQLException e) {
                        Logger.getLogger(SqlClients.class.getName()).log(Level.SEVERE, null, e);
                        success = false;
                }

                return success;
        }
}

