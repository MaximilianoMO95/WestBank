package westbank;

import westbank.negocio.MysqlConnect;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

class DbConnectionTest {
        private static final MysqlConnect mysqlConn = new MysqlConnect(
                "jdbc:mysql://127.0.0.1:3306/westbank",
                "javatest",
                "Facil-123"
        );

        @Test
        void testDatabaseConnection() {
                try (Connection connection = mysqlConn.connect()) {
                        assertNotNull(connection, "Connection should not be null");
                        assertTrue(connection.isValid(5), "Connection should be valid");

                        System.out.println("Database connection successful!");
                        mysqlConn.disconnect();
                } catch (SQLException e) {
                        fail("Error connecting to the database: " + e.getMessage());
                }
        }
}

