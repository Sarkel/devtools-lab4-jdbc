package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/*
    @author Sebastian Kubalski
*/
public class DbHelper {
    private static final String DB_SCRIPT_PATH = "src/main/resources/init_db.sql";
    private static DbHelper INSTANCE;

    private Connection connection;

    public static DbHelper getInstance() {
        return Optional.ofNullable(INSTANCE)
                .orElseGet(() -> {
                    INSTANCE = new DbHelper();
                    return INSTANCE;
                });
    }

    private DbHelper() {
    }

    public void connect() {
        final ConnectionDetails connectionDetails = getConnectionDetails();

        try {
            connection = DriverManager.getConnection(
                    connectionDetails.url,
                    connectionDetails.user,
                    connectionDetails.password
            );
            System.out.println("Connected to database");
            connection.setAutoCommit(true);
            setup();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return Optional.ofNullable(connection)
                .orElseGet(() -> {
                    this.connect();
                    return connection;
                });
    }

    public void close() throws SQLException {
        getConnection().close();
    }

    // todo: refactor the method to supports larger files. Current assumption - small db files,
    //      that can be loaded directly to memory
    private void setup() throws SQLException, IOException {
        String script = Files.readString(Path.of(DB_SCRIPT_PATH));

        final Statement statement = connection.createStatement();
        statement.executeUpdate(script);
        statement.close();

        System.out.println("Executed DB init script");
    }

    private ConnectionDetails getConnectionDetails() {
        ConfigHelper configHelper = ConfigHelper.getInstance();

        String host = ConfigHelper.getInstance().getProperty("db.host");
        String port = ConfigHelper.getInstance().getProperty("db.port");
        String database = ConfigHelper.getInstance().getProperty("db.database");

        return new ConnectionDetails(
                String.format("jdbc:mysql://%s:%s/%s", host, port, database),
                configHelper.getProperty("db.user"),
                configHelper.getProperty("db.password")
        );
    }

    private record ConnectionDetails(String url, String user, String password) {}
}
