package westbank.negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Logger;
import java.util.logging.Level;

public class MysqlConnect {
        private String DB_URL = "jdbc:mysql://127.0.0.1:3306/westbank";
        private String DB_USER = "myuser";
        private String DB_PASSWORD = "mypassword";

        private Connection connection;

        // Default
        public MysqlConnect() {}

        public MysqlConnect(String DB_URL, String DB_USER, String DB_PASSWORD) {
                this.DB_URL = DB_URL;
                this.DB_USER = DB_USER;
                this.DB_PASSWORD = DB_PASSWORD;
        }

        public Connection connect() {
                // Create new connection
                if (connection == null) {
                        try {
                                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                        } catch (SQLException e) {
                                Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, e);
                        }
                }

                return connection;
        }

        public void disconnect() {
                if (connection == null) {
                        return;
                }

                try {
                        connection.close();
                        connection = null;

                } catch (SQLException e) {
                        Logger.getLogger(MysqlConnect.class.getName()).log(Level.SEVERE, null, e);
                }
        }
}
