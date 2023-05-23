package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;

public class ConnectionUtility {
    private static final Logger logger = LogManager.getLogger("io.github.worthant.lab8.ConnectionUtility");

    private static Connection connection = null;

    private ConnectionUtility() {}

    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                Properties info = new Properties();
                info.load(ConnectionUtility.class.getResourceAsStream("/db.cfg"));
                // "jdbc:postgresql://localhost:5432/studs"
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", info);
            } catch (SQLException | IOException e) {
                logger.error("something went wrong during database connection initialization", e);
            }
        }
        return connection;
    }
}
